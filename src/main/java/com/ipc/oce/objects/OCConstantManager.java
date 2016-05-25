/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCConstantMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Применяется в виде свойства Константы глобального контекста для предоставления доступа к константам.
 * @author Konovalov
 *
 */
public class OCConstantManager extends _OCAbstractManager {

	/**
	 * @param aDispatch
	 */
	public OCConstantManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCConstantManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * @param object
	 */
	public OCConstantManager(OCObject object) {
		super(object);
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.objects._OCAbstractManager#loadMetadata()
	 */
	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getConstants().find(managerName);
	}

	@Override
	public OCConstantMetadataObject getMetadata() throws JIException {
		return new OCConstantMetadataObject(super.getMetadata());
	}
	
	/**
	 * Получает значение константы. Примечание: При каждом использовании метода происходит считывание значения из базы данных. 
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValue() throws JIException{
		return new OCVariant(callMethodA("Get")) ;
	}
	
	/**
	 * Устанавливает значение константы в базе данных.
	 * @param value
	 * @throws JIException
	 */
	public void setValue(OCVariant value) throws JIException{
		callMethod("Set", ocVariant2JI(value));
	}
	
	/**
	 * Создает менеджер значения константы
	 * @return
	 * @throws JIException
	 */
	public OCConstantValueManager createValueManager() throws JIException{
		return new OCConstantValueManager(callMethodA("CreateValueManager"));
	}

}
