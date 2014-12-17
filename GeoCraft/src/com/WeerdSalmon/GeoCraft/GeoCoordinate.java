package com.WeerdSalmon.GeoCraft;

public class GeoCoordinate {
	
	public double latitude, longitude;
	
	public GeoCoordinate() {
		latitude = 0;
		longitude = 0;
	}
	
	public GeoCoordinate(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "("+this.latitude+", "+this.longitude+")";
	} 
	
}
