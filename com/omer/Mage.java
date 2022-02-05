package com.omer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Mage extends Player{

	private int x;
	private int y;
	public static int direction;
	public static boolean going[]= new boolean[4];
	private int width = 49;
	private int height = 49;
	private int attackVelX;
	private int attackVelY;
	private int numberX;
	private int numberY;
	private boolean attacking = false;
	private int attackx;
	private int attacky;
	private ID id;
	private Handler handler;
	Image attack;
	Image img;
	public Mage(int x, int y, ID id, Handler handler) {
		super(x, y, id,handler);
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		direction = 1;
		setImages(direction);
	}

	public void tick() {
		collision();
		setImages(direction);
		if(attacking) {
			attackx += attackVelX;
			attacky += attackVelY;
			if(attackx > x + 70 || attacky > y + 70 || attackx < x - 70 || attacky < y - 70)
				attacking = !attacking;
		}
	}
	public void render(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
		if(attacking)
			g.drawImage(attack, attackx, attacky, 50, 50, null);
	}

	public void setImages(int direction) {
		switch(direction) {
		case 0:
			img = new ImageIcon("src/assets/WizardNeutralBackLeft.png").getImage();
			break;
		case 1:
			img = new ImageIcon("src/assets/WizardNeutralRight.png").getImage();
			break;
		case 2:
			img = new ImageIcon("src/assets/WizardNeutralLeft.png").getImage();
			break;
		case 3:
			img = new ImageIcon("src/assets/WizardNeutralLeft.png").getImage();
			break;
		}
	}

	public void collision() {
		for(int i=0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			if(temp.getID() == ID.Wall) {
				if(getBounds().intersects(temp.getBounds())){
					numberX = temp.getNumberX();
					numberY = temp.getNumberY();

					if(x > temp.getX()) {
						Game.canGo[3] = false;
						Game.Gamex -=1;
					}

					if(x < temp.getX()) {
						Game.canGo[1] = false;
						Game.Gamex += 1;
					}

					if(y >= temp.getY()) {
						Game.canGo[0] = false;
						Game.Gamey -= 1;
					}
					if(y  <= temp.getY() ) {
						Game.canGo[2] = false;
						Game.Gamey +=1;
					}	





				}	if(!getBounds().intersects(temp.getBounds()) && temp.getNumberX() == numberX && temp.getNumberY() == numberY) {
					Game.canGo[0] = true;
					Game.canGo[1] = true;
					Game.canGo[2] = true;
					Game.canGo[3] = true;
				}
			}
		}
	}

	public void attack() {
		/*System.out.println("TEST");
		switch(direction) {
		case 0: attack = new ImageIcon("/Users/omersen/eclipse-workspace/Escape The DungeonV2/src/assets/IceBallUp.png").getImage();
		attackVelY = -5;
		attackVelX = 0;
		break;
		case 1: attack = new ImageIcon("/Users/omersen/eclipse-workspace/Escape The DungeonV2/src/assets/IceBallRight.png").getImage();
		attackVelY = 0;
		attackVelX = 5;
		break;
		case 2: attack = new ImageIcon("/Users/omersen/eclipse-workspace/Escape The DungeonV2/src/assets/IceBallDown.png").getImage();
		attackVelY = 5;
		attackVelX = 0;
		break;
		case 3:attack = new ImageIcon("/Users/omersen/eclipse-workspace/Escape The DungeonV2/src/assets/IceBallLeft.png").getImage();
		attackVelY = 0;
		attackVelX = -5;
		break;
		}
		attackx = x;
		attacky = y;
		attacking = true;*/
		handler.addObject(new Attacks(200,300,ID.Attack,handler));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void setDirection(int dir) {
		this.direction = dir;
	}

}
