package net.fumfy.examples.bluetoothle.di;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.support.annotation.Nullable;

import com.polidea.rxandroidble.RxBleClient;

import net.fumfy.examples.bluetoothle.domain.prefs.SharedPrefsStorage;
import net.fumfy.examples.bluetoothle.domain.prefs.SharedPrefsWrapper;
import net.fumfy.examples.bluetoothle.domain.thread.InteractorThread;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 17/12/2017.
 *
 * @author Simon Barnes
 */

@Module
class AppModule {

	@Provides
	@Named("app.context")
	Context provideContext(Application application) {
		return application.getApplicationContext();
	}

	@Provides
	@Named("workerThread")
	InteractorThread providesWorkerThread() {
		return Schedulers::io;
	}

	@Provides
	@Named("observerThread")
	InteractorThread providesObserverThread() {
		return AndroidSchedulers::mainThread;
	}

	@Nullable
	@Provides
	BluetoothAdapter provideBluetoothAdapter() {
		return BluetoothAdapter.getDefaultAdapter();
	}

	@Provides
	RxBleClient providesRxBleClient(@Named("app.context") Context context) {
		return RxBleClient.create(context);
	}

	@Provides
	@Singleton
	SharedPrefsWrapper provideSharedPrefs(@Named("app.context") Context context) {
		return new SharedPrefsStorage(context);
	}

}
