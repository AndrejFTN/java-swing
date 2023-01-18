package drawing;

import geometry.Shape;

public class MoveToFrontCommand implements Command{

	private Shape shape;
	private PnlDrawing pnlDrawing;
	private int index;
	
	public MoveToFrontCommand(PnlDrawing pnlDrawing, int index) {
		this.pnlDrawing = pnlDrawing;
		this.index = index;
	}
	
	@Override
	public void Do() {
		shape = pnlDrawing.getShape(index); 
		pnlDrawing.removeOnIndex(index);
		pnlDrawing.addShape(shape);
	}

	@Override
	public void Undo() {
		pnlDrawing.removeShape(shape);
		pnlDrawing.insertOnIndex(index, shape);
		
	}

	@Override
	public String toString() {
		return "MoveToFrontCommand [pnlDrawing=" + pnlDrawing + ", index=" + index + "]";
	}
	
	
}
