package api;

public class DriverExecution {
	private final static int capabilities = 0;
	private final static int application = 1;
	private final static int closeWindows = 2;
	private final static int close = 3;
	
	public DriverExecution(int type,String[] commandsData){
		if(type == capabilities) {
			System.out.println("Success : Driver => Capabilities");
		}
		else if(type == application) {
			System.out.println("Success : Driver => Application");	
		}
		else if(type == closeWindows) {
			System.out.println("Success : Driver => Close Windowws");
		}
		else if(type == close) {
			System.out.println("Success : Driver => Close");
		}
		else {
			System.out.println("Error : wrong subType");
		}
		
	}
}
