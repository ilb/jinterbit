/**
 * 
 */
package com.ipc.oce;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.exceptions.ConfigurationException;

/**
 * @author Konovalov
 *
 */
public abstract class ShellTool {
	
	protected static final String INSTNAME_KEY = "-inst";
	
	protected static final String PRP_KEY = "-prp";
	
	private static final String MSG_CONNECTED = "Connected";
	
	private static final String MSG_DISCONNECTED = "Disconnected";
	
	private Properties configuration = null;
	
	private String[] shellArgs = null;
	
	private List<String> keyList = null;
	
	private OCApp app = null;
	
	private boolean isInitialized = false;
	
	protected ShellTool(String[] shellArgs) {
		super();
		System.out.println("(C) 2011 \"Interprocom\"");
		this.shellArgs = shellArgs;
	}
	
	protected final OCApp getApplication() {
		return this.app;
	}
	
	protected final void connect() throws JIException, IOException, ConfigurationException {
		if (!isInitialized) {
			initCfg();
		}
		String driverName = (String)configuration.get(PropertiesReader.OCE_CFG_DRIVER);
		System.out.println("\tApp using '" + driverName + "'");
		ApplicationDriver driver = ApplicationDriver.loadDriver(driverName);
		driver.setAutoRegistration(true);
		OCApp app = OCApp.getNewInstance();
		app.setApplicationDriver(driver);
		try {
			System.out.println("\tConnecting...");
			app.connect(configuration);
			prMes(MSG_CONNECTED + " successfully", true);
		} catch (JIException e) {
			prMes(MSG_CONNECTED, false);
			throw e;
		} catch (IOException e) {
			prMes(MSG_CONNECTED, false);
			throw e;
		}
	}
	
	protected final void initCfg() throws IOException, ConfigurationException {
		String prpPath = getArgAndParam(PRP_KEY);
		PropertiesReader pr = null;
		File file = new File(prpPath);
		try {
			pr = new PropertiesReader(file);
			prMes("Configuration file has read", true);
		} catch (IOException e) {
			prMes("File readed. File not found: " + file.getAbsolutePath(), false);
			throw e;
		}
		String instName = null;
		if (hasArg(INSTNAME_KEY)) {
			instName = getArgAndParam(INSTNAME_KEY);
			try {
				configuration = pr.getPropertiesForInstance(instName);
				prMes("Instance configuration received", true);
			} catch (ConfigurationException e) {
				prMes("Instance configured", false);
				throw e;
			}
		} else {
			printHelp();
			throwMissingKey(INSTNAME_KEY);
		}
		isInitialized = true;
	}

	protected String[] getShellArgs() {
		return shellArgs;
	}
	
	protected List<String> listAllKeys() {
		if (keyList == null) {
			keyList = new ArrayList<String>();
			for (String k : shellArgs) {
				if (k.startsWith("-")) {
					keyList.add(k);
				}
			}
		}
		return keyList;
	}

	protected boolean hasArg(String argName) {
		boolean res = false;
		for (String s : shellArgs) {
			if (s.equals(argName)) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	protected String getArgAndParam(String argName) {
		if (shellArgs == null) {
			return null;
		}
			
		for (int i = 0; i < shellArgs.length; i++) {
			String arg = shellArgs[i];
			if (arg.equals(argName)) {
				return shellArgs[i+1];
			}
		}
		return null;
	}
	
	protected static void prMes(String mes, boolean statusOK) {
		System.out.println((statusOK?"[OK]":"[FAIL]") + mes);
	}
	
	static void throwMissingKey(String key) {
		throw new IllegalArgumentException("Key '" + key + "' is missing");
	}
	
	protected abstract void printHelp();
	
	protected abstract void doWork() throws JIException;
	
	protected void exitApplication() {
		if (app != null) {
			try {
				app.exit();
				prMes(MSG_DISCONNECTED, true);
			} catch (JIException e) {
				prMes(MSG_DISCONNECTED, false);
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
}
