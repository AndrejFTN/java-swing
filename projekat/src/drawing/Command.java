package drawing;

import java.util.ArrayList;

import geometry.Shape;

public interface  Command {


	
	void Do();
	
	void Undo();
	
}
