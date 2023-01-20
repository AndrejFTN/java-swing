package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import geometry.Shape;

public class SaveSerialized implements Saver{

	private ArrayList<Shape> list;
	
	public SaveSerialized(ArrayList<Shape> list) {
		this.list = list;
	}
	
	@Override
	public void save(String filePath) {
		ArrayList<Shape> helper = new ArrayList<Shape>();
		for(int i = 0; i < this.list.size(); i ++) {
			helper.add(this.list.get(i));
		}
		ObjectOutputStream stream;
		try {
			stream = new ObjectOutputStream(new FileOutputStream(filePath));
			stream.writeObject(this.list);
			stream.close();
			JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public String saveAs() {
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Choose file");
		int response = jFileChooser.showSaveDialog(null);
		if(response == JFileChooser.APPROVE_OPTION) {
			String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
			try {
				ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath));
				stream.writeObject(this.list);
				stream.close();
				JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				return filePath;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}else {
			return null;
		}
		
	}

	
}
