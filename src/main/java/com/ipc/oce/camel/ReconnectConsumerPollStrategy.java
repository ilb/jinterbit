/**
 * 
 */
package com.ipc.oce.camel;

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.spi.PollingConsumerPollStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Konovalov
 *
 */
public class ReconnectConsumerPollStrategy implements PollingConsumerPollStrategy {
	
	private static final int ERROR_THRESHOLD = 10;
	
	private static final int RETRY_TRESHOLD = 3;
	
	private int errorCounter = 0;
	
	protected static final transient Log LOG = LogFactory.getLog(ReconnectConsumerPollStrategy.class);
	/**
	 * 
	 */
	public ReconnectConsumerPollStrategy() {
		
	}

	public boolean begin(Consumer arg0, Endpoint arg1) {
		return true;
	}

	public void commit(Consumer arg0, Endpoint arg1, int messageConsumed) {
		
	}

	public boolean rollback(Consumer consumer, Endpoint endpoint, int retryCounter, Exception exception) throws Exception {
		LOG.error(exception.getMessage());
		errorCounter++;
		if (retryCounter > RETRY_TRESHOLD || (errorCounter >= ERROR_THRESHOLD)) {
			LOG.info("Initiate restart now");
			endpoint.stop();
			endpoint.start();
			errorCounter = 0; // reset error counter
		} else {
			LOG.info("Retry to poll "+(RETRY_TRESHOLD - retryCounter)+" times befor restart endpoint.");
			return true;
		}
		return false;
	}

}
