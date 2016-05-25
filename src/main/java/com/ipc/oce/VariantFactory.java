/**
 * 
 */
package com.ipc.oce;

import java.util.HashMap;
import java.util.Map;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.varset.EActivator;



/**
 * @author Konovalov
 *
 */
public abstract class VariantFactory {
	
	private OCApp appInstance = null;

	private Map<String, EActivator> varsetObjects = null;
	
	private static final int DEFAULT_SIZE = 50;
	
	/**
	 * 
	 */
	protected VariantFactory() {
		varsetObjects = new HashMap<String, EActivator>(DEFAULT_SIZE);
	}
	
	public void storeObject(String objectName, EActivator objectInstance){
		varsetObjects.put(objectName, objectInstance);
	}
	
	/**
	 * Поиск загегистрированных varset активаторов.
	 * @param <T>
	 * @param name <SimpleClassName>.<FieldValue>
	 * @return если объект найден будет выполнен bind(rebind) к сессии. Если не найден, то Null
	 * @throws JIException
	 */
	@SuppressWarnings("unchecked")
	public <T> T findAndBindObject(String name) throws JIException{
		EActivator activator = varsetObjects.get(name);
		if (activator != null) {
			activator.bind(appInstance); // bind forced
		}
		return (T) activator;
	}
	
	/**
	 * @deprecated since 0.4.0 use bindObject instead! 
	 * bind активатора рекомендуется производить при обращении к объекту. 
	 * Хранение ссылок на готовые объекты не привело ни к чему хорошему.
	 * @param <T>
	 * @param objectName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T findObject(String objectName){
		return (T) varsetObjects.get(objectName);
	}
	
	protected final void setAppInstance(OCApp appInstance) {
		if (appInstance == null) {
			throw new RuntimeException(
					"VariantFactory: application instance can't be null");
		}
		this.appInstance = appInstance;
	}
	
	/**
	 * Метод создения наборов активаторов. Реализуется в конкретной имплементации для версии драйвера
	 * @throws JIException
	 */
	protected abstract void activate() throws JIException;
	
/*	protected OCObject inner2object(String inner) throws JIException{
		return new OCObject(appInstance.valueFromStringInternal(inner));
	}*/
	
	
}
