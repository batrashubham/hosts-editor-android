package com.nilhcem.hostseditor.list;

import java.util.List;

import javax.inject.Inject;

import android.util.Log;
import android.widget.Filter;

import com.nilhcem.hostseditor.bus.event.RefreshHostsEvent;
import com.nilhcem.hostseditor.core.Host;
import com.nilhcem.hostseditor.core.HostsManager;
import com.squareup.otto.Bus;

public class ListHostsSearchFilter extends Filter {
	private static final String TAG = "ListHostsSearchFilter";

	@Inject Bus mBus;
	@Inject HostsManager mHostsManager;

	@SuppressWarnings("unchecked")
	@Override
	protected void publishResults(CharSequence constraint, FilterResults results) {
		Log.d(TAG, "Publishing result for: " + constraint);
		mBus.post(new RefreshHostsEvent((List<Host>) results.values));
	}

	@Override
	protected FilterResults performFiltering(CharSequence constraint) {
		Log.d(TAG, "Perform filtering for: " + constraint);
		FilterResults results = new FilterResults();
		results.values = mHostsManager.filterHosts(constraint);
		return results;
	}
}