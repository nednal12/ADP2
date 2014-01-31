package com.bmarohnic.buysafe;

import java.util.List;

import com.bmarohnic.lib.Recall;
import com.bmarohnic.lib.RecallsDataSource;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class WatchListFragment extends ListFragment {

	RecallsDataSource recallsDataSource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		recallsDataSource = new RecallsDataSource(getActivity());
		recallsDataSource.openDB();
		
		// Check the size of the database. If it contains zero entries, inform the user
		// of that fact. Otherwise, display the data contained in the database via
		// the adapter.
		List<Recall> recalls = recallsDataSource.retrieveAllData();
		if (recalls.size() == 0) {
			Toast.makeText(getActivity(), "There are no saved recalls in your watch list", Toast.LENGTH_LONG).show();
		}
		
		ArrayAdapter<Recall> adapter = new ArrayAdapter<Recall>(getActivity(), android.R.layout.simple_list_item_1, recalls);
		setListAdapter(adapter);
		
	}

}
