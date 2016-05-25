/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCValueList;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.varset.EComparisonType;

/**
 * Используется для управления параметрами отбора по одному элементу
 * отбора. Объект предназначен для установки условия отбора по: значению поля
 * списка; критерию отбора; графе журнала (для журнала документов); виду
 * документа (для журнала документов). Объект используется в качестве элемента
 * коллекции Отбор для установки фильтрации в различных выборках, наборах
 * записей и визуальных представлениях.
 * 
 * @author Konovalov
 * 
 */
public class OCFilterItem extends OCObject {

	/**
	 * @param object
	 */
	public OCFilterItem(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCFilterItem(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCFilterItem(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит способ сравнения, используемый при фильтрации по элементу отбора. 
	 * @return
	 * @throws JIException
	 */
	public EComparisonType getComparisonType() throws JIException {
		return new EComparisonType(get("ComparisonType"));
	}
	
	/**
	 * Установка способа сравнения, используемый при фильтрации по элементу отбора.
	 * @param type - способ сравнения.
	 * @throws JIException
	 */
	public void setComparisonType(EComparisonType type) throws JIException {
		put("ComparisonType", new JIVariant(ocObject2Dispatch(type)));
	}
	
	/**
	 * Содержит значение, по которому будет осуществляться
	 * фильтрация. Примечание: Если ВидСравнения принимает значение ВСписке,
	 * ВСпискеПоИерархии, НеВСписке или НеВСпискеПоИерархии, то тип значения
	 * СписокЗначений.
	 * 
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValue() throws JIException {
		return new OCVariant(get("Value"));
	}
	
	/**
	 * Установка значения, по которому будет осуществляться
	 * фильтрация. Примечание: Если ВидСравнения принимает значение ВСписке,
	 * ВСпискеПоИерархии, НеВСписке или НеВСпискеПоИерархии, то тип значения
	 * СписокЗначений.
	 * 
	 * @throws JIException
	 */
	public void setValue(OCVariant variant) throws JIException {
		put("Value", variant);
	}
	
	public void setValue(OCValueList list) throws JIException {
		put("Value", new JIVariant(ocObject2Dispatch(list)));
	}
	
	/**
	 * Содержит правое значение сравнения, если в качестве вида сравнения используется интервал. 
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValueTo() throws JIException {
		return new OCVariant(get("ValueTo"));
	}
	
	/**
	 * Устанавливает правое значение сравнения, если в качестве вида сравнения используется интервал. 
	 * @param variant
	 * @throws JIException
	 */
	public void setValueTo(OCVariant variant) throws JIException {
		put("ValueTo", variant);
	}
	
	/**
	 * Содержит левое значение сравнения, если в качестве вида сравнения используется интервал. 
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValueFrom() throws JIException {
		return new OCVariant(get("ValueFrom"));
	}
	
	/**
	 * Устанавливает левое значение сравнения, если в качестве вида сравнения используется интервал. 
	 * @param variant
	 * @throws JIException
	 */
	public void setValueFrom(OCVariant variant) throws JIException {
		put("ValueFrom", variant);
	}
	
	/**
	 * Содержит имя элемента отбора.
	 * @return String
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Содержит признак использования элемента отбора в фильтре. Если свойство
	 * имеет значение Истина, то будет выполняться фильтрация по данному
	 * элементу отбора, иначе элемент отбора не будет участвовать в фильтрации.
	 * 
	 * @return Boolean
	 * @throws JIException
	 */
	public Boolean isUse() throws JIException {
		return get("Use").getObjectAsBoolean();
	}
	
	/**
	 * Устанавливает признак использования элемента отбора в фильтре. Если
	 * свойство имеет значение Истина, то будет выполняться фильтрация по
	 * данному элементу отбора, иначе элемент отбора не будет участвовать в
	 * фильтрации.
	 * 
	 * @param признак
	 *            использования
	 * @throws JIException
	 */
	public void setUse(boolean use) throws JIException {
		put("Use", new JIVariant(use));
	}
	
	/**
	 * Пользовательское представление элемента отбора.
	 * @return
	 * @throws JIException
	 */
	public String getPresentation() throws JIException {
		return get("Presentation").getObjectAsString2();
	}
	
	/**
	 * Пользовательское представление элемента отбора.
	 * @param presentation
	 * @throws JIException
	 */
	public void setPresentation(String presentation) throws JIException {
		put("Presentation", new JIVariant(presentation));
	}
	
	/**
	 * Путь к данным для элемента отбора. Например: Номенклатура.ЕдиницаИзмерения.Код.
	 * @return String
	 * @throws JIException
	 */
	public String getDataPath() throws JIException {
		return get("DataPath").getObjectAsString2();
	}
	
	/**
	 * Тип значения для отбора.
	 * @return
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException {
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Устанавливает значение отбора и флаг использования. При выполнении
	 * данного метода вид сравнения устанавливается в значение Равно.
	 * Примечание: Метод имеет смысл применять, если необходимо установить и
	 * значение и использование отбора.
	 * 
	 * @param variant
	 *            - Значение сравнения.
	 * @param use
	 *            - Признак использования отбора по значению.
	 * @throws JIException 
	 */
	public void set(OCVariant variant, boolean use) throws JIException {
		callMethod("Set", new Object[]{ocVariant2JI(variant), new JIVariant(use)});
	}
}
