package server;

import java.io.IOException;

import api.DesktopRequest;

public class RequestAnalyser {
	public RequestAnalyser(String msg) throws IOException {
		
		int cpt = 0;
		int size = msg.length();
		int nbrOfData;
		
		if (size>=0) {
			String [] data = msg.split("/");
			nbrOfData = data.length;
			//System.out.println(data.getClass());
			while(cpt < nbrOfData){
				cpt = cpt + 1;
			}
			
			controller(data);
		}
		else {
			System.out.println("No data.");
		}
		
	}
	
	
	public boolean controller(String [] data) throws IOException {
		boolean result = false;
		int order = 2;
		int suborder = 4;
		try {
			order = Integer.valueOf(data[1]);
			suborder = Integer.valueOf(data[2]);
		}finally {
			long startTime = System.nanoTime();
			result = DesktopRequest.chooseRequest(order,suborder,data);
			long stopTime = System.nanoTime();
			long timeToExec = (stopTime - startTime)/1000000;
			System.out.println("Time : " + timeToExec +" milli seconds");
		}
		
		
		
		
		return result;
	}
}
