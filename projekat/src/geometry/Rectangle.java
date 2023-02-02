package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape{
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeftPoint , int width , int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint , int width , int height , boolean selected) {
		this (upperLeftPoint , width , height);
		this.setSelected(selected);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color) {
		this(upperLeftPoint, width, height, selected);
		this.setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, Color color, Color innerColor) {
		this(upperLeftPoint, width, height);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, width, height, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public int area() {
		return this.height * this.width;
	}
	
	public int circumference() {
		return this.height * 2 + 2 * this.width;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width && this.height == pomocna.height)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean contains(int x , int y) {
		if(x >= upperLeftPoint.getX() && x <= (upperLeftPoint.getX() + width)
				&& y >= upperLeftPoint.getY() && y <= (upperLeftPoint.getY() + height))
			return true;
		else 
			return false;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		this.fill(g);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX()-2 , this.upperLeftPoint.getY()-2 , 4, 4);
			g.drawRect(this.upperLeftPoint.getX()-2 + this.width, upperLeftPoint.getY()-2 , 4, 4);
			g.drawRect(this.upperLeftPoint.getX()-2 + this.width , upperLeftPoint.getY()-2 + this.height , 4, 4);
			g.drawRect(this.upperLeftPoint.getX()-2 , this.upperLeftPoint.getY()-2 + this.height, 4, 4);
		}
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.upperLeftPoint.getY() + 1, this.width - 1, this.height - 1);
	}
	
	//Overloading metode , isto ime , ali drugaciji tip podatka (= potpis metode)
	public boolean contains(Point p) {
		if(p.getX() >= upperLeftPoint.getX() && p.getX() <= (upperLeftPoint.getX() + width)
				&& p.getY() >= upperLeftPoint.getY() && p.getY() <= (upperLeftPoint.getY() + height))
			return true;
		else 
			return false;
	}
	
	@Override
	public void moveTo(int x, int y) {
		this.upperLeftPoint.moveTo(x, y);
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX,byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		//Poredimo povrsine
		if(o instanceof Rectangle) {
			return this.area() - ((Rectangle)o).area();
		}
		return 0;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public String toString() {
		return "Rectangle,x:" + upperLeftPoint.getX() + ",y:" + upperLeftPoint.getY() + ",height:" + this.height + 
				",width:" + this.width + ",color:" + this.getColor().getRGB() + ",innerColor:" + this.getInnerColor().getRGB() + ",selected:" + this.isSelected();
		
	}

	public Rectangle clone() {
		return new Rectangle(this.getUpperLeftPoint(), this.getWidth(), this.getHeight(), this.isSelected(), this.getColor(), this.getInnerColor());
	}
}
