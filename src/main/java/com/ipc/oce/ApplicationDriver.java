/**
 * 
 */
package com.ipc.oce;

import java.util.Arrays;

import javax.xml.namespace.NamespaceContext;

import com.ipc.oce.v80.ApplicationDriverV80;
import com.ipc.oce.v81.ApplicationDriverV81;
import com.ipc.oce.v82.ApplicationDriverV82;
import com.ipc.oce.v83.ApplicationDriverV83;


/**
 * @author Konovalov
 *
 */
public abstract class ApplicationDriver {
	
	/**
	 * Массив имен доступных драйверов.
	 */
	public static final String[] AVAILABLE_DRIVERS;
	static {
		AVAILABLE_DRIVERS = new String[] {
			"V80Driver",
			"V81Driver",
			"V82Driver",
                        "V83Driver"
		};
	}
	
	/**
	 * Парсер синонимов 1С.
	 */
	private TypeSynonymParser typeSynonymParser = null;
	
	/**
	 * Фабрика статичных объектов (varsets).
	 */
	private VariantFactory variantGUIDTable = null;
	
	/**
	 * Контекст пространства имен xml 1С.
	 */
	private NamespaceContext namespaceContext = null;
	
	/**
	 * Версия драйвера.
	 */
	protected String driverVersion = null;
	
	/**
	 * Идентификатор объекта DCOM (имя или CLSID).
	 */
	protected String cApplicationId = null;
	
	/**
	 * Указывает на то, что находится в cApplicationId (имя или CLSID).
	 */
	protected Boolean cApplIdIsClsId = true;
	
	/**
	 * Параметр выполнения авторегистрации в DCOM.
	 */
	protected Boolean autoRegistration = false;
	
	/**
	 * 
	 */
	protected ApplicationDriver() {
		super();
	}
	
	/**
	 * Проверка первичных настроек драйвера.
	 * 
	 * @return true - все необходимые настройки установлены, false - иначе.
	 */
	private boolean isAllSet() {
		boolean res = true;
		if (typeSynonymParser == null) {
			res = false;
		}
		if (variantGUIDTable == null) {
			res = false;
		}
		if (cApplicationId == null && cApplIdIsClsId == null) {
			res = false;
		}
		return res;
	}
	
	/**
	 * Получение парсера синонимов 1С.
	 * @return TypeSynonymParser
	 */
	protected final TypeSynonymParser getTypeSynonymParser() {
		return typeSynonymParser;
	}
	
	/**
	 * Установка парсера синонимов 1С.
	 * 
	 * @param typeSynonymParser
	 *            - конкретная реализация парсера. Определяется относительно
	 *            версии 1С.
	 */
	protected final void setTypeSynonymParser(TypeSynonymParser typeSynonymParser) {
		this.typeSynonymParser = typeSynonymParser;
	}
	
	protected final VariantFactory getVariantGUIDTable() {
		return variantGUIDTable;
	}
	
	protected final void setVariantGUIDTable(VariantFactory variantGUIDTable) {
		this.variantGUIDTable = variantGUIDTable;
	}

	public final String getDriverVersion() {
		return driverVersion;
	}

	protected final String getcApplicationId() {
		return cApplicationId;
	}

	protected final Boolean getcApplIdIsClsId() {
		return cApplIdIsClsId;
	}

	protected final Boolean getAutoRegistration() {
		return autoRegistration;
	}

	public final void setAutoRegistration(Boolean autoRegistration) {
		this.autoRegistration = autoRegistration;
	}
	
	
	
	protected NamespaceContext getNamespaceContext() {
		return namespaceContext;
	}

	protected void setNamespaceContext(NamespaceContext namespaceContext) {
		this.namespaceContext = namespaceContext;
	}
	
	/**
	 * Получение экземпляра ApplicationDriver указаной версии (имени).
	 * 
	 * @param driverName - имя загружаемого драйвера.
	 * @return ApplicationDriver или null, если имя было null (это фактически
	 *         означает использование default-драйвера)
	 */
	public static ApplicationDriver loadDriver(final String driverName) {
		ApplicationDriver driver = null;
		if (driverName == null) {
			return null;
		}
		if (driverName.equals("V81Driver")) {
			driver = new ApplicationDriverV81();
		} else
		if (driverName.equals("V80Driver")) {
			driver = new ApplicationDriverV80();
		} else
		if (driverName.equals("V82Driver")) {
			driver = new ApplicationDriverV82();
		} else 
		if (driverName.equals("V83Driver")) {
			driver = new ApplicationDriverV83();
		} else {
			throw new RuntimeException("Driver with name '" + driverName
					+ "' not found. Choose: "
					+ Arrays.toString(AVAILABLE_DRIVERS));
		}
		
		if (driver.isAllSet()) {
			return driver;
		} else {
			throw new RuntimeException("Driver " + driverName + "["
					+ driver.getDriverVersion() + "] not configured");
		}
	}
}
