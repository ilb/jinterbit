package com.ipc.oce.metadata;

import java.util.ArrayList;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Представляет собой объект для управления допустимыми типами значений. В
 * основном предназначен для присвоения в качестве значений соответствующим
 * свойствам других объектов. Набор допустимых типов и квалификаторы примитивных
 * типов задаются при создании объекта.
 * 
 * @author Konovalov
 * 
 */
public class OCTypeDescription extends OCObject {
	
	private OCTypesArray types = null;
	
	private OCType cachedSingleType = null;
	
	private Boolean cachedIsMultiType = null;
	
	public OCTypeDescription(OCObject object) throws JIException {
		super(object);
		init();
	}

	public OCTypeDescription(IJIDispatch aDispatch) throws JIException {
		super(aDispatch);
		init();
	}

	public OCTypeDescription(JIVariant aDispatch) throws JIException {
		super(aDispatch);
		init();
	}

	private void init() throws JIException {
		if (types == null) {
			JIVariant[] variants = callMethodA("Types", new Object[] {});
			types = new OCTypesArray(variants[0]);
		}
	}
	
	/**
	 * Содержит квалификаторы строки, используемые для описания допустимых значений строкового типа. 
	 * @return
	 * @throws JIException
	 */
	public OCStringQualifier getStringQualifiers() throws JIException {
		return new OCStringQualifier(get("StringQualifiers"));
	}
	
	/**
	 *  Содержит квалификаторы даты - объект, используемый для описания допустимых значений типа Дата. 
	 * @return
	 * @throws JIException
	 */
	public OCDateQualifiers getDateQualifiers() throws JIException {
		return new OCDateQualifiers(get("DateQualifiers"));
	}
	
	/**
	 * Содержит квалификаторы числа, используемые для описания допустимых значений числового типа. 
	 * @return
	 * @throws JIException
	 */
	public OCNumberQualifiers getNumberQualifiers() throws JIException {
		return new OCNumberQualifiers(get("NumberQualifiers"));
	}
	
	/**
	 * Получение всех типов объекта кроме типа Null.
	 * @return
	 * @throws JIException
	 */
	public OCType[] getNotNullTypes() throws JIException {
		List<OCType> lst = new ArrayList<OCType>();
		OCType[] ts = getTypes();
		for (OCType t : ts) {
			if (t.getTypeCode() != OCType.OCT_NULL) {
				lst.add(t);
			}
		}
		return lst.toArray(new OCType[lst.size()]);
		
	}
	
	/**
	 * Получение типов объекта как массив.
	 * @return массив OCType
	 * @throws JIException
	 */
	public OCType[] getTypes() throws JIException {
		return types.toArray();
	}
	/**
	 * Проверяет на множественность типов. 
	 * (Один объект, например, атрибут может иметь значения 
	 * различных типов. В основном это динамические типы)
	 * @return
	 * @throws JIException
	 */
	public Boolean isMultiType() throws JIException {
		if (cachedIsMultiType == null) {
			cachedIsMultiType = (types.size() != 1);
		}
		return cachedIsMultiType;
	}
	/**
	 * Get ONLY first type. Use isMultiType to ensure
	 * @return OCType
	 * @throws JIException
	 */
	public OCType getType() throws JIException {
		if (cachedSingleType == null) {
			cachedSingleType = types.getType(0);
		}
		return cachedSingleType;
	}
}
