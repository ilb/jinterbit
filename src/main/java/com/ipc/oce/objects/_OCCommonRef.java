package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;
import com.ipc.oce.xml.oc.OCXMLDataType;

/**
 * Класс содержащий общие методы для объектов-ссылок (Ref-ов)
 * Для суперклассов можно сделать переопределение методов с приведением к конкретным типам
 * @author Konovalov
 *
 */
public class _OCCommonRef extends OCObject implements MetadataHolder, NamedRef{
	
	private _OCCommonMetadataObject metadata = null;
	private String refFullName = null;
	
	public _OCCommonRef(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public _OCCommonRef(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public _OCCommonRef(OCObject object) {
		super(object);
	}
	

	/**
	 * Содержит ссылку на элемент. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public _OCCommonRef getRef() throws JIException{
		return new _OCCommonRef(get("Ref"));
	}
	
	/**
	 * Получает объект для модификации и записи элемента, на котором в данный момент спозиционирована выборка. 
	 * @return
	 * @throws JIException
	 */
	public _OCCommonObject getObject() throws JIException{
		JIVariant var = callMethodA("GetObject");
		/*if(var.getType() == JIVariant.VT_EMPTY)
			return null;*/
		_OCCommonObject co = new _OCCommonObject(var) {
			
			@Override
			public void write() throws JIException {
				throw new UnsupportedOperationException();
				
			}
		};
		return co;
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных.
	 * @return
	 * @throws JIException
	 */
	public _OCCommonMetadataObject getMetadata() throws JIException{
		if (metadata == null) {
			metadata = new _OCCommonMetadataObject(callMethodA("Metadata"));
		}
		return metadata;
	}
	
	/**
	 * Определяет, является ли ссылка пустой или нет. 
	 * @return Истина - ссылка не указывает ни на какой объект (пустая ссылка); Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isEmpty() throws JIException{
		return callMethodA("IsEmpty").getObjectAsBoolean();
	}
	
	/**
	 * Создает новый элемент копированием существующего. 
	 * Примечание:
	 * Использование метода не приводит к записи созданного объекта в базу данных. 
	 * @return
	 * @throws JIException
	 */
	public _OCCommonObject copy() throws JIException{
		_OCCommonObject co = new _OCCommonObject(callMethodA("Copy")) {
			
			@Override
			public void write() throws JIException {
				throw new UnsupportedOperationException();
				
			}
		};
		return co;
	}
	
	/**
	 * Получение полного имени ссылки.
	 * @return полное наименование ссылки
	 * @throws JIException
	 */
	public String getRefFullName() throws JIException {
		if (refFullName == null) {
			refFullName = getMetadata().getFullName();
		}
		return refFullName;
	}
	
	/**
	 * Получает уникальный идентификатор ссылки.
	 * Ссылка может быть получена из уникального идентификатора 
	 * с помощью метода менеджера ПолучитьСсылку. 
	 * @return
	 * @throws JIException
	 */
	public OCUUID getUUID() throws JIException {
		return new OCUUID(callMethodA("UUID"));
	}

	@Override
	protected IJIDispatch dispatch() {
		return super.dispatch();
	}
	
	/**
	 * Получение xml-типа объекта.
	 * @return
	 * @throws JIException
	 */
	public OCXMLDataType getXMLType() throws JIException {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		return app.getXMLTypeOf(this);
	}
	
	/**
	 * Получение типа объекта
	 * @return OCType
	 * @throws JIException
	 */
	public OCType getOCType() throws JIException {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		return app.fromXMLType(getXMLType());
	}
	
}
