package com.ipc.oce.metadata;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataAccountingRegisterCollection;
import com.ipc.oce.metadata.collection.OCMetadataAccumulationRegisterCollection;
import com.ipc.oce.metadata.collection.OCMetadataCalculationRegisterCollection;
import com.ipc.oce.metadata.collection.OCMetadataCatalogCollection;
import com.ipc.oce.metadata.collection.OCMetadataChartOfCharacteristicTypeCollection;
import com.ipc.oce.metadata.collection.OCMetadataChartsOfAccountCollection;
import com.ipc.oce.metadata.collection.OCMetadataChartsOfCalculationTypeCollection;
import com.ipc.oce.metadata.collection.OCMetadataConstantCollection;
import com.ipc.oce.metadata.collection.OCMetadataDocumentCollection;
import com.ipc.oce.metadata.collection.OCMetadataDocumentJournalCollection;
import com.ipc.oce.metadata.collection.OCMetadataDocumentNumeratorCollection;
import com.ipc.oce.metadata.collection.OCMetadataEnumCollection;
import com.ipc.oce.metadata.collection.OCMetadataExchangePlanCollection;
import com.ipc.oce.metadata.collection.OCMetadataInformationRegistersCollection;
import com.ipc.oce.metadata.collection.OCMetadataReportCollection;
import com.ipc.oce.metadata.collection.OCMetadataRoleCollection;
import com.ipc.oce.metadata.collection.OCMetadataSequenceCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.metadata.collection.OCMetadataXDTOPackagesCollection;
import com.ipc.oce.metadata.objects.OCLanguage;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;
import com.ipc.oce.varset.OCObjectAutonumerationMode;

/**
 * Используется для обращения к метаданным конфигурации. Доступ к объекту
 * осуществляется через свойство глобального контекста Метаданные. Объект
 * предоставляет доступ к общим свойствам конфигурации и к основным коллекциям
 * описаний свойств объектов метаданных (документам, справочникам и т.п.).
 * Полученный из свойств этой коллекции объект метаданных предоставляет в свою
 * очередь доступ к свойствам документа и к коллекциям объектов метаданных
 * подчиненного класса (например, реквизитам, табличным частям справочника и
 * т.п.) и так далее.
 * 
 * @author Konovalov
 * 
 */
public class OCConfigurationMetadataObject extends OCObject {

	public OCConfigurationMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCConfigurationMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Версия конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getVersion() throws JIException {
		return get("Version").getObjectAsString2();
	}

	/**
	 * Краткое наименование конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Информация об авторе конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getCopyright() throws JIException {
		return get("Copyright").getObjectAsString2();
	}

	/**
	 * Произвольная строка символов. Как правило, расшифровывает и поясняет Имя
	 * конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getComment() throws JIException {
		return get("Comment").getObjectAsString2();
	}
	
	/**
	 * Краткая информация о конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getBriefInformation() throws JIException {
		return get("BriefInformation").getObjectAsString2();
	}

	/**
	 * Объект метаданных, который описывает основной язык конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCLanguage getDefaultLanguage() throws JIException {
		return new OCLanguage(get("DefaultLanguage"));
	}

	/**
	 * Ссылка на информацию о конфигурации. Может задаваться как с префиксом
	 * схемы («http://»), так и без него. Слева в верхней части страниц справки
	 * указывается наименование конфигурации, определенное в свойстве
	 * КраткаяИнформация (если свойство не задано, то выбирается из значения
	 * свойства Синоним, если свойство Синоним не задано, то выбирается из
	 * свойства объекта ОбъектМетаданныхКонфигурация). Показываемое наименование
	 * конфигурации имеет ссылку, заданную данным свойством.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getConfigurationInformationAddress() throws JIException {
		return get("ConfigurationInformationAddress").getObjectAsString2();
	}

	/**
	 * Ссылка на информацию о поставщике конфигурации (указывается в свойстве
	 * АвторскиеПрава). Может задаваться как с префиксом схемы («http://»), так
	 * и без него. Информация о поставщике, отображаемая в левой части подвала
	 * на страницах справки, имеет ссылку, определенную в данном свойстве.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getVendorInformationAddress() throws JIException {
		return get("VendorInformationAddress").getObjectAsString2();
	}

	/**
	 * Содержит адрес ресурса, который может использоваться для обновления
	 * прикладного решения
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getUpdateCatalogAddress() throws JIException {
		return get("UpdateCatalogAddress").getObjectAsString2();
	}

	/**
	 * Подробная информация о конфигурации (допускается использование
	 * многострочного текста).
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getDetailedInformation() throws JIException {
		return get("DetailedInformation").getObjectAsString2();
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все подсистемы,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException {
		return new OCMetadataSubsystemCollection(get("Subsystems"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все документы,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataDocumentCollection getDocuments() throws JIException {
		return new OCMetadataDocumentCollection(get("Documents"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все справочники,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataCatalogCollection getCatalogs() throws JIException {
		return new OCMetadataCatalogCollection(get("Catalogs"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все перечисления,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataEnumCollection getEnums() throws JIException {
		return new OCMetadataEnumCollection(get("Enums"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все планы счетов,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataChartsOfAccountCollection getChartsOfAccounts()
			throws JIException {
		return new OCMetadataChartsOfAccountCollection(get("ChartsOfAccounts"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все константы,
	 * определенные в конфигурации.
	 * 
	 * @return OCMetadataConstantCollection
	 * @throws JIException
	 */
	public OCMetadataConstantCollection getConstants() throws JIException {
		return new OCMetadataConstantCollection(get("Constants"));
	}
	
	/**
	 * Коллекция объектов метаданных, которые описывают все роли, определенные в
	 * конфигурации.
	 * 
	 * @return OCMetadataRoleCollection
	 * @throws JIException
	 */
	public OCMetadataRoleCollection getRoles() throws JIException {
		return new OCMetadataRoleCollection(get("Roles"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все журналы документов,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataDocumentJournalCollection getDocumentJournals()
			throws JIException {
		return new OCMetadataDocumentJournalCollection(get("DocumentJournals"));
	}
	
	/**
	 * Коллекция объектов метаданных, которые описывают все нумераторы,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataDocumentNumeratorCollection getDocumentNumerators()
			throws JIException {
		return new OCMetadataDocumentNumeratorCollection(
				get("DocumentNumerators"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все отчеты, определенные
	 * в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataReportCollection getReports() throws JIException {
		return new OCMetadataReportCollection(get("Reports"));
	}
	
	/**
	 * Коллекция объектов метаданных, которые описывают все пакеты XDTO, определенные в конфигурации
	 * @return
	 * @throws JIException
	 */
	public OCMetadataXDTOPackagesCollection getXDTOPackages()
			throws JIException {
		return new OCMetadataXDTOPackagesCollection(get("XDTOPackages"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все регистры
	 * бухгалтерии, определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAccountingRegisterCollection getAccountingRegisters()
			throws JIException {
		return new OCMetadataAccountingRegisterCollection(
				get("AccountingRegisters"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все планы видов расчета,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataChartsOfCalculationTypeCollection getChartsOfCalculationTypes()
			throws JIException {
		return new OCMetadataChartsOfCalculationTypeCollection(
				get("ChartsOfCalculationTypes"));
	}
	
	/**
	 * Коллекция объектов метаданных, которые описывают все регистры накопления,
	 * определенные в конфигурации
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAccumulationRegisterCollection getAccumulationRegisters()
			throws JIException {
		return new OCMetadataAccumulationRegisterCollection(
				get("AccumulationRegisters"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все планы видов
	 * характеристик, определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataChartOfCharacteristicTypeCollection getChartsOfCharacteristicTypes()
			throws JIException {
		return new OCMetadataChartOfCharacteristicTypeCollection(
				get("ChartsOfCharacteristicTypes"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все планы обмена,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataExchangePlanCollection getExchangePlans()
			throws JIException {
		return new OCMetadataExchangePlanCollection(get("ExchangePlans"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все последовательности,
	 * определенные в конфигурации
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSequenceCollection getSequences() throws JIException {
		return new OCMetadataSequenceCollection(get("Sequences"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все регистры расчета,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataCalculationRegisterCollection getCalculationRegisters()
			throws JIException {
		return new OCMetadataCalculationRegisterCollection(
				get("CalculationRegisters"));
	}

	/**
	 * Коллекция объектов метаданных, которые описывают все регистры сведений,
	 * определенные в конфигурации.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataInformationRegistersCollection getInformationRegisters()
			throws JIException {
		return new OCMetadataInformationRegistersCollection(
				get("InformationRegisters"));
	}

	/**
	 * Определяет, переиспользовать или нет автоматически полученные номера
	 * объектов, если они не записаны в базу данных.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCObjectAutonumerationMode getObjectAutonumerationMode()
			throws JIException {
		return new OCObjectAutonumerationMode(get("ObjectAutonumerationMode"));
	}

	/**
	 * Содержит варианты режимов управления блокировкой данных, устанавливаемых
	 * по умолчанию
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCDefaultDataLockControlMode getDataLockControlMode()
			throws JIException {
		return new OCDefaultDataLockControlMode(get("DataLockControlMode"));
	}
	
	/**
	 * Базовый объект для инициализации конкретного объекта медаданных. Пример: new OCDocumentMetadataObject(findByFullName(<имя документа>));
	 * @param fullName
	 * @return OCObject если объект найден и null в противном случае
	 * @throws JIException
	 * @throws Exception
	 * @throws NoSuchMethodException
	 */
	public _OCCommonMetadataObject findByFullName(String fullName) throws JIException {
		JIVariant var = callMethodA(
				"FindByFullName", 
				new Object[]{new JIVariant(fullName)})[0];
		_OCCommonMetadataObject oco = null;
		if (var.getType() != JIVariant.VT_EMPTY) {
			oco = new _OCCommonMetadataObject(var);
		}
		return oco;
	}
	
	/**
	 * Осуществляет поиск объекта метаданных, отвечающего за указанный тип.
	 * Например, для объекта типа Справочник.Товары позволяет найти объект
	 * метаданных Справочник.Товары. Объекты метаданных конфигурации, отвечающие
	 * за прикладные объекты, определяют соответствующий набор типов. Например,
	 * наличие в конфигурации объекта метаданных Справочник.Валюты определяет
	 * наличие в системе нескольких типов (СправочникСсылка.Валюты,
	 * СправочникМенеджер.Валюты, СправочникОбъект.Валюты,
	 * СправочникВыборка.Валюты, СправочникСписок.Валюты). Примечание: Позволяет
	 * по указанному типу найти объект метаданных, который определяет его в
	 * конфигурации.
	 * 
	 * @param type
	 *            Тип, по которому будет выполнен поиск объекта метаданных.
	 * @return _OCCommonMetadataObject
	 * @throws JIException
	 */
	public _OCCommonMetadataObject findByType(OCType type) throws JIException {
		JIVariant var = callMethodA("FindByType", new JIVariant(ocObject2Dispatch(type)))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new _OCCommonMetadataObject(var);
		} else {
			return null;
		}
	}
}
