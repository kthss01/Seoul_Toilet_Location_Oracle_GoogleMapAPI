package com.kay.model.vo;

import java.util.ArrayList;

public class Path implements Cloneable {
	private String weight = ""; // default 5 pixels
	private String color = ""; // rgba 투명도 넣어줘야함 투명도 따로 처리하도록 하기
	private String opacity = "80"; // 투명도 따로 처리 50% 80 00 ~ ff 까지
	private String fillColor = ""; // 다각형이 되면 채울 색
	
	private ArrayList<String> locations = new ArrayList<String>();
	
	public Path() {
	}

	public Path(String color) {
		super();
		this.color = color;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOpacity() {
		return opacity;
	}

	public void setOpacity(String opacity) {
		this.opacity = opacity;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	
	public String getLocations() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < locations.size(); i++) {
			sb.append(locations.get(i));
			if (i != locations.size() - 1)
				sb.append("%7C");
		}
		return sb.toString();
	}

	public void setLocation(String location) {
		locations.add(location);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("path=");

		if (!color.equals(""))
			sb.append("color:").append(color).append(opacity).append("%7C");

		if (!weight.equals(""))
			sb.append("weight:").append(weight).append("%7C");
		
		// location
		sb.append(getLocations());
		
		return sb.toString();
	}
	
	@Override
	protected Path clone() throws CloneNotSupportedException {
		Path path = (Path) super.clone();
		
		path.locations = (ArrayList<String>) locations.clone();
		
		return path;
	}
}
