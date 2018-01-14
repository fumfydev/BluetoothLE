package net.fumfy.examples.bluetoothle.domain.thread;

import rx.Scheduler;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 16/12/2017.
 *
 * @author Simon Barnes
 */

public interface InteractorThread {
	Scheduler scheduler();
}
