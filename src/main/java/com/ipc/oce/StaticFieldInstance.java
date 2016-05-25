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
public class StaticFieldInstance extends OCObject {
	
	private String fieldName = null; 
	
	protected StaticFieldInstance(JIVariant object) throws JIException {
		super(object);
	}

	public final String getFieldName() {
		return fieldName;
	}

	protected final void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	

	@Override
	protected IJIDispatch dispatch() {
		return super.dispatch();
	}

	@Override
	public int hashCode() {
		try {
			return innerRepresentation().hashCode();
		} catch (JIException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean equals(Object paramObject) {
		if (paramObject == null || !(paramObject instanceof OCObject)) {
			return false;
		} else {
			OCObject p = (OCObject) paramObject;
			try {
				return innerRepresentation().equals(p.innerRepresentation());
			} catch (JIException e) {
				return false;
			}
		}
	}
	
	
}
