/*
The abstract factory pattern provides a way to encapsulate a group of individual factories that have a common theme without specifying their concrete classes.[1] In normal usage, the client software creates a concrete implementation of the abstract factory and then uses the generic interface of the factory to create the concrete objects that are part of the theme. The client does not know (or care) which concrete objects it gets from each of these internal factories, since it uses only the generic interfaces of their products.[1] This pattern separates the details of implementation of a set of objects from their general usage and relies on object composition, as object creation is implemented in methods exposed in the factory interface.

https://en.wikipedia.org/wiki/Abstract_factory_pattern#/media/File:Abstract_factory_UML.svg

What is?
Like factory pattern but everything is encapsulated
	The method that orders the object
	the factories that build the objects
	the final objects
	the final objects contain other objects that use the strategy pattern 
		Object class fields are objects - composition.

What can you do with it?
	Create families of related objects without specifying a concrete class
	use when you have many objects that can be added or changed dynamically at run time
	model anything and have the objects interact through common interfaces
	BAD: can get complicated
*/

public class AbstractFactoryPatternTest{
	
	public static void main(String[] args){
		Product[] products = new Product[3];
		AbstractProductFactory f = new TabletFactory();
		Product p = f.orderProduct();
		products[0] = p;
		f = new PhoneFactory();
		p = f.orderProduct();
		products[1] = p;
		f = new LaptopFactory();
		p = f.orderProduct();
		products[2] = p;
		
		for(Product pr : products)
			System.out.println(pr);
		
	}
}

class LaptopComponentFactory extends ProductComponentFactory{
	Screen addScreen(){
		return new LaptopScreen();
	}
	
	Body addBody(){
		return new LaptopBody();		
	}
}

class PhoneComponentFactory extends ProductComponentFactory{
	Screen addScreen(){
		return new PhoneScreen();
	}
	
	Body addBody(){
		return new PhoneBody();		
	}
}

class TabletComponentFactory extends ProductComponentFactory{
	Screen addScreen(){
		return new TabletScreen();
	}
	
	Body addBody(){
		return new TabletBody();		
	}
}

abstract class ProductComponentFactory{
	abstract Screen addScreen();
	abstract Body addBody();
}


class LaptopFactory extends AbstractProductFactory{
	
	private final double  PRICE = 162.22;
	
	protected Product makeProduct(){
		ProductComponentFactory factory = new LaptopComponentFactory();
		Product product = new Laptop(factory);
		product.setName(product.getClass().getName());
		product.setPrice(PRICE);
		
		return product;
	}
}

class TabletFactory extends AbstractProductFactory{
	
	private final double  PRICE = 132.22;
	
	protected Product makeProduct(){
		ProductComponentFactory factory = new TabletComponentFactory();
		Product product = new Tablet(factory);
		product.setName(product.getClass().getName());
		product.setPrice(PRICE);
		return product;
	}
}

class PhoneFactory extends AbstractProductFactory{
	
	private final double  PRICE = 102.22;
	
	protected Product makeProduct(){
		ProductComponentFactory factory = new PhoneComponentFactory();
		Product product = new Phone(factory);
		product.setName(product.getClass().getName());
		product.setPrice(PRICE);
		return product;
	}
}

abstract class AbstractProductFactory{
	protected abstract Product makeProduct();
	
	public Product orderProduct(){
		Product prod = makeProduct();
		prod.makeProduct();
		prod.turnOn();
		prod.turnOff();
		return prod;
	}
}

class LaptopBody implements Body{
	public String toString(){
		return this.getClass().getName();
	}
}

class PhoneBody implements Body{
	public String toString(){
		return this.getClass().getName();
	}
}

class TabletBody implements Body{
	public String toString(){
		return this.getClass().getName();
	}
}

interface Body{
	public String toString();
}

class LaptopScreen implements Screen{
	public String toString(){
		return this.getClass().getName();
	}
}

class PhoneScreen implements Screen{
	public String toString(){
		return this.getClass().getName();
	}
}

class TabletScreen implements Screen{
	public String toString(){
		return this.getClass().getName();
	}
}

interface Screen{
	public String toString();
}

abstract class Product{
	private String name;
	private double price;
	private Screen screen;
	private Body body;
	ProductComponentFactory factory;
	
	public void setScreen(Screen s){
		screen = s;
	}
	
	public void setBody(Body b){
		body = b;
	}

	public Product(ProductComponentFactory f){
		factory = f;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setPrice(double price){
		this.price = price;
	}

	public void setName(String name){
		this.name = name;
	}
	
	abstract Product makeProduct();
	abstract void turnOn();
	abstract void turnOff();
	public String toString(){
		return name+" : "+price+" : "+screen+" : "+body;
	}
}

class Tablet extends Product{
	
	public Tablet(ProductComponentFactory f){
		super(f);
	}
	
	Product makeProduct(){
		System.out.println("Making a "+this.getName());
		setScreen(factory.addScreen());
		setBody(factory.addBody());
		return this;
	}
	
	void turnOn(){
		System.out.println("Turning on "+this.getName());
	}
	
	void turnOff(){
		System.out.println("Turning off "+this.getName());
	}
}

class Phone extends Product{
	
	public Phone(ProductComponentFactory f){
		super(f);
	}
	
	Phone makeProduct(){
		System.out.println("Making a "+this.getName());
		setScreen(factory.addScreen());
		setBody(factory.addBody());
		return this;
	}
	
	void turnOn(){
		System.out.println("Turning on "+this.getName());
	}
	
	void turnOff(){
		System.out.println("Turning off "+this.getName());
	}
}

class Laptop extends Product{
	
	public Laptop(ProductComponentFactory f){
		super(f);
	}
	
	Laptop makeProduct(){
		System.out.println("Making a "+this.getName());
		setScreen(factory.addScreen());
		setBody(factory.addBody());
		return this;
	}
	
	void turnOn(){
		System.out.println("Turning on "+this.getName());
	}
	
	void turnOff(){
		System.out.println("Turning off "+this.getName());
	}
}

