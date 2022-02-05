package com.omer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Hud {

	public static int health = 100;
	public static int mana = 400;
	public static int selected =1;
	public static int potionCount = 5;
	public static int manaCount = 5;
	public static int coins = 999999999;
	public static boolean fireball = false;
	private int count = 0;
	Image images[] = new Image[7];
	Image coin;

	public Hud() {
		images[0] = new ImageIcon("src/assets/IceballLeft.png").getImage();	
		images[1] = new ImageIcon("src/assets/redPotion.png").getImage();
		images[2] = new ImageIcon("src/assets/smallMana.png").getImage();
		images[3] = new ImageIcon("src/assets/FireballUp.png").getImage();
		coin =  new ImageIcon("src/assets/coins.png").getImage();
		
	}
	
	public void tick() {
		this.getClass();
		health = Game.clamp(health, 0, 100);
		mana = Game.clamp(mana, 0, 400);
		if(count == 60) {
			count = 0;
			mana++;
		}
		if(mana < 400)
			count ++;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(selected*50+100, 397, 50, 56);
		g.setColor(Color.gray);
		g.fillRect(40, 40, 200, 30);
		g.fillRect(40, 80, 200, 30);
		g.setColor(Color.green);
		g.fillRect(40, 40, health*2, 30);
		g.setColor(Color.blue);
		g.fillRect(40, 80, mana/2, 30);
		g.setColor(Color.gray);
		g.fillRect(150, 400, 350, 50);
		g.setColor(Color.black);
		for(int i=150; i < 500; i+= 50) {
			g.drawRect(i, 400, 50, 50);
		}
		g.drawImage(images[0], 155, 405, 40, 40, null);
		g.drawImage(images[1], 205, 405, 40, 40, null);
		g.drawImage(images[2], 255, 405, 40, 40, null);
		if(fireball)
			g.drawImage(images[3], 305, 405, 40, 40, null);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
		g.drawString(String.valueOf(potionCount), 240, 415);
		g.drawString(String.valueOf(manaCount), 290, 415);
		g.drawString(health+"/100", 120, 60);
		g.drawString(mana+"/400", 120, 100);
		g.drawImage(coin, 600, 40, 40, 40, null);
		g.drawString(String.valueOf(coins), 550, 70);


	}
}
