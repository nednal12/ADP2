package com.bmarohnic.lib;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FileManager {
	
	public static boolean writeStringToFile (Context context, String filename, String content){
		try {
			FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
			try {
				fileOutputStream.write(content.getBytes());
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public static String readStringFromFile(Context context, String filename) {
		String xmlContent = "";
		
		FileInputStream fileInputStream = null;
		
//		File xmlFile = new File(filename);
		try {
			fileInputStream = context.openFileInput(filename);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		byte[] contentBytes = new byte[1024];
		int bytesRead = 0;
		StringBuffer xmlBuffer = new StringBuffer();
		
		try {
			while ((bytesRead = bufferedInputStream.read(contentBytes)) != -1) {
				xmlContent = new String(contentBytes,0,bytesRead);
				xmlBuffer.append(xmlContent);
			}
			
			xmlContent = xmlBuffer.toString();
			fileInputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "No cached data to display";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Read error encountered";
		}
		
		return xmlContent;
	}
}
