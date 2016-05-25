package com.ipc.oce.metadata.collection;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public abstract class _OCMetadataObjectCollection<T> extends OCObject implements Iterable<T>{

	
	public _OCMetadataObjectCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public _OCMetadataObjectCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает значение по индексу. (первый индеск 0)
	 * @param i - Индекс объекта в дереве метаданных. 
	 * @return OCObject
	 * @throws JIException
	 */
	public OCObject get(int i) throws JIException {
		JIVariant[] objects = callMethodA(
				"Get", 
				new Object[]{new JIVariant(i)}
				);
		if (objects == null || objects.length < 1) {
			throw new JIException(-1, 
					"GET(" + i + ") returns null or zero elements");
		}
		return new OCObject(objects[0]);
	}
	
	/**
	 * Осуществляет поиск в коллекции объекта описания метаданного по его имени. 
	 * @param objectName - Наименование объекта описания метаданного. 
	 * @return если объект не найден, то возвращается null
	 * @throws JIException
	 */
	public OCObject find(String objectName) throws JIException {
		JIVariant var = callMethodA(
				"Find", 
				new Object[]{JIVariant.makeVariant(objectName)}
				)[0];
		OCObject obj = null;
		if (var.getType() != JIVariant.VT_EMPTY) {
			obj = new OCObject(var);
		}
		return obj;
	}

}
