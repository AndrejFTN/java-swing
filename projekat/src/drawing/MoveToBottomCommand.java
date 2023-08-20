package drawing;

import geometry.Shape;
import mvc.DrawingModel;
import mvc.PnlDrawing;

public class MoveToBottomCommand implements Command {

	private Shape shape;
	private DrawingModel drawingModel;
	private int index;
	
	public MoveToBottomCommand(DrawingModel drawingModel, int index) {
		this.drawingModel = drawingModel;
		this.index = index;
	}
	
	@Override
	public void Do() {
		shape = drawingModel.getShape(index); 
		drawingModel.removeOnIndex(index);
		drawingModel.insertOnIndex(0, shape);
	}

	@Override
	public void Undo() {
		drawingModel.removeShape(shape);
		drawingModel.insertOnIndex(index, shape);
		
	}

	@Override
	public String toString() {
		return "MoveToBottom," + index;
	}
	
}
