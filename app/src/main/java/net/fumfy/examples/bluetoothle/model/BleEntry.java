package net.fumfy.examples.bluetoothle.model;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.polidea.rxandroidble.RxBleDevice;
import com.polidea.rxandroidble.scan.ScanRecord;

import net.fumfy.examples.bluetoothle.domain.data.BluetoothClassStrings;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 10/12/2017.
 *
 * @author Simon Barnes
 */
public class BleEntry implements Parcelable {

	private String name;
	private String macAddress;
	private String type;
	private int itemType;
	private ParcelUuid[] parcelUuids;
	private BluetoothClass bluetoothClass;
	private int rssi;
	private int advertiseFlags;
	private List<ParcelUuid> serviceUuids;
	private SparseArray<byte[]> manufacturerSpecificData;
	private Map<ParcelUuid, byte[]> serviceData;
	private int txPowerlevel;
	private byte[] rawBytes;

	public BleEntry(String name, String macAddress, String type,
	         int itemType, ParcelUuid[] parcelUuids,
	         BluetoothClass bluetoothClass, int rssi, int advertiseFlags,
	         List<ParcelUuid> serviceUuids, SparseArray<byte[]> manufacturerSpecificeData,
	         Map<ParcelUuid, byte[]> serviceData, int txPowerlevel,
	         byte[] rawBytes) {
		this.name = name;
		this.macAddress = macAddress;
		this.type = type;
		this.itemType = itemType;
		this.parcelUuids = parcelUuids;
		this.bluetoothClass = bluetoothClass;
		this.rssi = rssi;
		this.advertiseFlags = advertiseFlags;
		this.serviceUuids = serviceUuids;
		this.manufacturerSpecificData = manufacturerSpecificeData;
		this.serviceData = serviceData;
		this.txPowerlevel = txPowerlevel;
		this.rawBytes = rawBytes;
	}

	public String getName() {
		return name;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public String getType() {
		return type;
	}

	private int getItemType() {
		return itemType;
	}

	@SuppressWarnings({"unused"})
	public String getClass_name(@NonNull Context context) {
		return BluetoothClassStrings
			.getBluetoothDeviceClassName(bluetoothClass.getDeviceClass(), context);
	}

	public String getMajor_class_name(@NonNull Context context) {
		return BluetoothClassStrings
			.getBluetoothMajorClassName(bluetoothClass.getMajorDeviceClass(), context);
	}

	public String getDisplayName() {
		return ((name==null||name.isEmpty())?
			((macAddress==null||macAddress.isEmpty())?"No Name":macAddress):name);
	}

	private int getRssi() {
		return rssi;
	}

	private int getAdvertiseFlags() {
		return advertiseFlags;
	}

	private List<ParcelUuid> getServiceUuids() {
		return serviceUuids;
	}

	private SparseArray<byte[]> getManufacturerSpecificData() {
		return manufacturerSpecificData;
	}

	private Map<ParcelUuid, byte[]> getServiceData() {
		return serviceData;
	}

	private int getTxPowerlevel() {
		return txPowerlevel;
	}

	private byte[] getRawBytes() {
		return rawBytes;
	}

	private ParcelUuid[] getParcelUuids() {
		return parcelUuids;
	}

	private BluetoothClass getBluetoothClass() {
		return bluetoothClass;
	}

	static BleEntry from(RxBleDevice device, int rssi, ScanRecord scanRecord) {
		int type = device.getBluetoothDevice().getType();

		return new BleEntry(
			device.getName(),
			device.getMacAddress(),
			deviceType(type),
			type,
			device.getBluetoothDevice().getUuids(),
			device.getBluetoothDevice().getBluetoothClass(),
			rssi,
			scanRecord.getAdvertiseFlags(),
			scanRecord.getServiceUuids(),
			scanRecord.getManufacturerSpecificData(),
			scanRecord.getServiceData(),
			scanRecord.getTxPowerLevel(),
			scanRecord.getBytes()
		);
	}

	private static String deviceType(int type) {
		String deviceType;
		switch (type) {
			case BluetoothDevice.DEVICE_TYPE_CLASSIC:
				deviceType = "DEVICE_TYPE_CLASSIC";
				break;
			case BluetoothDevice.DEVICE_TYPE_LE:
				deviceType = "DEVICE_TYPE_LE";
				break;
			case BluetoothDevice.DEVICE_TYPE_DUAL:
				deviceType = "DEVICE_TYPE_DUAL";
				break;
			case BluetoothDevice.DEVICE_TYPE_UNKNOWN:
			default:
				deviceType = "DEVICE_TYPE_UNKNOWN";
		}
		return deviceType;
	}

	private BleEntry(@NonNull Parcel source) {
		name = source.readString();
		macAddress = source.readString();
		type = source.readString();
		itemType = source.readInt();
		parcelUuids = (ParcelUuid[]) source.readParcelableArray(ParcelUuid.class.getClassLoader());
		bluetoothClass = source.readParcelable(BluetoothClass.class.getClassLoader());
		rssi = source.readInt();
		advertiseFlags = source.readInt();
		serviceUuids = new ArrayList<>();
		source.readTypedList(serviceUuids, ParcelUuid.CREATOR);
		int manufacturerSize = source.readInt();
		manufacturerSpecificData = new SparseArray<>(manufacturerSize);
		for (int i = 0; i < manufacturerSize; ++i) {
			int manufacturerId = source.readInt();
			if (source.readInt() == 1) {
				int manufacturerDataLength = source.readInt();
				byte[] manufacturerData = new byte[manufacturerDataLength];
				source.readByteArray(manufacturerData);
				manufacturerSpecificData.put(manufacturerId, manufacturerData);
			}
		}
		int serviceDataSize = source.readInt();
		serviceData = new HashMap<>(serviceDataSize);
		for (int i = 0; i < serviceDataSize; ++i) {
			ParcelUuid serviceDataUuid = source.readParcelable(
				ParcelUuid.class.getClassLoader());
			if (source.readInt() == 1) {
				int serviceDataLength = source.readInt();
				byte[] servicedata = new byte[serviceDataLength];
				source.readByteArray(servicedata);
				serviceData.put(serviceDataUuid, servicedata);
			}
		}
		txPowerlevel = source.readInt();
		rawBytes = new byte[source.readInt()];
		source.readByteArray(rawBytes);
	}

	@SuppressWarnings("WeakerAccess")
	public static final Parcelable.Creator<BleEntry> CREATOR = new Parcelable.Creator<BleEntry>() {
		@Override
		public BleEntry createFromParcel(Parcel source) {
			return new BleEntry(source);
		}

		@Override
		public BleEntry[] newArray(int size) {
			return new BleEntry[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(macAddress);
		dest.writeString(type);
		dest.writeInt(itemType);
		dest.writeParcelableArray(parcelUuids, 0);
		dest.writeParcelable(bluetoothClass, 0);
		dest.writeInt(rssi);
		dest.writeInt(advertiseFlags);
		dest.writeTypedList(serviceUuids);
		int manufacturerSpecificDataSize = 0;
		if (manufacturerSpecificData != null) {
			manufacturerSpecificDataSize = manufacturerSpecificData.size();
		}
		dest.writeInt(manufacturerSpecificDataSize);
		for (int i = 0; i < manufacturerSpecificDataSize; ++i) {
			dest.writeInt(manufacturerSpecificData.keyAt(i));
			byte[] data = manufacturerSpecificData.valueAt(i);
			if (data == null) {
				dest.writeInt(0);
			} else {
				dest.writeInt(1);
				dest.writeInt(data.length);
				dest.writeByteArray(data);
			}
		}
		if (serviceData != null) {
			dest.writeInt(serviceData.size());
			for (ParcelUuid uuid : serviceData.keySet()) {
				dest.writeParcelable(uuid, flags);
				byte[] data = serviceData.get(uuid);
				if (data == null) {
					dest.writeInt(0);
				} else {
					dest.writeInt(1);
					dest.writeInt(data.length);
					dest.writeByteArray(data);
				}
			}
		} else {
			dest.writeInt(0);
		}
		dest.writeInt(txPowerlevel);
		if (rawBytes != null) {
			dest.writeInt(rawBytes.length);
			dest.writeByteArray(rawBytes);
		} else {
			dest.writeInt(0);
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BleEntry)) {
			return false;
		}
		BleEntry bleEntry = (BleEntry) obj;

		return new EqualsBuilder()
			.append(name, bleEntry.getName())
			.append(macAddress, bleEntry.getMacAddress())
			.append(type, bleEntry.getType())
			.append(itemType, bleEntry.getItemType())
			.append(parcelUuids, bleEntry.getParcelUuids())
			.append(bluetoothClass, bleEntry.getBluetoothClass())
			.append(rssi, bleEntry.getRssi())
			.append(advertiseFlags, bleEntry.getAdvertiseFlags())
			.append(serviceUuids, bleEntry.getServiceUuids())
			.append(manufacturerSpecificData, bleEntry.getManufacturerSpecificData())
			.append(serviceData, bleEntry.getServiceData())
			.append(txPowerlevel, bleEntry.getTxPowerlevel())
			.append(rawBytes, bleEntry.getRawBytes())
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,31)
			.append(name)
			.append(macAddress)
			.append(type)
			.append(itemType)
			.append(parcelUuids)
			.append(bluetoothClass)
			.append(rssi)
			.append(advertiseFlags)
			.append(serviceUuids)
			.append(manufacturerSpecificData)
			.append(serviceData)
			.append(txPowerlevel)
			.append(rawBytes)
			.toHashCode();
	}
}
