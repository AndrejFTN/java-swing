package drawing;

import geometry.Shape;
import mvc.DrawingModel;
import mvc.PnlDrawing;

public class MoveToFrontCommand implements Command{

	private Shape shape;
	private DrawingModel drawingModel;
	private int index;
	
	public MoveToFrontCommand(DrawingModel drawingModel, int index) {
		this.drawingModel = drawingModel;
		this.index = index;
	}
	
	@Override
	public void Do() {
		shape = drawingModel.getShape(index); 
		drawingModel.removeOnIndex(index);
		drawingModel.addShape(shape);
	}

	@Override
	public void Undo() {
		drawingModel.removeShape(shape);
		drawingModel.insertOnIndex(index, shape);
		
	}

	@Override
	public String toString() {
		return "MoveToFront," + index;
	}
	
	
}
