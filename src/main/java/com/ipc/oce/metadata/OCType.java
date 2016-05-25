package com.ipc.oce.metadata;

import java.sql.Types;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.TypeSynonymParser;


public class OCType extends OCObject {
	
	// статичные типы
	public static final int OCT_UNKNOWN = 0;
	public static final int OCT_BOOLEAN = 10;
	public static final int OCT_NUMBER = 20;
	public static final int OCT_DATE = 30;
	public static final int OCT_STRING = 40;
	
	
	// NULL is NULL
	public static final int OCT_NULL = 99;
	
	// динамические типы
	public static final int OCT_REF_CATALOG = 100;
	public static final int OCT_REF_DOCUMENT = 200;
	public static final int OCT_REF_CHART_OF_ACCOUNT = 250;
	public static final int OCT_REF_CHART_OF_CHARACTERISTIC = 251;
	public static final int OCT_REF_EXCHANGE_PLAN = 252;
	public static final int OCT_REF_ENUM = 300;
	
	public static final int OCT_QUERY_RESULT = 600;
	
	public static final int OCT_ARRAY = 701;
	public static final int OCT_STRUCTURE = 702;
	public static final int OCT_VALUETABLE = 703;
	public static final int OCT_VALUESTORAGE = 704;
	
	/**
	 *  ссылка неопознанного типа (в идеале к концу разработки отпадет как класс)
	 */
	public static final int OCT_REF_UNKNOWN = 900;
	
	
	/**
	 * первая часть синонима.
	 */
	private String linkType = null;
	
	/**
	 * вторая часть синонима.
	 */
	private String linkSinonym = null;
	
	/**
	 * синоним типа полностью.
	 */
	private String synonym = null;
	
	private String xmlTypeName = null;
	
	private TypeSynonymParser synonymParser = null;


	public OCType(OCObject object) throws JIException {
		super(object);
		setupSynonymParser();
	}
	
	public OCType(IJIDispatch aDispatch) throws JIException {
		super(aDispatch);
		setupSynonymParser();
	}
	
	public OCType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
		setupSynonymParser();
	}
	
	private void setupSynonymParser() {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		synonymParser = app.getTypeSynonymParser();
	}
	
	/**
	 * Получение типа данных в xml.
	 * @return 
	 * @throws JIException
	 */
	public String getXMLType() throws JIException {
		if (xmlTypeName == null) {
			OCApp app = OCApp.getInstance(getAssociatedSessionID());
			xmlTypeName = app.getXMLType(this).getTypeName();
		}
		return xmlTypeName;
	}
	
	public String getSynonym() throws JIException { // Булево, Строка, Ссылка туда-то... (Справочник ссылка: Банковские счета)
		if (synonym == null) {
			synonym = synonymParser.getSynonym(this);
		}
		return synonym;
	}
	
	public boolean isSimple() throws JIException {
		return synonymParser.isSimple(getSynonym());
	}
	
	public boolean isLink() throws JIException {
		return synonymParser.isLink(getSynonym());
	}
	
	/**
	 * Наименование ссылки
	 * @return
	 * @throws JIException
	 */
	public String getLinkSynonym() throws JIException {
		if (linkSinonym == null) {
			linkSinonym = synonymParser.getLinkSynonym(this);
		}
		return linkSinonym;
	}
	
	/**
	 * Наименование типа ссылки.
	 * @return String
	 * @throws JIException - ошибка DCOM.
	 */
	public String getLinkType() throws JIException {
		if (linkType == null) {
			linkType = synonymParser.getLinkType(this);
		}
		return linkType;
	}
	
	/**
	 * Перевод типов данных из 1С во внутренние коды.
	 * @return внутренний код
	 * @throws JIException
	 */
	public int getTypeCode() throws JIException {
		if (isSimple()) {
			String synonym = getSynonym();
			return nameToCode(synonym);
		} else {
			String linkT = getLinkType();
			return nameToCode(linkT);
		}
	}
	
	public static String typesToSQLTypeName(OCType[] types) throws JIException {
		String res = "OTHER";
		if (types.length == 1) {
			OCType t = types[0];
			int oceTypeCode = t.getTypeCode();
			if (oceTypeCode < OCT_REF_CATALOG) {
				switch(oceTypeCode) {
					case OCType.OCT_BOOLEAN: {
						res = "SMALLINT";
						break;
					}
					case OCType.OCT_DATE: {
						res = "TIMESTAMP";
						break;
					}
					case OCType.OCT_NULL: {
						res = "NULL";
						break;
					}
					case OCType.OCT_NUMBER: {
						res = "NUMERIC";
						break;
					}
					case OCType.OCT_STRING: {
						res = "VARCHAR";
						break;
					}
					case OCType.OCT_UNKNOWN: {
						res = "JAVA_OBJECT";
						break;
					}
					default:
						res = "JAVA_OBJECT";
				}
			} else {
				res = "JAVA_OBJECT";
			}
		}
		return res;
	}
	
	public static int typesToSQLType(OCType[] types) throws JIException {
		int res = Types.OTHER;
		if (types.length == 1) {
			OCType t = types[0];
			int oceTypeCode = t.getTypeCode();
			if (oceTypeCode < OCT_REF_CATALOG) {
				switch (oceTypeCode) {
					case OCType.OCT_BOOLEAN: {
						res = Types.SMALLINT; // fix in 0.4.0
						break;
					}
					case OCType.OCT_DATE: {
						res = Types.TIMESTAMP;
						break;
					}
					case OCType.OCT_NULL: {
						res = Types.NULL;
						break;
					}
					case OCType.OCT_NUMBER: {
						res = Types.NUMERIC;
						break;
					}
					case OCType.OCT_STRING: {
						res = Types.VARCHAR;
						break;
					}
					case OCType.OCT_UNKNOWN: {
						res = Types.JAVA_OBJECT;
						break;
					}
					default: {
						res = Types.JAVA_OBJECT;
					}
				}
			} else {
				res = Types.JAVA_OBJECT;
			}
		}
		return res;
	}
	
	/**
	 * Перевод строкового наименования типа из 1С в числовой код OCT.
	 * ВНИМАНИЕ: Это для метаданных. Используется например для Документ.РасчетЕСН
	 * @param name - имя типа 1С
	 * @return код OCT*
	 */
	public static int nameToCode(final String name) {
		
		int res = OCT_UNKNOWN;
		
		if (name.equals("Документ") || name.equals("Document")) {
			return OCT_REF_DOCUMENT;
		} else
		if (name.equals("Справочник") || name.equals("Catalog")) {
			return OCT_REF_CATALOG;
		} else
		if (name.equals("ПланСчетов")) {
			return OCT_REF_CHART_OF_ACCOUNT;
		} else
		if (name.equals("Перечисление") || name.equals("Enum")) {
			return OCT_REF_ENUM;
		} else
		if (name.equals("Результат запроса")) {
			return OCT_QUERY_RESULT;
		} else
		if (name.equals("Массив")) { // eng - OK
			return OCT_ARRAY;
		} else 
		if (name.equals("Структура")) { // eng - OK
			return OCT_STRUCTURE;
		} else
		if (name.equals("ТаблицаЗначений")) { // end - OK
			return OCT_VALUETABLE;
		} else
		if (name.equals("Булево") || name.equals("Boolean")) {
			return OCT_BOOLEAN;
		} else
		if (name.equals("Строка") || name.equals("String") || name.equals("string")) {
			return OCT_STRING;
		} else
		if (name.equals("Число") || name.equals("Number")) {
			return OCT_NUMBER;
		} else
		if (name.equals("Дата") || name.equals("Date") || name.equals("dateTime")) {
			return OCT_DATE;
		} else
		if (name.equals("ХранилищеЗначения")) {
			return OCT_VALUESTORAGE;
		} else
		if (name.equals("ПланВидовХарактеристик")) {
			return OCT_REF_CHART_OF_CHARACTERISTIC;
		} else
		if (name.equals("ПланОбмена")) {
			return OCT_REF_EXCHANGE_PLAN;
		} else
		if (name.equals("Null")) {
			return OCT_NULL;
		}
		
		return res;
	}
	
	@Override
	public String toString() {
		try {
			return getSynonym();
		} catch (JIException e) {
			e.printStackTrace();
		}
		return linkSinonym;
	}
	
}
