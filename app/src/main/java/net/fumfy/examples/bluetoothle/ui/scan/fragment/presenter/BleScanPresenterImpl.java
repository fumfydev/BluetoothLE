package net.fumfy.examples.bluetoothle.ui.scan.fragment.presenter;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.polidea.rxandroidble.scan.ScanResult;

import net.fumfy.examples.bluetoothle.BuildConfig;
import net.fumfy.examples.bluetoothle.R;
import net.fumfy.examples.bluetoothle.commons.presenter.BaseMvpPresenter;
import net.fumfy.examples.bluetoothle.di.scope.ActivityScope;
import net.fumfy.examples.bluetoothle.domain.data.Constants;
import net.fumfy.examples.bluetoothle.domain.interactor.GetBleDevicesInteractor;
import net.fumfy.examples.bluetoothle.domain.prefs.SharedPrefsWrapper;
import net.fumfy.examples.bluetoothle.domain.thread.InteractorThread;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.model.BleModel;
import net.fumfy.examples.bluetoothle.ui.main.BleScanActivity;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.view.BleScanView;
import net.fumfy.examples.bluetoothle.ui.scan.utils.PresenterHelper;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/11/2017.
 *
 * @author Simon Barnes
 */
@ActivityScope
public class BleScanPresenterImpl extends BaseMvpPresenter<BleScanView>
	implements BleScanPresenter {
	private static final String SAVED_STATE_MODEL = "model";

	private BleModel model;
	private GetBleDevicesInteractor bleDevicesInteractor;
	private PresenterHelper presenterHelper;
	private Subscription scanSubscription;
	private InteractorThread mainThread;
	private SharedPrefsWrapper prefs;
	private BluetoothAdapter bluetoothAdapter;

	private Context context;
	private BleScanActivity bleScanActivity;

	private boolean attached = false;
	private long scanTimerLength;
	private CompositeSubscription compositeSubscription = new CompositeSubscription();

	@Inject
	BleScanPresenterImpl(BleModel model,
	                     GetBleDevicesInteractor bleDevicesInteractor,
	                     @Named("observerThread") InteractorThread mainThread,
	                     SharedPrefsWrapper prefs,
	                     PresenterHelper presenterHelper,
	                     @Nullable BluetoothAdapter bluetoothAdapter) {
		this.model = model;
		this.bleDevicesInteractor =  bleDevicesInteractor;
		this.mainThread = mainThread;
		this.prefs = prefs;
		this.presenterHelper = presenterHelper;
		this.bluetoothAdapter = bluetoothAdapter;
	}

	@Override
	public void initPresenter(Context activityContext) {
		context = activityContext;
		if (context instanceof BleScanActivity) {
			bleScanActivity = (BleScanActivity) context;
		} else {
			throw new RuntimeException(
				"This presenter must be initialised by an activity implementing BleScanActivity");
		}
	}

	@Override
	public void attach(BleScanView view, Bundle savedState) {
		super.attach(view, savedState);
		attached = true;
		if (savedState == null) {
			model.clear();
			if (checkBluetoothAndLocationPermissionsOk()) {
				// Start scan
				startScan();
			}
		} else {
			if (savedState.containsKey(SAVED_STATE_MODEL)) {
				model.from(savedState.getParcelable(SAVED_STATE_MODEL));
				this.view.renderBleDevices((model!=null?model.getList():null));
				this.view.showMessage(model.getMessage());
			}
		}

		if (model.isScanning()) {
			startScan();
		}
	}

	@Override
	public void detach() {
		super.detach();
		attached = false;
		bleScanActivity = null;
		context = null;
	}

	@Override
	public void onClickBleEntry(BleEntry bleDevice) {
		bleScanActivity.onScanEntryClick(bleDevice);
	}

	@Override
	public void onStopScan() {
		compositeSubscription.clear();
		model.setScanTimerCount(0);
		scanForBleOnCompleted();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(SAVED_STATE_MODEL, model);
	}

	@Override
	public void onDestroy() {
		compositeSubscription.clear();
		detach();
	}

	private boolean checkPermissions() {
		if (presenterHelper.checkPermission(ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
			requestLocationPermission();
			return false;
		} else {
			return true;
		}
	}

	private boolean checkBluetoothAndLocationPermissionsOk() {
		boolean result = false;

		if ((bluetoothAdapter == null) || (!presenterHelper.checkFeatureBluetoothLe())) {
			// Error bluetooth or BluetoothLe is not supported by this device show error
			model.setMessage(context.getString(R.string.DEVICE_NOT_SUPPORTED));
			view.showMessageThenFinish(model.getMessage());
		} else if (!bluetoothAdapter.isEnabled()) {
			enableBluetooth();
		} else if (presenterHelper.checkPermission(ACCESS_COARSE_LOCATION)
			!= PERMISSION_GRANTED) {
			requestLocationPermission();
		} else if (!model.isScanning()){
			// Start scan
			result = true;
		}

		return result;
	}


	private void enableBluetooth() {
		Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		view.startActivityForResult(enableBtIntent,	Constants.ENABLE_BT_REQUEST);
	}

	private void requestLocationPermission() {
		String[] permissions;
		if (BuildConfig.DEBUG) {
			permissions = new String[]{
				Manifest.permission.ACCESS_COARSE_LOCATION,
				// For LeakCanary leak detection in debug build only
				Manifest.permission.WRITE_EXTERNAL_STORAGE
			};
		} else {
			permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
		}
		view.requestPermissions(permissions, Constants.ENABLE_LOCATION_REQUEST);
	}

	/**
	 * Called when a swipe gesture triggers a refresh.
	 */
	@Override
	public void onRefresh() {
		if (!model.isScanning()) {
			if (checkBluetoothAndLocationPermissionsOk()) {
				model.clear();
				view.renderBleDevices(null);
				startScan();
			}
		}
	}

	@Override
	public void bluetoothEnabled(boolean enabled) {
		if (enabled) {
			// Continue to scan
			if (checkPermissions() && ((attached) && (!model.isScanning()))) {
				startScan();
			}

		} else {
			if (attached) {
				// show error
				model.setMessage(context.getString(R.string.BT_ACCESS_REQUIRED));
				view.showMessage(model.getMessage());
				view.stopProgressView();
			}
		}
	}

	@Override
	public void locationEnabled(boolean enabled) {
		if (enabled) {
			// Continue to scan
			startScan();
		} else {
			model.setMessage(context.getString(R.string.LOCATION_ACCESS_REQUIRED));
			view.showMessage(model.getMessage());
			view.stopProgressView();
		}
	}

	private void startScan() {
		model.setScanning(true);
		model.setMessage(context.getString(R.string.SCANNING));
		view.showScanningMessage(model.getMessage());
		view.startProgressView();
		scanForBle();
	}

	private void scanForBle() {
		Timber.d("in scanForBle()");
		int scanTimerCount = model.getScanTimerCount();
		final long scanTime = (scanTimerCount==0?
			prefs.readLong(Constants.pref_scan_duration, Constants.DEFAULT_SCAN_DURATION)
			:scanTimerCount);
		scanTimerLength = scanTime;

		if (compositeSubscription.hasSubscriptions()) {
			compositeSubscription.clear();
		}

		Subscription timerSubscription = Observable
			.interval(Constants.scan_timer_interval, Constants.scan_time_unit)
			.takeUntil(interval -> interval == scanTime)
			.observeOn(mainThread.scheduler())
			.doOnSubscribe(() -> scanSubscription = bleDevicesInteractor
				.interact()
				.doOnNext(this::scanForBleOnNext)
				.doOnError(this::scanForBleOnError)
				.subscribe())
			.doOnNext(this::showScanTimerMessage)
			.doOnCompleted(this::scanForBleOnCompleted)
			.subscribe();

		compositeSubscription.addAll(scanSubscription, timerSubscription);
	}

	private void showScanTimerMessage(Long interval) {
		model.setScanTimerCount((int) (scanTimerLength - interval));
		int scanTimerCount = model.getScanTimerCount();
		model.setMessage(context
			.getResources().getQuantityString(R.plurals.scanning_timer, scanTimerCount, scanTimerCount));
		view.showScanningMessage(model.getMessage());
	}

	private void scanForBleOnNext(ScanResult scanResult) {
		Timber.d("in scanForBleOnNext()");
		if (!model.listContains(scanResult.getBleDevice())) {
			model.addDevice(scanResult.getBleDevice(),
				scanResult.getRssi(), scanResult.getScanRecord());
			view.renderBleDevices(model.getList());
		}
	}

	private void scanForBleOnCompleted() {
		Timber.d("in scanForBleOnCompleted()");
		scanSubscription.unsubscribe();
		int count = model.getItemCount();
		Resources res = context.getResources();
		model.setMessage(res.getQuantityString(R.plurals.numberOfDevices, count, count));

		if (attached) {
			view.stopProgressView();
			view.showMessage(model.getMessage());
		} else {
			Timber.e("onBleDeviceScanFinished view not attach");
		}
		model.setScanning(false);
	}


	private void scanForBleOnError(Throwable throwable) {
		Timber.e(throwable, "in scanForBleOnError()");
	}

}
