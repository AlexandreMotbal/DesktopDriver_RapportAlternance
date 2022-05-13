package control_window;

import java.io.IOException;
import java.util.ArrayList;

public class Control {
	private static String os = System.getProperty("os.name").toLowerCase();
	public static Device defineDevice() throws IOException {
		Device result =  null;
		if(os.equals("linux")) {
			
			result = new LinuxControl();
		}
		else if(os.equals("mac os x")){
			result = new MacControl();
		}
		return result;
	}
}
