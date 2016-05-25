package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Определяет набор варианты ограничения длины строки. Используется для определения свойства ДопустимаяДлина и в конструкторе объекта КвалификаторыСтроки, если задается длина строки отличная от нуля.
 * @author Konovalov
 *
 */
public class OCAllowedLength extends OCObject implements IOCVariantSet{
	public static final String VARIABLE = "Переменная";
	public static final String FIXED = "Фиксированная";
	
	public OCAllowedLength(OCObject object) {
		super(object);
	}

	public OCAllowedLength(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAllowedLength(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	public String getAllowedLengthAsString(){
		return toString();
	}

	
	public String stringValue() {
		return getAllowedLengthAsString();
	}
}
