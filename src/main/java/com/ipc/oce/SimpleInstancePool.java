/**
 * 
 */
package com.ipc.oce;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Простое хранение ссылок на экземпляры OCApp.
 * @author Konovalov
 *
 */
public class SimpleInstancePool implements InstancePool {
	
	private static final int DEFAULT_POOL_SIZE = 10;
	
	private int maximumInstances = DEFAULT_POOL_SIZE;
	
	private Map<Integer, OCApp> instancesStorage = null;
	//==================================
	public SimpleInstancePool() {
		instancesStorage = new ConcurrentHashMap<Integer, OCApp>();
	}
	

	public int getMaximumInstances() {
		return maximumInstances;
	}

	protected void setMaximumInstances(int maximumInstances) {
		this.maximumInstances = maximumInstances;
	}

	public OCApp get(Integer key) {
		return instancesStorage.get(key);
	}
 
	public OCApp put(Integer key, OCApp value) {
		if (size() == maximumInstances) {
			throw new RuntimeException(
					"Maximum number of open instances reached");
		}
		return instancesStorage.put(key, value);
	}
 
	public OCApp remove(Integer key) {
		return instancesStorage.remove(key);
	}
 
	public int size() {
		return instancesStorage.size();
	}
	
	/**
	 * Возвращает первую попавшуюся OCApp с успешным пингом.
	 * ВНИМАНИЕ! Данный метод можно использовать только если:
	 * 1) в пуле находятся соединения к одной базе 1С.
	 * 2) если вызов производится НЕ из внетренних классов OCTitbit.
	 * @return активное соединение OCApp или null если в пуле не содержится живых соединений.
	 */
	public OCApp getFirstConnectedInstance() {
		Set<Integer> sessionIdSet = instancesStorage.keySet();
		OCApp activeApp = null;
		for (Integer si : sessionIdSet) {
			OCApp app = get(si);
			if (app.ping()) {
				activeApp = app;
			}
		}
		return activeApp;
	}
	
	
}
