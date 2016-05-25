/**
 * 
 */
package com.ipc.oce.exceptions;

/**
 * @author Konovalov
 *
 */
public class ConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ConfigurationException() {
		
	}

	/**
	 * @param arg0
	 */
	public ConfigurationException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public ConfigurationException(Throwable arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ConfigurationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
