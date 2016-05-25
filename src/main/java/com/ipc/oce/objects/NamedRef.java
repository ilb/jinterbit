/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;

/**
 * @author Konovalov
 *
 */
public interface NamedRef {
	/**
	 * Получение полного имени ссылки (<Имя>.<Значение>)
	 * @return полное имя ссылки
	 * @throws JIException - ошибка работы DCOM
	 */
	String getRefFullName() throws JIException;
}
