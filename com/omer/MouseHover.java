package com.omer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseHover implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//Fire Hover Menu
		if(Game.state == Game.STATE.STORE) {
			if(mx >= 400 && mx <= 530) {
				if(my >= 40 && my <= 200) {
					Shop.fireHover = true;
				}else 
					Shop.fireHover = false;
			}
			else 
				Shop.fireHover = false;
		}
		
	}

}
