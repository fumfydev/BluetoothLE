package net.fumfy.examples.bluetoothle.ui.detail.fragment.presenter;

import net.fumfy.examples.bluetoothle.commons.presenter.MvpPresenter;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.view.BleScanDetailView;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 21/12/2017.
 *
 * @author Simon Barnes
 */
public interface BleScanDetailPresenter extends MvpPresenter<BleScanDetailView> {

	void setModel(BleEntry entry);

}
