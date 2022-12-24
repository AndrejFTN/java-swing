package drawing;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import geometry.Circle;
import geometry.Donut;
import geometry.Hexagon;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingController {
	
	private PnlDrawing pnlDrawing;
	
	private DrawingModel drawingModel = new DrawingModel();
	private FrmDrawing frmDrawing;
	
	public DrawingController(PnlDrawing pnlDrawing, FrmDrawing frmDrawing) {
		this.pnlDrawing = pnlDrawing;
		this.frmDrawing = frmDrawing;
	}
	
	public void redo() {
		if(drawingModel.getCurrentCommand() + 1 <= drawingModel.getCommands().size() - 1) {
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);;
			drawingModel.getCommands().get(drawingModel.getCurrentCommand()).Do();	
		}
		
	}
	
	public void undo() {
		if(drawingModel.getCurrentCommand() - 1 >= -1) {
			drawingModel.getCommands().get(drawingModel.getCurrentCommand()).Undo();
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() - 1);;	
		}
	}
	
	public void drawPoint(Point point) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setPoint(point);
		dlgPoint.setColors(drawingModel.getEdgeColor());
		dlgPoint.setVisible(true);
		if(dlgPoint.getPoint() != null) {
			AddCommand addCommand = new AddCommand(dlgPoint.getPoint(), pnlDrawing);
			drawingModel.getCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);;
			addCommand.Do();
			frmDrawing.addLog(addCommand.toString());
			return;
		}
	}
	
	public void drawHexagon(Point point) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setPoint(point);
		dlgHexagon.setColors(drawingModel.getEdgeColor(), drawingModel.getInnerColor());
		dlgHexagon.setVisible(true);
		if(dlgHexagon.getHexagon() != null) {
				AddCommand addCommand = new AddCommand(dlgHexagon.getHexagon(), pnlDrawing);
				drawingModel.getCommands().add(addCommand);
				drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
				addCommand.Do();
				return;
			
		}
	}
	
	public void drawLine(Point point) {
		if(drawingModel.isLineWaitingForEndPoint()) {
			
			DlgLine dlgLine = new DlgLine();
			Line line = new Line(drawingModel.getStartPoint(),point);
			dlgLine.setLine(line);
			dlgLine.setColors(drawingModel.getEdgeColor());
			dlgLine.setVisible(true);
			if(dlgLine.getLine()!= null) {
				AddCommand addCommand = new AddCommand(dlgLine.getLine(), pnlDrawing);
				drawingModel.getCommands().add(addCommand);
				drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
				addCommand.Do();
				drawingModel.setLineWaitingForEndPoint(false);
				return;
			}					
		}
		drawingModel.setStartPoint(point);
		drawingModel.setLineWaitingForEndPoint(true);
		return;
	}
	
	public void drawRectangle(Point point) {
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.setPoint(point);
		dlgRectangle.setColors(drawingModel.getEdgeColor(), drawingModel.getInnerColor());
		dlgRectangle.setVisible(true);
		
		if(dlgRectangle.getRectangle() != null) {
			AddCommand addCommand = new AddCommand(dlgRectangle.getRectangle(), pnlDrawing);
			drawingModel.getCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			addCommand.Do();
			return;
		}
	}
	
	public void drawCircle(Point point) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setPoint(point);
		dlgCircle.setColors(drawingModel.getEdgeColor(), drawingModel.getInnerColor());
		dlgCircle.setVisible(true);
		
		if(dlgCircle.getCircle() != null) {
			AddCommand addCommand = new AddCommand(dlgCircle.getCircle(), pnlDrawing);
			drawingModel.getCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			addCommand.Do();
			return;
		}
		return;
	}
	
	public void drawDonut(Point point) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setPoint(point);
		dlgDonut.setColors(drawingModel.getEdgeColor(), drawingModel.getInnerColor());
		dlgDonut.setVisible(true);
		
		if(dlgDonut.getDonut() != null) {
			AddCommand addCommand = new AddCommand(dlgDonut.getDonut(), pnlDrawing);
			drawingModel.getCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			addCommand.Do();
			return;
		}
		return;
	}
	
	public void instanceOfPoint(Shape shape1, int index) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setPoint((Point)shape1);
		dlgPoint.setVisible(true);
		
		if(dlgPoint.getPoint() != null) {
			pnlDrawing.setShape(index, dlgPoint.getPoint());
			pnlDrawing.repaint();
		}
	}
	public void instanceOfLine(Shape shape1, int index) {
		DlgLine dlgLine = new DlgLine();
		dlgLine.setLine((Line)shape1);
		dlgLine.setVisible(true);
		
		if(dlgLine.getLine() != null) {
			pnlDrawing.setShape(index, dlgLine.getLine());
			pnlDrawing.repaint();
		}
	}
	
	public void instanceOfRectangle(Shape shape1, int index) {
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.setRectangle((Rectangle)shape1);
		dlgRectangle.setVisible(true);
		
		if(dlgRectangle.getRectangle() != null) {
			pnlDrawing.setShape(index, dlgRectangle.getRectangle());
			pnlDrawing.repaint();
		}
	}
	
	public void instanceOfDonut(Shape shape1, int index) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setDonut((Donut)shape1);
		dlgDonut.setVisible(true);
		
		if(dlgDonut.getDonut() != null) {
			pnlDrawing.setShape(index, dlgDonut.getDonut());
			pnlDrawing.repaint();
		}
	}
	
	public void instanceOfCircle(Shape shape1, int index) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setCircle((Circle)shape1);
		dlgCircle.setVisible(true);
		
		if(dlgCircle.getCircle() != null) {
			pnlDrawing.setShape(index, dlgCircle.getCircle());
			pnlDrawing.repaint();
		}
	}
	
	public void instanceOfHexagon(Shape shape1, int index) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setHexagon((Hexagon)shape1);
		dlgHexagon.setVisible(true);
		
		if(dlgHexagon.getHexagon() != null) {
			pnlDrawing.setShape(index, dlgHexagon.getHexagon());
			pnlDrawing.repaint();
		}
	
	}
	
	public void delete() {
		if (pnlDrawing.isEmpty()) return;
		if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) pnlDrawing.removeSelected();
	}
	
	public Point makeMouseClick(int x, int y) {
		return new Point(x, y);
	}
	
}
