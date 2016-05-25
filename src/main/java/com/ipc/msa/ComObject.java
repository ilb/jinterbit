package com.ipc.msa;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Базовый класс представляющий оболочку для IDispatch.
 * @author Konovalov
 *
 */
public class ComObject {
	
	/**
	 * IDispatch объекта.
	 */
	private IJIDispatch	dispatch;

	/**
	 * Конструктор COM-обертки из IDispatch объекта.
	 * @param aDispatch IDispatch объекта
	 */
	public ComObject(final IJIDispatch aDispatch) {
		super();
		dispatch = aDispatch;
	}

	/**
	 * Конструктор.
	 * 
	 * @param aDispatch
	 *            - JIVariant являющийся COM-объектом с интерфейсом IDispatch.
	 * @throws JIException
	 *             ошибка приведения JIVariant к объекту с IDispathc
	 *             интерфейсом.
	 */
	public ComObject(final JIVariant aDispatch) throws JIException {
		this(ComApp.toDispatch(aDispatch));
	}
	
	/**
	 * Конструктор по умолчанию. IDispatch объекта не задат.
	 */
	public ComObject() {
		super();
		dispatch = null;
	}

	/**
	 * Вызов процедуры с параметрами.
	 * 
	 * @param aName
	 *            имя процедуры.
	 * @param aInparams
	 *            список параметров JIVariant.
	 * @throws JIException
	 *             ошибка DCOM
	 */
	public void callMethod(String aName, Object[] aInparams) throws JIException {
		dispatch.callMethod(aName, aInparams);
	}

	/**
	 * Вызов метода без параметров и без результата запроса. (Процедура без
	 * параметров)
	 * 
	 * @param aName
	 *            - имя метода в IDispatch объекта.
	 * @throws JIException ошибка DCOM.
	 */
	public final void callMethod(final String aName) throws JIException {
		dispatch.callMethod(aName);
	}
	
	/**
	 * Вызов функции с параметрами.
	 * @param aName имя функции.
	 * @param aInparams массив параметров JIVairant
	 * @return Массив вызодных значений определенных в функции IDispatch
	 * @throws JIException ошибка DCOM.
	 */
	public final JIVariant[] callMethodA(final String aName,
			final Object[] aInparams) throws JIException {
		return dispatch.callMethodA(aName, aInparams);
	}

	/**
	 * Вызов функции без параметров.
	 * @param aName имя функции
	 * @return единственный параметр ответа.
	 * @throws JIException ошибка DCOM.
	 */
	public final JIVariant callMethodA(final String aName) throws JIException {
		return dispatch.callMethodA(aName);
	}

	/**
	 * Получение свойств объекта по его имени из IDispatch.
	 * @param aName имя свойства
	 * @return JIVariant значение свойства.
	 * @throws JIException ошибка DCOM.
	 */
	public final JIVariant get(final String aName) throws JIException {
		return dispatch.get(aName);
	}
	
	/**
	 * Получение свойств объекта по его ID из IDispatch.
	 * @param id свойства заданного в IDispatch
	 * @return JIVariant
	 * @throws JIException ошибка обращений к свойству.
	 */
	public final JIVariant getByID(final int id) throws JIException {
		return dispatch.get(id);
	}

	public void put(String aName, JIVariant aInparam) throws JIException {
		dispatch.put(aName, aInparam);
	}

	public void put(String aName, Object[] aParams) throws JIException {
		dispatch.put(aName, aParams);
	}

	/**
	 * Получение IJIDispatch объекта.
	 * @return IJIDispatch
	 */
	public final IJIDispatch dispatch() {
		return dispatch;
	}
	
	/**
	 * Установка IDispatch для текущего экземпляра.
	 * @param aDispatch - IJIDispatch ссылка
	 */
	public final void setDispatch(final IJIDispatch aDispatch) {
		dispatch = aDispatch;
	}
}
