/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCExchangePlanMetadataObject;

/**
 * Предназначен для изменения узлов. Позволяет изменять атрибуты узла и
 * записывать его. Важно! У всех событий этого объекта назначены
 * предопределенные процедуры-обработчики. Имена процедур соответствует именам
 * событий. Процедуры должны располагаться в модуле плана обмена.
 * XML-сериализация
 * 
 * @author Konovalov
 * 
 */
public class OCExchangePlanObject extends _OCCommonObject implements AttributeBean {

	/**
	 * @param object
	 */
	public OCExchangePlanObject(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCExchangePlanObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCExchangePlanObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения реквизитов узла. Доступ к значению осуществляется по имени, как оно задано в конфигураторе
	 * @param attributeName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Набор свойств содержит значения реквизитов узла. Доступ к значению осуществляется по имени, как оно задано в конфигураторе
	 * @param attributeName - имя атрибута
	 * @param variant - значение атрибута
	 * @throws JIException
	 */
	public void setAttributeValue(String name, OCVariant variant) throws JIException {
		put(name, variant);
	}
	
	/**
	 * ПланОбменаТабличнаяЧасть.<Имя плана обмена>.<Имя табличной части>. Набор свойств содержит табличные части узла. Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе.
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	/**
	 * Может использоваться в тех случаях, когда необходимо хранить некоторые
	 * значения, связанные с объектом, на время выполнения некоторых операций,
	 * без изменения объекта. Например, при обработке событий в подписке на
	 * события. XML-сериализация.
	 * 
	 * @return OCStructure
	 * @throws JIException
	 */
	public OCStructure getAdditionalProperties() throws JIException {
		return new OCStructure(get("AdditionalProperties"));
	}
	
	/**
	 * Содержит код узла. 
	 * @return
	 * @throws JIException
	 */
	public String getCode() throws JIException{
		return get("Code").getObjectAsString2();
	}
	
	/**
	 * Установка кода узла.
	 * @param code - код узла.
	 * @throws JIException
	 */
	public void setCode(String code) throws JIException {
		put("Code" , new JIVariant(code));
	}
	
	/**
	 * Содержит наименование узла. 
	 * @return
	 * @throws JIException
	 */
	public String getDescription() throws JIException{
		return get("Description").getObjectAsString2();
	}
	
	/**
	 * Установка наименования description. 
	 * @param description - описание узла.
	 * @throws JIException
	 */
	public void setDescription(String description) throws JIException {
		put("Description", new JIVariant(description));
	}
	
	/**
	 * Номер отправленного сообщения.
	 * @return
	 * @throws JIException
	 */
	public Integer getSentNo() throws JIException{
		return get("SentNo").getObjectAsInt();
	}
	
	public void setSentNo(int sentNo) throws JIException {
		put("SentNo", new JIVariant(sentNo));
	}
	
	/**
	 * Номер принятого сообщения.
	 * @return
	 * @throws JIException
	 */
	public Integer getReceivedNo() throws JIException{
		return get("ReceivedNo").getObjectAsInt();
	}
	
	public void setReceivedNo(int receivedNo) throws JIException {
		put("ReceivedNo", new JIVariant(receivedNo));
	}
	
	/**
	 * Содержит признак пометки удаление узла. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	public void setDeletitionMark(boolean mark) throws JIException {
		put("DeletionMark", new JIVariant(mark));
	}
	
	/**
	 * Содержит ссылку на узел. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public OCExchangePlanRef getRef() throws JIException{
		return new OCExchangePlanRef(super.getRef());
	}
	
	/**
	 * Содержит сам объект. Предназначено для получения объекта в модуле объекта или модуле формы. 
	 * @return
	 * @throws JIException
	 */
	public OCExchangePlanObject thisObject() throws JIException {
		return new OCExchangePlanObject(callMethodA("ThisObject"));
	}
	
	/**
	 * Определяет, был ли изменен объект после считывания из базы данных. Метод не позволяет определить, был ли изменен объект другими пользователями 
	 * @return Истина - объект изменен; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isModified() throws JIException {
		return callMethodA("Modified").getObjectAsBoolean();
	}
	
	/**
	 * Заполняет данные узла данными из другого объекта. Инициирует событие ОбработкаЗаполнения и вызов его процедуры-обработчика в модуле плана обмена. В ней может размещаться алгоритм, заполняющий данные узла из переданного значения. 
	 * @param object Произвольный. Значение, на основании которого выполняется заполнение узла
	 * @throws JIException
	 */
	public void fill(OCObject object) throws JIException{
		callMethod("Fill", new Object[]{ocObject2Dispatch(object)});
	}
	
	/**
	 * Позволяет для нового (созданного и еще не записанного) объекта получить ранее установленное методом УстановитьСсылкуНового значение ссылки. 
	 * @return ПланОбменаСсылка. 
	 * @throws JIException
	 */
	public OCExchangePlanRef getNewObjectRef() throws JIException{
		return new OCExchangePlanRef(callMethodA("GetNewObjectRef"));
	}
	
	/**
	 * Устанавливает новый код. Если в коде присутствует числовая часть, то новый код автоматически устанавливается следующим за имеющимся максимальным. При этом определяется текущий максимальный код среди узлов данного плана обмена. 
	 * Если передан префикс, то новый код устанавливается следующим образом: выполняется поиск максимального кода среди кодов, имеющих данный префикс, новый код выбирается как следующий от найденного кода. 
	 * @param preffix Строковое значение префикса. Если префикс указан, то новый код будет формироваться с учетом префикса
	 * @throws JIException
	 */
	public void setNewCode(String preffix) throws JIException{
		callMethod("SetNewCode", new Object[]{new JIVariant(preffix)} );
	}
	
	/**
	 * Устанавливает/снимет пометку на удаление. Объект при этом не удаляется из базы данных. Окончательное удаление помеченных узлов происходит при удалении помеченных объектов. 
	 * @param mark Признак установки / снятие пометки на удаление. Истина - пометка будет установлена; Ложь - пометка будет снята. 
	 * @throws JIException
	 */
	public void setDeletionMark(Boolean mark) throws JIException{
		callMethod("SetDeletionMark", new Object[]{new JIVariant(mark)});
	}
	
	/**
	 * Устанавливает значение для нового (созданного и еще не записанного) объекта, которое будет назначено при записи в качестве ссылки. Значение не может равняться ссылке какого-либо из имеющихся в базе данных объекта данного типа. Уникальность ссылки проверяется при записи объекта. 
	 * @param ref Ссылка, которая будет назначена при записи нового объекта
	 * @throws JIException
	 */
	public void setNewObjectRef(OCExchangePlanRef ref) throws JIException{
		callMethod("SetNewObjectRef", new Object[]{ref.dispatch()});
	}
	
	public OCExchangePlanMetadataObject getMetadata() throws JIException {
		return new OCExchangePlanMetadataObject(super.getMetadata());
	}
}
