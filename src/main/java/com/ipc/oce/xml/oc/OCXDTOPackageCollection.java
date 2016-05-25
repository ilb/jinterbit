/**
 * 
 */
package com.ipc.oce.xml.oc;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Коллекция пакетов XDTO
 * @author Konovalov
 *
 */
public class OCXDTOPackageCollection extends OCObject implements Iterable<OCXDTOPackage>{

	/**
	 * @param object
	 */
	public OCXDTOPackageCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTOPackageCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTOPackageCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество элементов коллекции
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает пакет XDTO по индексу
	 * @param index  Индекс элемента коллекции
	 * @return OCXDTOPackage
	 * @throws JIException
	 */
	public OCXDTOPackage getPackage(int index) throws JIException{
		return new OCXDTOPackage(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Получает пакет XDTO по индексу или имени
	 * @param namespace URI пространства имен пакета
	 * @return OCXDTOPackage
	 * @throws JIException
	 */
	public OCXDTOPackage getPackage(String namespace) throws JIException{
		return new OCXDTOPackage(callMethodA("Get",
				new Object[] { new JIVariant(namespace) })[0]);
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCXDTOPackage> iterator() {
		EnumVARIANT<OCXDTOPackage> enumV = new EnumVARIANT<OCXDTOPackage>(this) {
			
			@Override
			protected OCXDTOPackage castToGeneric(JIVariant variant) {
				try {
					return new OCXDTOPackage(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}

}
