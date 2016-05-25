package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCEnumMetadataObject;


/**
 * Представляет собой ссылку на значение перечисления. Используется для ссылки на значение перечисления в реквизитах объектов базы данных и переменных встроенного языка.
 * @author Konovalov
 *
 */
// Специфический объект - обладает свойствами лишь отчасти повторяющими общие ссылки
public class OCEnumRef extends OCObject implements MetadataHolder, NamedRef{
	private String refFullName = null;
	private String enumValue = null;
	public OCEnumRef(OCObject object) {
		super(object);
	}

	public OCEnumRef(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCEnumRef(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	protected IJIDispatch dispatch() { // для передачи в различные методы
		return super.dispatch();
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных перечисления. Другой путь получения того же значения - через свойство глобального контекста Метаданные. Например: Метаданные.Перечисления.ВидыКонтрагентов. 
	 * @return
	 * @throws JIException
	 */
	public OCEnumMetadataObject getMetadata() throws JIException{
		return new OCEnumMetadataObject(callMethodA("Metadata"));
	}
	
	/**
	 * Определяет, является ли ссылка пустой или нет. 
	 * @return Истина - ссылка не указывает ни на какой объект (пустая ссылка); Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isEmpty() throws JIException{
		return callMethodA("IsEmpty").getObjectAsBoolean();
	}

	public String getRefFullName() throws JIException {
		if (refFullName == null) {
			refFullName = getMetadata().getFullName();
		}
		return refFullName;
	}
	
	/**
	 * Получение значения перечисления. Используется метод 
	 * приведения ссылки перечисления к строке(1С). 
	 * В принципе аналогично нативной реализации
	 * @return строковое значение перечисления.
	 */
	public String getEnumValue(){
		if (enumValue == null) {
			enumValue =  toString();
		}
		return enumValue;
	}

	@Override
	public int hashCode() {
		try { 
			return getEnumValue().hashCode();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public boolean equals(final Object paramObject) {
		if (paramObject == null || !(paramObject instanceof OCEnumRef)) {
			return false;
		}
		OCEnumRef ref = (OCEnumRef) paramObject;
		try {
			return (getEnumValue().equals(ref.getEnumValue()));
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	
}
