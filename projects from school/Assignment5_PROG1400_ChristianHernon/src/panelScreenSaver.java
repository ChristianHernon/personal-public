//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class panelScreenSaver extends JPanel {
	
	//Properties
	ArrayList<Shape> myShapes = new ArrayList<Shape>();
	private final int ANIMATION_DELAY = 15; //set to 15 
	private Timer animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());
	private int numShapes;

	public panelScreenSaver(int numShapes, int width, int height) {
		this.numShapes = numShapes;
		this.setSize(width, height);
		this.fillArrayList(numShapes);
		animationTimer.start();
	}//end constructor	
	
	public void fillArrayList(int numShapes){
		Random myRandom = new Random();
		for(int i = 0; i < numShapes; i++){
			int shape = myRandom.nextInt(5);
			switch (shape) {
				case 0: myShapes.add((Shape)new Square(this));
						break;
				case 1: myShapes.add((Shape)new Circle(this));
						break;
				case 2: myShapes.add((Shape)new Triangle(this));
						break;
				case 3: myShapes.add((Shape)new myRectangle(this));
						break;
				case 4: myShapes.add((Shape)new Mystify(this));
						break;
			}
		}
	}//end fillArrayList
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);		
		for(int i = 0; i < numShapes; i++) {
			myShapes.get(i).draw(g, this);
		}
	}//end paintComponent
	
	private class TimerHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			repaint();
		}
	}//end TimerHandler

}//end panelScreenSaver class
