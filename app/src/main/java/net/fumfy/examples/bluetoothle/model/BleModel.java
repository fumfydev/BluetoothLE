package net.fumfy.examples.bluetoothle.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.scan.ScanRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;


/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 10/12/2017.
 *
 * @author Simon Barnes
 */
@Singleton
public class BleModel implements Parcelable {

	private List<BleEntry> items = new ArrayList<>();
	private String message = "";
	private int scanTimerCount=0;
	private boolean scanning=false;

	@Inject
	BleModel() {
	}

	public int getItemCount() {
		return items.size();
	}

	public void addDevice(RxBleDevice device, int rssi, ScanRecord scanRecord) {
		items.add(BleEntry.from(device, rssi, scanRecord));
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(@NonNull String message) {
		this.message = message;
	}

	public int getScanTimerCount() {
		Timber.d("in getScanTimerCount returning %d", scanTimerCount);
		return scanTimerCount;
	}

	public void setScanTimerCount(int scanTimerCount) {
		Timber.d("in setScanTimerCount(%d)", scanTimerCount);
		this.scanTimerCount = scanTimerCount;
	}

	public boolean isScanning() {
		return scanning;
	}

	public void setScanning(boolean scanning) {
		this.scanning = scanning;
	}

	public List<BleEntry> getList() {
		return items;
	}

	public boolean listContains(RxBleDevice device) {
		// Only test MAC Address
		String macAddress = device.getMacAddress();
		for (BleEntry item : items) {
			if (item.getMacAddress().equals(macAddress)) {
				return true;
			}
		}
		return false;
	}

	public void clear() {
		items.clear();
		message = null;
		scanTimerCount = 0;
	}

	public void from(BleModel model) {
		message = model.getMessage();
   		items = model.getList();
		scanTimerCount = model.getScanTimerCount();
		scanning = model.isScanning();
	}

	private BleModel(Parcel source) {
		items = source.createTypedArrayList(BleEntry.CREATOR);
		message = source.readString();
		scanTimerCount = source.readInt();
		scanning = source.readInt() == 1;
	}

	static final Creator<BleModel> CREATOR = new Creator<BleModel>() {
		@Override
		public BleModel createFromParcel(Parcel source) {
			return new BleModel(source);
		}

		@Override
		public BleModel[] newArray(int size) {
			return new BleModel[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(items);
		dest.writeString(message);
		dest.writeInt(scanTimerCount);
		dest.writeInt(scanning ? 1 :0);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BleModel)) {
			return false;
		}
		BleModel bleModel = (BleModel) obj;
		EqualsBuilder builder = new EqualsBuilder()
			.append(scanning, bleModel.isScanning())
			.append(message, bleModel.getMessage())
			.append(scanTimerCount,bleModel.getScanTimerCount())
			.append(getItemCount(), bleModel.getItemCount());
		int itemsSize = getItemCount();
		if (itemsSize > 0) {
			for (int i = 0; i < itemsSize; i++) {
				if (i < bleModel.getItemCount()) {
					builder.append(items.get(i), bleModel.getList().get(i));
				} else {
					builder.append(items.get(i), null);
				}
			}
		}
		return builder.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder builder = new HashCodeBuilder(17,31)
			.append(message)
			.append(scanTimerCount)
			.append(scanning)
			.append(getItemCount());
		int itemsSize = getItemCount();
		if (itemsSize > 0) {
			for (int i = 0; i < itemsSize; i++) {
				builder.append(items.get(i));
			}
		}

		return builder.toHashCode();
	}
}
