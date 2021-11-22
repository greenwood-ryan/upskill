package workers;
import processes.*;

public class DeviceFactory{
	public static void main(String[] args){
		GeneralManufacturingProcess[] processes = {new PhoneManufacturingProcess(), new LaptopManufacturingProcess()};
		
		for(GeneralManufacturingProcess p : processes)
			p.launchProcess();
	}
}