/**
 * 
 */
package com.ipc.oce.events;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.jinterop.dcom.common.JIException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCArray;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCTextReader;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;
import com.ipc.oce.objects._OCAbstractManager;
import com.ipc.oce.varset.EEventLogLevel;

/**
 * Менеджер выгрузки и получения "журнала регистраций" 1С (EventLog). 
 * Алгоритм: 
 * 	1) выгрузка файла EventLog в формате XML на хост 1С 
 * 	2) "подхватывание" файла в текстовом режиме
 * 	3) удаление файла 
 * @author Konovalov
 *
 */
public class EventManager {
	
	public static final String FLD_COMPUTER = "Computer";
	
	public static final String FLD_USER = "User";
	
	public static final String FLD_APPLICATION_NAME = "ApplicationName";
	
	/**
	 * Дата начала фильтрации событий.
	 */
	public static final String FLD_START_DATE = "StartDate";
	
	/**
	 * Дата конца фильтрации событий.
	 */
	public static final String FLD_END_DATE = "EndDate";
	
	/**
	 * Тип события по различным типам объектов.
	 * @see EVENT_*
	 */
	public static final String FLD_EVENT = "Event";
	
	/**
	 * Тип объекта метаданных (_OCCommonMetadataObject).
	 */
	public static final String FLD_METADATA = "Metadata";
	
	/**
	 * Тип(ы) уровней событий (INFORMATION, ERROR, WARNING, NOTE).
	 */
	public static final String FLD_EVENT_LEVEL = "Level";
	
	public static final String EVENT_ALL = null;
	public static final String EVENT_SESSION_START = "_$Session$_.Start";
	public static final String EVENT_SESSION_FINISH = "_$Session$_.Finish";
	public static final String EVENT_INFOBASE_CFG_UPD = "_$InfoBase$_.ConfigUpdate";
	public static final String EVENT_INFOBASE_DBCFG_UPD = "_$InfoBase$_.DBConfigUpdate";
	public static final String EVENT_INFOBASE_EVLOG_SETTING_UPD = "_$InfoBase$_.EventLogSettingsUpdate";
	public static final String EVENT_INFOBASE_ADMPARAM_UPD = "_$InfoBase$_.InfoBaseAdmParamsUpdate";
	public static final String EVENT_INFOBASE_MASTERNODE_UPD = "_$InfoBase$_.MasterNodeUpdate";
	public static final String EVENT_INFOBASE_REGIONAL_UPD = "_$InfoBase$_.RegionalSettingsUpdate";
	public static final String EVENT_INFOBASE_TARINFO = "_$InfoBase$_.TARInfo";
	public static final String EVENT_INFOBASE_TARMESS = "_$InfoBase$_.TARMess";
	public static final String EVENT_INFOBASE_TARIMPORTANT = "_$InfoBase$_.TARImportant";
	public static final String EVENT_DATA_NEW = "_$Data$_.New";
	public static final String EVENT_DATA_UPDATE = "_$Data$_.Update";
	public static final String EVENT_DATA_DELETE = "_$Data$_.Delete";
	public static final String EVENT_DATA_TOTALPERIOD_UPD = "_$Data$_.TotalsPeriodUpdate";
	public static final String EVENT_DATA_POST = "_$Data$_.Post";
	public static final String EVENT_DATA_UNPOST = "_$Data$_.Unpost";
	public static final String EVENT_USER_NEW = "_$User$_.New";
	public static final String EVENT_USER_UPDATE = "_$User$_.Update";
	public static final String EVENT_USER_DELETE = "_$User$_.Delete";
	public static final String EVENT_JOB_START = "_$Job$_.Start";
	public static final String EVENT_JOB_SUCCEED = "_$Job$_.Succeed";
	public static final String EVENT_JOB_FAIL = "_$Job$_.Fail";
	public static final String EVENT_JOB_CANCEL = "_$Job$_.Cancel";
	public static final String EVENT_PERFORM_ERROR = "_$PerformError$_";
	public static final String EVENT_TRANSACTION_BEGIN = "_$Transaction$_.Begin";
	public static final String EVENT_TRANSACTION_COMMIT = "_$Transaction$_.Commit";
	public static final String EVENT_TRANSACTION_ROLLBACK = "_$Transaction$_.Rollback";
	
	private OCApp appInstance = null;
	private String remoteTempDir = "";
	
	/**
	 * 1С формат даты и времени.
	 */
	private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");
	private static long fileNumber = 0;
	private boolean deleteAfterUnload = true;
	//==================================================================
	
	/**
	 * Конструктор. Создание экземпляра EventManager
	 * @param appInstance активный экземпляр OCApp
	 * @param remoteTempDirectory путь к папке, в кторорую будет производится выгрузка журнала регистрации
	 */
	public EventManager(OCApp appInstance, String remoteTempDirectory) {
		super();
		this.appInstance = appInstance;
		remoteTempDir = remoteTempDirectory;
	}

	public final void setAppInstance(OCApp appInstance) {
		this.appInstance = appInstance;
	}
	
	
	public final boolean isDeleteAfterUnload() {
		return deleteAfterUnload;
	}

	/**
	 * Установка параметра очистки папки с журналом регистраций.
	 * @param deleteAfterUnload true - после получения журнала на локальный хост, на удаленном хосте (1С) будет произведена очистка каталога. false - удаление производится не будет.
	 */
	public final void setDeleteAfterUnload(boolean deleteAfterUnload) {
		this.deleteAfterUnload = deleteAfterUnload;
	}

	/**
	 * Путь до временной директории храннения/выгрузки EventLog на стороне 1С.
	 * @return
	 */
	protected String getTempDirectory() {
		return remoteTempDir;
	}
	
	/**
	 * Получение журнала регистраций 1С. Базовый метод для всех остальных getLog.
	 * @param OCStructure
	 * @return строковое представление xml данных журнала
	 * @throws JIException
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Document getEventLog(OCStructure parameters) throws JIException, ParserConfigurationException, SAXException, IOException {
		String fileName = generateFileName();
		String fullPath = remoteTempDir + "\\" + fileName;
		
		// unloading event log to file
		appInstance.unloadEventLog(fullPath, parameters, null, null);
		
		// read xml event's file to String
		OCTextReader textReader = appInstance.newTextReader();
		textReader.open(fullPath, "UTF-8");
		String res = textReader.read();
		textReader.close();
		
		if (isDeleteAfterUnload()) {
			// delete event's files
			appInstance.deleteFiles(remoteTempDir, fileName);
		}
		return com.ipc.oce.xml.xerces.Utils.xml2Document(res);
	}
	
	/**
	 * Получение журнала регистраций 1С.
	 * @param startDate
	 * @param endDate
	 * @param eventTypes
	 * @param metadataObject
	 * @return org.w3c.dom.Document представление журнала
	 * @throws JIException
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Document getEventLog(Date startDate, Date endDate, String[] eventTypes, _OCCommonMetadataObject[] metadataObject) throws JIException, ParserConfigurationException, SAXException, IOException {		
		return getEventLog(startDate, endDate, null, eventTypes, metadataObject);
	}
	
	/**
	 * Метод аналог getEventLog(Date startDate, Date endDate, EEventLogLevel[]
	 * eventLevels, String[] eventTypes, _OCCommonMetadataObject[]
	 * metadataObject) только EEventLogLevel и _OCCommonMetadataObject
	 * передаются как строковые массивы соответствующих имен.
	 * 
	 * @param startDate
	 *            начальное время выборки. null - с самого начала.
	 * @param endDate
	 *            конечное время выборки. null - до самого конца.
	 * @param eventLevels
	 *            - уровень сообщений (информационные, ошибки, предупреждения и
	 *            нотификации)
	 * @param eventTypes
	 * @param metadataObjects
	 * @return
	 * @throws JIException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Document getEventLog(Date startDate, Date endDate, String[] eventLevels, String[] eventTypes, String[] metadataObjects) throws JIException, ParserConfigurationException, SAXException, IOException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
	    EEventLogLevel[] eEventLevels = null;
	    if (eventLevels != null && eventLevels.length > 0) {
	    	eEventLevels = new EEventLogLevel[eventLevels.length];
			int index = 0;
			for (String eLevel : eventLevels) {
			    EEventLogLevel eLevelObj  = appInstance.findVarset(eLevel);
			    eEventLevels[index++] = eLevelObj;
			}
	    }
	    
	    _OCCommonMetadataObject[] metadataObjectsBnd = null;
	    if (metadataObjects != null && metadataObjects.length > 0) {
			metadataObjectsBnd = new _OCCommonMetadataObject[metadataObjects.length];
			int index = 0;
			for (String metaFullName : metadataObjects) {
			    _OCAbstractManager abstractManager = appInstance.findManager(metaFullName);
			    _OCCommonMetadataObject commonMetadataObject = abstractManager.getMetadata();
			    metadataObjectsBnd[index++] = commonMetadataObject;
			}
	    }
	    
	    return getEventLog(startDate, endDate, eEventLevels, eventTypes, metadataObjectsBnd);
	}
	
	/**
	 * Получение журнала регистраций 1С.
	 * @param startDate
	 * @param endDate
	 * @param eventLevels
	 * @param eventTypes
	 * @param metadataObject
	 * @return
	 * @throws JIException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document getEventLog(Date startDate, Date endDate, EEventLogLevel[] eventLevels, String[] eventTypes, _OCCommonMetadataObject[] metadataObject) throws JIException, ParserConfigurationException, SAXException, IOException {		
		// prepare parameters
		OCStructure structure = appInstance.newStructure();
		
		//setup event's filtraton
		if (eventTypes != null && eventTypes.length > 0) {
			OCArray array = appInstance.newArray();
			for (String eventName : eventTypes) {
				array.add(new OCVariant(eventName));
			}
			structure.insert(FLD_EVENT, array);
		}
		
		// setup start date filtration
		if (startDate != null) {
			structure.insert(FLD_START_DATE, startDate);
		}
		
		// setup end date filtration
		if (endDate != null) {
			structure.insert(FLD_END_DATE, endDate);
		}

		// setup metadata filtration
		if (metadataObject != null && metadataObject.length > 0) {
			OCArray array = appInstance.newArray();
			for (_OCCommonMetadataObject cmo : metadataObject) {
				OCVariant variant = new OCVariant(cmo);
				array.add(variant);
			}
			structure.insert(FLD_METADATA, array);
		}
		
		// insert levels' condition
		if (eventLevels != null && eventLevels.length > 0) {
			OCArray array = appInstance.newArray();
			for (EEventLogLevel level : eventLevels) {
				OCVariant variant = new OCVariant(level);
				array.add(variant);
			}
			structure.insert(FLD_EVENT_LEVEL, array);
		}
		
		return getEventLog(structure);
	}
	
	
	
	/**
	 * Получение журнала регистраций 1С.
	 * @param startDate
	 * @param endDate
	 * @param eventType
	 * @return org.w3c.dom.Document представление журнала
	 * @throws JIException
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Document getEventLog(Date startDate, Date endDate, String eventType) throws JIException, ParserConfigurationException, SAXException, IOException {		
		return getEventLog(startDate, endDate, (EEventLogLevel[]) null, new String[]{eventType}, (_OCCommonMetadataObject[]) null);
	}
	
	/**
	 * Получение всего журнала регистраций 1С. 
	 * @return org.w3c.dom.Document представление журнала
	 * @throws JIException
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Document getEventLog() throws JIException, ParserConfigurationException, SAXException, IOException {
		return getEventLog((OCStructure) null);
	}
	
	/**
	 * Генерация имени файла для выгрузки отчета.
	 * @return
	 */
	private synchronized String generateFileName() {
		StringBuffer sb = new StringBuffer(30);
		sb.append(DATA_FORMAT.format(new Date()));
		
		sb.append("_").append(Long.valueOf(fileNumber).toString()).append(".xml");
		fileNumber++;
		return sb.toString();
	}
	
}
