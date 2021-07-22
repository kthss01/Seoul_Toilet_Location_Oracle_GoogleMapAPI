package com.kay.common;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;
import com.kay.controller.MainController;
import com.kay.model.vo.Toilet;

public class LocationTemplate {
	
	private static Projection proj = null;
	
	private static Map<String, Toilet> toiletMap = null;
	
	// 최대로 찾는 화장실 갯수 (마커 최대갯수가 15개라 찾는 화장실 갯수도 15개 최대로) 
	public static final int MAX_TOILET = 15;
	// 특정 위치에서 가까운 화장실의 id를 담고있는 String 배열 
	private static String[] nearToiletIdArr = null;
	
	public static Map<String, Toilet> getToilet() {
		if (toiletMap == null) {
			toiletMap = new MainController().selectAllToilet();
		}
		
		return toiletMap;
	}
	
	public static String[] getNearToiletId() {
		if (nearToiletIdArr == null) {
			nearToiletIdArr = new String[MAX_TOILET];
		}
		
		return nearToiletIdArr;
	}
	
	public static List<Toilet> getNearToilet() {
		List<Toilet> list = new ArrayList<Toilet>();
		Map<String, Toilet> toiletMap = getToilet();
		
		String[] nearToiletIdArr = getNearToiletId();
		
		for (String toiletId : nearToiletIdArr) {
			list.add(toiletMap.get(toiletId));
		}
		
		return list;
	}
	
	public static Projection getPojection() {
		if (proj == null) {
			proj = ProjectionFactory.fromPROJ4Specification(
		            new String[] {
		                "+proj=tmerc", 
		                "+lat_0=38", 
		                "+lon_0=127.5", 
		                "+k=0.9996", 
		                "+x_0=1000000", 
		                "+y_0=2000000",
		                "+ellps=GRS80", 
		                "+units=m +no_defs"
		            });
		}

		return proj;
	}
	
	// 좌표계 변환 WGS84 -> GSR80
	public static Point2D.Double getTransformWGSToGRS(double longitude, double latitute) {
		Point2D.Double srcProject = new Point2D.Double(longitude, latitute);
		
		return getPojection().transform(srcProject, new Point2D.Double());
	}

	// 좌표계 변환 GRS80 -> WGS84
	public static Point2D.Double getTransformGRSToWGS(double longitude, double latitute) {
		Point2D.Double srcProject = new Point2D.Double(longitude, latitute);
		
		return getPojection().inverseTransform(srcProject, new Point2D.Double());
	}
	
	// DB에서 처리하기로 변경하기로해서 필요 X
//	/**
//	 * 두 지점간의 거리 계산
//	 *
//	 * @param lat1 지점 1 위도
//	 * @param lon1 지점 1 경도
//	 * @param lat2 지점 2 위도
//	 * @param lon2 지점 2 경도
//	 * @param unit 거리 표출단위
//	 * @return
//	 */
//	public static double distance(double lon1, double lat1, double lon2, double lat2, String unit) {
//
//		double theta = lon1 - lon2;
//		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
//				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
//
//		dist = Math.acos(dist);
//		dist = rad2deg(dist);
//		dist = dist * 60 * 1.1515;
//
//		if (unit.equals("kilometer")) {
//			dist = dist * 1.609344;
//		} else if (unit.equals("meter")) {
//			dist = dist * 1609.344;
//		}
//
//		return (dist);
//	}
//
//	// This function converts decimal degrees to radians
//	private static double deg2rad(double deg) {
//		return (deg * Math.PI / 180.0);
////		return Math.toRadians(deg);
//	}
//
//	// This function converts radians to decimal degrees
//	private static double rad2deg(double rad) {
//        return (rad * 180 / Math.PI);
//    }
	
}
