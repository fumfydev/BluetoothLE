package net.fumfy.examples.bluetoothle.domain.interactor;

import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.scan.ScanResult;
import com.polidea.rxandroidble.scan.ScanSettings;

import net.fumfy.examples.bluetoothle.domain.thread.InteractorThread;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 17/12/2017.
 *
 * @author Simon Barnes
 */

public class GetBleDevicesInteractor {
	private RxBleClient client;
	private InteractorThread workerThread;
	private InteractorThread observerThread;

	@Inject
	public GetBleDevicesInteractor(RxBleClient client,
	                               @Named("workerThread")InteractorThread workerThread,
	                               @Named("observerThread")InteractorThread observerThread) {
		this.client = client;
		this.workerThread = workerThread;
		this.observerThread = observerThread;
	}

	public Observable<ScanResult> interact() {
		return client.scanBleDevices(
			new ScanSettings.Builder()
				.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
				.setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
				.build())
			.subscribeOn(workerThread.scheduler())
			.observeOn(observerThread.scheduler())
			.distinct(scanResult -> scanResult.getBleDevice().getMacAddress());
	}

}
