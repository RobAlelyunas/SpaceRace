package com.dss.SpaceRace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App { 

	JFrame frame;
	Space space;
	GameOverPanel gameOverScreen;
	
	public App() {
		frame = new JFrame();
        frame = new JFrame("Space Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Space.MAX_WIDTH,Space.MAX_HEIGHT);
        frame.getContentPane().setPreferredSize(new Dimension(Space.MAX_WIDTH, Space.MAX_HEIGHT));
        frame.getContentPane().setLayout(null);
       
        space = new Space(); 
        space.setBounds(0, 0, Space.MAX_WIDTH,Space.MAX_HEIGHT);      
        
        gameOverScreen = new GameOverPanel(this);
        int x = (Space.MAX_WIDTH - GameOverPanel.MAX_WIDTH)/2;
        int y = (Space.MAX_HEIGHT - GameOverPanel.MAX_HEIGHT)/2;
        gameOverScreen.setBounds(x,y,GameOverPanel.MAX_WIDTH,GameOverPanel.MAX_HEIGHT);
        
        frame.getContentPane().add(gameOverScreen);       
        frame.getContentPane().add(space); 
	}

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

	private void start() {
		frame.pack();
		frame.setVisible(true);
		newGame();
	}
	public void newGame() {
		gameOverScreen.setVisible(false);
		space.setUpNewGame();
		gameOverScreen.setUpNewGame();
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
				frame.repaint();
				gameOver = space.detectGameOver();
			}
			gameOverScreen.setVisible(true);
		}

	}
	public void quit() {
		System.exit(0);
		
	}
    
}
