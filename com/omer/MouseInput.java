package com.omer;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.omer.Game.STATE;

public class MouseInput implements MouseListener{


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(Game.state == STATE.MENU) {
			if(mx >= Game.WIDTH / 2 + -50 && mx <= Game.WIDTH / 2 + 50) {
				if(my >= 150&& my <=200)
				{
					//Pressed play Button 
					System.out.println("PLAY");
					Game.state = STATE.GAME;
				}
			}
			if(mx >= Game.WIDTH / 2 -50 && mx <= Game.WIDTH / 2 + 50) {
				if(my >= 250&& my <=300)
				{
					//Pressed help Button 
				}
			}
			if(mx >= Game.WIDTH / 2 -50 && mx <= Game.WIDTH / 2 +50) {
				if(my >= 350&& my <=400)
				{
					//Pressed exit Button 
					System.exit(1);
				}
			}
		}


		if(Game.state == STATE.GAME) {
			if(my >= 400 && my <= 450) {
				if(mx >=150 && mx < 200) {
					Hud.selected = 1;
				}
				if(mx >= 200 && mx < 250) {
					Hud.selected = 2;
				}
				if(mx >=250 && mx < 300) {
					Hud.selected = 3;
				}
				if(mx >=300 && mx < 350) {
					Hud.selected = 4;
				}
				if(mx >=350 && mx < 400) {
					Hud.selected = 5;
				}
				if(mx >=400 && mx < 450) {
					Hud.selected = 6;
				}
				if(mx >=450 && mx < 500) {
					Hud.selected = 7;
				}
			}

		}
		if(mx >= 600 && mx <=640) {
			if(my >= 40 && my <= 80) {
				if(Game.state == Game.STATE.STORE)
					Game.state = Game.STATE.GAME;
				else 
					Game.state = Game.STATE.STORE;

			}
		}
		if(Game.state == Game.STATE.STORE) {
			if(mx >= 400 && mx <= 560) {
				if(my >= 40 && my <= 200) {
					if(Hud.coins >= Shop.firePrice) {
						if(!Hud.fireball) {
							Hud.fireball = true;
							Player.FireLevel = 1;
						}else {
							Player.FireLevel ++;
							FireBall.damage += 20;
						}
						Hud.coins -= Shop.firePrice;
						Shop.firePrice += 200;
					}
				}
			}
			
			if(mx >= 40 && mx <= 170) {
				if(my >= 40 && my <= 200) {
					if(Hud.coins >= Shop.healthPrice) {
						Hud.coins -= Shop.healthPrice;
						Hud.potionCount++;
					}
				}
			}
			
			if(mx >= 220 && mx <= 350) {
				if(my >= 40 && my <= 200) {
					if(Hud.coins >= Shop.manaPrice) {
						Hud.coins -= Shop.manaPrice;
						Hud.manaCount++;
					}
				}
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
