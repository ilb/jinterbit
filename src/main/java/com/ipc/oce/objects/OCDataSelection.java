/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Содержит ссылки на данные, представленные в базе данных. Объект позволяет
 * обойти данные, попавшие в выборку и прочитать их.
 * 
 * @author Konovalov
 * 
 */
public class OCDataSelection extends OCObject {

	/**
	 * @param object
	 */
	public OCDataSelection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataSelection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Производит считывание из базы данных элемента данных, соответствующего
	 * текущей позиции выборки и возвращает соответствующий объект.
	 * 
	 * @return Данные, УдалениеОбъекта, Неопределено. Если текущей позиции
	 *         выборки соответствует удаленный объект, то возвращается значение
	 *         типа УдалениеОбъекта. Если выборка находится в позиции
	 *         "перед первым элементом" или "после последнего", то возвращается
	 *         значение Неопределено.
	 * @throws JIException
	 */
	public OCObject get() throws JIException {
		return new OCObject(callMethodA("Get"));
	}
	
	/**
	 * Позиционирует выборку в начало. После вызова метода Следующий выборка
	 * спозиционируется на первую запись в ней.
	 * 
	 * @throws JIException
	 */
	public void reset() throws JIException {
		callMethod("Reset");
	}
	
	/**
	 * Получает следующий элемент данных из выборки. Сразу после получения
	 * выборки или обращения к методу Сбросить, выборка находится в позиции
	 * "перед первым элементом". Таким образом первое после этого обращение к
	 * данному методу позиционирует выборку на первый элемент.
	 * 
	 * @return Истина - следующий элемент данных выбран; Ложь - достигнут конец
	 *         выборки.
	 * @throws JIException
	 */
	public boolean next() throws JIException {
		return callMethodA("Next").getObjectAsBoolean();
	}

}
