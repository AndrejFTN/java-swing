package drawing;

public class MoveUpCommand implements Command{

	
	private PnlDrawing pnlDrawing;
	private int index;
	
	public MoveUpCommand(PnlDrawing pnlDrawing, int index) {
		this.pnlDrawing = pnlDrawing;
		this.index = index;
	}
	
	
	@Override
	public void Do() {
		pnlDrawing.swapShapes(index, index + 1); 
		
	}

	@Override
	public void Undo() {
		pnlDrawing.swapShapes(index, index + 1); 
	}


	@Override
	public String toString() {
		return "MoveUp," + index;
	}

	
}
