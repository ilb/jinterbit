package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCCatalogMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Предназначен для управления справочником, как объектом конфигурации. С помощью этого объекта осуществляется поиск элементов, создание новых элементов и групп, работа с формами и макетами справочника. Доступ к объекту осуществляется через свойства объекта СправочникиМенеджер. Полное имя типа объекта определяется с учетом имени справочника конфигурации. Например, для справочника "Номенклатура" имя типа будет выглядеть СправочникМенеджер.Номенклатура.
 * @author Konovalov
 *
 */
public class OCCatalogManager extends _OCAbstractManager {

	public OCCatalogManager(OCObject object) {
		super(object);
	}

	public OCCatalogManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCatalogManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
		
	}
	
	/**
	 * Получить существующий каталог или создать новый. Созданный каталог уже записан в базу данных.
	 * @param manager менеджер каталогов (домументу, справочники и т.д.)
	 * @param parent - родительский каталог для нового каталога (null или emptyRef для корневых папок)
	 * @param folderName - имя каталога
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef getOrCreateCatalogFolder(OCCatalogRef parent, String folderName) throws JIException {
		parent = parent != null ? parent : emptyRef();
		OCCatalogRef folderRef = findByDescription(folderName, true, parent, null);
		if (folderRef.isEmpty()) { // folder not found
			OCCatalogObject obj = createFolder();
			obj.setDescription(folderName);
			obj.setParent(parent);
			obj.write();
			obj.read();
			folderRef = obj.getRef();
		}
		return folderRef;
	}
	
	/**
	 * Формирует выборку элементов справочника по заданным условиям. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogSelection select() throws JIException {
		return select(null, null, null, null);
	}
	
	/**
	 * Формирует выборку элементов справочника по заданным условиям. 
	 * @param parentRef Отбор по родителю. Имеет смысл только для многоуровневых справочников. Если параметр не задан, то отбор по родителю не производится. Чтобы отобрать элементы верхнего уровня, нужно в качестве данного параметра указать пустую ссылку на элемент справочника. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogSelection select(OCCatalogRef parentRef) throws JIException{
		return select(parentRef, null, null, null);
	}
	
	/**
	 * Формирует выборку элементов справочника по заданным условиям. 
	 * @param parentRef Отбор по родителю. Имеет смысл только для многоуровневых справочников. Если параметр не задан, то отбор по родителю не производится. Чтобы отобрать элементы верхнего уровня, нужно в качестве данного параметра указать пустую ссылку на элемент справочника.
	 * @param ownerRef Отбор по владельцу. Имеет смысл только для подчиненных справочников. Если параметр не задан, то отбор по владельцу не производится. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogSelection select(OCCatalogRef parentRef, OCCatalogRef ownerRef) throws JIException{
		return select(parentRef, ownerRef, null, null);
	}
	
	/**
	 * Формирует выборку элементов справочника по заданным условиям.
	 * @param parentRef Отбор по родителю. Имеет смысл только для многоуровневых справочников. Если параметр не задан, то отбор по родителю не производится. Чтобы отобрать элементы верхнего уровня, нужно в качестве данного параметра указать пустую ссылку на элемент справочника.
	 * @param ownerRef Отбор по владельцу. Имеет смысл только для подчиненных справочников. Если параметр не задан, то отбор по владельцу не производится.
	 * @param structure  Задает поле и значение отбора открываемой выборки. Ключ структуры описывает имя поля, а значение структуры - значение отбора по этому полю. В качестве полей для отбора могут задаваться только поля "Код", "Наименование" и реквизиты справочника, для которых в конфигураторе признак индексирования установлен в значение "Индексировать" или в значение "Индексировать с доп. упорядоч.". Важно! Структура может содержать только один элемент.
	 * @param order  Строка с именем реквизита документа, определяющая упорядочивание документов в выборке. Может быть указано поле "Дата" или имя реквизита документа, для которого признак индексирования в конфигураторе установлен в значения "Индексировать" или "Индексировать с доп. упорядочиванием". После указания имени через пробел может быть указано направление сортировки. Направление определяется: "Убыв" ("Desc") - упорядочивать по убыванию, и "Возр" ("Asc") - упорядочивать по возрастанию. По умолчанию выборка упорядочивается по возрастанию. Если параметр не задан, выборка упорядочивается по хронологии документов. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogSelection select(OCCatalogRef parentRef, OCCatalogRef ownerRef, OCStructure structure, String order) throws JIException{
		PreparedPredicateStruct wrapper = null;
		if (structure != null) {
			wrapper = new PreparedPredicateStruct(structure);
		}
		JIVariant parentVar = new JIVariant(
				parentRef != null ? parentRef.dispatch() : null);
		JIVariant ownerVar = new JIVariant(
				ownerRef != null ? ownerRef.dispatch() : null);
		JIVariant structureVariant = new JIVariant(
				structure != null ? wrapper.dispatch() : null);
		return new OCCatalogSelection(
				callMethodA("Select", new Object[]{parentVar, ownerVar, structureVariant, new JIVariant(order)})[0]);
	}
	
	/**
	 * Формирует иерархическую выборку элементов справочника по заданным условиям. При иерархической выборке для каждого элемента сначала выбираются элементы, для которых он является родителем, а затем уже выбираются элементы следующего уровня. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogSelection selectHierarchically() throws JIException{
		return new OCCatalogSelection(callMethodA("SelectHierarchically"));
	}
	
	/**
	 * Формирует иерархическую выборку элементов справочника по заданным условиям. При иерархической выборке для каждого элемента сначала выбираются элементы, для которых он является родителем, а затем уже выбираются элементы следующего уровня.
	 * @param parentRef Отбор по родителю. Имеет смысл только для многоуровневых справочников. Если параметр не задан, то отбор по родителю не производится. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogSelection selectHierarchically(OCCatalogRef parentRef) throws JIException{
		return new OCCatalogSelection(callMethodA("SelectHierarchically", new Object[]{new JIVariant(parentRef.dispatch())})[0]);
	}
	
	public OCCatalogSelection selectHierarchically(OCCatalogRef parentRef, OCCatalogRef ownerRef, OCStructure structure, String order) throws JIException{
		PreparedPredicateStruct wrapper = null;
		if (structure != null) {
			wrapper = new PreparedPredicateStruct(structure);
		}
		JIVariant parentVar = new JIVariant(
				parentRef != null ? parentRef.dispatch() : null);
		JIVariant ownerVar = new JIVariant(
				ownerRef != null ? ownerRef.dispatch() : null);
		JIVariant structureVariant = new JIVariant(
				structure != null ? wrapper.dispatch() : null);
		return new OCCatalogSelection(callMethodA("SelectHierarchically",
				new Object[] { parentVar, ownerVar, structureVariant,
						new JIVariant(order) })[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его коду. 
	 * @param code
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef findByCode(Integer code) throws JIException{
		return findByCode(code, false, null, null);
	}
	
	/**
	 * Осуществляет поиск элемента по его коду. 
	 * @param code
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef findByCode(String code) throws JIException{
		return findByCode(code, false, null, null); // BUG-FIX: 2nd null -> false
	}
	
	/**
	 * Осуществляет поиск элемента по его коду
	 * @param code  Искомый код. Строка(!!!) или число(!!!) в зависимости от настроек справочника в конфигураторе
	 * @param [isFullCode] Определяет режим поиска по полному коду. Истина - искомый код следует задавать в виде строки, состоящей из последовательности кодов по уровням справочника, разделенных символом "/".
	 * @param [parentRef] Родитель, в пределах которого нужно выполнять поиск. Если не указан, то поиск будет проводиться во всем справочнике
	 * @param [ownerRef] Владелец, в пределах которого нужно выполнять поиск. Если не указан, то поиск будет проводиться во всем справочнике
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef findByCode(Object code, Boolean isFullCode, OCCatalogRef parentRef, OCCatalogRef ownerRef) throws JIException{
		return new OCCatalogRef(callMethodA("FindByCode",
				new Object[] {
						JIVariant.makeVariant(code),
						new JIVariant(isFullCode),
						new JIVariant((parentRef != null ? parentRef.dispatch()
								: null)),
						new JIVariant((ownerRef != null ? ownerRef.dispatch()
								: null)) })[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его наименованию. 
	 * Примечание:
	 * Если существует несколько элементов с указанным наименованием, то будет найден только один из них. 
	 * @param description
	 * @return Ссылка на найденный элемент справочника. Если не существует ни одного элемента с требуемым наименованием, то будет возвращена пустая ссылка. 
	 * @throws JIException
	 */
	public OCCatalogRef findByDescription(String description) throws JIException{
		return new OCCatalogRef(callMethodA("FindByDescription",
				new Object[] { JIVariant.makeVariant(description) })[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его наименованию. Примечание: Если существует несколько элементов с указанным наименованием, то будет найден только один из них. 
	 * @param description  Строка, содержащая искомое наименование
	 * @param useStartWith  Определяет режим поиска по полному соответствию. Поиск будет успешным, если строка поиска: в случае значения параметра Ложь - будет соответствовать левой части наименования; в случае значения параметра Истина - будет полностью совпадать с наименованием (за исключением "хвостовых" пробелов в наименовании).
	 * @param parentRef Родитель, в пределах которого нужно выполнять поиск. Если не указан, то поиск будет проводиться во всем справочнике
	 * @param ownerRef Владелец, в пределах которого нужно выполнять поиск. Если не указан, то поиск будет проводиться во всем справочнике
	 * @return Ссылка на найденный элемент справочника. Если не существует ни одного элемента с требуемым наименованием, то будет возвращена пустая ссылка
	 * @throws JIException
	 */
	public OCCatalogRef findByDescription(String description, Boolean useStartWith, OCCatalogRef parentRef, OCCatalogRef ownerRef) throws JIException{
		return new OCCatalogRef(callMethodA("FindByDescription", new Object[]{
				new JIVariant(description),
				new JIVariant(useStartWith),
				new JIVariant((parentRef != null ? parentRef.dispatch() : null)),
				new JIVariant((ownerRef != null ? ownerRef.dispatch() : null))
				})[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его наименованию. Примечание: Если существует несколько элементов с указанным наименованием, то будет найден только один из них.
	 * Отличие от метода findByDescription в измененной сигнатуре для того что бы принимать параметр ownerRef любого подтипа _OCCommonRef, так как бывают ситуации когда
	 * владельцем может быть не только справочником.
	 * @param description  Строка, содержащая искомое наименование
	 * @param useStartWith  Определяет режим поиска по полному соответствию. Поиск будет успешным, если строка поиска: в случае значения параметра Ложь - будет соответствовать левой части наименования; в случае значения параметра Истина - будет полностью совпадать с наименованием (за исключением "хвостовых" пробелов в наименовании).
	 * @param parentRef Родитель, в пределах которого нужно выполнять поиск. Если не указан, то поиск будет проводиться во всем справочнике
	 * @param ownerRef Владелец, в пределах которого нужно выполнять поиск. Если не указан, то поиск будет проводиться во всем справочнике
	 * @return Ссылка на найденный элемент справочника. Если не существует ни одного элемента с требуемым наименованием, то будет возвращена пустая ссылка
	 * @throws JIException
	 */
	public OCCatalogRef findByDescriptionByVariousOwner(String description, Boolean useStartWith, OCCatalogRef parentRef, _OCCommonRef ownerRef) throws JIException{
		return new OCCatalogRef(callMethodA("FindByDescription", new Object[]{
				new JIVariant(description),
				new JIVariant(useStartWith),
				new JIVariant((parentRef != null ? parentRef.dispatch() : null)),
				new JIVariant((ownerRef != null ? ownerRef.dispatch() : null))
				})[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по значению реквизита. Примечание: Если
	 * существует несколько элементов с указанным значением реквизита, то будет
	 * найдет только один из них. Для реквизитов типа Строка поиск
	 * осуществляется по точному соответствию.
	 * 
	 * @param attributeName
	 *            Имя реквизита, как он задан в конфигураторе, по значению
	 *            которого осуществляется поиск. Тип значения произвольный,
	 *            кроме ХранилищеЗначения и строк произвольной длины.
	 * @param attributeValue
	 *            Произвольный. Значение реквизита, по которому должен
	 *            выполняться поиск
	 * @param [parentRef] Родитель, в пределах которого нужно выполнять поиск.
	 *        Если не указан, то поиск будет проводиться во всем справочнике
	 * @param [ownerRef] Владелец, в пределах которого нужно выполнять поиск.
	 *        Если не указан, то поиск будет проводиться во всем справочнике
	 * @return Ссылка на найденный элемент справочника. Если не существует ни
	 *         одного элемента с требуемым значением реквизита, то будет
	 *         возвращена пустая ссылка.
	 * @throws JIException
	 */
	public OCCatalogRef findByAttribute(String attributeName, OCVariant attributeValue, OCCatalogRef parentRef, OCCatalogRef ownerRef) throws JIException{
		return new OCCatalogRef(callMethodA("FindByAttribute",
				new Object[] {
						new JIVariant(attributeName),
						new JIVariant(ocVariant2JI(attributeValue)),
						new JIVariant((parentRef != null ? parentRef.dispatch()
								: null)),
						new JIVariant((ownerRef != null ? ownerRef.dispatch()
								: null)) })[0]);
	}
	
	/**
	 * Создает новый элемент справочника. 
	 * Примечание:
	 * Использование метода не приводит к записи созданного объекта в базу данных. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogObject createItem() throws JIException{
		return new OCCatalogObject(callMethodA("CreateItem"));
	}
	
	/**
	 * Создает новую группу справочника
	 * @return
	 * @throws JIException
	 */
	public OCCatalogObject createFolder() throws JIException{
		return new OCCatalogObject(callMethodA("CreateFolder"));
	}
	
	/**
	 * Получает пустое значение ссылки на справочник данного вида. 
	 * Примечание:
	 * Может использоваться, например, когда нужно передать пустую ссылку в параметр метода. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef emptyRef() throws JIException{
		return new OCCatalogRef(callMethodA("EmptyRef"));
	}
	
	/**
	 * Получает имя предопределенного элемента. Если данный элемент не является предопределенным, то возвращается пустая строка
	 * @param ref Ссылка на элемент, имя которого требуется получить
	 * @return
	 * @throws JIException
	 */
	public String getPredefinedItemName(OCCatalogRef ref) throws JIException{
		return callMethodA("GetPredefinedItemName", new Object[]{new JIVariant(ref.dispatch())})[0].getObjectAsString2();
	}
	
	/**
	 * Содержит ссылки на предопределенный объект справочников. Доступ к значению осуществляется по имени, как они заданы в конфигураторе. Имена имеют только предопределенные элементы справочников. Количество таких свойств задается списком предопределенных элементов справочника.
	 * @param name Доступ к значению осуществляется по имени, как они заданы в конфигураторе
	 * @return ссылку на предопределенный элемент справочника
	 * @throws JIException 
	 */
	public OCCatalogRef getPredefinedItem(String name) throws JIException{
		return new OCCatalogRef(get(name));
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.objects.OCAbstractManager#loadMetadata()
	 */
	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getCatalogs().find(managerName);
	}

	@Override
	public OCCatalogMetadataObject getMetadata() throws JIException {
		return new OCCatalogMetadataObject(super.getMetadata());
	}

	/**
	 * Формирует ссылку из значения типа УникальныйИдентификатор.
	 * Данный уникальный идентификатор может быть в дальнейшем получен из ссылки методом УникальныйИдентификатор. 
	 * @param uuid
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef getRef(OCUUID uuid) throws JIException {
		return new OCCatalogRef(callMethodA("GetRef",
				new Object[] { uuid.dispatch() })[0]);
	}
	
	/**
	 * Создание новой ссылки на элемент. Аналог getRef без параметра.
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef newRef() throws JIException{
		return new OCCatalogRef(callMethodA("GetRef"));
	}
	
	
	
}
