package com.WeerdSalmon.LatLngSpwn;

public class GeoCoordinate {
	
	public float latitude, longitude;
	public GeoCoordinate() {
		latitude = 0;
		longitude = 0;
	}
	
	public GeoCoordinate(float latitude, float longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
}
