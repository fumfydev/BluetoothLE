package net.fumfy.examples.bluetoothle.di.main;

import net.fumfy.examples.bluetoothle.ui.main.BleScanMainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 14/12/2017.
 *
 * @author Simon Barnes
 */
@Subcomponent()
public interface BleScanMainActivitySubcomponent extends AndroidInjector<BleScanMainActivity> {
	@Subcomponent.Builder
	abstract class Builder extends AndroidInjector.Builder<BleScanMainActivity> {}
}
