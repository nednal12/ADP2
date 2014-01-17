package com.bmarohnic.lib;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

public class APIInterface {
	
	public static String getAPIData(URL url){
		
		// Declare apiString here so that it is not declared multiple times within the while loop.
		String apiString = "";
		
		// The LandingPage class verifies that the device is connected to the network before calling
		// getAPIData but the try-catch block provides extra insurance against things that might go wrong.
		try {
			
			URLConnection urlConnection = url.openConnection();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			
			byte[] apiBytes = new byte[1024];
			int howManyBytesRead = 0;
			StringBuffer apiBuffer = new StringBuffer();
			
			while((howManyBytesRead = bufferedInputStream.read(apiBytes)) != -1){
				apiString = new String(apiBytes, 0, howManyBytesRead);
				apiBuffer.append(apiString);
				
			}
			
			return apiBuffer.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("APIInterface", e.toString());
		}
		
		
		return apiString;
	}

}
