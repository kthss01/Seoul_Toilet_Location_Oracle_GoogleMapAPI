package com.kay.controller;

import static com.kay.common.GoogleMapTemplate.Map;
import static com.kay.common.GoogleMapTemplate.downloadMap;
import static com.kay.common.GoogleMapTemplate.fileDelete;
import static com.kay.common.GoogleMapTemplate.getMap;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.kay.common.LocationTemplate;
import com.kay.model.exception.MainException;
import com.kay.model.vo.Location;
import com.kay.model.vo.Marker;
import com.kay.service.MainService;

public class MainController {

	public static ImageIcon setMap(String location) {
		if (!location.equals(""))
			Map().setCenter(location);

		downloadMap();
		ImageIcon icon = getMap();
		fileDelete();

		return icon;
	}

	public static void addMarker(String location, String size, String color, String label) {

		Marker marker = new Marker();

		marker.setLocation(location);

		marker.setSize(size);
		marker.setColor(color);
		marker.setLabel(label);

		ArrayList<Marker> markers = Map().getMarkers().getMarkers();
		boolean hasMarker = false;
		for (Marker temp : markers) {
			if (marker.equals(temp)) {
				hasMarker = true;
				temp.setLocation(marker.getLocation());
				break;
			}
		}

		if (!hasMarker)
			Map().getMarkers().addMarker(marker);
	}

	public static void updateMap(String location, String sizeX, String sizeY, String zoomLevel, String maptype) {
		Map().setChanged(true);

		Map().setCenter(location);
		Map().setSizeX(Integer.parseInt(sizeX));
		Map().setSizeY(Integer.parseInt(sizeY));
		Map().setZoom(Integer.parseInt(zoomLevel));

		Map().setMaptype(maptype);
	}

	public void exitProgram() {
		new MainService().exitProgram();
	}

	// 서울 위치 좌표인 GRS80을 WGS84 좌표계로 모두 변경
	public void convertLocation() {
		ArrayList<Location> list;
		
		int result;
		
		try {
			System.out.println("convertLocation : 조회 시작");
			list = new MainService().selectAllSeoulLocation();
			
			System.out.println("convertLocation: 변환 시작");
//			System.out.println(list.size());
			for (Location location : list) {
//				System.out.println(location);
				if (location.getLoc_x() == null || location.getLoc_y() == null) 
					continue;
				double x = Double.parseDouble(location.getLoc_x());
				double y = Double.parseDouble(location.getLoc_y());
//				System.out.println(x + "\t" + y);
				Point2D.Double loc = LocationTemplate.getTransform(x, y);
				location.setLoc_x(String.valueOf(loc.getX()));
				location.setLoc_y(String.valueOf(loc.getY()));
			}
			
			System.out.println("convertLocation: 업데이트 시작");
			result = new MainService().updateAllSeoulLocation(list);
			if (result > 0) {
				System.out.println("서울 위치 좌표 변환 성공");
			}
			
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
