package com.WeerdSalmon.LatLngSpwn;

public final class CoordinateConverter {
	private float scale = 1;

	
	public CoordinateConverter(float scale) {
		this.scale = scale;
	}

	/**
	 * 
	 * @param mc
	 * @return
	 */
	public GeoCoordinate mcToGeoCoordinate(MinecraftCoordinate mc) {
		return null;
	}

	/**
	 * Should query the map at MC x,z to obtain the y altitude to spawn. If not,
	 * might spawn in mountain.
	 * 
	 * @param gc
	 *            {@link GeoCoordinate}
	 * @return {@link MinecraftCoordinate}
	 */
	public static MinecraftCoordinate geoToMineCraftCoordinate(GeoCoordinate gc) {
		return null;
	}
}
