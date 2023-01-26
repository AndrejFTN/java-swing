package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveLog implements Saver {

	private DefaultListModel<String> modelList;
	
	public SaveLog(DefaultListModel<String> modelList) {
		this.modelList = modelList;
	}
	
	@Override
	public void save(String filePath) {
		ArrayList<String> helper = new ArrayList<String>();
		for(int i = 0; i < modelList.size(); i++) {
			helper.add(modelList.get(i));
		}
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			for(int i = 0; i < helper.size(); i++) {
				fileWriter.write(helper.get(i) + System.lineSeparator());
			}
			fileWriter.close();
			JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

	@Override
	public String saveAs() {
		ArrayList<String> helper = new ArrayList<String>();
		for(int i = 0; i < modelList.size(); i++) {
			helper.add(modelList.get(i));
		}
		JFileChooser jFileChooser = new JFileChooser(new File("D:\\"));
		jFileChooser.setDialogTitle("Save Log");
		int response = jFileChooser.showSaveDialog(null);
		if(response == JFileChooser.APPROVE_OPTION) {
			String filePath = jFileChooser.getSelectedFile().getAbsolutePath() + ".log";
			try {
				FileWriter fileWriter = new FileWriter(filePath);
				for(int i = 0; i < helper.size(); i++) {
					fileWriter.write(helper.get(i) + System.lineSeparator());
				}
				fileWriter.close();
				JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				return filePath;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}
		return null;
	}

}
