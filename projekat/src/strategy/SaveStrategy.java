package strategy;

public class SaveStrategy implements Saver {
	
	private Saver saver;
	
	public SaveStrategy(Saver saver) {
		this.saver = saver;
	}

	public Saver getSaver() {
		return saver;
	}

	public void setSaver(Saver saver) {
		this.saver = saver;
	}

	@Override
	public void save(String filePath) {
		this.saver.save(filePath);
		
	}

	@Override
	public String saveAs() {
		return this.saver.saveAs();
	}
	
	
}
