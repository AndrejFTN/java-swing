package drawing;

public class ObserverMessage {

	private boolean enableDelete, enableEdit, enableMoveUp, enableMoveDown, enableMoveToFront,
	enableMoveToBottom, enableRedo, enableUndo;

	public boolean isEnableDelete() {
		return enableDelete;
	}

	public void setEnableDelete(boolean enableDelete) {
		this.enableDelete = enableDelete;
	}

	public boolean isEnableEdit() {
		return enableEdit;
	}

	public void setEnableEdit(boolean enableEdit) {
		this.enableEdit = enableEdit;
	}

	public boolean isEnableMoveUp() {
		return enableMoveUp;
	}

	public void setEnableMoveUp(boolean enableMoveUp) {
		this.enableMoveUp = enableMoveUp;
	}

	public boolean isEnableMoveDown() {
		return enableMoveDown;
	}

	public void setEnableMoveDown(boolean enableMoveDown) {
		this.enableMoveDown = enableMoveDown;
	}

	public boolean isEnableMoveToFront() {
		return enableMoveToFront;
	}

	public void setEnableMoveToFront(boolean enableMoveToFront) {
		this.enableMoveToFront = enableMoveToFront;
	}

	public boolean isEnableMoveToBottom() {
		return enableMoveToBottom;
	}

	public void setEnableMoveToBottom(boolean enableMoveToBottom) {
		this.enableMoveToBottom = enableMoveToBottom;
	}

	public boolean isEnableRedo() {
		return enableRedo;
	}

	public void setEnableRedo(boolean enableRedo) {
		this.enableRedo = enableRedo;
	}

	public boolean isEnableUndo() {
		return enableUndo;
	}

	public void setEnableUndo(boolean enableUndo) {
		this.enableUndo = enableUndo;
	}
	
	
}
