package net.fumfy.examples.bluetoothle.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import net.fumfy.examples.bluetoothle.R;
import net.fumfy.examples.bluetoothle.commons.BaseActivity;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.navigation.Navigator;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.presenter.BleScanPresenterImpl;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.view.BleScanListFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/12/2017.
 *
 * @author Simon Barnes
 */
public class BleScanMainActivity extends BaseActivity
    implements BleScanActivity {

    @Inject
    Navigator navigator;

    @Inject
    BleScanPresenterImpl bleScanPresenter;

    @Inject
    BleScanListFragment bleScanListFragment;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_ble);

        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        bleScanPresenter.initPresenter(this);

        if (savedInstanceState == null) {
            addFragment(R.id.content, bleScanListFragment);
        }
    }

	@Override
    public void onScanEntryClick(BleEntry bleEntry) {
        navigator.toDetailActivity(this, bleEntry);
    }
}
