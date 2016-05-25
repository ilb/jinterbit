/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Предназначен для хранения значения в специальном формате. Он может быть
 * записан в поля базы данных, имеющие соответствующий тип. Это позволят
 * сохранять в базе данных значения, тип которых не может быть выбран в качестве
 * типа поля, например, Картинка. Большинство объектов, которые имеют
 * неизменяемое значение, а также универсальные коллекции, могут быть помещены в
 * ХранилищеЗначения. К значению, хранящемуся в объекте, нельзя обращаться, его
 * можно только извлечь из хранилища. Описания типов объектов, которые могут
 * быть помещены в ХранилищеЗначения, включают текст "Сериализуется". Замечание!
 * Не рекомендуется хранить в реквизитах типа ХранилищеЗначения ссылки на другие
 * объекты базы данных.
 * 
 * Важно! В системе не поддерживается ссылочная целостность по объектам,
 * сохраненным в базе данных в полях типа ХранилищеЗначения. Это означает, что
 * при попытке удаления, например, элементов справочников система не
 * контролирует ссылки на эти элементы, сохраненные в ХранилищеЗначения.
 * Возможен обмен с сервером. Сериализуется. XML-сериализация. Поддержка
 * отображения в XDTO; пространство имен: {http://v8.1c.ru/8.1/data/core}. Имя
 * типа XDTO: ValueStorage.
 * 
 * @author Konovalov
 * 
 */
public class OCValueStorage extends OCObject {

	/**
	 * @param object
	 */
	public OCValueStorage(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueStorage(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueStorage(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает из хранилища сохраненное в нем значение. 
	 * @return Произвольный. Значение, содержащееся в хранилище.
	 * @throws JIException
	 */
	public OCVariant get() throws JIException {
		return new OCVariant(callMethodA("Get"));
	}

}
