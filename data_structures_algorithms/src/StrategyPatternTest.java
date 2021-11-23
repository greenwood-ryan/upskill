/*
The strategy pattern uses composition instead of inheritance. In the strategy pattern, behaviors are defined as separate interfaces and specific classes that implement these interfaces. This allows better decoupling between the behavior and the class that uses the behavior. The behavior can be changed without breaking the classes that use it, and the classes can switch between behaviors by changing the specific implementation used without requiring any significant code changes. Behaviors can also be changed at run-time as well as at design-time.
When to use it?:
	1. To reduce a long list of conditionals
	2. To avoid duplicating code
	3. To keep class changes from forcing other class changes
	4. To hide complicated / secret code from the user (inject the functionality)
Negative:
	1. Increased number of classes and objects.

--
*/

public class StrategyPatternTest{
	public static void main(String[] args){
		try{
			FighterFactory factory = new FighterFactory();
			Fighter charles = factory.getInstance("CharlesdeOliveira");
			Fighter connor = factory.getInstance("ConnorMcGregor");
			
			System.out.print("Connor ");
			connor.fight();
			System.out.print("Charles ");
			charles.fight();
			System.out.println("Charles is losing changes strategy...");
			charles.setFightingStrategy(new Box());
			System.out.print("Connor ");
			connor.fight();
			System.out.print("Charles ");
			charles.fight();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
//behaviour specified
interface FightingStrategy{
	public void fight();	
}
//behaviour implemented
class JiuJitsu implements FightingStrategy{
	public void fight(){
		System.out.println("fights using  BJJ");
	}
}

class Grapple implements FightingStrategy{
	public void fight(){
		System.out.println("fights using  Sambo");
	}
}

class Box implements FightingStrategy{
	public void fight(){
		System.out.println("fights using  Boxing");
	}
}

abstract class Fighter{
	//composition of behaviour in class note: doesn't implement fighting strategy.
	private FightingStrategy strategy;
	
	public Fighter(FightingStrategy fs){
		strategy = fs;
	}
	
	public void fight(){
		strategy.fight();
	}
	//dynamically adjust behaviour
	public void setFightingStrategy(FightingStrategy fs){
		strategy = fs;
	}
}

class CharlesdeOliveira extends Fighter{
	public CharlesdeOliveira(){
		super(new JiuJitsu());
	}
}
	
class ConnorMcGregor extends Fighter{
	public ConnorMcGregor(){
		super(new Box());
	}
}

//factory pattern
class FighterFactory{
	public Fighter getInstance(String classNameString)throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Fighter obj = null;
		try{
			if(classNameString.equals(null))
				throw new ClassNotFoundException("Please provide a valid class name");
			else{
				Class clz = this.getClass().getClassLoader().loadClass(classNameString);
				obj = (Fighter)clz.newInstance();
			}
		}catch(ClassNotFoundException cnfe) {
			throw cnfe;
		}catch(InstantiationException ie){
			throw ie;
		}catch(IllegalAccessException iae){
			throw iae;
		}finally{
			return obj;
		}
	}
}	