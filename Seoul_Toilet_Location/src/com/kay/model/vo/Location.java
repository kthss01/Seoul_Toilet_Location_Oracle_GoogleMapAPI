package com.kay.model.vo;

public class Location {
	String siGunGuName;
	String address;
	String mainNum;
	String subNum;
	String buildingName;
	String hangJungDongName;
	String loc_x;
	String loc_y;

	public Location() {
	}

	public Location(String address, String mainNum, String subNum, String buildingName, String loc_x, String loc_y) {
		super();
		this.address = address;
		this.mainNum = mainNum;
		this.subNum = subNum;
		this.buildingName = buildingName;
		this.loc_x = loc_x;
		this.loc_y = loc_y;
	}

	public Location(String siGunGuName, String address, String mainNum, String subNum, String buildingName,
			String hangJungDongName, String loc_x, String loc_y) {
		super();
		this.siGunGuName = siGunGuName;
		this.address = address;
		this.mainNum = mainNum;
		this.subNum = subNum;
		this.buildingName = buildingName;
		this.hangJungDongName = hangJungDongName;
		this.loc_x = loc_x;
		this.loc_y = loc_y;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMainNum() {
		return mainNum;
	}

	public void setMainNum(String mainNum) {
		this.mainNum = mainNum;
	}

	public String getSubNum() {
		return subNum;
	}

	public void setSubNum(String subNum) {
		this.subNum = subNum;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getLoc_x() {
		return loc_x;
	}

	public void setLoc_x(String loc_x) {
		this.loc_x = loc_x;
	}

	public String getLoc_y() {
		return loc_y;
	}

	public void setLoc_y(String loc_y) {
		this.loc_y = loc_y;
	}

	public String getSiGunGuName() {
		return siGunGuName;
	}

	public void setSiGunGuName(String siGunGuName) {
		this.siGunGuName = siGunGuName;
	}

	public String getHangJungDongName() {
		return hangJungDongName;
	}

	public void setHangJungDongName(String hangJungDongName) {
		this.hangJungDongName = hangJungDongName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(siGunGuName).append("\t").append(address)
				.append("\t").append(mainNum).append("\t").append(subNum).append("\t")
				.append(buildingName).append("\t").append(hangJungDongName).append("\t")
				.append(loc_x).append("\t").append(loc_y);
		return builder.toString();
	}

	

}
