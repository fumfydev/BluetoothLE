package net.fumfy.examples.bluetoothle.ui.scan.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 13/12/2017.
 *
 * @author Simon Barnes
 */

public class PresenterHelper {
	private Context context;

	@Inject
	public PresenterHelper(@Named("app.context") Context context) {
		this.context = context;
	}

	public int checkPermission(String permission) {
		return ContextCompat.checkSelfPermission(context, permission);
	}

	public boolean checkFeatureBluetoothLe() {
		return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
	}
}

