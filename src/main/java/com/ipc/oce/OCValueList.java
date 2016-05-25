/**
 * 
 */
package com.ipc.oce;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.metadata.OCTypeDescription;

/**
 * Используется для доступа к методам списка значений в целом.
 * @author Konovalov
 *
 */
public class OCValueList extends OCObject implements Iterable<OCValueListItem>{

	/**
	 * @param object
	 */
	public OCValueList(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueList(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueList(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Определяет тип для значений, которые могут храниться в элементах данного списка значений. 
	 * @return
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException {
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Определяет тип для значений, которые могут храниться в элементах данного списка значений. 
	 * @param typeDescription
	 * @throws JIException
	 */
	public void setValueType(OCTypeDescription typeDescription) throws JIException {
		putRef("ValueType", typeDescription);
	}
	
	// TODO походу объект бесполезен

	/**
	 * Получает количество элементов списка значений. 
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index
	 * @return
	 * @throws JIException
	 */
	public OCValueListItem get(int index) throws JIException {
		return new OCValueListItem(callMethodA("Get", new JIVariant(index))[0]);
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCValueListItem> iterator() {
		EnumVARIANT<OCValueListItem> enumV = new EnumVARIANT<OCValueListItem>(this) {
			
			@Override
			protected OCValueListItem castToGeneric(JIVariant variant) {
				try {
					return new OCValueListItem(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
}
