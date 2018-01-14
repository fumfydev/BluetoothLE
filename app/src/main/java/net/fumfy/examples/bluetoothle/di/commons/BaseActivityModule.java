package net.fumfy.examples.bluetoothle.di.commons;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import net.fumfy.examples.bluetoothle.di.scope.ActivityScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 17/12/2017.
 *
 * @author Simon Barnes
 */
@Module
public abstract class BaseActivityModule {
	public static final String FRAGMENT_MANAGER = "fragmentManager";

	@Provides
	@Named(FRAGMENT_MANAGER)
	@ActivityScope
	static FragmentManager activityFragmentManager(AppCompatActivity activity) {
		return activity.getSupportFragmentManager();
	}

}
