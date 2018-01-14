package net.fumfy.examples.bluetoothle.di.detail.fragment.presenter;

import net.fumfy.examples.bluetoothle.ui.detail.fragment.presenter.BleScanDetailPresenter;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.presenter.BleScanDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 22/11/2017.
 *
 * @author Simon Barnes
 */
@Module
public class BleScanDetailPresenterModule {

	@Provides
	BleScanDetailPresenter
	provideBleScanDetailPresenter(BleScanDetailPresenterImpl presenter){
		return presenter;
	}
}
