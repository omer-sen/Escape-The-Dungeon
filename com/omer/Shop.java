package com.omer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Shop {

	Image coin;
	Image img[] = new Image[6];
	public static int firePrice = 500;
	public static int manaPrice = 50;
	public static int healthPrice = 50;
	public static boolean fireHover = false;
	public Shop() {
		coin = new ImageIcon("src/assets/coins.png").getImage();
		img[0] = new ImageIcon("src/assets/redPotion.png").getImage();
		img[1] = new ImageIcon("src/assets/smallMana.png").getImage();
		img[2] = new ImageIcon("src/assets/FireballLeft.png").getImage();
	}

	public void render(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
		g.drawImage(coin, 600, 40, 40, 40, null);
		g.setColor(Color.white);
		g.drawString(String.valueOf(Hud.coins), 550, 70);

		// 90 x 120
		//top 3		
		//Health potion
		g.drawRect(40, 40, 130, 160); //40 - 160 - 200px // 150 
		g.drawLine(40, 150, 170, 150);
		g.drawImage(img[0], 60, 60, 90, 70, null);
		g.drawString("Buy: "+20, 55, 180);
		g.drawString("x"+Hud.potionCount, 165, 40);

		//Mana potion
		g.drawRect(220, 40, 130, 160);
		g.drawLine(220, 150, 350, 150);
		g.drawImage(img[1], 240, 60, 90, 70, null);
		g.drawString("Buy: "+20, 235, 180);
		g.drawString("x"+Hud.manaCount, 345, 40);
		
		//Open or level up Fireball
		g.drawRect(400, 40, 130, 160);
		g.drawLine(400, 150, 530, 150);
		g.drawImage(img[2], 420, 60, 90, 70, null);
		if(!Hud.fireball) {
			g.drawString("Unlock: "+firePrice, 415, 180);
		}else {
			g.drawString("Level "+Player.FireLevel+": "+firePrice, 415, 180);
		}

		//bottom 3
		g.drawRect(40, 250, 130, 160);
		g.drawLine(40, 360, 170, 360);

		g.drawRect(220, 250, 130, 160);
		g.drawLine(220, 360, 350, 360);

		g.drawRect(400, 250, 130, 160);
		g.drawLine(400, 360, 530, 360);

		if(fireHover) {
			g.setColor(Color.yellow);
			g.fillRect(220, 140, 200, 200);
			g.setColor(Color.black);
			g.drawString("Damages", 280, 170);
			g.drawString("Level 1: 50", 250, 200);
			g.drawString("Level 2: 70", 250, 230);
			g.drawString("Level 3: 90", 250, 260);
			g.drawString("Destroys walls at level 10", 200, 290);
			g.drawString("+20 for each level", 250, 320);
		}
	}
}

