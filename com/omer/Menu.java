package com.omer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 -50, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 -50, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 -50, 350, 100, 50);
	
	public void render(Graphics g) {
		Font fnt0 = new Font("arial", Font.BOLD, 45);
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("ESCAPE THE DUNGEON", Game.WIDTH/2 -250, 100);
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(fnt1);
		g2d.draw(playButton);
		g.drawString("PLAY", Game.WIDTH/2 -40, 185);
		g2d.draw(helpButton);
		g.drawString("HELP", Game.WIDTH/2 -40, 285);
		g2d.draw(quitButton);
		g.drawString("QUIT", Game.WIDTH/2 -40, 385);
	}
}
