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
		originalCircle = oldCircle.clone();
		
		oldCircle.setCenter(newCircle.getCenter());
		oldCircle.setColor(newCircle.getColor());
		oldCircle.setInnerColor(newCircle.getInnerColor());
		try {
			oldCircle.setRadius(newCircle.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldCircle.setSelected(newCircle.isSelected());
	}

	@Override
	public void Undo() {
		oldCircle.setCenter(originalCircle.getCenter());
		oldCircle.setColor(originalCircle.getColor());
		oldCircle.setInnerColor(originalCircle.getInnerColor());
		try {
			oldCircle.setRadius(originalCircle.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldCircle.setSelected(originalCircle.isSelected());
		
	}


	@Override
	public String toString() {
		return "Update Circle: (newCircle = " + newCircle + ", oldCircle=" + oldCircle + ", originalCircle="
				+ originalCircle + ")";
	}

	
}
