package net.fumfy.examples.bluetoothle.commons.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.fumfy.examples.bluetoothle.commons.presenter.MvpPresenter;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 11/12/2017.
 *
 * @author Simon Barnes
 */

public abstract class BaseMvpFragment<P extends MvpPresenter> extends BaseFragment
	implements MvpView {

	@Inject
	protected P presenter;

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		Timber.d("onSaveInstanceState(outState)");
		presenter.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}
}
