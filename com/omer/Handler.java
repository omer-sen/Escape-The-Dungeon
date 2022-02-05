package com.omer;
import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	ArrayList<GameObject> list = new ArrayList<GameObject>();
	public void render(Graphics g) {
		for(int i = 0; i < list.size(); i++) {
			GameObject temp = list.get(i);
			//if(temp.getX() >=-50 && temp.getX() <= 690) {
				//if(temp.getY() >= -50 && temp.getY() <= 690) {
					temp.render(g);
			//	}
			//}
		}
	}
	
	public void tick() {
		for(int i = 0; i < list.size(); i++) {
			GameObject temp = list.get(i);
			temp.tick();
		}
	}
	
	public void addObject(GameObject obj) {
		list.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		list.remove(obj);
	}	
	
	public GameObject getPlayer() {
		for(int i=0; i < list.size(); i++) {
			GameObject temp = list.get(i);
			if(temp.getID() == ID.Player)
				return temp;
		}
		return null;
	}
}
