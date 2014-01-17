package com.bmarohnic.buysafe;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.bmarohnic.lib.APIInterface;
import com.bmarohnic.lib.NetworkConnection;

public class LandingPage extends Activity {

	Context _context = this;
	Boolean _isDeviceConnected = false;
	
	
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
    		 
    		Log.i("API data from Async", result);
    		
    		
    		
    		
    		
    		Log.i("onPostExecute", "XML Written to file");
    	}
    	
    	
    	
    	
    }
}