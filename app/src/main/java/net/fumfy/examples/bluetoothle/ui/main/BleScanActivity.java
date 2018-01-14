package net.fumfy.examples.bluetoothle.ui.main;

import net.fumfy.examples.bluetoothle.commons.BaseActivityInterface;
import net.fumfy.examples.bluetoothle.model.BleEntry;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 15/12/2017.
 *
 * @author Simon Barnes
 */
public interface BleScanActivity extends BaseActivityInterface {

	void onScanEntryClick(BleEntry bleDevice);
}
