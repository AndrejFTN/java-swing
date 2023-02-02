package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape{
	
	private Point center;
	private int radius;
	private int i = 0;
	private int j = 0;
	
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center , int radius , boolean selected) {
		this (center , radius);
		setSelected(selected);
		
	}
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		this.setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		this.setInnerColor(innerColor);
		
	}
	
	public double area() {
		return this.radius * this.radius * Math.PI;
	}
	
	public double circumference() {
		return 2 * this.radius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle pomocna = (Circle)obj;
			if(this.center.equals(pomocna.center) && this.radius == pomocna.radius)
				return true;
			else
				return false;
		}else
			return false;
		
	}
	
	public boolean contains(int x , int y) {
		return center.distance(x, y) <= radius;
		
	}
	
	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);//(moramo dobiti gornju levu tacku ovala) , tako sto oduzmemo od centra radius
		this.fill(g);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX()-2 , center.getY()-2 , 4, 4);
			g.drawRect(center.getX()-2 + radius, center.getY()-2 , 4, 4);
			g.drawRect(center.getX()-2 - radius, center.getY()-2 , 4, 4);
			g.drawRect(center.getX()-2 , center.getY()-2 - radius, 4, 4);
			g.drawRect(center.getX()-2 , center.getY()-2 + radius, 4, 4);
		}
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1, this.radius * 2 - 2, this.radius * 2 - 2);
	}
	
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		
		if(o instanceof Circle) {
			return (int)(this.area() - ((Circle)o).area());
		}
		return 0;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) throws Exception {
		if(radius < 0) {
			throw new Exception("Vrednost poluprecnika mora biti veca od 0");
		}
		this.radius = radius;
	}
	
	public String toString() {
		return "Circle,x:" + center.getX() + ",y:" + center.getY() + ",Radius:" + this.radius + 
				",Selected:" + this.isSelected() +	",Color:" + this.getColor().getRGB() + ",InnerColor:" + this.getInnerColor().getRGB();
		
	}

	public Circle clone() {
		return new Circle(this.getCenter(), this.getRadius(), this.isSelected(), this.getColor(), this.getInnerColor());
	}
}
