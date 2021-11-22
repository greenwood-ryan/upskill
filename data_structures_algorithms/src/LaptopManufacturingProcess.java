package processes;

public class LaptopManufacturingProcess extends GeneralManufacturingProcess{

	protected void assembleDevice(){
		System.out.println("Assembling Laptop");
	}
	protected void testDevice(){
		System.out.println("Testing Laptop");
	}
	protected void packageDevice(){
		System.out.println("Packaging Laptop");		
	}
	protected void storeDevice(){
		System.out.println("Storing Laptop");		
	}

	public LaptopManufacturingProcess(){
		super("LaptopManufacturingProcess");
	}
}