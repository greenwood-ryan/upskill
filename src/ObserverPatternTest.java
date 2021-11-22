/*
The observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.  It is mainly used for implementing distributed event handling systems, in "event driven" software. In those systems, the subject is usually named a "stream of events" or "stream source of events", while the observers are called "sinks of events".  This pattern then perfectly suits any process where data arrives from some input that is not available to the CPU at startup, but instead arrives "at random" (HTTP requests, GPIO data, user input from keyboard/mouse/..., distributed databases and blockchains, ...).

When to use it? :
	1. When you need many other objects to receive an update when another object changes
	e.g. Stock market
		Stock market needs to send updates to objects representing individual stocks
		The Subject (publisher) sends many stocks to the Observers
		The Observers (subscribers) take the updates they want and use them.
Benefits:
	1. Loose coupling - Subject needs know nothing about Observers
Drawbacks:
	1. The Subject (publisher) may send updates that don't matter to the Observer (subscriber)
*/
import java.util.*;

public class ObserverPatternTest{
	public static void main(String[] args){
		StockGrabber sg = new StockGrabber();
		StockObserver so = new StockObserver(sg);
		sg.setAAPLPrice(145.02);
		sg.setIBMPrice(198.02);
		sg.setGooglePrice(585.02);
		sg.register(new StockObserver(sg));
		sg.register(new StockObserver(sg));
		sg.setAAPLPrice(254.02);
		sg.deRegister(so);
	}

}

interface Subject{
	public void register(Observer o);
	
	public void notifyObservers();
	
	public void deRegister(Observer o);
}

class StockGrabber implements Subject{
	
	//Observers kept in a list
	private List<Observer> observers;
	private double ibm, aapl, google;
	
	public StockGrabber(){
		observers = new ArrayList();
	}
	
	public void register(Observer o){
		observers.add(o);
	}
	
	public void notifyObservers(){
		for(Observer o: observers)
			o.update(ibm, aapl, google);
	}
	
	public void setIBMPrice(double price){
		ibm = price;
		notifyObservers();
	}
	
	public void setAAPLPrice(double price){
		aapl = price;
		notifyObservers();
	}
	
	public void setGooglePrice(double price){
		google = price;
		notifyObservers();
	}
	public void deRegister(Observer o){
		int index = 0;
		if(o != null)
			index = observers.indexOf(o);
		System.out.println("Observer "+(index+1)+" was deleted");
		observers.remove(index);
	}
}

class StockObserver implements Observer{
	private double ibm, aapl, google;
	private static int observerIdTracker = 0;
	private int observerId;
	private Subject stockGrabber;
	
	public StockObserver(Subject sg){
		stockGrabber = sg;
		this.observerId = ++observerIdTracker;
		System.out.println("New observer : "+observerId);
		stockGrabber.register(this);
	}
	
	public void update(double ibm, double aapl, double google){
		this.ibm = ibm;
		this.aapl = aapl;
		this.google = google;
		printThePrices();
	}
	
	public void printThePrices(){
		System.out.println(observerId+"\nIBM = "+ibm+" : Apple = "+aapl+" : Google = "+google);
	}
}



interface Observer{
	public void update(double ibm, double aapl, double google);
}