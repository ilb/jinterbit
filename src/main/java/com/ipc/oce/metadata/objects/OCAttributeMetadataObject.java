package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.varset.OCAttributeUse;
import com.ipc.oce.varset.OCIndexing;


/**
 * Используется для обращения к метаданным реквизита объекта конфигурации. 
 * @author Konovalov
 *
 */
public class OCAttributeMetadataObject extends _OCCommonMetadataObject {
	private OCTypeDescription typeDescription = null;
	
	public OCAttributeMetadataObject(OCObject object) {
		super(object);
	}

	public OCAttributeMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAttributeMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public OCTypeDescription getTypeDescription() throws JIException{
		if (typeDescription == null) {
			typeDescription = new OCTypeDescription(get("Type"));
		}
		return typeDescription;
	}
	/**
	 * Returns only first type if TypeDescription contains more then one type.
	 * @return
	 * @throws JIException
	 */
	public OCType getType() throws JIException{
		return getTypeDescription().getType();
	}
	
	/**
	 * Вид дополнительных индексов, создаваемых для работы с данными объекта метаданных. 
	 * Для всех реквизитов, кроме реквизитов, описывающих отчеты, обработки и табличные части отчетов и обработок. 
	 * @return
	 * @throws JIException
	 */
	public OCIndexing getIndexing() throws JIException{
		return new OCIndexing(get("Indexing"));
	}
	
	/**
	 * Тип: ИспользованиеРеквизита, Булево. Для регламентных заданий установка этого свойства в значение Истина указывает, что регламентное задание будет выполняться автоматически согласно расписанию. В противном случае автоматическое выполнения регламентного задания не будет.
	 * Для реквизитов справочников, планов видов характеристик, для табличных частей справочников и планов видов характеристик это свойство указывает способ использования даных подчиненного объекта конфигурации (например, ДляЭлемента, ДляГруппы, ДляГруппыИЭлемента). 
	 * Примечание:
	 * Для реквизитов справочников или планов видов характеристик. 
	 * @return
	 * @throws JIException
	 */
	public OCAttributeUse getUse() throws JIException{
		return new OCAttributeUse(get("Use"));
	}
	
	/**
	 *  Устанавливает связь реквизитов регистра расчета с измерениями регистра сведений, который используется для хранения данных графика. 
	 * @return
	 * @throws JIException
	 */
	public OCDimensionMetadataObject getScheduleLink() throws JIException{
		return new OCDimensionMetadataObject(get("ScheduleLink"));
	}
}
