package com.bighatchet.skyline.models;

public class Building implements Comparable<Building> {

	private Float lx;
	private Float h;
	private Float rx;

	public Building() {

	}

	public Float getLeftX() {
		return lx;
	}

	public void setLeftX(float width) {
		this.lx = width;
	}

	public Float getHeight() {
		return h;
	}

	public void setHeight(float height) {
		this.h = height;
	}

	public Float getRightX() {
		return rx;
	}

	public void setRightX(float location) {
		this.rx = location;
	}

	@Override
	public int compareTo(Building b) {
		if (this.lx == b.lx) {
			return 0;
		} else if (this.lx > b.lx) {
			return 1;
		} else {
			return -1;
		}
	}
}
