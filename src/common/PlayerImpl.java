package common;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements IPlayer{
	
	private String name;
	private CouleurPion color;
	private boolean turn;
	private List<IObservator> observers = new ArrayList<IObservator>();

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
	
	public boolean hasTurn()
	{
		return turn;
	}
	
	public void setTurn(boolean val)
	{
		turn = val;
	}
	
	public void changeTurn()
	{
		turn = !turn;
		notifyObservers();
	}

	@Override
	public void addObserver(IObservator observer) {
		observers.add(observer);
	}

	@Override
	public void deleteObserver(IObservator observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		for (IObservator item : observers)
		{
			item.observableChanged(this);
		}
		
	}
}
