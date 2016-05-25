/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCXSComponentFixedList extends OCObject {

	protected OCXSComponentFixedList(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество элементов коллекции. 
	 * @return int
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает элемент коллекции по индексу. 
	 * @param index Индекс элемента коллекции.
	 * @return 
	 * @return
	 * @throws JIException
	 */
	@SuppressWarnings("unchecked")
	public <T extends OCXSBasicComponent> T get(int index) throws JIException {
		JIVariant var = callMethodA("Get", new JIVariant(index))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return (T) new OCXSBasicComponent(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Проверяет наличие в коллекции указанной компоненты схемы XML. 
	 * @param object Проверяемая компонента схемы XML. 
	 * @return true - если найден, false - элемент отсутствует
	 * @throws JIException
	 */
	public boolean contains(OCXSBasicComponent object) throws JIException {
		return callMethodA("Contains", new JIVariant(ocObject2Dispatch(object)))[0].getObjectAsBoolean();
	}

}
