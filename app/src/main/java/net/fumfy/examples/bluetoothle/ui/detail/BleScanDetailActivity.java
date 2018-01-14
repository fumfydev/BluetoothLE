package net.fumfy.examples.bluetoothle.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import net.fumfy.examples.bluetoothle.R;
import net.fumfy.examples.bluetoothle.commons.BaseActivity;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.presenter.BleScanDetailPresenter;
import net.fumfy.examples.bluetoothle.ui.detail.fragment.view.BleScanDetailFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 21/12/2017.
 *
 * @author Simon Barnes
 */
public class BleScanDetailActivity extends BaseActivity
 implements BleScanDetailActivityInterface {

	public static final String BUNDLE_KEY_BLE_ENTRY = "bleEntry";

	@Inject
	BleScanDetailFragment fragment;

	@Inject
	BleScanDetailPresenter presenter;

	@BindView(R.id.detail_toolbar)
	Toolbar toolbar;

	public static void startBleScanDetailActivity(Context context, @NonNull BleEntry bleEntry) {
		Intent intent = new Intent(context, BleScanDetailActivity.class);
		intent.putExtra(BUNDLE_KEY_BLE_ENTRY, bleEntry);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_ble_scan_detail);
		super.onCreate(savedInstanceState);

		setSupportActionBar(toolbar);

		if (savedInstanceState == null) {

			BleEntry bleEntry = getIntent().getParcelableExtra(BUNDLE_KEY_BLE_ENTRY);

			presenter.setModel(bleEntry);

			addFragment(R.id.detail_container, fragment);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		fragment = null;
		presenter = null;
	}

	@Override
	public ActionBar getActivityActionBar() {
		return getSupportActionBar();
	}
}
