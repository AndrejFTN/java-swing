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
		oldPoint.setSelected(newPoint.isSelected());
	}

	@Override
	public void Undo() {
		oldPoint.setX(pointOriginal.getX());
		oldPoint.setY(pointOriginal.getY());
		oldPoint.setColor(pointOriginal.getColor());
		oldPoint.setSelected(pointOriginal.isSelected());
		
	}

	@Override
	public String toString() {
		return "Update_" + pointOriginal.toString() + "|" + newPoint.toString();
	}
	
}
