package com.dss.SpaceRace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Diamond {

	double x;
	double y;
	int w;
	int h;
	
	public Diamond(double x, double y) {
		this.x = x;
		this.y = y;
		w = 5;
		h = 5;
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,w,h);
	}

	public void paintComponent(Graphics g) {
		Color currentColor = g.getColor();
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y, w, h);
		g.setColor(currentColor);
		
	}

}
