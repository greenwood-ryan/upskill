/*
In object-oriented programming, the decorator pattern is a design pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.[1] The decorator pattern is often useful for adhering to the Single Responsibility Principle, as it allows functionality to be divided between classes with unique areas of concern.[2] Decorator use can be more efficient than subclassing, because an object's behavior can be augmented without defining an entirely new object.

What problems can it solve?
Responsibilities should be added to (and removed from) an object dynamically at run-time.[4]
A flexible alternative to subclassing for extending functionality should be provided.
When using subclassing, different subclasses extend a class in different ways. But an extension is bound to the class at compile-time and can't be changed at run-time

Subclass the original Component class into a Decorator class (see UML diagram);
In the Decorator class, add a Component pointer as a field;
In the Decorator class, pass a Component to the Decorator constructor to initialize the Component pointer;
In the Decorator class, forward all Component methods to the Component pointer; and
In the ConcreteDecorator class, override any Component method(s) whose behavior needs to be modified.

This pattern is designed so that multiple decorators can be stacked on top of each other, each time adding a new functionality to the overridden method(s).

COMPOSITION
*/
public class DecoratorPatternTest{
	public static void main(String[] args){
		Pizza p = new TomatoSauce(new Mozarella(new PlainPizza()));
		System.out.println("Ingredients: "+p.getDescription());
		System.out.println("Price: "+p.getCost());
	}
}

class TomatoSauce extends ToppingDecorator{
	public TomatoSauce(Pizza p){
		super(p);
		System.out.println("Adding "+this.getClass().getName()+"...");
		
	}
	
	public String getDescription(){
		return temp.getDescription()+", "+this.getClass().getName();
	}
	
	public double getCost(){
		return temp.getCost()+0.10;
	}
}

class Mozarella extends ToppingDecorator{
	public Mozarella(Pizza p){
		super(p);
		System.out.println("Adding "+this.getClass().getName()+"...");
		
	}
	
	public String getDescription(){
		return temp.getDescription()+", "+this.getClass().getName();
	}
	
	public double getCost(){
		return temp.getCost()+0.50;
	}
}

abstract class ToppingDecorator implements Pizza{
	protected Pizza temp;
	
	public ToppingDecorator(Pizza p){
		temp = p;
		System.out.println("Adding dough...");
	}
	
	public String getDescription(){
		return temp.getDescription();
	}
	public double getCost(){
		return temp.getCost();
	}
}

class PlainPizza implements Pizza{
	public String getDescription(){
		return "Thin dough";
	}
	public double getCost(){
		return 4.00;
	}
}

interface Pizza{
	public String getDescription();
	public double getCost();
}
