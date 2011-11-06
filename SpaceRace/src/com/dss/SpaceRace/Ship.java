package com.dss.SpaceRace;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Ship implements KeyListener {
	private static final double POWER = 5;
	private double x;
	private double y;
	private int w;
	private int h;
	private double dx; //per sec
	private double dy; //per sec
    private Image shipImageNoGlow;
    private Image shipImageGT;
    private Image shipImageGB;
    private Image shipImageGL;
    private Image shipImageGR;
    private Image shipImage;
	
	
	public Ship() {
		x = 300;
		y = 225;
		w = 30;
		h = 26;
		dx = 0;
		dy = 0;
		shipImageNoGlow = new ImageIcon(Ship.class.getResource("/images/ship.png")).getImage();
		shipImageGT = new ImageIcon(Ship.class.getResource("/images/shipGlowTop.png")).getImage();
		shipImageGB = new ImageIcon(Ship.class.getResource("/images/shipGlowBottom.png")).getImage();
		shipImageGL = new ImageIcon(Ship.class.getResource("/images/shipGlowLeft.png")).getImage();
		shipImageGR = new ImageIcon(Ship.class.getResource("/images/shipGlowRight.png")).getImage();
		shipImage = shipImageNoGlow;
	}

	public void move(long elapsedTime) {
		x = x+(dx/1000*elapsedTime);
	    y = y+(dy/1000*elapsedTime);
	    if(x > App.MAX_WIDTH) {
	    	x = x-App.MAX_WIDTH;
	    }
	    if (x<0) {
	    	x = App.MAX_WIDTH+x;
	    }
	    if (y>App.MAX_HEIGHT) {
	    	y = y-App.MAX_HEIGHT;
	    }
	    if (y<0) {
	    	y = App.MAX_HEIGHT+y;
	    }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==e.VK_DOWN) {
			dy=dy+POWER;
			shipImage = shipImageGT;
		}
		if(e.getKeyCode()==e.VK_UP) {
			dy=dy-POWER;
			shipImage = shipImageGB;
		}
		if(e.getKeyCode()==e.VK_RIGHT) {
			dx=dx+POWER;
			shipImage = shipImageGL;
		}
		if(e.getKeyCode()==e.VK_LEFT) {
			dx=dx-POWER;
			shipImage = shipImageGR;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		shipImage = shipImageNoGlow;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(shipImage, (int)x, (int)y, w, h, null);
		boolean drawSecondShip = false;
		int x2 = (int)x;
		int y2 = (int)y;
		if ((x+w)>App.MAX_WIDTH) {
			drawSecondShip = true;
			x2 = x2 - App.MAX_WIDTH;
		}
		if ((y+h)>App.MAX_HEIGHT) {
			drawSecondShip = true;
			y2 = y2 - App.MAX_HEIGHT;
		}
		if (drawSecondShip) {
			g.drawImage(shipImage, x2, y2, w, h, null);
		}
	}
	
}
