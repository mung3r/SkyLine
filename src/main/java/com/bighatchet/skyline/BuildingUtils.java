package com.bighatchet.skyline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.bighatchet.skyline.models.Building;

public class BuildingUtils {

	private static final String FILENAME = "buildings.csv";
	private static final float MAX_X = 100.0f;
	private static final float MAX_HEIGHT = 25.0f;
	private static final float MAX_WIDTH = 25.0f;

	public static void main(String[] args) {
		try {
			int numBuildings = Integer.parseInt(args[0]);

			FileWriter buildingFileWriter = new FileWriter(new File(FILENAME));
			Random random = new Random();

			while (numBuildings > 0) {

				float right = random.nextFloat() * MAX_X;
				float height = random.nextFloat() * MAX_HEIGHT;
				float left = right + random.nextFloat() * MAX_WIDTH;

				StringBuilder sb = new StringBuilder();
				sb.append(left).append(',').append(height).append(',').append(right).append('\n');

				buildingFileWriter.write(sb.toString());
				numBuildings--;
			}

			buildingFileWriter.close();
			System.out.println("Generated " + FILENAME);

		} catch (Exception e) {
			System.out.println("Error creating file.");
		}
	}

	public static ArrayList<Building> loadBuildings(String filename) {
		TreeSet<Building> buildingSet = new TreeSet<Building>();

		try {
			BufferedReader buildingFileReader = new BufferedReader(new FileReader(new File(filename)));
			String line;

			while ((line = buildingFileReader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");

				Building building = new Building();
				building.setLeftX(Float.parseFloat(st.nextToken()));
				building.setHeight(Float.parseFloat(st.nextToken()));
				building.setRightX(Float.parseFloat(st.nextToken()));
				buildingSet.add(building);
			}
		} catch (Exception e) {
			System.out.println("Error reading file.");
		}

		return new ArrayList<Building>(buildingSet);
	}
}
