package drawing;

import geometry.Shape;

public class SelectCommand implements Command {

	private Shape shape;
	
	public SelectCommand(Shape shape) {
		this.shape = shape;
	}
	
	@Override
	public void Do() {
		this.shape.setSelected(true);
		
	}

	@Override
	public void Undo() {
		this.shape.setSelected(false);
		
	}
	
}
