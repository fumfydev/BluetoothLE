package net.fumfy.examples.bluetoothle.ui.scan.fragment.presenter;

import android.content.Context;

import net.fumfy.examples.bluetoothle.commons.presenter.MvpPresenter;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.view.BleScanView;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 15/12/2017.
 *
 * @author Simon Barnes
 */
public interface BleScanPresenter extends MvpPresenter<BleScanView> {

	void onRefresh();

	void bluetoothEnabled(boolean enabled);

	void locationEnabled(boolean enabled);

	void onClickBleEntry(BleEntry entry);

	void onStopScan();

	void initPresenter(Context activityContext);
}
