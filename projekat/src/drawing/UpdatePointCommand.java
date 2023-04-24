package drawing;

import geometry.Point;
import geometry.Shape;

public class UpdatePointCommand implements Command {

	Point oldPoint;
	Point pointOriginal;
	Point newPoint;
	
	public UpdatePointCommand(Point oldPoint, Point newPoint) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}

	@Override
	public void Do() {
		pointOriginal = oldPoint.clone();
		
		oldPoint.setX(newPoint.getX());
		oldPoint.setY(newPoint.getY());
		oldPoint.setColor(newPoint.getColor());
		oldPoint.setSelected(true);
	}

	@Override
	public void Undo() {
		oldPoint.setX(pointOriginal.getX());
		oldPoint.setY(pointOriginal.getY());
		oldPoint.setColor(pointOriginal.getColor());
		oldPoint.setSelected(true);
		
	}

	@Override
	public String toString() {
		return "Update,Point,x:" + pointOriginal.getX() + ",y:" + pointOriginal.getY() + ",Selected:" + pointOriginal.isSelected() +
				",Color:" + pointOriginal.getColor().getRGB() + ",newPoint x:" + newPoint.getX() + ",y:" + newPoint.getY() + ",select:" + newPoint.isSelected() + ",color:" + newPoint.getColor().getRGB();
	}
	
}
