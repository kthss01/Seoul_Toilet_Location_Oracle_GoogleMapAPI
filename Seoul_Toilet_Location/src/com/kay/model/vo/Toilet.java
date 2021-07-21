package com.kay.model.vo;

public class Toilet {
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

	public Toilet() {
	}

	public Toilet(String id, String useable, String locationName, String usingTime, String guName,
			String roadAddress, String numAddress, String locX, String locY, String phone, String detailName1,
			String datailContent1, String detailName2, String datailContent2, String detailName3, String datailContent3,
			String detailName4, String datailContent4, String detailName5, String datailContent5) {
		super();
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

	

}
