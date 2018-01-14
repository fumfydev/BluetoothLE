package net.fumfy.examples.bluetoothle.commons.presenter;

import android.os.Bundle;

import net.fumfy.examples.bluetoothle.commons.view.MvpView;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/12/2017.
 *
 * @author Simon Barnes
 */

public interface MvpPresenter<V extends MvpView> {

	void attach(V view, Bundle saveState);

	void detach();

	void onSaveInstanceState(Bundle outState);

	void onDestroy();

}
