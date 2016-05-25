/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Предназначен для управления планами видов характеристик и предоставляет доступ к значениям типа ПланВидовХарактеристикМенеджер.<Имя плана видов характеристик>. Доступ к объекту осуществляется через свойство глобального контекста ПланыВидовХарактеристик.
 * @author Konovalov
 *
 */
public class OCChartsOfCharacteristicTypeCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCChartsOfCharacteristicTypeCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCChartsOfCharacteristicTypeCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCChartsOfCharacteristicTypeCollection(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит менеджеры отдельных планов видов характеристик. Доступ к менеджеру осуществляется по имени, как они заданы в конфигураторе. 
	 * @param name
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesManager getChartsOfCharacteristicType(String name) throws JIException{
		OCChartOfCharacteristicTypesManager manager = new OCChartOfCharacteristicTypesManager(get(name));
		manager.setManagerName(name);
		return manager;
	}

}
