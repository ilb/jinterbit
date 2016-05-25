/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCConstantMetadataObject;

/**
 * @author Konovalov
 * Используется для доступа к константе. Выполняет получение (чтение) константы и ее установку (запись). В отличие от КонстантаМенеджер.<Имя константы> получение и установку выполняет в/из внутреннего свойства Значение. Любая запись константы (интерактивно в форме, объекты КонстантыНабор и КонстантаМенеджер.<Имя константы>) явно или неявно создает объект этого типа и производит запись с его помощью, что обеспечивает вызов обработчиков событий этого объекта.
 */
public class OCConstantValueManager extends OCObject {

	/**
	 * @param object
	 */
	public OCConstantValueManager(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCConstantValueManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCConstantValueManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Может использоваться в тех случаях, когда необходимо хранить некоторые значения, связанные с объектом, на время выполнения некоторых операций, без изменения объекта. Например, при обработке событий в подписке на события.
	 * @return
	 * @throws JIException
	 */
	public OCStructure getAdditionalProperties() throws JIException{
		return new OCStructure(get("AdditionalProperties"));
	}
	
	/**
	 * Содержит значение константы. Чтение и запись производится методами Прочитать и Записать. 
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValue() throws JIException{
		return new OCVariant(get("Value"));
	}
	
	/**
	 * Содержит значение константы. Чтение и запись производится методами Прочитать(getValue) и Записать(setValue).
	 * @param object
	 * @throws JIException
	 */
	public void setValue(OCVariant object) throws JIException{
		put("Value", ocVariant2JI(object));
	}
	
	/**
	 * Записывает значение константы из свойства Значение(Value) в базу данных. 
	 * @throws JIException
	 */
	public void write() throws JIException{
		callMethod("Write");
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных константы. Другой путь получения того же значения - через свойство глобального контекста Метаданные. Например: Метаданные.Константы.Валюта. 
	 * @return
	 * @throws JIException
	 */
	public OCConstantMetadataObject getMetadata() throws JIException{
		return new OCConstantMetadataObject(callMethodA("Metadata"));
	}
	
	/**
	 * Считывает значение константы из базы данных в свойство Значение(Value)
	 * @throws JIException
	 */
	public void read() throws JIException{
		callMethod("Read");
	}
}
