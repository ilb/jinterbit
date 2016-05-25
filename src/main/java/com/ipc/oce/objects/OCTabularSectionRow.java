package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;

/**
 * Используется для доступа к свойствам и методам строки табличной части.
 * Имя строки табличной части формируется следующим образом:
 * <Префикс полного имени объекта>ТабличнаяЧастьСтрока.<Имя прикладного объекта>.<Имя табличной части>.
 * Например: СправочникТабличнаяЧастьСтрока.Номенклатура.Состав, где "Номенклатура" - имя справочника, как оно задано в конфигураторе, "Состав" - имя табличной части справочника "Номенклатура". 
 * @author Konovalov
 *
 */
public class OCTabularSectionRow extends OCObject {

	public OCTabularSectionRow(OCObject object) {
		super(object);
	}

	public OCTabularSectionRow(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCTabularSectionRow(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	protected IJIDispatch dispatch(){
		return super.dispatch();
	}
	
	/**
	 * Номер строки табличной части. 
	 * @return Integer
	 * @throws JIException
	 */
	public Integer getLineNumber() throws JIException{
		return super.get("LineNumber").getObjectAsInt();
	}
	
	/**
	 * Набор свойств содержит значения колонок строки табличной части. Доступ к значению осуществляется по имени колонки. Имена свойств совпадают с именами колонок табличной части. 
	 * @param columnName
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getColumnValue(String columnName) throws JIException{
		return new OCVariant(get(columnName));
	}
	
	public void setColumnValue(String columnName, OCVariant variant) throws JIException{
		put(columnName, variant);
	}
}
