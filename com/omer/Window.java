package com.omer;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends Canvas{
	
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);

		frame.setSize(width,height);
	//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	//	frame.setUndecorated(true);
		//frame.setPreferredSize(new Dimension(width, height));
		//frame.setMaximumSize(new Dimension(width, height));
		//frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		//device.setFullScreenWindow(frame);
		game.start();
	}
}
