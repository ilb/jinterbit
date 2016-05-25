/**
 * 
 */
package com.ipc.oce.events;


/**
 * @author Konovalov
 * 
 */
public abstract class EventLogListener {

	private Exception lastException = null;

	public EventLogListener() {

	}
	
	// exception detection 
	public boolean hasErrors() {
		return lastException != null;
	}
	
	public Exception getLastException() {
		return lastException;
	}
	
	protected void setLastException(Exception e) {
		lastException = e;
	}

	protected abstract void processEvent(String xmlEvents);
}
