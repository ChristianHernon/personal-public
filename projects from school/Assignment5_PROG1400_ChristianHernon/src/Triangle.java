//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Triangle extends Shape {

	//Properties
	private Color color;
	private int R;
	private int G;
	private int B;
	private int r;
	private int g;
	private int b;
	private int count;
	private int[] xPoints;
	private int[] yPoints;
	private int numPoints;
	private int moveX;
	private int moveY;
	private Polygon p;
	
	public Triangle(JPanel jp) {
		this.color = makeColor();
		this.R = color.getRed();
		this.B = color.getBlue();
		this.G = color.getGreen();
		this.r = 1;
		this.g = 1;
		this.b = 1;
		this.count = 0;
		this.xPoints = new int[3];
		this.xPoints[0] = rollRandom(jp.getWidth() - 60);
		this.xPoints[1] = xPoints[0] + 60;
		this.xPoints[2] = xPoints[0] + 30;
		this.yPoints = new int[3];
		this.yPoints[0] = rollRandom(jp.getHeight() - 60);
		this.yPoints[1] = yPoints[0];
		this.yPoints[2] = yPoints[0] - 60;
		this.numPoints = 3;
		this.p = new Polygon(xPoints, yPoints, numPoints);
		this.moveX = 1;
		this.moveY = 1;
	}//end constructor
	
	@Override
	public void draw(Graphics g, JPanel jp) {
		Graphics2D g2d = (Graphics2D)g;
		count = rollRandom(3);
		switch (count) {
			case 0: modRed();
			case 1: modGreen();
			case 2: modBlue();
		}
		color = new Color(R, G, B);
		g2d.setColor(color);
		Rectangle bounds = p.getBounds();
		xCheck(jp, bounds);
		yCheck(jp, bounds);
		p.translate(moveX, moveY);
		g2d.fillPolygon(p);
	}//end draw
	
	public void xCheck(JPanel jp, Rectangle bounds) {
		int leftEdge = (int) bounds.getX();
		int rightEdge = leftEdge + (int) bounds.getWidth();
		if(leftEdge <= 0) {
			moveX = 1;
		}
		else if(rightEdge >= jp.getWidth()) {
			moveX = -1;
		}
	}//end xCheck
	
	public void yCheck(JPanel jp, Rectangle bounds) {
		int lowerEdge = (int) bounds.getY();
		int upperEdge = lowerEdge + (int) bounds.getHeight();
		if(lowerEdge <= 0){
			moveY = 1;
		}
		else if(upperEdge >= jp.getHeight()) {
			moveY = -1;
		}
	}//end yCheck
	
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

}//end Triangle class
