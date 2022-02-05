package com.omer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Map map;
	private Handler handler;
	private Player player;
	private boolean[] keyDown = new boolean [4];
	public KeyInput(Map m, Handler handler, Player player) {
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		this.map = map;
		this.handler = handler;
		this.player = player;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_P) {
			if(Game.state == Game.STATE.PAUSE)
				Game.state = Game.STATE.GAME;
			else
				Game.state = Game.STATE.PAUSE;
		}
		if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)) { Game.setVelY(+5); player.setDirection(0); keyDown[0] = true; }
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { Game.setVelY(-5); player.setDirection(2); keyDown[1] = true; }
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { Game.setVelX(+5); player.setDirection(3); keyDown[2] = true; }
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { Game.setVelX(-5); player.setDirection(1); keyDown[3] = true; }
		if(key == KeyEvent.VK_SPACE) {player.attack();}
		if(key == KeyEvent.VK_1) Hud.selected = 1;
		if(key == KeyEvent.VK_2) Hud.selected = 2;
		if(key == KeyEvent.VK_3) Hud.selected = 3;
		if(key == KeyEvent.VK_4) Hud.selected = 4;
		if(key == KeyEvent.VK_5) Hud.selected = 5;
		if(key == KeyEvent.VK_6) Hud.selected = 6;
		if(key == KeyEvent.VK_7) Hud.selected = 7;
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[1] = false;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[2] = false;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[3] = false;

		if(!keyDown[0]&& ! keyDown[1]) Game.setVelY(0);

		if(!keyDown[2]&& ! keyDown[3]) Game.setVelX(0);



	}

}
