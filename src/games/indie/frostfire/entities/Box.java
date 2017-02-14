package games.indie.frostfire.entities;

public class Box {
	
	private int offset_x = 0, offset_y = 0;
	private int width, height;
	
	public Box(Entity entity) {
		this.width = entity.getWidth();
		this.height = entity.getHeight();
	}
	
	public Box(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getOffset_x() {
		return offset_x;
	}

	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}

	public int getOffset_y() {
		return offset_y;
	}

	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}

}
