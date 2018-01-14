package net.fumfy.examples.bluetoothle.ui.detail.fragment.view;

import net.fumfy.examples.bluetoothle.commons.view.MvpView;
import net.fumfy.examples.bluetoothle.model.BleEntry;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 21/12/2017.
 *
 * @author Simon Barnes
 */
public interface BleScanDetailView extends MvpView {

	void renderScanDetail(BleEntry entry);

}
