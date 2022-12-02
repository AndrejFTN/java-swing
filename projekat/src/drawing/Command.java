package drawing;

import java.util.ArrayList;

import geometry.Shape;

public abstract class Command {

	protected Shape shape;
	protected PnlDrawing shapes;
	
	public Command(Shape shape, PnlDrawing shapes) {
		
		this.shape = shape;
		this.shapes = shapes;
		
	}
	
	public abstract void Do();
	
	public abstract void Undo();
	
}
