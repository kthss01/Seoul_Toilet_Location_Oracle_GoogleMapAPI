package practice.advance.mvc.model.vo;

import java.util.ArrayList;

public class Marker {
	private String size = GoogleMap.getMarkerSize()[0]; // tiny, mid, small 중 하나 안쓰면 default(normal) "" 시 normal로 처리할 예정
	private String color = ""; // black, brown, green, purple, yellow, blue, gray, orange, red, white 중 하나 또는
								// 0xFFFFCC 같은 32-bit hex color
	private String label = ""; // A-Z, 0-9 대문자 하나 인거 같음 한글 되는진 모르겠음 tiny, small 시엔 안보임
	private ArrayList<String> locations = new ArrayList<String>(); // 위치 주소나 위도,경도로 표현

	public Marker() {
	}

	public Marker(String size, String color, String label, String location) {
		super();
		this.size = size;
		this.color = color;
		this.label = label;
		locations.add(location);
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLocation() {
		return locations.get(0);
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
		sb.append("markers=");

		String temp = size;

		if (size.equals(GoogleMap.getMarkerSize()[0]))
			temp = "";

		if (!temp.equals(""))
			sb.append("size:").append(temp).append("%7C");
		if (!color.equals(""))
			sb.append("color:").append(color).append("%7C");
		if (!label.equals(""))
			sb.append("label:").append(label).append("%7C");

		// location
		sb.append(getLocations());

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		Marker mark = (Marker) obj;

		return mark.getSize().equals(size) && mark.getColor().equals(color) && mark.getLabel().equals(label);
	}

}