package drawing;

import java.util.ArrayList;

import geometry.Shape;

public class UnSelectCommand implements Command {
	private ArrayList<Shape> shapes;
	
	public UnSelectCommand(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	@Override
	public void Do() {
		for(int i = 0; i < shapes.size(); i ++) {
			shapes.get(i).setSelected(false);
		}
	}

	@Override
	public void Undo() {
		for(int i = 0; i < shapes.size(); i ++) {
			shapes.get(i).setSelected(true);
		}
	}
	
	public String toString() {
		String placeholder = "";
		
		for(Shape shape : shapes) {
			placeholder += shape.toString() + "#";
			
		}
		
		placeholder = placeholder.substring(0, placeholder.length()-1);
		return "UnSelect,#" + placeholder;
	}
}
