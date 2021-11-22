/*
In object-oriented programming, the command pattern is a behavioral design pattern in which an object is used to encapsulate all information needed to perform an action or trigger an event at a later time. This information includes the method name, the object that owns the method and values for the method parameters.

Four terms always associated with the command pattern are command, receiver, invoker and client. A command object knows about receiver and invokes a method of the receiver. Values for parameters of the receiver method are stored in the command. The receiver object to execute these methods is also stored in the command object by aggregation. The receiver then does the work when the execute() method in command is called. An invoker object knows how to execute a command, and optionally does bookkeeping about the command execution. The invoker does not know anything about a concrete command, it knows only about the command interface. Invoker object(s), command objects and receiver objects are held by a client object, the client decides which receiver objects it assigns to the command objects, and which commands it assigns to the invoker. The client decides which commands to execute at which points. To execute a command, it passes the command object to the invoker object.

Using command objects makes it easier to construct general components that need to delegate, sequence or execute method calls at a time of their choosing without the need to know the class of the method or the method parameters. Using an invoker object allows bookkeeping about command executions to be conveniently performed, as well as implementing different modes for commands, which are managed by the invoker object, without the need for the client to be aware of the existence of bookkeeping or modes.

Allows you to store lists of code that is executed at a later time or many times
Benefits:
1. allows you to set aside a list of commands for later use.
2. can store multiple commands in a class to use again and again
3. can implement undo procedure for past commands
Negative:
1. creating many small classes that store lists of commands.
*/
import java.util.*;

//client
public class CommandPatternTest{
	public static void main(String[] args){
		ElectronicDevice device = TVRemote.getDevice();
		TurnTVOn onCommand = new TurnTVOn(device);
		TurnTVOff offCommand = new TurnTVOff(device);
		TurnTVUp upCommand = new TurnTVUp(device);
		TurnTVDown downCommand = new TurnTVDown(device);
		UndoAllCommands undoAll = new UndoAllCommands();
		
		DeviceButton db = new DeviceButton(onCommand);
		undoAll.add(onCommand);
		db.pressExecute();
		
		db = new DeviceButton(offCommand);
		undoAll.add(offCommand);
		db.pressExecute();
		
		db = new DeviceButton(upCommand);
		undoAll.add(upCommand);
		db.pressExecute();
		undoAll.add(upCommand);
		db.pressExecute();
		
		db = new DeviceButton(downCommand);
		undoAll.add(downCommand);
		db.pressExecute();
		
		Television tv = new Television();
		onCommand = new TurnTVOn(tv);
		db = new DeviceButton(onCommand);
		undoAll.add(onCommand);
		db.pressExecute();
		Radio rd = new Radio();
		onCommand = new TurnTVOn(rd);
		db = new DeviceButton(onCommand);
		undoAll.add(onCommand);
		db.pressExecute();
		System.out.println("\nUndoing all");
		undoAll.execute();

		
	}
}

class UndoAllCommands implements Command{
	
	List<Command> commands = new ArrayList();
	
	public void add(Command c){
		if(c != null)
			commands.add(c);
	}
	
	public void execute(){
		for(Command c : commands)
			c.undo();
	}
	
	public void undo(){
		for(Command c : commands)
			c.execute();
	}
}

class TurnOffAllReceivers implements Command{
	
	List<ElectronicDevice> devices;
	
	public TurnOffAllReceivers(List<ElectronicDevice> d){
		devices = d;
	}
	

	public void execute(){
		for(ElectronicDevice d : devices)
			d.off();
	}
	
	public void undo(){
		for(ElectronicDevice d : devices)
			d.on();
	}
}

//new receiver
class Radio implements ElectronicDevice{
	
	private int volume = 0;

	public void on(){
		System.out.println(this.getClass().getName()+" is ON");
	}
	public void off(){
		System.out.println(this.getClass().getName()+" is OFF");
		
	}
	public void volumeUp(){
		System.out.println(this.getClass().getName()+" volume is "+(++volume));

	}
	public void volumeDown(){
		System.out.println(this.getClass().getName()+" volume is "+(--volume));
		
	}
}

//get a receiver
class TVRemote{
	public static ElectronicDevice getDevice(){
		return new Television();
	}
}

//invoker
class DeviceButton{
	Command command;
	
	public DeviceButton(Command com){
		command = com;
	}
	
	public void pressExecute(){
		command.execute();
	}

	public void pressUndo(){
		command.undo();
	}	
}

//command
class TurnTVDown implements Command{
	
	ElectronicDevice device;
	
	public TurnTVDown(ElectronicDevice d){
		device = d;
	}
	
	public void execute(){
		device.volumeDown();
	}
	
	public void undo(){
		device.volumeUp();
	}
}

//command
class TurnTVUp implements Command{
	
	ElectronicDevice device;
	
	public TurnTVUp(ElectronicDevice d){
		device = d;
	}
	
	public void execute(){
		device.volumeUp();
	}
	
	public void undo(){
		device.volumeDown();
	}
}

//command
class TurnTVOff implements Command{
	
	ElectronicDevice device;
	
	public TurnTVOff(ElectronicDevice d){
		device = d;
	}
	
	public void execute(){
		device.off();
	}
	
	public void undo(){
		device.on();
	}
}

//command
class TurnTVOn implements Command{
	
	ElectronicDevice device;
	
	public TurnTVOn(ElectronicDevice d){
		device = d;
	}
	
	public void execute(){
		device.on();
	}
	
	public void undo(){
		device.off();
	}
}

//command
interface Command{
	public void execute();
	public void undo();
}

//receiver
class Television implements ElectronicDevice{
	
	private int volume = 0;

	public void on(){
		System.out.println(this.getClass().getName()+" is ON");
	}
	public void off(){
		System.out.println(this.getClass().getName()+" is OFF");
		
	}
	public void volumeUp(){
		System.out.println(this.getClass().getName()+" volume is "+(++volume));

	}
	public void volumeDown(){
		System.out.println(this.getClass().getName()+" volume is "+(--volume));
		
	}

}
//receiver
interface ElectronicDevice{
	public void on();
	public void off();
	public void volumeUp();
	public void volumeDown();
	
}

