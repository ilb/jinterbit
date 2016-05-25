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
 * Объект этого типа возвращается методами Выбрать и ВыбратьПоРегистратору у
 * объекта типа РегистрСведенийМенеджер.<Имя регистра сведений> и представляет
 * собой специализированный способ перебора записей регистра сведений. Обход
 * записей выполняется системой динамически. Это означает, что использование
 * выборки не считывает все записи сразу, а выбирает их порциями из базы данных.
 * Такой подход позволяет достаточно быстро обходить с помощью выборки большое
 * количество записей и не загружать в память всех элементов выборки.
 * 
 * @author Konovalov
 * 
 */
public class OCInformationRegisterSelection extends _OCCommonSelection {

	/**
	 * @param object
	 */
	public OCInformationRegisterSelection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCInformationRegisterSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCInformationRegisterSelection(JIVariant aDispatch)
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
	 * Набор свойств содержит значения реквизитов регистра сведения. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигураторе.
	 * 
	 * @param name
	 *            имя реквизита.
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getAttribute(String name) throws JIException {
		return new OCVariant(get(name));
	}
	
	/**
	 * Набор свойств содержит значения ресурсов регистра сведений. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигураторе.
	 * 
	 * @param name
	 *            имя ресурса.
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getResource(String name) throws JIException {
		return new OCVariant(get(name));
	}
	
	/**
	 * Содержит признак активности записи. Используется для регистров сведений,
	 * для которых в конфигураторе установлен режим записи
	 * "Подчинение регистратору". Записи, для которых значение данного свойства
	 * установлено в Ложь, не будут учитываться при получении сведений на
	 * определенный момент времени.
	 * 
	 * @return признак активности записи
	 * @throws JIException
	 */
	public boolean isActive() throws JIException {
		return get("Active").getObjectAsBoolean();
	}
	
	/**
	 * Содержит уникальный номер строки данной записи в списке записей по
	 * регистратору, указанному в значении свойства Регистратор. Используется
	 * для регистров, управляемых регистратором.
	 * 
	 * @return уникальный номер строки данной записи
	 * @throws JIException
	 */
	public int getLineNumber() throws JIException {
		return get("LineNumber").getObjectAsInt();
	}
	
	/**
	 * Содержит дату и время записи периодического регистра сведений.
	 * 
	 * @return Date
	 * @throws JIException
	 */
	public Date getPeriod() throws JIException {
		return get("Period").getObjectAsDate();
	}
	
	/**
	 * Содержит регистратор, который занес данную запись регистра сведений. Для
	 * регистра, у которого в конфигураторе установлен режим записи
	 * "Независимый", смысла не имеет.
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
	
	/**
	 * Получает объект для модификации, записи и удаления записи регистра сведений, на которой в данный момент спозиционирована выборка. 
	 * @return OCInformationRegisterRecordManager Менеджер записи регистра сведений
	 * @throws JIException
	 */
	public OCInformationRegisterRecordManager getRecordManager() throws JIException {
		return new OCInformationRegisterRecordManager(callMethodA("GetRecordManager"));
	}

}
