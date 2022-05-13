package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import control_window.Control;
import control_window.Device;
import dataprocessing.Cli;

public class WindowExecution {
	private final static int title = 0;
	private final static int handle = 1;
	private final static int list = 2;
	private final static int move = 3;
	private final static int resize = 4;
	private final static int toFront = 5;
	private final static int switchWindow = 6;
	private final static int close = 7;
	private final static int url = 8;
	private final static int keys = 9;
	private final static int state = 10;

	
		
	public boolean execute(int type, String[] commandsData) throws IOException {
		boolean result = false;
		if(type == close || type == handle || type == state || type == keys) {
			if(type == state) {
				System.out.println("Success : Record => state");
				String target = "";
				String order ="wmctrl -R " + target;
				Cli.ExecuteCli(order);				
			}
			else if(type == keys) {
				System.out.println("Success : Record => keys");
				String target = "";
				String order ="wmctrl -R " + target;
				//Ajout de la windows key
				Cli.ExecuteCli(order);
			}
			else if(type == close) {
				System.out.println("Success : Record => close");
				
				String target = "";
				String order ="wmctrl -c " + target;
				Cli.ExecuteCli(order);
			}
			else if(type == handle) {
				System.out.println("Success : Record => handle");
			}
		}
		else if(type == toFront) {
			//Recup�ration de la fenetre en data[0] [NECESSAIRE]
			//R�cup�ration de l'�tat en Data[1]
			
			System.out.println("Success : Record => tofront");
			
			String target = "";
			String order ="wmctrl -R " + target;
			Cli.ExecuteCli(order);
			// Si Os == linux
			//CLI => wmctrl -R [logiciel]
		}
		else if(type == url) {
			System.out.println("Success : Record => url");
			String target = "";
			String order ="firefox " + target;
			Cli.ExecuteCli(order);
		}
		
		else if(type == title) {
			System.out.println("Success : Record => title");
			String target = "";
			String order ="ps -x | grep" + target;
			Cli.ReadCli(Cli.ExecuteCli(order));
			
		}
		else if(type == list) {
			System.out.println("Success : Record => list");
			Device c = Control.defineDevice();
			c.getAllWindow();
			
			result = true;
		}
		else if(type == switchWindow) {
			System.out.println("Success : Record => switchWindow");
		}
		else if(type == move || type == resize) {
			if(type == move) {
				System.out.println("Success : Record => move");
				List<String> target[];
				String order ="wmctrl -e '0" +"x,y"+"1920,1080'";
			}
			else if(type == resize) {
				Device c = Control.defineDevice();
				c.resize(commandsData[3],commandsData[4], commandsData[5]);
				
				result = true;
				
			}
		}


		

		
		else {
			System.out.println("Error : wrong subType");
		}
		return result;
	}
}
