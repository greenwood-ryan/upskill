/*
In software engineering, the singleton pattern is a software design pattern that restricts the instantiation of a class to one "single" instance. This is useful when exactly one object is needed to coordinate actions across the system.
*/
import java.io.*;
public class SingletonPatternTest{
	
	public static void main(String[] args){
		SingletonPatternExample ins = SingletonPatternExample.getInstance();
		System.out.println(ins.toString());
		//test cloning it
		try{
			System.out.println(SingletonPatternExample.getInstance().clone().toString());
		}catch(Exception e){
			System.out.println(e.toString());
		}			
		try{
			//test serializizing and deserializing
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(ins);
	 
			//De-serialization of object
			ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bis);
			SingletonPatternExample copied = (SingletonPatternExample) in.readObject();
 			
			System.out.println(copied.toString());
		}catch(Exception e){
			System.out.println(e.toString());
		}
		//test use classloader to get instance
		try{
			ClassLoader classLoader = SingletonPatternExample.class.getClassLoader(); 
			Object spe =  (Object)classLoader.loadClass("SingletonPatternExample").newInstance();
			System.out.println(spe.toString());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}


class SingletonPatternExample implements Cloneable, Serializable{
	
	private static SingletonPatternExample instance = null;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (Object)this.getInstance();
	}
	
	protected Object readResolve() {
		return (Object)this.getInstance();
	}

	private SingletonPatternExample() {
	}


	static final SingletonPatternExample getInstance() {
		if(instance == null)
			instance = new SingletonPatternExample();
		return instance;

	}
}
