/**
 * 
 */
package com.ipc.oce.junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.jinterop.dcom.common.JIException;
import org.junit.Ignore;
import org.junit.Test;

import com.ipc.oce.OCStructure;
import com.ipc.oce.OCValueTable;
import com.ipc.oce.OCVariant;
import com.ipc.oce.ReportProcessor;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCCatalogRef;
import com.ipc.oce.objects.OCCatalogSelection;
import com.ipc.oce.objects.reports.CommonTemplate;
import com.ipc.oce.objects.reports.OCDataCompositionDetailsData;
import com.ipc.oce.objects.reports.OCDataCompositionProcessor;
import com.ipc.oce.objects.reports.OCDataCompositionResultSpreadsheetDocumentOutputProcessor;
import com.ipc.oce.objects.reports.OCDataCompositionSchema;
import com.ipc.oce.objects.reports.OCDataCompositionSchemaDataSet;
import com.ipc.oce.objects.reports.OCDataCompositionSchemaDataSetObject;
import com.ipc.oce.objects.reports.OCDataCompositionSchemaParameter;
import com.ipc.oce.objects.reports.OCDataCompositionSchemaParameters;
import com.ipc.oce.objects.reports.OCDataCompositionSettings;
import com.ipc.oce.objects.reports.OCDataCompositionTemplate;
import com.ipc.oce.objects.reports.OCDataCompositionTemplateComposer;
import com.ipc.oce.objects.reports.OCReportManager;
import com.ipc.oce.objects.reports.OCReportObject;
import com.ipc.oce.objects.reports.OCSpreadsheetDocument;
import com.ipc.oce.objects.reports.OCSpreadsheetDocumentHeaderFooter;
import com.ipc.oce.query.OCQuery;
import com.ipc.oce.query.OCQueryResult;

/**
 * @author Konovalov
 *
 */
public class JUReport01 extends BasicTest {

	@Ignore
	@Test
	public void generateReport01() throws JIException, ParseException {
		OCQuery query = app.newQuery();
		String queryText = "ВЫБРАТЬ РАЗРЕШЕННЫЕ "
	+ " 	СчетаРасходов.Счет КАК Счет "
	+ " ПОМЕСТИТЬ СчетаРасходов "
	+ " ИЗ "
	+ " 	(ВЫБРАТЬ "
	+ " 		ЗНАЧЕНИЕ(ПланСчетов.Хозрасчетный.СебестоимостьПродаж) КАК Счет "
	+ " 	 "
	+ " 	ОБЪЕДИНИТЬ "
	+ " 	 "
	+ " 	ВЫБРАТЬ "
	+ " 		ЗНАЧЕНИЕ(ПланСчетов.Хозрасчетный.Продажи_Акцизы) "
	+ " 	 "
	+ " 	ОБЪЕДИНИТЬ "
	+ " 	 "
	+ " 	ВЫБРАТЬ "
	+ " 		ЗНАЧЕНИЕ(ПланСчетов.Хозрасчетный.Продажи_ЭкспортныеПошлины) "
	+ " 	 "
	+ " 	ОБЪЕДИНИТЬ "
	+ " 	 "
	+ " 	ВЫБРАТЬ "
	+ " 		ЗНАЧЕНИЕ(ПланСчетов.Хозрасчетный.Продажи_РасходыНаПродажу) "
	+ " 	 "
	+ " 	ОБЪЕДИНИТЬ "
	+ " 	 "
	+ " 	ВЫБРАТЬ "
	+ " 		ЗНАЧЕНИЕ(ПланСчетов.Хозрасчетный.Продажи_УправленческиеРасходы)) КАК СчетаРасходов "
	+ "  "
	+ " ИНДЕКСИРОВАТЬ ПО "
	+ " 	Счет "
	+ " ; "
	+ " ВЫБРАТЬ РАЗРЕШЕННЫЕ "
	+ " 	\"\" КАК Знак, "
	+ " 	Расходы.СуммаОборотДт КАК Сумма, "
	+ " 	Расходы.Период КАК Период, "
	+ " 	Расходы.Счет КАК Счет, "
	+ " 	\"Доходы без НДС\" КАК Вид, "
	+ " 	Расходы.КорСчет, "
	+ " 	\"Дт\" КАК БухВидРесурса "
	+ " ПОМЕСТИТЬ Расходы "
	+ " ИЗ "
	+ " 	РегистрБухгалтерии.Хозрасчетный.Обороты( "
	+ " 			&НачалоПериода, "
	+ " 			&КонецПериода, "
	+ " 			Месяц, "
	+ " 			Счет В ИЕРАРХИИ "
	+ " 				(ВЫБРАТЬ "
	+ " 					СчетаРасходов.Счет "
	+ " 				ИЗ "
	+ " 					СчетаРасходов КАК СчетаРасходов), "
	+ " 			, "
	+ " 			Организация = &Организация, "
	+ " 			, "
	+ " 			) КАК Расходы "
	+ "  "
	+ " ИНДЕКСИРОВАТЬ ПО "
	+ " 	Период "
	+ " ; "
	+ " ВЫБРАТЬ РАЗРЕШЕННЫЕ "
	+ " 	\"\" КАК Знак, "
	+ " 	Доходы.СуммаОборотКт КАК Сумма, "
	+ " 	Доходы.Период КАК Период, "
	+ " 	Доходы.Счет КАК Счет, "
	+ " 	Доходы.КорСчет, "
	+ " 	\"Кт\" КАК БухВидРесурса "
	+ " ПОМЕСТИТЬ Доходы "
	+ " ИЗ "
	+ " 	РегистрБухгалтерии.Хозрасчетный.Обороты(&НачалоПериода, &КонецПериода, Месяц, Счет В ИЕРАРХИИ (ЗНАЧЕНИЕ(ПланСчетов.Хозрасчетный.Выручка)), , Организация = &Организация, , ) КАК Доходы "
	+ "  "
	+ " ОБЪЕДИНИТЬ ВСЕ "
	+ "  "
	+ " ВЫБРАТЬ "
	+ " 	\"-\", "
	+ " 	-НДС.СуммаОборотДт, "
	+ " 	НДС.Период, "
	+ " 	НДС.Счет, "
	+ " 	НДС.КорСчет, "
	+ " 	\"Дт\" "
	+ " ИЗ "
	+ " 	РегистрБухгалтерии.Хозрасчетный.Обороты(&НачалоПериода, &КонецПериода, Месяц, Счет В ИЕРАРХИИ (ЗНАЧЕНИЕ(ПланСчетов.Хозрасчетный.Продажи_НДС)), , Организация = &Организация, , ) КАК НДС "
	+ "  "
	+ " ИНДЕКСИРОВАТЬ ПО "
	+ " 	Период "
	+ " ; "
	+ " ВЫБРАТЬ РАЗРЕШЕННЫЕ "
	+ " 	Расходы.Знак, "
	+ " 	Расходы.Сумма, "
	+ " 	Расходы.Период, "
	+ " 	Расходы.Счет, "
	+ " 	\"Расходы\" КАК Вид, "
	+ " 	Расходы.КорСчет, "
	+ " 	Расходы.БухВидРесурса "
	+ " ИЗ "
	+ " 	Расходы КАК Расходы "
	+ "  "
	+ " ОБЪЕДИНИТЬ ВСЕ "
	+ "  "
	+ " ВЫБРАТЬ "
	+ " 	Доходы.Знак, "
	+ " 	Доходы.Сумма, "
	+ " 	Доходы.Период, "
	+ " 	Доходы.Счет, "
	+ " 	\"Доходы без НДС\", "
	+ " 	Доходы.КорСчет, "
	+ " 	Доходы.БухВидРесурса "
	+ " ИЗ "
	+ " 	Доходы КАК Доходы "
	+ "  "
	+ " ОБЪЕДИНИТЬ ВСЕ "
	+ "  "
	+ " ВЫБРАТЬ "
	+ " 	NULL, "
	+ " 	ВложенныйЗапрос.Сумма, "
	+ " 	ВложенныйЗапрос.Период, "
	+ " 	ВложенныйЗапрос.Счет, "
	+ " 	ВложенныйЗапрос.Вид, "
	+ " 	NULL, "
	+ " 	NULL "
	+ " ИЗ "
	+ " 	(ВЫБРАТЬ "
	+ " 		ЕСТЬNULL(ДоходыСвернуто.Сумма, 0) - ЕСТЬNULL(РасходыСвернуто.Сумма, 0) КАК Сумма, "
	+ " 		ВЫБОР "
	+ " 			КОГДА ДоходыСвернуто.Период ЕСТЬ NULL " 
	+ " 				ТОГДА РасходыСвернуто.Период "
	+ " 			ИНАЧЕ ДоходыСвернуто.Период "
	+ " 		КОНЕЦ КАК Период, "
	+ " 		ВЫБОР "
	+ " 			КОГДА ЕСТЬNULL(ДоходыСвернуто.Сумма, 0) - ЕСТЬNULL(РасходыСвернуто.Сумма, 0) >= 0 "
	+ " 				ТОГДА \"Прибыль\" "
	+ " 			ИНАЧЕ \"Убыток\" "
	+ " 		КОНЕЦ КАК Вид, "
	+ " 		NULL КАК Счет "
	+ " 	ИЗ "
	+ " 		(ВЫБРАТЬ "
	+ " 			СУММА(Доходы.Сумма) КАК Сумма, "
	+ " 			Доходы.Период КАК Период "
	+ " 		ИЗ "
	+ " 			Доходы КАК Доходы "
	+ " 		 "
	+ " 		СГРУППИРОВАТЬ ПО "
	+ " 			Доходы.Период) КАК ДоходыСвернуто "
	+ " 			ЛЕВОЕ СОЕДИНЕНИЕ (ВЫБРАТЬ "
	+ " 				СУММА(Расходы.Сумма) КАК Сумма, "
	+ " 				Расходы.Период КАК Период "
	+ " 			ИЗ "
	+ " 				Расходы КАК Расходы "
	+ " 			 "
	+ " 			СГРУППИРОВАТЬ ПО "
	+ " 				Расходы.Период) КАК РасходыСвернуто "
	+ " 			ПО ДоходыСвернуто.Период = РасходыСвернуто.Период) КАК ВложенныйЗапрос";
		query.setText(queryText);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		query.setParameter("НачалоПериода", new OCVariant(sdf.parse("2009-01-01")));
		query.setParameter("КонецПериода", new OCVariant(sdf.parse("2010-01-01")));
		
		OCCatalogManager catMan = app.getCatalogManager("Организации");
		OCCatalogSelection catSel = catMan.select();
		catSel.next();
		OCCatalogRef orgRef = catSel.getRef();
		
		query.setParameter("Организация", new OCVariant(orgRef));
		
		OCQueryResult result = query.execute();
		OCValueTable table = result.unload();
		System.out.println(table.listColumns());
		
		OCStructure structure = app.newStructure();
		structure.insert("ТаблицаДанных", table);
		
		ReportProcessor rProc = app.getReportProcessor();
		OCReportManager manager = rProc.getReport("ДоходыРасходы");
		
		
		OCReportObject report = manager.create();
		
		OCDataCompositionSchema dataCompositionSchema = report.getDataCompositionSchema();
		for (OCDataCompositionSchemaDataSet object : dataCompositionSchema.getDataSets()) {
			System.out.println(object);
			System.out.println(object.getClass().getName());
			if (object instanceof OCDataCompositionSchemaDataSetObject) {
				OCDataCompositionSchemaDataSetObject dsObject = (OCDataCompositionSchemaDataSetObject)object;
				System.out.println(dsObject.getObjectName());
			}
		}
		OCDataCompositionSettings defaultSettings = dataCompositionSchema.getDefaultSettings();
		
		OCDataCompositionDetailsData details = rProc.createDetailsData();
		
		OCDataCompositionTemplateComposer templateComposer = rProc.createTemplateComposer();
		
		OCDataCompositionTemplate template = templateComposer.execute(dataCompositionSchema, defaultSettings, details);
		
		OCDataCompositionProcessor processor = rProc.createCompositionProcessor();
		processor.initialize(template, structure, details, true);
		
		OCDataCompositionResultSpreadsheetDocumentOutputProcessor spreadSheetProcessor = rProc.createDataCompositionResultSpreadsheetDocumentOutputProcessor();
		OCSpreadsheetDocument spreadsheetDocument = rProc.createSpreadsheetDocument();
		spreadsheetDocument.clear();
		spreadSheetProcessor.setDocument(spreadsheetDocument);
		spreadsheetDocument = spreadSheetProcessor.output(processor);
		
		//==================
		OCSpreadsheetDocumentHeaderFooter footer = spreadsheetDocument.getHeader();
		footer.setEnabled(true);
		footer.setStartPage(0);
		footer.setCenterText("[&Дата] ([&Date])");
		System.out.println(footer.getVerticalAlign());
		System.out.println(footer.getCenterText());
		//==================
		spreadsheetDocument.writeTXT("C:\\Developer\\Temp\\testRTXT.txt");
	}
	
	@Ignore
	@Test
	public void generateReport02() throws JIException, ParseException {
		
		ReportProcessor rProc = app.getReportProcessor();
		OCReportManager manager = rProc.getReport("КарточкаУчетаПоСтраховымВзносам");
		
		OCReportObject report = manager.create();
		
		
		OCDataCompositionSchema dataCompositionSchema = report.getDataCompositionSchema();
		for (OCDataCompositionSchemaDataSet object : dataCompositionSchema.getDataSets()) {
			System.out.println("==========================" + object.toString());
			System.out.println(object.getClass().getName());
			if (object instanceof OCDataCompositionSchemaDataSetObject) {
				OCDataCompositionSchemaDataSetObject dsObject = (OCDataCompositionSchemaDataSetObject)object;
				System.out.println(dsObject.getObjectName());
			}
		}
		System.out.println("======= PARAMETERS =============");
		OCDataCompositionSchemaParameters parameters = dataCompositionSchema.getParameters();	
		for (OCDataCompositionSchemaParameter param : parameters) {
			System.out.println(param.toString());
		}
		
		System.out.println("================================");
		OCDataCompositionSettings defaultSettings = dataCompositionSchema.getDefaultSettings();
		
		System.out.println(defaultSettings.showXML());
		
		OCDataCompositionDetailsData details = rProc.createDetailsData();
		
		OCDataCompositionTemplateComposer templateComposer = rProc.createTemplateComposer();
		
		OCDataCompositionTemplate template = templateComposer.execute(dataCompositionSchema, defaultSettings, details);
		
		OCDataCompositionProcessor processor = rProc.createCompositionProcessor();
		processor.initialize(template, null, details, true);
		
		OCDataCompositionResultSpreadsheetDocumentOutputProcessor spreadSheetProcessor = rProc.createDataCompositionResultSpreadsheetDocumentOutputProcessor();
		OCSpreadsheetDocument spreadsheetDocument = rProc.createSpreadsheetDocument();
		spreadsheetDocument.clear();
		spreadSheetProcessor.setDocument(spreadsheetDocument);
		spreadsheetDocument = spreadSheetProcessor.output(processor);
		
		spreadsheetDocument.writeTXT("C:\\Developer\\Temp\\testRTXT01.txt");
	}
	
	@Test 
	public void commonTemplate() throws JIException {

		ReportProcessor rp = app.getReportProcessor();
		CommonTemplate ct = rp.getCommonTemplate("ЗаголовокОтчета");
		System.out.println(ct);
		System.out.println(ct.getClass().getName());
		
	}
}
