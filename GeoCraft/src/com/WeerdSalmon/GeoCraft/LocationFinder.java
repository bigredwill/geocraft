package com.WeerdSalmon.GeoCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.plugin.Plugin;






public class LocationFinder {
	public static final int LATTOMETERS = 110540;
	public static final double LNGTOMETERS = 111.320;
	
	private Plugin plug;
	public KeyPoint key;
	
	public double scale = 1;
	
	public LocationFinder(Plugin p) {
		this.plug = p;

	}
	
	public MinecraftCoordinate gcToMC(GeoCoordinate gc) {
		//Calculate distance between keypoint and gc 
		GeoCoordinate vectDiff = this.key.gcDistFromKeyPoint(gc);
		
		//convert to meters and scale according to map scale
		int xScaled = (int) (latToMeters(vectDiff.latitude) / scale);
		int zScaled = (int) (lngToMeters(vectDiff.longitude) / scale);
		
		//get Minecraft coordinates from keypoint
		xScaled += key.mc.x;
		zScaled += key.mc.z;
		
		return new MinecraftCoordinate(xScaled,0,zScaled);
	}
	
	public String geocode(GeoCoordinate gc) {
		
		//https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452
		String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";
		String param = url + gc.latitude + "," + gc.longitude;
		String query = "";
		
		try {
			InputStream response = new URL(param).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response));
			StringBuilder result = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
			    result.append(line);
			}	

			Map<String, Object> jsonroot = new Gson().fromJson(result.toString(), Map.class);
			ArrayList<Map<String, Object>> al =  (ArrayList<Map<String, Object>>) jsonroot.get("results");
			plug.getLogger().info(al.get(0).get("formatted_address").toString());
			query = al.get(0).get("formatted_address").toString();
			
//			for (Iterator iterator = ((ArrayList<Map<String, Object>>) al.get(0).get("address_components")).iterator(); iterator.hasNext();) {
//				Map<String, Object> map = (Map<String, Object>) iterator.next();
//				plug.getLogger().info(map.toString());
//				
//			}
			
		} catch (IOException e) {
			plug.getLogger().info("IOException");
			e.printStackTrace();
		}
		return query;
	}
//	thanks Jim Lewis
//	http://stackoverflow.com/a/1253545
	public int latToMeters(double latitude) {
		return (int) Math.ceil(LATTOMETERS * latitude);
	}
	
	public int lngToMeters(double longitude) {
		return (int) Math.ceil(longitude * (LNGTOMETERS*Math.cos(longitude) * 1000));
	}
	
	public GeoCoordinate mcToGC(MinecraftCoordinate mc){

		//calculate distance in Minecraft Blocks
		MinecraftCoordinate vectDiff = this.key.mcDistFromKeyPoint(mc);
		
		double xScaled = vectDiff.x * scale;
		double zScaled = vectDiff.z * scale;
		
		double lat = metersToLat(zScaled);
		double lng = metersToLng(xScaled, lat);
		
		lat = key.gc.latitude + lat;
		lng = key.gc.longitude - lng;
		return new GeoCoordinate(lat,lng);
	}
	
	public double metersToLat(double meters){
		double newLat = 0;
		newLat = meters / LATTOMETERS;
		return newLat;
	}
	
	public double metersToLng(double meters, double lat){
		double newLng = 0;
		newLng = meters / (LNGTOMETERS*Math.cos(lat) * 1000);
		return newLng;
	}
	
	
	public void setKeyPoint(KeyPoint k) {
		this.key = k;
	}
	
	public void setScale(double s) {
		this.scale = s;
	}
	

}
