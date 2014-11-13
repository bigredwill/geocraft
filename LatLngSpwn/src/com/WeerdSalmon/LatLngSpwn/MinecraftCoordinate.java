package com.WeerdSalmon.LatLngSpwn;

public class MinecraftCoordinate {
	
	public int x, y, z;
	public MinecraftCoordinate(){
		x = 0;
		y = 0;
		z = 0;
	}
	
	public MinecraftCoordinate(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX(){
		return this.x;
	}
	public int getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setZ(int z) {
		this.z = z;
	}
	@Override
	public String toString() {
		return String.format("(%d,%d,%d)", x,y,z);
	} 
}

