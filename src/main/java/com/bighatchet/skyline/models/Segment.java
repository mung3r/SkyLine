package com.bighatchet.skyline.models;

public class Segment {

	private float lx;
	private float h;

	public Segment(float lx, float h) {
		this.lx = lx;
		this.h = h;
	}

	public float getX() {
		return lx;
	}

	public void setLeftX(float lx) {
		this.lx = lx;
	}

	public float getHeight() {
		return h;
	}

	public void setHeight(float height) {
		this.h = height;
	}
}
