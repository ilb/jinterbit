package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.msa.ComApp;
import com.ipc.msa.ComObject;

/**
 * Базовый объект для всех DCOM объектов 1С.
 * @author Konovalov
 *
 */

public class OCObject {
	
	/**
	 * Listener ошибок вызываемый в catch каждого метода экземпляра объекта.
	 */
	private ErrorListener errorListener = new ErrorListenerVoidImpl();
	
	private ComObject comObject = new ComObject();
	
	/**
	 * Кэш внутреннего представления.
	 */
	private String inner = null;
	
	/**
	 * Кэш hash объекта.
	 */
	private int hash = Integer.MAX_VALUE;
	
	/**
	 * Идентификатор сессии.
	 */
	private int sessionID = -1;
	
	public OCObject(OCObject object) {
		this(object.dispatch());
		sessionID =  dispatch().getAssociatedSession().getSessionIdentifier();
	}
	
	public OCObject(IJIDispatch aDispatch) {
		super();
		comObject.setDispatch(aDispatch);
		sessionID =  dispatch().getAssociatedSession().getSessionIdentifier();
	}

	public OCObject(JIVariant aDispatch) throws JIException {
		super();
		comObject.setDispatch(ComApp.toDispatch(aDispatch));
		sessionID =  dispatch().getAssociatedSession().getSessionIdentifier();
	}
	
	/**
	 * Установка ErrorListener базового объекта.
	 * @param errorListener (ErrorListener)
	 */
	public synchronized void setErrorListener(ErrorListener errorListener) {
		if (errorListener != null) {
			this.errorListener = errorListener;
		} else {
			throw new NullPointerException("ErrorListener can't be null");
		}
	}
	
	/**
	 * Получение ErrorListener базового объекта.
	 * @return ErrorListener
	 */
	protected ErrorListener getErrorListener() {
		return errorListener;
	}

	/**
	 * @deprecated TODO Убрать этот конструктор!
	 */
	public OCObject() {
		super();
	}

	protected void callMethod(String aName, Object[] aInparams) throws JIException {
		try {
			comObject.callMethod(aName, aInparams);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}
	
	protected void callMethod(String aName, JIVariant variant) throws JIException {
		try{
			callMethod(aName, new Object[]{variant});
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected void callMethod(String aName) throws JIException {
		try{
			comObject.callMethod(aName);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected JIVariant[] callMethodA(String aName, Object[] aInparams)
			throws JIException {
		try {
			return comObject.callMethodA(aName, aInparams);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}
	
	protected JIVariant[] callMethodA(String aName, JIVariant variant) throws JIException {
		try {
			return callMethodA(aName, new Object[]{variant});
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected JIVariant callMethodA(String aName) throws JIException {
		try {
			return comObject.callMethodA(aName);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected IJIDispatch dispatch() {
		try {
			return comObject.dispatch();
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected void setDispatch(IJIDispatch aDispatch) {
		try {
			comObject.setDispatch(aDispatch);
			sessionID =  dispatch().getAssociatedSession().getSessionIdentifier();
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected JIVariant get(String aName) throws JIException {
		try {
			return comObject.get(aName);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}
	
	protected JIVariant getByID(int aID) throws JIException {
		try {
			return comObject.getByID(aID);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected void put(String aName, JIVariant aInparam) throws JIException {
		try {
			comObject.put(aName, aInparam);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}
	
	protected void putRef(String aName, OCObject object) throws JIException {
		try {
			comObject.put(aName, new JIVariant(object.dispatch()));
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected void put(String aName, Object[] aParams) throws JIException {
		try {
			comObject.put(aName, aParams);
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}
	
	/**
	 * Установка значения свойства объекта DCOM.
	 * @param aName - имя поля.
	 * @param variant - значение поля.
	 * @throws JIException - ошибка DCOM.
	 */
	protected void put(String aName, OCVariant variant) throws JIException {
		try {
			put(aName, variant.getJIVariant());
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}
	
	/**
	 * Получить внутренне представление объекта.
	 * @return строковое значение внутреннего представления объекта в 1С.
	 * @throws JIException
	 */
	public String innerRepresentation() throws JIException {
		try {
			if (inner == null) {
				inner = OCApp.getInstance(getAssociatedSessionID())
						.valueToStringInternal(dispatch());
			}
			return inner;
		} catch (JIException e) {
			errorListener.onError(this, e);
			throw e;
		} catch (RuntimeException e) {
			errorListener.onError(this, e);
			throw e;
		}
	}

	protected int getAssociatedSessionID() {
		return sessionID; 
	}
	
	public OCApp getApplicationContext() {
		return OCApp.getInstance(getAssociatedSessionID());
	}
	
	@Override
	public String toString() {
		try {
			OCApp app = OCApp.getInstance(getAssociatedSessionID());
			if (app == null) {
				throw new IllegalStateException(
						"Application instance is null. May be session not started or expired");
			}
			return app.string(this);
		} catch (JIException e) {
			e.printStackTrace();
		}
		return super.toString();
	}
	
	
	
	@Override
	public int hashCode() {
		try {
			if (hash == Integer.MAX_VALUE) {
				hash = innerRepresentation().hashCode();
			}
			return hash;
		} catch (JIException e) {
			return super.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object paramObject) {
		if (paramObject == this) {
			return true;
		}
		if (paramObject instanceof OCObject) {
			OCObject oco = (OCObject) paramObject;
			try {
				return this.innerRepresentation().equals(oco.innerRepresentation());
			} catch (JIException e) {
				return super.equals(paramObject);
			}
		} else {
			return super.equals(paramObject);
		}
	}
	/**
	 * Сервисный метод. Выполняет преобразование OCVariant в JIVariant для унаследованнх объектов из доругих пакетов
	 * @param var
	 * @return
	 */
	protected JIVariant ocVariant2JI(OCVariant var) {
		return var.getJIVariant();
	}
	
	protected IJIDispatch ocObject2Dispatch(OCObject object) {
		return object.dispatch();
	}
}
