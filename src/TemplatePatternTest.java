/*
In object-oriented programming, the template method is one of the behavioral design patterns identified by Gamma et al.[1] in the book Design Patterns. The template method is a method in a superclass, usually an abstract superclass, and defines the skeleton of an operation in terms of a number of high-level steps. These steps are themselves implemented by additional helper methods in the same class as the template method.

The helper methods may be either abstract methods, in which case subclasses are required to provide concrete implementations, or hook methods, which have empty bodies in the superclass. Subclasses can (but are not required to) customize the operation by overriding the hook methods. The intent of the template method is to define the overall structure of the operation, while allowing subclasses to refine, or redefine, certain steps

The template method is used in frameworks, where each implements the invariant parts of a domain's architecture, while providing hook methods for customization. This is an example of inversion of control. The template method is used for the following reasons.[3]

	It lets subclasses implement varying behavior (through overriding of the hook methods).[6]
	It avoids duplication in the code: the general workflow of the algorithm is implemented once in the abstract class's template method, and necessary variations are implemented in the subclasses.[6]
	It controls the point(s) at which specialization is permitted. If the subclasses were to simply override the template method, they could make radical and arbitrary changes to the workflow. In contrast, by overriding only the hook methods, only certain specific details of the workflow can be changed,[6] and the overall workflow is left intact.
*/

public class TemplatePatternTest{
	public static void main(String[] args){
		SubwayBase[] sarmies = {new ItalianSandwich(), new SouthAfricanSandwich(), new VegetarianSandwich()};
		for(SubwayBase sarmie : sarmies)
			sarmie.makeSandwich();
	}
}

class VegetarianSandwich extends SubwayBase{
	String[] cheeses = {"Roquefort","Gouda"};
	String[] condiments = {"Vinegar","Oil"};

	void addCondiments(){
		System.out.println("\nAdding the condiments");
		for(String condiment : condiments){
			System.out.print(condiment+" ");
		}
	}
	void addCheese(){
		System.out.println("\nAdding the cheeses");
		for(String cheese : cheeses){
			System.out.print(cheese+" ");
		}
	}
	
	void addMeat(){}

	boolean customerWantsMeat(){
		return false;
	}
}

class SouthAfricanSandwich extends SubwayBase{
	String[] meats = {"Biltong","Boerewors","Steak"};
	String[] cheeses = {"Cheddar","Gouda"};
	String[] condiments = {"Peri sauce","Tomato sauce"};

	void addCondiments(){
		System.out.println("\nAdding the condiments");
		for(String condiment : condiments){
			System.out.print(condiment+" ");
		}
	}
	void addCheese(){
		System.out.println("\nAdding the cheeses");
		for(String cheese : cheeses){
			System.out.print(cheese+" ");
		}
	}
	void addMeat(){
		System.out.println("\n\nAdding the meat");
		for(String meat : meats){
			System.out.print(meat+" ");
		}
	}
}

class ItalianSandwich extends SubwayBase{
	String[] meats = {"Pepperoni","Salami","Bacon"};
	String[] cheeses = {"Roquefort","Gouda"};
	String[] condiments = {"Vinegar","Oil"};

	void addCondiments(){
		System.out.println("\nAdding the condiments");
		for(String condiment : condiments){
			System.out.print(condiment+" ");
		}
	}
	void addCheese(){
		System.out.println("\nAdding the cheeses");
		for(String cheese : cheeses){
			System.out.print(cheese+" ");
		}
	}
	void addMeat(){
		System.out.println("\n\nAdding the meat");
		for(String meat : meats){
			System.out.print(meat+" ");
		}
	}
}

abstract class SubwayBase{
	
	//template method
	final void makeSandwich(){
		cutBun();
		if(customerWantsMeat())
			addMeat();
		if(customerWantsCheese())
			addCheese();
		if(customerWantsCondiments())
			addCondiments();
		wrap();
	}
	
	//force implementation
	abstract void addCondiments();
	abstract void addCheese();
	abstract void addMeat();
	
	//hook methods that can be overridden optionally
	boolean customerWantsCondiments(){
		return true;
	}
	boolean customerWantsMeat(){
		return true;
	}
	boolean customerWantsCheese(){
		return true;
	}

	//definite actions
	void cutBun(){
		System.out.println("Cutting the bun");
	}
	
	void wrap(){
		System.out.println("Wrapping the sandwich");
	}
}