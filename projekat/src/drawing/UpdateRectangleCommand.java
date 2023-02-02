package drawing;

import geometry.Rectangle;

public class UpdateRectangleCommand implements Command {

	Rectangle oldRectangle;
	Rectangle newRectangle;
	Rectangle originalRectangle;
	
	
	
	
	public UpdateRectangleCommand(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void Do() {
		originalRectangle = oldRectangle.clone();
		
		oldRectangle.setUpperLeftPoint(newRectangle.getUpperLeftPoint());
		oldRectangle.setHeight(newRectangle.getHeight());
		oldRectangle.setWidth(newRectangle.getWidth());
		oldRectangle.setColor(newRectangle.getColor());
		oldRectangle.setSelected(newRectangle.isSelected());
		oldRectangle.setInnerColor(newRectangle.getInnerColor());
	}

	@Override
	public void Undo() {
		oldRectangle.setUpperLeftPoint(originalRectangle.getUpperLeftPoint());
		oldRectangle.setHeight(originalRectangle.getHeight());
		oldRectangle.setWidth(originalRectangle.getWidth());
		oldRectangle.setColor(originalRectangle.getColor());
		oldRectangle.setSelected(originalRectangle.isSelected());
		oldRectangle.setInnerColor(originalRectangle.getInnerColor());
		
	}

	@Override
	public String toString() {
		return "Update Rectangle: (oldRectangle=" + oldRectangle + ", newRectangle=" + newRectangle
				+ ", originalRectangle=" + originalRectangle + ")";
	}

}
