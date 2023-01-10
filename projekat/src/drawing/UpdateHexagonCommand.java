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
		originalHexagon = oldHexagon;
		
		
		oldHexagon.setColor(newHexagon.getColor());
		oldHexagon.setInnerColor(newHexagon.getInnerColor());
		oldHexagon.setPoint(newHexagon.getCenter());
		//oldHexagon.setRadius(newHexagon.getRadius()); try catch u hexagonu, da li treba i ovde, tako i za circle
		oldHexagon.setSelected(newHexagon.isSelected());
	}

	@Override
	public void Undo() {
		oldHexagon.setColor(originalHexagon.getColor());
		oldHexagon.setInnerColor(originalHexagon.getInnerColor());
		oldHexagon.setPoint(originalHexagon.getCenter());
		//oldHexagon.setRadius(originalHexagon.getRadius()); try catch u hexagonu, da li treba i ovde, tako i za circle
		oldHexagon.setSelected(originalHexagon.isSelected());
		
	}

	@Override
	public String toString() {
		return "UpdateHexagonCommand [newHexagon=" + newHexagon + ", oldHexagon=" + oldHexagon + ", originalHexagon="
				+ originalHexagon + "]";
	}
	
	
}
