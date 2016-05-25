/**
 * 
 */
package com.ipc.oce;

/**
 * @author Konovalov
 *
 */
public interface InstancePool {

	/**
	 * Максимально допустимый объем пула.
	 * @return max количество экземпляров OCApp в пуле
	 */
	int getMaximumInstances();

	/**
	 * Получение экземпляра OCApp по номеру COM-сессии.
	 * @param key - ключ - идентификатор DCOM-сессии
	 * @return экземпляр OCApp или null если не найден по ключу key
	 */
	OCApp get(Integer key);
	
	/**
	 * Помещение экземпляра OCApp в пул экхемпляров. 
	 * Важно! Помещать нужно только экземпляры, 
	 * для которых выполнено соединение с 1С 
	 * (метод connect вернул номер соединения)
	 * @param key - номер COM-сессии с 1C
	 * @param value - экземпляр OCApp
	 * @return замененный экземпляр OCApp или null
	 */
	OCApp put(Integer key, OCApp value);

	/**
	 * Удаление экземпляра OCApp из пула.
	 * @param key - сессионный идентификатор DCOM
	 * @return удаленный экземпляр
	 */
	OCApp remove(Integer key);
	
	/**
	 * Текущий размер пула экземпляров.
	 * @return размер пула
	 */
	int size();

}