package net.fumfy.examples.bluetoothle.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 17/12/2017.
 *
 * @author Simon Barnes
 */
@Singleton
@Component(
	modules = {
		AndroidSupportInjectionModule.class,
		AppModule.class,
		ActivityBindingsModule.class
	})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

	@Override
	void inject(DaggerApplication instance);

	@Component.Builder
	interface Builder {

		@BindsInstance
		AppComponent.Builder application(Application application);

		AppComponent build();
	}
}
