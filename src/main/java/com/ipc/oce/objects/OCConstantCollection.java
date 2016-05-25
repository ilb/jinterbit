/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Применяется в виде свойства Константы глобального контекста для предоставления доступа к константам.
 * @author Konovalov
 */
public class OCConstantCollection extends OCObject implements Iterable<OCConstantManager>{

	/**
	 * @param object
	 */
	public OCConstantCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCConstantCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCConstantCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Применяется в виде свойства Константы глобального контекста для предоставления доступа к константам.
	 * @param constantName
	 * @return
	 * @throws JIException
	 */
	public OCConstantManager getConstant(String constantName) throws JIException{
		OCConstantManager cm = new OCConstantManager(get(constantName));
		cm.setManagerName(constantName);
		return cm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCConstantManager> iterator() {
		EnumVARIANT<OCConstantManager> enumV = new EnumVARIANT<OCConstantManager>(this) {
			
			@Override
			protected OCConstantManager castToGeneric(JIVariant variant) {
				try {
					OCConstantManager manager = new OCConstantManager(variant);
					manager.setManagerName(manager.toString().split("\\.")[1]);
					return manager;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
}
