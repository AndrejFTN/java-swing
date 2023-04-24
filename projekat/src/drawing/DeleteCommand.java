package drawing;

import java.util.ArrayList;

import geometry.Shape;

public class DeleteCommand implements Command{

	private ArrayList<Shape> shapes;
	private PnlDrawing pnlDrawing;
	private ArrayList<Integer> indexList = new ArrayList<Integer>();
	
	public DeleteCommand(ArrayList<Shape> shapes, PnlDrawing pnlDrawing) {
		this.shapes = shapes;
		this.pnlDrawing = pnlDrawing;
	}
	
	
	@Override
	public void Do() {
		for(Shape shape : shapes) {
			indexList.add(pnlDrawing.getIndex(shape));
		}
		
		for(Shape shape : shapes) {
			pnlDrawing.removeShape(shape);
		}
	}

	@Override
	public void Undo() {
		for(int i = 0; i < indexList.size(); i++) {
			pnlDrawing.insertOnIndex(i, shapes.get(i));
		}
		
	}
	
	public String toString() {
		String placeholder = "";
		for(Shape shape : shapes) {
			placeholder += shape.toString() + "|";
		}
		placeholder = placeholder.substring(0, placeholder.length()-1);
		return "Delete,|" + placeholder;
	}

}
