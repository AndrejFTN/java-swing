package drawing;

import java.util.ArrayList;

import geometry.Shape;

public class AddCommand extends Command {

	public AddCommand(Shape shape, PnlDrawing shapes) {
		super(shape, shapes);
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

	
	
}
