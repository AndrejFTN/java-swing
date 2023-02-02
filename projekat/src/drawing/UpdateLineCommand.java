package drawing;

import geometry.Line;
import geometry.Point;

public class UpdateLineCommand implements Command {
	
	//uraditi clone u line klasi, odraditi do i undo i u kontroleru zavrsiti
	// i proveriti da li treba sve tacek ovako

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
		oldLine.setSelected(originalLine.isSelected());
		
	}

	@Override
	public String toString() {
		return "Update Line: (newLine=" + newLine + ", oldLine=" + oldLine + ", originalLine=" + originalLine
				+ ")";
	}

	
}
