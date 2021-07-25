package com.kay.model.vo;

import java.util.HashMap;
import java.util.Map;

import com.kay.model.exception.MainException;

public class Toilet {
	
	private final static Map<String, String> COLOR_MAP = new HashMap<String, String>() {
		{
			put("화장실", GoogleMap.getLegendColor()[0]);
			put("공원", GoogleMap.getLegendColor()[1]);
			put("지하철", GoogleMap.getLegendColor()[2]);
			put("공공시설", GoogleMap.getLegendColor()[3]);
			put("상가", GoogleMap.getLegendColor()[4]);
			put("기타", GoogleMap.getLegendColor()[5]);
		}
	};
	
	private final static Map<String, String> LEGEND_MAP = new HashMap<String, String>() {
		{
			// 화장실
			put("화장실", "화장실"); 
			put("공중화장실", "화장실"); 
			put("개방화장실", "화장실");
			
			// 공원
			put("근린공원", "공원");
			put("공원", "공원");
			
			// 지하철
			put("지하철", "지하철");
			put("철도역", "지하철");
			
			// 공공시설
			put("전시관", "공공시설");
			put("공용시설", "공공시설");
			put("체육시설", "공공시설");
			put("도서관", "공공시설");
			put("공공시설", "공공시설");
			put("박물관", "공공시설");
			put("학교", "공공시설");
			put("대학교", "공공시설");
			put("공공청사(임시)", "공공시설");
			put("병원", "공공시설");
			put("문화시설", "공공시설");
			put("공공청사", "공공시설");
			put("공공기관", "공공시설");
			
			// 상가
			put("상가(전통시장)", "상가");
			put("시장(상가)", "상가");
			put("전통시장", "상가");
			put("재래시장", "상가");
			put("상가", "상가");
			put("상가(재래시장)", "상가");
			put("민간", "상가");
			put("음식점", "상가");
			put("지하상가", "상가");
			put("상가(시장)", "상가");
			
			// 기타
			put("빌딩", "기타");
			put("장례식장", "기타");
			put("예식장", "기타");
			put("택시회사", "기타");
			put("기타", "기타");
			put("정교시설", "기타");
			put("종교시설", "기타");
			put("주차장", "기타");
			put("교통시설", "기타");
			put("주유소및충전소", "기타");
			put("교회", "기타");
			put("아크릴전문기업", "기타");
		}
	};
	
	private float distance;
	private String id;
	private String useable;
	private String locationName;
	private String usingTime;
	private String guName;
	private String roadAddress;
	private String numAddress;
	private String locX;
	private String locY;
	private String phone;

	private String detailName1;
	private String detailContent1;
	private String detailName2;
	private String detailContent2;
	private String detailName3;
	private String detailContent3;
	private String detailName4;
	private String detailContent4;
	private String detailName5;
	private String detailContent5;
	
	private String kind; // 시설구분 화장실 | 공원 | 지하철 | 공공시설 | 상가 | 기타 중 하나
	private String color; // 시설구분에 따른 마커 색깔

	public Toilet() {
	}

	public Toilet(String id, String useable, String locationName, String usingTime, String guName,
			String roadAddress, String numAddress, String locX, String locY, String phone, String detailName1,
			String datailContent1, String detailName2, String datailContent2, String detailName3, String datailContent3,
			String detailName4, String datailContent4, String detailName5, String datailContent5) throws MainException {
		this();
		this.distance = -1;
		this.id = id;
		this.useable = useable;
		this.locationName = locationName;
		this.usingTime = usingTime;
		this.guName = guName;
		this.roadAddress = roadAddress;
		this.numAddress = numAddress;
		this.locX = locX;
		this.locY = locY;
		this.phone = phone;
		this.detailName1 = detailName1;
		this.detailContent1 = datailContent1;
		this.detailName2 = detailName2;
		this.detailContent2 = datailContent2;
		this.detailName3 = detailName3;
		this.detailContent3 = datailContent3;
		this.detailName4 = detailName4;
		this.detailContent4 = datailContent4;
		this.detailName5 = detailName5;
		this.detailContent5 = datailContent5;
		
		this.kind = findKind();
		this.color = findColor();
//		System.out.println(kind + " " + color);
	}

	private String findColor() {
		return COLOR_MAP.get(kind);
	}

	private String findKind() throws MainException {
				
		if (detailName3 == null && detailName1 == null && detailName2 == null)
			throw new MainException("Toilet find Kind 에러: " + locationName);
		
		if (detailName3.equals("소재지용도")) {
			return LEGEND_MAP.get(detailContent3);
		} else if (detailName1.equals("시설구분")) {
			return LEGEND_MAP.get(detailContent1);
		} else if (detailName2.equals("시설구분")){
			return LEGEND_MAP.get(detailContent2);
		}
		
		System.out.println(locationName + " : 범례 찾지 못함!");
		return "미확인";
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(String usingTime) {
		this.usingTime = usingTime;
	}

	public String getGuName() {
		return guName;
	}

	public void setGuName(String guName) {
		this.guName = guName;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getNumAddress() {
		return numAddress;
	}

	public void setNumAddress(String numAddress) {
		this.numAddress = numAddress;
	}

	public String getLocX() {
		return locX;
	}

	public void setLocX(String locX) {
		this.locX = locX;
	}

	public String getLocY() {
		return locY;
	}

	public void setLocY(String locY) {
		this.locY = locY;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDetailName1() {
		return detailName1;
	}

	public void setDetailName1(String detailName1) {
		this.detailName1 = detailName1;
	}

	public String getDatailContent1() {
		return detailContent1;
	}

	public void setDatailContent1(String datailContent1) {
		this.detailContent1 = datailContent1;
	}

	public String getDetailName2() {
		return detailName2;
	}

	public void setDetailName2(String detailName2) {
		this.detailName2 = detailName2;
	}

	public String getDatailContent2() {
		return detailContent2;
	}

	public void setDatailContent2(String datailContent2) {
		this.detailContent2 = datailContent2;
	}

	public String getDetailName3() {
		return detailName3;
	}

	public void setDetailName3(String detailName3) {
		this.detailName3 = detailName3;
	}

	public String getDatailContent3() {
		return detailContent3;
	}

	public void setDatailContent3(String datailContent3) {
		this.detailContent3 = datailContent3;
	}

	public String getDetailName4() {
		return detailName4;
	}

	public void setDetailName4(String detailName4) {
		this.detailName4 = detailName4;
	}

	public String getDatailContent4() {
		return detailContent4;
	}

	public void setDatailContent4(String datailContent4) {
		this.detailContent4 = datailContent4;
	}

	public String getDetailName5() {
		return detailName5;
	}

	public void setDetailName5(String detailName5) {
		this.detailName5 = detailName5;
	}

	public String getDatailContent5() {
		return detailContent5;
	}

	public void setDatailContent5(String datailContent5) {
		this.detailContent5 = datailContent5;
	}

	public String getLocation() {
		String str = String.format("%.6f,%.6f", Double.parseDouble(locY), Double.parseDouble(locX));
		
		return str;
		
//		try {
//			return URLEncoder.encode(str, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(distance).append("\t").append(id).append("\t")
				.append(useable).append("\t").append(locationName).append("\t")
				.append(usingTime).append("\t").append(guName).append("\t").append(roadAddress)
				.append("\t").append(numAddress).append("\t").append(locX).append("\t")
				.append(locY).append("\t").append(phone).append("\t").append(detailName1)
				.append("\t").append(detailContent1).append("\t").append(detailName2)
				.append("\t").append(detailContent2).append("\t").append(detailName3)
				.append("\t").append(detailContent3).append("\t").append(detailName4)
				.append("\t").append(detailContent4).append("\t").append(detailName5)
				.append("\t").append(detailContent5);
		return builder.toString();
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public static Map<String, String> getColorMap() {
		return COLOR_MAP;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static Map<String, String> getLegendMap() {
		return LEGEND_MAP;
	}

	

}
