package com.kay.model.vo;

public class Toilet {
	private float distance;
	private String id;
	private String userable;
	private String locationName;
	private String usingTime;
	private String guName;
	private String roadAddress;
	private String numAddress;
	private String locX;
	private String locY;
	private String phone;

	private String detailName1;
	private String datailContent1;
	private String detailName2;
	private String datailContent2;
	private String detailName3;
	private String datailContent3;
	private String detailName4;
	private String datailContent4;
	private String detailName5;
	private String datailContent5;

	public Toilet() {
	}

	public Toilet(String id, String userable, String locationName, String usingTime, String guName,
			String roadAddress, String numAddress, String locX, String locY, String phone, String detailName1,
			String datailContent1, String detailName2, String datailContent2, String detailName3, String datailContent3,
			String detailName4, String datailContent4, String detailName5, String datailContent5) {
		super();
		this.distance = -1;
		this.id = id;
		this.userable = userable;
		this.locationName = locationName;
		this.usingTime = usingTime;
		this.guName = guName;
		this.roadAddress = roadAddress;
		this.numAddress = numAddress;
		this.locX = locX;
		this.locY = locY;
		this.phone = phone;
		this.detailName1 = detailName1;
		this.datailContent1 = datailContent1;
		this.detailName2 = detailName2;
		this.datailContent2 = datailContent2;
		this.detailName3 = detailName3;
		this.datailContent3 = datailContent3;
		this.detailName4 = detailName4;
		this.datailContent4 = datailContent4;
		this.detailName5 = detailName5;
		this.datailContent5 = datailContent5;
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

	public String getUserable() {
		return userable;
	}

	public void setUserable(String userable) {
		this.userable = userable;
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
		return datailContent1;
	}

	public void setDatailContent1(String datailContent1) {
		this.datailContent1 = datailContent1;
	}

	public String getDetailName2() {
		return detailName2;
	}

	public void setDetailName2(String detailName2) {
		this.detailName2 = detailName2;
	}

	public String getDatailContent2() {
		return datailContent2;
	}

	public void setDatailContent2(String datailContent2) {
		this.datailContent2 = datailContent2;
	}

	public String getDetailName3() {
		return detailName3;
	}

	public void setDetailName3(String detailName3) {
		this.detailName3 = detailName3;
	}

	public String getDatailContent3() {
		return datailContent3;
	}

	public void setDatailContent3(String datailContent3) {
		this.datailContent3 = datailContent3;
	}

	public String getDetailName4() {
		return detailName4;
	}

	public void setDetailName4(String detailName4) {
		this.detailName4 = detailName4;
	}

	public String getDatailContent4() {
		return datailContent4;
	}

	public void setDatailContent4(String datailContent4) {
		this.datailContent4 = datailContent4;
	}

	public String getDetailName5() {
		return detailName5;
	}

	public void setDetailName5(String detailName5) {
		this.detailName5 = detailName5;
	}

	public String getDatailContent5() {
		return datailContent5;
	}

	public void setDatailContent5(String datailContent5) {
		this.datailContent5 = datailContent5;
	}

	@Override
	public String toString() {
		return "distance : " + distance + ", id : " + id + ", userable : " + userable + ", locationName : "
				+ locationName + ", usingTime : " + usingTime + ", guName : " + guName + ", roadAddress : "
				+ roadAddress + ", numAddress : " + numAddress + ", locX : " + locX + ", locY : " + locY + ", phone : "
				+ phone + ", detailName1 : " + detailName1 + ", datailContent1 : " + datailContent1 + ", detailName2 : "
				+ detailName2 + ", datailContent2 : " + datailContent2 + ", detailName3 : " + detailName3
				+ ", datailContent3 : " + datailContent3 + ", detailName4 : " + detailName4 + ", datailContent4 : "
				+ datailContent4 + ", detailName5 : " + detailName5 + ", datailContent5 : " + datailContent5;
	}

}
