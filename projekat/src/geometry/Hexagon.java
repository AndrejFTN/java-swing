package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Hexagon extends SurfaceShape{
	
	private Point center;
	private int radius;
	
	public Hexagon() {
		
	}
	
	public Hexagon(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Hexagon(Point center, int radius, Color color) {
		this(center, radius);
		this.setColor(color);
	}
	
	public Hexagon(Point center, int radius, boolean selected, Color color) {
		this(center, radius, color);
		this.setSelected(selected);
	}
	
	public Hexagon(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		this.setInnerColor(innerColor);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		Polygon polygon = new Polygon();

        for (int i = 0; i < 6; i++) {
            int xval = (int) (center.getX() + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yval = (int) (center.getY() + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            polygon.addPoint(xval, yval);
        }
        
        g.drawPolygon(polygon);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		//proveriti kako radi za hexagon
		//g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1, this.radius * 2 - 2, this.radius * 2 - 2);
	}

	public Point getCenter() {
		return center;
	}

	public void setPoint(Point point) {
		this.center = point;
	}

	public double getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) throws Exception {
		if(radius < 0) {
			throw new Exception("Vrednost poluprecnika mora biti veca od 0");
		}
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Hexagon [point=" + center + ", radius=" + radius + "]";
	}

	
	
}
