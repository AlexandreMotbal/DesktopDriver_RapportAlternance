package api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorIdentifier;
import oshi.hardware.HardwareAbstractionLayer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class Capabilities {	
	public Capabilities() throws IOException {
		String machineName = InetAddress.getLocalHost().getHostName();
		String osName = System.getProperty("os.name");
		String driverVersion = "1";
		double screenWidth = getScreenWidth();
		double screenHeight = getScreenHeight();
		String netVersion = getNetVersion();
		String driveLetter = getDriveLetter();
		long diskSpace = getTotalSpace(getDriveLetter());
		long freeSpace = getFreeSpace(getDriveLetter());
		String cpuName = getCpuName();
		String cpuSocket = getCpuSocket();
		String cpuArchitecture =  getCpuArchitecture();
		String cpuMaxClockSpeed = getCpuMaxClockSpeed();
		int cpuCores = getCpuCores();
		
		
		
	    
		
	}
	
	
	
	
	//*********************DISPLAY DATA*********************
	private double getScreenWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize.getWidth();
	}
	private double getScreenHeight() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize.getHeight();
	}
	//*********************DOTNET FRAMEWORK*********************
	private String getNetVersion() throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("dotnet --list-sdks");
		return pr.toString();
	}
	//*********************MEMORY SPACE DATA*********************
	private String getDriveLetter() {
		File[] roots = File.listRoots();
		return roots[0].toString();
	}
	private long getTotalSpace(String letter) {
		File file = new File(letter);
		return file.getTotalSpace();
	}
	private long getFreeSpace(String letter) {
		File file = new File(letter);
	    return file.getFreeSpace();
	}
	
	//*********************CPU DATA*********************
	private ProcessorIdentifier getCpu() {
		SystemInfo systemInfo = new SystemInfo();
		HardwareAbstractionLayer hardware = systemInfo.getHardware();
		CentralProcessor processor = hardware.getProcessor();
		CentralProcessor.ProcessorIdentifier processorIdentifier = processor.getProcessorIdentifier();
		return processorIdentifier;
		
	}
	private String getCpuName() {
		return getCpu().getName();
	}
	private String getCpuSocket() {
		return getCpu().getName();
	}
	private String getCpuArchitecture() {
		return getCpu().getMicroarchitecture();
	}
	private String getCpuMaxClockSpeed() {
		Double speed = getCpu().getVendorFreq() / 1000000000.0;
		return speed.toString();
	}
	private int getCpuCores() {
		SystemInfo systemInfo = new SystemInfo();
		HardwareAbstractionLayer hardware = systemInfo.getHardware();
		CentralProcessor processor = hardware.getProcessor();
		int physicalCoresNumber = processor.getPhysicalProcessorCount();
		return physicalCoresNumber;
	}
}
