package net.fumfy.examples.bluetoothle.ui.scan.fragment.view;

import android.content.Intent;
import android.support.annotation.Nullable;

import net.fumfy.examples.bluetoothle.commons.view.MvpView;
import net.fumfy.examples.bluetoothle.model.BleEntry;

import java.util.List;


/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 15/12/2017.
 *
 * @author Simon Barnes
 */
public interface BleScanView extends MvpView {

	void renderBleDevices(@Nullable List<BleEntry> list);

	void startProgressView();

	void stopProgressView();

	void showMessage(String message);

	void showScanningMessage(String message);

	void showMessageThenFinish(String message);

	void requestPermissions(String[] permissions, int requestCode);

	void startActivityForResult(Intent enableBtIntent, int enableBtRequest);
}
