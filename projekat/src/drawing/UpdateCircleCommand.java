package drawing;

import geometry.Circle;

public class UpdateCircleCommand implements Command{

	Circle newCircle, oldCircle, originalCircle;
	
	public UpdateCircleCommand(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}
	
	
	@Override
	public void Do() {
		originalCircle = oldCircle;
		
		oldCircle.setCenter(newCircle.getCenter());
		oldCircle.setColor(newCircle.getColor());
		oldCircle.setInnerColor(newCircle.getInnerColor());
		//oldCircle.setRadius(newCircle.getRadius());
		oldCircle.setSelected(newCircle.isSelected());
	}

	@Override
	public void Undo() {
		oldCircle.setCenter(originalCircle.getCenter());
		oldCircle.setColor(originalCircle.getColor());
		oldCircle.setInnerColor(originalCircle.getInnerColor());
		//oldCircle.setRadius(originalCircle.getRadius());
		oldCircle.setSelected(originalCircle.isSelected());
		
	}


	@Override
	public String toString() {
		return "UpdateCircleCommand [newCircle=" + newCircle + ", oldCircle=" + oldCircle + ", originalCircle="
				+ originalCircle + "]";
	}

	
}
