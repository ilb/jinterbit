/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCExchangePlanMetadataObject;

/**
 * Значение данного типа представляет собой ссылку на узел. Оно используется для ссылки на узел в реквизитах других объектов и переменных встроенного языка.
 * Эти значения не позволяют модифицировать узел, однако позволяют обращаться к его реквизитам и другой информации. 	
 * Значения данного типа в системе поддерживаются централизовано. При обращении к информации по ссылке происходит считывание узла, а в дальнейших обращениях данный узел уже не считывается, а используются ранее полученные значения.
 * @author Konovalov
 *
 */
public class OCExchangePlanRef extends _OCCommonRef {

	/**
	 * @param object
	 */
	public OCExchangePlanRef(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCExchangePlanRef(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCExchangePlanRef(JIVariant aDispatch) throws JIException {
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
		return new OCExchangePlanRef(super.getRef());
	}
	
	public OCExchangePlanMetadataObject getMetadata() throws JIException {
		return new OCExchangePlanMetadataObject(super.getMetadata());
	}
	
	public OCExchangePlanObject getObject() throws JIException{
		return new OCExchangePlanObject(super.getObject());
	}
}