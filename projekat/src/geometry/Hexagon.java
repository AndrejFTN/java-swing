package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hexagon extends SurfaceShape{
	
	private hexagon.Hexagon hexagon;
	
	
	public Hexagon() {
		
	}
	
	public Hexagon(Point center, int radius) {
		this.hexagon = new hexagon.Hexagon(center.getX(), center.getY(), (int) radius); // <--

	}
	
	public Hexagon(Point center, int radius, Color color) {
		this(center, radius);
		setColor(color);
	}
	
	public Hexagon(Point center, int radius, boolean selected, Color color) {
		this(center, radius, color);
		this.hexagon.setSelected(selected);
	}
	
	public Hexagon(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return ((3 * Math.sqrt(3) *
                (this.hexagon.getR() * this.hexagon.getR())) / 2); 
	}
	
	public double circumference() {
		return 6*this.hexagon.getR();
	}

	@Override
	public void moveTo(int x, int y) {
		this.hexagon.setX(x);
		this.hexagon.setY(y);
		
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(this.hexagon.getX() + byX);
		this.hexagon.setY(this.hexagon.getY() + byY);
		
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Hexagon) {
			return (int) ((int) this.area() - ((Hexagon)o).area());
		}
		
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return this.hexagon.doesContain(x, y);
	}
	
	/*@Override
	public boolean contains(int x, int y) {
		
		for (int y1 = 0; y1 < 6; y1++) {
            int xval = (int) (center.getX() + radius
                    * Math.cos(y1 * 2 * Math.PI / 6D));
            int yval = (int) (center.getY() + radius
                    * Math.sin(y1 * 2 * Math.PI / 6D));
            
            Point point = new Point(xval, yval);
           
            niz[y1] = point;
        }
		
		
		Point point1 = new Point(x, y);
		
		for(int i = 0; i < niz.length - 3; i++) {
			double areaBigT = areaOfTriangle(niz[i + 1], niz[i + 2], niz[i + 3]);
			
			double t1 = areaOfTriangle(point1, niz[0], niz[1]);
			double t2 = areaOfTriangle(point1, niz[1], niz[2]);
			double t3 = areaOfTriangle(point1, niz[2], niz[3]);
			
			double areaSmallT = t1 + t2 + t3;
			
			if(Math.abs(areaSmallT - areaSmallT) < 0.1) {
				return true;
			}
		}

		return false;
	}
	
	public double areaOfTriangle(Point a, Point b, Point c) {
		double sideA = Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2));
		double sideB = Math.sqrt(Math.pow((a.getX() - c.getX()), 2) + Math.pow((a.getY() - c.getY()), 2));
		double sideC = Math.sqrt(Math.pow((b.getX() - c.getX()), 2) + Math.pow((b.getY() - b.getY()), 2));
		double s = sideA + sideB + sideC;
		double area = Math.sqrt(s* (s - sideA) * (s - sideB) * (s - sideC));
		return area;
	}
	*/

	@Override
	public void draw(Graphics g) {
		
		this.hexagon.setBorderColor(getColor());
		this.hexagon.setAreaColor(getInnerColor());
		
		this.hexagon.paint(g);

		
		/*Polygon polygon = new Polygon();
		g.setColor(getColor());
        for (int i = 0; i < 6; i++) {
            int xval = (int) (center.getX() + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yval = (int) (center.getY() + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            polygon.addPoint(xval, yval);
        }
        
        
        g.drawPolygon(polygon);
        
        if(isSelected()) {
        	g.setColor(Color.BLUE);
            for (int i = 0; i < 6; i++) {
                int xval = (int) (center.getX() + radius
                        * Math.cos(i * 2 * Math.PI / 6D));
                int yval = (int) (center.getY() + radius
                        * Math.sin(i * 2 * Math.PI / 6D));
                
                g.drawRect(xval - 2, yval - 2, 4, 4);
            }
        }
        
        g.setColor(getInnerColor());
        g.fillPolygon(polygon);*/
		
		if(isSelected()) {
        	g.setColor(Color.BLUE);
            for (int i = 0; i < 6; i++) {
                int xval = (int) (hexagon.getX() + hexagon.getR()
                        * Math.cos(i * 2 * Math.PI / 6D));
                int yval = (int) (hexagon.getY() + hexagon.getR()
                        * Math.sin(i * 2 * Math.PI / 6D));
                
                g.drawRect(xval - 2, yval - 2, 4, 4);
            }
        }
	}
	
	
	
	
	@Override
	public void fill(Graphics g) {
		
	}

	public Point getCenter() {
		return new Point(this.hexagon.getX(), this.hexagon.getY());
	}

	public void setPoint(Point point) {
		this.hexagon.setX(point.getX());
		this.hexagon.setY(point.getY());
	}

	public int getRadius() {
		return hexagon.getR();
	}
	
	public void setRadius(int radius) throws Exception {
		if(radius < 0) {
			throw new Exception("Vrednost poluprecnika mora biti veca od 0");
		}
		this.hexagon.setR(radius);
	}

	@Override
	public String toString() {
		return "Hexagon,x:" + hexagon.getX() + ",y:" + hexagon.getY() + ",radius:" + hexagon.getR() + 
				",selected:" + this.isSelected() +	",color:" + this.getColor().getRGB() + ",innerColor:" + this.getInnerColor().getRGB();
		
	}

	public Hexagon clone() {
		return new Hexagon(this.getCenter(), (int)this.getRadius(), this.isSelected(), this.getColor(), this.getInnerColor());
	}
	
}
