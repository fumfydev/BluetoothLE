package net.fumfy.examples.bluetoothle.commons;

import com.squareup.leakcanary.LeakCanary;

import net.fumfy.examples.bluetoothle.BuildConfig;
import net.fumfy.examples.bluetoothle.commons.timber.ReleaseTree;

import dagger.android.support.DaggerApplication;
import timber.log.Timber;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 15/12/2017.
 *
 * @author Simon Barnes
 */
public abstract class BaseApplication extends DaggerApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		} else {
			Timber.plant(new ReleaseTree());
		}
	}
}
