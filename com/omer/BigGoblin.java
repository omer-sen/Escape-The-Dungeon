package com.omer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class BigGoblin extends GameObject{

	private float x;
	private float y;
	private float oX;
	private float oY;
	private int width = 100;
	private int height = 100;
	private int direction = 1;
	private int health = 300;
	private float tempX = 0;
	private float tempY = 0;
	private float velX = 3;
	private float velY = 0;
	private ID id;
	private Handler handler;
	private int dropItem;
	Image img;
	GameObject player;
	Random r;

	public BigGoblin(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.oX = x;
		this.oY = y;
		this.id = id;
		this.handler = handler;
		for(int i = 0; i < handler.list.size(); i++) {
			if(handler.list.get(i).getID() == ID.Player) player = handler.list.get(i);
		}
		setImages();
		r = new Random();
		dropItem = r.nextInt(1)+1;
	}

	public void render(Graphics g) {

		g.drawImage(img, (int)x, (int)y, width, height, null);
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y-30, 100, 4);
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y-30, health/8, 4);
	}
	public void tick() {
		if(velY == 0)
			y = Game.Gamey + oY;
		if(velX == 0)
			x = Game.Gamex + oX;
		tempX += velX;
		tempY += velY;


		if(velX != 0) 
			x = Game.Gamex + oX +tempX;
		if(velY != 0)
			y = Game.Gamey + oY +tempY;
		if(velX > 0)
			direction = 1;
		if(velX < 0)
			direction = 3;
		if(velY > 0)
			direction = 2;
		if(velY < 0)
			direction = 0;
		setImages();
		x += velX;
		y += velY;

		float diffX = x - player.getX()-1;
		float diffY = y - player.getY()-1;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));

		velX = (float) ((-1.0/distance) * diffX)*2;
		velY = (float) ((-1.0/distance) * diffY)*2;
		setImages();
		collision();
		if(health <=0) {
			Hud.coins += 200;
			handler.removeObject(this);
		}
	}

	public void setImages() {
		switch(direction) {
		case 0: img = new ImageIcon("src/assets/goblin.PNG").getImage();
		break;
		case 1: img = new ImageIcon("src/assets/goblinRight.PNG").getImage();
		break;
		case 2: img = new ImageIcon("src/assets/goblin.PNG").getImage();
		break;
		case 3: img = new ImageIcon("src/assets/goblin.PNG").getImage();
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public void collision() {
		for(int i=0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			if(temp.getID() == ID.Attack) {
				if(getBounds().intersects(temp.getBounds())) {
					health -= Attacks.damage;
					handler.removeObject(temp);
				}
			}
			if(temp.getID() == ID.FireBall) {
				if(getBounds().intersects(temp.getBounds())) {
					health -= FireBall.damage;
					handler.removeObject(temp);
				}
			}
			if(temp.getID() == ID.Wall) {
				if(getBounds().intersects(temp.getBounds())) {
					handler.removeObject(temp);
				}
			}
		}
	}

	public ID getID() {
		return id;
	}
}
