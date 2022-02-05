package com.omer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Attacks extends GameObject{

	private float x;
	private float y;
	private ID id;
	private float oX;
	private float oY;
	Image img;
	private float tempX = 0;
	private float tempY = 0;
	private float velX;
	private float velY;
	private int width = 48;
	private int height = 48;
	private float gameX;
	private float gameY;
	private int count = 0;
	private boolean attacking;
	public static int damage = 10;
	Handler handler;
	public Attacks(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.oX = x;
		this.oY = y;
		this.id = id;
		this.gameX = Game.Gamex;
		this.gameY = Game.Gamey;
		this.handler = handler;
		attack();
		this.y = -gameY + oY + tempY;
		this.x = -gameX + oX + tempX;
	}

	public void render(Graphics g) {
		g.drawImage(img, (int)x, (int)y, width, height, null);
	}

	public void tick() {

		tempX += velX;
		tempY += velY;
		if(velX != 0) {
			x = -gameX + oX + tempX;
		}
		if(velY != 0)
			y = -gameY + oY + tempY;
		if(velY == 0)
			y += Game.velY;
		if(velX == 0)
			x += Game.velX;
		collision();
		/*if(count <1) {
			if(Game.velX != 0 && Player.direction == 1 && Player.direction != 3 ) {
				velX = 10;
				count++;
			}
			if(Game.velX != 0 && Player.direction == 3 && Player.direction != 1) {
				velX = -10;
				count++;
			}
			if(Game.velY < 0 && Player.direction == 2 && velY > 0) {
				velY = 10;
				count++;
			}
			if(Game.velY < 0 && Player.direction == 0 && velY >0) {
				velY = -10;
				count++;
			}

		}*/

	}

	public void collision() {
		for(int i=0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			if(temp.getID() == ID.Wall || temp.getID() == ID.OuterWall) {
				if(getBounds().intersects(temp.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public void attack() {
		switch(Player.direction) {
		case 0: img = new ImageIcon("src/assets/IceBallUp.png").getImage();
		velY = -5;
		velX = 0;
		break;
		case 1: img = new ImageIcon("src/assets/IceBallRight.png").getImage();
		velY = 0;
		velX = 5;
		break;
		case 2: img = new ImageIcon("src/assets/IceBallDown.png").getImage();
		velY = 5;
		velX = 0;
		break;
		case 3: img = new ImageIcon("src/assets/IceBallLeft.png").getImage();
		velY = 0;
		velX = -5;
		break;
		}


		attacking = true;
	}

	public ID getID() {
		return id;
	}

}
