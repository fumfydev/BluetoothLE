package net.fumfy.examples.bluetoothle.domain.data;

import java.util.concurrent.TimeUnit;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/12/2017.
 *
 * @author Simon Barnes
 */

public class Constants {

	public static final int ENABLE_BT_REQUEST = 1001;
	public static final int ENABLE_LOCATION_REQUEST = 1002;

	// Scan Timer Constants
	public static final int scan_timer_interval = 1;
	public static final TimeUnit scan_time_unit = TimeUnit.SECONDS;

	// Settings and Preferences Default
	public static final String pref_scan_duration = "scan_time";
	public static final long DEFAULT_SCAN_DURATION = 10;
}
