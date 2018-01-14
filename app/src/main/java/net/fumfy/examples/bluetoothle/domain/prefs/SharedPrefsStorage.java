package net.fumfy.examples.bluetoothle.domain.prefs;

import android.content.Context;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/12/2017.
 *
 * @author Simon Barnes
 */

public class SharedPrefsStorage implements SharedPrefsWrapper {
	private Context context;

	public SharedPrefsStorage(Context context) {
		this.context = context;
	}

	@Override
	public void writeLong(String key, long value) {
		context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
			.edit().putLong(key, value).apply();
	}

	@Override
	public long readLong(String key, long defValue) {
		return context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
			.getLong(key, defValue);
	}

	@Override
	public void writeInt(String key, int value) {
		context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
			.edit().putInt(key, value).apply();
	}

	@Override
	public int readInt(String key, int defValue) {
		return context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
			.getInt(key, defValue);
	}

	@Override
	public void writeString(String key, String value) {
		context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
			.edit().putString(key, value).apply();
	}

	@Override
	public String readString(String key, String defValue) {
		return context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
			.getString(key, defValue);
	}
}
