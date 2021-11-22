/*
The bridge pattern is a design pattern used in software engineering that is meant to "decouple an abstraction from its implementation so that the two can vary independently", introduced by the Gang of Four.[1] The bridge uses encapsulation, aggregation, and can use inheritance to separate responsibilities into different classes.

When a class varies often, the features of object-oriented programming become very useful because changes to a program's code can be made easily with minimal prior knowledge about the program. The bridge pattern is useful when both the class and what it does vary often

What problems can the Bridge design pattern solve? [3]

	An abstraction and its implementation should be defined and extended independently from each other.
	A compile-time binding between an abstraction and its implementation should be avoided so that an implementation can be selected at run-time.
	When using subclassing, different subclasses implement an abstract class in different ways. But an implementation is bound to the abstraction at compile-time and cannot be changed at run-time.

What solution does the Bridge design pattern describe?

	Separate an abstraction (Abstraction) from its implementation (Implementor) by putting them in separate class hierarchies.
	Implement the Abstraction in terms of (by delegating to) an Implementor object.
	This enables to configure an Abstraction with an Implementor object at run-time.

Progressively add functionality while seperating major differences by using abstract classes.

When to use it?
	When you want to be able to change both the abstract classes and the concrete classes independently
	When you want the first abstract class to define the rules
	Then you add a concrete class with additional rules
	An abstract class has a reference to the device and it defines the abstract methods that will be implemented
	The concrete class defines the abstract methods required
*/

public class BridgePatternTest{
	public static void main(String[] args){
		BridgeRemoteButton tv = new BridgeTVRemoteMute(new BridgeTV(1, 200));
		BridgeRemoteButton tv2 = new BridgeTVRemotePause(new BridgeTV(1, 200));
		System.out.println("Test TV was muted");
		tv.button9Pressed();
		System.out.println("Test TV was paused");
		tv2.button9Pressed();
		System.out.println("Test volume was increased");
		tv.button7Pressed();
		System.out.println("Test volume was decreased");
		tv.button8Pressed();
		System.out.println("Test channel down");
		tv.button5Pressed();
		System.out.println("Test channel up");
		tv.button6Pressed();
		BridgeRemoteButton dvd = new BridgeDVDRemoteSlowPlay(new BridgeDVD(1, 14));
		System.out.println("Test DVD was played slowly");
		dvd.button9Pressed();
		System.out.println("Test volume was increased");
		dvd.button7Pressed();
		System.out.println("Test volume was decreased");
		dvd.button8Pressed();
		System.out.println("Test chapter up");
		dvd.button5Pressed();
		System.out.println("Test chapter down");
		dvd.button6Pressed();
	}
}

//REFINED ABSTRACTION (concrete)
class BridgeTVRemotePause extends BridgeRemoteButton{
	
	public BridgeTVRemotePause(EntertainmentDevice ed){
		super(ed);
	}
	
	public void button9Pressed(){
		System.out.println("TV was paused");
	}
}

//REFINED ABSTRACTION (concrete)
class BridgeDVDRemoteSlowPlay extends BridgeRemoteButton{
	
	public BridgeDVDRemoteSlowPlay(EntertainmentDevice ed){
		super(ed);
	}
	
	public void button9Pressed(){
		System.out.println("DVD was set to slow play");
	}
}

//REFINED ABSTRACTION (concrete)
class BridgeTVRemoteMute extends BridgeRemoteButton{
	
	public BridgeTVRemoteMute(EntertainmentDevice ed){
		super(ed);
	}
	
	public void button9Pressed(){
		System.out.println("TV was muted");
	}
}

//ABSTRACTION:
abstract class BridgeRemoteButton{
	private EntertainmentDevice device;
	
	public BridgeRemoteButton(EntertainmentDevice ed){
		device = ed;
	}

	public abstract void button9Pressed();
	
	public void deviceFeedback(){
		device.deviceFeedback();
	}

	public void button5Pressed(){
		device.button5Pressed();
	}

	public void button6Pressed(){
		device.button6Pressed();
	}

	public void button7Pressed(){
		device.button7Pressed();
	}

	public void button8Pressed(){
		device.button8Pressed();
	}	
}

//CONCRETE IMPLEMENTOR: concrete class to implement
class BridgeDVD extends EntertainmentDevice{
	
	public BridgeDVD(int state, int maxSetting){
		super.state = state;
		super.maxSetting = maxSetting;
	}
	

	
	public void button5Pressed(){
		System.out.println("Next Chapter");
		state++;
	}
	
	public void button6Pressed(){
		System.out.println("Previous Chapter");
		state--;
	}

}

//CONCRETE IMPLEMENTOR: concrete class to implement
class BridgeTV extends EntertainmentDevice{
	
	public BridgeTV(int state, int maxSetting){
		super.state = state;
		super.maxSetting = maxSetting;
	}
	

	
	public void button5Pressed(){
		System.out.println("Channel Down");
		state--;
	}
	
	public void button6Pressed(){
		System.out.println("Channel Up");
		state++;
	}

}

//ABSTRACT IMPLEMENTOR: abstract class that for every device we create
abstract class EntertainmentDevice{
	//channel or chapter(dvd) device is on
	public int state;
	//max channel number or max chapter possible
	public int maxSetting;
	public int volumeSetting = 0;
	
	public abstract void button5Pressed();
	public abstract void button6Pressed();
	
	public void deviceFeedback(){
		if(state > maxSetting || state < 0)
			state = 0;
		System.out.println("On "+state);
	}
	
	public void button7Pressed(){
		volumeSetting++;
		System.out.println("Volume at "+volumeSetting);
	}

	public void button8Pressed(){
		volumeSetting--;
		System.out.println("Volume at "+volumeSetting);
	}
}