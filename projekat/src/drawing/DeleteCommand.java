package drawing;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;
import mvc.PnlDrawing;

public class DeleteCommand implements Command{

	private ArrayList<Shape> shapes;
	private DrawingModel drawingModel;
	private ArrayList<Integer> indexList = new ArrayList<Integer>();
	
	public DeleteCommand(ArrayList<Shape> shapes, DrawingModel drawingModel) {
		this.shapes = shapes;
		this.drawingModel = drawingModel;
	}
	
	
	@Override
	public void Do() {
		for(Shape shape : shapes) {
			indexList.add(drawingModel.getIndex(shape));
		}
		
		for(Shape shape : shapes) {
			drawingModel.removeShape(shape);
		}
	}

	@Override
	public void Undo() {
		for(int i = 0; i < indexList.size(); i++) {
			drawingModel.insertOnIndex(i, shapes.get(i));
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
