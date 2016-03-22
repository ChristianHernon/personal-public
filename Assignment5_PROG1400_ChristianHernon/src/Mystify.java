//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Mystify extends Shape {

	//Properties
	private Color color;
	private int count;
	private int R;
	private int G;
	private int B;
	private int r;
	private int g;
	private int b;
	private int[] xPoints;
	private int[] xMove;
	private int[] yPoints;
	private int[] yMove;
	private int numPoints;
	private Polygon p;
	private ArrayList<Polygon> myTrails;
	private int numTrails;
	private int spacing;
	private int[] test = {-1, 1, 2, 3, 4};
	private int colorSpeed;
	private int speed;
//	private int shrinking;
//	private int shrinkTimer;
	
	public Mystify(JPanel jp) {
		this.color = makeColor();
		this.count = 0;
		this.R = color.getRed();
		this.B = color.getBlue();
		this.G = color.getGreen();
		this.r = test[rollRandom(2)];
		this.g = test[rollRandom(2)];
		this.b = test[rollRandom(2)];
		this.colorSpeed = test[rollRandom(3) + 1];
		this.xPoints = new int[4];
		this.speed = test[1 + rollRandom(4)];
		this.xPoints[0] = rollRandom(jp.getWidth());
		this.xPoints[1] = rollRandom(jp.getWidth());
		this.xPoints[2] = rollRandom(jp.getWidth());
		this.xPoints[3] = rollRandom(jp.getWidth());
		this.xMove = new int[4];
		this.xMove[0] = rollRandom(2);
		this.xMove[1] = rollRandom(2);
		this.xMove[2] = rollRandom(2);
		this.xMove[3] = rollRandom(2);
		this.yPoints = new int[4];
		this.yPoints[0] = rollRandom(jp.getHeight());
		this.yPoints[1] = rollRandom(jp.getHeight());
		this.yPoints[2] = rollRandom(jp.getHeight());
		this.yPoints[3] = rollRandom(jp.getHeight());
		this.yMove = new int[4];
		this.yMove[0] = rollRandom(2);
		this.yMove[1] = rollRandom(2);
		this.yMove[2] = rollRandom(2);
		this.yMove[3] = rollRandom(2);
		this.numPoints = 4;
		this.myTrails = new ArrayList<Polygon>();
		this.numTrails = 3 + rollRandom(15);
		this.spacing = 1 + rollRandom(8);
//		this.shrinking = rollRandom(2);
//		this.shrinkTimer = 0;
	}//end constructor
	
	@Override
	public void draw(Graphics g, JPanel jp) {
		myTrails.clear();
		p = new Polygon(xPoints, yPoints, numPoints);
		for(int i = 0; i < numTrails; i++) {
			myTrails.add(new Polygon(xPoints, yPoints, numPoints));
			myTrails.get(i).translate(spacing * i, spacing * i);
		}
		count = rollRandom(3);
		switch (count) {
			case 0: modRed();
					break;
			case 1: modGreen();
					break;
			case 2: modBlue();
					break;
		}
		color = new Color(R, G, B);
		g.setColor(color);
		g.drawPolygon(p);
		for(int i = 0; i < myTrails.size(); i++) {
			g.drawPolygon(myTrails.get(i));
		}
//		shrinkTimer += 1;
//		if(shrinkTimer == 8) {
//			alterSpacing();
//			shrinkTimer = 0;
//		}		
		checkBounds(jp);
		movePoints();
	}//end draw
	
	public void checkBounds(JPanel jp) {
		for(int i = 0; i < numPoints; i++) {
			if(xPoints[i] <= 0) {
				xMove[i] = 1;
				//alterSpacing();
			}
			else if(xPoints[i] >= jp.getWidth()) {
				xMove[i] = 0;
				//alterSpacing();
			}
			if(yPoints[i] <= 0) {
				yMove[i] = 1;
				//alterSpacing();
			}
			else if(yPoints[i] >= jp.getHeight()) {
				yMove[i] = 0;
				//alterSpacing();
			}
		}
	}//end checkBounds
	
//	public void alterSpacing() {
//		if(shrinking == 1) {
//			spacing -= 1;
//			if(spacing ==0) {
//				shrinking = 0;
//			}
//		}
//		else if(shrinking == 0) {
//			spacing += 1;
//			if(spacing == 5) {
//				shrinking = 1;
//			}
//		}
//	}
	
	public void movePoints() {
		for(int i = 0; i < numPoints; i++) {
			switch (xMove[i]) {
				case 0: xPoints[i] -= speed;
						break;
				case 1: xPoints[i] += speed;
						break;
			}
			switch (yMove[i]) {
				case 0: yPoints[i] -= speed;
						break;
				case 1: yPoints[i] += speed;
						break;
			}
		}
	}//end movePoints
	
	public void modRed() {
		if(R >= 255){
			r = -1;
		}
		else if(R <= 0) {
			r = 1;
		}
		R += r * colorSpeed;
		if(R < 0) {
			R = 0;
		}
		else if(R > 255) {
			R = 255;
		}
	}//end modRed
	
	public void modGreen() {
		if(G >= 255) {
			g = -1;
		}
		else if(G <= 0) {
			g = 1;
		}
		G += g * colorSpeed;
		if(G < 0) {
			G = 0;
		}
		else if(G > 255) {
			G = 255;
		}
	}//end modGreen
	
	public void modBlue() {
		if(B >= 255) {
			b = -1;
		}
		else if(B <= 0) {
			b = 1;
		}
		B += b * colorSpeed;
		if(B < 0) {
			B = 0;
		}
		else if(B > 255) {
			B = 255;
		}
	}//end modBlue

}//end Triangle class
