/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.OCObject;

/**
 * Коллекция компонент схемы XML, имеющих имя.
 * @author Konovalov
 *
 */
public class OCXSNamedComponentMap extends OCObject {

	protected OCXSNamedComponentMap(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество элементов в коллекции.
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает компоненты коллекции по индексу.
	 * @param index Индекс компоненты в коллекции. 
	 * @return OCXSBasicComponent
	 * @throws JIException
	 */
	public OCXSBasicComponent getByIndex(int index) throws JIException {
		JIVariant var = callMethodA("Get", new JIVariant(index))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCXSBasicComponent(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Получает компоненты по имени. 
	 * @param name Имя компоненты.
	 * @return OCXSBasicComponent
	 * @throws JIException
	 */
	public OCXSBasicComponent getByName(String name) throws JIException {
		JIVariant var = callMethodA("Get", new JIVariant(name))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCXSBasicComponent(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Получает компоненты по имени и URI пространства имен. 
	 * @param name Имя компоненты. 
	 * @param uri  URI пространства имен компоненты. 
	 * @return OCXSBasicComponent
	 * @throws JIException
	 */
	public OCXSBasicComponent getByQName(String name, String uri) throws JIException {
		JIVariant var = callMethodA("Get", new Object[]{new JIVariant(name), new JIVariant(uri)})[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCXSBasicComponent(var);
		} else {
			return null;
		}
	}
}
