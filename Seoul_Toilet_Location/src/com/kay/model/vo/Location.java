package com.kay.model.vo;

public class Location {
	String address;
	String mainNum;
	String subNum;
	String buildingName;
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

	@Override
	public String toString() {
		return address + "\t" + mainNum + "\t" + subNum + "\t" + buildingName + "\t" + loc_x + "\t" + loc_y;
	}

}
