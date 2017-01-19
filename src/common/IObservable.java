package common;

public interface IObservable {
	public void addObserver(IObservator observer);
	
	public void deleteObserver(IObservator observer);
	
	public void notifyObservers();
}
