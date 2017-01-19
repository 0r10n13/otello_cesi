package common;

import java.rmi.Remote;

import common.PlayerImpl.Color;

public interface IPlayer extends Remote {
	public String getName();

	public void setName(String name);

	public Color getColor();

	public void setColor(Color color);
}
