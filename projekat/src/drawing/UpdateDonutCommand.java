package drawing;

import geometry.Donut;

public class UpdateDonutCommand implements Command{

	Donut newDonut;
	Donut originalDonut;
	Donut oldDonut;
	
	public UpdateDonutCommand(Donut oldDonut, Donut newDonut) {
		this.newDonut = newDonut;
		this.oldDonut = oldDonut;
	}
	
	@Override
	public void Do() {
		originalDonut = oldDonut.clone();
		
		oldDonut.setCenter(newDonut.getCenter());
		//oldDonut.setRadius(newDonut.getRadius());
		oldDonut.setInnerColor(newDonut.getInnerColor());
		oldDonut.setInnerRadius(newDonut.getInnerRadius());
		oldDonut.setSelected(newDonut.isSelected());
		oldDonut.setColor(newDonut.getColor());
	}

	@Override
	public void Undo() {
		
		oldDonut.setCenter(originalDonut.getCenter());
		//oldDonut.setRadius(newDonut.getRadius());
		oldDonut.setInnerColor(originalDonut.getInnerColor());
		oldDonut.setInnerRadius(originalDonut.getInnerRadius());
		oldDonut.setSelected(originalDonut.isSelected());
		oldDonut.setColor(originalDonut.getColor());
	}

	@Override
	public String toString() {
		return "Update Donut: (newDonut=" + newDonut + ", originalDonut=" + originalDonut + ", oldDonut="
				+ oldDonut + ")";
	}
	
	
}
