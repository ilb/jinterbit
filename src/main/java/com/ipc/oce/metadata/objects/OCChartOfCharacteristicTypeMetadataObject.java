package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.AttributeMetadataHolder;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.metadata.collection.OCMetadataTabularSectionCollection;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;


/**
 * Используется для обращения к метаданным объекта конфигурации - план видов характеристик. 
 * @author Konovalov
 *
 */
public class OCChartOfCharacteristicTypeMetadataObject extends _OCCommonMetadataObject implements AttributeMetadataHolder{

	public OCChartOfCharacteristicTypeMetadataObject(OCObject object) {
		super(object);
	}

	public OCChartOfCharacteristicTypeMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartOfCharacteristicTypeMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Если свойство установлено в значение Истина, то новому объекту базы данных система будет автоматически присваивать очередной порядковый номер/код.
	 * @return
	 * @throws JIException
	 */
	public Boolean isAutoNumbering() throws JIException{
		return get("AutoNumbering").getObjectAsBoolean();
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то при отображении справочника или плана видов характеристик в виде иерархического списка, группы будут находиться в верхних строках списка, а элементы будут распологаться ниже.
	 * Если это свойство установлено в значение Ложь, то расположение групп и элементов будет подчиняться установленным правилам сортировки. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isFoldersOnTop() throws JIException{
		return get("FoldersOnTop").getObjectAsBoolean();
	}
	
	/**
	 * Максимальная длина кода объекта базы данных (например, справочника, счета). 
	 * @return
	 * @throws JIException
	 */
	public Integer getCodeLength() throws JIException{
		return get("CodeLength").getObjectAsInt();
	}
	
	/**
	 * Максимальная длина наименования объекта базы данных (например, справочника, задачи). 
	 * @return
	 * @throws JIException
	 */
	public Integer getDescriptionLength() throws JIException{
		return get("DescriptionLength").getObjectAsInt();
	}
	
	/**
	 * Дополнительные значения характеристик
	 * @return
	 * @throws JIException
	 */
	public OCCatalogMetadataObject getCharacteristicExtValues() throws JIException{
		return new OCCatalogMetadataObject(get("CharacteristicExtValues"));
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то справочник или план видов характеристик, имеет иерархическую структуру. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isHierarchical() throws JIException{
		return get("Hierarchical").getObjectAsBoolean();
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то при создании нового объекта базы данных будет выполняться автоматический контроль уникальности его кода/номера. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isCheckUnique() throws JIException{
		return get("CheckUnique").getObjectAsBoolean();
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым относится данный объект метаданных.
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException{
		return new OCMetadataSubsystemCollection(get("Subsystems"));
	}
	
	/**
	 * Определяет режим управления блокировкой данных объекта конфигурации (например, Автоматический, Управляемый, АвтоматическийИУправляемый). 
	 * @return
	 * @throws JIException
	 */
	public OCDefaultDataLockControlMode getDataLockControlMode() throws JIException{
		return new OCDefaultDataLockControlMode(get("DataLockControlMode"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта метаданных.
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException{
		return new OCMetadataAttributeCollection(get("Attributes"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих табличные части данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataTabularSectionCollection getTabularSections() throws JIException{
		return new OCMetadataTabularSectionCollection(get("TabularSections"));
	}
	
	/**
	 * Тип данных объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCTypeDescription getType() throws JIException{
		return new OCTypeDescription(get("Type"));
	}
}
