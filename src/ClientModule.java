public class ClientModule{
	
	void hireNewEmployee(Employee emp){
		
	}
	
	void terminateEmployee(Employee emp){
		
	}
	
	void printEmployeeReport(Employee emp){
		
	}
}

class Employee{
	long id;
	String name, department;
	boolean working;
	
	public String toString(){
		return "id="+id+" : name="+name+" : department="+department+" : working="+working;
	}
}

class EmployeeReportFormatter extends ReportFormatter{
	Employee employee;
	FormatType formatType;
	
	public void getFormattedEmployee(){
		
	}
}

class ReportFormatter{
	private String convertObjectToXML(Object obj){
		String xml = "";
		
		return xml;
	}

	private String convertObjectToCSV(Object obj){
		String csv = "";
		
		return csv;
	}
	
	public String getFormattedValue(){
		String val = "";
		
		return val;
	}
}

class EmployeeDAO{
	DatabaseConnectionManager connectionManager;
	Employee emp;
	
	public void saveEmployee(Employee emp){
		
	}
	
	public void deleteEmployee(Employee emp){
		
	}
}

class DatabaseConnectionManager{
	
	public DatabaseConnectionManager getManagerInstance(){
		return null;
	}
	
	public void connect(){
		
	}
	
	public void getConnectionObject(){
		
	}
	
	public void disconnect(){
		
	}
}

class FormatType{
	
}