//************************************************
//Author: 	Christian Hernon, W0223388
//Date: 	April 16, 2015
//Purpose: 	PROG1400 Assignment #5 - Screensaver
//************************************************

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class myRectangle extends Shape {

	//Properties
	private int x;
	private int y;
	private int width;
	private int height;
	private int moveX;
	private int moveY;
	private int count;
	private boolean shrinking;
	private int max;
	
	public myRectangle(JPanel jp) {
		this.width = 100;
		this.height = 60;
		this.x = rollRandom(jp.getWidth() - width);
		this.y = rollRandom(jp.getHeight() - height);
		this.moveX = 1;
		this.moveY = 1;
		this.count = 0;
		this.shrinking = true;
		this.max = 20 + rollRandom(30);
	}//end constructor
	
	@Override
	public void draw(Graphics g, JPanel jp) {
		Graphics2D g2d = (Graphics2D) g;
		alterWidth();
		xCheck(jp);
		yCheck(jp);
		x +=moveX;
		y +=moveY;
		//Plaid paint example by David Russel saved to the NSCC Share Drive
		BufferedImage buffImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		Graphics2D gg = buffImage.createGraphics();
		gg.setColor(makeColor());
		gg.fillRect(0, 0, 10, 10);
		gg.setColor(makeColor());
		gg.drawRect(1,  1,  6,  6);
		gg.setColor(makeColor());
		gg.fillRect(1,  1,  6,  6);
		gg.setColor(makeColor());
		gg.fillRect(4,  4,  3,  3);	
		g2d.setPaint(new TexturePaint(buffImage, new Rectangle(10, 10)));
		//end plaid paint example
		g2d.fillRect(x, y, width, height);	
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
	
	public void alterWidth() {
		if(shrinking) {
			width -= 1;
			count += 1;
			if(count == max) {
				shrinking = false;
			}
		}
		else if(!shrinking) {
			width += 1;
			count -= 1;
			if(count == 0) {
				shrinking = true;
			}			
		}
		else {
			JOptionPane.showMessageDialog(null, "Run for cover! Something has gone wrong.");
		}
	}

}//end Square class
