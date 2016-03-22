//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Square extends Shape {

	//Properties
	private Color color;
	private int x;
	private int y;
	private int width;
	private int height;
	private int moveX;
	private int moveY;
	private int maxHeight;
	private boolean shrinking;
	
	public Square(JPanel jp) {
		this.color = makeColor();
		this.maxHeight = 90;
		this.width = 90;
		this.height = maxHeight;
		this.x = rollRandom(jp.getWidth() - width);
		this.y = rollRandom(jp.getHeight() - height);
		this.moveX = 1;
		this.moveY = 1;
		this.shrinking = true;
	}//end constructor
	
	@Override
	public void draw(Graphics g, JPanel jp) {
		alterHeight();
		xCheck(jp);
		yCheck(jp);
		x +=moveX;
		y +=moveY;
		g.setColor(color);
		g.fillRect(x, y, width, height);	
	}//end draw
	
	public void xCheck(JPanel jp) {
		int leftEdge = x;
		int rightEdge = x + width;
		if(leftEdge <= 0) {
			moveX = 1;
		}
		else if(rightEdge >= jp.getWidth()) {
			moveX = -1;
		}
	}//end xCheck
	
	public void yCheck(JPanel jp) {
		int upperEdge = y;
		int lowerEdge = y + height;
		if(upperEdge <= 0){
			moveY = 1;
		}
		else if(lowerEdge >= jp.getHeight()) {
			moveY = -1;
		}
	}//end yCheck
	
	public void alterHeight() {
		if(shrinking) {
			height -= 1;
			y += 1;
			if(height == 0) {
				shrinking = false;
				color = makeColor();
			}
		}
		else if(!shrinking) {
			height += 1;
			y -= 1;
			if(height == maxHeight) {
				shrinking = true;
			}
		}
	}//end alterHeight

}//end Square class
