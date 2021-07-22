package com.kay.controller;

import static com.kay.common.GoogleMapTemplate.Map;
import static com.kay.common.GoogleMapTemplate.downloadMap;
import static com.kay.common.GoogleMapTemplate.fileDelete;
import static com.kay.common.GoogleMapTemplate.getMap;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;

import com.kay.common.LocationTemplate;
import com.kay.model.exception.MainException;
import com.kay.model.vo.Location;
import com.kay.model.vo.Marker;
import com.kay.model.vo.Toilet;
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
	// DB에서 처리하기로 변경하여 필요 X
//	public void convertLocation() {
//		ArrayList<Location> list;
//		
//		int result;
//		
//		try {
//			System.out.println("convertLocation : 조회 시작");
//			list = new MainService().selectAllSeoulLocation();
//			
//			System.out.println("convertLocation: 변환 시작");
////			System.out.println(list.size());
//			for (Location location : list) {
////				System.out.println(location);
//				if (location.getLoc_x() == null || location.getLoc_y() == null) 
//					continue;
//				double x = Double.parseDouble(location.getLoc_x());
//				double y = Double.parseDouble(location.getLoc_y());
////				System.out.println(x + "\t" + y);
//				Point2D.Double loc = LocationTemplate.getTransformGRSToWGS(x, y);
//				location.setLoc_x(String.valueOf(loc.getX()));
//				location.setLoc_y(String.valueOf(loc.getY()));
//			}
//			
//			System.out.println("convertLocation: 업데이트 시작");
//			result = new MainService().updateAllSeoulLocation(list);
//			if (result > 0) {
//				System.out.println("서울 위치 좌표 변환 성공");
//			}
//			
//		} catch (MainException e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	public Map<String, Toilet> selectAllToilet() {
		Map<String, Toilet> map = null;
		
		try {
			map = new MainService().selectAllToilet();
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}
		
		return map;
	}
	
	// 도로명 주소 본번 주번으로 서울 위치 테이블에서 해당 위치를 찾아주는 함수
	public Location selectAddressSeoulLocation(String address, String mainNum, String subNum) {
		Location location = null;
		
		try {
			location = new MainService().selectAddressSeoulLocation(address, mainNum, subNum);
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}
		
		return location;
	}
	
	// 좌표 x,y 로 서울 위치 테이블에서 좌표와 가장 가까운 해당 위치를 찾아주는 함수
	public Location selectXYSeoulLocation(String loc_x, String loc_y) {
		Location location = null;
		
		// 서울 위치 테이블은 GRS80 좌표계로 되어있기 때문에 
		// 입력받은 WGS84 좌표계를 GRS80 좌표계로 변환 필요
		Point2D.Double loc = LocationTemplate.getTransformWGSToGRS(Double.parseDouble(loc_y), Double.parseDouble(loc_x));
		loc_x = String.valueOf(loc.getX());
		loc_y = String.valueOf(loc.getY());
		
		try {
			location = new MainService().selectXYSeoulLocation(loc_x, loc_y);
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}
		
		return location;
	}
	
	// 좌표 x,y 로 화장실 테이블에서 가장 가까운 화장실을 찾고 그 거리를 계산하는 함수
	public void selectFindToilet(String loc_x, String loc_y) {
		
		// 서울 위치 테이블은 GRS80 좌표계로 되어있고
		// 화장실 테이블은 WGS84 좌표계이기 때문에
		// GRS80 -> WGS84로 좌표계 변환 필요
		Point2D.Double loc = LocationTemplate.getTransformGRSToWGS(Double.parseDouble(loc_x), Double.parseDouble(loc_y));
		loc_x = String.valueOf(loc.getX());
		loc_y = String.valueOf(loc.getY());
		
		try {
			new MainService().selectFindToilet(loc_x, loc_y);
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}
	}
}
