package com.omer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Wall extends GameObject {
	
	private float x;
	private float y;
	private ID id;
	private int numberX;
	private int numberY;
	private Image img;
	private float oX;
	private float oY;
	private int width = 50;
	private int height = 50;
	private Handler handler;
	
	public Wall(int x, int y, ID id, Map map, int numberX, int numberY, Handler handler) {
		super(x, y, id);
		this.oX = x;
		this.oY = y;
		this.id = id;
		this.numberX = numberX;
		this.numberY = numberY;
		this.x = oX;
		this.y = oY;
		img = map.getWall();
		this.handler = handler;
	}

	
	public void render(Graphics g) {
		g.drawImage(img, (int)x, (int)y, width, height, null);
	}

	
	public void tick() {
		x = Game.Gamex + oX;
		y = Game.Gamey + oY;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width,height);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getNumberX() {
		return numberX;
	}
	
	public int getNumberY() {
		return numberY;
	}
	
	public ID getID() {
		return id;
	}
	
}
