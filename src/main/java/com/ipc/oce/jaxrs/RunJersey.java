/**
 * 
 */
package com.ipc.oce.jaxrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

/**
 * @author Konovalov
 * 
 */
public class RunJersey {
	
	private static class InputParameters {
		String cfgPath = null;
		String cfgInst = null;
		String cfgTmpDir = null;
		String host = null;
	}
	
	private static InputParameters parseInput(String[] args) {
		InputParameters ip = new InputParameters();
		List<String> lParams = Arrays.asList(args);
		if (lParams.contains("--help")) {
			throw new RuntimeException("HELP MESSAGE");
		}
		ip.host = findPair("-h", args)[1];
		ip.cfgPath = findPair("-c", args)[1];
		ip.cfgInst = findPair("-i", args)[1];
		return ip;
	}
	
	private static String[] findPair(String key, String[] args) {
		short pos = 0;
		for (String cur : args) {
			pos++;
			if (cur.equals(key)) {
				return new String[]{cur, args[pos]};
			}
		}
		throw new IllegalStateException("Key '" + key + "' missed");
	}
	/**
	 * @param args
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws IllegalArgumentException, IOException {
		Map<String,String> initParams = new HashMap<String, String>();
		initParams.put("com.sun.jersey.config.property.packages", "com.ipc.oce.jaxrs" );
	    SelectorThread selector = GrizzlyWebContainerFactory.create( "http://localhost:9998/", initParams );
	    System.in.read();
	    selector.stopEndpoint();
	    System.exit(0);
	    
		/*InputParameters iParam = parseInput(args);
		
		String baseUri = iParam.host;
		final Map<String, String> initParams = new HashMap<String, String>();
		
		initParams.put("com.sun.jersey.config.property.packages", "com.ipc.oce.jaxrs");  
		initParams.put(ResourceSessionAccessor.CONFIG_FILE_PATH, iParam.cfgPath);
		initParams.put(ResourceSessionAccessor.CONFIG_INST_NAME, iParam.cfgInst);
		initParams.put("tempLogDirectory", System.getProperty("java.io.tmpdir"));
		
		System.out.println("Starting grizzly...");        
		SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);    
		System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl\nHit enter to stop it...", baseUri, baseUri));         
		System.in.read();        
		threadSelector.stopEndpoint();          
		System.exit(0);*/
	}
}
