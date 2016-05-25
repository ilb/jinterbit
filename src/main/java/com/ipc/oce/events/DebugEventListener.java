/**
 * 
 */
package com.ipc.oce.events;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Konovalov
 *
 */
public class DebugEventListener extends EventLogListener {

	private static final transient Log LOG = LogFactory.getLog(DebugEventListener.class);
	
	public DebugEventListener() {
		super();
	}

	@Override
	protected void processEvent(String xmlEvents) {
		LOG.info(xmlEvents);
	}

}
