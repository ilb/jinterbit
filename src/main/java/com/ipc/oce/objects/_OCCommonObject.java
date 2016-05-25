/**
 * 
 */
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
 * @author Konovalov
 *
 */
public class _OCCommonObject extends OCObject implements MetadataHolder {
	
	protected BeforeWriteHandler preWH = null;
	
	protected AfterWriteHandler postWH = null;
	
	private _OCCommonMetadataObject metadata = null;
	
	/**
	 * @param object
	 */
	public _OCCommonObject(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public _OCCommonObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public _OCCommonObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public final void setBeforeWriteHandler(BeforeWriteHandler preWH) {
		this.preWH = preWH;
		this.preWH.setAppInstance(OCApp.getInstance(getAssociatedSessionID()));
	}

	public final void setAfterWriteHandler(AfterWriteHandler postWH) {
		this.postWH = postWH;
		this.postWH.setAppInstance(OCApp.getInstance(getAssociatedSessionID()));
	}

	/**
	 * Предоставляет доступ к объекту описания метаданных.
	 * @return _OCCommonMetadataObject
	 * @throws JIException
	 */
	public _OCCommonMetadataObject getMetadata() throws JIException {
		if (metadata == null) {
			metadata = new _OCCommonMetadataObject(callMethodA("Metadata"));
		}
		return metadata;
	}
	
	/**
	 * Содержит ссылку на элемент. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public _OCCommonRef getRef() throws JIException {
		return new _OCCommonRef(get("Ref"));
	}
	
	/**
	 * Создает новый элемент копированием существующего. 
	 * Примечание:
	 * Использование метода не приводит к записи созданного объекта в базу данных. 
	 * @return
	 * @throws JIException
	 */
	public _OCCommonObject copy() throws JIException {
		_OCCommonObject co = new _OCCommonObject(callMethodA("Copy")) {
			
			@Override
			public void write() throws JIException {
				throw new UnsupportedOperationException();
				
			}
		};
		return co;
	}
	
	/**
	 * Определяет, записан ли объект в базу данных. 
	 * @return Истина - изменяется еще ни разу не записанный документ; Ложь - документ уже записан. 
	 * @throws JIException
	 */
	public Boolean isNew() throws JIException {
		return callMethodA("IsNew").getObjectAsBoolean();
	}
	
	/**
	 * Запись объекта в базу данных
	 * @throws JIException
	 */
	public void write() throws JIException {
		if (preWH != null) {
			preWH.handle(this);
		}
		callMethod("Write");
		if (postWH != null) {
			postWH.handle(this);
		}
	}
	
	/**
	 * Чтение объекта из базы данных
	 * @throws JIException
	 */
	public void read() throws JIException {
		callMethod("Read");
	}

	/**
	 * Выполняет блокировку объекта от изменения другими режимами или пользователями. 
	 * @throws JIException
	 */
	public void lock() throws JIException {
		callMethod("Lock");
	}

	/**
	 * Выполняет разблокировку объекта, если он заблокирован этим объектом.
	 * @throws JIException
	 */
	public void unlock() throws JIException {
		callMethod("Unlock");
	}

	/**
	 * Определяет, заблокирован ли объект данным объектом. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isLocked() throws JIException {
		return callMethodA("IsLocked").getObjectAsBoolean();
	}

	public <T extends OCObject> void fill(T object) throws JIException {
		callMethod("Fill", new JIVariant(ocObject2Dispatch(object)));
	}

	/**
	 * Удаляет элемент из базы данных. Важно! В отличие от метода
	 * УстановитьПометкуУдаления, данный производит непосредственное удаление
	 * элемента справочника без возможности восстановления и без проверки
	 * ссылочной целостности. Использовать данный метод нужно крайне
	 * осмотрительно.
	 * 
	 * @throws JIException
	 */
	public void delete() throws JIException {
		callMethod("Delete");
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
