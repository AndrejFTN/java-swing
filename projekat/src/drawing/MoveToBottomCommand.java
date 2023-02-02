package drawing;

import geometry.Shape;

public class MoveToBottomCommand implements Command {

	private Shape shape;
	private PnlDrawing pnlDrawing;
	private int index;
	
	public MoveToBottomCommand(PnlDrawing pnlDrawing, int index) {
		this.pnlDrawing = pnlDrawing;
		this.index = index;
	}
	
	@Override
	public void Do() {
		shape = pnlDrawing.getShape(index); 
		pnlDrawing.removeOnIndex(index);
		pnlDrawing.insertOnIndex(0, shape);
	}

	@Override
	public void Undo() {
		pnlDrawing.removeShape(shape);
		pnlDrawing.insertOnIndex(index, shape);
		
	}

	@Override
	public String toString() {
		return "MoveToBottom," + index;
	}
	
}
