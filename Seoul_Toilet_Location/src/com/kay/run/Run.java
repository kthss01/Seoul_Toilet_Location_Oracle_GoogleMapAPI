package com.kay.run;

import java.util.List;

import com.kay.common.LocationTemplate;
import com.kay.controller.MainController;
import com.kay.model.vo.Location;
import com.kay.model.vo.Toilet;
import com.kay.view.MainView;


public class Run {

	public static void main(String[] args) {
//		new MainController().convertLocation();
		
		// toilet test
//		Map<String, Toilet> map = LocationTemplate.getToilet();
//		for (Toilet toilet : map.values()) {
//			System.out.println(toilet);
//		}
//		System.out.println(map.size());
		
		// 도로명 주소, 건물본번, 건물부번 입력받아 서울 위치 테이블에서 가져오기
//		System.out.println("도로명 주소, 건물본번, 건물부번 입력받아 서울 위치 테이블에서 가져오기");
//		String address = "자하문로";
//		String mainNum = "94";
//		String subNum = "0";
//		Location location = new MainController().selectAddressSeoulLocation(address, mainNum, subNum);
//		System.out.println(location);
		
//		// 서울 위치 테이블에서 해당 좌표와 가장 가까운 위치 가져오기
//		System.out.println("서울 위치 테이블에서 해당 좌표와 가장 가까운 위치 가져오기");
//		// 37.57185863552551, 126.99885096392968
////		String loc_x = "37.57185863552551";
////		String loc_y = "126.99885096392968";
//		// 37.570422342703516, 126.9888591131944
////		String loc_x = "37.570422342703516";
////		String loc_y = "126.9888591131944";
////		37.434982,126.903401
////		37.577923260323274, 127.00157376223095
//		String loc_x = "37.577923260323274";
//		String loc_y = "127.00157376223095";
//		Location location2 = new MainController().selectXYSeoulLocation(loc_x, loc_y);
//		System.out.println(location2);
//		
//		// 해당 좌표와 가장 가까운 화장실 15개 찾기
//		System.out.println("해당 좌표와 가장 가까운 화장실 15개 찾기");
//		loc_x = location2.getLoc_x();
//		loc_y = location2.getLoc_y();
//		new MainController().selectFindToilet(loc_x, loc_y);
//		List<Toilet> toiletList = LocationTemplate.getNearToilet();
//		for (Toilet toilet : toiletList) {
//			System.out.println(toilet);
//		}
		
		// view
		new MainView();
	}
}
