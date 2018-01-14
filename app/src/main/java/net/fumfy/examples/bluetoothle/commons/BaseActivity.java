package net.fumfy.examples.bluetoothle.commons;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import net.fumfy.examples.bluetoothle.di.commons.BaseActivityModule;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

/*
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/12/2017.
 *
 * @author Simon Barnes
 */

/**
 * A class extending {@link DaggerAppCompatActivity} that supports Butterknife binding in onCreate
 * and unbinding in {@link #onDestroy()}
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

	private Unbinder unbinder;

	@Inject
	@Named(BaseActivityModule.FRAGMENT_MANAGER)
	protected FragmentManager fragmentManager;

	/**
	 * Perform initialization of all fragments and loaders.
	 * <p>
	 * The Butterknife binding, supported
	 *  by this class, is performed in this method. The unbinding of the view is performed
	 *  in {@link #onDestroy()}.
	 *  </p>
	 *  <p>Be sure to call super.onCreate() after setContentView() for the binding to take effect.</p>
	 */
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Timber.plant();
		unbinder = ButterKnife.bind(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}

	@Override
	public void finishAffinity() {
		Timber.d("in finishAffinity()");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Timber.d("calling finishAndRemoveTask()");
			finishAndRemoveTask();
		} else {
			super.finishAffinity();
		}
	}

	public final void addFragment(@IdRes int containerId, Fragment fragment) {
		fragmentManager.beginTransaction()
			.add(containerId, fragment)
			.commit();
	}

}
