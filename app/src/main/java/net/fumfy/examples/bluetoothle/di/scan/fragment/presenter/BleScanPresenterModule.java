package net.fumfy.examples.bluetoothle.di.scan.fragment.presenter;

import net.fumfy.examples.bluetoothle.ui.scan.fragment.presenter.BleScanPresenter;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.presenter.BleScanPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/11/2017.
 *
 * @author Simon Barnes
 */

@Module
public abstract class BleScanPresenterModule {

	@Binds
	abstract BleScanPresenter
	providesBleScanPresenter(BleScanPresenterImpl blePresenter);
}
