package com.ipc.oce;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Предназначен для доступа к элементам массива, его методам и конструктору.
 * Возможен обмен с сервером. Сериализуется.
 * 
 * @author Konovalov
 * 
 */
public class OCArray extends OCObject implements Iterable<OCVariant> {

	public OCArray(OCObject object) {
		super(object);
	}

	public OCArray(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCArray(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество элементов в массиве. 
	 * @return количество элементов в массиве. 
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает значение по индексу. Работает аналогично оператору []. 
	 * @param i Индекс элемента. 
	 * @return 
	 * @throws JIException
	 */
	public OCVariant get(int i) throws JIException {
		return new OCVariant(callMethodA("Get", new Object[]{new JIVariant(i)})[0]);
	}
	
	/**
	 * Вставляет значение в массив по указанному индексу. 
	 * @param variant
	 * @param index
	 * @throws JIException
	 */
	public void insert(int index, OCVariant variant ) throws JIException {
		callMethod("Insert", new Object[]{new JIVariant(index), variant.getJIVariant()});
	}
	
	/**
	 * Добавляет элемент в конец массива. 
	 * @param variant
	 * @throws JIException
	 */
	public void add(OCVariant variant) throws JIException {
		callMethod("Add", new Object[]{variant.getJIVariant()});
	}
	
	/**
	 * Получает наибольший индекс элемента массива. Наибольший индекс соответствует количеству элементов массива минус 1. 
	 * @return Наибольший индекс в массиве. Если количество элементов массива равно 0, возвращает -1.  
	 * @throws JIException
	 */
	public Integer uBound() throws JIException {
		return callMethodA("UBound").getObjectAsInt();
	}
	
	/**
	 * Удаляет все значения из массива. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Удаляет значение из массива по указанному индексу. 
	 * Примечание:
	 * Если указанный в параметре индекс больше, чем ВГраница, то никаких действий не производится и не выдается сообщение "Индекс находится за границами массива". 
	 * @param index Индекс удаляемого элемента. 
	 * @throws JIException
	 */
	public void delete(int index) throws JIException {
		callMethod("Delete", new Object[]{new JIVariant(index)});
	}

	public Iterator<OCVariant> iterator() {
		Iterator<OCVariant> iter = new Iterator<OCVariant>() {
			int currentRow = 0;
			
			public void remove() {
				try {
					delete(currentRow);
				} catch (JIException e) {
					e.printStackTrace();
				}
				
			}
			
			public OCVariant next() {
				try {
					return get(currentRow++);
				} catch (JIException e) {
					e.printStackTrace();
					return null;
				}
			}
			
			public boolean hasNext() {
				try {
					return (currentRow < size());
				} catch (JIException e) {
					e.printStackTrace();
					return false;
				}
			}
		};
		return iter;
	}
	
}
