/**
 * 
 */
package com.ipc.oce;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.exceptions.ConfigurationException;
import com.ipc.oce.metadata.OCConfigurationMetadataObject;

/**
 * @author Konovalov
 *
 */
public class TestPlatform extends ShellTool {

	static String cp = System.getProperty("console.encoding","Cp866");
	
	private static final String MSG_1C_CONF_VERSION = "1C conf version: ";

	private static final String MSG_1C_CONF_NAME = "1C conf name: ";

	private static final String VERSION = "0.2.0";

	

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ConfigurationException 
	 * @throws JIException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws JIException, ConfigurationException, UnsupportedEncodingException {
		
		TestPlatform testPlatform = new TestPlatform(args);
		
		// check for keys
		if (testPlatform.hasArg(PRP_KEY)) {
			// go ahead if test connection encounter
			try {
				testPlatform.initCfg();
				
				testPlatform.connect();
				
				testPlatform.doWork();
				
			} catch (JIException e) {
				e.printStackTrace();
			} catch (IOException ioe) {
				String msg = ioe.getMessage();
				try {
					System.out.write(msg.getBytes(cp));
					System.out.println();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
			finally {
				testPlatform.exitApplication();
			}
		} else if (testPlatform.hasArg("-prpsample")) {
			testPlatform.printPropertiesFileHelp();
			testPlatform.exitApplication();
		} else {
			testPlatform.printHelp();
			testPlatform.exitApplication();
		}
	}
	
	/**
	 * @param shellArgs
	 * @throws ConfigurationException 
	 * @throws IOException 
	 */
	protected TestPlatform(String[] shellArgs) {
		super(shellArgs);
		System.out.println("1C DCOM testing tool.\nVERSION: " + VERSION);
	}
	
	private void printPropertiesFileHelp() {
		String mess = "\t# buh configuration\n" +
			"\toce.buh.driver = V81Driver\n" +
			"\toce.buh.host = 192.168.10.10\n"+
			"\toce.buh.host.user = COMUser\n"+
			"\toce.buh.host.password = COMPassword\n"+
			"\toce.buh.1c.dbpath = C:\\Developer\\Temp\\1Cdb\n"+
			"\toce.buh.1c.user = 1CUser\n"+
			"\toce.buh.1c.password = 1CPassword";
		System.out.println("HOW TO connect to filebased 1C database:");
		System.out.println("----------------------------------------");
		System.out.println(mess);
		System.out.println("----------------------------------------");
		System.out.println("WHERE:");
		System.out.println("\tbuh - instance name");
		System.out.println("\toce.<inst>.driver - driver name (V80Driver, V81Driver ot V82Driver)");
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.ShellTool#printHelp()
	 */
	@Override
	protected void printHelp() {
		System.out.println(
				"Help content:\n" +
				"\t1) Test connection:\t testplatform -prp <path2file> -inst <instanceName>\n" + 
				"\t2) Propertie file sample:\t testplatform -prpsample"
				);
		
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.ShellTool#doWork()
	 */
	@Override
	protected void doWork() throws JIException {
		OCApp app = getApplication();
		OCConfigurationMetadataObject metadata = app.getMetadata();
		
		// get cfg name
		try {
			String cfgName = metadata.getName();
			System.out.println("\t" + MSG_1C_CONF_NAME + cfgName);
		} catch (JIException ee) {
			prMes(MSG_1C_CONF_NAME, false);
		}
		
		// get cfg version
		try {
			String cfgVer = metadata.getVersion();
			System.out.println("\t" + MSG_1C_CONF_VERSION + " " + cfgVer);
		} catch (JIException ee) {
			prMes(MSG_1C_CONF_VERSION, false);
		}
		
	}

}
