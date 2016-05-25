/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * @author Konovalov
 *
 */
public class DefaultEnumVARIANT extends EnumVARIANT<OCVariant> {

	/**
	 * @param object
	 */
	public DefaultEnumVARIANT(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public DefaultEnumVARIANT(IJIDispatch aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public DefaultEnumVARIANT(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	protected OCVariant castToGeneric(JIVariant variant) {
		return new OCVariant(variant);
	}

}
