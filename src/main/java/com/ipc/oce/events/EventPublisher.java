/**
 * 
 */
package com.ipc.oce.events;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ipc.oce.OCApp;
import com.ipc.oce.xml.xerces.Utils;

/**
 * @author Konovalov
 *
 */
public class EventPublisher extends TimerTask {
	
	/**
	 * Список EventLogListener для публикации сообщений.
	 */
	private List<EventLogListener> listeners = null;
	
	/**
	 * Экземпляр OCApp с которым работает EventPublisher.
	 */
	private OCApp app = null;
	
	/**
	 * Экземпляр менеджера событий.
	 */
	private EventManager manager = null;
	
	/**
	 * Время последнего обращения к журналу 1С.
	 */
	private Date lastUpdated = null;
	
	/**
	 * Список типов событий. 
	 * Например: обновление\создание\удаление данных, открытие сессий и т.д.
	 */
	private List<String> eventTypes = null;
	
	/**
	 * Список уровенй событий (INFORMATION, WARNING, ERROR, NOTE).
	 */
	private List<String> eventLevels = null;
	
	/**
	 * Список объектов метаданных.
	 */
	private List<String> dataObjectTypes = null;
	
	/**
	 * UUID экземпляра EventPublisher.
	 */
	private UUID publisherUUID = null;
	
	/**
	 * Логгер.
	 */
	private static transient Log logger = LogFactory.getLog(EventPublisher.class);
	
	/**
	 * Конструктор.
	 * @param app - экземпляр OCApp
	 * @param remoteTmpDir - каталог для временного хранения 
	 * выгрузки журнала 1С на стороне сервера DCOM
	 */
	public EventPublisher(OCApp app, String remoteTmpDir) {
		
			this.app = app;
	    	
	    	publisherUUID = UUID.randomUUID();
		
	    	listeners = Collections.synchronizedList(
	    			new ArrayList<EventLogListener>()
	    			);
		
	    	manager = new EventManager(app, remoteTmpDir);
		
	    	lastUpdated = new Date();
		
	    	eventTypes = new ArrayList<String>();
		
	    	dataObjectTypes = new ArrayList<String>();
		
	    	eventLevels = new ArrayList<String>();
	}
	
	
	/**
	 * Получение отметки времени просмотра журнала.
	 * @return время последнего успешного сканирования журнала 
	 * и точки для следующего сканирования.
	 */
	public final Date getLastUpdated() {
		return lastUpdated;
	}
	
	/**
	 * Установка времени начала сканирования журнала.
	 * @param startTime - дата
	 */
	public final void setStartTime(final Date startTime) {
		this.lastUpdated = startTime;
	}
	
	/**
	 * UUID экземпляра EventPublisher.
	 * @return UUID
	 */
	public final UUID getPublisherUUID() {
	    return publisherUUID;
	}
	
	// -- get\set last access time
	private void updateTime(long backTime) {
		lastUpdated.setTime(System.currentTimeMillis() - backTime);
	}
	private Date getUpdateTime() {
		return lastUpdated;
	}
	
	// --- manage listeners ------------------
	public final synchronized boolean addListener(EventLogListener paramE) {
		return listeners.add(paramE);
	}
	
	/**
	 * Удаление заданного listener из рассылки.
	 * @param paramObject - экземпляр EventLogListener
	 * @return true - если удаление произведено, false - в противном случае.
	 */
	public final synchronized boolean removeListener(EventLogListener paramObject) {
		return listeners.remove(paramObject);
	}
	
	/**
	 * Удаление всех listener-ов из списка рассылки.
	 */
	public final synchronized void clearListeners() {
		listeners.clear();
	}
	
	// --- eventTypes --- 4425
	
	public EventPublisher addEventType(String eventType) {
		if (!(eventTypes.contains(eventType))) {
			this.eventTypes.add(eventType);
		}
		return this;
	}
	
	public boolean removeEventType(String eventType) {
		return eventTypes.remove(eventType);
	}
	
	public void clearEventTypes() {
		eventTypes.clear();
	}
	
	// --- event Level ---
	
	public void addEventLevel(String level) {
		if (!(eventLevels.contains(level))) {
			eventLevels.add(level);
		}
	}
	
	public boolean removeEventLevel(String level) {
		return eventLevels.remove(level);
	}
	
	public void clearEventLevels() {
		eventLevels.clear();
	}
	
	// -- metadata objectTypes
	public void addMetadataType(String typeName) {
		if (!(dataObjectTypes.contains(typeName))) {
			dataObjectTypes.add(typeName);
		}
	}
	
	public boolean removeMetadataType(String typeName) {
		return dataObjectTypes.remove(typeName);
	}
	
	public void clearMetadataTypes() {
		dataObjectTypes.clear();
	}
	
	/**
	 * Creates a new timer. The associated thread does not run as a daemon.
	 * @return экземпляр объекта Timerb
	 */
	public static Timer createTimer() {
	    return new Timer();
	}
	
	/**
	 * Creates a new timer whose associated thread may be specified 
	 * to run as a daemon. A deamon thread is called for if the timer 
	 * will be used to schedule repeating "maintenance activities", 
	 * which must be performed as long as the application is running, 
	 * but should not prolong the lifetime of the application.
	 * @param deamon - true if the associated thread should run as a daemon.
	 * @return экземпляр объекта Timer
	 * @see java.util.Timer
	 */
	public static Timer createTimer(boolean deamon) {
	    return new Timer(deamon);
	}
	
	public static void schedule(final Timer timer, final EventPublisher publisher, int delay, int period) {
	    timer.schedule(publisher, delay, period);
	}
	
	@Override
	public final synchronized void run() {
	    long start = System.currentTimeMillis();
		if (!(app.ping())) {
			try {
				logger.info("Harvester [" + getPublisherUUID() + "] reconnecting...");
				app.reconnect();
				logger.info("Harvester [" + getPublisherUUID() + "] connected again");
			} catch (JIException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		Date lastAccessTime = getUpdateTime();
		manager.setAppInstance(app); // prevent corruption
		boolean timeUpdateAllowed = true;
		try {
			Document log = manager.getEventLog(
				lastAccessTime, 
				null,
				eventLevels.toArray(new String[eventLevels.size()]),
				eventTypes.toArray(new String[eventTypes.size()]), 
				dataObjectTypes.toArray(new String[dataObjectTypes.size()]));
			
			XPathSelector selector = new XPathSelector(app);
			int eventCount = selector.selectForInt(
					log, 
					"count(/v8e:EventLog/v8e:Event)"
					);
			
			if (eventCount > 0) {
				for (EventLogListener ell : listeners) {
					ell.processEvent(Utils.document2XML(log));
				}
			}
			
		} catch (JIException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (ParserConfigurationException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (SAXException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (IOException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (SecurityException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (IllegalArgumentException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (NoSuchMethodException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (IllegalAccessException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (InvocationTargetException e) {
		    logger.error(e.getMessage(), e);
		    populateException(e);
		    timeUpdateAllowed = false;
		} catch (TransformerFactoryConfigurationError e) {
			 logger.fatal(e.getMessage(), e);
			 timeUpdateAllowed = false;
		} catch (TransformerException e) {
			 logger.error(e.getMessage(), e);
			 populateException(e);
			 timeUpdateAllowed = false;
		} catch (XPathExpressionException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			timeUpdateAllowed = false;
		}
		long workTime = (System.currentTimeMillis() - start);
		
		if (timeUpdateAllowed) {
			// чтобы не пропустить события пока мы выполняли запрос и обработку
			updateTime(workTime); 
		}
		
		logger.info("Harvester [" + getPublisherUUID() + "] time: " + workTime);
	}
	
	/**
	 * Передача исключения всем зарегистрированным EventLogListener.
	 * @param e - Exception
	 */
	private void populateException(final Exception e) {
		for (EventLogListener ell : listeners) {
			ell.setLastException(e);
		}
	}
	
}
