/**
 * 
 */
package com.ipc.oce.camel;

import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.jinterop.dcom.common.JIException;
import org.w3c.dom.Document;

import com.ipc.oce.OCApp;
import com.ipc.oce.events.EventManager;

/**
 * @author Konovalov
 *
 */
public class ScheduledEventConsumer extends ScheduledPollConsumer {
	
	protected static final String COUNT_V8E_EVENT_LOG_RECORDS = "count(/v8e:EventLog/v8e:Event)";
	
	/**
	 * Фабрика XPATH.
	 */
	private static final XPathFactory FACTORY = XPathFactory.newInstance();
	
	/**
	 * Дата начала выборки и последняя дата обновления.
	 */
	private Date startDate = null;
	
	private String tempCatalog = null;
	
	/**
	 * @param endpoint
	 * @param processor
	 */
	public ScheduledEventConsumer(DefaultEndpoint endpoint, Processor processor) {
		super(endpoint, processor);
		setPollStrategy(new ReconnectConsumerPollStrategy());
		OCXEEndpoint ocxeEndpoint = (OCXEEndpoint) endpoint;
		
		startDate = new Date();
		tempCatalog = ocxeEndpoint.getTempDir();
		// выбор альтернативного каталога временных файлов
		if (tempCatalog == null) {
			try {
				tempCatalog = ocxeEndpoint.getApp_instance().getTempFilesDir();
			} catch (JIException e) {
				throw new RuntimeException(e);
			}
		}
		

		setDelay(ocxeEndpoint.getDelay());
		setInitialDelay(ocxeEndpoint.getInitialDelay());
		setTimeUnit(ocxeEndpoint.getTimeUnit());
	}
	
	@Override
	protected int poll() throws Exception {
		try{
			long start = System.currentTimeMillis();
			OCXEEndpoint ocxeEndpoint = (OCXEEndpoint) getEndpoint();
			OCApp application = ocxeEndpoint.getApp_instance();
			
			EventManager eventManager = new EventManager(ocxeEndpoint.getApp_instance(), tempCatalog);
			eventManager.setDeleteAfterUnload(true);
			
			Document docXml = eventManager.getEventLog(
						getTimePoint(),  
						null, 
						null,
						ocxeEndpoint.getEventTypes() != null ? ocxeEndpoint.getEventTypes().toArray(new String[]{}) : (String[]) null, 
						ocxeEndpoint.getListenForObjects() != null ? ocxeEndpoint.getListenForObjects().toArray(new String[]{}) : null
					);
		
			XPath xpath = FACTORY.newXPath();
			xpath.setNamespaceContext(application.getNamespaceContext());
			XPathExpression expr = xpath.compile(COUNT_V8E_EVENT_LOG_RECORDS);
			String xpathRes = expr.evaluate(docXml);
			
			int eventCount = Integer.parseInt(xpathRes);
			
			if (eventCount > 0) {
				Exchange exchange = new DefaultExchange(getEndpoint());
				Message message = exchange.getIn();
				message.setBody(docXml, String.class);
				exchange.setIn(message);
				
				getProcessor().process(exchange);
			}
			
			long workTime = System.currentTimeMillis() - start;
			updateTimePoint(workTime);
			return eventCount > 0 ? 1 : 0;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	// TODO При перестарте endpoint-а, время сбрасывается и интервалы могут теряться.
	private void updateTimePoint(long backTime) {
		startDate.setTime(System.currentTimeMillis() - backTime);
	}
	
	private Date getTimePoint() {
		return startDate;
	}

}
