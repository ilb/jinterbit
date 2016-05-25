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
 * Предоставляет доступ к записи регистра сведений. Объект не создается
 * непосредственно, а предоставляется другими объектами, связанными с регистром
 * сведений. Например, данный объект представляет записи регистра в наборе
 * записей.
 * 
 * @author Konovalov
 * 
 */
public class OCInformationRegisterRecord extends OCObject {

	/**
	 * @param object
	 */
	public OCInformationRegisterRecord(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCInformationRegisterRecord(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCInformationRegisterRecord(JIVariant aDispatch) throws JIException {
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
	 * Набор свойств содержит значения реквизитов регистра сведения. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигураторе.
	 * 
	 * @param name
	 *            имя реквизита.
	 * @param variant
	 *            значение реквизита
	 * @throws JIException
	 */
	public void setAttribute(String name, OCVariant variant) throws JIException {
		put(name, variant);
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
	 * Набор свойств содержит значения ресурсов регистра сведений. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигураторе.
	 * @param name имя ресурса
	 * @param variant значение ресурса.
	 * @throws JIException
	 */
	public void setResource(String name, OCVariant variant) throws JIException {
		put(name, variant);
	}
	
	/**
	 * Содержит признак активности записи. Используется для регистров сведений,
	 * для которых в конфигураторе установлен режим записи
	 * "Подчинение регистратору". Записи, для которых значение данного свойства
	 * установлено в Ложь, не будут учитываться при получении "первых" или
	 * "последних" записей регистра, а также при получении сведений на
	 * определенный момент времени.
	 * 
	 * @return признак активности записи.
	 * @throws JIException
	 */
	public boolean isActive() throws JIException {
		return get("Active").getObjectAsBoolean();
	}
	
	/**
	 * Содержит признак активности записи. Используется для регистров сведений,
	 * для которых в конфигураторе установлен режим записи
	 * "Подчинение регистратору". Записи, для которых значение данного свойства
	 * установлено в Ложь, не будут учитываться при получении "первых" или
	 * "последних" записей регистра, а также при получении сведений на
	 * определенный момент времени.
	 * 
	 * @throws JIException
	 */
	public void setActive(boolean active) throws JIException {
		put("Active", new JIVariant(active));
	}
	
	/**
	 * Содержит уникальный номер строки данной записи в списке записей по
	 * регистратору, указанному в значении свойства Регистратор. Используется
	 * для регистров, управляемых регистратором.
	 * 
	 * @return уникальный номер строки
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
	 * Установить дату и время записи периодического регистра сведений.
	 * @param period Date
	 * @throws JIException
	 */
	public void setPeriod(Date period) throws JIException {
		put("Period", new JIVariant(period));
	}
	
	/**
	 * Содержит регистратор, который занес данную запись регистра сведений.
	 * Примечание: Для регистра, у которого в конфигураторе установлен режим
	 * записи "Независимый", смысла не имеет.
	 * 
	 * @return OCDocumentRef
	 * @throws JIException
	 */
	public OCDocumentRef getRecorder() throws JIException {
		JIVariant var = get("Recorder");
		return var.getType() != JIVariant.VT_EMPTY ? new OCDocumentRef(var) : null;
	}
	
	/**
	 * Содержит регистратор, который занес данную запись регистра сведений.
	 * Примечание: Для регистра, у которого в конфигураторе установлен режим
	 * записи "Независимый", смысла не имеет.
	 * @param ref OCDocumentRef регистратор
	 * @throws JIException
	 */
	public void setRecorder(OCDocumentRef ref) throws JIException {
		put("Recorder", new JIVariant(ocObject2Dispatch(ref)));
	}
	
	/**
	 * Получает момент времени, соответствующий записи регистра. Применяется для
	 * регистров сведений, для которых в конфигураторе установлен режим записи
	 * "Подчинение регистратору".
	 * 
	 * @return OCPointInTime
	 * @throws JIException
	 */
	public OCPointInTime getPointInTime() throws JIException {
		return new OCPointInTime(callMethodA("PointInTime"));
	}
}
