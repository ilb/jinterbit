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
import com.ipc.oce.varset.EAccountingRecordType;

/**
 * Используется для доступа к записи регистра бухгалтерии. Объект не создается
 * непосредственно, а предоставляется другими объектами, отвечающими за регистр
 * бухгалтерии. Например, данный объект представляет записи регистра в наборе
 * записей.
 * 
 * @author Konovalov
 * 
 */
public class OCAccountingRegisterRecord extends OCObject implements AttributeBean{

	/**
	 * @param object
	 */
	public OCAccountingRegisterRecord(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccountingRegisterRecord(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccountingRegisterRecord(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения реквизитов регистра бухгалтерии. Доступ к значению осуществляется по имени, как оно задано в конфигураторе. 
	 * @param attributeName - именя реквизита, как оно задано в конфигураторе.
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Установка значения реквизитов регистра бухгалтерии.
	 * @param attributeName - имени реквизита, как оно задано в конфигураторе.
	 * @param variant - значение реквизита.
	 * @throws JIException
	 */
	public void setAttributeValue(String attributeName, OCVariant variant) throws JIException {
		put(attributeName, variant);
	}
	
	/**
	 * Набор свойств содержит значения ресурсов регистра бухгалтерии. Доступ к значению осуществляется по имени, как оно задано в конфигураторе. 
	 * @param resourceName - именя ресурса, как оно задано в конфигураторе. 
	 * @return Number
	 * @throws JIException
	 */
	public Number getResource(String resourceName) throws JIException{
		OCVariant v = new OCVariant(get(resourceName));
		return (Number) v.value();
	}
	
	/**
	 * Установка значения ресурсов регистра бухгалтерии.
	 * @param resourceName - именя ресурса, как оно задано в конфигураторе.
	 * @param number - значение ресурса
	 * @throws JIException
	 */
	public void setResource(String resourceName, Number number) throws JIException {
		put(resourceName, JIVariant.makeVariant(number));
	}
	
	/**
	 * Набор свойств содержит значения измерений регистра бухгалтерии. Доступ к значению осуществляется по имени, как оно задано в конфигурации. 
	 * @param dimensionName - имени измерения, как оно задано в конфигурации
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getDimension(String dimensionName) throws JIException{
		return new OCVariant(get(dimensionName));
	}
	
	/**
	 * Установка значения измерений регистра бухгалтерии.
	 * @param dimensionName - имени измерения, как оно задано в конфигурации
	 * @param variant - значение измерения
	 * @throws JIException
	 */
	public void setDimension(String dimensionName, OCVariant variant) throws JIException {
		put(dimensionName, variant);
	}
	
	/**
	 * Содержит признак активности записи. Неактивные записи не отражаются в итогах и не учитываются при подсчете остатков и оборотов. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isActive() throws JIException {
		return get("Active").getObjectAsBoolean();
	}
	
	/**
	 * Установка признака активности записи.
	 * @param activeStatus - признак активности.
	 * @throws JIException
	 */
	public void setActive(boolean activeStatus) throws JIException {
		put("Active", new JIVariant(activeStatus));
	}
	
	/**
	 * Определяет вид движения для записи регистра, не поддерживающего корреспонденцию. 
	 * @return
	 * @throws JIException
	 */
	public EAccountingRecordType getRecordType() throws JIException {
		return new EAccountingRecordType(get("RecordType"));
	}
	
	/**
	 * Определяет вид движения для записи регистра, не поддерживающего корреспонденцию.
	 * @param recordType - тип записи
	 * @throws JIException
	 */
	public void setRecordType(EAccountingRecordType recordType) throws JIException {
		put("RecordType", new JIVariant(ocObject2Dispatch(recordType)));
	}
	
	/**
	 * Номер записи. Примечание: Нумерация начинается с 1.
	 * 
	 * @return - номер записи
	 * @throws JIException
	 */
	public Integer getLineNumber() throws JIException {
		return get("LineNumber").getObjectAsInt();
	}
	
	/**
	 * Период, на который сделана запись. 
	 * @return
	 * @throws JIException
	 */
	public Date getPeriod() throws JIException {
		return get("Period").getObjectAsDate();
	}
	
	/**
	 * Период, на который сделана запись. 
	 * @param period
	 * @throws JIException
	 */
	public void setPeriod(Date period) throws JIException {
		put("Period" , new JIVariant(period));
	}
	
	/**
	 * Регистратор записи.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef getRecorder() throws JIException {
		return new OCDocumentRef(get("Recorder"));
	}
	
	/**
	 * Регистратор записи.
	 * @param ref
	 * @throws JIException
	 */
	public void setRecorder(OCDocumentRef ref) throws JIException {
		put("Recorder", new JIVariant(ocObject2Dispatch(ref)));
	}
	
	/**
	 * Коллекция субконто. Позволяет устанавливать значения субконто.
	 * Примечание: Только для регистров, не поддерживающих корреспонденцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCAccountingRegisterExtDimensions getExtDimensions() throws JIException {
		return new OCAccountingRegisterExtDimensions(get("ExtDimensions"));
	}
	
	/**
	 * Коллекция субконто дебета. Примечание: Только для регистров,
	 * поддерживающих корреспонденцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCAccountingRegisterExtDimensions getExtDimensionsDr() throws JIException {
		return new OCAccountingRegisterExtDimensions(get("ExtDimensionsDr"));
	}
	
	/**
	 * Коллекция субконто кредита. Примечание: Только для регистров,
	 * поддерживающих корреспонденцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCAccountingRegisterExtDimensions getExtDimensionsCr() throws JIException {
		return new OCAccountingRegisterExtDimensions(get("ExtDimensionsCr"));
	}
	
	/**
	 * Значение реквизита счет. Примечание: Только для регистра, не
	 * поддерживающего корреспонденцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getAccount() throws JIException {
		return new OCChartOfAccountsRef(get("Account"));
	}
	
	/**
	 * Счет дебета. Примечание: Только для регистра, поддерживающего
	 * корреспонденцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getAccountDr() throws JIException {
		return new OCChartOfAccountsRef(get("AccountDr"));
	}
	
	/**
	 * Счет кредита. Примечание: Только для регистра, поддерживающего
	 * корреспонденцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getAccountCr() throws JIException {
		return new OCChartOfAccountsRef(get("AccountCr"));
	}
	
	/**
	 * Получает момент времени, соответствующий записи регистра. 
	 * @return OCPointInTime
	 * @throws JIException
	 */
	public OCPointInTime getPointInTime() throws JIException {
		return new OCPointInTime(callMethodA("PointInTime"));
	}
}
