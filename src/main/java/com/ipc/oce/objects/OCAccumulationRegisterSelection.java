/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.varset.OCAccumulationRecordType;

/**
 * Объект этого типа возвращается методами Выбрать и ВыбратьПоРегистратору у объекта типа РегистрНакопленияМенеджер.<Имя регистра накопления> и представляет собой специализированный способ перебора записей регистра накопления.
 * Обход записей выполняется системой динамически. Это означает, что использование выборки не считывает все записи сразу, а выбирает их порциями из базы данных. Такой подход позволяет достаточно быстро обходить с помощью выборки большое количество записей и не загружать в память всех элементов выборки. 
 * @author Konovalov
 *
 */
public class OCAccumulationRegisterSelection extends _OCCommonSelection implements AttributeGetter{

	/**
	 * @param object
	 */
	public OCAccumulationRegisterSelection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccumulationRegisterSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccumulationRegisterSelection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит флаг, определяющий влияние записи на итоги регистра накопления. Если значение Ложь, то запись не учитывается в итогах регистра. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isActive() throws JIException{
		return get("Active").getObjectAsBoolean();
	}
	
	/**
	 * Содержит вид движения регистра. (ВАЖНО!!! Только для регистров остатков)
	 * @return
	 * @throws JIException
	 */
	public OCAccumulationRecordType getRecordType() throws JIException{
		return new OCAccumulationRecordType(get("RecordType"));
	}
	
	/**
	 * Содержит уникальный номер строки данной записи в списке записей по регистратору, указанному в значении свойства Регистратор
	 * @return
	 * @throws JIException
	 */
	public Integer getLineNumber() throws JIException{
		return get("LineNumber").getObjectAsInt();
	}
	
	/**
	 * Содержит дату и время записи регистра накопления. Совместно с Регистратор и НомерСтроки определяет последовательность движений
	 * @return
	 * @throws JIException
	 */
	public Date getPeriod() throws JIException{
		return get("Period").getObjectAsDate();
	}
	
	/**
	 * Содержит ссылку на документ, внесший запись регистра накопления
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef getRecorder() throws JIException{
		return new OCDocumentRef(get("Recorder"));
	}
	
	/**
	 * Набор свойств содержит значения реквизитов регистра накопления. Доступ к значению осуществляется по имени, как оно задано в конфигураторе 
	 * @param attributeName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Набор свойств содержит значения ресурсов регистра накопления. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигураторе
	 * 
	 * @param resourceName
	 * @return
	 * @throws JIException
	 */
	public Number getResource(String resourceName) throws JIException{
		OCVariant v = new OCVariant(get(resourceName));
		return (Number) v.value();
	}
	
	/**
	 * Набор свойств содержит значения измерений регистра накопления. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигурации
	 * 
	 * @param dimensionName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getDimension(String dimensionName) throws JIException{
		return new OCVariant(get(dimensionName));
	}
}
