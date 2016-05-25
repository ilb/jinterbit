/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCReportMetadataObject;
import com.ipc.oce.objects.OCTabularSectionManager;

/**
 * Представляет собой объект отчета. Используется для доступа к реквизитам и
 * табличным частям, формам и макетам отчета.
 * 
 * @author Konovalov
 * 
 */
public class OCReportObject extends OCObject {

	protected OCReportObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	protected OCReportObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит реквизиты отчета. Доступ к значению осуществляется
	 * по имени, как оно задано в конфигураторе.
	 * 
	 * @param attributeName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException {
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Набор свойств содержит реквизиты отчета. Доступ к значению осуществляется
	 * по имени, как оно задано в конфигураторе
	 * 
	 * @param attributeName
	 * @param variant
	 * @throws JIException
	 */
	public void setAttributeValue(String attributeName, OCVariant variant) throws JIException {
		put(attributeName, variant);
	}
	
	/**
	 * Набор свойств содержит табличные части отчета. Доступ к табличной части
	 * осуществляется по имени, как оно задано в конфигураторе.
	 * 
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException {
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	/**
	 * Содержит данный объект отчета. Свойство предназначено для получения
	 * объекта в модуле объекта отчета или модуле формы.
	 * 
	 * @return
	 */
	public OCReportObject thisObject() {
		return this;
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных отчета. Другой путь
	 * получения того же значения - через свойство глобального контекста
	 * Метаданные. Например: Метаданные.Отчеты.Продажи.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCReportMetadataObject getMetadata() throws JIException {
		return new OCReportMetadataObject(callMethodA("Metadata"));
	}
	
	/**
	 * Получает макет отчета.
	 * @param templateName - Имя макета, как оно задано в конфигураторе
	 * @return ТабличныйДокумент, ТекстовыйДокумент, другой объект, который может быть макетом.. 
	 * @throws JIException
	 */
	public CommonTemplate getTemplate(String templateName) throws JIException {
		JIVariant var = callMethodA("GetTemplate", new JIVariant(templateName))[0];
		return new CommonTemplate(var);
	}
	
	/**
	 * Компoновщик для редактирования настроек отчета.
	 * 
	 * @return OCDataCompositionSettingsComposer
	 * @throws JIException
	 */
	public OCDataCompositionSettingsComposer getSettingsComposer() throws JIException {
		return new OCDataCompositionSettingsComposer(get("SettingsComposer"));
	}
	
	/**
	 * Схема компоновки, на основании которой будет выполняться отчет.
	 * @return OCDataCompositionSchema
	 * @throws JIException
	 */
	public OCDataCompositionSchema getDataCompositionSchema() throws JIException {
		return new OCDataCompositionSchema(get("СхемаКомпоновкиДанных"));
	}
	
	/**
	 * Схема компоновки, на основании которой будет выполняться отчет.
	 * @param schema - OCDataCompositionSchema
	 * @throws JIException
	 */
	public void setDataCompositionSchema(OCDataCompositionSchema schema) throws JIException {
		putRef("CompositionDataSchema", schema);
	}
	
	/**
	 * Выполняет компоновку.
	 * @param document - Табличный документ, в который будет выводиться результат. 
	 * @throws JIException
	 */
	public void composeResult(OCSpreadsheetDocument document, OCDataCompositionDetailsData details) throws JIException {
		callMethod("ComposeResult", new Object[]{
				new JIVariant(ocObject2Dispatch(document)),
				(details != null ? new JIVariant(ocObject2Dispatch(details)) : null)
		});
	}

}
