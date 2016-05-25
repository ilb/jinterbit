/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Значения параметров данных. Реализованы в виде значений параметров.
 * XML-сериализация. Поддержка отображения в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data-composition-system/settings}. Имя типа XDTO:
 * DataParameterValues.
 * 
 * @author Konovalov
 * 
 */
public class OCDataCompositionDataParameterValues extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionDataParameterValues(OCObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionDataParameterValues(IJIDispatch aDispatch) {
		super(aDispatch);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionDataParameterValues(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
		// TODO Auto-generated constructor stub
	}

}
