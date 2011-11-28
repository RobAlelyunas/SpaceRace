package com.dss.SpaceRace;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App { 

	JFrame frame;
	Space space;
	
	public App() {
		frame = new JFrame();
        frame = new JFrame("Space Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,550);
       
        space = new Space();
        space.setBackground(Color.BLACK);
        space.setPreferredSize(new Dimension(Space.MAX_WIDTH,Space.MAX_HEIGHT));
        frame.getContentPane().add(space); 
            
	}

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

	private void start() {
		frame.pack();
		frame.setVisible(true);
		Thread t = new Thread(new GameActionThread());
        t.start();
	}
	
	private class GameActionThread implements Runnable {

		@Override
		public void run() {
			boolean gameOver = false;
			while(!gameOver){
				try {
				 Thread.sleep(4);
				}
				catch(Exception e){}
				space.moveAll();
				space.detectCollisions();
				space.repaint();
				gameOver = space.detectGameOver();
				
			}
			
			
		}
		
	}
    
}
