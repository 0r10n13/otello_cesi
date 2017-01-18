package common;

public class Player {
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
	//test
	
}
