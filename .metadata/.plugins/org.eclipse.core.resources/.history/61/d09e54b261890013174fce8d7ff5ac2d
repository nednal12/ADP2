package com.bmarohnic.buysafe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bmarohnic.lib.XMLParser.XMLTagData;

public class DetailActivity extends Activity {

	private static final int REQUEST_CODE = 100;
	String manufacturer = null;
	String type = null;
	String recallURL = null;
	String pieceMealURL = null;
	List<XMLTagData> entries;
	Button btManufacturer;
	Button btRecallURL;
	Button btType;
	TextView tvPrname;
	private SharedPreferences spWatchList;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		spWatchList = getPreferences(MODE_PRIVATE);
		
		setContentView(R.layout.activity_detail);
		
		tvPrname = (TextView) findViewById(R.id.tvPrname);
		btManufacturer = (Button) findViewById(R.id.btManufacturer);
		btRecallURL = (Button) findViewById(R.id.btRecallURL);
		btType = (Button) findViewById(R.id.btType);
		
		intent = getIntent();
		
		manufacturer = intent.getStringExtra("manufacturer");
		type = intent.getStringExtra("type");
		recallURL = intent.getStringExtra("recallURL");
		
		
		tvPrname.setText(intent.getStringExtra("prname"));
		btManufacturer.setText(manufacturer);
		btType.setText(type);
		
		btManufacturer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				pieceMealURL = "http://www.cpsc.gov/cgibin/CPSCUpcWS/CPSCUpcSvc.asmx/getRecallByWord?message1=" + manufacturer + 
		    			"&userId=myuserid&password=mypassword";
		    	
				Log.i("DetailActivity", pieceMealURL);
				
				Intent intent = new Intent(DetailActivity.this, ExtendedActivity.class);
		    	
		    	intent.putExtra("apiURL", pieceMealURL);
		    	intent.putExtra("callingActivity", "DetailActivity");
		    	
		    	startActivityForResult(intent, REQUEST_CODE);
				
			}
			
		});
		
		// Called when the product type button is selected.
		// Passes the newly constructed API request string to the Extended Results Activity
		btType.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				pieceMealURL = "http://www.cpsc.gov/cgibin/CPSCUpcWS/CPSCUpcSvc.asmx/getRecallByWord?message1=" + type + 
		    			"&userId=myuserid&password=mypassword";
		    	
				Log.i("DetailActivity", pieceMealURL);
				
		    	Intent intent = new Intent(DetailActivity.this, ExtendedActivity.class);
		    	
		    	intent.putExtra("apiURL", pieceMealURL);
		    	intent.putExtra("callingActivity", "DetailActivity");
		    	
		    	startActivityForResult(intent, REQUEST_CODE);
				
			}
			
		});
		
		// Called when the CPSC.gov button is selected.
		// Uses and implicit intent to open the webpage associated with the specific recall
		btRecallURL.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
		    	
		    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recallURL));
				startActivity(intent);
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_detail, menu);
        MenuItem addToWatchList = menu.findItem(R.id.addToWatchlist);
        
        addToWatchList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				SharedPreferences.Editor spEditor = spWatchList.edit();
				
				String spRecallNo = intent.getStringExtra("recallNo");
				String spPrname = intent.getStringExtra("prname");
				String spRecDate = intent.getStringExtra("recDate");
				String spType = intent.getStringExtra("type");
				String spRecallURL = intent.getStringExtra("recallURL");
				String spHazard = intent.getStringExtra("hazard");
				String spManufaturer = intent.getStringExtra("manufacturer");
				String spCountry_mfg = intent.getStringExtra("country_mfg");
				
				Set<String> stringSet = new HashSet<String>();
				String[] recallDetailsArray = {spRecallNo,spPrname,spRecDate,spType,spRecallURL,spHazard,spManufaturer,spCountry_mfg};
				
				for (String string : recallDetailsArray) {
					stringSet.add(string);
				}
				spEditor.putStringSet(spRecallNo, stringSet);
				spEditor.commit();
				Toast.makeText(DetailActivity.this, "Recall added to watchlist", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
        return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			
			manufacturer = data.getStringExtra("manufacturer");
			type = data.getStringExtra("type");
			recallURL = data.getStringExtra("recallURL");
			
			tvPrname.setText(data.getStringExtra("prname"));
			btManufacturer.setText(manufacturer);
			btType.setText(type);
		}
	}
	
	 
}
 