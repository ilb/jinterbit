/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Индекс коллекции. Абсолютно бесполезный объект
 * @author Konovalov
 *
 */
public class OCCollectionIndex extends OCObject {

	/**
	 * @param object
	 */
	public OCCollectionIndex(OCObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aDispatch
	 */
	public OCCollectionIndex(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCCollectionIndex(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	protected IJIDispatch dispatch() {
		return super.dispatch();
	}
	
	

}
