//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public abstract class Shape {
	
	private Random myRandom;
	
	public Shape() {
		this.myRandom = new Random();
	}
	
	public abstract void draw(Graphics g, JPanel jp);
	
	public int rollRandom(int max) {
		int num = myRandom.nextInt(max);
		return num;
	}
	
	public Color makeColor() {	
		int R = (int)(Math.random() * 256);
		int G = (int)(Math.random() * 256);
		int B = (int)(Math.random() * 256);		
		Color newColor = new Color(R, G, B);		
		return newColor;	
	}
}
