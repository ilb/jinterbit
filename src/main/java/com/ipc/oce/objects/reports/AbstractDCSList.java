/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
abstract class AbstractDCSList extends OCObject {

	/**
	 * @param object
	 */
	AbstractDCSList(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	AbstractDCSList(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	AbstractDCSList(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	protected OCObject insert(int index) throws JIException {
		return new OCObject(callMethodA("Insert", new JIVariant(index))[0]);
	}
	
	protected OCObject add() throws JIException {
		return new OCObject(callMethodA("Add"));
	}
	
	protected int indexOf(OCObject value) throws JIException {
		return callMethodA("IndexOf", new JIVariant(ocObject2Dispatch(value)))[0].getObjectAsInt();
	}
	
	/**
	 * Получить размер коллекции.
	 * @return размер коллекции.
	 * @throws JIException
	 */
	protected int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Найти параметр в коллекции по имени.
	 * @param paramName - имя параметра.
	 * @return
	 * @throws JIException
	 */
	protected OCObject find(String paramName) throws JIException {
		JIVariant var = callMethodA("Find", new JIVariant(paramName))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCObject(var);
		} else {
			throw new JIException(-1, "Parameter '" + paramName + "' not found");
		}
	}
	
	protected void clear() throws JIException {
		callMethod("Clear");
	}
	
	protected OCObject get(int index) throws JIException {
		return new OCObject(callMethodA("Get", new JIVariant(index))[0]);
	}

	protected void move(OCObject param, int offset) throws JIException {
		callMethod("Move", new Object[]{new JIVariant(ocObject2Dispatch(param)), new JIVariant(offset)});
	}
	
	protected void delete(OCObject param) throws JIException {
		callMethod("Delete", new JIVariant(ocObject2Dispatch(param)));
	}

}
