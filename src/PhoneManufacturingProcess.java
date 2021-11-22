package processes;

public class PhoneManufacturingProcess extends GeneralManufacturingProcess{

	protected void assembleDevice(){
		System.out.println("Assembling phone");
	}
	protected void testDevice(){
		System.out.println("Testing phone");
	}
	protected void packageDevice(){
		System.out.println("Packaging phone");		
	}
	protected void storeDevice(){
		System.out.println("Storing phone");		
	}

	public PhoneManufacturingProcess(){
		super("PhoneManufacturingProcess");
	}
}
