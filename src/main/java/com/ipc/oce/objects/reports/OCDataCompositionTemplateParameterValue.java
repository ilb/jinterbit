/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;

/**
 * Содержит значение параметра макета. XML-сериализация. Поддержка отображения в
 * XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data-composition-system/composition-template}. Имя типа
 * XDTO: ParameterValue.
 * 
 * @author Konovalov
 * 
 */
public class OCDataCompositionTemplateParameterValue extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionTemplateParameterValue(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionTemplateParameterValue(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionTemplateParameterValue(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Значение параметра макета. 
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue() throws JIException {
		return new OCVariant(get("Value"));
	}
	
	/**
	 * Значение параметра макета. 
	 * @param variant
	 * @throws JIException
	 */
	public void setValue(OCVariant variant) throws JIException {
		put("Value", new JIVariant(ocVariant2JI(variant)));
	}
	
	/**
	 * Имя параметра макета. 
	 * @return
	 * @throws JIException
	 */
	public OCVariant getName() throws JIException {
		return new OCVariant(get("Name"));
	}
	
	/**
	 * Имя параметра макета. 
	 * @param variant
	 * @throws JIException
	 */
	public void setName(OCVariant variant) throws JIException {
		put("Name", new JIVariant(ocVariant2JI(variant)));
	}

}
