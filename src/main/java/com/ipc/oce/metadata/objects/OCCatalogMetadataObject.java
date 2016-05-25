package com.ipc.oce.metadata.objects;

import java.util.ArrayList;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.AttributeMetadataHolder;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataTabularSectionCollection;


public class OCCatalogMetadataObject extends _OCCommonMetadataObject implements AttributeMetadataHolder{
	public static final String META_OBJECT_NAME = "Справочник"; 
	private static List<String> mandatoryAttributes = null;
	private static List<String> keyFields = null;
	private OCMetadataTabularSectionCollection tabularCollection = null;
	private OCMetadataAttributeCollection attributeCollection = null;
	static{
		mandatoryAttributes = new ArrayList<String>(4);
		mandatoryAttributes.add("Code"); 			// int or string
		mandatoryAttributes.add("Description"); 	// string
		mandatoryAttributes.add("DeletionMark"); 	//boolean
		mandatoryAttributes.add("Predefined"); 		// boolean

		//mandatoryAttributes.add("Parent"); // TODO тут что-то надо придумать
		
		keyFields = new ArrayList<String>(1);
		keyFields.add("Code");
	}
	
	public static final List<String> getMandatoryAttributes() {
		return mandatoryAttributes;
	}

	public static final List<String> getKeyFields() {
		return keyFields;
	}

	public OCCatalogMetadataObject(OCObject object) {
		super(object);
	}

	public OCCatalogMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCatalogMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCMetadataAttributeCollection getAttributes() throws JIException{
		if (attributeCollection == null) {
			attributeCollection = new OCMetadataAttributeCollection(get("Attributes"));
		}
		return attributeCollection;
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
	 * Если это свойство установлено в значение Истина, то при создании нового объекта базы данных будет выполняться автоматический контроль уникальности его кода/номера. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isCheckUnique() throws JIException{
		return get("CheckUnique").getObjectAsBoolean();
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то максимальное количество уровней иерархии ограничено значением свойства КоличествоУровней. В противном случае максимальное количество уровней иерархии не ограничено. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isLimitLevelCount() throws JIException{
		return get("LimitLevelCount").getObjectAsBoolean();
	}
	
	/**
	 * Максимальное количество уровней иерархии справочника при условии, что свойство ОграничиватьКоличествоУровней имеет значение Истина. Если ОграничиватьКоличествоУровней имеет значение Ложь, то максимальное количество уровней иерархии неограничено. 
	 * @return
	 * @throws JIException
	 */
	public Integer getLevelCount() throws JIException{
		return get("LevelCount").getObjectAsInt();
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то справочник или план видов характеристик, имеет иерархическую структуру. 
	 * @return Boolean
	 * @throws JIException 
	 */
	public Boolean isHierarchical() throws JIException{
		return get("Hierarchical").getObjectAsBoolean();
	}
	
	public OCMetadataTabularSectionCollection getTabularSections() throws JIException{
		if (tabularCollection == null) {
			tabularCollection = new OCMetadataTabularSectionCollection(get("TabularSections"));
		}
		return tabularCollection;
	}
	
	public static String getKeyName() {
		if (!isComplexKey()) {
			return keyFields.get(0);
		} else {
			throw new IllegalStateException(
					META_OBJECT_NAME + " has complex key");
		}
	}

	public static boolean isComplexKey() {
		return (keyFields.size() == 1);
	}
	
	public static String[] getComplexKeyNames() {
		if (isComplexKey()) {
			return keyFields.toArray(new String[keyFields.size()]);
		} else {
			throw new IllegalStateException(
					META_OBJECT_NAME + " has simple key");
		} 
	}
}
