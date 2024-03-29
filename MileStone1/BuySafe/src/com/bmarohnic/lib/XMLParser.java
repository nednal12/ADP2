//package com.bmarohnic.lib;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//
//import android.util.Xml;
//
//public class XMLParser {
//	private static final String namespace = null;
//	
//	public List parse(InputStream in) throws XmlPullParserException, IOException{
//		try {
//			XmlPullParser parser = Xml.newPullParser();
//			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//			parser.setInput(in, null);
//			parser.nextTag();
//			return readFeed(parser);
//		} finally{
//			in.close();
//		}
//	}
//
//	private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException{
//		List entries = new ArrayList();
//		
//		parser.require(XmlPullParser.START_TAG, namespace, "feed");
//		while (parser.next() != XmlPullParser.END_TAG) {
//			if (parser.getEventType() != XmlPullParser.START_TAG) {
//				continue;
//			}
//		
//			String name = parser.getName();
//			
//			if (name.equals("result")) {
//				entries.add(readEntry(parser));
//			}
//		}
//		return null;
//	}
//}