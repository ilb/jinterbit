/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;

/**
 * Набор значений, однозначно идентифицирующих запись регистра. Объект
 * используется в тех случаях, когда необходимо сослаться на определенную
 * запись. Например, он выступает в качестве значения свойства ТекущаяСтрока
 * табличного поля, отображающего список записей регистра. <b>Важно!</b> Данный объект
 * нельзя использовать как устойчивый идентификатор записи, например, как ссылку
 * для элементов справочника. Фактически имеет смысл только пока запись есть и
 * не перезаписывалась.
 * 
 * @author Konovalov
 * 
 */
public class OCInformationRegisterRecordKey extends OCObject {

	/**
	 * @param object
	 */
	public OCInformationRegisterRecordKey(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCInformationRegisterRecordKey(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCInformationRegisterRecordKey(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	/**
	 * Набор свойств содержит значения измерений регистра сведений. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигурации.
	 * 
	 * @param name
	 *            имя измерения
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getDimension(String name) throws JIException {
		return new OCVariant(get(name));
	}
	
	/**
	 * Набор свойств содержит значения измерений регистра сведений. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигурации.
	 * 
	 * @param name
	 *            - имя измерения
	 * @param variant
	 *            - значение
	 * @throws JIException
	 */
	public void setDimension(String name, OCVariant variant) throws JIException {
		put(name, variant);
	}
	
	/**
	 * Содержит дату и время записи периодического регистра сведений.
	 * Для непериодических регистров сведений смысла не имеет. 
	 * @return Date
	 * @throws JIException
	 */
	public Date getPeriod() throws JIException {
		return get("Period").getObjectAsDate();
	}

	/**
	 * Установить дату и время записи периодического регистра сведений.
	 * Для непериодических регистров сведений смысла не имеет. 
	 * @param period Date
	 * @throws JIException
	 */
	public void setPeriod(Date period) throws JIException {
		put("Period", new JIVariant(period));
	}
	
	/**
	 * Содержит регистратор, который занес данную запись регистра сведений.
	 * Имеет смысл только для регистра, у которого в конфигураторе установлен
	 * режим записи "Подчинение регистратору" и периодичность
	 * "По позиции регистратора".
	 * 
	 * @return OCDocumentRef
	 * @throws JIException
	 */
	public OCDocumentRef getRecorder() throws JIException {
		JIVariant var = get("Recorder");
		OCDocumentRef ref = null;
		if (var.getType() != JIVariant.VT_EMPTY) {
			ref = new OCDocumentRef(var);
		}
		return ref;
	}
}
