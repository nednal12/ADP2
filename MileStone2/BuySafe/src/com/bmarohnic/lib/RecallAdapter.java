package com.bmarohnic.lib;

import java.util.List;

import com.bmarohnic.buysafe.R;
import com.bmarohnic.lib.XMLParser.XMLTagData;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RecallAdapter extends ArrayAdapter<XMLTagData> {

	private Context context;
	private List<XMLTagData> objects;
	
	
	public RecallAdapter(Context context, int resource, List<XMLTagData> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}
	 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		XMLTagData entry = objects.get(position);
		
		LayoutInflater layoutInflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		View view = layoutInflater.inflate(R.layout.item_recall, null);
		
		TextView tvPrname = (TextView) view.findViewById(R.id.tvPrname);
		TextView tvType = (TextView) view.findViewById(R.id.tvType);
		TextView tvManufacturer = (TextView) view.findViewById(R.id.tvManufacturer);
		TextView tvRecDate = (TextView) view.findViewById(R.id.tvRecDate);
		
		tvPrname.setText(entry.prname);
		tvType.setText(entry.type);
		tvManufacturer.setText(entry.manufacturer);
		tvRecDate.setText(entry.recDate);
		
		return view;
	}
}
