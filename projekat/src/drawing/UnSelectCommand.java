package drawing;

import geometry.Shape;

public class UnSelectCommand implements Command {
	private Shape shape;
	
	public UnSelectCommand(Shape shape) {
		this.shape = shape;
	}
	
	@Override
	public void Do() {
		this.shape.setSelected(false);
		
	}

	@Override
	public void Undo() {
		this.shape.setSelected(true);
		
	}
}
