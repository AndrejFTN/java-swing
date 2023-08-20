package mvc;

import javax.swing.JPanel;

import geometry.Point;
import geometry.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class PnlDrawing extends JPanel {
	
	
	
	private DrawingModel drawingModel = null;
	/**
	 * Create the panel.
	 */
	public PnlDrawing() {
		setBackground(Color.WHITE);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = drawingModel.getShapes().iterator();
		while(it.hasNext())
			it.next().draw(g);
	}
	
	public void setModel(DrawingModel model) {
		this.drawingModel = model;
	}
}
