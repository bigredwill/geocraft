package com.WeerdSalmon.LatLngSpwn;

public class GeoCoordinate {
	
	public double latitude, longitude;
	public static final int LATTOM = 110540;
	public static final double LNGTOM = 111.320;
	
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

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double latToMeters() {
		
		return LATTOM * latitude;
	}
	
	public double lngToMeters() {
		return longitude * (LNGTOM*Math.cos(this.latitude) * 1000);
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "("+this.latitude+", "+this.longitude+")";
	} 
	
}