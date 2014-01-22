package com.bmarohnic.buysafe;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bmarohnic.lib.APIInterface;
import com.bmarohnic.lib.NetworkConnection;
import com.bmarohnic.lib.XMLParser;
import com.bmarohnic.lib.XMLParser.XMLTagData;
import com.bmarohnic.buysafe.DetailActivity;
import com.bmarohnic.buysafe.LandingPage;

public class LandingPage extends ListActivity {

	Context _context = this;
	Boolean _isDeviceConnected = false;
	List<XMLTagData> entries;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        
        // Call getConnectionStatus and getConnectionType located within the NetworkConnection class.
        // The results will be used to determine whether to retrieve new data from the API 
        // or load stored data resident on the device if it exists.
        _isDeviceConnected = NetworkConnection.getConnectionStatus(_context);
        
        if(_isDeviceConnected == true){
        	Log.i("Network Connection", NetworkConnection.getConnectionType(_context));
        	
        	getData();
        	
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing_page, menu);
        return true;
    }
    
    
    private void getData(){
    	
    	// Create two date variables to hold the start date and end date for the API call.
    	// getTime() returns the number of milliseconds for a date value.
    	// 604800000 is the number of milliseconds in a week.
    	Date endDateDate = new Date();
    	Date startDateDate = new Date(endDateDate.getTime() - 604800000);
    	
    	// Create and instance of the SimpleDateFormat and set it to display dates in yyyy-MM-dd format
    	// because this is what is required by the API.
    	// SimpleDateFormat accepts a date and returns a string.
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    	String endDateString = simpleDateFormat.format(endDateDate);
    	String startDateString = simpleDateFormat.format(startDateDate);
    	
    	Log.i("getData", "startDateString is " + startDateString);
    	Log.i("getData", "endDateString is " + endDateString);
    	
    	String pieceMealURL = "http://www.cpsc.gov/cgibin/CPSCUpcWS/CPSCUpcSvc.asmx/getRecallByDate?startDate=" + startDateString + 
    			"&endDate=" + endDateString + "&userId=myuserid&password=mypassword";
    	
    	Log.i("Complete URL", pieceMealURL);
    	
    	try {
    		URL completeURL = new URL(pieceMealURL);
    		
    		AsynchronousTask asyncTask = new AsynchronousTask();
    		
    		asyncTask.execute(completeURL);
    		
    	} catch (MalformedURLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }
    
    
    private class AsynchronousTask extends AsyncTask<URL, Void, String>{

    	@Override
    	protected String doInBackground(URL... urls) {
    		String apiDataString = "";
    		
    		for(URL url: urls){
    			apiDataString = APIInterface.getAPIData(url);
    		}
    		
    		
    		return apiDataString;
    	}
    	
    	
    	@Override
    	protected void onPostExecute(String result) {
    		 
    		Log.i("String API data from Async", result);
    		
    		try {
    			// Convert the string returned by APIInterface.getAPIData into an InputStream.
    			// The required data type to the XMLPullParser is InputStream.
				InputStream inputStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
				XMLParser xmlParser = new XMLParser();
				try {
					entries = xmlParser.parse(inputStream);
					
					for (XMLTagData entry : entries) {
						Log.i("MainActivity", entry.recallNo);
						Log.i("MainActivity", entry.recallURL);
						Log.i("MainActivity", entry.recDate);
						Log.i("MainActivity", entry.manufacturer);
						Log.i("MainActivity", entry.type);
						Log.i("MainActivity", entry.prname);
						Log.i("MainActivity", entry.hazard);
						Log.i("MainActivity", entry.country_mfg);
						
					}
			ArrayAdapter<XMLTagData> adapter = new ArrayAdapter<XMLParser.XMLTagData>(LandingPage.this, android.R.layout.simple_list_item_1, entries);
			setListAdapter(adapter);	
			
						
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	XMLTagData entry = entries.get(position);
    	
    	Intent intent = new Intent(this, DetailActivity.class);
    	intent.putExtra("recallNo", entry.recallNo);
    	intent.putExtra("recallURL", entry.recallURL);
    	intent.putExtra("recDate", entry.recDate);
    	intent.putExtra("manufacturer", entry.manufacturer);
    	intent.putExtra("type", entry.type);
    	intent.putExtra("type", entry.prname);
    	intent.putExtra("hazard", entry.hazard);
    	intent.putExtra("country_mfg", entry.country_mfg);
    	
    	
    	
    }
}