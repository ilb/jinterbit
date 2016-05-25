/**
 * 
 */
package com.ipc.oce.camel;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;

/**
 * @author Konovalov
 *
 */
public class OCXEEndpoint extends ScheduledPollEndpoint {
	
	private static final transient Log LOG = LogFactory.getLog(OCXEEndpoint.class);
	
	/**
	 * Active instance of OCApp (1C Application connection).
	 */
	private OCApp appInstance = null;
	
	/**
	 * Хост сервера DCOM.
	 */
	private String host = null;
	
	/**
	 * Логин пользователя при подключении к DCOM.
	 */
	private String login = null;
	
	/**
	 * Пароль пользователя при подключении к DCOM.
	 */
	private String password = null;
	
	/**
	 * Путь в файловой системе до базы данных 1С.
	 */
	private String dbpath = null;
	
	/**
	 * Логин пользователя при подключении к базе данных 1С.
	 */
	private String dbuser = null;
	
	/**
	 * Пароль пользователя при подключении к базе данных 1С.
	 */
	private String dbpassword = null;
	
	/**
	 * Наименование сервера базы 1С.
	 */
	private String dbsrvr = null;
	
	/**
	 * Ссылка на базу 1С.
	 */
	private String dbref = null;

	//=================================
	// Parameters for scheduledConsumer
	private int delay = 5000;
	
	private int initialDelay = 0;
	
	private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
	//Если указано null, то путь для временных файлов будет получен методом 1С
	//Существование явно указанного пути не проверяется (если путь отсутствует, будет получена ошибка 1С)
	private String tempDir = null; // "C:\\Developer\\Temp\\forDelete";
	
	private List<String> eventTypes = null;
	
	private List<String> listenForObjects = null;
	
	//=================================
	
	public OCXEEndpoint(String uri, OCXEComponent component) throws SecurityException, JIException, IOException {
		super(uri, component);
		
		appInstance = OCApp.getNewInstance();
		
		if (component.getDriver() != null) {
			appInstance.setApplicationDriver(component.getDriver());
		}
	}

	public DataObjectProducer createProducer() throws Exception {
		DataObjectProducer producer = new DataObjectProducer(this);
		return producer;
	}

	public Consumer createConsumer(Processor processor) throws Exception {
		Consumer consumer = new ScheduledEventConsumer(this, processor);
		return consumer;
	}

	public boolean isSingleton() {
		return true;
	}

	protected OCApp getApp_instance() {
		return appInstance;
	}

	protected String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	protected String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	protected String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	protected String getDbpath() {
		return dbpath;
	}

	public void setDbpath(String dbpath) {
		this.dbpath = dbpath;
	}

	protected String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	protected String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	
	protected String getDbsrvr() {
		return dbsrvr;
	}

	public void setDbsrvr(String dbsrvr) {
		this.dbsrvr = dbsrvr;
	}

	protected String getDbref() {
		return dbref;
	}

	public void setDbref(String dbref) {
		this.dbref = dbref;
	}

	protected int getDelay() {
		return delay;
	}

	protected int getInitialDelay() {
		return initialDelay;
	}

	protected TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setInitialDelay(int initialDelay) {
		this.initialDelay = initialDelay;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = TimeUnit.valueOf(timeUnit);
	}
	
	protected String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	protected List<String> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(List<String> eventTypes) {
		this.eventTypes = eventTypes;
	}

	protected List<String> getListenForObjects() {
		return listenForObjects;
	}

	public void setListenForObjects(List<String> listenForObjects) {
		this.listenForObjects = listenForObjects;
	}

	private int connect() throws SecurityException, JIException, IOException {
	    	Properties prp = new Properties();
	    	prp.put(PropertiesReader.OCE_CFG_HOST, getHost());
	    	prp.put(PropertiesReader.OCE_CFG_HOST_USER, getLogin());
	    	prp.put(PropertiesReader.OCE_CFG_HOST_PASSWORD, getPassword());
	    	if (getDbpath() != null) {
	    		prp.put(PropertiesReader.OCE_CFG_1CDB_PATH, getDbpath());
	    	}
	    	prp.put(PropertiesReader.OCE_CFG_1CDB_USER, getDbuser());
	    	prp.put(PropertiesReader.OCE_CFG_1CDB_PASSWORD, getDbpassword());
	    	if (getDbsrvr() != null) {
	    		prp.put(PropertiesReader.OCE_CFG_1CSRVR, getDbsrvr());
	    	}
	    	if (getDbref() != null) {
	    		prp.put(PropertiesReader.OCE_CFG_1CREF, getDbref());
	    	}
		int sessionNumber = appInstance.connect(prp);
		return sessionNumber;
	}

	@Override
	public void doStart() throws Exception {
		LOG.info("OCE Endpoint start");
		int sessionNumber = connect();
		LOG.info("Instance connected: " + sessionNumber);
		super.start();
	}
	
	@Override
	public void doStop() throws Exception {
		LOG.info("OCE Endpoint closing...");
		try {
			getApp_instance().exit();
		} catch (JIException jie) {
			jie.printStackTrace();
		}
		LOG.info("OCE Endpoint closed");
		super.stop();
	}
	
	
	
}
