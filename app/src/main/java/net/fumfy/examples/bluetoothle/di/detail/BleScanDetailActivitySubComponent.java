package net.fumfy.examples.bluetoothle.di.detail;

import net.fumfy.examples.bluetoothle.di.detail.fragment.presenter.BleScanDetailPresenterModule;
import net.fumfy.examples.bluetoothle.di.detail.fragment.view.BleScanDetailFragmentModule;
import net.fumfy.examples.bluetoothle.ui.detail.BleScanDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/12/2017.
 *
 * @author Simon Barnes
 */
@Subcomponent(modules = {
	BleScanDetailFragmentModule.class,
	BleScanDetailPresenterModule.class
})
public interface BleScanDetailActivitySubComponent extends AndroidInjector<BleScanDetailActivity> {
	@Subcomponent.Builder
	abstract class Builder extends AndroidInjector.Builder<BleScanDetailActivity> {}
}
