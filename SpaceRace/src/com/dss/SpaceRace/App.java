package com.dss.SpaceRace;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
	
	public static final int MAX_WIDTH = 600;
	public static final int MAX_HEIGHT = 550;
	JFrame frame;
	Space space;
	
	public App() {
		frame = new JFrame();
        frame = new JFrame("Space Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,550);
       
        space = new Space();
        space.setBackground(Color.BLACK);
        space.setPreferredSize(new Dimension(MAX_WIDTH,MAX_HEIGHT));
        frame.getContentPane().add(space); 
            
	}

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

	private void start() {
		frame.pack();
		frame.setVisible(true);
		Thread t = new Thread(new RepaintThread());
        t.start();
	}
	
	private class RepaintThread implements Runnable {

		@Override
		public void run() {
			while(true){
				try {
				 Thread.sleep(10);
				}
				catch(Exception e){}
				space.repaint();
				
			}
			
			
		}
		
	}
    
}
