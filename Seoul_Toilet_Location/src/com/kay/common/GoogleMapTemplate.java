package com.kay.common;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;

import com.kay.model.vo.GoogleMap;

public class GoogleMapTemplate {
	
	private static GoogleMap map = null;
	
	private static GoogleMap originMap = null; // 화장실 리스트 선택시 이전 검색 정보 clone 해두기 위한 변수
	
	public static GoogleMap Map() {
		if (map == null) {
			map = new GoogleMap();
			map.initMap();
		}
		return map;
	}
	
	public static GoogleMap OriginMap() {
		return originMap;
	}
	
	public static boolean isCloneMap() {
		return originMap != null;
	}
	
	public static void CloneMap() {
		try {
			originMap = map.clone();
			
//			System.out.println(map.toString());
//			System.out.println();
//			System.out.println(map2);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public static void RestoreMap() {
		try {
			
//			System.out.println(map2);
//			System.out.println();
//			System.out.println(map);
			
			map = originMap.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public static void downloadMap() {
		if(Map().isChanged() == false) {
			return;
		}
		
		try {
			String imageURL = map.toString();
			System.out.println(imageURL); // test
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(!map.getCenter().equals("") ? map.getCenter() : "template");
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ImageIcon getMap() {
		Map().setChanged(false);
		
		return new ImageIcon(
				(new ImageIcon(map.getCenter())).getImage().getScaledInstance(Map().getSizeX(), Map().getSizeY(), java.awt.Image.SCALE_SMOOTH)); // 이거 사이즈 좀 잘 보자
	}
	
	public static void fileDelete() {
		File f = new File(map.getCenter());
		f.delete();
	}
	
	/**
	 * Converts a hex string to a color. If it can't be converted null is returned.
	 * @param hex (i.e. #CCCCCCFF or CCCCCC)
	 * @return Color
	 */
	public static Color HexToColor(String hex) 
	{
	    hex = hex.replace("0x", "");
	    switch (hex.length()) {
	        case 6:
	            return new Color(
	            Integer.valueOf(hex.substring(0, 2), 16),
	            Integer.valueOf(hex.substring(2, 4), 16),
	            Integer.valueOf(hex.substring(4, 6), 16));
	        case 8:
	            return new Color(
	            Integer.valueOf(hex.substring(0, 2), 16),
	            Integer.valueOf(hex.substring(2, 4), 16),
	            Integer.valueOf(hex.substring(4, 6), 16),
	            Integer.valueOf(hex.substring(6, 8), 16));
	    }
	    return null;
	}
}
