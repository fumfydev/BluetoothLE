package net.fumfy.examples.bluetoothle.ui.detail.fragment.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import net.fumfy.examples.bluetoothle.commons.presenter.BaseMvpPresenter;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.view.BleScanDetailView;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 21/12/2017.
 *
 * @author Simon Barnes
 */
@Singleton
public class BleScanDetailPresenterImpl extends BaseMvpPresenter<BleScanDetailView>
	implements BleScanDetailPresenter {
	@SuppressWarnings({"FieldCanBeLocal", "unused"})
	private Context context;
	private BleEntry model;
	private final String SAVE_STATE_MODEL = "model";

	@Inject
	BleScanDetailPresenterImpl(@Named("app.context") Context context) {
		this.context = context;
	}

	@Override
	public void attach(@NonNull BleScanDetailView view, Bundle saveState) {

		if (saveState != null) {
			model = saveState.getParcelable(SAVE_STATE_MODEL);
		}

		if (model != null) {
			view.renderScanDetail(model);
		} else {
			Timber.d("model is null");
		}
	}

	@Override
	public void detach() {
		super.detach();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(SAVE_STATE_MODEL, model);
	}

	@Override
	public void onDestroy() {
		model = null;
	}

	@Override
	public void setModel(@NonNull BleEntry entry) {
		model = entry;
	}
}
