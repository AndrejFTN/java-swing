package drawing;

import mvc.DrawingModel;
import mvc.PnlDrawing;

public class MoveDownCommand implements Command {
	
	private DrawingModel drawingModel;
	private int index;
	
	public MoveDownCommand(DrawingModel drawingModel, int index) {
		this.drawingModel = drawingModel;
		this.index = index;
	}
	
	
	@Override
	public void Do() {
		drawingModel.swapShapes(index, index - 1); 
		
	}

	@Override
	public void Undo() {
		drawingModel.swapShapes(index, index - 1); 
	}


	@Override
	public String toString() {
		return "MoveDown," + index;
	}

}
