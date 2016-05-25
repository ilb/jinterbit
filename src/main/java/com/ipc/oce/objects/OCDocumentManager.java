package com.ipc.oce.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;
import com.ipc.oce.query.OCQuery;
import com.ipc.oce.query.OCQueryResult;
import com.ipc.oce.query.OCQueryResultSelection;

/**
 * Предназначен для управления документом, как объектом конфигурации. С помощью этого объекта осуществляется поиск документов, получение выборки документов, создание нового документа, работа с формами и макетами документа. Доступ к объекту осуществляется через свойства объекта ДокументыМенеджер. Полное имя типа объекта определяется с учетом имени документа конфигурации. Например, для документа "Расходная накладная" имя типа будет выглядеть ДокументМенеджер.РасходнаяНакладная.
*  Возможен обмен с сервером. 
 * @author Konovalov
 *
 */
public class OCDocumentManager extends _OCAbstractManager {

	public OCDocumentManager(OCObject object) {
		super(object);
	}

	public OCDocumentManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Формирует выборку документов за определенный период.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentSelection select() throws JIException{
		return new OCDocumentSelection(callMethodA("Select"));
	}
	/**
	 * Формирует выборку документов за определенный период.
	 * @param dateStart Дата и время начала периода выборки документов. Если параметр не указан, то выбираются все документы, начиная с самого первого документа в базе данных.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentSelection select(Date dateStart) throws JIException{
		return new OCDocumentSelection(callMethodA("Select", new Object[]{new JIVariant(dateStart)})[0]);
	}
	/**
	 * Формирует выборку документов за определенный период.
	 * @param dateStart Дата и время начала периода выборки документов. Если параметр не указан, то выбираются все документы, начиная с самого первого документа в базе данных.
	 * @param dateEnd Дата окончания периода выбираемых документов. Если параметр не указан, то выбираются все документы, заканчивая самым последним документом в базе данных.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentSelection select(Date dateStart, Date dateEnd) throws JIException{
		if (dateStart == null && dateEnd == null) {
			return select();
		}
		if (dateEnd == null) {
			return select(dateStart);
		}
		return new OCDocumentSelection(callMethodA(
				"Select", new Object[]{
						new JIVariant(dateStart), 
						new JIVariant(dateEnd)}
				)[0]);
	}
	
	/**
	 * Формирует выборку документов по заданным параметрам
	 * 
	 * @param dateStart
	 *            Дата и время начала периода выборки документов. Если параметр
	 *            не указан, то выбираются все документы, начиная с самого
	 *            первого документа в базе данных. Значение по умолчанию: Пустая
	 *            дата
	 * @param dateEnd
	 *            Дата окончания периода выбираемых документов. Если параметр не
	 *            указан, то выбираются все документы, заканчивая самым
	 *            последним документом в базе данных. Значение по умолчанию:
	 *            Пустая дата
	 * @param structure
	 *            Задает поле и значение отбора открываемой выборки. Ключ
	 *            структуры описывает имя поля, а значение структуры - значение
	 *            отбора по этому полю. В качестве полей для отбора могут
	 *            задаваться только поля "Дата" и реквизиты документа, для
	 *            которых в конфигураторе признак индексирования установлен в
	 *            значение "Индексировать" или в значение
	 *            "Индексировать с доп. упорядочиванием". Важно! Структура может
	 *            содержать только один элемент. Если параметр не указан, то
	 *            отбор не используется.
	 * @param order
	 *            Строка с именем реквизита документа, определяющая
	 *            упорядочивание документов в выборке. Может быть указано поле
	 *            "Дата" или имя реквизита документа, для которого признак
	 *            индексирования в конфигураторе установлен в значения
	 *            "Индексировать" или "Индексировать с доп. упорядочиванием".
	 *            После указания имени через пробел может быть указано
	 *            направление сортировки. Направление определяется: "Убыв"
	 *            ("Desc") - упорядочивать по убыванию, и "Возр" ("Asc") -
	 *            упорядочивать по возрастанию. По умолчанию выборка
	 *            упорядочивается по возрастанию. Если параметр не задан,
	 *            выборка упорядочивается по хронологии документов.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentSelection select(Date dateStart, Date dateEnd, OCStructure structure, String order) throws JIException {
		PreparedPredicateStruct wrapper = null;
		if (structure != null) {
			wrapper = new PreparedPredicateStruct(structure);
		}
		JIVariant structureVariant = new JIVariant(
				structure != null ? wrapper.dispatch() : null);
		return new OCDocumentSelection(callMethodA("Select", new Object[] {
				new JIVariant(dateStart), new JIVariant(dateEnd),
				structureVariant, new JIVariant(order) })[0]);
	}
	
	/**
	 * Создает новый документ
	 * @return OCDocumentObject
	 * @throws JIException
	 */
	public OCDocumentObject createDocument() throws JIException{
		return new OCDocumentObject(callMethodA("CreateDocument"));
	}
	
	/**
	 * Создает новый документ
	 * @param number номер документа
	 * @param date дата документа
	 * @return OCDocumentObject
	 * @throws JIException
	 */
	public OCDocumentObject createDocument(String number, Date date) throws JIException {
		OCDocumentObject object = createDocument();
		object.setNumber(number);
		object.setDate(date);
		return object;
	}
	
	/**
	 * Создает новый документ
	 * @param number номер документа
	 * @param date дата документа
	 * @return OCDocumentObject
	 * @throws JIException
	 */
	public OCDocumentObject createDocument(Integer number, Date date) throws JIException {
		OCDocumentObject object = createDocument();
		object.setNumber(number);
		object.setDate(date);
		return object;
	}
	
	/**
	 * Осуществляет поиск документа по номеру. (Для документов не имеющих периода)
	 * @param number
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef findByNumber(String number) throws JIException{
		return new OCDocumentRef(callMethodA("FindByNumber", new Object[]{JIVariant.makeVariant(number)})[0]);
	}
	
	/**
	 * Осуществляет поиск документа по номеру. (Для документов не имеющих периода)
	 * @param number
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef findByNumber(Integer number) throws JIException{
		return new OCDocumentRef(callMethodA("FindByNumber", new Object[]{JIVariant.makeVariant(number)})[0]);
	}
	
	/**
	 * Осуществляет поиск документа по номеру с указанием даты в периоде документа
	 * @param number Номер искомого документа.
	 * @param docDate Дата из интервала, в котором проводится поиск по номеру. Сам интервал определяется как период уникальности номеров документа, в который входит указанная дата. Например, если номера документов уникальны в пределах месяца и задана дата 10 декабря 2001 года, то поиск будет проводиться в интервале с 01 по 31 декабря 2001 года. Параметр используется для документов с периодической нумерацией. 
	 * @return ссылка на документ с заданным номером относящийся к периоду для указанной даты
	 * @throws JIException
	 */
	public OCDocumentRef findByNumber(String number, Date docDate) throws JIException{
		return new OCDocumentRef(callMethodA("FindByNumber", new Object[]{JIVariant.makeVariant(number), JIVariant.makeVariant(docDate)})[0]);
	}
	
	/**
	 * Осуществляет поиск документа по реквизиту. Примечание: Если существует несколько документов с указанным значением реквизита, то будет найдет только один из них. Для реквизитов типа Строка поиск осуществляется по точному соответствию.
	 * ВАЖНО! Поиск может производиться ТОЛЬКО по индексированным. 
	 * @param attributeName Имя реквизита, как он задан в конфигураторе, по значению которого осуществляется поиск. Тип значения произвольный, кроме ХранилищеЗначения и строк произвольной длины. 
	 * @param variant  Произвольный. Значение реквизита, по которому должен выполняться поиск
	 * @return Ссылка на найденный документ. Если не существует ни одного документа с требуемым значением реквизита, то будет возвращена пустая ссылка. 
	 * @throws JIException
	 */
	public OCDocumentRef findByAttribute(String attributeName, OCVariant variant) throws JIException{
		return new OCDocumentRef(callMethodA(
									"FindByAttribute", 
									new Object[]{
											new JIVariant(attributeName),
											ocVariant2JI(variant)
									}
									)[0]);
	}
	
	/**
	 * Осуществляет поиск документа по номеру с указанием даты в периоде документа
	 * @param number Номер искомого документа.
	 * @param docDate Дата из интервала, в котором проводится поиск по номеру. Сам интервал определяется как период уникальности номеров документа, в который входит указанная дата. Например, если номера документов уникальны в пределах месяца и задана дата 10 декабря 2001 года, то поиск будет проводиться в интервале с 01 по 31 декабря 2001 года. Параметр используется для документов с периодической нумерацией.
	 * @return ссылка на документ с заданным номером относящийся к периоду для указанной даты 
	 * @throws JIException
	 */
	public OCDocumentRef findByNumber(Integer number, Date docDate) throws JIException{
		return new OCDocumentRef(callMethodA("FindByNumber", new Object[]{JIVariant.makeVariant(number), JIVariant.makeVariant(docDate)})[0]);
	}
	
	/**
	 * Поиск ссылки на документ по номеру и дате. ПРИМЕЧАНИЕ: Использует механизм запросов (OCQuery). В отличии от findByNumber дата документа указывается точно, а не должна попадать в определенный период
	 * @param number номер документа
	 * @param docDate (дата документа). ВНИМАНИЕ: Используется точное сравнение (=)
	 * @return Ссылка на документ с заданным домером и у конкретной датой
	 * @throws JIException
	 */
	public OCDocumentRef findByNumber2(String number, Date docDate) throws JIException {
		OCApp instance = OCApp.getInstance(getAssociatedSessionID());
		
		// prepare query
		OCQuery query = instance.newQuery(
				"SELECT Doc.Ref FROM Document."
				+ managerName
				+ " AS Doc WHERE Doc.Number = &NUM_A AND Doc.Date = &DATE_A");
		
		// set parameters
		query.setParameter("NUM_A", new OCVariant(number));
		query.setParameter("DATE_A", new OCVariant(docDate));
		
		OCQueryResult result = query.execute();
		OCQueryResultSelection selection = result.choose();
		OCDocumentRef ref = null;
		if (selection.next()) {
			ref = new OCDocumentRef(selection.getObject(0));
		} else {
			ref = emptyRef();
		}
		return ref;
	}
	
	/**
	 * Поиск ссылок на документы по номеру во всех периодах. ПРИМЕЧАНИЕ: Использует механизм запросов (OCQuery). 
	 * @param number номер документа
	 * @return Массив ссылок на документы. (С одним номером может быть несколько документов в различных периодах)
	 * @throws JIException
	 */
	public OCDocumentRef[] findByNumber2(String number) throws JIException {
		OCApp instance = OCApp.getInstance(getAssociatedSessionID());
		OCQuery query = instance.newQuery("SELECT Doc.Ref FROM Document." + managerName + " AS Doc WHERE Doc.Number = &NUM_A");
		
		query.setParameter("NUM_A", new OCVariant(number));
		
		OCQueryResult result = query.execute();
		OCQueryResultSelection selection = result.choose();
		int selectionSZ = selection.size();
		List<OCDocumentRef> refList = new ArrayList<OCDocumentRef>(selectionSZ);
		while (selection.next()) {
			refList.add(new OCDocumentRef(selection.getObject(0)));
		}
		
		return refList.toArray(new OCDocumentRef[selectionSZ]);
	}
	
	/**
	 * @deprecated since 0.5.2 use emptyRef.
	 * Получает пустое значение ссылки на документ данного вида. (кто его знает как это тут применить)
	 * Примечание:
	 * Может использоваться, например, когда нужно передать пустую ссылку в параметр метода. 
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef getEmptyRef() throws JIException{
		return new OCDocumentRef(callMethodA("EmptyRef"));
	}

	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getDocuments().find(managerName);
	}

	@Override
	public OCDocumentMetadataObject getMetadata() throws JIException {
		return new OCDocumentMetadataObject(super.getMetadata());
	}

	/**
	 * Формирует ссылку из значения типа УникальныйИдентификатор.
	 * Данный уникальный идентификатор может быть в дальнейшем получен из ссылки методом УникальныйИдентификатор. 
	 * @param uuid
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef getRef(OCUUID uuid) throws JIException {
		if (uuid != null) {
		return new OCDocumentRef(callMethodA("GetRef",
				new Object[] { uuid.dispatch() })[0]);
		} else {
			return newRef();
		}
	}
	
	/**
	 * Создание новой ссылки на документ. Аналог getRef без параметра.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef newRef() throws JIException{
		return new OCDocumentRef(callMethodA("GetRef"));
	}
	
	/**
	 * 
	 * Получает пустое значение ссылки на документ данного вида. 
	 * @return ДокументСсылка. Может использоваться, например, когда нужно передать пустую ссылку в параметр метода. 
	 * @throws JIException
	 */
	public OCDocumentRef emptyRef() throws JIException{
		return new OCDocumentRef(callMethodA("EmptyRef"));
	}
	
	
}
