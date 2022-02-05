package com.omer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player extends GameObject {

	private float x;
	private float y;
	public static int direction;
	public static int FireLevel = 0;
	public static boolean going[]= new boolean[4];
	private int width = 48;
	private int height = 48;
	private float attackVelX;
	private float attackVelY;
	private int numberX;
	private int numberY;
	private int count = 0;
	private boolean attacking = false;
	private float attackx;
	private float attacky;
	private ID id;
	private Handler handler;
	Image attack;
	Image img;
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
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
		g.drawImage(img, (int)x, (int)y, width, height, null);
		if(attacking)
			g.drawImage(attack, (int)attackx, (int)attacky, 49, 49, null);
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
			if(temp.getID() == ID.Wall || temp.getID() == ID.OuterWall) {
				if(getBounds().intersects(temp.getBounds())){
					numberX = temp.getNumberX();
					numberY = temp.getNumberY();

					if(x > temp.getX()) {
						Game.canGo[3] = false;
						Game.Gamex -= 1;
					}

					if(x < temp.getX()) {
						Game.canGo[1] = false;
						Game.Gamex += 1;
					}

					if(y >= temp.getY()) {
						Game.canGo[0] = false;
						Game.Gamey -= 1;
					}
					if(y  <= temp.getY()) {
						Game.canGo[2] = false;
						Game.Gamey += 1;
					}	

				}	if(!getBounds().intersects(temp.getBounds()) && temp.getNumberX() == numberX && temp.getNumberY() == numberY) {
					Game.canGo[0] = true;
					Game.canGo[1] = true;
					Game.canGo[2] = true;
					Game.canGo[3] = true;
				}
			}
			if(temp.getID() == ID.Goblin) {
				if(getBounds().intersects(temp.getBounds())) {
					if(count == 20) {
						Hud.health -= 5;
						count = 0;
					}
					count++;
				}
			}
		}
	}

	public void attack() {
		if(Hud.selected==1 && Hud.mana >= 5) {
			handler.addObject(new Attacks(Game.Gamex+x,Game.Gamey+y,ID.Attack,handler));
			Hud.mana -= 5;
		}
		if(Hud.selected==2 && Hud.health <100 && Hud.potionCount > 0) {
			Hud.potionCount --;
			Hud.health += 20;
		}
		if(Hud.selected==3 && Hud.mana < 400 && Hud.manaCount > 0) {
			Hud.manaCount --;
			Hud.mana += 50;
		}
		if(Hud.selected==4 && Hud.mana >= 10 && FireLevel > 0) {
			handler.addObject(new FireBall(Game.Gamex+x,Game.Gamey+y,ID.FireBall,handler));
			Hud.mana -= 10;
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public ID getID() {
		return id;
	}

	public float getX() {
		return  x;
	}

	public float getY() {
		return y;
	}
	
}