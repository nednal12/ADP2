package com.bmarohnic.lib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {

	static Boolean _connection = false;
	static String _connectionType = "No Connection";
	
	// getConnectionType can be called from outside the class in order to retrieve the
	// type of connection currently being used to connect to the network.
	public static String getConnectionType(Context context){
		networkConnection(context);
		return _connectionType;
	}
	
	// getConnectionStatus can be called from outside the class in order to retrieve
	// whether or not the device is connected to the network.
	public static Boolean getConnectionStatus(Context context){
		networkConnection(context);
		return _connection;
		
	}
	
	
	// This is the primary method within NetworkConnection class.
	// It is responsible for retrieving network information using via ConnectivityManager.
	private static void networkConnection(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		
		if(networkInfo != null){
			if(networkInfo.isConnected()){
				_connectionType = networkInfo.getTypeName();
				_connection = true;
				
			}
		}
	}
}
