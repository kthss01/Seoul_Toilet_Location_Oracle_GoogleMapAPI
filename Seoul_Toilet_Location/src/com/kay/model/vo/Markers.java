package com.kay.model.vo;

import java.util.ArrayList;

public class Markers implements Cloneable {
	
	private ArrayList<Marker> list = new ArrayList<Marker>();
	
	public Markers() {
	}

	public ArrayList<Marker> getMarkers() {
		return list;
	}
	
	public void addMarker(Marker marker) {
		list.add(marker);
	}
	
	public Marker getMarker(int i) {
		return list.get(i);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < list.size(); i++) {
			Marker marker = list.get(i);
			sb.append(marker.toString());
			
			if (i != list.size() - 1) {
				sb.append("&");
			}
		}
		
		return sb.toString();
	}

	@Override
	protected Markers clone() throws CloneNotSupportedException {
		Markers markers = (Markers) super.clone();
		
		markers.list = (ArrayList<Marker>) list.clone();
		
		return markers;
	}
	
	
}
