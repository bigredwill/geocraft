/**
 * 
 */
package com.WeerdSalmon.GeoCraft;

/**
 * @author Casa
 *
 */
public class KeyPoint {
	public MinecraftCoordinate mc;
	public GeoCoordinate gc;
	
	public KeyPoint(MinecraftCoordinate mc, GeoCoordinate gc) {
		this.mc = mc;
		this.gc = gc;
	}
	
	public GeoCoordinate getGC(){
		return this.gc;
	}
	
	public MinecraftCoordinate getMC(){
		return this.mc;
	}
	
	public MinecraftCoordinate mcDistFromKeyPoint(MinecraftCoordinate fc) {
		int x, y, z;
		x = mc.x - fc.x;
		y = mc.y - fc.y;
		z = mc.z - fc.z;
		return new MinecraftCoordinate(x, y, z);
	}

	
	public GeoCoordinate gcDistFromKeyPoint(GeoCoordinate fc) {
		double lat, lng;
		lat = gc.latitude - fc.latitude;
		lng = gc.longitude - fc.longitude;
		return new GeoCoordinate(lat, lng);
	}
	
	
}
