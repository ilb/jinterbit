package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.varset.OCAttributeUse;

public class OCTabularSectionMetadataObject extends _OCCommonMetadataObject {

	public OCTabularSectionMetadataObject(OCObject object) {
		super(object);
	}

	protected OCTabularSectionMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCTabularSectionMetadataObject(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	/**
	 * ИспользованиеРеквизита, Булево. Для регламентных заданий установка этого
	 * свойства в значение Истина указывает, что регламентное задание будет
	 * выполняться автоматически согласно расписанию. В противном случае
	 * автоматическое выполнения регламентного задания не будет. Для реквизитов
	 * справочников, планов видов характеристик, для табличных частей
	 * справочников и планов видов характеристик это свойство указывает способ
	 * использования даных подчиненного объекта конфигурации (например,
	 * ДляЭлемента, ДляГруппы, ДляГруппыИЭлемента).
	 * 
	 * @return OCAttributeUse
	 * @throws JIException
	 */
	public OCAttributeUse getUse() throws JIException {
		return new OCAttributeUse(get("Use"));
	}

	/**
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта
	 * метаданных.
	 * 
	 * @return OCMetadataAttributeCollection
	 * @throws JIException
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException {
		return new OCMetadataAttributeCollection(get("Attributes"));
	}
}
