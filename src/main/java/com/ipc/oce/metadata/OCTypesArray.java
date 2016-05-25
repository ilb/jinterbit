package com.ipc.oce.metadata;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;


public class OCTypesArray extends OCArray {

	private OCType[] typeArray = null;
	
	public OCTypesArray(OCObject object) {
		super(object);
	}

	public OCTypesArray(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCTypesArray(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}


	public OCType getType(int i) throws JIException {
		OCVariant v = super.get(i);
		return new OCType(v.getAsOCObject()); 
	}
	
	public OCType[] toArray() throws JIException {
		if (typeArray == null) {
			int sz = size();
			typeArray = new OCType[sz];
			for (int z = 0; z < sz; z++) {
				typeArray[z] = getType(z);
			}
		}
		return typeArray;
	}
	
}
