package net.fumfy.examples.bluetoothle.domain.prefs;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 02/12/2017.
 *
 * @author Simon Barnes
 */

public interface SharedPrefsWrapper {
	void writeLong(String key, long value);
	long readLong(String key, long defValue);

	void writeInt(String key, int value);
	int readInt(String key, int defValue);

	void writeString(String key, String value);
	String readString(String key, String defValue);
}
