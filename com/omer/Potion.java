package com.omer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Potion extends GameObject{

	private float x;
	private float y;
	private float oX;
	private float oY;
	private int width = 20;
	private int height = 20;
	private ID id;
	private int kind;
	Image img;
	Random r;
	
	public Potion(int x, int y, ID id) {
		super(x, y, id);
		this.oX = x;
		this.oY = y;
		this.id = id;
		r = new Random();
		if(r.nextInt(2)==0) {
			kind = 0;
			img = new ImageIcon("src/assets/redPotion.png").getImage();
		}else {
			kind = 1;
			img = new ImageIcon("src/assets/smallMana.png").getImage();
		}
	}

	public void render(Graphics g) {
		g.drawImage(img, (int)x, (int)y, width, height, null);
	}

	public void tick() {
		x = Game.Gamex + oX;
		y = Game.Gamey + oY;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	
	
	
}
