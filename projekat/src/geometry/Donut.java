package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center , int radius, int innerRadius) {
		super(center, radius);  
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center , int radius, int innerRadius, boolean selected) {
		this(center,radius,innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut pomocna = (Donut)obj;
			if(this.getCenter().equals(pomocna.getCenter()) && this.getRadius() == pomocna.getRadius() && innerRadius == pomocna.innerRadius)
				return true;
			else
				return false;
		} else 
			return false;
		
	}
	
	public boolean contains(int x , int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return dFromCenter > innerRadius && super.contains(x,y); 
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius && super.contains(p.getX(),p.getY()); 
	}
	
	@Override 
	public void draw(Graphics g) {
		
		
		Ellipse2D eIner=new Ellipse2D.Float(getCenter().getX()-innerRadius,getCenter().getY()- innerRadius,2* innerRadius,2* innerRadius);
		Ellipse2D eOuter=new Ellipse2D.Float(getCenter().getX()-getRadius(),getCenter().getY()- getRadius(),2* getRadius(),2* getRadius());
		Area outer=new Area(eOuter);
		Area iner=new Area(eIner);
		outer.subtract(iner);
		
		g.setColor(getInnerColor());
		((Graphics2D) g).fill(outer);
		
		g.setColor(getColor()); 
		//g.drawOval(this.getCenter().getX() - innerRadius, this.getCenter().getY() - innerRadius, innerRadius * 2, innerRadius * 2);
		((Graphics2D) g).draw(outer);
		
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX()-2 - innerRadius, getCenter().getY()-2 , 4, 4);
			g.drawRect(getCenter().getX()-2 + innerRadius, getCenter().getY()-2 , 4, 4);
			g.drawRect(getCenter().getX()-2, getCenter().getY()-2 + innerRadius , 4, 4);
			g.drawRect(getCenter().getX()-2, getCenter().getY()-2 - innerRadius, 4, 4);
		}
	}
	
	/*@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2 );
	}*/
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return (int)(this.area() - ((Donut)o).area());
		}
		return 0;
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		 return "Donut,x:" + getCenter().getX() + ",y:" + getCenter().getY() + ",Radius:" + getRadius() + 
				",innerRadius:" + this.innerRadius + ",Selected:" + isSelected() +	",Color:" + this.getColor().getRGB() + ",InnerColor:" + this.getInnerColor().getRGB();
	}
	
	public Donut clone() {
		return new Donut(this.getCenter(), this.getRadius(), this.getInnerRadius(), this.isSelected(), this.getColor(), this.getInnerColor());
	}
}
