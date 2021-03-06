package com.kay.controller;

import static com.kay.common.GoogleMapTemplate.Map;
import static com.kay.common.GoogleMapTemplate.downloadMap;
import static com.kay.common.GoogleMapTemplate.fileDelete;
import static com.kay.common.GoogleMapTemplate.getMap;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.kay.common.GoogleMapTemplate;
import com.kay.common.LocationTemplate;
import com.kay.model.exception.MainException;
import com.kay.model.vo.GoogleMap;
import com.kay.model.vo.Location;
import com.kay.model.vo.Marker;
import com.kay.model.vo.Path;
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

	public static ImageIcon setToiletMap(Toilet toilet) {

		String center = GoogleMapTemplate.OriginMap().getCenter();

		Marker centerMarker = new Marker(center);

		Map().clearMap();

		Map().setCenter(toilet.getLocation());

		Map().getMarkers().addMarker(centerMarker);

		String size = "mid";
		String color = toilet.getColor();
		String label = toilet.getMarkerLabel();

		Map().getMarkers().addMarker(new Marker(size, color, label, toilet.getLocation()));

		Path path = new Path();
		path.setColor(color);

		path.setLocation(toilet.getLocation());
		path.setLocation(center);

		Map().getPaths().addPath(path);

		downloadMap();
		ImageIcon icon = getMap();
		fileDelete();

		return icon;
	}

	public static ImageIcon findToiletMap(String address) {

		Map().setCenter(address);
		Map().getMarkers().getMarkers().clear();
		Map().getMarkers().getMarkers().add(new Marker(address));

		updateMarker();

		downloadMap();
		ImageIcon icon = getMap();
		fileDelete();

		return icon;
	}

	public static ImageIcon downloadGoogleMap() {
		downloadMap();
		ImageIcon icon = getMap();
		fileDelete();

		return icon;
	}

	public static ImageIcon updateMapTypeMap(String maptype) {
		Map().setMaptype(maptype);

		downloadMap();
		ImageIcon icon = getMap();
		fileDelete();

		return icon;
	}

	public static ImageIcon updateZoomLevelMap(int zoomLevel) {
		Map().setZoom(zoomLevel);

		downloadMap();
		ImageIcon icon = getMap();
		fileDelete();

		return icon;
	}

	private static void updateMarker() {
		List<Toilet> toiletList = LocationTemplate.getNearToilet();
		for (int i = 0; i < toiletList.size(); i++) {
			Toilet toilet = toiletList.get(i);
			System.out.println(toilet);

			String size = "mid";
			String color = toilet.getColor();
			String label = toilet.getMarkerLabel();

			Map().getMarkers().addMarker(new Marker(size, color, label, toilet.getLocation()));
		}

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

	// ?????? ?????? ????????? GRS80??? WGS84 ???????????? ?????? ??????
	// DB?????? ??????????????? ???????????? ?????? X
//	public void convertLocation() {
//		ArrayList<Location> list;
//		
//		int result;
//		
//		try {
//			System.out.println("convertLocation : ?????? ??????");
//			list = new MainService().selectAllSeoulLocation();
//			
//			System.out.println("convertLocation: ?????? ??????");
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
//			System.out.println("convertLocation: ???????????? ??????");
//			result = new MainService().updateAllSeoulLocation(list);
//			if (result > 0) {
//				System.out.println("?????? ?????? ?????? ?????? ??????");
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

	// ????????? ?????? ?????? ???????????? ?????? ?????? ??????????????? ?????? ????????? ???????????? ??????
	public Location selectAddressSeoulLocation(String address, String mainNum, String subNum) {
		Location location = null;

		try {
			location = new MainService().selectAddressSeoulLocation(address, mainNum, subNum);
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}

		return location;
	}

	// ?????? x,y ??? ?????? ?????? ??????????????? ????????? ?????? ????????? ?????? ????????? ???????????? ??????
	public Location selectXYSeoulLocation(String loc_x, String loc_y) {
		Location location = null;

		// ?????? ?????? ???????????? GRS80 ???????????? ???????????? ?????????
		// ???????????? WGS84 ???????????? GRS80 ???????????? ?????? ??????
		Point2D.Double loc = LocationTemplate.getTransformWGSToGRS(Double.parseDouble(loc_y),
				Double.parseDouble(loc_x));
		loc_x = String.valueOf(loc.getX());
		loc_y = String.valueOf(loc.getY());

		try {
			location = new MainService().selectXYSeoulLocation(loc_x, loc_y);
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}

		return location;
	}

	// ?????? x,y ??? ????????? ??????????????? ?????? ????????? ???????????? ?????? ??? ????????? ???????????? ??????
	public void selectFindToilet(String loc_x, String loc_y) {
		// ?????? ?????? ???????????? GRS80 ???????????? ????????????
		// ????????? ???????????? WGS84 ??????????????? ?????????
		// GRS80 -> WGS84??? ????????? ?????? ??????
		Point2D.Double loc = LocationTemplate.getTransformGRSToWGS(Double.parseDouble(loc_x),
				Double.parseDouble(loc_y));

		loc_x = String.valueOf(loc.getX());
		loc_y = String.valueOf(loc.getY());

		try {
			new MainService().selectFindToilet(loc_x, loc_y);
		} catch (MainException e) {
			System.out.println(e.getMessage());
		}
	}

	public String searchAddressSeoulLocation(String address) {
		// ?????? ????????? ????????????19??? 24 ?????? ?????? ????????? ??? split?????? ??? ??????

		int offset = address.split(" ").length - 2;
		if (address.split(" ").length == 5)
			offset--;

		String mainNum = address.split(" ")[1 + offset];
		String subNum = mainNum.indexOf("-") != -1 ? mainNum.split("-")[1] : "0";
		mainNum = mainNum.indexOf("-") != -1 ? mainNum.split("-")[0] : mainNum;
		address = address.split(" ")[offset];
		
		System.out.println(address + " " + mainNum + " " + subNum);
		
		Location location = selectAddressSeoulLocation(address, mainNum, subNum);

		System.out.println(location);

		Point2D.Double loc = LocationTemplate.getTransformGRSToWGS(Double.parseDouble(location.getLoc_x()),
				Double.parseDouble(location.getLoc_y()));
		String loc_x = String.format("%.6f", loc.getX());
		String loc_y = String.format("%.6f", loc.getY());

		new MainController().selectFindToilet(location.getLoc_x(), location.getLoc_y());

		return loc_y + "," + loc_x;

//		return subNum.equals("0") || subNum.equals("") ? address + " " + mainNum : address + " " + mainNum + "-" + subNum; 
	}

	public String searchXYSeoulLocation(String address) {
		// 37.56970320467521, 126.96665458339758 ??? ?????? ????????? ???

		String[] xy = address.split(", ");
//		Location location = selectXYSeoulLocation(xy[0], xy[1]);
//		
//		System.out.println(location);
//		
//		Point2D.Double loc = LocationTemplate.getTransformGRSToWGS(Double.parseDouble(location.getLoc_x()), Double.parseDouble(location.getLoc_y()));
//		String loc_x = String.format("%.6f", loc.getX());
//		String loc_y = String.format("%.6f", loc.getY());
//		
//		new MainController().selectFindToilet(location.getLoc_x(), location.getLoc_y());

		Point2D.Double loc = LocationTemplate.getTransformWGSToGRS(Double.parseDouble(xy[1]),
				Double.parseDouble(xy[0]));
		String loc_x = String.valueOf(loc.getX());
		String loc_y = String.valueOf(loc.getY());

		new MainController().selectFindToilet(loc_x, loc_y);

		loc_x = String.format("%.6f", Double.parseDouble(xy[0]));
		loc_y = String.format("%.6f", Double.parseDouble(xy[1]));

		return loc_x + "," + loc_y;

//		return subNum.equals("0") || subNum.equals("") ? address + " " + mainNum : address + " " + mainNum + "-" + subNum; 
	}
}
