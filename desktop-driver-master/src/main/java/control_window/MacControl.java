package control_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MacControl implements Device{
	//TODO class macos
	public void close(String pid) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("swift /Users/agilitest/Desktop/main.swift close "+pid);
	}
	public void resize(String pid, String w, String h) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String target = getNameByPid(pid);
		//Process pr = rt.exec("tccutil reset AppleEvents; tccutil reset SystemPolicyAllFiles");
		Process pr = rt.exec("/Users/agilitest/Desktop/MacOSDriver2 resize "+target+" "+h+" "+w);
		///        Users/agilitest/downloads/control_lib-main/control_lib/src/main/java/control
		
        String ligne=null;
		InputStream inputStream = pr.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ( (ligne = br.readLine()) != null){
        	System.out.println(ligne);
			
        }
        
	}
	public void move(String pid, int x, int y) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String target = getNameByPid(pid);
		Process pr = rt.exec("/Users/agilitest/Desktop/MacOSDriver2 move "+target+" "+x+" "+y);
	}
	public void getBuildVersion(String name) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String target = name;
		Process pr = rt.exec("/Users/agilitest/Desktop/MacOSDriver2 version "+target);
		
	}
	public void getAppNameByPid(String pid) throws IOException {
		//
		
	}
	public void getIconPath(String pid) throws IOException {
		//
	}
	public void getVersionByAppName(String name) throws IOException {
		//

	}
	@SuppressWarnings("null")
	public ArrayList<String> getAllWindowName() throws IOException {
		ArrayList<String> activeWindow = getAllWindow();
		ArrayList<String> resultActiveWindow = null;
		
		//select only appName of opened app
		for(int n = 0; n < activeWindow.size(); ) {
			resultActiveWindow.add(activeWindow.get(n));
			n = n  + 2;
		}
		
		return activeWindow;
	}
	
	public ArrayList<String> getAllWindowPid() throws IOException{
		ArrayList<String> activeWindow = getAllWindow();
		ArrayList<String> resultActiveWindow = null;
		
		//select only pid of opened app
		for(int n = 1; n < activeWindow.size(); ) {
			resultActiveWindow.add(activeWindow.get(n));
			n = n  + 2;
		}
		
		return activeWindow;
	}
	public ArrayList<String> getAllWindow() throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("/Users/agilitest/Desktop/MacOSDriver2 getwindowlist");
		InputStream inputStream = pr.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String ligne=null;
        ArrayList<String> activeWindow = new ArrayList<String>();
        int cpt = 0;
        
        boolean isDiscovered = true;
        
        //array[line][PID,hostname,windowName]
        while ( (ligne = br.readLine()) != null){
        	isDiscovered = true;
        	
			for(int n = 0; n < activeWindow.size(); ) {
				if(ligne.equals(activeWindow.get(n))) {
					isDiscovered = false;
					break;
				}
				n = n +1;
				
				
			}
			if(isDiscovered == true) {
				activeWindow.add(ligne);
			}
			
        }
        return activeWindow;
	}
	public String getActiveWindow() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	public String getNameByPid(String pid) throws IOException {
		ArrayList<String> windowList = getAllWindow();
		String result = "notFound";
		for(int n = 0; n < windowList.size(); ) {
			if(windowList.get(n).equals(pid)) {
				result = windowList.get(n-1);
				break;
			}
			n = n +1;
		}
		return result;
		
	}
	public String getPidByName(String windowName) throws IOException {
		ArrayList<String> windowList = getAllWindow();
		String result = "notFound";
		for(int n = 1; n < windowList.size(); ) {
			if(windowList.get(n).equals(windowName)) {
				result = windowList.get(n-1);
			}
			n = n +1;
		}
		return result;
	}
	


}

