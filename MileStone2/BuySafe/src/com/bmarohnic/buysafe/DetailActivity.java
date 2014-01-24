package com.bmarohnic.buysafe;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bmarohnic.lib.XMLParser.XMLTagData;

public class DetailActivity extends Activity {

	String manufacturer = null;
	String type = null;
	String recallURL = null;
	String pieceMealURL = null;
	List<XMLTagData> entries;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_detail);
		
		TextView tvPrname = (TextView) findViewById(R.id.tvPrname);
		Button btManufacturer = (Button) findViewById(R.id.btManufacturer);
		Button btRecallURL = (Button) findViewById(R.id.btRecallURL);
		Button btType = (Button) findViewById(R.id.btType);
		
		Intent intent = getIntent();
		
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
		    	
		    	startActivity(intent);
				
			}
			
		});
		
		
		btType.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				pieceMealURL = "http://www.cpsc.gov/cgibin/CPSCUpcWS/CPSCUpcSvc.asmx/getRecallByWord?message1=" + type + 
		    			"&userId=myuserid&password=mypassword";
		    	
				Log.i("DetailActivity", pieceMealURL);
				
		    	Intent intent = new Intent(DetailActivity.this, ExtendedActivity.class);
		    	
		    	intent.putExtra("apiURL", pieceMealURL);
		    	
		    	startActivity(intent);
				
			}
			
		});
		
		
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
        return true;
	}
	
	
	
	 
}
 