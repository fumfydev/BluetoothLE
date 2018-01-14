package net.fumfy.examples.bluetoothle.domain.data;

import android.bluetooth.BluetoothClass;
import android.content.Context;

import net.fumfy.examples.bluetoothle.R;

import java.util.HashMap;
import java.util.Map;


/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/12/2017.
 *
 * @author Simon Barnes
 */

public class BluetoothClassStrings {

	private static final Map<Integer, Integer> namesMajorClass = new HashMap<Integer, Integer>() {{
		put(BluetoothClass.Device.Major.AUDIO_VIDEO, R.string.bt_class_major_audio_video);
		put(BluetoothClass.Device.Major.COMPUTER, R.string.bt_class_major_computer);
		put(BluetoothClass.Device.Major.HEALTH, R.string.bt_class_major_health);
		put(BluetoothClass.Device.Major.IMAGING, R.string.bt_class_major_imaging);
		put(BluetoothClass.Device.Major.MISC, R.string.bt_class_major_misc);
		put(BluetoothClass.Device.Major.NETWORKING, R.string.bt_class_major_networking);
		put(BluetoothClass.Device.Major.PERIPHERAL, R.string.bt_class_major_peripheral);
		put(BluetoothClass.Device.Major.PHONE, R.string.bt_class_major_phone);
		put(BluetoothClass.Device.Major.TOY, R.string.bt_class_major_toy);
		put(BluetoothClass.Device.Major.UNCATEGORIZED, R.string.bt_class_major_uncategorized);
		put(BluetoothClass.Device.Major.WEARABLE, R.string.bt_class_major_wearable);
	}};

	private static final Map<Integer, Integer> namesDeviceClass = new HashMap<Integer, Integer>() {{
		put(BluetoothClass.Device.Major.UNCATEGORIZED, R.string.bt_class_major_uncategorized);
		put(0x0,R.string.bt_class_not_set);
		put(BluetoothClass.Device.AUDIO_VIDEO_CAMCORDER, R.string.bt_class_av_camcorder);
		put(BluetoothClass.Device.AUDIO_VIDEO_CAR_AUDIO, R.string.bt_class_av_car_audio);
		put(BluetoothClass.Device.AUDIO_VIDEO_HANDSFREE, R.string.bt_class_av_handsfree);
		put(BluetoothClass.Device.AUDIO_VIDEO_HEADPHONES, R.string.bt_class_av_headphones);
		put(BluetoothClass.Device.AUDIO_VIDEO_HIFI_AUDIO, R.string.bt_class_av_hifi_audio);
		put(BluetoothClass.Device.AUDIO_VIDEO_LOUDSPEAKER, R.string.bt_class_av_loudspeaker);
		put(BluetoothClass.Device.AUDIO_VIDEO_MICROPHONE, R.string.bt_class_av_microphone);
		put(BluetoothClass.Device.AUDIO_VIDEO_PORTABLE_AUDIO, R.string.bt_class_av_portable_audio);
		put(BluetoothClass.Device.AUDIO_VIDEO_SET_TOP_BOX, R.string.bt_class_av_set_top_box);
		put(BluetoothClass.Device.AUDIO_VIDEO_UNCATEGORIZED, R.string.bt_class_av_uncategorised);
		put(BluetoothClass.Device.AUDIO_VIDEO_VCR, R.string.bt_class_av_vcr);
		put(BluetoothClass.Device.AUDIO_VIDEO_VIDEO_CAMERA, R.string.bt_class_av_video_camera);
		put(BluetoothClass.Device.AUDIO_VIDEO_VIDEO_CONFERENCING,
			R.string.bt_class_av_video_conferencing);
		put(BluetoothClass.Device.AUDIO_VIDEO_VIDEO_DISPLAY_AND_LOUDSPEAKER,
			R.string.bt_class_av_video_display_and_loudspeaker);
		put(BluetoothClass.Device.AUDIO_VIDEO_VIDEO_GAMING_TOY,
			R.string.bt_class_av_video_gaming_toy);
		put(BluetoothClass.Device.AUDIO_VIDEO_VIDEO_MONITOR, R.string.bt_class_av_video_monitor);
		put(BluetoothClass.Device.AUDIO_VIDEO_WEARABLE_HEADSET,
			R.string.bt_class_av_video_wearable_headset);
		put(BluetoothClass.Device.COMPUTER_DESKTOP, R.string.bt_class_computer_desktop);
		put(BluetoothClass.Device.COMPUTER_HANDHELD_PC_PDA,
			R.string.bt_class_computer_handeld_pc_pda);
		put(BluetoothClass.Device.COMPUTER_LAPTOP, R.string.bt_class_computer_laptop);
		put(BluetoothClass.Device.COMPUTER_PALM_SIZE_PC_PDA,
			R.string.bt_class_computer_plam_size_pc_pda);
		put(BluetoothClass.Device.COMPUTER_SERVER, R.string.bt_class_computer_server);
		put(BluetoothClass.Device.COMPUTER_WEARABLE, R.string.bt_class_computer_wearable);
		put(BluetoothClass.Device.COMPUTER_UNCATEGORIZED, R.string.bt_class_computer_uncategorised);
		put(BluetoothClass.Device.HEALTH_BLOOD_PRESSURE, R.string.bt_class_health_blood_pressure);
		put(BluetoothClass.Device.HEALTH_DATA_DISPLAY, R.string.bt_class_health_data_display);
		put(BluetoothClass.Device.HEALTH_GLUCOSE, R.string.bt_class_health_glucose);
		put(BluetoothClass.Device.HEALTH_PULSE_OXIMETER, R.string.bt_class_health_pulse_oximeter);
		put(BluetoothClass.Device.HEALTH_PULSE_RATE, R.string.bt_class_health_pulse_rate);
		put(BluetoothClass.Device.HEALTH_THERMOMETER, R.string.bt_class_health_thermometer);
		put(BluetoothClass.Device.HEALTH_UNCATEGORIZED, R.string.bt_class_health_uncategorised);
		put(BluetoothClass.Device.PHONE_CELLULAR, R.string.bt_class_phone_cellular);
		put(BluetoothClass.Device.PHONE_CORDLESS, R.string.bt_class_phone_cordless);
		put(BluetoothClass.Device.PHONE_ISDN, R.string.bt_class_phone_isdn);
		put(BluetoothClass.Device.PHONE_MODEM_OR_GATEWAY,
			R.string.bt_class_phone__modem_or_gateway);
		put(BluetoothClass.Device.PHONE_SMART, R.string.bt_class_phone_smart);
		put(BluetoothClass.Device.PHONE_UNCATEGORIZED, R.string.bt_class_phone_uncategorised);
		put(BluetoothClass.Device.TOY_CONTROLLER, R.string.bt_class_toy_controller);
		put(BluetoothClass.Device.TOY_DOLL_ACTION_FIGURE, R.string.bt_class_toy_doll_action_figure);
		put(BluetoothClass.Device.TOY_GAME, R.string.bt_class_toy_game);
		put(BluetoothClass.Device.TOY_ROBOT, R.string.bt_class_toy_robot);
		put(BluetoothClass.Device.TOY_UNCATEGORIZED, R.string.bt_class_toy_uncategorised);
		put(BluetoothClass.Device.TOY_VEHICLE, R.string.bt_class_toy_vehicle);
		put(BluetoothClass.Device.WEARABLE_GLASSES, R.string.bt_class_wearable_glasses);
		put(BluetoothClass.Device.WEARABLE_HELMET, R.string.bt_class_wearable_helmet);
		put(BluetoothClass.Device.WEARABLE_JACKET, R.string.bt_class_wearable_jacket);
		put(BluetoothClass.Device.WEARABLE_PAGER, R.string.bt_class_wearable_pager);
		put(BluetoothClass.Device.WEARABLE_UNCATEGORIZED, R.string.bt_class_wearable_uncategorised);
		put(BluetoothClass.Device.WEARABLE_WRIST_WATCH, R.string.bt_class_wearable_wrist_watch);
		put(0x0500, R.string.bt_class_peripheral_nknp);
		put(0x0540, R.string.bt_class_peripheral_keyboard);
		put(0x0580, R.string.bt_class_peripheral_p);
		put(0x05C0, R.string.bt_class_peripheral_kp);

	}};

	public static String getBluetoothMajorClassName(Integer major_class, Context context) {
		if (namesMajorClass.containsKey(major_class)) {
			return context.getString(namesMajorClass.get(major_class));
		} else {
			return context.getString(R.string.bt_class_unknown_class, major_class);
		}
	}

	public static String getBluetoothDeviceClassName(Integer device_class, Context context) {
		if(namesDeviceClass.containsKey(device_class)) {
			return context.getString(namesDeviceClass.get(device_class));
		} else {
			return context.getString(R.string.bt_class_unknown_class, device_class);
		}
	}
}
