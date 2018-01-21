package net.fumfy.examples.bluetoothle.ui.scan.fragment.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import net.fumfy.examples.bluetoothle.R;
import net.fumfy.examples.bluetoothle.commons.view.BaseMvpFragment;
import net.fumfy.examples.bluetoothle.domain.data.Constants;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.main.BleScanActivity;
import net.fumfy.examples.bluetoothle.ui.scan.adapter.BleEntryAdapter;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.presenter.BleScanPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/12/2017.
 *
 * @author Simon Barnes
 */
public class BleScanListFragment extends BaseMvpFragment<BleScanPresenter>
	implements BleScanView,
	SwipeRefreshLayout.OnRefreshListener {

	@BindView(R.id.notification_tv)
	TextView notificationTv;

	@BindView(R.id.scanning_tv)
	TextView scanningTv;

	@BindView(R.id.btn_stop)
	ImageButton btn_stop;

	@BindView(R.id.swipe_container)
	SwipeRefreshLayout swipeRefreshLayout;

	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;

	private Context context;
	private BleEntryAdapter adapter;
	private BleScanActivity bleScanActivity;

	@Inject
	public BleScanListFragment() {
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		return inflater.inflate(R.layout.fragment_ble_list, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		adapter = new BleEntryAdapter(context, presenter);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(adapter);

		swipeRefreshLayout.setOnRefreshListener(this);

		presenter.attach(this, savedInstanceState);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.context = context;
		if (context instanceof BleScanActivity) {
			bleScanActivity = (BleScanActivity) context;
		} else {
			throw new RuntimeException(context.toString()
				+ " must implement bleScanActivity");
		}
	}

	@Override
	public void onDestroyView() {
		Timber.d("onDestroyView()");
		recyclerView.setAdapter(null);
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Timber.d("onDestroy()");
		presenter.onDestroy();
		presenter = null;
		context = null;
	}

	@Override
	public void onRefresh() {
		presenter.onRefresh();
	}

	@OnClick(R.id.btn_stop)
	void onClickStopScan() {
		presenter.onStopScan();
	}

	@Override
	public void startProgressView() {
		swipeRefreshLayout.setRefreshing(true);
	}

	@Override
	public void stopProgressView() {
		swipeRefreshLayout.setRefreshing(false);
	}

	@Override
	public void renderBleDevices(List<BleEntry> list) {
		adapter.setList(list);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void showMessage(String message) {
		btn_stop.setVisibility(View.GONE);
		scanningTv.setVisibility(View.GONE);
		notificationTv.setVisibility(View.VISIBLE);
		notificationTv.setText(message);
	}

	@Override
	public void showScanningMessage(String message) {
		btn_stop.setVisibility(View.VISIBLE);
		scanningTv.setVisibility(View.VISIBLE);
		notificationTv.setVisibility(View.GONE);
		scanningTv.setText(message);
	}

	@Override
	public void showMessageThenFinish(String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		bleScanActivity.finishAffinity();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case Constants.ENABLE_BT_REQUEST:
				presenter.bluetoothEnabled(resultCode == RESULT_OK);
				break;
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
	                                       @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case Constants.ENABLE_LOCATION_REQUEST:
				if (permissions.length > 0) {
					for (int i = 0; i < permissions.length; i++) {
						if (permissions[i].equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
							presenter.locationEnabled(
								grantResults[i] == PackageManager.PERMISSION_GRANTED);
							return;
						}
					}
				}
				presenter.locationEnabled(false);
				break;
		}
	}
}
