package api;

import java.io.IOException;

public class DesktopRequest {
	private final static int driver = 0;
	private final static int record = 1;
	private final static int window = 2;
	private final static int Element = 3;
	private final static int keyboard = 4;
	private final static int mouse = 5;
	

	public static boolean chooseRequest(int type, int subtype, String [] data) throws IOException {
		boolean result = false;
		if(type == driver) {
			
		}
		else if(type == record) {
					
		}
		else if(type == window) {
			WindowExecution windowController = new WindowExecution();
			boolean isSuccess = windowController.execute(subtype, data);
			if(isSuccess == true) {
				result = true;
			}
		}
		else if(type == Element) {
			
		}
		else if(type == keyboard) {
			
		}
		else if(type == mouse) {
			
		}
		else {
			System.out.println("error : Wrong main type");
		}
		
		return result;
	}
	
}
