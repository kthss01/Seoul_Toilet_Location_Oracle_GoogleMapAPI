package com.kay.run;

import java.util.Map;

import com.kay.common.LocationTemplate;
import com.kay.model.vo.Toilet;
import com.kay.view.MainView;


public class Run {

	public static void main(String[] args) {
//		new MainController().convertLocation();
		
		// toilet test
		Map<String, Toilet> map = LocationTemplate.getToilet();
		for (Toilet toilet : map.values()) {
			System.out.println(toilet);
		}
		System.out.println(map.size());
		
//		new MainView();
	}
}
