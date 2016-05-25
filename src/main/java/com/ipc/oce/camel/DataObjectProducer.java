/**
 * 
 */
package com.ipc.oce.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.objects._OCCommonObject;
import com.ipc.oce.xml.oc.MarshalHelper;
import com.ipc.oce.xml.oc.UnmarshalHelper;

/**
 * @author Konovalov
 *
 */
public class DataObjectProducer extends DefaultProducer {
	
	private static final transient Log LOG = LogFactory.getLog(DataObjectProducer.class);
	
	/**
	 * @param endpoint
	 */
	public DataObjectProducer(Endpoint endpoint) {
		super(endpoint);
	}


	public void process(Exchange paramExchange) throws Exception {
		OCApp appInstance = ((OCXEEndpoint) getEndpoint()).getApp_instance();
//		if( !(app_instance.ping()) ){ 
//			// double-checked reconnect
//			synchronized (app_instance) {
//				if(!(app_instance.ping()))
//					app_instance.reconnect();
//			}
//		}
		UnmarshalHelper unmarshalHelper = new UnmarshalHelper(appInstance);
		
		String payload = paramExchange.getIn().getBody(String.class);
		OCObject ocObject = unmarshalHelper.xml2object(payload, true);
		
		_OCCommonObject co = new _OCCommonObject(ocObject);

		co.write();
		LOG.info("Object '" + co.toString() + "' written to db");
		ExchangePattern exchangePattern = paramExchange.getPattern();
		
		if (exchangePattern == ExchangePattern.InOnly) {
			paramExchange.setOut(paramExchange.getIn());
		} else if (exchangePattern == ExchangePattern.InOut) {
			co.read(); // повторно считываем сохраненный объект из базы
			//LOG.info("After read "+co.toString());
			MarshalHelper marshalHelper = new MarshalHelper(appInstance);
			String payload2 = marshalHelper.object2xml(co, "UTF-8");
			Message messageOut = paramExchange.getOut();
			messageOut.setBody(payload2);
		}
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
	

}
