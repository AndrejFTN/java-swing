package mvc;

import java.awt.Color;
import java.util.ArrayList;

import drawing.Command;
import geometry.Point;
import geometry.Shape;

public class DrawingModel {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private int i;


	public DrawingModel() {
		
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
		
	}
	public Shape getShape(int index) {
		return shapes.get(index);
	}
	
	public void setShape(int index, Shape shape) {
		shapes.set(index, shape);
	}
	
	public void insertOnIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	public void removeShape(Shape shape) {
		shapes.remove(shape);
		
	}
	
	public void removeSelected() {
		shapes.removeIf(shape -> shape.isSelected());
	
	}
	public ArrayList getSelectedShapes() {
		ArrayList<Shape> helperList = new ArrayList<Shape>();
		for(int i = 0; i < shapes.size(); i ++) {
			if(shapes.get(i).isSelected()) {
				helperList.add(shapes.get(i));
			}
		}
		return helperList;
	}
	
	
	public int getSelected() {
		for (i = shapes.size()-1; i >= 0; i--) {
			if (shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return shapes.isEmpty();
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void swapShapes(int index1, int index2) {
		Shape placeHolder = shapes.get(index1);
		shapes.set(index1, shapes.get(index2));
		shapes.set(index2, placeHolder);
	}

	public void removeOnIndex(int index) {
		shapes.remove(index);
		
	}
	
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public int getIndex(Shape shape) {
		return this.shapes.indexOf(shape);
	}
	public void empty() {
		this.shapes.clear();
	}
}
