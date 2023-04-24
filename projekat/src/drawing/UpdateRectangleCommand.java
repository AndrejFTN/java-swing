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
		oldRectangle.setSelected(true);
		oldRectangle.setInnerColor(newRectangle.getInnerColor());
	}

	@Override
	public void Undo() {
		oldRectangle.setUpperLeftPoint(originalRectangle.getUpperLeftPoint());
		oldRectangle.setHeight(originalRectangle.getHeight());
		oldRectangle.setWidth(originalRectangle.getWidth());
		oldRectangle.setColor(originalRectangle.getColor());
		oldRectangle.setSelected(true);
		oldRectangle.setInnerColor(originalRectangle.getInnerColor());
		
	}

	@Override
	public String toString() {
		return "Update,Rectangle,x:" + originalRectangle.getUpperLeftPoint().getX() + ",y:" + originalRectangle.getUpperLeftPoint().getY() + ",height:" + originalRectangle.getHeight() + 
				",width:" + originalRectangle.getWidth() + ",color:" + originalRectangle.getColor().getRGB() + ",innerColor:" + originalRectangle.getInnerColor().getRGB() + ",selected:" + originalRectangle.isSelected()
				+ ",NewRectangle x:" + newRectangle.getUpperLeftPoint().getX() + ",y:" + newRectangle.getUpperLeftPoint().getY() + ",height:" + newRectangle.getHeight() + 
				",width:" + newRectangle.getWidth() + ",color:" + newRectangle.getColor().getRGB() + ",innerColor:" + newRectangle.getInnerColor().getRGB() + ",selected:" + newRectangle.isSelected();
	}

}
