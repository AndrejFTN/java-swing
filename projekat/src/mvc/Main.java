package mvc;

import java.awt.EventQueue;

public class Main {


	public static void main(String[] args) {
		DrawingModel drawingModel = new DrawingModel();
		
		FrmDrawing frame = new FrmDrawing();
		frame.getPnlDrawing().setModel(drawingModel);
		DrawingController drawingController = new DrawingController(frame, drawingModel);
		frame.setController(drawingController);
		drawingController.addObserver(frame);
		frame.setVisible(true);
	}


}
