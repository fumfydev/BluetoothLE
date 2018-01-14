package net.fumfy.examples.bluetoothle.commons.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import timber.log.Timber;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 11/12/2017.
 *
 * @author Simon Barnes
 */

public abstract class BaseFragment extends DaggerFragment {

	@Nullable
	private Unbinder view_unbinder;

	/**
	 * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
	 * has returned, but before any saved state has been restored in to the view.
	 * This gives subclasses a chance to initialize themselves once
	 * they know their view hierarchy has been completely created.  The fragment's
	 * view hierarchy is not however attached to its parent at this point.
	 * <p>
	 * Classes extending the class must call <i><code>super.onViewCreated(view. savedInstanceState)</code></i>
	 * in the first line of their implementation of this method. The Butterknife binding, supported
	 *  by this class, is performed in this method. The Butterknife binding of the view is unbound
	 *  in {@link #onDestroyView()}.
	 *
	 * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
	 * @param savedInstanceState If non-null, this fragment is being re-constructed
	 * from a previous saved state as given here.
	 **/
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Timber.d("onViewCreated()");
		view_unbinder = ButterKnife.bind(this, view);
	}

	/**
	 * Called when the view previously created by {@link #onCreateView} has
	 * been detached from the fragment.  The next time the fragment needs
	 * to be displayed, a new view will be created.  This is called
	 * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
	 * <em>regardless</em> of whether {@link #onCreateView} returned a
	 * non-null view.  Internally it is called after the view's state has
	 * been saved but before it has been removed from its parent.
	 * <p>
	 * The Butterknife binding of the view is unbound in this method.
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Timber.d("onDestroyView()");
		if (view_unbinder != null) {
			view_unbinder.unbind();
		}
	}
}
