package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCTypeDescription;


/**
 * Используется для обращения к метаданным объекта конфигурации - измерение. 
 * @author Konovalov
 *
 */
public class OCDimensionMetadataObject extends _OCCommonMetadataObject {

	public OCDimensionMetadataObject(OCObject object) {
		super(object);
	}

	public OCDimensionMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDimensionMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Если свойство установлено в значение Истина, то предполагается, что получение базы будет выполняться с учетом значений данного измерения регистра расчета. При этом система выполнит дополнительное индексирование таблицы регистра расчета для оптимизации получения базы по двум и более измерениями.
	 * Если свойство установлено в значение Ложь, то дополнительное индексирование таблицы регистра расчета выполняться не будет. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isBaseDimension() throws JIException{
		return get("BaseDimension").getObjectAsBoolean();
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то для измерения регистра бухгалтерии означает, что по каждому значению данного измерения будет вестись отдельный баланс. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isBalance() throws JIException{
		return get("Balance").getObjectAsBoolean();
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то записи регистра сведений, содержащие в данном измерении ссылки на объекты базы данных, будут существовать до тех пор, пока существуют данные объекты базы данных. При удалении объекта базы данных будет удалены все записи регистра сведений, содержащие в этом измерении ссылку на данный объект. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isMaster() throws JIException{
		return get("Master").getObjectAsBoolean();
	}
	
	/**
	 *  Если это свойство установлено в значение Истина, то запись движений регистра с пустым значением этого измерения будет невозможна. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isDenyIncompleteValues() throws JIException{
		return get("DenyIncompleteValues").getObjectAsBoolean();
	}
	/**
	 * Используется для измерений регистра бухгалтерии, регистра накопления, регистра расчета, регистра сведений, последовательности. 
	 * Тип данных объекта метаданных
	 */
	public OCTypeDescription getTypeDescription() throws JIException{
		return new OCTypeDescription(get("Type"));
	}
	
	/**
	 * Объект метаданных, описывающий измерение регистра расчета. На это измерение будет установлен отбор при анализе существования зависимых записей, которые необходимо перерасчитать. 
	 * @return
	 * @throws JIException
	 */
	public OCCalculationRegisterMetadataObject getRegisterDimension() throws JIException{
		return new OCCalculationRegisterMetadataObject(get("RegisterDimension"));
	}
}
