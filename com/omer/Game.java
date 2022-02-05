package com.omer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

//import sun.audio.*;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static float Gamex;
	public static float Gamey;
	public static float velX;
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static float velY;
	public static boolean canGo[] = new boolean[4];
	private Map m;
	private Handler handler;
	private int time = 0;
	private boolean startTime = false;
	private boolean displayLevel = false;
	private Thread thread;
	private boolean running;
	Player p;
	Menu menu;
	Random r;
	Shop shop;
	static STATE state = STATE.MENU;
	private Hud hud;
	public Game() {
		r = new Random();
		menu = new  Menu();
		shop = new Shop();
		for(int i=0; i<4;i++) {
			canGo[i] = true;
		}
		hud = new Hud();
		m = new Map();
		handler = new Handler();
		new Window(WIDTH, HEIGHT, "Escape The Dungeon", this);

		p = new Player(getWidth()/2-49, getHeight()/2-49, ID.Player,handler);
		addKeyListener(new KeyInput(m,handler,p));
		addMouseListener(new MouseInput());
		addMouseMotionListener(new MouseHover());
		handler.addObject(p);
		//Server server = new Server(5000);
		newMap();
	}

	public void newMap() {

		removeAll();
		for(int x = 0; x < m.getSize(); x++) {
			for(int y = 0; y < m.getHeight(); y++) {
				if(m.getMap(x, y).equals("w"))
					handler.addObject(new Wall(x*50,y*50, ID.Wall, m, x, y, handler));
				if(m.getMap(x, y).equals("o"))
					handler.addObject(new Wall(x*50,y*50, ID.OuterWall, m, x, y, handler));
			}
		}
		for(int x = 0; x < m.getSize(); x++) {
			for(int y = 0; y < m.getHeight(); y++) {
				if(m.getMap(x, y).equals("g"))
					handler.addObject(new Goblin(x*50,y*50, ID.Goblin, handler));
			}
		}
		handler.addObject(p);
		for(int x = 0; x < m.getSize(); x++) {
			for(int y = 0; y < m.getHeight(); y++) {

				if(m.getMap(x, y).equals("b"))
					handler.addObject(new BigGoblin(x*50, y*50, ID.Goblin,handler));
			}
		}


	}
	public void removeAll() {
		for(int i=0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			handler.removeObject(temp);
			i--;
		}
	}
	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public void stop() {
		thread = null;
		running = false;
	}

	public void run() {
		this.requestFocus();
		long lastTime =  System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//	framess = frames;
				System.out.println(frames);
				frames = 0;
			}
		}
	}

	public void tick() {
		
		if(state == STATE.GAME) {
			handler.tick();
			hud.tick();
			if(!canGo[0])
				if(velY > 0)
					velY = 0;
			if(!canGo[1])
				if(velX < 0)
					velX = 0;
			if(!canGo[2])
				if(velY < 0)
					velY = 0;
			if(!canGo[3])
				if(velX > 0)
					velX = 0;

			Gamex += velX;
			Gamey += velY;
			if(Hud.health <= 0) {
				render();
				stop();

			}
			if(checkLevel() || (Map.level == 1 && state == STATE.GAME)) {
				startTime = true;
				displayLevel = true;
			}
			if(startTime && state == STATE.GAME) {
				time++;
			}
		}
	}

	public boolean checkLevel() {
		int count = 0;
		for(int i =0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			if(temp.getID() == ID.Goblin) {
				count++;
			}
		}
		if(count == 0) {
			removeAll();
			m.setLevel(m.getLevel()+1);
			m.setMap();
			newMap();
			time = 0;
			//Map.bigGoblinChance++;
			return true;
		}
		return false;
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if(state == STATE.GAME||state == STATE.PAUSE) {
			handler.render(g);
			hud.render(g);

		}else if(state == STATE.MENU) {
			menu.render(g);
		}else if(state == STATE.STORE) {
			shop.render(g);
		}
		if(time <=120 && displayLevel && state == STATE.GAME) {
			g.setFont(new Font("TimesRoman", Font.BOLD, 70)); 
			g.setColor(Color.white);
			g.drawString("LEVEL: "+Map.level, 170, 230);
		}else {
			displayLevel = false;
			startTime = false;
		}
		g.dispose();
		bs.show();
	}

	public static void setVelX(float X) {
		velX = X;
	}

	public static void setVelY(float Y) {
		velY = Y;
	}

	public static int clamp(int var, int min, int max) {
		if(var < min)
			return var = min;
		if(var > max)
			return var = max;
		else
			return var;
	}
	public static void main(String[] args) {
		new Game();
	}


	public static enum STATE {
		MENU,
		GAME,
		STORE,
		PAUSE();
	}






}
