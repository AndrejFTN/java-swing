package drawing;

import geometry.Line;
import geometry.Point;

public class UpdateLineCommand implements Command {
	


	Line newLine, oldLine, originalLine;
	
	
	

	public UpdateLineCommand(Line oldLine, Line newLine) {		
		this.newLine = newLine;
		this.oldLine = oldLine;
	}

	@Override
	public void Do() {
		originalLine = oldLine.clone();
		
		oldLine.setStartPoint(newLine.getStartPoint());
		oldLine.setEndPoint(newLine.getEndPoint());
		oldLine.setColor(newLine.getColor());
		oldLine.setSelected(newLine.isSelected());
		
	}

	@Override
	public void Undo() {
		oldLine.setStartPoint(originalLine.getStartPoint());
		oldLine.setEndPoint(originalLine.getEndPoint());
		oldLine.setColor(originalLine.getColor());
		oldLine.setSelected(true);
		
	}

	@Override
	public String toString() {
		return "Update,Line,Startx:" + originalLine.getStartPoint().getX() + ",StartY:" + originalLine.getStartPoint().getY() + ",endX:" + originalLine.getEndPoint().getX() + 
				",endY:" + originalLine.getEndPoint().getY() + ",selected:" + originalLine.isSelected() +	",color:" + originalLine.getColor().getRGB()
				+ ",NewLine Startx:" + newLine.getStartPoint().getX() + ",StartY:" + newLine.getStartPoint().getY() + ",endX:" + newLine.getEndPoint().getX() + 
				",endY:" + newLine.getEndPoint().getY() + ",selected:" + newLine.isSelected() +	",color:" + newLine.getColor().getRGB();
	}

	
}
