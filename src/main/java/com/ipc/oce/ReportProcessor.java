/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.objects.reports.CommonTemplate;
import com.ipc.oce.objects.reports.OCDataCompositionDetailsData;
import com.ipc.oce.objects.reports.OCDataCompositionProcessor;
import com.ipc.oce.objects.reports.OCDataCompositionResultSpreadsheetDocumentOutputProcessor;
import com.ipc.oce.objects.reports.OCDataCompositionTemplateComposer;
import com.ipc.oce.objects.reports.OCReportCollection;
import com.ipc.oce.objects.reports.OCReportManager;
import com.ipc.oce.objects.reports.OCSpreadsheetDocument;
import com.ipc.oce.objects.reports.OCTextDocument;

/**
 * @author Konovalov
 *
 */
public class ReportProcessor {
	private final OCApp app;
	
	protected ReportProcessor(final OCApp appInstance) {
		app = appInstance;
	}
	
	public CommonTemplate getCommonTemplate(String templateName) throws JIException {
		JIVariant var = app.callMethodA("GetCommonTemplate", new JIVariant(templateName))[0];
		CommonTemplate ct = null;
		try {
			OCSpreadsheetDocument sd = new OCSpreadsheetDocument(var);
			sd.getHeader();
			ct = sd;
		} catch (JIException e) {
			try {
				OCTextDocument td = new OCTextDocument(var);
				td.getLineSeparator();
				ct = td;
			} catch (JIException e1) {
				ct = new CommonTemplate(var);
			}
		} 
		return ct;
	}
	
	public OCReportManager getReport(String reportName) throws JIException{
		OCReportCollection collection = new OCReportCollection(app.get("Reports"));
		OCReportManager manager = collection.getReport(reportName);
		return manager;
	}
	
	/**
	 * Создает пустую конструкцию DataCompositionDetailsData.
	 * @return OCDataCompositionDetailsData
	 * @throws JIException
	 */
	public OCDataCompositionDetailsData createDetailsData() throws JIException {
		return new OCDataCompositionDetailsData(app.newObject("DataCompositionDetailsData"));
	}
	
	/**
	 * Создает новый DataCompositionTemplateComposer.
	 * @return OCDataCompositionTemplateComposer
	 * @throws JIException
	 */
	public OCDataCompositionTemplateComposer createTemplateComposer() throws JIException {
		return new OCDataCompositionTemplateComposer(app.newObject("DataCompositionTemplateComposer"));
	}
	
	/**
	 * Создание нового DataCompositionProcessor.
	 * @return OCDataCompositionProcessor
	 * @throws JIException
	 */
	public OCDataCompositionProcessor createCompositionProcessor() throws JIException {
		return new OCDataCompositionProcessor(app.newObject("DataCompositionProcessor"));
	}
	
	/**
	 * Создание нового OCSpreadsheetDocument.
	 * @return OCSpreadsheetDocument
	 * @throws JIException
	 */
	public final OCSpreadsheetDocument createSpreadsheetDocument() throws JIException {
		return new OCSpreadsheetDocument(app.newObject("SpreadsheetDocument"));
	}
	
	/**
	 * Создание нового OCDataCompositionResultSpreadsheetDocumentOutputProcessor.
	 * @return OCDataCompositionResultSpreadsheetDocumentOutputProcessor
	 * @throws JIException 
	 */
	public final OCDataCompositionResultSpreadsheetDocumentOutputProcessor createDataCompositionResultSpreadsheetDocumentOutputProcessor() throws JIException {
		return new OCDataCompositionResultSpreadsheetDocumentOutputProcessor(app.newObject("DataCompositionResultSpreadsheetDocumentOutputProcessor"));
	}
}
