package drawing;

import java.awt.Color;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {
	
	public int currentCommand = -1;
	public ArrayList<Command> undoCommands = new ArrayList<Command>();
	public ArrayList<Command> redoCommands = new ArrayList<Command>();
	
	public Color edgeColor = Color.BLACK, innerColor = Color.WHITE;
	
	public boolean lineWaitingForEndPoint = false;
	public Point startPoint;


	public boolean isLineWaitingForEndPoint() {
		return lineWaitingForEndPoint;
	}




	public void setLineWaitingForEndPoint(boolean lineWaitingForEndPoint) {
		this.lineWaitingForEndPoint = lineWaitingForEndPoint;
	}




	public Point getStartPoint() {
		return startPoint;
	}




	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	

	public int getCurrentCommand() {
		return currentCommand;
	}




	public void setCurrentCommand(int currentCommand) {
		this.currentCommand = currentCommand;
	}




	public Color getInnerColor() {
		return innerColor;
	}




	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}




	public ArrayList<Command> getUndoCommands() {
		return undoCommands;
	}




	public void setUndoCommands(ArrayList<Command> commands) {
		this.undoCommands = commands;
	}




	public ArrayList<Command> getRedoCommands() {
		return redoCommands;
	}




	public void setRedoCommands(ArrayList<Command> redoCommands) {
		this.redoCommands = redoCommands;
	}




	public Color getEdgeColor() {
		return edgeColor;
	}




	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}




	public DrawingModel() {
		
	}

	
}
