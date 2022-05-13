package control_window;

import java.io.IOException;
import java.util.ArrayList;

public abstract interface Device {
	public void close(String pid) throws IOException;
	public void resize(String pid, String w, String h) throws IOException;
	public void move(String pid, int x, int y) throws IOException;
	public String getActiveWindow() throws IOException;
	public String getPidByName(String windowName) throws IOException;
	public String getNameByPid(String pid) throws IOException;
	public ArrayList<String> getAllWindow() throws IOException;
}
