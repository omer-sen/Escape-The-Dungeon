package com.omer;

import java.awt.Image;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Map {

	private Image wall;
	private Scanner m;
	public static int level = 1;
	private int max = 2;
	String line;
	private int size;
	private int height;
	public static int bigGoblinChance = 1;
	private String Map[] = new String[9999];
	Random r = new Random();

	public Map() {

		wall = new ImageIcon("src/assets/block.gif").getImage();
		//	openFile();
		//	readFile();
		//closeFile();

		//random();
		setMap();
	}

	public Image getWall() {
		return wall;
	}
	public String getMap(int x, int y) {
		String index = Map[y].substring(x,x+1);

		return index;
	}

	public void random() {
		Game.Gamex = 180;
		Game.Gamey = 80;
		size = r.nextInt(10)+40;
		line = "";
		height = r.nextInt(10)+40;
		for(int i=0; i < height; i++) {
			line = "w";
			for(int x = 0; x < size-2; x++) {
				int temp = r.nextInt(100);
				switch(temp) {
				case 0: line = line + "w";
				break;
				case 1: line = line + "w";
				break;
				case 2: line = line + "w";
				break;
				case 3: line = line + "w";
				break;
				case 4: line = line + "w";
				break;
				case 5: line = line + "w";
				break;
				case 6: line = line + "w";
				break;
				case 7: line = line + "w";
				break;
				case 8: line = line + "w";
				break;
				case 9: line = line + "w";
				break;
				case 10: line = line + "w";
				break;
				case 11: line = line + "g";
				break;
				default: line = line + "e";
				break;
				}
				temp = r.nextInt(1000);
				if(temp == 0) {
					line = line + "b";
				}
			} 
			Map[i] = line + "o";

		}
		Map[0]="ooooo";
		Map[height-1] ="";
		Map[1]="oeeeo";
		Map[2]="oeeeo";
		Map[3]="oeeeo";
		for(int i=0; i< size-4;i++) {
			Map[0] += "e";
			Map[1] += "e";
			Map[2] += "e";
			Map[3] += "o";
		}
		for(int i=0; i< size;i++) {
			Map[height-1] +="o";

		}

	}
	public void openFile() {
		if(level == 1) {
			try {
				m = new Scanner(new File("src/assets/map.txt"));
			}catch(Exception e) {}
		}else if(level == 2) {
			try {
				m = new Scanner(new File("src/assets/arena.txt"));
			}catch(Exception e) {}
		}
	}

	public void readFile() {
		while(m.hasNext()) {
			for(int i = 0; i < 30; i++) {
				Map[i] = m.next();
			}
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setMap() {
		if(level <= max) {
			openFile();
			readFile();
			closeFile();
		}else 
			random();
	}
	public void closeFile() {

	}



	public int getHeight() {
		if(level <=max)
			return 30;
		else 
			return height;
	}

	public int getSize() {
		if(level <= max)
			return 30;
		else
			return size;
	}
}
