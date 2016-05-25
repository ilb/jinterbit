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
import com.ipc.oce.metadata.collection._OCMetadataObjectPropertyValueCollection;
import com.ipc.oce.varset.OCBusinessProcessNumberType;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;
import com.ipc.oce.varset.OCDocumentNumberPeriodicity;
import com.ipc.oce.varset.OCPosting;
import com.ipc.oce.varset.OCRealTimePosting;


public class OCDocumentMetadataObject extends _OCCommonMetadataObject implements AttributeMetadataHolder {
	public static String metadataObjectName = "Документ"; 
	
	private OCMetadataAttributeCollection attributeCollection = null;
	private OCMetadataTabularSectionCollection tabularCollection = null;
	
	private List<String> attributesName = null;
	
	private List<String> tabularSectionsName = null;
	//=============================================================
	

	public OCDocumentMetadataObject(OCObject object) {
		super(object);
	}

	public OCDocumentMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получения списка имен атрибутов.
	 * @return List<String>
	 * @throws JIException
	 */
	public synchronized List<String> getAttributesName() throws JIException {
		if (attributesName == null) {
			OCMetadataAttributeCollection attrCollection = getAttributes();
			attributesName = new ArrayList<String>();
			for (OCAttributeMetadataObject attr : attrCollection) {
				attributesName.add(attr.getName());
			}
		}
		return attributesName;
	}
	
	/**
	 * Если свойство установлено в значение Истина, то новому объекту базы
	 * данных система будет автоматически присваивать очередной порядковый
	 * номер/код.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Boolean isAutoNumbering() throws JIException {
		return get("AutoNumbering").getObjectAsBoolean();
	}

	/**
	 * Максимальная длина номера объекта базы данных (например, документа,
	 * бизнес-процесса).
	 * 
	 * @return
	 * @throws JIException
	 */
	public int getNumberLength() throws JIException {
		return get("NumberLength").getObjectAsInt();
	}

	/**
	 * ОбъектМетаданных: Нумератор.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCDocumentNumeratorMetadataObject getNumerator() throws JIException {
		try {
			return new OCDocumentNumeratorMetadataObject(get("Numerator"));
		} catch (IllegalStateException ise) {
			return null;
		}
	}
	
	/**
	 * Если это свойство установлено в значение Разрешить, то оперативное
	 * проведение документа разрешено. В противном случае оперативное проведение
	 * документа запрещено
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCRealTimePosting getRealTimePosting() throws JIException {
		return new OCRealTimePosting(get("RealTimePosting"));
	}

	/**
	 * Устанавливает пределы контроля уникальности номеров и период
	 * повторяемости номеров (например, Год, Квартал, Месяц, День).
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCDocumentNumberPeriodicity getNumberPeriodicity()
			throws JIException {
		return new OCDocumentNumberPeriodicity(get("NumberPeriodicity"));
	}
	
	/**
	 * Определяет, разрешено ли проведение документа при записи
	 * @return
	 * @throws JIException
	 */
	public OCPosting getPosting() throws JIException {
		return new OCPosting(get("Posting"));
	}
	
	/**
	 * Определяет режим управления блокировкой данных объекта конфигурации
	 * (например, Автоматический, Управляемый, АвтоматическийИУправляемый).
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCDefaultDataLockControlMode getDataLockControlMode() throws JIException {
		return new OCDefaultDataLockControlMode(get("DataLockControlMode"));
	}
	
	/**
	 * Коллекция объектов метаданных, 
	 * описывающих реквизиты данного объекта метаданных. 
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException {
		if (attributeCollection == null) {
			attributeCollection = new OCMetadataAttributeCollection(get("Attributes"));
		}
		return attributeCollection;
	}
	
	/**
	 * Коллекция объектов метаданных, 
	 * описывающих табличные части данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataTabularSectionCollection getTabularSections() throws JIException {
		if (tabularCollection == null) {
			tabularCollection = new OCMetadataTabularSectionCollection(get("TabularSections"));
		}
		return tabularCollection;
	}
	
	/**
	 * Получение списка имен табличных частей.
	 * @return List<String>
	 * @throws JIException
	 */
	public synchronized List<String> getTabularSectionsName() throws JIException {
		if (tabularSectionsName == null) {
			OCMetadataTabularSectionCollection tabCollection = getTabularSections();
			tabularSectionsName = new ArrayList<String>();
			for (OCTabularSectionMetadataObject tab : tabCollection) {
				tabularSectionsName.add(tab.getName());
			}
		}
		return tabularSectionsName;
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то при создании нового объекта базы данных будет выполняться автоматический контроль уникальности его кода/номера. 
	 * @return
	 * @throws JIException
	 */
	public Boolean checkUnique() throws JIException {
		return get("CheckUnique").getObjectAsBoolean();
	}
	
	/**
	 * Определяет тип номера бизнес-процесса (например, Число, Строка). 
	 * @return
	 * @throws JIException
	 */
	public OCBusinessProcessNumberType getNumberType() throws JIException {
		return new OCBusinessProcessNumberType(get("NumberType"));
	}
	
	/**
	 * 
	 * @return
	 * @throws JIException
	 */
	public _OCMetadataObjectPropertyValueCollection getBasedOn() throws JIException {
		return new _OCMetadataObjectPropertyValueCollection(get("BasedOn"));
	}
	
	@Override
	public String toString() {
		try {
			return getName();
		} catch (JIException e) {
			return "";
		}
	}
	

}
