package com.dss.SpaceRace;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Diamond {
	private Image diamondImage;
	double x;
	double y;
	int w;
	int h;
	
	public Diamond(double x, double y) {
		diamondImage = new ImageIcon(Ship.class.getResource("/images/diamond.png")).getImage();
		this.x = x;
		this.y = y;
		w = 14;
		h = 11;
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,w,h);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(diamondImage,(int)x, (int)y, w, h, null);		
	}

}
