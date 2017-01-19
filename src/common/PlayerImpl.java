package common;

public class PlayerImpl implements IPlayer{
	
	private String name;
	private CouleurPion color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CouleurPion getColor() {
		return color;
	}

	public void setColor(CouleurPion color) {
		this.color = color;
	}
}
