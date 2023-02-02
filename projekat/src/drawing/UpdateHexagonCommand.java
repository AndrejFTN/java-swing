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
		oldHexagon.setSelected(newHexagon.isSelected());
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
		oldHexagon.setSelected(originalHexagon.isSelected());
		
	}

	@Override
	public String toString() {
		return "Update Hexagon: (newHexagon=" + newHexagon + ", oldHexagon=" + oldHexagon + ", originalHexagon="
				+ originalHexagon + ")";
	}
	
	
}
