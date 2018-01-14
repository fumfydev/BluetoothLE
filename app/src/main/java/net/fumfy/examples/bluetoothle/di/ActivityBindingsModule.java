package net.fumfy.examples.bluetoothle.di;

import net.fumfy.examples.bluetoothle.di.detail.BleScanDetailActivityModule;
import net.fumfy.examples.bluetoothle.di.main.BleScanMainActivityModule;
import net.fumfy.examples.bluetoothle.di.scope.ActivityScope;
import net.fumfy.examples.bluetoothle.ui.detail.BleScanDetailActivity;
import net.fumfy.examples.bluetoothle.ui.main.BleScanMainActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 17/12/2017.
 *
 * @author Simon Barnes
 */
@Module(includes = AndroidInjectionModule.class)
abstract class ActivityBindingsModule {
	@ActivityScope
	@ContributesAndroidInjector(modules = { BleScanMainActivityModule.class} )
	abstract BleScanMainActivity bleScanActivityInjector();

	@ActivityScope
	@ContributesAndroidInjector(modules = { BleScanDetailActivityModule.class })
	abstract BleScanDetailActivity bleScanDetailActivity();

}
