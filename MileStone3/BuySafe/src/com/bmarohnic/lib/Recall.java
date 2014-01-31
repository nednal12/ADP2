package com.bmarohnic.lib;

// This class is used as a data structure in order to hold recall information that
// is stored on an SQLite database.

public class Recall {

	// Declare the variables contained within the structure.
	private long recallId;
	private String recallNo;
	private String recallURL;
	private String type;
	private String hazard;
	private String prname;
	private String manufacturer;
	private String country_mfg;
	private String recDate;
	
	// Basic constructor
	public Recall() {
		
	}

	// Basic get and set methods in order to access the various variables
	public Recall(String recallNo) {
		this.recallNo = recallNo;
	}
	
	public long getRecallId() {
		return recallId;
	}

	public void setRecallId(long recallId) {
		this.recallId = recallId;
	}

	public String getRecallNo() {
		return recallNo;
	}

	public void setRecallNo(String recallNo) {
		this.recallNo = recallNo;
	}
	
	public String getRecallURL() {
		return recallURL;
	}

	public void setRecallURL(String recallURL) {
		this.recallURL = recallURL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHazard() {
		return hazard;
	}

	public void setHazard(String hazard) {
		this.hazard = hazard;
	}

	public String getPrname() {
		return prname;
	}

	public void setPrname(String prname) {
		this.prname = prname;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCountry_mfg() {
		return country_mfg;
	}

	public void setCountry_mfg(String country_mfg) {
		this.country_mfg = country_mfg;
	}

	public String getRecDate() {
		return recDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}
	
	// Overridden toString() method used to display the recall description
	// in a ListView.
	@Override
	public String toString() {
		return prname;
	}
}
