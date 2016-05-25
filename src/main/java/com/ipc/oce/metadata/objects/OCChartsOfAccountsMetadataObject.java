package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.AttributeMetadataHolder;
import com.ipc.oce.metadata.collection.OCMetadataAccountingFlagCollection;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataExtDimensionAccountingFlagCollection;
import com.ipc.oce.metadata.collection.OCMetadataTabularSectionCollection;
import com.ipc.oce.metadata.collection._OCMetadataObjectPropertyValueCollection;


/**
 * Используется для обращения к метаданным объекта конфигурации - план счетов. 
 * @author Konovalov
 *
 */
public class OCChartsOfAccountsMetadataObject extends _OCCommonMetadataObject implements AttributeMetadataHolder{
	private OCMetadataAttributeCollection attributeCollection = null;
	private OCMetadataTabularSectionCollection tabularCollection = null;
	
	public OCChartsOfAccountsMetadataObject(OCObject object) {
		super(object);
	}

	public OCChartsOfAccountsMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartsOfAccountsMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта метаданных. 
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException{
		if (attributeCollection == null) {
			attributeCollection = new OCMetadataAttributeCollection(get("Attributes"));
		}
		return attributeCollection;
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих табличные части данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataTabularSectionCollection getTabularSections() throws JIException{
		if (tabularCollection == null) {
			tabularCollection = new OCMetadataTabularSectionCollection(get("TabularSections"));
		}
		return tabularCollection;
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих признаки учета плана счетов. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAccountingFlagCollection getAccountingFlags() throws JIException{
		return new OCMetadataAccountingFlagCollection(get("AccountingFlags"));
	}
	
	/**
	 * Если свойство установлено в значение Истина, то упорядочивание по полю Код будет фактически подменяться упорядочиванием по полю Порядок. Другими словами, упорядочивание по полю Код и по полю Порядок будут давать одинаковый результат. При этом по умолчанию список счетов будет упорядочиваться по полю Код.
	 * Если свойство установлено в значение Ложь, то упорядочивание по полю Код будет фактическим, а по умолчанию список счетов будет упорядочиваться (по полю Код или по полю Порядок) в зависимости от значения свойства ДлинаПорядка. 
	 * @return
	 * @throws JIException 
	 */
	public Boolean getAutoOrderByCode() throws JIException{
		return get("AutoOrderByCode").getObjectAsBoolean();
	}
	
	/**
	 *  Коллекция объектов конфигурации, данные которых могут являться основанием для ввода нового объекта базы данных этого типа. 
	 * @return
	 * @throws JIException
	 */
	public _OCMetadataObjectPropertyValueCollection getBasedOn() throws JIException{
		// TODO че-то непонятно как эту колекцию обходить
		return new _OCMetadataObjectPropertyValueCollection(get("BasedOn"));
	}
	
	/**
	 * Максимальная длина кода объекта базы данных
	 * @return
	 * @throws JIException
	 */
	public Integer getCodeLength() throws JIException{
		return get("CodeLength").getObjectAsInt();
	}
	
	/**
	 * Максимальная длина наименования объекта базы данных 
	 * @return
	 * @throws JIException
	 */
	public Integer getDescriptionLength() throws JIException{
		return get("DescriptionLength").getObjectAsInt();
	}
	
	/**
	 *  Максимальная длина поля Порядок плана счетов. Если значение этого свойства равно 0, то по умолчанию список счетов упорядочивается по полю Код. В противном случае - по полю Порядок. 
	 * @return
	 * @throws JIException
	 */
	public Integer getOrderLength() throws JIException{
		return get("OrderLength").getObjectAsInt();
	}
	
	/**
	 *  Если это свойство установлено в значение Истина, то при создании нового объекта базы данных будет выполняться автоматический контроль уникальности его кода/номера. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isCheckUnique() throws JIException{
		return get("CheckUnique").getObjectAsBoolean();
	}
	
	/**
	 * Определяет максимальное количество субконто, которые могут быть у одного счета. 
	 * @return
	 * @throws JIException
	 */
	public Integer getMaxExtDimensionCount() throws JIException{
		return get("MaxExtDimensionCount").getObjectAsInt();
	}
	
	/**
	 * Строка, описывающая структуру кода счетов и субсчетов. 
	 * @return
	 * @throws JIException
	 */
	public String getCodeMask() throws JIException{
		return get("CodeMask").getObjectAsString2();
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих признаки учета субконто плана счетов. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataExtDimensionAccountingFlagCollection getExtDimensionAccountingFlags() throws JIException{
		return new OCMetadataExtDimensionAccountingFlagCollection(
				get("ExtDimensionAccountingFlags"));
	}
	
}
