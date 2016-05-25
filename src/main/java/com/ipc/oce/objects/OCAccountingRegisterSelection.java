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
 * @author Konovalov
 *
 */
public class OCAccountingRegisterSelection extends _OCCommonSelection implements AttributeGetter{

	/**
	 * @param object
	 */
	public OCAccountingRegisterSelection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccountingRegisterSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccountingRegisterSelection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения реквизитов регистра бухгалтерии. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигураторе
	 * 
	 * @param attributeName
	 *            - имя реквизита.
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Набор свойств содержит значения ресурсов регистра бухгалтерии. Доступ к
	 * значению осуществляется по имени, как оно задано в конфигураторе.
	 * 
	 * @param resourceName
	 *            - имя ресурса
	 * @return Number
	 * @throws JIException
	 */
	public Number getResource(String resourceName) throws JIException{
		OCVariant v = new OCVariant(get(resourceName));
		return (Number) v.value();
	}
	
	/**
	 * Набор свойств содержит значения измерений. Доступ к значению
	 * осуществляется по имени, как оно задано в конфигурации.
	 * 
	 * @param dimensionName
	 *            - имя измерения
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getDimension(String dimensionName) throws JIException{
		return new OCVariant(get(dimensionName));
	}
	
	/**
	 * Указывает, попадает ли в учет данная запись. Используется различными
	 * методами (являются некоторыми функциями от регистра: вычисление остатка,
	 * оборотов и т.д.), которые не должны учитывать неактивные записи. С другой
	 * стороны, методы перебора записей должны учитывать неактивные записи.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Boolean isActive() throws JIException {
		return get("Active").getObjectAsBoolean();
	}
	
	/**
	 * Содержит вид движения для записи регистра не поддерживающего корреспонденцию.
	 * @return EAccountingRecordType
	 * @throws JIException
	 */
	public EAccountingRecordType getRecordType() throws JIException {
		// Тут какая-то странность: платформа говорит что поля нет
		// возможно это доступно "Только для регистра без поддержки корреспонденции."
		return new EAccountingRecordType(get("RecordType"));
	}
	
	/**
	 *  Номер записи в наборе записей. Уникально в пределах набора записей по регистратору. 
	 * @return Integer
	 * @throws JIException
	 */
	public Integer getLineNumber() throws JIException {
		return get("LineNumber").getObjectAsInt();
	}
	
	/**
	 * Дата, на которую записываются движения. 
	 * @return Date
	 * @throws JIException
	 */
	public Date getPeriod() throws JIException {
		return get("Period").getObjectAsDate();
	}
	
	/**
	 * Ссылка на объект, являющийся регистратором данного движения
	 * @return OCDocumentRef
	 * @throws JIException
	 */
	public OCDocumentRef getRecorder() throws JIException {
		return new OCDocumentRef(get("Recorder"));
	}
	
	/**
	 * Содержит коллекцию значений дополнительных измерений.
	 * Только для регистра без поддержки корреспонденции
	 * @return OCAccountingRegisterExtDimensions
	 * @throws JIException
	 */
	public OCAccountingRegisterExtDimensions getExtDimensions() throws JIException {
		return new OCAccountingRegisterExtDimensions(get("ExtDimensions"));
	}
	
	/**
	 * Содержит коллекцию значений дополнительных измерений, относящихся к
	 * дебетовому счету. Примечание: Только для регистра с поддержкой
	 * корреспонденции. Только для регистра с поддержкой корреспонденции
	 * 
	 * @return OCAccountingRegisterExtDimensions
	 * @throws JIException
	 */
	public OCAccountingRegisterExtDimensions getExtDimensionsDr() throws JIException {
		return new OCAccountingRegisterExtDimensions(get("ExtDimensionsDr"));
	}
	
	/**
	 * Содержит коллекцию значений дополнительных измерений, относящихся к кредитовому счету.
	 * Только для регистра с поддержкой корреспонденции
	 * @return OCAccountingRegisterExtDimensions
	 * @throws JIException
	 */
	public OCAccountingRegisterExtDimensions getExtDimensionsCr() throws JIException {
		return new OCAccountingRegisterExtDimensions(get("ExtDimensionsCr"));
	}
	
	/**
	 * Содержит ссылку на счет записи. Только для регистра без поддержки
	 * корреспонденции.
	 * 
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getAccount() throws JIException {
		return new OCChartOfAccountsRef(get("Account"));
	}
	
	/**
	 * Содержит ссылку на дебетовый счет записи. Примечание: Только для регистра
	 * с поддержкой корреспонденции.
	 * 
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getAccountDr() throws JIException {
		return new OCChartOfAccountsRef(get("AccountDr"));
	}
	
	/**
	 * Содержит ссылку на кредитовый счет записи. Примечание: Только для
	 * регистра с поддержкой корреспонденции.
	 * 
	 * @return OCChartOfAccountsRef
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getAccountCr() throws JIException {
		return new OCChartOfAccountsRef(get("AccountCr"));
	}
	
}
