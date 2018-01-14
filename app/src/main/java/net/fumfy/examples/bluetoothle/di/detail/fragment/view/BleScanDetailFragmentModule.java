package net.fumfy.examples.bluetoothle.di.detail.fragment.view;


import net.fumfy.examples.bluetoothle.di.detail.fragment.presenter.BleScanDetailPresenterModule;
import net.fumfy.examples.bluetoothle.di.scope.FragmentScope;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.view.BleScanDetailFragment;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.view.BleScanDetailView;

import dagger.Binds;
import dagger.Module;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/12/2017.
 *
 * @author Simon Barnes
 */
@Module(includes = BleScanDetailPresenterModule.class)
public abstract class BleScanDetailFragmentModule {

	@Binds
	@FragmentScope
	abstract BleScanDetailView bleScanDetailView(BleScanDetailFragment bleScanDetailFragment);
}
