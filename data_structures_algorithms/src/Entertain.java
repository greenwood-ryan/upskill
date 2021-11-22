import java.io.*;
//Open closed principle, singleton, single responsibility principle, strategy pattern, dependency inversion principle
public class Entertain{
	public static void main(String[] args){
		RemoteControl rc = RemoteControl.getInstance();
		Device[] devices = {new TV(), new SoundSystem(), new Projector()};
		for(Device d : devices){
			rc.connectTo(d);
			rc.switchOn(d);
			rc.switchOff(d);
		}
	}
}

class RemoteControl implements Cloneable, Serializable{
	
	private static RemoteControl instance = null;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (Object)this.getInstance();
	}
	
	protected Object readResolve() {
		return (Object)this.getInstance();
	}

	private RemoteControl() {
	}
	
	public static void connectTo(Device device){
		device.connectRemote();
	}
	
	public void switchOn(Device device){
		device.switchOn();
	}
	
	public void switchOff(Device device){
		device.switchOff();
	}


	static final RemoteControl getInstance() {
		if(instance == null)
			instance = new RemoteControl();
		return instance;

	}
}

interface Device{
	public void switchOn();
	public void switchOff();
	public void connectRemote();
}

class TV implements Device{
	public void switchOn(){
		System.out.println("TV switches on...");
	}

	public void switchOff(){
		System.out.println("TV switches off...");
	}

	public void connectRemote(){
		System.out.println("TV connected to remote...");
	}
}

class Projector implements Device{
	public void switchOn(){
		System.out.println("Projector switches on...");
	}

	public void switchOff(){
		System.out.println("Projector switches off...");
	}

	public void connectRemote(){
		System.out.println("Projector connected to remote...");
	}
}

class SoundSystem implements Device{
	public void switchOn(){
		System.out.println("SoundSystem switches on...");
	}

	public void switchOff(){
		System.out.println("SoundSystem switches off...");
	}

	public void connectRemote(){
		System.out.println("SoundSystem connected to remote...");
	}
}

