package dataprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cli{
	public static Process ExecuteCli(String order) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(order);
		return pr;
	}
	public static List<String> ReadCli(Process pr) throws IOException {
		List<String> outputCli = new ArrayList<String>();
		InputStream inputStream = pr.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	    String line=null;
	    while ( (line = br.readLine()) != null){
	        System.out.println(line);
	        outputCli.add(line);
	        
	    }
	    return outputCli;
	}
}
