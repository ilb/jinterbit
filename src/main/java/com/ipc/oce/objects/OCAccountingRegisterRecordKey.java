/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Набор значений, однозначно идентифицирующих запись регистра. Объект
 * используется в тех случаях, когда необходимо сослаться на определенную
 * запись. Например, он выступает в качестве значение свойства ТекущаяСтрока
 * табличного поля, отображающего список записей регистра. Важно! Данный объект
 * нельзя использовать как устойчивый идентификатор записи, например, как ссылку
 * для элементов справочника. Фактически имеет смысл только пока запись есть и
 * не перезаписывалась.
 * 
 * @author Konovalov
 * 
 */
public class OCAccountingRegisterRecordKey extends OCObject {

	/**
	 * @param object
	 */
	public OCAccountingRegisterRecordKey(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccountingRegisterRecordKey(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccountingRegisterRecordKey(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит уникальный номер строки данной записи в списке записей по
	 * регистратору, указанному в значении свойства Регистратор.
	 * 
	 * @return Integer
	 * @throws JIException
	 */
	public Integer getLineNumber() throws JIException{
		return get("LineNumber").getObjectAsInt();
	}
	
	/**
	 * Содержит ссылку на документ, внесший запись регистра накопления. 
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef getRecorder() throws JIException{
		return new OCDocumentRef(get("Recorder"));
	}
}
