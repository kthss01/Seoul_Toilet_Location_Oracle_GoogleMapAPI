package practice.advance.mvc.controller;

import static practice.advance.mvc.common.GoogleMapTemplate.Map;
import static practice.advance.mvc.common.GoogleMapTemplate.downloadMap;
import static practice.advance.mvc.common.GoogleMapTemplate.fileDelete;
import static practice.advance.mvc.common.GoogleMapTemplate.getMap;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import practice.advance.mvc.model.vo.GoogleMap;
import practice.advance.mvc.model.vo.Marker;

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

}
