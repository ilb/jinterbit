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
import com.ipc.oce.metadata.objects.OCExchangePlanMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Предназначен для управления планом обмена, как объектом конфигурации. С
 * помощью этого объекта осуществляется поиск узлов, создание новых узлов,
 * работа с формами и макетами плана обмена. Доступ к объекту осуществляется
 * через свойства объекта ПланыОбменаМенеджер. Полное имя типа объекта
 * определяется с учетом имени плана обмена конфигурации.
 * 
 * @author Konovalov
 * 
 */
public class OCExchangePlanManager extends _OCAbstractManager {

	/**
	 * @param aDispatch
	 */
	public OCExchangePlanManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCExchangePlanManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * @param object
	 */
	public OCExchangePlanManager(OCObject object) {
		super(object);
	}

	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getExchangePlans().find(managerName);
	}

	@Override
	public OCExchangePlanMetadataObject getMetadata() throws JIException {
		return new OCExchangePlanMetadataObject(super.getMetadata());
	}
	
	/**
	 * Формирует выборку узлов по заданным условиям.
	 * @return ExchangePlanSelection
	 * @throws JIException
	 */
	public OCExchangePlanSelection select() throws JIException{
		return new OCExchangePlanSelection(callMethodA("Select"));
	}
	
	/**
	 * Формирует выборку узлов по заданным условиям.
	 * @param structure Структура. Задает поле и значение отбора открываемой выборки. Ключ структуры описывает имя поля, а значение структуры - значение отбора по этому полю. В качестве полей для отбора могут задаваться только поля "Код", "Наименование" и реквизиты узла, для которых в конфигураторе признак индексирования установлен в значение "Индексировать" или в значение "Индексировать с доп. упорядоч.". Важно! Структура может содержать только один элемент.
	 * @param order  Строка с именем реквизита узла, определяющая упорядочивание узлов в выборке. Может быть указано "Код", "Наименование" или имя одного из реквизитов примитивного типа (Число, Строка, Дата, Булево), для которого установлен признак "Индексирование" в значение "Индексировать" или в "Индексировать с доп. упорядоч." в конфигураторе. После имени реквизита через пробел может быть указано направление сортировки. Направление определяется: "Убыв" ("Desc") - упорядочивать по убыванию; "Возр" ("Asc") - упорядочивать по возрастанию. По умолчанию сортировка производится по возрастанию. Если параметр не указан, то порядок определяется основным представлением плана обмена. Значение по умолчанию: Пустая строка 
	 * @return ExchangePlanSelection
	 * @throws JIException
	 */
	public OCExchangePlanSelection select(OCStructure structure, String order) throws JIException{
		return new OCExchangePlanSelection(callMethodA("Select", new Object[]{ocObject2Dispatch(structure) , new JIVariant(order) })[0]);
	}
	
	/**
	 * Если не существует ни одного узла с требуемым кодом, то возвращается пустая ссылка
	 * @param code Искомый код. 
	 * @return
	 * @throws JIException
	 */
	public OCExchangePlanRef findByCode(String code) throws JIException{
		return new OCExchangePlanRef(callMethodA("FindByCode", new Object[]{new JIVariant(code)})[0]);
	}
	
	/**
	 * Осуществляет поиск узла плана обмена по его наименованию.
	 * 
	 * @param description
	 *            - Строка, содержащая искомое наименование.
	 * @param exact
	 *            - Определяет режим поиска по полному соответствию. Поиск будет
	 *            успешным, если строка поиска: в случае значения параметра Ложь
	 *            - будет соответствовать левой части наименования; в случае
	 *            значения параметра Истина - будет полностью совпадать с
	 *            наименованием (за исключением "хвостовых" пробелов в
	 *            наименовании).
	 * 
	 * @return OCExchangePlanRef -  Ссылка на найденный узел. Если не существует ни одного узла с требуемым наименованием, то будет возвращена пустая ссылка
	 * @throws JIException
	 */
	public OCExchangePlanRef findByDescription(String description, boolean exact) throws JIException {
		return new OCExchangePlanRef(callMethodA("FindByDescription", new Object[]{new JIVariant(description), new JIVariant(exact)})[0]);
	}
	
	/**
	 * Осуществляет поиск узла плана обмена по его наименованию.
	 * 
	 * @param description
	 *            - Строка, содержащая искомое наименование.
	 * 
	 * @return OCExchangePlanRef -  Ссылка на найденный узел. Если не существует ни одного узла с требуемым наименованием, то будет возвращена пустая ссылка
	 * @throws JIException
	 */
	public OCExchangePlanRef findByDescription(String description) throws JIException {
		return findByDescription(description, false);
	}
	
	/**
	 * Осуществляет поиск узла по значению реквизита. Если существует несколько
	 * узлов с указанным наименованием, то будет найден только один из них.
	 * 
	 * @param attrName
	 *            Имя реквизита, как он задан в конфигураторе, по значению
	 *            которого осуществляется поиск. Тип значения произвольный,
	 *            кроме ХранилищеЗначения и строк произвольной длины.
	 * @param value
	 *            Значение реквизита, по которому должен выполняться поиск.
	 * @return Если не существует ни одного узла с требуемым наименованием, то
	 *         будет возвращена пустая ссылка.
	 * @throws JIException
	 */
	public OCExchangePlanRef findByAttribute(String attrName, OCVariant value) throws JIException {
		return new OCExchangePlanRef(callMethodA("FindByAttribute", new Object[]{new JIVariant(attrName), ocVariant2JI(value)})[0]);
	}
	
	/**
	 * Формирует ссылку из значения типа УникальныйИдентификатор. Данный уникальный идентификатор может быть в дальнейшем получен из ссылки методом УникальныйИдентификатор. 
	 * @param uuid Уникальный идентификатор, из которого будет формироваться ссылка. Если параметр не указан, то будет сформирована новая уникальная ссылка
	 * @return ПланОбменаСсылка. 
	 * @throws JIException
	 */
	public OCExchangePlanRef getRef(OCUUID uuid) throws JIException{
		return new OCExchangePlanRef(callMethodA("GetRef", new Object[]{uuid.dispatch()})[0]);
	}
	
	/**
	 * Получает пустое значение ссылки на план обмена данного вида. Может использоваться, например, в тех случаях, когда нужно передать пустую ссылку в параметр метода. 
	 * @return ПланОбменаСсылка. 
	 * @throws JIException
	 */
	public OCExchangePlanRef emptyRef() throws JIException{
		return new OCExchangePlanRef(callMethodA("EmptyRef"));
	}
	
	/**
	 * Создает новый узел обмена. Использование метода не приводит к записи созданного объекта в базу данных. 
	 * @return ПланОбменаОбъект. 
	 * @throws JIException
	 */
	public OCExchangePlanObject createNode() throws JIException{
		return new OCExchangePlanObject(callMethodA("CreateNode"));
	}
	
	/**
	 * Получает ссылку на предопределенный узел, соответствующий данной базе данных
	 * @return ПланОбменаСсылка. 
	 * @throws JIException
	 */
	public OCExchangePlanRef thisNode() throws JIException{
		return new OCExchangePlanRef(callMethodA("ThisNode"));
	}

}
