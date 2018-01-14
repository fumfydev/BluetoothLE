package net.fumfy.examples.bluetoothle.commons.presenter;

import android.os.Bundle;

import net.fumfy.examples.bluetoothle.commons.view.MvpView;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/12/2017.
 *
 * @author Simon Barnes
 */

public abstract class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {
	protected V view;

	@Override
	public void attach(V view, Bundle savedState) {
		this.view = view;
	}

	@Override
	public void detach() {
		view = null;
	}

	@Override
	public abstract void onDestroy();

	@Override
	public abstract void onSaveInstanceState(Bundle outState);
}
