package common;

public class PlayerImpl implements IPlayer{
	public static enum Color {BLANC, NOIR};
	
	private String name;
	private Color color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
