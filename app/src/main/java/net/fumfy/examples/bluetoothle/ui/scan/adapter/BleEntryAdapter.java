package net.fumfy.examples.bluetoothle.ui.scan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fumfy.examples.bluetoothle.databinding.BleDeviceBinding;
import net.fumfy.examples.bluetoothle.model.BleEntry;
import net.fumfy.examples.bluetoothle.ui.scan.fragment.presenter.BleScanPresenter;

import java.util.List;

import timber.log.Timber;

/**
 * Project : BluetoothLE
 * Created by Simon Barnes on 12/12/2017.
 *
 * @author Simon Barnes
 */

public class BleEntryAdapter extends RecyclerView.Adapter<BleEntryAdapter.BleBaseViewHolder> {
	private BleScanPresenter presenter;
	private List<BleEntry> entries;
	private Context context;

	public BleEntryAdapter(Context context, BleScanPresenter presenter) {
		this.context = context;
		this.presenter = presenter;
	}

	public void setList(List<BleEntry> entries) {
		this.entries = entries;
	}

	@Override
	public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
		super.onDetachedFromRecyclerView(recyclerView);
		Timber.d("onDetachedFromRecyclerView()");
		context = null;
		presenter = null;
	}

	@Override
	public BleBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Only Ble devices mapped currently
		BleBaseViewHolder viewHolder;
		switch (viewType) {

			default:
				viewHolder = new BleEntryViewHolder(BleDeviceBinding.inflate(
					LayoutInflater.from(context),
					parent,
					false
				));
		}
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(BleBaseViewHolder holder, int position) {
		// Multiple ViewHolder types not yet implemented
		BleEntry entry = entries.get(position);
		switch (entry.getType()) {

			default:
			holder.bind(entry);
		}
	}

	@Override
	public int getItemCount() {
		return (entries != null ? entries.size() : 0);
	}

	abstract static class BleBaseViewHolder extends RecyclerView.ViewHolder {

		BleBaseViewHolder(View itemView) {
			super(itemView);
		}

		public abstract void bind(BleEntry entry);
	}

	abstract class ClickBleEntryView extends BleBaseViewHolder {
		ClickBleEntryView(View itemView) { super(itemView);}

		void bindClickListener(View bleView, BleEntry entry) {
			bleView.setOnClickListener(v -> presenter.onClickBleEntry(entry));
		}
	}

	class BleEntryViewHolder extends ClickBleEntryView {
		private BleDeviceBinding binding;

		BleEntryViewHolder(BleDeviceBinding binding) {
			super(binding.getRoot());

			this.binding = binding;
		}

		@Override
		public void bind(BleEntry entry) {
			binding.setDevice(entry);
			binding.executePendingBindings();

			bindClickListener(itemView, entry);
		}
	}
}
