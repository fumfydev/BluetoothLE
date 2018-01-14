package net.fumfy.examples.bluetoothle.ui.detail.fragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fumfy.examples.bluetoothle.R;
import net.fumfy.examples.bluetoothle.commons.view.BaseMvpFragment;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.detail.BleScanDetailActivityInterface;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.presenter.BleScanDetailPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 21/12/2017.
 *
 * @author Simon Barnes
 */
public class BleScanDetailFragment extends BaseMvpFragment<BleScanDetailPresenter>
	implements BleScanDetailView {

	private BleScanDetailActivityInterface bleScanDetailActivityInterface;
	private ActionBar actionBar;

	@BindView(R.id.detailTextView)
	TextView detailTextView;

	@Inject
	public BleScanDetailFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_ble_device_detail, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		actionBar = bleScanDetailActivityInterface.getActivityActionBar();

		presenter.attach(this, savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof BleScanDetailActivityInterface) {
			bleScanDetailActivityInterface = (BleScanDetailActivityInterface) context;
		} else {
			throw new RuntimeException(context.toString()
				+ " must implement BleScanDetailActivityInterface");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		bleScanDetailActivityInterface = null;
		actionBar = null;
		presenter.detach();
	}

	@Override
	public void renderScanDetail(BleEntry entry) {
		actionBar.setTitle(String.format(getString(R.string.detail_title), entry.getDisplayName()));
		detailTextView.setText(entry.getDisplayName());
	}

}
