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
		oldCircle.setSelected(true);
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
		oldCircle.setSelected(true);
		
	}


	@Override
	public String toString() {
		return "Update,Circle,x:" + originalCircle.getCenter().getX() + ",y:" + originalCircle.getCenter().getY() + ",Radius:" + originalCircle.getRadius() + 
				",Selected:" + originalCircle.isSelected() + ",Color:" + originalCircle.getColor().getRGB() + ",InnerColor:" + originalCircle.getInnerColor().getRGB()
				+ ",NewCircle x:" + newCircle.getCenter().getX() + ",y:" + newCircle.getCenter().getY() + ",Radius:" + newCircle.getRadius() + 
				",Selected:" + newCircle.isSelected() + ",Color:" + newCircle.getColor().getRGB() + ",InnerColor:" + newCircle.getInnerColor().getRGB();
	}

	
}
