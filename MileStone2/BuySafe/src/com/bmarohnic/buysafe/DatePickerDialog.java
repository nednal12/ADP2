package com.bmarohnic.buysafe;

import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;

public interface DatePickerDialog {

	OnDateSetListener onDateSetListener = new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			
		}
	};
}
