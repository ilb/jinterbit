package com.ipc.oce;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.UUID;

import javax.xml.namespace.NamespaceContext;

import com.ipc.oce.metadata.AccessRight;
import com.ipc.oce.metadata.objects.OCRoleMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.msa.ComApp;
import com.ipc.oce.exceptions.ConfigurationException;
import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.objects.OCAccountingRegisterCollection;
import com.ipc.oce.objects.OCAccountingRegisterManager;
import com.ipc.oce.objects.OCAccumulationRegisterCollection;
import com.ipc.oce.objects.OCCatalogCollection;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCChartOfAccountsManager;
import com.ipc.oce.objects.OCChartOfCharacteristicTypesManager;
import com.ipc.oce.objects.OCChartsOfAccountCollection;
import com.ipc.oce.objects.OCChartsOfCharacteristicTypeCollection;
import com.ipc.oce.objects.OCConstantCollection;
import com.ipc.oce.objects.OCConstantManager;
import com.ipc.oce.objects.OCDocumentCollection;
import com.ipc.oce.objects.OCDocumentJournalCollection;
import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCEnumCollection;
import com.ipc.oce.objects.OCEnumManager;
import com.ipc.oce.objects.OCEnumRef;
import com.ipc.oce.objects.OCExchangePlanManager;
import com.ipc.oce.objects.OCExchangePlansCollection;
import com.ipc.oce.objects.OCInfoBaseUsersManager;
import com.ipc.oce.objects.OCInformationRegisterCollection;
import com.ipc.oce.objects.OCInformationRegisterManager;
import com.ipc.oce.objects.OCUUID;
import com.ipc.oce.objects._OCCommonRef;
import com.ipc.oce.objects.OCInfoBaseUser;
import com.ipc.oce.objects.reports.OCReportCollection;
import com.ipc.oce.query.OCQuery;
import com.ipc.oce.varset.EActivator;
import com.ipc.oce.xml.oc.OCDOMBuilder;
import com.ipc.oce.xml.oc.OCDOMWriter;
import com.ipc.oce.xml.oc.OCXDTOFactory;
import com.ipc.oce.xml.oc.OCXDTOPackageCollection;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.ipc.oce.xml.oc.OCXMLDataType;
import com.ipc.oce.xml.oc.OCXMLReader;
import com.ipc.oce.xml.oc.OCXMLSchemaBuilder;
import com.ipc.oce.xml.oc.OCXMLSchemaSet;
import com.ipc.oce.xml.oc.OCXMLWriter;

/**
 * Реализация методов 1С. Фактически методы класса это методы и свойства
 * глобального контекста 1С. Глобальный контекст инициализируется при открытии
 * конфигурации в режиме "1С:Предприятие" и существует вплоть до ее закрытия.
 * Все свойства, процедуры и функции глобального контекста доступны в любом
 * программном модуле конфигурации. Доступ к свойствам, процедурам и функциям
 * глобального контекста осуществляется непосредственно из любого программного
 * модуля, используя их имена (без ссылки на какой-либо объект).
 * 
 * 
 * @author Konovalov
 * 
 */
public class OCApp extends OCObject {
	
	/**
	 * Экземпляр транзакции в текущей сессии. 
	 */
	private Transaction transaction = null;
	
	/**
	 * Флаг использования подключения к файловой базе 1С. 
	 */
	private static final int CON_FILEBASED = 10;
	
	/**
	 * Флаг использования подключения к серверной базе 1С.
	 */
	private static final int CON_SERVERBASED = 15;
	
	/**
	 * Тип подключения неопознан.
	 */
	private static final int CON_UNKNOWN = 20;

	/**
	 * Адрес DCOM сервера в сети (IP или hostname).
	 */
	private String host = null;
	
	/**
	 * Домен пользователя сервера DCOM.
	 */
	private String domain = null;
	
	/**
	 * Логин на DCOM-сервер.
	 */
	private String login = null;
	
	/**
	 * Пароль на DCOM-сервер.
	 */
	private String password = null;

	private String dbpath = null;
	private String dbsrvr = null;
	private String dbref = null;

	/**
	 * Тип соединения текущей сессии.
	 */
	private int connectionType = CON_FILEBASED;
	
	/**
	 * Пользователь 1С.
	 */
	private String dbuser = null;
	
	/**
	 * Пароль пользователя 1С.
	 */
	private String dbpassword = null;

	/**
	 * Текущая верия библиотеки.
	 */
	public static final String VERSION = "0.5.4";

	/**
	 * Логгер.
	 */
	private static final transient Log LOG = LogFactory.getLog(OCApp.class);

	private static InstancePool instancesStorage = null;

	/**
	 * Экземплят корневого объекта сессии. DISPATCH этого объекта == DISPATCH
	 * OCApp
	 */
	private final ComApp pComApp = new ComApp();
	
	/**
	 * Драйвер определяющий поведение приложения для конкретной версии 1С.
	 */
	private ApplicationDriver driver = null;

	static {
		// use simple as a default storage
		instancesStorage = new SimpleInstancePool();
	}

	/**
	 * Создание нового объекта (экземпляра) контекста 1С. (соединение не
	 * произодится @see connect).
	 * 
	 * @return новый OCApp
	 */
	public static OCApp getNewInstance() {
		return new OCApp();
	}

	/**
	 * Метод делигат для InstanceStore.get .
	 * 
	 * @param sessionCode - номер DCOM-сессии.
	 * @return существующий экземпляр OCApp
	 */
	public static OCApp getInstance(final int sessionCode) {
		return instancesStorage.get(sessionCode);
	}
	
	@SuppressWarnings("deprecation")
	protected OCApp() {
		// setup default driver
		driver = new DefaultApplicationDriver();
		driver.getVariantGUIDTable().setAppInstance(this);
	}

	// ===================================================================================

	/**
	 * Откритие соединения с COM-сервером (не с 1С).
	 * @param aServer - сетевой адрес сервера DCOM.
	 * @param aUser - пользователь DCOM.
	 * @param aPassword - пароль пользователя DCOM.
	 */
	private void open(final String aServer, final String aUser, final String aPassword) 	throws JIException, SecurityException, IOException {
		open(aServer, aServer, aUser, aPassword);
	}
	
	/**
	 * Откритие соединения с COM-сервером (не с 1С).
	 * @param server - сетевой адрес сервера DCOM.
	 * @param domain - домен пользователя DCOM сервера
	 * @param user - пользователь DCOM.
	 * @param aPassword - пароль пользователя DCOM.
	 * @throws JIException
	 * @throws SecurityException
	 * @throws IOException
	 */
	private void open(final String server, String domain, final String user, final String aPassword) 	throws JIException, SecurityException, IOException {
		pComApp.setAutoRegistration(driver.getAutoRegistration());
		pComApp.open(server, domain, user, aPassword, driver.getcApplicationId(), driver.getcApplIdIsClsId());
		setDispatch(pComApp.dispatch());
		//int maxConnections = get("MaxConnections").getObjectAsUnsigned().getValue().intValue();
		//int poolCapacity = get("PoolCapacity").getObjectAsUnsigned().getValue().intValue();
	}

	/**
	 * Установка GlobalTimeout.
	 * 
	 * @param value
	 *            время в миллисекундах
	 */
	public final void setGlobalTimeout(final int value) {
		if (pComApp == null) {
			throw new IllegalStateException(
					"Connect to server first. Session isn't active");
		}
		pComApp.setGlobalTimeout(value);
	}

	/**
	 * Получение общего таймуата.
	 * 
	 * @return время в миллисекундах
	 */
	public final int getGlobalTimeout() {
		if (pComApp == null) {
			throw new IllegalStateException(
					"Connect to server first. Session isn't active");
		}
		return pComApp.getGlobalTimeout();
	}

	/**
	 * Полученике хранилища экземпляров.
	 * 
	 * @return InstancePool
	 */
	public static final InstancePool getInstancesStorage() {
		return instancesStorage;
	}

	/**
	 * Установка специфического хранилища экземпляров.
	 * 
	 * @param instancesStorage
	 *            экземпляр реализации InstancePool
	 */
	public static synchronized final void setInstancesStorage(InstancePool instancesStorage) {
		OCApp.instancesStorage = instancesStorage;
	}

	/**
	 * Установка драйвера приложения. Версия драйвера должна соответствовать
	 * подключаемой версии.
	 * 
	 * @param driver - ApplicationDriver
	 *            или null для default-драйвера
	 */
	public final void setApplicationDriver(ApplicationDriver driver) {
		if (driver == null) {
			driver = new DefaultApplicationDriver();
		}
		this.driver = driver;
		// set instance to variable table
		this.driver.getVariantGUIDTable().setAppInstance(this);
	}
	
	/**
	 * Получение статичных полей. Аналог варсетов и EActivator. Возможно
	 * механизм варсетов будет depricated.
	 * 
	 * @param qField
	 *            полное имя константы (Например: DocumentPostingMode.Regular)
	 * @return OCObject
	 * @throws JIException - ошибка платформы или DCOM
	 */
	public final StaticFieldInstance getStaticFields(final String qField) 
								throws JIException {
		if (qField == null) {
			return null;
		}
		String[] split = qField.split("\\.");
		int deep = split.length;
		StaticFieldInstance res = new StaticFieldInstance(get(split[0]));
		for (int z = 1; z < deep; z++) {
			res = new StaticFieldInstance(res.get(split[z]));
		}
		res.setFieldName(qField);
		return res;
	}

	/**
	 * Получение разборщика синонимов.
	 *  
	 * @return TypeSynonymParser
	 */
	public final TypeSynonymParser getTypeSynonymParser() {
		return driver.getTypeSynonymParser();
	}
	
	/**
	 * Получение фабрики Varset-ов.
	 * @return VariantFactory
	 */
	protected final VariantFactory getVariantFactory() {
		return driver.getVariantGUIDTable();
	}

	/**
	 * Short path for getVariantFactory.findVarset .
	 * 
	 * @param <T> тип возвращаемого объекта 
	 * (один из com.ipc.oce.varset.E*) расширяющих EActivator
	 * @param name полное имя объекта, например (EEventLogLevel.INFORMATION)
	 * @return Экземпляр варсета или null если объект не найден
	 * @throws JIException ошибка DCOM.
	 */
	public final <T extends EActivator> T findVarset(final String name)
			throws JIException {
		return getVariantFactory().findAndBindObject(name);
	}
	
	/**
	 * Получение общего модуля.
	 * @param moduleName - Имя общего модуля.
	 * @return
	 * @throws JIException
	 */
	public OCCommonModule getCommonModule(String moduleName) throws JIException {
		return new OCCommonModule(get(moduleName));
	}
	
	/**
	 * Осуществляет поиск всех помеченных на удаление объектов. Примечаение:
	 * весьма медленный метод.
	 * 
	 * @return Массив со списком ссылок помеченных на удаление объектов
	 * @throws JIException
	 */
	public OCArray findMarkedForDeletion() throws JIException {
		return new OCArray(callMethodA("FindMarkedForDeletion"));
	}

	/**
	 * Предоставляем NamespaceContext для EventLog xml.
	 * 
	 * @return
	 */
	public final NamespaceContext getNamespaceContext() {
		return driver.getNamespaceContext();
	}

	private void close() throws JIException {
		pComApp.close();
	}

	/**
	 * Вызов конструктора по умолчанию.
	 * 
	 * @param aName - имя объекта
	 * @return IJIDispatch
	 * @throws JIException
	 */
	protected IJIDispatch newObject(String aName) throws JIException {
		return newObject(aName, (JIVariant[]) null);
	}

	/**
	 * Вызов конструктора с одним параметром.
	 * 
	 * @param aName имя объекта.
	 * @param variant аргументы конструктора
	 * @return IJIDispatch
	 * @throws JIException
	 */
	protected IJIDispatch newObject(String aName, JIVariant variant)
			throws JIException {
		return newObject(aName, new JIVariant[] { variant });
	}

	/**
	 * Вызов конструктора с параметрами.
	 * 
	 * @param aName имя объекта
	 * @param variant
	 *            параметры конструктора. Если вызывается конструктор по
	 *            умолчанию, то null
	 * @return
	 * @throws JIException
	 */
	protected IJIDispatch newObject(String aName, JIVariant[] variant)
			throws JIException {
		if (variant == null) {
			variant = new JIVariant[0];
		}
		Object[] params = new Object[1 + variant.length];
		params[0] = new JIVariant(aName);
		for (int z = 0; z < variant.length; z++) {
			params[z + 1] = variant[z];
		}
		JIVariant[] obj = callMethodA("NewObject", params);
		return (obj.length == 0) ? null : ComApp.toDispatch(obj[0]);
	}

	/**
	 * Создание нового объекта Запрос.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCQuery newQuery() throws JIException {
		return new OCQuery(newObject("Query"));
	}

	/**
	 * Создание нового объекта Запрос.
	 * 
	 * @param query
	 *            строка запроса
	 * @return
	 * @throws JIException
	 */
	public OCQuery newQuery(String query) throws JIException {
		OCQuery queryObject = new OCQuery(newObject("Query"));
		queryObject.setText(query);
		return queryObject;
	}

	/**
	 * Создание объекта структуры. Используется в запросах
	 * 
	 * @return экземпляр структуры
	 * @throws JIException
	 */
	public OCStructure newStructure() throws JIException {
		return new OCStructure(newObject("Structure"));
	}
	
	/**
	 * Создание нового списка значений.
	 * @return OCValueList
	 * @throws JIException
	 */
	public OCValueList newValueList() throws JIException {
		return new OCValueList(newObject("ValueList"));
	}
	
	public OCValueTable newValueTable() throws JIException {
		return new OCValueTable(newObject("ValueTable"));
	}

	/**
	 * Создание объекта массив.
	 * 
	 * @return экземпляр массива
	 * @throws JIException
	 */
	public OCArray newArray() throws JIException {
		return new OCArray(newObject("Array"));
	}

	/**
	 * Создание объекта массив.
	 * 
	 * @param size
	 *            - размер массива
	 * @return экземпляр массива
	 * @throws JIException
	 */
	public OCArray newArray(int size) throws JIException {
		return new OCArray(newObject("Array", new JIVariant(size)));
	}

	/**
	 * Создание нового объекта XMLWriter.
	 * 
	 * @return экземпляр XMLWriter
	 * @throws JIException
	 */
	public OCXMLWriter newXMLWriter() throws JIException {
		return new OCXMLWriter(newObject("XMLWriter"));
	}
	
	/**
	 * Получает тип данных XML, соответствующий переданному в качестве параметра
	 * типу.
	 * 
	 * @param type
	 *            - Тип. Тип значения, для которого необходимо определить
	 *            соответствующий XML тип.
	 * @return OCXMLDataType
	 * @throws JIException
	 */
	public final OCXMLDataType getXMLType(OCType type) throws JIException {
		return new OCXMLDataType(callMethodA("XMLType", new JIVariant(ocObject2Dispatch(type)))[0]);
	}
	
	/**
	 * Выполняет преобразование из строки, полученной из текста элемента или
	 * значения атрибута XML, в значение в соответствии с указанным типом.
	 * Выполняет действие обратное действию метода XMLСтрока.
	 * 
	 * @param type
	 *            - Тип, значение которого надо получить при преобразовании из
	 *            строкового представления XML.
	 * @param xmlString
	 *            - Строка, содержащая строковое представление значения,
	 *            полученное из XML.
	 * @return Неопределено, Null, Булево, Число, Строка, Дата,
	 *         УникальныйИдентификатор, ДвоичныеДанные, ХранилищеЗначения,
	 *         значения перечислений, значения системных перечислений (ВидСчета,
	 *         ВидДвиженияБухгалтерии, ВидДвиженияНакопления, ДопустимыйЗнак,
	 *         ДопустимаяДлина, ЧастиДаты) или все ссылки на объекты базы
	 *         данных.
	 * @throws JIException
	 */
	public final OCVariant getXMLValue(OCType type, String xmlString) throws JIException {
		return new OCVariant(callMethodA("XMLValue", new Object[]{new JIVariant(ocObject2Dispatch(type)), new JIVariant(xmlString)})[0]);
	}
	
	/**
	 * Производит проверку возможности чтения из XML значения указанного типа.
	 * Данный метод получает тип данных XML из объекта ЧтениеXML, а затем
	 * пытается определить, имеется ли соответствующий тип 1С:Предприятия,
	 * аналогично тому как это делает метод ПолучитьXMLТип.
	 * 
	 * @param reader - Объект, через который производится чтение XML.
	 * @return Истина - тип 1С:Предприятия существует; Ложь - в противном случае.
	 * @throws JIException
	 */
	public boolean canReadXML(OCXMLReader reader) throws JIException {
		return callMethodA("CanReadXML", new JIVariant(ocObject2Dispatch(reader)))[0].getObjectAsBoolean();
	}
	
	/**
	 * Получает тип данных XML, соответствующий типу переданного в качестве
	 * параметра значения.
	 * 
	 * @param object
	 *            - Произвольный. Значение, для типа которого нужно получить
	 *            соответствующий тип данных XML.
	 * @return ТипДанныхXML, Неопределено. Если тип данных XML, соответствующий
	 *         типу переданного в качестве параметра значения определен, то
	 *         метод возвращает тип данных XML. Если не определен, то метод
	 *         возвращает Неопределено.
	 * @throws JIException
	 */
	public OCXMLDataType getXMLTypeOf(Object object) throws JIException {
		OCVariant var = new OCVariant(object);
		JIVariant jVar = callMethodA("XMLTypeOf", var.getJIVariant())[0];
		if (jVar.getType() != JIVariant.VT_EMPTY) {
			return new OCXMLDataType(jVar);
		} else {
			return null;
		}
	}
	
	/**
	 * Получает XML представление значения для помещения в текст элемента или
	 * значение атрибута XML. XML представление зависит от типа значения.
	 * 
	 * @param object
	 *            - Null, Булево, Число, Строка, Дата, УникальныйИдентификатор,
	 *            ДвоичныеДанные, ХранилищеЗначения, значения перечислений,
	 *            значения системных перечислений (ВидСчета,
	 *            ВидДвиженияБухгалтерии, ВидДвиженияНакопления, ДопустимыйЗнак,
	 *            ДопустимаяДлина, ЧастиДаты) или все ссылки на объекты базы
	 *            данных. Значение, для которого должно быть получено строковое
	 *            представление, которое может быть использовано как текст
	 *            элемента или значение атрибута XML.
	 * @return String
	 * @throws JIException ошибка DCOM
	 */
	public final String getXMLString(final Object object) throws JIException {
		JIVariant arg = null;
		if (object instanceof OCObject) {
			arg = new JIVariant(ocObject2Dispatch((OCObject) object));
		} else {
			arg = JIVariant.makeVariant(object);
		}
		return callMethodA("XMLString", arg)[0].getObjectAsString2();
	}
	
	/**
	 * Возвращает тип, соответствующий типу данных XML. 
	 * @param xmlDataType - Тип XML, для которого нужно получить соответствующий тип. 
	 * @return Тип, Неопределено. Неопределено, если соответствующий тип данных не обнаружен. 
	 * @throws JIException
	 */
	public OCType fromXMLType(OCXMLDataType xmlDataType) throws JIException {
		JIVariant var = callMethodA("FromXMLType", new JIVariant(ocObject2Dispatch(xmlDataType)))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCType(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Получает тип, соответствующий типу данных XML. 
	 * @param xmlTypeName - Имя типа XML. 
	 * @param namespace - URI пространства имен типа XML.
	 * @return Тип, Неопределено. Неопределено, если соответствующий тип данных не обнаружен. 
	 * @throws JIException
	 */
	public OCType fromXMLType(String xmlTypeName, String namespace) throws JIException {
		JIVariant var = callMethodA("FromXMLType", new Object[]{new JIVariant(xmlTypeName), new JIVariant(namespace)})[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCType(var);
		} else {
			return null;
		}
	}
	
	
	/**
	 * Создание нового TypeDescription по имени.
	 * @param name - имя типа.
	 * @return OCTypeDescription
	 * @throws JIException
	 */
	public OCTypeDescription newTypeDescription(String name) throws JIException {
		return new OCTypeDescription(newObject("TypeDescription", new JIVariant(name)));
	}
	
	/**
	 * Short path for newTypeDescription(typeName).getType().
	 * @param typeName - имя типа.
	 * @return OCType
	 * @throws JIException
	 */
	public OCType newType(String typeName) throws JIException {
		return newTypeDescription(typeName).getType();
	}

	/**
	 * Создание нового XMLReader.
	 * 
	 * @return экземпляр XMLReader
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXMLReader newXMLReader() throws JIException {
		return new OCXMLReader(newObject("XMLReader"));
	}

	/**
	 * Создание нового TextReader.
	 * 
	 * @return OCTextReader
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCTextReader newTextReader() throws JIException {
		return new OCTextReader(newObject("TextReader"));
	}
	
	/**
	 * Создает объект и помещает в него двоичные данные, взятые из файла. 
	 * @param fileName - Имя файла, из которого будет загружено значение в виде двоичных данных 
	 * @return OCBinaryData
	 * @throws JIException
	 */
	public final OCBinaryData newBinaryData(String fileName) throws JIException {
		OCBinaryData bd = null;
		bd = new OCBinaryData(newObject("BinaryData", new JIVariant(fileName)));
		bd.setFileName(fileName);
		return bd;
	}

	/**
	 * Создает сериализатор значений 8.1 в XML на основе XDTO.
	 * 
	 * @param factory
	 *            OCXDTOFactory
	 * @return OCXDTOSerializer
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXDTOSerializer newXDTOSerialezer(final OCXDTOFactory factory)
			throws JIException {
		return new OCXDTOSerializer(newObject("XDTOSerializer", new JIVariant(
				ocObject2Dispatch(factory))));
	}

	/**
	 * Создает новый XMLSchemaBuilder.
	 * 
	 * @return OCXMLSchemaBuilder
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXMLSchemaBuilder newXMLSchemaBuilder() throws JIException {
		return new OCXMLSchemaBuilder(newObject("XMLSchemaBuilder"));
	}

	/**
	 * Создает новый экземпляр OCDOMBuilder.
	 * 
	 * @return OCDOMBuilder
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCDOMBuilder newDOMBuilder() throws JIException {
		return new OCDOMBuilder(newObject("DOMBuilder"));
	}

	/**
	 * Создает новый экземпляр OCDOMWriter.
	 * 
	 * @return OCDOMWriter
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCDOMWriter newDOMWriter() throws JIException {
		return new OCDOMWriter(newObject("DOMWriter"));
	}

	/**
	 * Создает фабрику типов XDTO по набору схем XML.
	 * 
	 * @param schemaSet
	 *            Набор схем XML, на основе которого будет создана фабрика XDTO
	 * @param packageCollection
	 *            Коллекция пакетов XDTO, которые необходимо добавить в фабрику
	 *            XDTO. Если в наборе схем присутствует схема с URI пространства
	 *            имен, соответствующему пакету XDTO из коллекции пакетов, то
	 *            будет использован имеющийся пакет, а не создан новый
	 * @return OCXDTOFactory
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXDTOFactory newXDTOFactory(final OCXMLSchemaSet schemaSet,
			final OCXDTOPackageCollection packageCollection) throws JIException {
		return new OCXDTOFactory(newObject("XDTOFactory", new JIVariant[] {
				new JIVariant(ocObject2Dispatch(schemaSet)),
				new JIVariant(ocObject2Dispatch(packageCollection)) }));
	}

	/**
	 * Создание фабрики XDTO по имени файла схемы XML.
	 * 
	 * @param path
	 *            Строка, представляющая собой путь к файлу, содержащему XML
	 *            схему
	 * @return OCXDTOFactory
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXDTOFactory createXDTOFactory(String path) throws JIException {
		return new OCXDTOFactory(callMethodA("CreateXDTOFactory",
				new Object[] { new JIVariant(path) })[0]);
	}

	/**
	 * Создание фабрики XDTO по массиву переданных имен.
	 * 
	 * @param paths
	 *            Массив путей к XML схемам, на основании которых необходимо
	 *            создать фабрику XDTO
	 * @return OCXDTOFactory
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXDTOFactory createXDTOFactory(OCArray paths) throws JIException {
		return new OCXDTOFactory(callMethodA("CreateXDTOFactory",
				new Object[] { ocObject2Dispatch(paths) })[0]);
	}

	/**
	 * Создает фабрику типов XDTO по набору схем XML.
	 * 
	 * @param schemaSet
	 *            Набор схем XML, на основе которого будет создана фабрика XDTO
	 * @return OCXDTOFactory
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXDTOFactory newXDTOFactory(final OCXMLSchemaSet schemaSet)
			throws JIException {
		return new OCXDTOFactory(
				newObject("XDTOFactory", new JIVariant[] { new JIVariant(
						ocObject2Dispatch(schemaSet)) }));
	}

	/**
	 * Новый НаборСхемXML.
	 * 
	 * @return OCXMLSchemaSet
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCXMLSchemaSet newXMLSchemaSet() throws JIException {
		return new OCXMLSchemaSet(newObject("XMLSchemaSet"));
	}

	/**
	 * Создает уникальный идентификатор из указанной строки GUID. Уникальность
	 * полученного таким образом идентификатора определяется уникальностью
	 * строки и поэтому не гарантирована.
	 * 
	 * @param uuid
	 *            Строка GUID. Строка задается в виде
	 *            "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX", где Х - символы
	 *            обозначающие шестнадцатеричное число.
	 * @return OCUUID
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCUUID createUUID(final String uuid) throws JIException {
		OCUUID ocUUID = new OCUUID(newObject("UUID", new JIVariant(uuid)));
		return ocUUID;
	}

	/**
	 * Создает новый UUID средствами 1с.
	 * 
	 * @return UUID
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCUUID createUUID() throws JIException {
		return new OCUUID(newObject("UUID"));
	}

	/**
	 * Создание нового случайного UUID средствами Java.
	 * 
	 * @return String
	 */
	public final String createUUID2() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Считывает значение в формате XML. Прочитаны могут быть значения тех
	 * типов, которые могут быть записаны методом ЗаписатьXML.
	 * 
	 * @param reader
	 *            Объект, через который происходит чтение XML.
	 * @return Значение считанного типа
	 * @throws JIException - ошибка DCOM.
	 */
	public final OCObject readXML(final OCXMLReader reader) throws JIException {
		return new OCObject(callMethodA("ReadXML",
				new Object[] { reader.dispatch() })[0]);
	}

	/**
	 * Записывает значения в формате XML без указания имени элемента. В качестве
	 * имени элемента будет использован тип значения
	 * 
	 * @param writer
	 *            Объект, через который осуществляется запись XML
	 * @param object
	 *            Записываемое в поток XML значение. Тип параметра определяется
	 *            совокупностью типов, для которых определена XML-сериализация
	 * @throws JIException - ошибка DCOM.
	 */
	public final void writeXML(final OCXMLWriter writer, final OCObject object)
			throws JIException {
		callMethod("WriteXML",
				new Object[] { new JIVariant(ocObject2Dispatch(writer)),
						new JIVariant(object.dispatch()) });
	}

	/**
	 * Записывает значения в формате XML с указанием полного имени элемента.
	 * 
	 * @param writer
	 *            Объект, через который осуществляется запись XML.
	 * @param object
	 *            Записываемое в поток XML значение. Тип параметра определяется
	 *            совокупностью типов, для которых определена XML-сериализация.
	 * @param fullName
	 *            Полное имя элемента XML, в который будет записано значение.
	 * @throws JIException - ошибка DCOM.
	 */
	public final void writeXML(final OCXMLWriter writer, final OCObject object, final String fullName)
			throws JIException {
		callMethod("WriteXML",
				new Object[] { new JIVariant(ocObject2Dispatch(writer)),
						new JIVariant(object.dispatch()),
						new JIVariant(fullName) });
	}

	/**
	 * Записывает значения в формате XML с указанием локального имени элемента и
	 * пространства имен, к которому принадлежит локальное имя.
	 * 
	 * @param writer
	 *            Объект, через который осуществляется запись XML.
	 * @param object
	 *            Записываемое в поток XML значение. Тип параметра определяется
	 *            совокупностью типов, для которых определена XML-сериализация.
	 * @param localName
	 *            Локальное имя элемента XML, в который будет записано значение.
	 * @param namespace
	 *            URI пространства имен, к которому принадлежит указанное
	 *            локальное имя.
	 * @throws JIException - ошибка DCOM.
	 */
	public final void writeXML(final OCXMLWriter writer, final OCObject object, 
			final String localName, final String namespace) throws JIException {
		callMethod("WriteXML",
				new Object[] { new JIVariant(ocObject2Dispatch(writer)),
						new JIVariant(object.dispatch()),
						new JIVariant(localName), new JIVariant(namespace) });
	}

	/**
	 * Позволяет выгрузить журнал регистрации в XML формате. Существует
	 * возможность установить фильтр, задаваемый структурой специального
	 * формата, указывающий, какие записи журнала регистрации выгружать, а также
	 * указать колонки, которые необходимо выгружать. Если указано имя входного
	 * файла, то происходит выгрузка из указанного файла.
	 * 
	 * @param outputFileName -
	 *            Строка, содержащая имя выходного XML файла.
	 * @param filter - Структура фильтра журнала регистрации
	 *        ДатаНачала/StartDate - Дата, Значение по умолчанию Неопределено;
	 *        ДатаОкончания/EndDate - Дата, Значение по умолчанию Неопределено;
	 *        Уровень/Level - Перечисление или массив перечислений, Значение по
	 *        умолчанию Неопределено; ИмяПриложения/ApplicationName - Строка или
	 *        массив строк, Значение по умолчанию Неопределено;
	 *        Пользователь/User - ПользовательИнформационнойБазы или массив
	 *        таких объектов, или Строка. Объект ПользовательИнформационнойБазы
	 *        и массив таких объектов можно получить из объекта
	 *        МенеджерПользователейИнформационнойБазы. Также можно задавать
	 *        пользователя по имени (строкой). Причем, пользователей, которые
	 *        были удалены из ИБ можно задавать только таким способом. Пустая
	 *        строка означает "пользователя по умолчанию", который используется,
	 *        когда в информационной базе нет ни одного пользователя (от имени
	 *        такого пользователя работают также фоновые задания). Если в
	 *        фильтре задается массив пользователей, то можно перемешивать в нем
	 *        значения обоих типов - строка и ПользовательИнформационнойБазы.
	 *        Значение по умолчанию Неопределено; Компьютер/Computer - Строка
	 *        или массив строк, Значение по умолчанию Неопределено;
	 *        Событие/Event - Строка или массив строк, Значение по умолчанию
	 *        Неопределено. Допустимые имена событий: _$Session$_.Start - Сеанс.
	 *        Начало; _$Session$_.Finish - Сеанс. Завершение;
	 *        _$InfoBase$_.ConfigUpdate - Информационная база. Изменение
	 *        конфигурации; _$InfoBase$_.DBConfigUpdate - Информационная база.
	 *        Изменение конфигурации базы данных;
	 *        _$InfoBase$_.EventLogSettingsUpdate - Информационная база.
	 *        Изменение параметров журнала регистрации;
	 *        _$InfoBase$_.InfoBaseAdmParamsUpdate - Информационная база.
	 *        Изменение параметров информационной базы;
	 *        _$InfoBase$_.MasterNodeUpdate - Информационная база. Изменение
	 *        главного узла; _$InfoBase$_.RegionalSettingsUpdate -
	 *        Информационная база. Изменение региональных установок;
	 *        _$InfoBase$_.TARInfo - Тестирование и исправление. Сообщение;
	 *        _$InfoBase$_.TARMess - Тестирование и исправление. Предупреждение;
	 *        _$InfoBase$_.TARImportant - Тестирование и исправление. Ошибка;
	 *        _$Data$_.New - Данные. Добавление; _$Data$_.Update - Данные.
	 *        Изменение; _$Data$_.Delete - Данные. Удаление;
	 *        _$Data$_.TotalsPeriodUpdate - Данные. Изменение периода
	 *        рассчитанных итогов; _$Data$_.Post - Данные. Проведение;
	 *        _$Data$_.Unpost - Данные. Отмена проведения; _$User$_.New -
	 *        Пользователи. Добавление; _$User$_.Update - Пользователи.
	 *        Изменение; _$User$_.Delete - Пользователи. Удаление; _$Job$_.Start
	 *        - Фоновое задание. Запуск; _$Job$_.Succeed - Фоновое задание.
	 *        Успешное завершение; _$Job$_.Fail - Фоновое задание. Ошибка
	 *        выполнения; _$Job$_.Cancel - Фоновое задание. Отмена;
	 *        _$PerformError$_ - Ошибка выполнения; _$Transaction$_.Begin -
	 *        Транзакция. Начало; _$Transaction$_.Commit - Транзакция. Фиксация;
	 *        _$Transaction$_.Rollback - Транзакция. Отмена. Метаданные/Metadata
	 *        - Объект описания метаданного или их массив, Значение по умолчанию
	 *        Неопределено; Данные/Data - Значение, тип значения – ссылка на
	 *        объект (СправочикСсылка и т.д.), Значение по умолчанию
	 *        Неопределено; ПредставлениеДанных/DataPresentation - Строка;
	 *        Комментарий/Comment - Строка; СтатусТранзакции/TransactionStatus -
	 *        Перечисление или массив перечислений, значение по умолчанию
	 *        Неопределено; Транзакция/TransactionID - Идентификатор транзакции.
	 *        Строка; Соединение/Connection - Номер соединения с информационной
	 *        базой. Число или массив чисел, значение по умолчанию Неопределено;
	 *        РабочийСервер/ServerName - Строка или массив строк, значение по
	 *        умолчанию Неопределено; ОсновнойIPПорт/Port - Номер основного IP
	 *        порт рабочего процесса. Число или массив чисел, значение по
	 *        умолчанию Неопределено; ВспомогательныйIPПорт/SyncPort - Номер
	 *        вспомогательного IP порта рабочего процесса. Число или массив
	 *        чисел, значение по умолчанию Неопределено.
	 * @param columns - Для выгрузки в XML формате доступны следующие колонки:
	 *        Уровень/Level Дата/Date Пользователь/User Компьютер/Computer
	 *        ИмяПриложения/ApplicationName Событие/Event Комментарий/Comment
	 *        Метаданные/Metadata Данные/Data
	 *        ПредставлениеДанных/DataPresentation ИмяПользователя/UserName
	 *        ПредставлениеПриложения/ApplicationPresentation
	 *        ПредставлениеСобытия/EventPresentation
	 *        ПредставлениеМетаданных/MetadataPresentation
	 *        СтатусТранзакции/TransactionStatus Транзакция/TransactionID
	 *        Соединение/Connection РабочийСервер/ServerName ОсновнойIPПорт/Port
	 *        ВспомогательныйIPПорт/SyncPort Для того, чтобы выгрузить
	 *        определенные колонки журнала регистрации необходимо перечислить их
	 *        имена, при этом необходимо имя каждой колонки отделять запятой.
	 * @param inputFileName - Строка, содержащая имя входного файла. В качестве
	 *        входного файла следует задавать ELF-файл. Это либо файл,
	 *        полученный при сохранении просматриваемого журнала регистрации в
	 *        файл (команда "Файл - Сохранить..."), либо файл 1Cv8.elf,
	 *        являющийся основным файлом журнала регистрации. В том же каталоге
	 *        могут находиться и относящиеся к нему LOG-файлы, в зависимости от
	 *        того, было ли установлено разделение по периодам.
	 * @throws JIException - ошибка DCOM.
	 */
	public final void unloadEventLog(final String outputFileName, final OCStructure filter, final String columns, final String inputFileName) throws JIException {
		callMethod("UnloadEventLog", new Object[] {
				new JIVariant(outputFileName),
				(filter != null ? new JIVariant(filter.dispatch()) : null),
				new JIVariant(columns), new JIVariant(inputFileName) });
	}

	/**
	 * Получение метаданных конфигурации.
	 * 
	 * @return OCConfigurationMetadataObject
	 * @throws JIException
	 *             - ошибка работы в DCOM
	 */
	public final OCConfigurationMetadataObject getMetadata() 
	throws JIException {
		return new OCConfigurationMetadataObject(get("Metadata"));
	}

	/**
	 * Выполнение соединения с серверной конфигурацией 1С.
	 * 
	 * @param aComServer
	 *            - адрес DCOM-сервера
	 * @param aComUser
	 *            - логин к DCOM-серверу
	 * @param aComPassword
	 *            - пароль для aComUser
	 * @param srvr
	 *            - имя сервера 1С
	 * @param ref
	 *            - ссылка на базу 1С
	 * @param aOCUser
	 *            - пользователь 1С
	 * @param aOCPassword
	 *            - пароль пользователя 1С
	 * @return номер сессии DCOM
	 * @throws JIException
	 *             - ошибка работы с DCOM
	 * @throws IOException
	 *             - IO
	 */
	private int connect2Serverbased(final String aComServer,
			final String aDomain, final String aComUser, final String aComPassword,
			final String srvr, final String ref, final String aOCUser,
			final String aOCPassword) throws JIException, IOException {
		long s = System.currentTimeMillis();
		// save settings
		host = aComServer;
		domain = aDomain != null ? aDomain : aComServer;
		login = aComUser;
		password = aComPassword;
		dbsrvr = srvr;
		dbref = ref;
		dbuser = aOCUser != null ? aOCUser : "";
		dbpassword = aOCPassword != null ? aOCPassword : "";
		connectionType = CON_SERVERBASED;

		open(host, domain, login, password);

		JIString conStr = new JIString("Srvr=\"" + dbsrvr + "\";Ref=\"" + dbref
				+ "\";Usr=\"" + aOCUser + "\";Pwd=\"" + aOCPassword + "\"");
		JIVariant[] var = callMethodA("Connect", new Object[] { conStr });
		IJIDispatch disp = ComApp.toDispatch(var[0]);
		setDispatch(disp);

		instancesStorage.put(getAssociatedSessionID(), this);
		LOG.info("OCTitbit version " + VERSION);
		LOG.info("Application " + getAssociatedSessionID() + " connected in "
				+ (System.currentTimeMillis() - s) + "ms");

		return getAssociatedSessionID();
	}

	/**
	 * Соединение с сервером 1С.
	 * 
	 * @param aComServer
	 *            - адрес COM-сенрвера
	 * @param aComUser
	 *            - имя пользователь COM
	 * @param aComPassword
	 *            - пароль пользователя COM
	 * @param aDBFile
	 *            - адрес файла с базой 1С
	 * @param aOCUser
	 *            - имя пользователя 1С, то которого будет производится
	 *            подключение к 1с
	 * @param aOCPassword
	 *            - пароль пользователя 1С
	 * @return номер COM-сессии
	 * @throws SecurityException
	 * @throws JIException
	 *             - ошибка работы с DCOM
	 * @throws IOException
	 *             - IO
	 */
	private int connect2Filebased(final String aComServer,
			final String aDomain, final String aComUser, final String aComPassword,
			final String aDBFile, final String aOCUser, final String aOCPassword)
			throws JIException, IOException {
		long s = System.currentTimeMillis();
		// save settings
		host = aComServer;
		domain = aDomain != null ? aDomain : aComServer;
		login = aComUser;
		password = aComPassword;
		dbpath = aDBFile;
		dbuser = aOCUser != null ? aOCUser : "";
		dbpassword = aOCPassword != null ? aOCPassword : "";
		connectionType = CON_FILEBASED;
		
		// Connect to DCOM-server (computer)
		open(host, domain, login, password);

		// Connect to application
		// JIString conStr = new JIString("File=\"" + aDBFile + "\";Usr=\"" +
		// aOCUser + "\";Pwd=\"" + aOCPassword + "\"");

		String connectionStr = "File=\"" + aDBFile + "\";Usr=\"" + aOCUser + "\";Pwd=\"" + aOCPassword + "\"";
		//String connectionStr = "File=" + aDBFile + ";Usr=" + aOCUser + ";Pwd=" + aOCPassword + "";

		JIString jconStr = new JIString(connectionStr);

		// System.out.println("***7");
		// FIX for V81.Connector ???
		JIVariant[] var = callMethodA("Connect", new Object[]{jconStr});
		
		//System.out.println("***8");
		
		IJIDispatch disp = ComApp.toDispatch(var[0]);
		setDispatch(disp);

		// ---- registration is starage ----
		instancesStorage.put(getAssociatedSessionID(), this);
		LOG.info("OCTitbit v" + VERSION);
		LOG.info("Application '" + getAssociatedSessionID() + "' connected in "
				+ (System.currentTimeMillis() - s) + "ms");

		return getAssociatedSessionID();
	}

	/**
	 * Выполнение соединения с 1С. Настроейка производится в соответствии с
	 * PropertieReader-ом и именем конфигурации в файле properties
	 * 
	 * @param reader
	 * @param configurationName
	 *            имя конфигурации
	 * @return номер COM-сессии
	 * @throws SecurityException
	 * @throws JIException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public int connect(PropertiesReader reader, String configurationName)
			throws JIException, IOException, ConfigurationException {
		Properties tmp = reader.getPropertiesForInstance(configurationName);

		return connect(tmp);
	}
	
	/**
	 * Выполнение соединения с 1С. Настроейка производится в соответствии с
	 * PropertieReader-ом и именем конфигурации в файле properties
	 * 
	 * @param reader
	 *            - PropertiesReader
	 * @param altConfigs
	 *            - массив имен конфигураций, который будут циклично
	 *            подставлятся пока не будет осуществлено подключение.
	 * @return номер COM-сессии
	 * @throws SecurityException
	 * @throws JIException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public int connect(PropertiesReader reader, String[] altConfigs) throws ConfigurationException, JIException, IOException {
		
		ConfigurationException ce = null;
		JIException je = null;
		IOException ie = null;
		
		for (String configurationName : altConfigs) {
			try {
				Properties tmp = reader.getPropertiesForInstance(configurationName);
				return connect(tmp);
			} catch (ConfigurationException ce1) {
				ce = ce1;
			} catch (JIException je1) {
				je = je1;
			} catch (IOException ie1) {
				ie = ie1;
			}
		}
		
		if (ce != null) {
			throw ce;
		}
		
		if (je != null) {
			throw je;
		}
		
		if (ie != null) {
			throw ie;
		}
		return -1;
	}
	
	/**
	 * Соединение с 1С.
	 * 
	 * @param properties
	 *            используется именование ключей из PropertiesReader (например
	 *            PropertiesReader.OCE_CFG_HOST)
	 * @return идентификатор сессии
	 * @throws SecurityException
	 * @throws JIException
	 * @throws IOException
	 *             - ошибка чтения файла настроек
	 * @see PropertiesReader
	 */
	public final int connect(final Properties properties) throws JIException,
			IOException {
		int conType = determinateConnectionType(properties);
		int sessNumber = -1;
		switch (conType) {
		case CON_FILEBASED:
			sessNumber = connect2Filebased(
					properties.getProperty(PropertiesReader.OCE_CFG_HOST),
					properties.getProperty(PropertiesReader.OCE_CFG_DOMAIN),
					properties.getProperty(PropertiesReader.OCE_CFG_HOST_USER),
					properties
							.getProperty(PropertiesReader.OCE_CFG_HOST_PASSWORD),
					properties.getProperty(PropertiesReader.OCE_CFG_1CDB_PATH),
					properties.getProperty(PropertiesReader.OCE_CFG_1CDB_USER),
					properties
							.getProperty(PropertiesReader.OCE_CFG_1CDB_PASSWORD));
			break;

		case CON_SERVERBASED:
			sessNumber = connect2Serverbased(
					properties.getProperty(PropertiesReader.OCE_CFG_HOST),
					properties.getProperty(PropertiesReader.OCE_CFG_DOMAIN),
					properties.getProperty(PropertiesReader.OCE_CFG_HOST_USER),
					properties
							.getProperty(PropertiesReader.OCE_CFG_HOST_PASSWORD),
					properties.getProperty(PropertiesReader.OCE_CFG_1CSRVR),
					properties.getProperty(PropertiesReader.OCE_CFG_1CREF),
					properties.getProperty(PropertiesReader.OCE_CFG_1CDB_USER),
					properties
							.getProperty(PropertiesReader.OCE_CFG_1CDB_PASSWORD));
			break;

		default:
			throw new IOException("Connection type unknown. "
					+ "Use or filebased config or serverbased");
		}
		
		driver.getVariantGUIDTable().activate();
		driver.getTypeSynonymParser().attachAppInstance(this);
		
		return sessNumber;
		
	}

	/**
	 * Определение типа подключения (Filebased или Serverbased).
	 * 
	 * @param prp
	 *            - перечерь всех настроек подключения
	 * @return тип подключения: CON_FILEBASED, CON_SERVERBASED или CON_UNKNOWN
	 */
	private static int determinateConnectionType(final Properties prp) {
		if (prp.getProperty(PropertiesReader.OCE_CFG_1CDB_PATH) != null) {
			return CON_FILEBASED;
		} else if (prp.getProperty(PropertiesReader.OCE_CFG_1CSRVR) != null
				&& prp.getProperty(PropertiesReader.OCE_CFG_1CREF) != null) {
			return CON_SERVERBASED;
		} else {
			return CON_UNKNOWN;
		}
	}

	/**
	 * Повторное подключение с параметрами заданными в метода connect. (При этом
	 * выполняется попытка закрытия - exit())
	 * 
	 * @return
	 * @throws SecurityException
	 * @throws JIException
	 * @throws IOException
	 */
	public int reconnect() throws JIException, IOException {
		try {
			exit();
		} catch (Throwable t) {
			
		}
		if (connectionType == CON_FILEBASED) {
			return connect2Filebased(host, domain, login, password, dbpath, dbuser,
					dbpassword);
		} else {
			return connect2Serverbased(host, domain, login, password, dbsrvr, dbref,
					dbuser, dbpassword);
		}
	}

	/**
	 * Ping экземпляра.
	 * 
	 * @return true экземпляр подключен, false - соединение разорвано
	 */
	public boolean ping() {
		boolean res = false;
		try {
			getComputerName();
			res = true;
		} catch (Throwable tr) {

		}
		return res;
	}
	
	/**
	 * Получает номер текущего соединения с информационной базой. 
	 * @return
	 * @throws JIException
	 */
	public int getInfoBaseConnectionNumber() throws JIException {
		return callMethodA("InfoBaseConnectionNumber").getObjectAsInt();
	}

	/**
	 * Создание нового экземпляра транзации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Transaction createTransaction() throws JIException {
		if (transaction != null && transaction.inTransaction()) {
			throw new IllegalStateException(
					"Can't create new transaction because transaction already started. Commit or rollback first");
		} else {
			transaction = new Transaction(this);
			return transaction;
		}

	}

	/**
	 * Получение количества свободных слотов для экземпляров OCApp.
	 * 
	 * @return int количество свободный слотов соединений.
	 */
	public static int unoccupiedInstancesSlot() {
		return instancesStorage.getMaximumInstances() - instancesStorage.size();
	}

	/**
	 * Закрытие DCOM сессии.
	 * 
	 * @throws JIException
	 */
	public void exit() throws JIException {
		// works only for V81.Application
		// dispatch().callMethod("Exit", new Object[]{new
		// JIVariant(Boolean.FALSE)});
		if (transaction != null && transaction.inTransaction()) {
			transaction.rollback();
		}
		close();
		instancesStorage.remove(getAssociatedSessionID());
	}

	/**
	 * Получает системное строковое представление переданного значения.
	 * 
	 * @param variant OCObject
	 *            variant
	 * @return java.langString. Системное представление значения в
	 *         информационной базе.
	 * @throws JIException
	 */
	public String valueToStringInternal(OCObject variant) throws JIException {
		return valueToStringInternal(variant.dispatch());
	}

	/**
	 * Получает системное строковое представление переданного значения.
	 * 
	 * @param dispatch
	 * @return java.langString. Системное представление значения в
	 *         информационной базе.
	 * @throws JIException
	 */
	protected String valueToStringInternal(IJIDispatch dispatch)
			throws JIException {
		return (callMethodA("ValueToStringInternal", new Object[] { dispatch })[0]
				.getObjectAsString2());
	}

	/**
	 * Получает системное строковое представление переданного значения.
	 * 
	 * @param variant
	 * @return java.langString. Системное представление значения в
	 *         информационной базе.
	 * @throws JIException
	 */
	protected String valueToStringInternal(JIVariant variant)
			throws JIException {
		return (callMethodA("ValueToStringInternal", new Object[] { variant })[0]
				.getObjectAsString2());
	}

	/**
	 * Преобразует значение из строкового системного представления во
	 * внутреннее.
	 * 
	 * @param s
	 *            Системное представление значения в строковом виде.
	 * @return Тип: Произвольный. Значение, полученное из строкового системного
	 *         представления.
	 * @throws JIException
	 */
	public OCObject valueFromStringInternal(String s) throws JIException {
		JIVariant var = new JIVariant(s);
		return new OCObject(callMethodA("ValueFromStringInternal",
				new Object[] { var })[0]);
	}

	/**
	 * Получает строковое представление значения произвольного типа.
	 * 
	 * @param variant
	 *            Выражение произвольного типа.
	 * @return
	 * @throws JIException
	 */
	public String string(OCObject variant) throws JIException {
		JIVariant v = callMethodA("String", new Object[] { variant.dispatch() })[0];
		return (v.getType() != JIVariant.VT_EMPTY ? v.getObjectAsString2() : "");
	}
	
	/**
	 * Получает из строки закодированной по алгоритму base64 двоичные данные. 
	 * @param data - Строка, закодированная по алгоритму base64. 
	 * @return OCBinaryData
	 * @throws JIException
	 */
	public OCBinaryData base64Value(String data) throws JIException {
		return new OCBinaryData(callMethodA("Base64Value", new JIVariant(data))[0]);
	}
	
	/**
	 * Получает строку, закодированную по алгоритму base64. 
	 * @param data - Двоичные данные, которые необходимо закодировать по алгоритму base64. 
	 * @return
	 * @throws JIException
	 */
	public String base64String(OCBinaryData data) throws JIException {
		return callMethodA("Base64String", new JIVariant(ocObject2Dispatch(data)))[0].getObjectAsString2();
	}

    /**
     * Определяет установку права доступа к объекту метаданных для текущего пользователя
     * @param right Перечисление AccessRight, роль 1С
     * @param metadataObject Объект метаданных
     * @param roleOrInfobaseUser Указывает пользователя или роль, для которых нужно определить доступность права.
     *                           При этом возвращается значение права так, как оно определено в конфигурации
     * @param standardAttributeStandardTabularSection Необязательное имя стандартного реквизита, имя стандартного реквизита
     *                                                стандартной табличной части или имя стандартной табличной части.
     *                                                Для стандартного реквизита стандартной табличной части, имя табличной части
     *                                                указывается перед точкой до имени реквизита. Например: "ВидыСубконто.ВидСубконто".
     * @return Истина - есть право доступа. Ложь - нет права доступа
     * @throws JIException
     */
    public Boolean accessRight(AccessRight right, _OCCommonMetadataObject metadataObject,
                               OCObject roleOrInfobaseUser, String standardAttributeStandardTabularSection) throws JIException {
        // Validation
        if (right == null) throw new IllegalArgumentException("Wrong [right] parameter - can't be null");
        if (metadataObject == null) throw new IllegalArgumentException("Wrong [metadataObject] parameter - can't be null");
        if (roleOrInfobaseUser != null && !(roleOrInfobaseUser instanceof OCInfoBaseUser || roleOrInfobaseUser instanceof OCRoleMetadataObject)) {
            throw new IllegalArgumentException("Wrong [roleOrInfobaseUser] parameter - correct type must be used: OCInfoBaseUser or OCRoleMetadataObject");
        }

        Object[] callParams = new Object[]{
                new JIVariant(right.name()),
                new JIVariant(ocObject2Dispatch(metadataObject)),
                new JIVariant(roleOrInfobaseUser != null ? roleOrInfobaseUser.dispatch() : null),
                JIVariant.makeVariant(standardAttributeStandardTabularSection)};
        return callMethodA("AccessRight", callParams)[0].getObjectAsBoolean();
    }

	/**
	 * Сетевое имя компьютера.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getComputerName() throws JIException {
		return callMethodA("ComputerName").getObjectAsString2();
	}

	/**
	 * Получает имя пользователя, указанного при запуске программы.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getUserName() throws JIException {
		return callMethodA("UserName").getObjectAsString2();
	}
	
	/**
	 * Получает полное имя пользователя, указанного при запуске программы.
	 * @return
	 * @throws JIException
	 */
	public String getUserFullName() throws JIException {
		return callMethodA("UserFullName").getObjectAsString2();
	}

	/**
	 * @deprecated Вызывает на исполнение команду операционной системы, как если
	 *             бы она была введена в командной строке. Недоступен на сервере
	 *             1С:Предприятие. Не используется в модуле внешнего соединения.
	 * @param command
	 *            Команда системы.
	 * @param currentCatalog
	 *            Устанавливает текущий каталог на время выполнения команды.
	 * @throws JIException
	 *//*
	public void system(String command, String currentCatalog)
			throws JIException {
		callMethodA("System", new Object[] { JIVariant.makeVariant(command),
				JIVariant.makeVariant(currentCatalog) });
	}*/

	/**
	 * Определяет, была ли изменена конфигурация базы данных динамически после
	 * старта. Под динамическим изменением понимается такое изменение
	 * конфигурации базы данных, при которой не потребовалось проведение
	 * реструктуризации, и в момент обновления с информационной базой работали
	 * пользователи.
	 * 
	 * @return Истина - в процессе работы пользователя с информационной базой
	 *         произошло обновление конфигурации базы данных, Ложь - в противном
	 *         случае.
	 * @throws JIException
	 */
	public Boolean isDataBaseConfigurationChangedDynamically()
			throws JIException {
		return callMethodA("DataBaseConfigurationChangedDynamically")
				.getObjectAsBoolean();
	}

	/**
	 * Определяет, используется ли в данный момент монопольный режим работы с
	 * информационной базой.
	 * 
	 * @return Истина - используется монопольный режим; Ложь - в противном
	 *         случае.
	 * @throws JIException
	 */
	public Boolean isExclusiveMode() throws JIException {
		return callMethodA("ExclusiveMode").getObjectAsBoolean();
	}

	/**
	 * Получает код локализации (язык, страна), установленный для данной
	 * информационной базы.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getLocaleCode() throws JIException {
		return callMethodA("LocaleCode").getObjectAsString2();
	}

	/**
	 * Удаляет указанные файлы.
	 * 
	 * @param folder
	 *            Путь к удаляемым файлам
	 * @param fileMask
	 *            Маска для выбора удаляемых файлов. В строке маски допускается
	 *            использование символа "*" (звездочка), обозначающего любое
	 *            число произвольных символов, и "?" (знак вопроса),
	 *            обозначающего один произвольный символ. Если <Маска> не
	 *            указана, то удаляются все файлы и каталог <Путь>.
	 * @throws JIException
	 */
	public void deleteFiles(String folder, String fileMask) throws JIException {
		if (fileMask != null) {
			callMethod("DeleteFiles", new Object[] { new JIVariant(folder),
					new JIVariant(fileMask) });
		} else {
			callMethod("DeleteFiles", new Object[] { new JIVariant(folder) });
		}
	}

	/**
	 * Выполняет перемещение (переименование) указанного файла. Допускается
	 * использование схем http, https и ftp для адресации файлов. При
	 * использовании этих схем в адресах необходимо указывать прямые слеши '/',
	 * а не обратные '\'.
	 * 
	 * @param source
	 * @param destination
	 * @throws JIException
	 */
	public void moveFile(String source, String destination) throws JIException {
		callMethod("MoveFile", new Object[] { new JIVariant(source),
				new JIVariant(destination) });
	}

	/**
	 * Получает имя каталога, который используется программой 1C для размещения
	 * временных файлов.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getTempFilesDir() throws JIException {
		return callMethodA("TempFilesDir").getObjectAsString2();
	}

	/**
	 * Получения любого заданного менеджера используя рефлексию по имени
	 * объекта. Например: Document.СчетНаОплатуПокупателю (OCDocumentManager
	 * documentManager = app.getManager("Document.СчетНаОплатуПокупателю");)
	 * 
	 * @param <T>
	 * @param fullName
	 *            полное имя объекта (например: Document.СчетНаОплатуПокупателю)
	 * @return типизированный менеджер (например OCDocumentManager)
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public <T> T findManager(String fullName) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		String[] sdS = fullName.split("\\.");
		// find getter
		Method method = getClass().getMethod(
				"get" + ObjectNameDecoder.ru2En(sdS[0]) + "Manager",
				String.class);
		Object o = method.invoke(this, sdS[1]);
		return (T) o;
	}

	/**
	 * Экспериментальный метод поиска data-объектов. Внимание: класс менеджера
	 * объекта должен поддерживать метод getRef. Метод может применятся для
	 * поиска документов, каталогов (справочников) и т.д. (не для перечислений.
	 * Для перечислений используется отдельный менеджер)
	 * 
	 * @param <T>
	 * @param managerFullname
	 *            полное имя типа объекта. Например: Catalog.Номенклатура
	 * @param uuid
	 *            объекта
	 * @return data-объект типа <T>
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws JIException
	 */
	@SuppressWarnings("unchecked")
	public <T> T findDataObject(String managerFullname, String uuid)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, JIException {
		OCUUID ocuuid = createUUID(uuid);
		Object manager = findManager(managerFullname); // named data object
														// manager

		// финт ушами
		Method method = manager.getClass().getMethod("getRef", OCUUID.class);
		_OCCommonRef ref = (_OCCommonRef) method.invoke(manager, ocuuid);

		return (T) ref.getObject();
	}
	
	/**
	 * Осуществляет поиск ссылок на объекты, переданные в параметре <Список ссылок>. 
	 * @param refs  Массив со списком ссылок на объекты, ссылки на которые нужно найти. 
	 * @return OCValueTable. Возвращает ссылки на найденные объекты в виде ТаблицаЗначений, состоящей из колонок с индексами: 0 - искомая ссылка; 1 - ссылка на объект, если найдена ссылка в объектной таблице; ключ записи, если ссылка найдена в независимом регистре сведений; ссылка на документ-регистратор для всех остальных необъектных таблиц; 2 - объект метаданных, которому соответствуют данные из колонки 1. 
	 * @throws JIException
	 */
	public OCValueTable findByRef(OCArray refs) throws JIException {
		return new OCValueTable(callMethodA("FindByRef", new JIVariant(ocObject2Dispatch(refs)))[0]);
	}
	
	/**
	 * Используется для доступа к определенным в конфигурации документам. 
	 * @return OCDocumentCollection
	 * @throws JIException
	 */
	public OCDocumentCollection getDocumentCollection() throws JIException {
		return new OCDocumentCollection(get("Documents"));
	}

	/**
	 * Набор свойств содержит менеджеры отдельных документов. Имена свойств
	 * совпадают с именами документов, как они заданы в конфигураторе Short path
	 * for app.getDocumentCollection().getDocument(<documentName>)
	 * 
	 * @param documentName - имя документа
	 * @return OCDocumentManager
	 * @throws JIException
	 */
	public OCDocumentManager getDocumentManager(String documentName) throws JIException {
		OCDocumentCollection collection = getDocumentCollection();
		try {
			return collection.getDocument(documentName);
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}
	
	/**
	 * Используется для доступа к определенным в конфигурации регистрам сведений. 
	 * @return OCInformationRegisterCollection
	 * @throws JIException
	 */
	public OCInformationRegisterCollection getInformationRegisterCollection() throws JIException {
		return new OCInformationRegisterCollection(get("InformationRegisters"));
	}
	
	/**
	 * Short path for getInformationRegisterCollection().getInformationRegister(...).
	 * @param managerName для регистра сведений
	 * @return OCInformationRegisterManager
	 * @throws JIException
	 */
	public OCInformationRegisterManager getInformationRegisterManager(String managerName) throws JIException {
		OCInformationRegisterCollection collection = getInformationRegisterCollection();
		try {
			return collection.getInformationRegister(managerName);
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}
	
	/**
	 * Массив состоит из значений типа УровеньЖурналаРегистрации. для которых
	 * включена регистрация событий в журнале. Если регистрация отключена,
	 * возвращается пустой массив.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCArray getEventLogUse() throws JIException {
		return new OCArray(callMethodA("GetEventLogUse"));
	}

	/**
	 * Используется для доступа к определенным в конфигурации справочникам. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogCollection getCatalogCollection() throws JIException {
		return new OCCatalogCollection(get("Catalogs"));
	}
	
	/**
	 * Short path for getCatalogCollection.
	 * 
	 * @param catalogName наименование справочника
	 * @return OCCatalogManager
	 * @throws JIException
	 */
	public OCCatalogManager getCatalogManager(String catalogName) throws JIException {
		OCCatalogCollection collection = getCatalogCollection();
		try {
			OCCatalogManager manager = collection.getCatalog(catalogName);
			return manager;
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}

	/**
	 * Используется для доступа к определенным в конфигурации журналам
	 * документов.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCDocumentJournalCollection getDocumentJournalCollection()
			throws JIException {
		return new OCDocumentJournalCollection(get("DocumentJournals"));
	}

	/**
	 * Используется для доступа к определенным в конфигурации перечислениям.
	 * 
	 * @return OCEnumCollection
	 * @throws JIException
	 */
	public OCEnumCollection getEnumCollection() throws JIException {
		return new OCEnumCollection(get("Enums"));
	}
	
	/**
	 * Получение ссылки на перечислений по полному имени.
	 * @param fullName полное имя перечисления. (СтавкиНДС.БезНДС)
	 * @return OCEnumRef
	 * @throws JIException 
	 */
	public OCEnumRef getEnumRef(String fullName) throws JIException {
		String[] splitted = fullName.split("\\.");
		if (splitted == null || splitted.length != 2) {
			throw new RuntimeException("Wrong parameter in getEnumRef (example: СтавкиНДС.БезНДС)");
		}
		OCEnumCollection collection = getEnumCollection();
		OCEnumManager manager = collection.getEnum(splitted[0]);
		return manager.getEnumValueRef(splitted[1]);
	}

	/**
	 * Используется для доступа к планам счетов.
	 * 
	 * @return OCChartsOfAccountCollection
	 * @throws JIException
	 */
	public OCChartsOfAccountCollection getChartOfAccountsCollection()
			throws JIException {
		return new OCChartsOfAccountCollection(get("ChartsOfAccounts"));
	}

	/**
	 * Доступ к указанному менеджеру планов счетов.
	 * 
	 * @param name
	 *            имя плана счетов
	 * @return OCChartsOfAccountManager
	 * @throws JIException
	 */
	public OCChartOfAccountsManager getChartOfAccountsManager(String name) throws JIException {
		OCChartsOfAccountCollection collection = new OCChartsOfAccountCollection(get("ChartsOfAccounts"));
		try {
			return collection.getChartsOfAccount(name);
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}
	
	/**
	 * Доступ к менеджеру регистров бухгалтерии.
	 * @param name - имя регистра.
	 * @return OCAccountingRegisterManager
	 * @throws JIException
	 */
	public OCAccountingRegisterManager getAccountingRegisterManager(String name)
			throws JIException {
		OCAccountingRegisterCollection collection = new OCAccountingRegisterCollection(get("AccountingRegisters"));
		try {
			return collection.getAccountingRegister(name);
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}

	/**
	 * @depricated since 0.5.0
	 * Используется для доступа к планам видов характеристик.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCChartsOfCharacteristicTypeCollection getChartOfCharacteristicTypesCollection()
			throws JIException {
		return new OCChartsOfCharacteristicTypeCollection(
				get("ChartsOfCharacteristicTypes"));
	}

	public OCChartOfCharacteristicTypesManager getChartOfCharacteristicTypesManager(
			String name) throws JIException {
		OCChartsOfCharacteristicTypeCollection collection = new OCChartsOfCharacteristicTypeCollection(
				get("ChartsOfCharacteristicTypes"));
		try {
			return collection.getChartsOfCharacteristicType(name);
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}

	/**
	 * Используется для доступа к определенным в конфигурации константам.
	 * @return OCConstantCollection
	 * @throws JIException
	 */
	public OCConstantCollection getConstantsCollection() throws JIException {
		return new OCConstantCollection(get("Constants"));
	}
	
	/**
	 * Используется для доступа к определенным в конфигурации константам.
	 * @param constantName - имя константы
	 * @return OCConstantManager
	 * @throws JIException
	 */
	public OCConstantManager getConstant(String constantName) throws JIException {
		OCConstantCollection collection = getConstantsCollection();
		try {
			return collection.getConstant(constantName);
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}

	/**
	 * Используется для доступа к определенным в конфигурации регистрам
	 * накопления.
	 * 
	 * @return OCAccumulationRegisterCollection
	 * @throws JIException
	 */
	public OCAccumulationRegisterCollection getAccumulationRegisterCollection() throws JIException {
		return new OCAccumulationRegisterCollection(
				get("AccumulationRegisters"));
	}
	
	/**
	 * Используется для доступа к определенным в конфигурации отчетам. 
	 * @return
	 * @throws JIException
	 */
	public OCReportCollection getReportCollection() throws JIException {
		return new OCReportCollection(get("Reports"));
	}
	
	public ReportProcessor getReportProcessor() {
		return new ReportProcessor(this);
	}

	/**
	 * Используется для доступа к планам обмена.
	 * 
	 * @return OCExchangePlansCollection
	 * @throws JIException
	 */
	public OCExchangePlansCollection getExchangePlansCollection()
			throws JIException {
		return new OCExchangePlansCollection(get("ExchangePlans"));
	}

	/**
	 * Используется для доступа к плану обмена по имени.
	 * 
	 * @param managerName
	 *            имя менеджера плана обмена
	 * @return OCExchangeManger
	 * @throws JIException
	 */
	public OCExchangePlanManager getExchangePlanManager(String managerName)
			throws JIException {
		OCExchangePlansCollection collection = getExchangePlansCollection();
		try {
			return collection.getExchangePlan(managerName);
		} catch (JIException e) {
			getErrorListener().onError(this, e);
			throw e;
		}
	}

	/**
	 * Используется для управления списком пользователей информационной базы.
	 * 
	 * @return OCInfoBaseUsersManager
	 * @throws JIException
	 */
	public OCInfoBaseUsersManager getInfoBaseUserManager() throws JIException {
		return new OCInfoBaseUsersManager(get("InfoBaseUsers"));
	}

	/**
	 * Фабрика XDTO, содержащая все пакеты XDTO, имеющиеся в конфигурации, а
	 * также предопределенные пакеты (например, пакет типов XML схемы).
	 * 
	 * @return OCXDTOFactory
	 * @throws JIException
	 */
	public OCXDTOFactory getXDTOFactory() throws JIException {
		return new OCXDTOFactory(get("XDTOFactory"));
	}

	/**
	 * Сериализатор XDTO, соответствующий глобальной фабрики XDTO.
	 * 
	 * @return OCXDTOSerializer
	 * @throws JIException
	 */
	public OCXDTOSerializer getXDTOSerializer() throws JIException {
		return new OCXDTOSerializer(get("XDTOSerializer"));
	}
	
	/**
	 * Получает тип данных XML, который может быть прочитан в настоящий момент
	 * из объекта типа ЧтениеXML. При получении типа данных XML может быть
	 * произведена операция чтения из объекта ЧтениеXML. В случае, если тип
	 * данных XML определить невозможно возвращается значение Неопределено. Тип
	 * данных XML определяется по следующему алгоритму: 1) Пропускаются
	 * пробельные символы, если текущая позиция объекта ЧтениеXML соответствует
	 * типу узла "Текст" и значение узла полностью состоит из пробельных
	 * символов. 2) Если тип текущего узла не соответствует значению
	 * НачалоЭлемента, то считается, что тип не определен. 3) Определяется
	 * наличие атрибута "nil" из пространства имен
	 * "http://www.w3.org/2001/XMLSchema-instance". Если атрибут существует и
	 * его значение равно "true", то считается, что тип не определен. 4)
	 * Определяется наличие атрибута "type" из пространства имен
	 * "http://www.w3.org/2001/XMLSchema-instance". Если атрибут существует, то
	 * производится анализ значения атрибута: значение разделяется на префикс
	 * пространства имен и локальное имя по префиксу пространства имен в объекте
	 * ЧтениеXML определяется URI пространства имен, соответствующее данному
	 * префиксу (если соответствующего URI пространства имен не обнаружено -
	 * считается, что тип не определен) по полученному локальному имени и URI
	 * пространства имен создается значение типа данных XML 5) Если атрибут
	 * "type" не существует, производится анализ имени элемента. Если локальное
	 * имя элемента совпадает с одним из имен типов пространства имен
	 * "http://www.w3.org/2001/XMLSchema" и пространство имен элемента не
	 * определено (пустая строка), то считается, что тип принадлежит
	 * пространству имен "http://www.w3.org/2001/XMLSchema".
	 * 
	 * @param reader -  Объект, из которого выполняется чтение. 
	 * @return OCXMLDataType
	 * @throws JIException - ошибка платормы или DCOM
	 */
	public final OCXMLDataType getXMLType(final OCXMLReader reader) throws JIException {
		JIVariant var = callMethodA("GetXMLType", new JIVariant(ocObject2Dispatch(reader)))[0];
		if (var != null) {
			return new OCXMLDataType(var);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "OCApp [host=" + host + ", dbuser=" + dbuser
				+ ", getAssociatedSessionID()=" + getAssociatedSessionID()
				+ "]";
	}

}
