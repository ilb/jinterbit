/**
 * 
 */
package com.ipc.msa;

import java.io.IOException;
import java.util.logging.Level;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.common.JISystem;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIClsid;
import org.jinterop.dcom.core.JIComServer;
import org.jinterop.dcom.core.JIProgId;
import org.jinterop.dcom.core.JISession;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Работа с приложениями по COM-интерфейсу.
 * 
 * @author nkv
 * 
 */
public class ComApp extends ComObject {

	/**
	 * Текущая COM сессия.
	 */
	private JISession session;

	/**
	 * Helper-метод преобразования IJIComObject в IDispatch через narrowObject.
	 * 
	 * @param aComObject
	 *            IJIComObject
	 * @return IJIDispatch
	 * @throws JIException
	 *             ошибка приведения COM-объекта к IJIDispatch.
	 */
	public static IJIDispatch toDispatch(final IJIComObject aComObject)
			throws JIException {
		return (IJIDispatch) JIObjectFactory
				.narrowObject((IJIComObject) aComObject
						.queryInterface(IJIDispatch.IID));
	}

	/**
	 * Helper-метод преобразования JIVariant в IDispatch через narrowObject.
	 * 
	 * @param aComObject
	 *            экземпляр JIVariant типа COM-объекта с интерфейсом IDispatch
	 * @return IJIDispatch
	 * @throws JIException
	 *             ошибка получения IDispatch интерфейса.
	 */
	public static IJIDispatch toDispatch(final JIVariant aComObject)
			throws JIException {
		return toDispatch(aComObject.getObjectAsComObject());
	}

	/**
	 * Default constructor.
	 */
	public ComApp() {
		super();
	}

	public void open(String server, String user, String password,
			String applId, boolean isClsId) throws JIException,
			SecurityException, IOException {
		open(server, server, user, password, applId, isClsId);
	}

	public void open(String server, String domain, String user, String password,
			String applId, boolean isClsId) throws JIException,
			SecurityException, IOException {
		
		JISystem.setInBuiltLogHandler(false);
		try {
			JISystem.getLogger().setLevel(Level.OFF);
		} catch (Exception e) {}
		
		//System.out.println("Domain: '" + domain + "', User: '" + user + "', Password: '" + password+"'");
		session = JISession.createSession(domain, user, password);
		
		//System.out.println("Server: '" + server + "', ClsId: '" + applId + "'");
		JIComServer comServer;
		if (isClsId) {
			comServer = new JIComServer(JIClsid.valueOf(applId), server,
					session);
		} else {
			comServer = new JIComServer(JIProgId.valueOf(applId), server,
					session);
		}

		// Instantiate the COM Server
		setDispatch(toDispatch(comServer.createInstance()));
	}

	/**
	 * Закрытие DCOM сессии.
	 * 
	 * @throws JIException
	 *             ошибка закрытия сессии.
	 */
	public final void close() throws JIException {
		// when your work is done
		JISession.destroySession(session);
		// optional step, the session will be destroyed anyways when the
		// application exits
		// but good to do when you are working with many COM servers and are
		// done with a few.
	}

	/**
	 * Установка параметра авторегистрации.
	 * 
	 * @param aAutoRegistration
	 *            true - ON, false - OFF.
	 */
	public final void setAutoRegistration(final boolean aAutoRegistration) {
		JISystem.setAutoRegisteration(aAutoRegistration);
	}

	/**
	 * Получение параметра авторегистрации подключения.
	 * 
	 * @return true - если включена авторегистрация.
	 */
	public final boolean getAutoRegistration() {
		return JISystem.isAutoRegistrationSet();
	}

	/**
	 * Установка GlobalTimeout.
	 * 
	 * @param value
	 *            время в миллисекундах
	 */
	public final void setGlobalTimeout(final int value) {
		if (session == null) {
			throw new IllegalStateException("You should create session first");
		}
		session.setGlobalSocketTimeout(value);
	}

	/**
	 * Получение общего таймуата.
	 * 
	 * @return время в миллисекундах
	 */
	public final int getGlobalTimeout() {
		if (session == null) {
			throw new IllegalStateException("You should create session first");
		}
		return session.getGlobalSocketTimeout();
	}

}
