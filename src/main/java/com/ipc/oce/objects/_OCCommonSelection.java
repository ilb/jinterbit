package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public abstract class _OCCommonSelection extends OCObject{
	
	private int selectionCapacity = -1;
	
	public _OCCommonSelection(OCObject object) {
		super(object);
	}

	public _OCCommonSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public _OCCommonSelection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает следующий объект из выборки. Для обхода списка объектов нужно после получения выборки использовать данный метод для позиционирования на первый и последующие объекты выборки до тех пор, пока он не вернет значение Ложь. 
	 * @return
	 * @throws JIException
	 */
	public Boolean next() throws JIException{
		return callMethodA("Next").getObjectAsBoolean();
	}

	/**
	 * @deprecated Получение размера выборки. Предусмотрен кэш, однако
	 *             применение данного метода приводит к полному перебору курсора
	 *             выборки. Стандартного метода для получения размера выборки в
	 *             1С нет. На больших выборках может работать весьма медленно
	 * @return количество записей в ResultSet-е
	 * @throws JIException
	 */
	public int size() throws JIException{
		if (selectionCapacity == -1) {
			selectionCapacity = 0;
			while (next()) {
				selectionCapacity++;
			}
		}
		return selectionCapacity;
	}
}
