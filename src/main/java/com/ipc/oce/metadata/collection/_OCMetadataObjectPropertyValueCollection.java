package com.ipc.oce.metadata.collection;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;


public class _OCMetadataObjectPropertyValueCollection extends OCObject {

	public _OCMetadataObjectPropertyValueCollection(OCObject object) {
		super(object);
	}

	public _OCMetadataObjectPropertyValueCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public _OCMetadataObjectPropertyValueCollection(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество объектов описания метаданных, входящих в коллекцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Определяет, имеется ли в данной коллекции переданный в качестве параметра объект описания метаданного
	 * @param object
	 * @return Истина - объект в коллекции есть; Ложь - в противном случае
	 * @throws JIException
	 */
	public Boolean contains(_OCCommonMetadataObject object) throws JIException {
		return callMethodA("Contains", new Object[]{new JIVariant((new SupportCommonMetadataObject(object)).dispatch() )})[0].getObjectAsBoolean();
	}
	class SupportCommonMetadataObject extends _OCCommonMetadataObject {
		/**
		 * @param object
		 */
		public SupportCommonMetadataObject(OCObject object) {
			super(object);
		}
		@Override
		protected IJIDispatch dispatch() {
			return super.dispatch();
		}
	}
}
