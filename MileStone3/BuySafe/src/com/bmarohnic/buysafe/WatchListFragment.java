package com.bmarohnic.buysafe;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.app.ListFragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class WatchListFragment extends ListFragment {

	//Set<String> stringSet = new HashSet<String>();
	private SharedPreferences spWatchList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Map<String, String[]> stringMap = (Map<String, String[]>) spWatchList.getAll();
		for (Map.Entry<String, String[]> entry : stringMap.entrySet()) {
			
		}
		
	}

}
