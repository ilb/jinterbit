/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;

/**
 * Объект этого типа возвращается методами Выбрать у объекта типа ПланОбменаМенеджер.<Имя плана обмена> и представляет собой специализированный способ перебора узлов. Обход узлов выполняется системой динамически. Это означает, что использование выборки не считывает все узлы сразу, а выбирает их порциями из базы данных. Такой подход позволяет достаточно быстро обходить с помощью выборки большие списки планов обмена и не загружать в память всех элементов выборки. 
 * @author Konovalov
 *
 */
public class OCExchangePlanSelection extends _OCCommonSelection {

	/**
	 * @param object
	 */
	public OCExchangePlanSelection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCExchangePlanSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCExchangePlanSelection(JIVariant aDispatch) throws JIException {
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
	 * ПланОбменаТабличнаяЧасть.<Имя плана обмена>.<Имя табличной части>. Набор свойств содержит табличные части узла. Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе.
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
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
	 * Содержит наименование узла. 
	 * @return
	 * @throws JIException
	 */
	public String getDescription() throws JIException{
		return get("Description").getObjectAsString2();
	}
	
	/**
	 * Номер отправленного сообщения.
	 * @return
	 * @throws JIException
	 */
	public Integer getSentNo() throws JIException{
		return get("SentNo").getObjectAsInt();
	}
	
	/**
	 * Номер принятого сообщения.
	 * @return
	 * @throws JIException
	 */
	public Integer getReceivedNo() throws JIException{
		return get("ReceivedNo").getObjectAsInt();
	}
	
	/**
	 * Содержит признак пометки удаление узла. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на узел. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public OCExchangePlanRef getRef() throws JIException{
		return new OCExchangePlanRef(get("Ref"));
	}
	
	/**
	 * Получает объект для модификации и записи узла, на котором в данный момент спозиционирована выборка
	 * @return
	 * @throws JIException
	 */
	public OCExchangePlanObject getObject() throws JIException{
		return new OCExchangePlanObject(callMethodA("GetObject"));
	}
}
