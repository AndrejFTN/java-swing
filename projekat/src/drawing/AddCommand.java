package drawing;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;
import mvc.PnlDrawing;

public class AddCommand implements Command {

	
	private Shape shape;
	private DrawingModel shapes;
	
	
	public AddCommand(Shape shape, DrawingModel shapes) {
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
		return "Add," + shape.toString();
	}
	
}
