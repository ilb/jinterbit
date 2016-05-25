/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Представляет собой запись регистрации документа в последовательности
 * @author Konovalov
 *
 */
public class OCSequenceRecord extends OCObject {

	/**
	 * @param object
	 */
	public OCSequenceRecord(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCSequenceRecord(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCSequenceRecord(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Задает дату записи последовательности
	 * @return
	 * @throws JIException
	 */
	public Date getPeriod() throws JIException{
		return get("Period").getObjectAsDate();
	}
	
	/**
	 * Задает дату записи последовательности
	 * @param date
	 * @throws JIException
	 */
	public void setDate(Date date) throws JIException{
		put("Period", new JIVariant(date));
	}
	
	/**
	 * Содержит документ, внесший запись последовательности
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef getRecorder() throws JIException{
		return new OCDocumentRef(get("Recorder"));
	}
	
	/**
	 * Устанавливает документ, внесший запись последовательности
	 * @param ref
	 * @throws JIException
	 */
	public void setRecorder(OCDocumentRef ref) throws JIException {
		put("Recorder", new JIVariant(ref.dispatch()));
	}
	
	/**
	 * Получает момент времени, соответствующий записи
	 * @return OCPointInTime
	 * @throws JIException
	 */
	public OCPointInTime getPointInTime() throws JIException {
		return new OCPointInTime(callMethodA("PointInTime"));
	}

}
