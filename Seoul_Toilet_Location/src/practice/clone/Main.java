package practice.clone;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// clone 테스트
		
		ArrayList<String> list = new ArrayList<String>();
		
		for (int i = 0; i < 5; i++) {
			list.add(String.valueOf(i));
		}
		
		// String 같은건 그냥 clone해도 됨
		ArrayList<String> list2 = (ArrayList<String>) list.clone();
		
//		list2.clear();
//		System.out.println(list.size());
		
		list.clear();
		for (String string : list2) {
			System.out.println(string);
		}
	}

}
