//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Circle extends Shape {

	//Properties
	private Color color1;
	private Color color2;
	private int R;
	private int G;
	private int B;
	private int x;
	private int y;
	private int diameter;
	private int moveX;
	private int moveY;
	private int count;
	private int r = 1;
	private int g = 1;
	private int b = 1;
	
	public Circle(JPanel jp) {
		this.color1 = makeColor();
		this.color2 = makeColor();
		this.R = color2.getRed();
		this.B = color2.getBlue();
		this.G = color2.getGreen();
		this.diameter = 80;
		this.x = rollRandom(jp.getWidth() - (diameter * 2));
		this.y = rollRandom(jp.getHeight() - (diameter * 2));
		this.moveX = 1;
		this.moveY = 1;
		this.count = 0;
	}//end constructor

	@Override
	public void draw(Graphics g, JPanel jp) {
		Graphics2D g2d = (Graphics2D) g;
		xCheck(jp);
		yCheck(jp);
		x += moveX;
		y += moveY;
		count = rollRandom(3);
		switch (count) {
			case 0: modRed();
					break;
			case 1: modGreen();
					break;
			case 2: modBlue();
					break;
		}
		color2 = new Color(R, G, B);
		g2d.setPaint(new GradientPaint(5, 20, color1, 25, 40, color2, true));
		g2d.fillOval(x, y, diameter, diameter);	
	}//end draw
	
	public void modRed() {
		if(R == 255){
			r = -1;
		}
		else if(R == 0) {
			r = 1;
		}
		R += r;
	}//end modRed
	
	public void modGreen() {
		if(G == 255) {
			g = -1;
		}
		else if(G == 0) {
			g = 1;
		}
		G += g;
	}//end modGreen
	
	public void modBlue() {
		if(B == 255) {
			b = -1;
		}
		else if(B == 0) {
			b = 1;
		}
		B += b;
	}//end modBlue
	
	public void xCheck(JPanel jp) {
		int leftEdge = x;
		int rightEdge = x + diameter;
		if(leftEdge <= 0) {
			moveX = 1;
		}
		else if(rightEdge >= jp.getWidth()) {
			moveX = -1;
		}
	}//end xCheck
	
	public void yCheck(JPanel jp) {
		int upperEdge = y;
		int lowerEdge = y + diameter;
		if(upperEdge <= 0){
			moveY = 1;
		}
		else if(lowerEdge >= jp.getHeight()) {
			moveY = -1;
		}
	}//end yCheck
	
}//end Circle class
