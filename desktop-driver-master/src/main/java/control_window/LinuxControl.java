package control_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LinuxControl implements Device {
	public void close(String pid) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("wmctrl -c"+ pid);
	}
	public void resize(String pid, int w, int h) throws IOException {
		Runtime rt = Runtime.getRuntime();
		//Proccess :  WMCTRL -r [windowName] -e [position,x,y,W,H]
		Process pr = rt.exec("wmctrl -r" + getNameByPid(pid) + " -e 1,-1,-1,"+w+","+h);
		
	}
	public void move(String pid, int x, int y) throws IOException {
		Runtime rt = Runtime.getRuntime();
		//Proccess :  WMCTRL -r [windowName] -e [position,x,y,W,H]
		Process pr = rt.exec("wmctrl -r" + getNameByPid(pid) + " -e 1,"+x+","+y+",-1,-1");
		
	}
	public String getBuildVersion(String name) {
		//TODO determiner la version de build
		return "0.0.0.0";
	}
	public String getAppNameByPid(String pid) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("cat /proc/"+pid+"/cmdline");
		InputStream inputStream = pr.getInputStream(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String ligne=null;
		String processName = null;
		while ( (ligne = br.readLine()) != null){
			for (int n = ligne.length(); n==0; n = n -1) {
				if(ligne.substring(n,n).equals("\\")){
					processName = ligne.substring(n+1,ligne.length());
					break;
				}
			}
		}	
		return processName;
		
	}
	public String getIconPath(String pid) throws IOException {
		String appName = getAppNameByPid(pid);
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("dpkg -L"+pid+" | grep .png");
		
		
		return appName;
	}
	public String getVersionByAppName(String name) throws IOException {
		String appName = getAppNameByPid(name);
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(appName+" --version");
		InputStream inputStream = pr.getInputStream(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String ligne=null;
		String result = null;
		while ( (ligne = br.readLine()) != null){
			result = ligne;
		}
		return ligne;

	}
	public String getActiveWindow() throws IOException {
		String result = null;
		Runtime rt = Runtime.getRuntime();
		//format [PID]
		Process pr = rt.exec("wmctrl -lp | grep $(xprop -root | grep _NET_ACTIVE_WINDOW | head -1 | \\\r\n"+ "    awk '{print $5}' | sed 's/,//' | sed 's/^0x/0x0/')");
		InputStream inputStream = pr.getInputStream(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String ligne=null;
		while ( (ligne = br.readLine()) != null){
			result = ligne;
		}
		return result;
	}
	
	public String getPidByName(String windowName) throws IOException {
		String pid = null;
		ArrayList<String> tab = getAllWindow();
		for(int cpt = 0; cpt<tab.size(); cpt = cpt +1) {
			if(tab.get(cpt).equals(windowName)) {
				pid = tab.get(cpt-1);
			}
		}
		
		return pid;
	}
	public String getNameByPid(String pid) throws IOException {
		String windownName= null;
		ArrayList<String> tab = getAllWindow();
		for(int cpt = 0; cpt<tab.size(); cpt = cpt +1) {
			if(tab.get(cpt).equals(pid)) {
				windownName = tab.get(cpt+1);
			}
		}
		
		return windownName;
	}
	public ArrayList<String> getAllWindow() throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("wmctrl -l -p");
		
		InputStream inputStream = pr.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String ligne=null;
        //array[line][PID,hostname,windowName]
        ArrayList<String> activeWindow = new ArrayList<String>();
        int cpt = 0;
        System.out.println(br);
		while ( (ligne = br.readLine()) != null){
			String[] tmp = { 
					ligne.substring(14,18),
					getHostname(),
					wmctrlConvertListOfWindows(ligne)
			};
            activeWindow.add(tmp[0]);
            activeWindow.add(tmp[2]);
            
            
        } 
		System.out.println(activeWindow);
        return activeWindow;
	}
	private String getHostname() throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("hostname");
		
		InputStream inputStream = pr.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String ligne=null;
        String hostname = null;
		 while ( (ligne = br.readLine()) != null){
            hostname = ligne;
        }
		
        return hostname;
	}
	private String wmctrlConvertListOfWindows(String textToCut) throws IOException {
		//Determine HostName to know where to cut
		String hostname = getHostname();
		int hostSize = hostname.length();
		int textSize = textToCut.length();
		String windowName = null;
		for(int cpt = 0; cpt+hostSize <= textSize; cpt = cpt + 1){
			//Index Out of bound protection
			if(cpt+hostSize>textToCut.length()) {
				System.out.println("Error: no matching hostname in wmctrlCOnverListOfWindows");
				break;
			}
			else {
				//text analysis, we will take everything after the hostName (keyword)
				if(textToCut.substring(cpt, cpt + hostSize).equals(hostname)) {
					windowName = textToCut.substring(cpt + hostSize, textSize);
				}
			}
		}
		if(windowName == null) {
			System.out.println("Name not find");
		}
		return windowName;
	}
	public void resize(String pid, String w, String h) throws IOException {
		// TODO Auto-generated method stub
		//
	}

}
