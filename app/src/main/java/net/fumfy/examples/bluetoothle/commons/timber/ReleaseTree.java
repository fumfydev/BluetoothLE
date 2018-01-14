package net.fumfy.examples.bluetoothle.commons.timber;

import android.support.annotation.Nullable;
import android.util.Log;

import timber.log.Timber;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 15/12/2017.
 *
 * @author Simon Barnes
 */
public class ReleaseTree extends Timber.Tree {

	@Override
	protected void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable t) {
		if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
			//noinspection UnnecessaryReturnStatement
			return;
		}
		// No Logging in demo release/non debug build
	}
}
