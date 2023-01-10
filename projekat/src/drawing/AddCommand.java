package drawing;

import java.util.ArrayList;

import geometry.Shape;

public class AddCommand implements Command {

	
	private Shape shape;
	private PnlDrawing shapes;
	
	
	public AddCommand(Shape shape, PnlDrawing shapes) {
		this.shape = shape;
		this.shapes = shapes;
	}

	@Override
	public void Do() {
		// TODO Auto-generated method stub
		shapes.addShape(shape);
	}

	@Override
	public void Undo() {
		// TODO Auto-generated method stub
		shapes.removeShape(shape);
	}

	@Override
	public String toString() {
		return "add_" + shape.toString();
	}
	
}
