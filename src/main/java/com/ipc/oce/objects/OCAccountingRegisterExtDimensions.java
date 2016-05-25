/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Коллекция значений субконто записи регистра бухгалтерии. Установка и
 * получение значения конкретного субконто осуществляется через оператор [], в
 * качестве параметра которому передается вид субконто. Возвращается значение
 * типа КлючИЗначение. В качестве ключа вид субконто, в качестве значения
 * значение субконто.
 * 
 * @author Konovalov
 * 
 */
public class OCAccountingRegisterExtDimensions extends OCObject {

	/**
	 * @param object
	 */
	public OCAccountingRegisterExtDimensions(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccountingRegisterExtDimensions(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccountingRegisterExtDimensions(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Характеристика.<Имя плана видов характеристик>. Набор свойств содержит
	 * значения видов субконто. Доступ к значению осуществляется по имени вида
	 * субконто. Имена имеют только предопределенные виды субконто.
	 * 
	 * @param characteristicName
	 *            - именя вида субконто
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 *//*
	public OCChartOfCharacteristicTypesRef getCharacteristic(String characteristicName) throws JIException {
		return new OCChartOfCharacteristicTypesRef(get(characteristicName));
	}
	
	*//**
	 * Характеристика.<Имя плана видов характеристик>. Набор свойств содержит
	 * значения видов субконто. Доступ к значению осуществляется по имени вида
	 * субконто. Имена имеют только предопределенные виды субконто.
	 * 
	 * @param characteristicName
	 *            - именя вида субконто
	 * @param ref
	 *            - ссылка OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 *//*
	public void setCharacteristic(String characteristicName, OCChartOfCharacteristicTypesRef ref) throws JIException {
		put(characteristicName, new OCVariant(ref));
	}*/
	// TODO вернуться к описанию OCAccountingRegisterExtDimensions
	

}
