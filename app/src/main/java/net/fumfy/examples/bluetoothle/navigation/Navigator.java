package net.fumfy.examples.bluetoothle.navigation;

import android.content.Context;

import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.detail.BleScanDetailActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 22/12/2017.
 *
 * @author Simon Barnes
 */
@Singleton
public class Navigator {

	@Inject
	Navigator() {
	}

	public void toDetailActivity(Context context, BleEntry bleEntry) {
		BleScanDetailActivity.startBleScanDetailActivity(context, bleEntry);
	}
}
