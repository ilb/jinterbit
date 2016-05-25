package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCChartsOfAccountsMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Предназначен для управления планом счетов, как объектом конфигурации. С
 * помощью этого объекта осуществляется поиск элементов, создание новых
 * элементов и групп, работа с формами и макетами плана счетов. Доступ к объекту
 * осуществляется через свойства объекта ПланыСчетовМенеджер. Полное имя типа
 * объекта определяется с учетом имени плана счетов конфигурации.
 * 
 * @author Konovalov
 * 
 */
public class OCChartOfAccountsManager extends _OCAbstractManager {

	public OCChartOfAccountsManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartOfAccountsManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCChartOfAccountsManager(OCObject object) {
		super(object);
	}
	
	/**
	 * Формирует выборку элементов плана счетов по заданным условиям. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsSelection select() throws JIException {
		return new OCChartOfAccountsSelection(callMethodA("Select"));
	}
	
	/**
	 * Формирует выборку элементов плана счетов по заданным условиям.
	 * 
	 * @param parentRef
	 *            Отбор по родителю. Если параметр не задан, то отбор по
	 *            родителю не производится.
	 * @return OCChartOfAccountsSelection
	 * @throws JIException
	 */
	public OCChartOfAccountsSelection select(OCChartOfAccountsRef parentRef) throws JIException {
		return new OCChartOfAccountsSelection(callMethodA("Select",
				new Object[] { new JIVariant(parentRef.dispatch()) })[0]);
	}
	
	/**
	 * Формирует иерархическую выборку элементов справочника по заданным условиям. При иерархической выборке для каждого элемента сначала выбираются элементы, для которых он является родителем, а затем уже выбираются элементы следующего уровня. 
	 * @return OCChartOfAccountsSelection
	 * @throws JIException
	 */
	public OCChartOfAccountsSelection selectHierarchically() throws JIException {
		return new OCChartOfAccountsSelection(callMethodA("SelectHierarchically"));
	}
	
	/**
	 * Формирует иерархическую выборку элементов справочника по заданным условиям. При иерархической выборке для каждого элемента сначала выбираются элементы, для которых он является родителем, а затем уже выбираются элементы следующего уровня. 
	 * @param parentRef  Отбор по родителю. Если параметр не задан, то отбор по родителю не производится. 
	 * @return OCChartOfAccountsSelection
	 * @throws JIException
	 */
	public OCChartOfAccountsSelection selectHierarchically(OCChartOfAccountsRef parentRef) throws JIException {
		return new OCChartOfAccountsSelection(callMethodA("SelectHierarchically", new Object[]{new JIVariant(parentRef.dispatch())})[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его коду. 
	 * @param code Искомый код. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsRef findByCode(String code) throws JIException {
		return new OCChartOfAccountsRef(callMethodA("FindByCode", new Object[]{new JIVariant(code)})[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его коду.
	 * 
	 * @param code
	 *            Искомый код.
	 * @param parentRef
	 *            Родитель, в пределах которого нужно выполнять поиск. Если не
	 *            указан, то во всем плане счетов.
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef findByCode(String code, OCChartOfAccountsRef parentRef) throws JIException {
		return new OCChartOfAccountsRef(callMethodA("FindByCode", new Object[]{new JIVariant(code), new JIVariant(parentRef.dispatch())})[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по значению реквизита.
	 * 
	 * @param attributeName
	 *            Имя реквизита, как он задан в конфигураторе, по значению
	 *            которого осуществляется поиск. Тип значения произвольный,
	 *            кроме ХранилищеЗначения и строк произвольной длины.
	 * @param attributeValue
	 *            Значение реквизита, по которому должен выполняться поиск.
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef findByAttribute(String attributeName, Object attributeValue) throws JIException {
		return new OCChartOfAccountsRef(callMethodA("FindByAttribute", new Object[]{ new JIVariant(attributeName), JIVariant.makeVariant(attributeValue)})[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по значению реквизита.
	 * 
	 * @param attributeName
	 *            Имя реквизита, как он задан в конфигураторе, по значению
	 *            которого осуществляется поиск. Тип значения произвольный,
	 *            кроме ХранилищеЗначения и строк произвольной длины.
	 * @param attributeValue
	 *            Значение реквизита, по которому должен выполняться поиск.
	 * @param parentRef
	 *            Родитель, в пределах которого нужно выполнять поиск. Если не
	 *            указан, то во всех счетах.
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef findByAttribute(String attributeName, Object attributeValue, OCChartOfAccountsRef parentRef) throws JIException {
		return new OCChartOfAccountsRef(callMethodA("FindByAttribute", new Object[]{ new JIVariant(attributeName), JIVariant.makeVariant(attributeValue), new JIVariant(parentRef.dispatch())})[0]);
	}
	
	/**
	 * Получает имя предопределенного элемента. Если данный элемент не является
	 * предопределенным, то возвращается пустая строка.
	 * 
	 * @param ref
	 *            Ссылка на элемент, имя которого требуется получить.
	 * @return String
	 * @throws JIException
	 */
	public String getPredefinedItemName(OCChartOfAccountsRef ref) throws JIException {
		return callMethodA("GetPredefinedItemName", new Object[]{new JIVariant(ref.dispatch())})[0].getObjectAsString2();
	}

	/**
	 * Получает пустое значение ссылки на план счетов данного вида. Может
	 * использоваться, например, когда нужно передать пустую ссылку в параметр
	 * метода.
	 * 
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef emptyRef() throws JIException {
		return new OCChartOfAccountsRef(callMethodA("EmptyRef"));
	}
	
	/**
	 * Создает новый счет. Использование метода не приводит к записи созданного
	 * объекта в базу данных.
	 * 
	 * @return OCChartOfAccountsObject
	 * @throws JIException
	 */
	public OCChartOfAccountsObject createAccount() throws JIException {
		return new OCChartOfAccountsObject(callMethodA("CreateAccount"));
	}

	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getChartsOfAccounts().find(managerName);
	}

	@Override
	public OCChartsOfAccountsMetadataObject getMetadata() throws JIException {
		return new OCChartsOfAccountsMetadataObject(super.getMetadata());
	}
	
	/**
	 * Формирует ссылку из значения типа УникальныйИдентификатор. Данный
	 * уникальный идентификатор может быть в дальнейшем получен из ссылки
	 * методом УникальныйИдентификатор.
	 * 
	 * @param uuid
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getRef(OCUUID uuid) throws JIException {
		return new OCChartOfAccountsRef(callMethodA("GetRef",
				new Object[] { uuid.dispatch() })[0]);
	}
	
	/**
	 * Новая ссылка на план счетов.
	 * 
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef newRef() throws JIException {
		return new OCChartOfAccountsRef(callMethodA("GetRef"));
	}

}
