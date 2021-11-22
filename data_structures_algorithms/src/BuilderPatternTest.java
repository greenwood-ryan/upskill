/*
The builder pattern is a design pattern designed to provide a flexible solution to various object creation problems in object-oriented programming. The intent of the Builder design pattern is to separate the construction of a complex object from its representation. It is one of the Gang of Four design patterns.
The Builder design pattern solves problems like:[2]
How can a class (the same construction process) create different representations of a complex object?
How can a class that includes creating a complex object be simplified?  Creating and assembling the parts of a complex object directly within a class is inflexible. It commits the class to creating a particular representation of the complex object and makes it impossible to change the representation later independently from (without having to change) the class.  The Builder design pattern describes how to solve such problems:

Encapsulate creating and assembling the parts of a complex object in a separate Builder object.
A class delegates object creation to a Builder object instead of creating the objects directly.
A class (the same construction process) can delegate to different Builder objects to create different representations of a complex object.
*/

public class BuilderPatternTest{
	public static void main(String[] args){
		MountainBikeBuilder mbb = new MountainBikeBuilder();
		BicycleDirector bd = new BicycleDirector(mbb);
		bd.build();
		System.out.println(mbb.build());
	}
}

class BicycleDirector{
	private BicycleBuilder builder;
	
	public BicycleDirector(BicycleBuilder bb){
		builder = bb;
	}
	
	public void build(){
		builder.setColour("Blue");
		builder.setHeight(29);
	}
}

class MountainBikeBuilder implements BicycleBuilder{

	private int height;
	private String colour;
	
	public void setHeight(int h){
		height = h;
	}
	
	public void setColour(String col){
		colour = col;
	}
	
	public Bicycle build(){
		return new Bicycle(height, "Giant", "Mountain Bike Steep Terrain", colour);
	}
}

interface BicycleBuilder{
	Bicycle build();
	public void setHeight(int h);
	public void setColour(String col);
}

class Bicycle{
	private int height;
	private String make, model, colour;
	
	public Bicycle(int h, String mk, String mdl, String clr){
		height = h;
		make = mk;
		model = mdl;
		colour = clr;
	}
	
	public String toString(){
		return "Make: "+make+", model: "+model+", colour: "+colour+", height: "+height;
	}
	
	public void set(int i){
		height = i;
	}

	public void setColour(String s){
		colour = s;
	}

	public void setModel(String s){
		model = s;
	}

	public void setMake(String s){
		make = s;
	}

	public int getHeight(){
		return height;
	}

	public String getMake(){
		return make;
	}

	public String getModel(){
		return model;
	}

	public String getColour(){
		return colour;
	}
}