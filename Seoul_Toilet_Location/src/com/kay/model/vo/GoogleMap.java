package com.kay.model.vo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GoogleMap implements Cloneable {
	private final static String BASIC_URL = "https://maps.googleapis.com/maps/api/staticmap";

	private final static String[] MAPTYPES = { "roadmap", "satellite", "terrain", "hybrid" };
	private final static String[] MARKER_SIZE = new String[] { "normal", "mid", "small", "tiny" };
	private final static String[] MARKER_COLOR = new String[] { "black", "brown", "green", "purple", "yellow", "blue",
			"gray", "orange", "red", "white" };

	private static ArrayList<String> markerLabel;

	private final static String[] LEGEND_COLOR = new String[] { "0xED315D", "0x7CA960", "0x06D6A0", "0x7BD4F4",
			"0xFFD166", "0x37433D" };

	private String key;

	private boolean isChanged; // 상태 변화시 체크

	private String center; // 주소나 위도,경도 형태로 표현
	private int zoom; // 0 ~ 21 정도로
	private int sizeX; // 이미지 크기 X (가로)
	private int sizeY; // 이미지 크기 Y (세로)
	// scale은 넘어가자 필요하면 그 때 만들기로
	private String maptype = ""; // roadmap (default), satellite, terrain, hybrid 중 enum을 만들지 해야할 거 같음
	
	private Markers markers; // marker들 여러개 따로 클래스로 처리하려고함
	// path도 일단 pass

	private Paths paths;
	
	private StringBuilder sb;

	public GoogleMap() {
		File f = new File("resources/key.secret");
		if (f.exists()) {
			readKey(f.getPath());
		} else {
			key = JOptionPane.showInputDialog("key : ");
		}

		markers = new Markers();
		
		paths = new Paths();

		markerLabel = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			markerLabel.add(String.valueOf(i));
		}

		for (int i = 0; i < 26; i++) {
			markerLabel.add(String.valueOf((char) ('A' + i)));
		}

	}

	private void readKey(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			key = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GoogleMap(String center, int zoom, int sizeX, int sizeY) {
		this();
		this.center = center;
		this.zoom = zoom;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public GoogleMap(String center, int zoom, int sizeX, int sizeY, String maptype) {
		this(center, zoom, sizeX, sizeY);
		this.center = center;
		this.zoom = zoom;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.maptype = maptype;
	}

	public static String[] getLegendColor() {
		return LEGEND_COLOR;
	}

	public static String[] getMarkerLabel() {
		return markerLabel.stream().toArray(String[]::new);
//		return markerLabel.toArray(new String[markerLabel.size()]);
	}

	public static String[] getMarkerColor() {
		return MARKER_COLOR;
	}

	public static String[] getMarkerSize() {
		return MARKER_SIZE;
	}

	public static String[] getMaptypes() {
		return MAPTYPES;
	}

	public Markers getMarkers() {
		return markers;
	}

	public void setMarkers(Markers markers) {
		this.markers = markers;
	}
	
	public Paths getPaths() {
		return paths;
	}
	
	public void setPaths(Paths paths) {
		this.paths = paths;
	}

	public String getMaptype() {
		return maptype;
	}

	public void setMaptype(String maptype) {
		this.maptype = maptype;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public static String getBasicUrl() {
		return BASIC_URL;
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	public void initMap() {
		zoom = 15;
		sizeX = 1024;
		sizeY = 1024;
		isChanged = true;
		center = "서울";
	}

	// url 생성
	@Override
	public String toString() {
		sb = new StringBuilder();

		sb.append(BASIC_URL).append("?");

		if (!center.equals("")) {
			try {
				sb.append("center=").append(URLEncoder.encode(center, "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (zoom != 0)
			sb.append("zoom=").append(zoom).append("&");
		sb.append("size=").append(sizeX).append("x").append(sizeY).append("&");

		if (!maptype.equals(""))
			sb.append("maptype=").append(maptype).append("&");

		if (markers.getMarkers().size() != 0)
			sb.append(markers.toString()).append("&");
		
		if (paths.getPaths().size() != 0) 
			sb.append(paths.toString()).append("&");

		sb.append("key=").append(key);

		return sb.toString();
	}

	@Override
	public GoogleMap clone() throws CloneNotSupportedException {
		GoogleMap googleMap = (GoogleMap) super.clone();

		googleMap.markers = (Markers) markers.clone();
		
		googleMap.paths = (Paths) paths.clone();

		return googleMap;
	}

	public void clearMap() {
		markers.getMarkers().clear();

		paths.getPaths().clear();
		
		center = "";
		zoom = 0;
	}

}
