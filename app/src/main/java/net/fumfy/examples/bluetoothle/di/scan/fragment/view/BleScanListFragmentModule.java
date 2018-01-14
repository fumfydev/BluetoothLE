package net.fumfy.examples.bluetoothle.di.scan.fragment.view;

import net.fumfy.examples.bluetoothle.di.scan.fragment.presenter.BleScanPresenterModule;
import net.fumfy.examples.bluetoothle.di.scope.FragmentScope;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.view.BleScanListFragment;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.view.BleScanView;

import dagger.Binds;
import dagger.Module;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/12/2017.
 *
 * @author Simon Barnes
 */
@FragmentScope
@Module (includes = {BleScanPresenterModule.class})
public abstract class BleScanListFragmentModule {

	@Binds
	@FragmentScope
	public abstract BleScanView bindScanView(BleScanListFragment bleScanListFragment);
}
