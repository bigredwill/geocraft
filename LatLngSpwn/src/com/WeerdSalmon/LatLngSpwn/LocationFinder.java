package com.WeerdSalmon.LatLngSpwn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.bukkit.craftbukkit.libs.com.google.gson.JsonElement;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonParser;
import org.bukkit.plugin.Plugin;




public class LocationFinder {
	private Plugin plug;
	public KeyPoint key;
	
	// Append IP to get location information
	private String dbipkey = "ee58ea25056523ef408053b761fb5d07e7abe472";
	private String telizeUrl = "http://www.telize.com/geoip/";
	private String dbipUrl = "http://api.db-ip.com/addrinfo?addr="; //173.194.67.1&api_key=123456789"
	// Append address, zipcode, or lat/lng
	private String googleUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";

	public double scale = 1;
	
	
	
	
	public LocationFinder(Plugin p) {
		this.plug = p;

	}
	
	public void setKeyPoint(KeyPoint k) {
		this.key = k;
	}
	
	public void setScale(double s) {
		this.scale = s;
	}
	
	
	public GeoCoordinate mcToGC(MinecraftCoordinate mc){
		//calculate distance in blocks
			//to meters
			//to lat lon
			//whereis lat lon
		
		MinecraftCoordinate vect = this.key.mcDistFromKeyPoint(mc);
		plug.getLogger().info("distance from keypoint: "+ vect.toString());
		
		double metersLat = vect.x*scale;
		double metersLng = vect.z*scale;
		plug.getLogger().info(metersLat + " "+metersLng);
		double lat = metersToLat(metersLat);
		plug.getLogger().info("latitude: "+lat);
		double lng = metersToLng(metersLng, lat);
		plug.getLogger().info("longitude: " + lng);
		
		lat += key.gc.latitude;
		lng += key.gc.longitude;
		plug.getLogger().info("latitude: "+lat);
		plug.getLogger().info("longitude: " + lng);
		
		return new GeoCoordinate(lat,lng);
		
	}
	
	public double metersToLng(double meters, double lat){
		double newLng = 0;
		
		newLng = meters / (GeoCoordinate.LNGTOM*Math.cos(lat) * 1000);
		
		return newLng;
	}
	
	public double metersToLat(double meters){
		double newLat = 0;
		
		newLat = meters/ GeoCoordinate.LATTOM;
		plug.getLogger().info("meters: " + meters);
		return newLat;
	}
	
	
	public MinecraftCoordinate gcToMC(String ip) {
		
		
		//calc distance from keypoint
		//x += dfk.x
		//y += dfk.y
		//assume x = east west
		//y = northsouth
		
		
		
		

		float latitude = 1;
		float longitude = 1;
		return new MinecraftCoordinate();

		
	}
	
	public GeoCoordinate ipToGC(String ip) {
		float latitude = 0, longitude = 0;
//		String url = telizeUrl;
		String url = dbipUrl;
		String charset = "UTF-8";
		String param1 = ip + "&api_key=" + dbipkey;
		String query = "";
		try {
			query = String.format("%s",
					URLEncoder.encode(param1, charset));
			plug.getLogger().info(query);
		} catch (UnsupportedEncodingException e) {
			plug.getLogger().info("Something went wrong with encoding URL, Location Finder");
			e.printStackTrace();
		}

		URLConnection connection = null;
		try {
			connection = new URL(url + "?" + query).openConnection();
		} catch (IOException e) {
			plug.getLogger().info("connection nope");
			e.printStackTrace();
		}
		connection.setRequestProperty("Accept-Charset", charset);
		try {
			InputStream response = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response));
			StringBuilder result = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
			    result.append(line);
			}
			plug.getLogger().info(result.toString());
			
			
			JsonElement jelement = new JsonParser().parse(result.toString());
			JsonObject jobject = jelement.getAsJsonObject();
			plug.getLogger().info(jobject.getAsString());
			
		
			
		} catch (IOException e) {
			plug.getLogger().info("inputstream nope");
			e.printStackTrace();
		}

		return (new GeoCoordinate(
				latitude, longitude));

	}
	

}