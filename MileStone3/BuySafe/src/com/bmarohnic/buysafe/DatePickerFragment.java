package com.bmarohnic.buysafe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

	int resourceId;
	Calendar calendar;
	
	static DatePickerFragment newDatePickerFragment(int someInt) {
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		
		// The id of the EditText field that initiated the call to this method is contained within someInt.
		// This will be used to send the date selected by the user back to the correct EditText view.
		// Therefore, package it up within the bundle so that it is available to the onDateSet() method.
		Bundle whichFieldInt = new Bundle();
		whichFieldInt.putInt("whichField", someInt);
		datePickerFragment.setArguments(whichFieldInt);
		
		return datePickerFragment;
		
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		final Calendar calendar = Calendar.getInstance();
		calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(getActivity(), this, year, monthOfYear, dayOfMonth);
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		
		Date thisDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
		
    	// Verify that the selected date is less than or equal to today's date.
		// If not, set it to today's date before sending back to the calling activity.
    	Date today = calendar.getTime();
    	if (thisDate.after(today)) {
			thisDate = today;
			
			// Notify the user that future dates are not allowed.
			Toast.makeText(getActivity(), "Future dates not allowed!", Toast.LENGTH_LONG).show();
		}
    	
    	// Specify the date format used.
    	// This is format required to be used in the API string.
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    	String datePickerDateString = simpleDateFormat.format(thisDate);
    	
    	// Get the id of the EditText view that initiated the call to this DialogFragment
    	// This will be used to set the date in the correct EditText view.
    	resourceId = getArguments().getInt("whichField");
    	
    	EditText etWhichEditText = (EditText) getActivity().findViewById(resourceId);
    	
    	// Set the EditText within the calling activity to the date selected by the user.
    	etWhichEditText.setText(datePickerDateString);
    	
    	// Close the DialogFragment
    	this.dismiss();
    	
	}
}
