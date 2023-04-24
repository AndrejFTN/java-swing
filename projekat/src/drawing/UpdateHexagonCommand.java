package drawing;

import geometry.Hexagon;

public class UpdateHexagonCommand implements Command{

	Hexagon newHexagon, oldHexagon, originalHexagon;
	
	public UpdateHexagonCommand(Hexagon oldHexagon, Hexagon newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}
	
	@Override
	public void Do() {
		originalHexagon = oldHexagon.clone();
		
		
		oldHexagon.setColor(newHexagon.getColor());
		oldHexagon.setInnerColor(newHexagon.getInnerColor());
		oldHexagon.setPoint(newHexagon.getCenter());
		try {
			oldHexagon.setRadius(newHexagon.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		oldHexagon.setSelected(true);
	}

	@Override
	public void Undo() {
		oldHexagon.setColor(originalHexagon.getColor());
		oldHexagon.setInnerColor(originalHexagon.getInnerColor());
		oldHexagon.setPoint(originalHexagon.getCenter());
		try {
			oldHexagon.setRadius(originalHexagon.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		oldHexagon.setSelected(true);
		
	}

	@Override
	public String toString() {
		return "Update,Hexagon,x:" + originalHexagon.getCenter().getX() + ",y:" + originalHexagon.getCenter().getY() + ",radius:" + originalHexagon.getRadius() + 
				",selected:" + originalHexagon.isSelected() +	",color:" + originalHexagon.getColor().getRGB() + ",innerColor:" + originalHexagon.getInnerColor().getRGB()
				+ "NewHexagon x:" + newHexagon.getCenter().getX() + ",y:" + newHexagon.getCenter().getY() + ",radius:" + newHexagon.getRadius() + 
				",selected:" + newHexagon.isSelected() +	",color:" + newHexagon.getColor().getRGB() + ",innerColor:" + newHexagon.getInnerColor().getRGB();
	}
	
	
}
