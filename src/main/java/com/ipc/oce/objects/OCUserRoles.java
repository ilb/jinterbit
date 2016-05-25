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
import com.ipc.oce.metadata.objects.OCRoleMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Содержит коллекцию ролей пользователя. 
 * @author Konovalov
 *
 */
public class OCUserRoles extends OCObject implements Iterable<OCRoleMetadataObject>{

	/**
	 * @param object
	 */
	public OCUserRoles(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCUserRoles(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCUserRoles(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Добавляет используемую роль в коллекцию ролей пользователя.
	 * @param role Добавляемая роль
	 * @throws JIException
	 */
	public void add(OCRoleMetadataObject role) throws JIException {
		callMethod("Add", new JIVariant(ocObject2Dispatch(role)));
	}
	
	/**
	 * Очищает коллекцию используемых ролей пользователя. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Определяет, содержит ли коллекция используемых ролей пользователя указанную роль. 
	 * @param role Искомая роль.
	 * @return Истина - роль содержится в коллекции; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public boolean contains(OCRoleMetadataObject role) throws JIException {
		return callMethodA("Contains", new JIVariant(ocObject2Dispatch(role)))[0].getObjectAsBoolean();
	}
	
	/**
	 * Удаляет указанную роль из коллекции используемых ролей пользователя.
	 * @param role Удаляемая роль
	 * @throws JIException
	 */
	public void delete(OCRoleMetadataObject role) throws JIException {
		callMethod("Delete", new JIVariant(ocObject2Dispatch(role)));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCRoleMetadataObject> iterator() {
		EnumVARIANT<OCRoleMetadataObject> enumV = new EnumVARIANT<OCRoleMetadataObject>(this) {
			
			@Override
			protected OCRoleMetadataObject castToGeneric(JIVariant variant) {
				try {
					OCRoleMetadataObject commonMetadata = new OCRoleMetadataObject(variant);
					return commonMetadata;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
				
			}
		};
		return enumV;
	}

}
