package processes;

public abstract class GeneralManufacturingProcess{
	private String processName;
	
	//protected methods so that only subclasses can implement
	protected abstract void assembleDevice();
	protected abstract void testDevice();
	protected abstract void packageDevice();
	protected abstract void storeDevice();
	
	//Template pattern method to be used by all subclasses
	public void launchProcess(){
		assembleDevice();
		testDevice();
		packageDevice();
		storeDevice();
	}	
	
	public GeneralManufacturingProcess(String name){
		processName = name;
	}
}
