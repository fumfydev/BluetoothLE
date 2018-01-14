package net.fumfy.examples.bluetoothle.di.main;

import android.support.v7.app.AppCompatActivity;

import net.fumfy.examples.bluetoothle.di.commons.BaseActivityModule;
import net.fumfy.examples.bluetoothle.di.scan.fragment.presenter.BleScanPresenterModule;
import net.fumfy.examples.bluetoothle.di.scan.fragment.view.BleScanListFragmentModule;
import net.fumfy.examples.bluetoothle.di.scope.ActivityScope;
import net.fumfy.examples.bluetoothle.di.scope.FragmentScope;
import net.fumfy.examples.bluetoothle.ui.main.BleScanMainActivity;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.view.BleScanListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 15/12/2017.
 *
 * @author Simon Barnes
 */
@Module(subcomponents = {BleScanMainActivitySubcomponent.class},
		includes = {BaseActivityModule.class,
			BleScanPresenterModule.class})
public abstract class BleScanMainActivityModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = BleScanListFragmentModule.class)
	abstract BleScanListFragment bleScanListFragmentInjector();

	@Binds
	@ActivityScope
	abstract AppCompatActivity activity(BleScanMainActivity bleScanMainActivity);
}
