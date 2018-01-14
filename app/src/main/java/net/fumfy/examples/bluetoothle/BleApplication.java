package net.fumfy.examples.bluetoothle;

import net.fumfy.examples.bluetoothle.commons.BaseApplication;
import net.fumfy.examples.bluetoothle.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 10/12/2017.
 *
 * @author Simon Barnes
 */

public class BleApplication extends BaseApplication {

	@Override
	protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
		return DaggerAppComponent
			.builder()
			.application(this)
			.build();
	}

}
