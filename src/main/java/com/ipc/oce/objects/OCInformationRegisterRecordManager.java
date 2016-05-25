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
 * Позволяет читать, записывать и удалять отдельную запись регистра сведений.
 * Используется только для регистров сведений, неизменяемых регистраторами, т.е.
 * для которых в конфигураторе установлен режим записи "Независимый".
 * Предназначен для интерактивной работы с записью регистра сведений. Доступ
 * (чтение и запись) к записям регистра сведений производится объектом
 * РегистрСведенийНаборЗаписей.<Имя регистра сведений> на системном уровне. При
 * этом, в общем случае, используются два набора записей: один предназначен для
 * удаления "старой" записи, другой - для записи данных, определенных менеджером
 * записи. Это проявляется, например, в том, что при выполнении записи могут
 * дважды вызываться события ПередЗаписью и ПриЗаписи объекта
 * РегистрСведенийНаборЗаписей.<Имя регистра сведений>, сначала для пустого
 * набора записей удаляющего "старую" запись, а затем для набора записей с
 * новыми данными.
 * 
 * @author Konovalov
 * 
 */
public class OCInformationRegisterRecordManager extends OCObject {

	protected OCInformationRegisterRecordManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	protected OCInformationRegisterRecordManager(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	protected OCInformationRegisterRecordManager(OCObject object) {
		super(object);
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
	 * Определяет, считана запись или нет. При изменении отбора считается, что
	 * набор перестает быть считанным.
	 * 
	 * @return Истина - набор не изменялся; Ложь - изменялся, в частности, если
	 *         набор не считывался и не записывался, а также, если записывался с
	 *         добавлением записей.
	 * @throws JIException
	 */
	public boolean isSelected() throws JIException {
		return callMethodA("Selected").getObjectAsBoolean();
	}

	/**
	 * Записывает в базу данных запись регистра сведений с текущими значениями
	 * свойств. Если запись регистра сведений была предварительно считана из
	 * базы данных, то при выполнении метода сначала считанная запись удаляется,
	 * а затем производится запись измененной записи. С помощью параметра
	 * <Замещать> регулируется, будет ли метод замещать запись, если в момент
	 * выполнения метода существует другая (исключая считанную) запись с такими
	 * же значениями измерений регистра и с таким же периодом для периодических
	 * регистров. При вызове с параметром <Замещать> равным Ложь после записи в
	 * информационную базу запись очищается.
	 * 
	 * @param replace
	 *            Определяет режим замещения существующей записи с тем же
	 *            набором значений измерений регистра и с тем же периодом (для
	 *            периодических регистров). Истина - если запись регистра
	 *            сведений с таким набором существует, то выполняется замещение;
	 *            Ложь - запись будет дописана к уже существующим в
	 *            информационной базе записям.
	 * @throws JIException
	 */
	public void write(boolean replace) throws JIException {
		callMethod("Write", new JIVariant(replace));
	}
	
	/**
	 * Записывает в базу данных запись регистра сведений с текущими значениями
	 * свойств. Если запись регистра сведений была предварительно считана из
	 * базы данных, то при выполнении метода сначала считанная запись удаляется,
	 * а затем производится запись измененной записи. С помощью параметра
	 * <Замещать> регулируется, будет ли метод замещать запись, если в момент
	 * выполнения метода существует другая (исключая считанную) запись с такими
	 * же значениями измерений регистра и с таким же периодом для периодических
	 * регистров.
	 * 
	 * @see #write(boolean:true) 
	 * @throws JIException
	 */
	public void write() throws JIException {
		write(true);
	}

	/**
	 * Определяет, изменен ли объект по отношению к считанным значениям.
	 * Примечание: Метод не позволяет определить, была ли изменена
	 * соответствующая запись другими пользователями.
	 * 
	 * @return Истина - объект изменен; Ложь - в противном случае.
	 * @throws JIException
	 */
	public boolean isModified() throws JIException {
		return callMethodA("Modified").getObjectAsBoolean();
	}

	/**
	 * Считывает запись регистра сведений по установленным значениям ключевых
	 * полей.
	 * 
	 * @throws JIException
	 */
	public void read() throws JIException {
		callMethod("Read");
	}
	
	/**
	 * Удаляет запись из набора записей с установленными значениями ключевых
	 * полей.
	 * 
	 * @throws JIException
	 */
	public void delete() throws JIException {
		callMethod("Delete");
	}
}
