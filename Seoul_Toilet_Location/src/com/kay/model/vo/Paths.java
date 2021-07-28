package com.kay.model.vo;

import java.util.ArrayList;

public class Paths implements Cloneable {

	private ArrayList<Path> list = new ArrayList<Path>();

	public Paths() {
	}

	public ArrayList<Path> getPaths() {
		return list;
	}

	public void addPath(Path path) {
		list.add(path);
	}

	public Path getPath(int i) {
		return list.get(i);
	}

	@Override
	protected Paths clone() throws CloneNotSupportedException {
		Paths paths = (Paths) super.clone();

		paths.list = (ArrayList<Path>) list.clone();

		return paths;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			Path path = list.get(i);
			sb.append(path.toString());

			if (i != list.size() - 1) {
				sb.append("&");
			}
		}

		return sb.toString();
	}

}
