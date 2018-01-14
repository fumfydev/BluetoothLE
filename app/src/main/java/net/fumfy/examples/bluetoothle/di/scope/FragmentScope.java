package net.fumfy.examples.bluetoothle.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 17/12/2017.
 *
 * @author Simon Barnes
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
