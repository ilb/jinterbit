/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCUserArray extends OCArray {

	/**
	 * @param object
	 */
	public OCUserArray(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCUserArray(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCUserArray(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получение пользователя 1С по индексу.
	 * @param index - индекс пользователя в массиве.
	 * @return OCInfoBaseUser
	 * @throws JIException
	 */
	public OCInfoBaseUser getUser(int index) throws JIException {
		return new OCInfoBaseUser(get(index).getAsOCObject());
	}
	
	/**
	 * Получение всех пользователей как массива.
	 * @return OCInfoBaseUser[]
	 * @throws JIException
	 */
	public OCInfoBaseUser[] toArray() throws JIException {
		int sz = size();
		OCInfoBaseUser[] res = new OCInfoBaseUser[sz];
		for (int z = 0; z < sz; z++) {
			res[z] = getUser(z);
		}
		return res;
	}

}
