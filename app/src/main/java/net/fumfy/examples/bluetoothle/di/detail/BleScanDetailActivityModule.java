package net.fumfy.examples.bluetoothle.di.detail;

import android.support.v7.app.AppCompatActivity;

import net.fumfy.examples.bluetoothle.di.commons.BaseActivityModule;
import net.fumfy.examples.bluetoothle.di.detail.fragment.presenter.BleScanDetailPresenterModule;
import net.fumfy.examples.bluetoothle.di.detail.fragment.view.BleScanDetailFragmentModule;
import net.fumfy.examples.bluetoothle.di.scope.ActivityScope;
import net.fumfy.examples.bluetoothle.di.scope.FragmentScope;
import net.fumfy.examples.bluetoothle.ui.detail.BleScanDetailActivity;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.view.BleScanDetailFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/12/2017.
 *
 * @author Simon Barnes
 */
@Module(subcomponents = {BleScanDetailActivitySubComponent.class},
		includes = {BleScanDetailPresenterModule.class, BaseActivityModule.class})
public abstract class BleScanDetailActivityModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = BleScanDetailFragmentModule.class)
	abstract BleScanDetailFragment bleScanDetailFragmentInjector();

	@Binds
	@ActivityScope
	abstract AppCompatActivity activity(BleScanDetailActivity bleScanDetailActivity);
}
