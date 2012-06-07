package com.bighatchet.skyline;

import java.util.ArrayList;
import java.util.List;

import com.bighatchet.skyline.models.Building;
import com.bighatchet.skyline.models.Segment;

public class SkyLineApp {

	private List<Building> buildings;

	public SkyLineApp() {
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	private List<Segment> buildSkyLine(int leftIndex, int rightIndex) {
		List<Segment> skyLine = new ArrayList<Segment>();

		if (leftIndex == rightIndex) {
			Building b = buildings.get(leftIndex);
			skyLine.add(new Segment(b.getLeftX(), b.getHeight()));
			skyLine.add(new Segment(b.getRightX(), 0));
		} else {
			int middleIndex = (leftIndex + rightIndex) / 2;
			List<Segment> skyLine1 = buildSkyLine(leftIndex, middleIndex);
			List<Segment> skyLine2 = buildSkyLine(middleIndex + 1, rightIndex);
			skyLine.addAll(mergeSkyLines(skyLine1, skyLine2));
		}

		return skyLine;
	}

	private List<Segment> mergeSkyLines(List<Segment> skyLine1, List<Segment> skyLine2) {
		List<Segment> skyLine = new ArrayList<Segment>();
		float curH1 = 0.0f;
		float curH2 = 0.0f;

		while (!skyLine1.isEmpty() && !skyLine2.isEmpty()) {
			if (skyLine1.get(0).getX() < skyLine2.get(0).getX()) {
				Segment segment = skyLine1.remove(0);
				curH1 = segment.getHeight();
				float maxH = Math.max(curH1, curH2);
				skyLine.add(new Segment(segment.getX(), maxH));
			} else {
				Segment segment = skyLine2.remove(0);
				curH2 = segment.getHeight();
				float maxH = Math.max(curH1, curH2);
				skyLine.add(new Segment(segment.getX(), maxH));
			}
		}

		skyLine.addAll(skyLine1);
		skyLine.addAll(skyLine2);

		return skyLine;
	}

	private List<Segment> getSkyLine() {
		ArrayList<Segment> skyLine = new ArrayList<Segment>();

		if (buildings != null) {
			skyLine.addAll(buildSkyLine(0, buildings.size() - 1));
		}

		return skyLine;
	}

	public static void main(String[] args) {

		if (args.length == 1) {
			SkyLineApp skyLineApp = new SkyLineApp();
			skyLineApp.setBuildings(BuildingUtils.loadBuildings(args[0]));
			List<Segment> skyLine = skyLineApp.getSkyLine();

			float curH = 0.0f;
			for (Segment segment : skyLine) {
				if (segment.getHeight() != curH) {
					System.out.println(String.format("%.2f, %.2f", segment.getX(), segment.getHeight()));
				}
				curH = segment.getHeight();
			}
		} else {
			System.out.println("Single argument must be a filename.");
		}
	}
}
