/**
 * 
 */
package com.ipc.oce;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;



/**
 * Представляет собой коллекцию пар КлючИЗначение. При этом ключ может быть только строковым и должен удовлетворять требованиям, предъявляемым к именованию переменных встроенного языка. 
 * К значениям структуры можно обращаться как к свойствам объекта. При этом ключ используется как имя свойства.
 * Структура используется обычно для хранения небольшого количества значений, каждое из которым имеет некоторое имя.
 * @author Konovalov
 *
 */
/*
 * Однако неочень понятно как незная ключей производить чтение структуры.
 * Хе-хе. Не прошло и полгода как был открыт прамерт -4!!!
 */
public class OCStructure  extends OCObject implements Iterable<OCKeyAndValue> {

	/**
	 * @param object
	 */
	public OCStructure(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCStructure(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCStructure(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * В качестве свойств структура предоставляет элементы. Имя свойства
	 * определяется ключом, а значение - свойства определяется значением
	 * элемента.
	 * 
	 * @param aName имя ключа
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValue(String aName) throws JIException {
		return new OCVariant(super.get(aName));
	}
	
	/**
	 * Устанавливает значение элемента структуры по ключу. Если элемент с переданным значением ключа существует, то его значение заменяется, в противном случае добавляется новый элемент. 
	 * @param key  Ключ устанавливаемого элемента
	 * @param variant (OCVariant) Произвольный. Значение устанавливаемого элемента
	 * @throws JIException
	 */
	public void insert(String key, OCVariant variant) throws JIException {
		callMethod("Insert", new Object[]{new JIVariant(key), variant.getJIVariant()});
	}
	
	/**
	 * Устанавливает значение элемента структуры по ключу. Если элемент с переданным значением ключа существует, то его значение заменяется, в противном случае добавляется новый элемент.
	 * @param key Ключ устанавливаемого элемента
	 * @param object OCObject
	 * @throws JIException
	 */
	public void insert(String key, OCObject object) throws JIException {
		OCVariant ocVariant = new OCVariant(object);
		insert(key, ocVariant);
	}
	
	/**
	 * Этот метод использует JIVariant.makeVariable основанный на obj.getClass.
	 * @param key
	 * @param object
	 * @throws JIException 
	 */
	public void insert(String key, Object object) throws JIException {
		callMethod("Insert", new Object[]{new JIVariant(key), JIVariant.makeVariant(object)});
	}
	
	/**
	 * Добавление данных в структуру через интерфейс Map. Используетя JIVariant.makeVariable
	 * @param map параметры для заполнения структуры
	 * @throws JIException
	 */
	public void insert(Map<String, Object> map) throws JIException {
		if (map == null) {
			return;
		}
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			insert(entry.getKey(), entry.getValue());
		}
		/* inefficient use of keySet iterator
		 * Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			insert(key, map.get(key));
		}*/
	}
	
	/**
	 * Добавление массива в структуру
	 * @param key
	 * @param array
	 * @throws JIException
	 */
	/*public void insertArray(String key, Object[] array) throws JIException{
		
		JIArray jiarray = new JIArray(array[0].getClass(),new int[]{array.length},1,false);
		jiarray.
		callMethod("Insert", new Object[]{new JIVariant(key), new JIVariant(new JIArray( vars, true )) });
	}*/
	
	/**
	 * Удаляет все элементы структуры. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * 
	 * @param key
	 * @throws JIException
	 */
	public void delete(String key) throws JIException {
		callMethod("Delete", new Object[]{new JIVariant(key)});
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 * #issue 19
	 */
	public Iterator<OCKeyAndValue> iterator() {
		EnumVARIANT<OCKeyAndValue> enumV = new EnumVARIANT<OCKeyAndValue>(this) {
			
			@Override
			protected OCKeyAndValue castToGeneric(JIVariant variant) {
				try {
					return new OCKeyAndValue(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}

}
