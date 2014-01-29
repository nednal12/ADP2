package com.bmarohnic.buysafe;


import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {

	EditText etKeywordSearch = null;
	EditText etFromSearch = null;
	EditText etToSearch = null;
	
	Button btKeywordSearch;
	Button btDateSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		etKeywordSearch = (EditText) findViewById(R.id.etKeywordSearch);
		
		btKeywordSearch = (Button) findViewById(R.id.btKeywordSearch);
		
		btKeywordSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (etKeywordSearch.getText().toString().equals("")) {
					Toast.makeText(SearchActivity.this, "Please enter a search string", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		final EditText etFromDate = (EditText) findViewById(R.id.etFromDate);
		final EditText etToDate = (EditText) findViewById(R.id.etToDate);
		
		etFromDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText etFromDate = (EditText) findViewById(R.id.etFromDate);
				int intIdForFromDate = etFromDate.getId();
//				DialogFragment newDatePickerFragment = new DatePickerFragment();
				DialogFragment newDatePickerFragment = DatePickerFragment.newDatePickerFragment(intIdForFromDate);
				newDatePickerFragment.show(getFragmentManager(), "etFromDate");
			}
		});
		
		etToDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText etToDate = (EditText) findViewById(R.id.etToDate);
				int intIdForToDate = etToDate.getId();
//				DialogFragment newDatePickerFragment = new DatePickerFragment();
				DialogFragment newDatePickerFragment = DatePickerFragment.newDatePickerFragment(intIdForToDate);
				newDatePickerFragment.show(getFragmentManager(), "etToDate");
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
}
