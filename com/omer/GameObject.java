package com.omer;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	private float x;
	private float y;
	private ID id;
	private int numberX;
	private int numberY;
	
	private int kind;
	public GameObject(float x, float y, ID id) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract Rectangle getBounds();
	
	public ID getID() {
		return id;
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
	
	public int getKind() {
		return kind;
	}
}
