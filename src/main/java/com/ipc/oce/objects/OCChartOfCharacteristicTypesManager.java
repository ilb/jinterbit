/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCChartOfCharacteristicTypeMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Набор свойств содержит менеджеры отдельных планов видов характеристик. Доступ к менеджеру осуществляется по имени, как они заданы в конфигураторе. 
 * @author Konovalov
 *
 */
public class OCChartOfCharacteristicTypesManager extends _OCAbstractManager {

	/**
	 * @param aDispatch
	 */
	public OCChartOfCharacteristicTypesManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesManager(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	/**
	 * @param object
	 */
	public OCChartOfCharacteristicTypesManager(OCObject object) {
		super(object);
	}

	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getChartsOfCharacteristicTypes().find(managerName);
	}

	@Override
	public OCChartOfCharacteristicTypeMetadataObject getMetadata() throws JIException {
		return new OCChartOfCharacteristicTypeMetadataObject(super.getMetadata());
	}
	
	/**
	 * Формирует выборку элементов вида характеристики по заданным условиям.
	 * 
	 * @param Отбор
	 *            по родителю. Имеет смысл только для многоуровневых видов
	 *            характеристик. Если параметр не задан, то отбор по родителю не
	 *            производится. Чтобы отобрать элементы верхнего уровня, нужно в
	 *            качестве данного параметра указать пустую ссылку на элемент
	 *            вида характеристики.
	 * @param Задает
	 *            поле и значение отбора открываемой выборки. Ключ структуры
	 *            описывает имя поля, а значение структуры - значение отбора по
	 *            этому полю. В качестве полей для отбора могут задаваться
	 *            только поля "Код", "Наименование" и реквизиты вида
	 *            характеристики, для которых в конфигураторе признак
	 *            индексирования установлен в значение "Индексировать" или в
	 *            значение "Индексировать с доп. упорядоч.". Важно! Структура
	 *            может содержать только один элемент.
	 * @param Строка
	 *            с именем реквизита вида характеристики, определяющая
	 *            упорядочивание элементов в выборке. Может быть указано "Код",
	 *            "Наименование" или имя одного из реквизитов примитивного типа
	 *            (Число, Строка, Дата, Булево), для которого установлен признак
	 *            "Индексирование" в значение "Индексировать" или в
	 *            "Индексировать с доп. упорядоч." в конфигураторе. После имени
	 *            реквизита через пробел может быть указано направление
	 *            сортировки. Направление определяется: "Убыв" ("Desc") -
	 *            упорядочивать по убыванию; "Возр" ("Asc") - упорядочивать по
	 *            возрастанию. По умолчанию сортировка производится по
	 *            возрастанию. Если параметр не указан, то порядок определяется
	 *            основным представлением вида характеристики. Значение по
	 *            умолчанию: Пустая строка
	 * @return OCChartOfCharacteristicTypesSelection
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesSelection select(
			OCChartOfCharacteristicTypesRef parent, OCStructure structure,
			String order) throws JIException {
		return new OCChartOfCharacteristicTypesSelection(
				callMethodA(
						"Select",
						new Object[] {
								parent != null ? ocObject2Dispatch(parent)
										: null,
								structure != null ? ocObject2Dispatch(structure)
										: null,
								order != null ? new JIVariant(order) : null })[0]);
	}
	
	/**
	 * Формирует выборку элементов вида характеристики по заданным условиям.
	 * @param Отбор по родителю. Имеет смысл только для многоуровневых видов характеристик. Если параметр не задан, то отбор по родителю не производится. Чтобы отобрать элементы верхнего уровня, нужно в качестве данного параметра указать пустую ссылку на элемент вида характеристики.
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesSelection select(OCChartOfCharacteristicTypesRef parent) throws JIException{
		return select(parent, null, null);
	}
	
	/**
	 * Формирует выборку элементов вида характеристики по заданным условиям.
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesSelection select() throws JIException{
		return select(null, null, null);
	}
	
	/**
	 * Формирует иерархическую выборку элементов справочника по заданным
	 * условиям. При иерархической выборке для каждого элемента сначала
	 * выбираются элементы, для которых он является родителем, а затем уже
	 * выбираются элементы следующего уровня.
	 * 
	 * @param Отбор
	 *            по родителю. Имеет смысл только для многоуровневых видов
	 *            характеристик. Если параметр не задан, то отбор по родителю не
	 *            производится.
	 * @param Ключ
	 *            структуры параметра описывает имя поля, а значение структуры -
	 *            значение отбора по этому полю. В качестве полей для отбора
	 *            могут задаваться только поля "Имя" и реквизиты вида
	 *            характеристики, для которых в конфигураторе признак
	 *            индексирования установлен в значение "Индексировать" или в
	 *            значение "Индексировать с доп. упорядоч.". Структура может
	 *            содержать только один элемент. Если параметр не указан, то
	 *            отбор не используется.
	 * @param Строка
	 *            с именем реквизита вида характеристики, определяющая
	 *            упорядочивание элементов в выборке. Может быть указано "Код",
	 *            "Наименование" или имя одного из реквизитов примитивного типа
	 *            (Число, Строка, Дата, Булево), для которого установлен признак
	 *            "Индексирование" в значение "Индексировать" или в
	 *            "Индексировать с доп. упорядоч." в конфигураторе. После имени
	 *            реквизита через пробел может быть указано направление
	 *            сортировки. Направление определяется: "Убыв" ("Desc") -
	 *            упорядочивать по убыванию; "Возр" ("Asc") - упорядочивать по
	 *            возрастанию. По умолчанию сортировка производится по
	 *            возрастанию. Если параметр не указан, то порядок определяется
	 *            основным представлением вида характеристики. Значение по
	 *            умолчанию: Пустая строка
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesSelection selectHierarchically(
			OCChartOfCharacteristicTypesRef parent, OCStructure structure,
			String order) throws JIException {
		return new OCChartOfCharacteristicTypesSelection(
				callMethodA(
						"Select",
						new Object[] {
								parent != null ? ocObject2Dispatch(parent)
										: null,
								structure != null ? ocObject2Dispatch(structure)
										: null,
								order != null ? new JIVariant(order) : null })[0]);
	}
	
	/**
	 * Формирует иерархическую выборку элементов справочника по заданным условиям. При иерархической выборке для каждого элемента сначала выбираются элементы, для которых он является родителем, а затем уже выбираются элементы следующего уровня.
	 * @param Отбор по родителю. Имеет смысл только для многоуровневых видов характеристик. Если параметр не задан, то отбор по родителю не производится.
	 * @return OCChartOfCharacteristicTypesSelection
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesSelection selectHierarchically(OCChartOfCharacteristicTypesRef parent) throws JIException{
		return selectHierarchically(parent, null, null);
	}
	
	/**
	 * Формирует иерархическую выборку элементов справочника по заданным условиям. При иерархической выборке для каждого элемента сначала выбираются элементы, для которых он является родителем, а затем уже выбираются элементы следующего уровня.
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesSelection selectHierarchically() throws JIException{
		return selectHierarchically(null, null, null);
	}
	
	/**
	 * Осуществляет поиск элемента по его коду
	 * 
	 * @param Искомый
	 *            код
	 * @param Родитель
	 *            , в пределах которого нужно выполнять поиск Если не указано,
	 *            то во всем плане видов характеристик.
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef findByCode(String code,
			OCChartOfCharacteristicTypesRef parent) throws JIException {
		return new OCChartOfCharacteristicTypesRef(callMethodA("FindByCode",
				new Object[] { new JIVariant(code),
						parent != null ? ocObject2Dispatch(parent) : null })[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его коду.
	 * @param code - Искомый код
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef findByCode(String code) throws JIException{
		return findByCode(code, null);
	}
	
	/**
	 * Осуществляет поиск элемента по его наименованию. Если существует
	 * несколько элементов с указанным наименованием, то будет найден только
	 * один из них
	 * 
	 * @param Строка
	 *            , содержащая искомое наименование.
	 * @param Определяет
	 *            режим поиска по полному соответствию. Поиск будет успешным,
	 *            если строка поиска: в случае значения параметра Ложь - будет
	 *            соответствовать левой части наименования; в случае значения
	 *            параметра Истина - будет полностью совпадать с наименованием
	 *            (за исключением "хвостовых" пробелов в наименовании). Значение
	 *            по умолчанию: Ложь
	 * @param Родитель
	 *            , в пределах которого нужно выполнять поиск. Если не указан,
	 *            то поиск будет проводиться во всех видах характеристик
	 * @return Ссылка на найденный вид характеристики. Если не существует ни
	 *         одного элемента с требуемым наименованием, то будет возвращена
	 *         пустая ссылка.
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef findByDescription(
			String description, Boolean exact,
			OCChartOfCharacteristicTypesRef parent) throws JIException {
		return new OCChartOfCharacteristicTypesRef(callMethodA(
				"FindByDescription", new Object[] { new JIVariant(description),
						exact != null ? new JIVariant(exact) : null,
						parent != null ? ocObject2Dispatch(parent) : null })[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по его наименованию. Если существует
	 * несколько элементов с указанным наименованием, то будет найден только
	 * один из них
	 * 
	 * @param Строка
	 *            , содержащая искомое наименование.
	 * @param Определяет
	 *            режим поиска по полному соответствию. Поиск будет успешным,
	 *            если строка поиска: в случае значения параметра Ложь - будет
	 *            соответствовать левой части наименования; в случае значения
	 *            параметра Истина - будет полностью совпадать с наименованием
	 *            (за исключением "хвостовых" пробелов в наименовании). Значение
	 *            по умолчанию: Ложь
	 * @return Ссылка на найденный вид характеристики. Если не существует ни
	 *         одного элемента с требуемым наименованием, то будет возвращена
	 *         пустая ссылка.
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef findByDescription(String description, Boolean exact) throws JIException{
		return findByDescription(description, exact, null);
	}
	
	/**
	 * Осуществляет поиск элемента по его наименованию. Если существует несколько элементов с указанным наименованием, то будет найден только один из них
	 * @param Строка, содержащая искомое наименование.
	 * @return Ссылка на найденный вид характеристики. Если не существует ни одного элемента с требуемым наименованием, то будет возвращена пустая ссылка.
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef findByDescription(String description) throws JIException{
		return findByDescription(description, null, null); 
	}
	
	/**
	 * Осуществляет поиск элемента по значению реквизита.
	 * 
	 * @param Имя
	 *            реквизита, как он задан в конфигураторе, по значению которого
	 *            осуществляется поиск. Тип значения произвольный, кроме
	 *            ХранилищеЗначения и строк произвольной длины.
	 * @param Произвольный
	 *            . Значение реквизита, по которому должен выполняться поиск.
	 * @param Родитель
	 *            , в пределах которого нужно выполнять поиск. Если не указан,
	 *            то во всем плане видов характеристик.
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef findByAttribute(String attrName,
			OCVariant variant, OCChartOfCharacteristicTypesRef parent)
			throws JIException {
		return new OCChartOfCharacteristicTypesRef(callMethodA(
				"FindByAttribute", new Object[] { new JIVariant(attrName),
						ocVariant2JI(variant),
						parent != null ? ocObject2Dispatch(parent) : null })[0]);
	}
	
	/**
	 * Осуществляет поиск элемента по значению реквизита.
	 * @param Имя реквизита, как он задан в конфигураторе, по значению которого осуществляется поиск. Тип значения произвольный, кроме ХранилищеЗначения и строк произвольной длины.
	 * @param Произвольный. Значение реквизита, по которому должен выполняться поиск.
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef findByAttribute(String attrName, OCVariant variant) throws JIException{
		return findByAttribute(attrName, variant, null);
	}
	
	/**
	 * Получает имя предопределенного элемента. Если данный элемент не является предопределенным, то возвращается пустая строка 
	 * @param  Ссылка на элемент, имя которого требуется получить. 
	 * @return имя предопределенного элемента
	 * @throws JIException
	 */
	public String getPredefinedItemName(OCChartOfCharacteristicTypesRef ref) throws JIException{
		return callMethodA("GetPredefinedItemName", new Object[]{ocObject2Dispatch(ref)})[0].getObjectAsString2();
	}
	
	/**
	 * Формирует ссылку из значения типа УникальныйИдентификатор. Данный уникальный идентификатор может быть в дальнейшем получен из ссылки методом УникальныйИдентификатор. 
	 * @param uuid Уникальный идентификатор, из которого будет формироваться ссылка
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef getRef(OCUUID uuid) throws JIException {
		return new OCChartOfCharacteristicTypesRef(callMethodA("GetRef",
				new Object[] { uuid.dispatch() })[0]);
	}
	
	/**
	 * Получает пустое значение ссылки на план видов характеристики данного вида. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef emptyRef() throws JIException{
		return new OCChartOfCharacteristicTypesRef(callMethodA("EmptyRef"));
	}
	
	/**
	 * Создает новую группу вида характеристики
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesObject createFolder() throws JIException{
		return new OCChartOfCharacteristicTypesObject(callMethodA("CreateFolder"));
	}
	
	/**
	 * Создает новый элемент вида характеристики
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesObject createItem() throws JIException{
		return new OCChartOfCharacteristicTypesObject(callMethodA("CreateItem"));
	}
}
