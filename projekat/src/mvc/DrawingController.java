package mvc;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import drawing.AddCommand;
import drawing.Command;
import drawing.DeleteCommand;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import drawing.MoveDownCommand;
import drawing.MoveToBottomCommand;
import drawing.MoveToFrontCommand;
import drawing.MoveUpCommand;
import drawing.ObserverMessage;
import drawing.SelectCommand;
import drawing.UnSelectCommand;
import drawing.UpdateCircleCommand;
import drawing.UpdateDonutCommand;
import drawing.UpdateHexagonCommand;
import drawing.UpdateLineCommand;
import drawing.UpdatePointCommand;
import drawing.UpdateRectangleCommand;
import geometry.Circle;
import geometry.Donut;
import geometry.Hexagon;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import strategy.SaveLog;
import strategy.SaveSerialized;
import strategy.SaveStrategy;

public class DrawingController extends Observable {



	private SaveStrategy saveStrategy = new SaveStrategy(null);
	private String filePath = "";

	private DrawingModel drawingModel;
	private FrmDrawing frmDrawing;

	private ArrayList<String> listLogCommand = new ArrayList<String>();

	public DrawingController(FrmDrawing frmDrawing, DrawingModel drawingModel) {
		this.drawingModel = drawingModel;
		this.frmDrawing = frmDrawing;
	}

	public void redo() {
		Command command = drawingModel.getRedoCommands().get(drawingModel.getRedoCommands().size() - 1);
		command.Do();
		drawingModel.getRedoCommands().remove(drawingModel.getRedoCommands().size() - 1);
		drawingModel.getUndoCommands().add(command);
		frmDrawing.getPnlDrawing().repaint();
		informObservers();
		frmDrawing.addLog("Redo");

	}

	public void undo() {
		Command command = drawingModel.getUndoCommands().get(drawingModel.getUndoCommands().size() - 1);
		command.Undo();
		drawingModel.getUndoCommands().remove(drawingModel.getUndoCommands().size() - 1);
		drawingModel.getRedoCommands().add(command);
		frmDrawing.getPnlDrawing().repaint();
		informObservers();
		frmDrawing.addLog("Undo");
	}

	public void drawPoint(Point point) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setPoint(point);
		dlgPoint.setColors(frmDrawing.getGlobalOutterColor());
		dlgPoint.setVisible(true);
		if (dlgPoint.getPoint() != null) {
			AddCommand addCommand = new AddCommand(dlgPoint.getPoint(), drawingModel);
			drawingModel.getUndoCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			;
			addCommand.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(addCommand.toString());
			return;
		}
	}

	public void drawHexagon(Point point) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setPoint(point);
		dlgHexagon.setColors(frmDrawing.getGlobalOutterColor(), frmDrawing.getGlobalInnerColor());
		dlgHexagon.setVisible(true);
		if (dlgHexagon.getHexagon() != null) {
			AddCommand addCommand = new AddCommand(dlgHexagon.getHexagon(), drawingModel);
			drawingModel.getUndoCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			addCommand.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(addCommand.toString());
			return;

		}
	}

	public void drawLine(Point point) {
		if (drawingModel.isLineWaitingForEndPoint()) {

			DlgLine dlgLine = new DlgLine();
			Line line = new Line(drawingModel.getStartPoint(), point);
			dlgLine.setLine(line);
			dlgLine.setColors(frmDrawing.getGlobalOutterColor());
			dlgLine.setVisible(true);
			if (dlgLine.getLine() != null) {
				AddCommand addCommand = new AddCommand(dlgLine.getLine(), drawingModel);
				drawingModel.getUndoCommands().add(addCommand);
				drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
				addCommand.Do();
				informObservers();
				frmDrawing.addLog(addCommand.toString());
				drawingModel.setLineWaitingForEndPoint(false);
				drawingModel.getRedoCommands().clear();
				informObservers();
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
		dlgRectangle.setColors(frmDrawing.getGlobalOutterColor(), frmDrawing.getGlobalInnerColor());
		dlgRectangle.setVisible(true);

		if (dlgRectangle.getRectangle() != null) {
			AddCommand addCommand = new AddCommand(dlgRectangle.getRectangle(), drawingModel);
			drawingModel.getUndoCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			addCommand.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(addCommand.toString());

			return;
		}
	}

	public void drawCircle(Point point) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setPoint(point);
		dlgCircle.setColors(frmDrawing.getGlobalOutterColor(), frmDrawing.getGlobalInnerColor());
		dlgCircle.setVisible(true);

		if (dlgCircle.getCircle() != null) {
			AddCommand addCommand = new AddCommand(dlgCircle.getCircle(), drawingModel);
			drawingModel.getUndoCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			addCommand.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(addCommand.toString());

			return;
		}
		return;
	}

	public void drawDonut(Point point) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setPoint(point);
		dlgDonut.setColors(frmDrawing.getGlobalOutterColor(), frmDrawing.getGlobalInnerColor());
		dlgDonut.setVisible(true);

		if (dlgDonut.getDonut() != null) {
			AddCommand addCommand = new AddCommand(dlgDonut.getDonut(), drawingModel);
			drawingModel.getUndoCommands().add(addCommand);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			addCommand.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(addCommand.toString());

			return;
		}
		return;
	}

	public void instanceOfPoint(Shape shape1, int index) {
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setPoint((Point) shape1);
		dlgPoint.setVisible(true);

		if (dlgPoint.getPoint() != null) {
			UpdatePointCommand cmd = new UpdatePointCommand((Point) shape1, dlgPoint.getPoint());
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			// pnlDrawing.setShape(index, dlgPoint.getPoint());

			frmDrawing.getPnlDrawing().repaint();
		}
	}

	public void instanceOfLine(Shape shape1, int index) {
		DlgLine dlgLine = new DlgLine();
		dlgLine.setLine((Line) shape1);
		dlgLine.setVisible(true);

		if (dlgLine.getLine() != null) {
			UpdateLineCommand cmd = new UpdateLineCommand((Line) shape1, dlgLine.getLine());
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			// pnlDrawing.setShape(index, dlgLine.getLine());

			frmDrawing.getPnlDrawing().repaint();
		}
	}

	public void instanceOfRectangle(Shape shape1, int index) {
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.setRectangle((Rectangle) shape1);
		dlgRectangle.setVisible(true);

		if (dlgRectangle.getRectangle() != null) {
			UpdateRectangleCommand cmd = new UpdateRectangleCommand((Rectangle) shape1, dlgRectangle.getRectangle());
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			// pnlDrawing.setShape(index, dlgRectangle.getRectangle());

			frmDrawing.getPnlDrawing().repaint();
		}
	}

	public void instanceOfDonut(Shape shape1, int index) {
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setDonut((Donut) shape1);
		dlgDonut.setVisible(true);

		if (dlgDonut.getDonut() != null) {
			UpdateDonutCommand cmd = new UpdateDonutCommand((Donut) shape1, dlgDonut.getDonut());
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			// pnlDrawing.setShape(index, dlgDonut.getDonut());

			frmDrawing.getPnlDrawing().repaint();
		}
	}

	public void instanceOfCircle(Shape shape1, int index) {
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setCircle((Circle) shape1);
		dlgCircle.setVisible(true);

		if (dlgCircle.getCircle() != null) {
			UpdateCircleCommand cmd = new UpdateCircleCommand((Circle) shape1, dlgCircle.getCircle());
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			// pnlDrawing.setShape(index, dlgCircle.getCircle());

			frmDrawing.getPnlDrawing().repaint();
		}
	}

	public void instanceOfHexagon(Shape shape1, int index) {
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setHexagon((Hexagon) shape1);
		dlgHexagon.setVisible(true);

		if (dlgHexagon.getHexagon() != null) {
			UpdateHexagonCommand cmd = new UpdateHexagonCommand((Hexagon) shape1, dlgHexagon.getHexagon());
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);
			// pnlDrawing.setShape(index, dlgHexagon.getHexagon());

			frmDrawing.getPnlDrawing().repaint();
		}

	}

	public void delete() {
		if (drawingModel.isEmpty())
			return;
		if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			DeleteCommand cmd = new DeleteCommand(drawingModel.getSelectedShapes(), drawingModel);
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();

		}
	}

	public Point makeMouseClick(int x, int y) {
		return new Point(x, y);
	}

	public void editShapes() {
		int index = drawingModel.getSelected();
		if (index == -1)
			return;

		Shape shape = drawingModel.getShape(index);

		if (shape instanceof Point) {
			instanceOfPoint(shape, index);

		} else if (shape instanceof Line) {
			instanceOfLine(shape, index);

		} else if (shape instanceof Rectangle) {
			instanceOfRectangle(shape, index);

		} else if (shape instanceof Donut) {
			instanceOfDonut(shape, index);

		} else if (shape instanceof Circle) {
			instanceOfCircle(shape, index);

		} else if (shape instanceof Hexagon) {
			instanceOfHexagon(shape, index);

		}
	}

	public void moveDown() {
		MoveDownCommand cmd = new MoveDownCommand(drawingModel, drawingModel.getSelected());
		cmd.Do();
		drawingModel.getRedoCommands().clear();
		informObservers();
		frmDrawing.addLog(cmd.toString());
		drawingModel.getUndoCommands().add(cmd);
		frmDrawing.getPnlDrawing().repaint();

	}

	public void moveToFront() {
		MoveToFrontCommand cmd = new MoveToFrontCommand(drawingModel, drawingModel.getSelected());
		cmd.Do();
		drawingModel.getRedoCommands().clear();
		informObservers();
		frmDrawing.addLog(cmd.toString());
		drawingModel.getUndoCommands().add(cmd);
		frmDrawing.getPnlDrawing().repaint();

	}

	public void moveUp() {
		MoveUpCommand cmd = new MoveUpCommand(drawingModel, drawingModel.getSelected());
		cmd.Do();
		drawingModel.getRedoCommands().clear();
		informObservers();
		frmDrawing.addLog(cmd.toString());
		drawingModel.getUndoCommands().add(cmd);
		drawingModel.setCurrentCommand(drawingModel.getCurrentCommand() + 1);// ne treba vise svugde
		frmDrawing.getPnlDrawing().repaint();

	}

	public void moveToBottom() {
		MoveToBottomCommand cmd = new MoveToBottomCommand(drawingModel, drawingModel.getSelected());
		cmd.Do();
		drawingModel.getRedoCommands().clear();
		informObservers();
		frmDrawing.addLog(cmd.toString());
		drawingModel.getUndoCommands().add(cmd);
		frmDrawing.getPnlDrawing().repaint();

	}

	public void mouseClicked(Point mouseClick) {
		ArrayList<Shape> shapes = drawingModel.getShapes();
		Shape shapeContainingClickPoint = null;
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).contains(mouseClick.getX(), mouseClick.getY())) {
				shapeContainingClickPoint = shapes.get(i);
				break;
			}
		}
		if (shapeContainingClickPoint != null) {
			if (shapeContainingClickPoint.isSelected()) {
				ArrayList<Shape> list = new ArrayList<Shape>();
				list.add(shapeContainingClickPoint);
				UnSelectCommand cmd = new UnSelectCommand(list);
				cmd.Do();
				drawingModel.getRedoCommands().clear();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else {
				SelectCommand cmd = new SelectCommand(shapeContainingClickPoint);
				cmd.Do();
				drawingModel.getRedoCommands().clear();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			}
		} else {
			deSelectAll();
		}
	}

	public void deSelectAll() {
		if (drawingModel.getSelectedShapes().size() > 0) {
			UnSelectCommand cmd = new UnSelectCommand(drawingModel.getSelectedShapes());
			cmd.Do();
			drawingModel.getRedoCommands().clear();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();

		}
	}

	public void informObservers() {
		ObserverMessage observerMessage = new ObserverMessage();
		observerMessage.setEnableDelete(drawingModel.getSelectedShapes().size() > 0);
		observerMessage.setEnableEdit(drawingModel.getSelectedShapes().size() == 1);
		observerMessage.setEnableUndo(drawingModel.getUndoCommands().size() > 0);
		observerMessage.setEnableRedo(drawingModel.getRedoCommands().size() > 0);
		observerMessage.setEnableMoveUp(drawingModel.getSelectedShapes().size() == 1
				&& drawingModel.getSelected() != drawingModel.getShapes().size() - 1);
		observerMessage.setEnableMoveDown(drawingModel.getSelectedShapes().size() == 1 && drawingModel.getSelected() != 0);
		observerMessage.setEnableMoveToFront(drawingModel.getSelectedShapes().size() == 1
				&& drawingModel.getSelected() != drawingModel.getShapes().size() - 1);
		observerMessage
				.setEnableMoveToBottom(drawingModel.getSelectedShapes().size() == 1 && drawingModel.getSelected() != 0);
		this.setChanged();
		this.notifyObservers(observerMessage);
	}

	public void writeSerialized() {
		this.saveStrategy.setSaver(new SaveSerialized(drawingModel.getShapes()));
		this.filePath = saveStrategy.saveAs();
	}

	public void save() {
		if (filePath == null) {
			JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
		}
		this.saveStrategy.save(this.filePath);
	}

	public void readSerialized() {
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Choose file");
		int response = jFileChooser.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			this.filePath = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filePath));
				ArrayList<Shape> helper = (ArrayList<Shape>) stream.readObject();
				drawingModel.setShapes(helper);
				frmDrawing.repaint();
				drawingModel.getUndoCommands().clear();
				drawingModel.getRedoCommands().clear();
				informObservers();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void writeLog() {
		this.saveStrategy.setSaver(new SaveLog(frmDrawing.getDefaultListModel()));
		this.filePath = saveStrategy.saveAs();
	}

	public void readLog() {
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Choose file");
		int response = jFileChooser.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			this.filePath = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					listLogCommand.add(line);
				}
				drawingModel.empty();
				frmDrawing.getPnlDrawing().repaint();
				frmDrawing.getDefaultListModel().clear();
				informObservers();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void loadLog() {
		if (listLogCommand.size() == 0) {
			JOptionPane.showMessageDialog(null, "Zero commands to load", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String log = listLogCommand.get(0);
		if (log.equals("Undo")) {
			Command command = drawingModel.getUndoCommands().get(drawingModel.getUndoCommands().size() - 1);
			command.Undo();
			drawingModel.getUndoCommands().remove(drawingModel.getUndoCommands().size() - 1);
			drawingModel.getRedoCommands().add(command);
			frmDrawing.getPnlDrawing().repaint();
			frmDrawing.addLog("Undo");
			informObservers();
			listLogCommand.remove(0);
			return;
		}
		if (log.equals("Redo")) {
			Command command = drawingModel.getRedoCommands().get(drawingModel.getRedoCommands().size() - 1);
			command.Do();
			drawingModel.getRedoCommands().remove(drawingModel.getRedoCommands().size() - 1);
			drawingModel.getUndoCommands().add(command);
			frmDrawing.getPnlDrawing().repaint();
			frmDrawing.addLog("Redo");
			informObservers();
			listLogCommand.remove(0);
			return;
		}
		String[] logParts = log.split(",");
		String commandName = logParts[0];
		if (commandName.equals("Add")) {
			String shapeName = logParts[1];
			if (shapeName.equals("Rectangle")) {
				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String heightPart = logParts[4];
				String[] heightShape = heightPart.split(":");
				int height = Integer.parseInt(heightShape[1]);

				String widthPart = logParts[5];
				String[] widthShape = widthPart.split(":");
				int width = Integer.parseInt(widthShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				String selectedPart = logParts[8];
				String[] selectShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectShape[1]);

				Rectangle rectangle = new Rectangle(new Point(x, y), width, height, select, color, innerColor);
				AddCommand cmd = new AddCommand(rectangle, drawingModel);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else if (shapeName.equals("Circle")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String selectedPart = logParts[5];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				Circle circle = new Circle(new Point(x, y), radius, select, color, innerColor);
				AddCommand cmd = new AddCommand(circle, drawingModel);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else if (shapeName.equals("Donut")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String innerRadiusPart = logParts[5];
				String[] innerRadiusShape = innerRadiusPart.split(":");
				int innerRadius = Integer.parseInt(innerRadiusShape[1]);

				String selectedPart = logParts[6];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[7];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[8];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				Donut donut = new Donut(new Point(x, y), radius, innerRadius, select, color, innerColor);
				AddCommand cmd = new AddCommand(donut, drawingModel);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else if (shapeName.equals("Hexagon")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String selectedPart = logParts[5];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				Hexagon hexagon = new Hexagon(new Point(x, y), radius, select, color, innerColor);
				AddCommand cmd = new AddCommand(hexagon, drawingModel);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else if (shapeName.equals("Line")) {

				String xStartPart = logParts[2];
				String[] xCoordinate = xStartPart.split(":");
				int startX = Integer.parseInt(xCoordinate[1]);

				String yStartPart = logParts[3];
				String[] yCoordinate = yStartPart.split(":");
				int startY = Integer.parseInt(yCoordinate[1]);

				String xEndPart = logParts[4];
				String[] xEndCoordinate = xEndPart.split(":");
				int endX = Integer.parseInt(xEndCoordinate[1]);

				String yEndPart = logParts[5];
				String[] yEndCoordinate = yEndPart.split(":");
				int endY = Integer.parseInt(yEndCoordinate[1]);

				String selectedPart = logParts[6];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[7];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				Line line = new Line(new Point(startX, startY), new Point(endX, endY), select, color);
				AddCommand cmd = new AddCommand(line, drawingModel);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else if (shapeName.equals("Point")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String selectedPart = logParts[4];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[5];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				Point point = new Point(x, y, select, color);
				AddCommand cmd = new AddCommand(point, drawingModel);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			}

		} else if (commandName.equals("Update")) {
			String shapeName = logParts[1];
			if (shapeName.equals("Point")) {
				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String selectedPart = logParts[4];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[5];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String xPart2 = logParts[6];
				String[] xCoordinate2 = xPart2.split(":");
				int x2 = Integer.parseInt(xCoordinate2[1]);

				String yPart2 = logParts[7];
				String[] yCoordinate2 = yPart2.split(":");
				int y2 = Integer.parseInt(yCoordinate2[1]);

				String selectedPart2 = logParts[8];
				String[] selectedShape2 = selectedPart2.split(":");
				boolean select2 = Boolean.parseBoolean(selectedShape2[1]);

				String colorPart2 = logParts[9];
				String[] colorShape2 = colorPart2.split(":");
				Color color2 = new Color(Integer.parseInt(colorShape2[1]));

				Point point = new Point(x, y, select, color);
				Point point2 = new Point(x2, y2, select2, color2);

				Point placeholder = null;
				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Point && ((Point) shape).equals(point)) {
						placeholder = (Point) shape;
					}
				}
				UpdatePointCommand cmd = new UpdatePointCommand(placeholder, point2);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Rectangle")) {
				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String heightPart = logParts[4];
				String[] heightShape = heightPart.split(":");
				int height = Integer.parseInt(heightShape[1]);

				String widthPart = logParts[5];
				String[] widthShape = widthPart.split(":");
				int width = Integer.parseInt(widthShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				String selectedPart = logParts[8];
				String[] selectShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectShape[1]);

				String xPart2 = logParts[9];
				String[] xCoordinate2 = xPart2.split(":");
				int x2 = Integer.parseInt(xCoordinate2[1]);

				String yPart2 = logParts[10];
				String[] yCoordinate2 = yPart2.split(":");
				int y2 = Integer.parseInt(yCoordinate2[1]);

				String heightPart2 = logParts[11];
				String[] heightShape2 = heightPart2.split(":");
				int height2 = Integer.parseInt(heightShape2[1]);

				String widthPart2 = logParts[12];
				String[] widthShape2 = widthPart2.split(":");
				int width2 = Integer.parseInt(widthShape2[1]);

				String colorPart2 = logParts[13];
				String[] colorShape2 = colorPart2.split(":");
				Color color2 = new Color(Integer.parseInt(colorShape2[1]));

				String innerColorPart2 = logParts[14];
				String[] innerColorShape2 = innerColorPart2.split(":");
				Color innerColor2 = new Color(Integer.parseInt(innerColorShape2[1]));

				String selectedPart2 = logParts[15];
				String[] selectShape2 = selectedPart2.split(":");
				boolean select2 = Boolean.parseBoolean(selectShape2[1]);

				Rectangle rectangle = new Rectangle(new Point(x, y), width, height, select, color, innerColor);
				Rectangle rectangle2 = new Rectangle(new Point(x2, y2), width2, height2, select2, color2, innerColor2);

				Rectangle placeholder = null;

				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Rectangle && ((Rectangle) shape).equals(rectangle)) {
						placeholder = (Rectangle) shape;
					}
				}
				UpdateRectangleCommand cmd = new UpdateRectangleCommand(placeholder, rectangle2);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else if (shapeName.equals("Line")) {

				String xStartPart = logParts[2];
				String[] xCoordinate = xStartPart.split(":");
				int startX = Integer.parseInt(xCoordinate[1]);

				String yStartPart = logParts[3];
				String[] yCoordinate = yStartPart.split(":");
				int startY = Integer.parseInt(yCoordinate[1]);

				String xEndPart = logParts[4];
				String[] xEndCoordinate = xEndPart.split(":");
				int endX = Integer.parseInt(xEndCoordinate[1]);

				String yEndPart = logParts[5];
				String[] yEndCoordinate = yEndPart.split(":");
				int endY = Integer.parseInt(yEndCoordinate[1]);

				String selectedPart = logParts[6];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[7];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String xStartPart2 = logParts[8];
				String[] xCoordinate2 = xStartPart2.split(":");
				int startX2 = Integer.parseInt(xCoordinate2[1]);

				String yStartPart2 = logParts[9];
				String[] yCoordinate2 = yStartPart2.split(":");
				int startY2 = Integer.parseInt(yCoordinate2[1]);

				String xEndPart2 = logParts[10];
				String[] xEndCoordinate2 = xEndPart2.split(":");
				int endX2 = Integer.parseInt(xEndCoordinate2[1]);

				String yEndPart2 = logParts[11];
				String[] yEndCoordinate2 = yEndPart2.split(":");
				int endY2 = Integer.parseInt(yEndCoordinate2[1]);

				String selectedPart2 = logParts[12];
				String[] selectedShape2 = selectedPart2.split(":");
				boolean select2 = Boolean.parseBoolean(selectedShape2[1]);

				String colorPart2 = logParts[13];
				String[] colorShape2 = colorPart2.split(":");
				Color color2 = new Color(Integer.parseInt(colorShape2[1]));

				Line line = new Line(new Point(startX, startY), new Point(endX, endY), select, color);
				Line line2 = new Line(new Point(startX2, startY2), new Point(endX2, endY2), select2, color2);

				Line placeholder = null;

				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Line && ((Line) shape).equals(line)) {
						placeholder = (Line) shape;
					}
				}
				UpdateLineCommand cmd = new UpdateLineCommand(placeholder, line2);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Circle")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String selectedPart = logParts[5];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				String xPart2 = logParts[8];
				String[] xCoordinate2 = xPart2.split(":");
				int x2 = Integer.parseInt(xCoordinate2[1]);

				String yPart2 = logParts[9];
				String[] yCoordinate2 = yPart2.split(":");
				int y2 = Integer.parseInt(yCoordinate2[1]);

				String radiusPart2 = logParts[10];
				String[] radiusShape2 = radiusPart2.split(":");
				int radius2 = Integer.parseInt(radiusShape2[1]);

				String selectedPart2 = logParts[11];
				String[] selectedShape2 = selectedPart2.split(":");
				boolean select2 = Boolean.parseBoolean(selectedShape2[1]);

				String colorPart2 = logParts[12];
				String[] colorShape2 = colorPart2.split(":");
				Color color2 = new Color(Integer.parseInt(colorShape2[1]));

				String innerColorPart2 = logParts[13];
				String[] innerColorShape2 = innerColorPart2.split(":");
				Color innerColor2 = new Color(Integer.parseInt(innerColorShape2[1]));

				Circle circle = new Circle(new Point(x, y), radius, select, color, innerColor);
				Circle circle2 = new Circle(new Point(x2, y2), radius2, select2, color2, innerColor2);

				Circle placeholder = null;

				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Circle && ((Circle) shape).equals(circle)) {
						placeholder = (Circle) shape;
					}
				}
				UpdateCircleCommand cmd = new UpdateCircleCommand(placeholder, circle2);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Hexagon")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String selectedPart = logParts[5];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				String xPart2 = logParts[8];
				String[] xCoordinate2 = xPart2.split(":");
				int x2 = Integer.parseInt(xCoordinate2[1]);

				String yPart2 = logParts[9];
				String[] yCoordinate2 = yPart2.split(":");
				int y2 = Integer.parseInt(yCoordinate2[1]);

				String radiusPart2 = logParts[10];
				String[] radiusShape2 = radiusPart2.split(":");
				int radius2 = Integer.parseInt(radiusShape2[1]);

				String selectedPart2 = logParts[11];
				String[] selectedShape2 = selectedPart2.split(":");
				boolean select2 = Boolean.parseBoolean(selectedShape2[1]);

				String colorPart2 = logParts[12];
				String[] colorShape2 = colorPart2.split(":");
				Color color2 = new Color(Integer.parseInt(colorShape2[1]));

				String innerColorPart2 = logParts[13];
				String[] innerColorShape2 = innerColorPart2.split(":");
				Color innerColor2 = new Color(Integer.parseInt(innerColorShape2[1]));

				Hexagon hexagon = new Hexagon(new Point(x, y), radius, select, color, innerColor);
				Hexagon hexagon2 = new Hexagon(new Point(x2, y2), radius2, select2, color2, innerColor2);

				Hexagon placeholder = null;

				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Hexagon && ((Hexagon) shape).equals(hexagon)) {
						placeholder = (Hexagon) shape;
					}
				}
				UpdateHexagonCommand cmd = new UpdateHexagonCommand(placeholder, hexagon2);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Donut")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String innerRadiusPart = logParts[5];
				String[] innerRadiusShape = innerRadiusPart.split(":");
				int innerRadius = Integer.parseInt(innerRadiusShape[1]);

				String selectedPart = logParts[6];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[7];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[8];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				String xPart2 = logParts[9];
				String[] xCoordinate2 = xPart2.split(":");
				int x2 = Integer.parseInt(xCoordinate2[1]);

				String yPart2 = logParts[10];
				String[] yCoordinate2 = yPart2.split(":");
				int y2 = Integer.parseInt(yCoordinate2[1]);

				String radiusPart2 = logParts[11];
				String[] radiusShape2 = radiusPart2.split(":");
				int radius2 = Integer.parseInt(radiusShape2[1]);

				String innerRadiusPart2 = logParts[12];
				String[] innerRadiusShape2 = innerRadiusPart2.split(":");
				int innerRadius2 = Integer.parseInt(innerRadiusShape2[1]);

				String selectedPart2 = logParts[13];
				String[] selectedShape2 = selectedPart2.split(":");
				boolean select2 = Boolean.parseBoolean(selectedShape2[1]);

				String colorPart2 = logParts[14];
				String[] colorShape2 = colorPart2.split(":");
				Color color2 = new Color(Integer.parseInt(colorShape2[1]));

				String innerColorPart2 = logParts[15];
				String[] innerColorShape2 = innerColorPart2.split(":");
				Color innerColor2 = new Color(Integer.parseInt(innerColorShape2[1]));

				Donut donut = new Donut(new Point(x, y), radius, innerRadius, select, color, innerColor);
				Donut donut2 = new Donut(new Point(x2, y2), radius2, innerRadius2, select2, color2, innerColor2);

				Donut placeholder = null;

				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Hexagon && ((Donut) shape).equals(donut)) {
						placeholder = (Donut) shape;
					}
				}
				UpdateDonutCommand cmd = new UpdateDonutCommand(placeholder, donut2);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			}

		} else if (commandName.equals("MoveUp")) {
			int index = Integer.parseInt(logParts[1]);
			MoveUpCommand cmd = new MoveUpCommand(drawingModel, index);
			cmd.Do();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();
		} else if (commandName.equals("MoveToFront")) {
			int index = Integer.parseInt(logParts[1]);
			MoveToFrontCommand cmd = new MoveToFrontCommand(drawingModel, index);
			cmd.Do();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();
		} else if (commandName.equals("MoveDown")) {
			int index = Integer.parseInt(logParts[1]);
			MoveDownCommand cmd = new MoveDownCommand(drawingModel, index);
			cmd.Do();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();
		} else if (commandName.equals("MoveToBottom")) {
			int index = Integer.parseInt(logParts[1]);
			MoveToBottomCommand cmd = new MoveToBottomCommand(drawingModel, index);
			cmd.Do();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();
		} else if (commandName.equals("Select")) {
			String shapeName = logParts[1];
			if (shapeName.equals("Rectangle")) {
				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String heightPart = logParts[4];
				String[] heightShape = heightPart.split(":");
				int height = Integer.parseInt(heightShape[1]);

				String widthPart = logParts[5];
				String[] widthShape = widthPart.split(":");
				int width = Integer.parseInt(widthShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				String selectedPart = logParts[8];
				String[] selectShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectShape[1]);

				Rectangle rectangle = new Rectangle(new Point(x, y), width, height, select, color, innerColor);

				Rectangle placeholder = null;
				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Rectangle && ((Rectangle) shape).equals(rectangle)) {
						placeholder = (Rectangle) shape;
					}
				}
				SelectCommand cmd = new SelectCommand(placeholder);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();

			} else if (shapeName.equals("Circle")) {
				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String selectedPart = logParts[5];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				Circle circle = new Circle(new Point(x, y), radius, select, color, innerColor);

				Circle placeholder = null;
				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Circle && ((Circle) shape).equals(circle)) {
						placeholder = (Circle) shape;
					}
				}
				SelectCommand cmd = new SelectCommand(placeholder);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Point")) {

				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String selectedPart = logParts[4];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[5];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				Point point = new Point(x, y, select, color);

				Point placeholder = null;
				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Point && ((Point) shape).equals(point)) {
						placeholder = (Point) shape;
					}
				}
				SelectCommand cmd = new SelectCommand(placeholder);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Line")) {
				String xStartPart = logParts[2];
				String[] xCoordinate = xStartPart.split(":");
				int startX = Integer.parseInt(xCoordinate[1]);

				String yStartPart = logParts[3];
				String[] yCoordinate = yStartPart.split(":");
				int startY = Integer.parseInt(yCoordinate[1]);

				String xEndPart = logParts[4];
				String[] xEndCoordinate = xEndPart.split(":");
				int endX = Integer.parseInt(xEndCoordinate[1]);

				String yEndPart = logParts[5];
				String[] yEndCoordinate = yEndPart.split(":");
				int endY = Integer.parseInt(yEndCoordinate[1]);

				String selectedPart = logParts[6];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[7];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				Line line = new Line(new Point(startX, startY), new Point(endX, endY), select, color);

				Line placeholder = null;
				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Line && ((Line) shape).equals(line)) {
						placeholder = (Line) shape;
					}
				}
				SelectCommand cmd = new SelectCommand(placeholder);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Hexagon")) {
				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String selectedPart = logParts[5];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[6];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[7];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				Hexagon hexagon = new Hexagon(new Point(x, y), radius, select, color, innerColor);

				Hexagon placeholder = null;
				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Hexagon && ((Hexagon) shape).equals(hexagon)) {
						placeholder = (Hexagon) shape;
					}
				}
				SelectCommand cmd = new SelectCommand(placeholder);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			} else if (shapeName.equals("Donut")) {
				String xPart = logParts[2];
				String[] xCoordinate = xPart.split(":");
				int x = Integer.parseInt(xCoordinate[1]);

				String yPart = logParts[3];
				String[] yCoordinate = yPart.split(":");
				int y = Integer.parseInt(yCoordinate[1]);

				String radiusPart = logParts[4];
				String[] radiusShape = radiusPart.split(":");
				int radius = Integer.parseInt(radiusShape[1]);

				String innerRadiusPart = logParts[5];
				String[] innerRadiusShape = innerRadiusPart.split(":");
				int innerRadius = Integer.parseInt(innerRadiusShape[1]);

				String selectedPart = logParts[6];
				String[] selectedShape = selectedPart.split(":");
				boolean select = Boolean.parseBoolean(selectedShape[1]);

				String colorPart = logParts[7];
				String[] colorShape = colorPart.split(":");
				Color color = new Color(Integer.parseInt(colorShape[1]));

				String innerColorPart = logParts[8];
				String[] innerColorShape = innerColorPart.split(":");
				Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

				Donut donut = new Donut(new Point(x, y), radius, innerRadius, select, color, innerColor);

				Donut placeholder = null;
				for (Shape shape : drawingModel.getShapes()) {
					if (shape instanceof Donut && ((Donut) shape).equals(donut)) {
						placeholder = (Donut) shape;
					}
				}
				SelectCommand cmd = new SelectCommand(placeholder);
				cmd.Do();
				informObservers();
				frmDrawing.addLog(cmd.toString());
				drawingModel.getUndoCommands().add(cmd);
				frmDrawing.getPnlDrawing().repaint();
			}
		} else if (commandName.equals("UnSelect")) {
			String[] parts = log.split("#");
			ArrayList<Shape> shapesToBeUnselected = new ArrayList<>();
			for (int i = 1; i < parts.length; i++) {
				String part = parts[i];
				System.out.println(part);
				String[] shapeParts = part.split(",");
				
				String shapeName = shapeParts[0];
				if (shapeName.equals("Point")) {

					String xPart = shapeParts[1];
					String[] xCoordinate = xPart.split(":");
					int x = Integer.parseInt(xCoordinate[1]);

					String yPart = shapeParts[2];
					String[] yCoordinate = yPart.split(":");
					int y = Integer.parseInt(yCoordinate[1]);

					String selectedPart = shapeParts[3];
					String[] selectedShape = selectedPart.split(":");
					boolean select = Boolean.parseBoolean(selectedShape[1]);

					String colorPart = shapeParts[4];
					String[] colorShape = colorPart.split(":");
					Color color = new Color(Integer.parseInt(colorShape[1]));

					Point point = new Point(x, y, select, color);
					Point placeholder = null;
					for (Shape shape : drawingModel.getShapes()) {
						if (shape instanceof Point && ((Point) shape).equals(point)) {
							placeholder = (Point) shape;
						}
					}
					
					shapesToBeUnselected.add(placeholder);

				} else if (shapeName.equals("Rectangle")) {
					String xPart = shapeParts[1];
					String[] xCoordinate = xPart.split(":");
					int x = Integer.parseInt(xCoordinate[1]);

					String yPart = shapeParts[2];
					String[] yCoordinate = yPart.split(":");
					int y = Integer.parseInt(yCoordinate[1]);

					String heightPart = shapeParts[3];
					String[] heightShape = heightPart.split(":");
					int height = Integer.parseInt(heightShape[1]);

					String widthPart = shapeParts[4];
					String[] widthShape = widthPart.split(":");
					int width = Integer.parseInt(widthShape[1]);

					String colorPart = shapeParts[5];
					String[] colorShape = colorPart.split(":");
					Color color = new Color(Integer.parseInt(colorShape[1]));

					String innerColorPart = shapeParts[6];
					String[] innerColorShape = innerColorPart.split(":");
					Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

					String selectedPart = shapeParts[7];
					String[] selectShape = selectedPart.split(":");
					boolean select = Boolean.parseBoolean(selectShape[1]);

					Rectangle rectangle = new Rectangle(new Point(x, y), width, height, select, color, innerColor);
					
					Rectangle placeholder = null;
					for (Shape shape : drawingModel.getShapes()) {
						if (shape instanceof Rectangle && ((Rectangle) shape).equals(rectangle)) {
							placeholder = (Rectangle) shape;
						}
					}
					
					shapesToBeUnselected.add(placeholder);
					
				} else if (shapeName.equals("Circle")) {

					String xPart = shapeParts[1];
					String[] xCoordinate = xPart.split(":");
					int x = Integer.parseInt(xCoordinate[1]);

					String yPart = shapeParts[2];
					String[] yCoordinate = yPart.split(":");
					int y = Integer.parseInt(yCoordinate[1]);

					String radiusPart = shapeParts[3];
					String[] radiusShape = radiusPart.split(":");
					int radius = Integer.parseInt(radiusShape[1]);

					String selectedPart = shapeParts[4];
					String[] selectedShape = selectedPart.split(":");
					boolean select = Boolean.parseBoolean(selectedShape[1]);

					String colorPart = shapeParts[5];
					String[] colorShape = colorPart.split(":");
					Color color = new Color(Integer.parseInt(colorShape[1]));

					String innerColorPart = shapeParts[6];
					String[] innerColorShape = innerColorPart.split(":");
					Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

					Circle circle = new Circle(new Point(x, y), radius, select, color, innerColor);
					
					Circle placeholder = null;
					for (Shape shape : drawingModel.getShapes()) {
						if (shape instanceof Circle && ((Circle) shape).equals(circle)) {
							placeholder = (Circle) shape;
						}
					}
					
					shapesToBeUnselected.add(placeholder);
				} else if (shapeName.equals("Donut")) {

					String xPart = shapeParts[1];
					String[] xCoordinate = xPart.split(":");
					int x = Integer.parseInt(xCoordinate[1]);

					String yPart = shapeParts[2];
					String[] yCoordinate = yPart.split(":");
					int y = Integer.parseInt(yCoordinate[1]);

					String radiusPart = shapeParts[3];
					String[] radiusShape = radiusPart.split(":");
					int radius = Integer.parseInt(radiusShape[1]);

					String innerRadiusPart = shapeParts[4];
					String[] innerRadiusShape = innerRadiusPart.split(":");
					int innerRadius = Integer.parseInt(innerRadiusShape[1]);

					String selectedPart = shapeParts[5];
					String[] selectedShape = selectedPart.split(":");
					boolean select = Boolean.parseBoolean(selectedShape[1]);

					String colorPart = shapeParts[6];
					String[] colorShape = colorPart.split(":");
					Color color = new Color(Integer.parseInt(colorShape[1]));

					String innerColorPart = shapeParts[7];
					String[] innerColorShape = innerColorPart.split(":");
					Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

					Donut donut = new Donut(new Point(x, y), radius, innerRadius, select, color, innerColor);
					
					Donut placeholder = null;
					for (Shape shape : drawingModel.getShapes()) {
						if (shape instanceof Donut && ((Donut) shape).equals(donut)) {
							placeholder = (Donut) shape;
						}
					}
					shapesToBeUnselected.add(placeholder);
				} else if (shapeName.equals("Hexagon")) {

					String xPart = shapeParts[1];
					String[] xCoordinate = xPart.split(":");
					int x = Integer.parseInt(xCoordinate[1]);

					String yPart = shapeParts[2];
					String[] yCoordinate = yPart.split(":");
					int y = Integer.parseInt(yCoordinate[1]);

					String radiusPart = shapeParts[3];
					String[] radiusShape = radiusPart.split(":");
					int radius = Integer.parseInt(radiusShape[1]);

					String selectedPart = shapeParts[4];
					String[] selectedShape = selectedPart.split(":");
					boolean select = Boolean.parseBoolean(selectedShape[1]);

					String colorPart = shapeParts[5];
					String[] colorShape = colorPart.split(":");
					Color color = new Color(Integer.parseInt(colorShape[1]));

					String innerColorPart = shapeParts[6];
					String[] innerColorShape = innerColorPart.split(":");
					Color innerColor = new Color(Integer.parseInt(innerColorShape[1]));

					Hexagon hexagon = new Hexagon(new Point(x, y), radius, select, color, innerColor);
					
					Hexagon placeholder = null;
					for (Shape shape : drawingModel.getShapes()) {
						if (shape instanceof Hexagon && ((Hexagon) shape).equals(hexagon)) {
							placeholder = (Hexagon) shape;
						}
					}
					
					shapesToBeUnselected.add(placeholder);
				} else if (shapeName.equals("Line")) {

					String xStartPart = shapeParts[1];
					String[] xCoordinate = xStartPart.split(":");
					int startX = Integer.parseInt(xCoordinate[1]);

					String yStartPart = shapeParts[2];
					String[] yCoordinate = yStartPart.split(":");
					int startY = Integer.parseInt(yCoordinate[1]);

					String xEndPart = shapeParts[3];
					String[] xEndCoordinate = xEndPart.split(":");
					int endX = Integer.parseInt(xEndCoordinate[1]);

					String yEndPart = shapeParts[4];
					String[] yEndCoordinate = yEndPart.split(":");
					int endY = Integer.parseInt(yEndCoordinate[1]);

					String selectedPart = shapeParts[5];
					String[] selectedShape = selectedPart.split(":");
					boolean select = Boolean.parseBoolean(selectedShape[1]);

					String colorPart = shapeParts[6];
					String[] colorShape = colorPart.split(":");
					Color color = new Color(Integer.parseInt(colorShape[1]));

					Line line = new Line(new Point(startX, startY), new Point(endX, endY), select, color);
					Line placeholder = null;
					for (Shape shape : drawingModel.getShapes()) {
						if (shape instanceof Line && ((Line) shape).equals(line)) {
							placeholder = (Line) shape;
						}
					}
					shapesToBeUnselected.add(placeholder);
				}
			}
			
			UnSelectCommand cmd = new UnSelectCommand(shapesToBeUnselected);
			cmd.Do();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();
			
		} else if (commandName.equals("Delete")) {
			DeleteCommand cmd = new DeleteCommand(drawingModel.getSelectedShapes(), drawingModel);
			cmd.Do();
			informObservers();
			frmDrawing.addLog(cmd.toString());
			drawingModel.getUndoCommands().add(cmd);
			frmDrawing.getPnlDrawing().repaint();
		}
		listLogCommand.remove(0);
	}
}
