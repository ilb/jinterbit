/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCValueTable;
import com.ipc.oce.metadata.objects.OCInformationRegisterMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Предназначен для управления данными конкретного регистра сведений. Позволяет
 * осуществлять поиск, выбирать и создавать записи регистра сведений.
 * 
 * @author Konovalov
 * 
 */
public class OCInformationRegisterManager extends _OCAbstractManager {

	
	protected OCInformationRegisterManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	protected OCInformationRegisterManager(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	protected OCInformationRegisterManager(OCObject object) {
		super(object);
	}

	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getInformationRegisters().find(managerName);
	}

	@Override
	public OCInformationRegisterMetadataObject getMetadata() throws JIException {
		return new OCInformationRegisterMetadataObject(super.getMetadata());
	}
	
	/**
	 * Формирует выборку записей регистра сведений.
	 * 
	 * @param startDate
	 *            - Начало интервала, за который будут выдаваться записи
	 *            периодического регистра сведений. Если не указан, то будут
	 *            выдаваться записи с самого ранней включительно.
	 * @param endDate
	 *            - Конец интервала, за который будут выдаваться записи
	 *            периодического регистра сведений. Если не указан, то будут
	 *            выдаваться записи до самой поздней включительно.
	 * @param structure
	 *            - Задает поле и значение отбора открываемой выборки. Ключ
	 *            структуры описывает имя поля, а значение структуры - значение
	 *            отбора по этому полю. В качестве полей для отбора могут
	 *            задаваться измерения или реквизиты, для которых в
	 *            конфигураторе признак индексирования установлен в значение
	 *            "Индексировать" или установлен признак "Ведущее". Вид
	 *            сравнения может быть только Равно. Важно! Структура может
	 *            содержать только один элемент. Если параметр не указан, то
	 *            отбор не используется.
	 * @param order
	 *            - Может содержать слово "Возр" или "Убыв", тогда стандартный
	 *            порядок будет использоваться с указанным направлением. Может
	 *            содержать имя поля (реквизита или измерения, для которых
	 *            указано индексирование) и слово "Возр" или "Убыв", тогда
	 *            упорядочивание будет производится по указанному полю в
	 *            указанном порядке. Значение по умолчанию: "Возр"
	 * @return OCInformationRegisterSelection
	 * @throws JIException
	 */
	public OCInformationRegisterSelection select(Date startDate, Date endDate, OCStructure structure, String order) throws JIException {
		OCInformationRegisterSelection resSelection = null;
		JIVariant var = callMethodA("Select", new Object[]{
				startDate != null ? new JIVariant(startDate) : null,
				endDate != null ? new JIVariant(endDate) : null,
				structure != null ? new JIVariant(ocObject2Dispatch(structure)) : null,
				order != null ? new JIVariant(order) : null
		})[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			resSelection = new OCInformationRegisterSelection(var);
		}
		return resSelection;
	}
	
	/**
	 * Формирует выборку по регистратору записей регистра сведений. Применяется для регистров сведений, для которых в конфигураторе установлен режим записи "Подчинение регистратору".
	 * @param documentRef -  Ссылка на документ, являющийся регистратором для записей регистра. 
	 * @return OCInformationRegisterSelection
	 * @throws JIException
	 */
	public OCInformationRegisterSelection selectByRecorder(
			OCDocumentRef documentRef) throws JIException {
		return new OCInformationRegisterSelection(callMethodA(
				"SelectByRecorder",
				new Object[] { new JIVariant(ocObject2Dispatch(documentRef)) })[0]);
	}
	
	/**
	 * Получает значения ресурсов записи регистра, соответствующей указанным
	 * значениям измерений регистра и периоду.
	 * 
	 * @param date
	 *            - Определяет момент времени, на который необходимо получить
	 *            значения ресурсов. Если указанный момент времени не совпадает
	 *            с периодом ни одной записи регистра, то будет возвращена
	 *            структура, содержащая пустые значения.
	 * @param selection
	 *            - Структура, содержащая отбор по измерениям регистра. В
	 *            переданной структуре должны содержаться те измерения, по
	 *            которым должны быть отфильтрованы записи. В элементе структуры
	 *            задается имя и значение отбора. Должны указываться значения
	 *            для всех измерений. Параметр обязателен для регистров, имеющих
	 *            измерения. Если параметр не указан, то отбор не используется.
	 * @return OCStructure Структура, содержащая значения ресурсов. 
	 * @throws JIException
	 */
	public OCStructure getResourceValues(Date date, OCStructure selection) throws JIException {
		return new OCStructure(callMethodA("Get", new Object[]{
				new JIVariant(date),
				selection != null ? new JIVariant(ocObject2Dispatch(selection)) : null
				})[0]);
	}
	
	/**
	 * Получает значения ресурсов записи регистра, соответствующей указанным
	 * значениям измерений регистра и периоду.
	 * 
	 * @param date
	 *            - Определяет момент времени, на который необходимо получить
	 *            значения ресурсов. Если указанный момент времени не совпадает
	 *            с периодом ни одной записи регистра, то будет возвращена
	 *            структура, содержащая пустые значения.
	 * @return OCStructure Структура, содержащая значения ресурсов.
	 * @throws JIException
	 */
	public OCStructure getResourceValues(Date date) throws JIException {
		return getResourceValues(date, null);
	}
	
	/**
	 * Получает значения ресурсов записи регистра, соответствующей указанным
	 * значениям измерений регистра. 
	 * 
	 * @param selection
	 *            - Структура, содержащая отбор по измерениям регистра. В
	 *            переданной структуре должны содержаться те измерения, по
	 *            которым должны быть отфильтрованы записи. В элементе структуры
	 *            задается имя и значение отбора. Должны указываться значения
	 *            для всех измерений. Параметр обязателен для регистров, имеющих
	 *            измерения. Если параметр не указан, то отбор не используется.
	 * @return OCStructure Структура, содержащая значения ресурсов.
	 * @throws JIException
	 */
	public OCStructure getResourceValues(OCStructure selection) throws JIException {
		return new OCStructure(callMethodA("Get", new Object[]{
				selection != null ? new JIVariant(ocObject2Dispatch(selection)) : null
				})[0]);
	}
	
	/**
	 * Получает значения ресурсов записи регистра, соответствующей указанным
	 * значениям измерений регистра.
	 * 
	 * @return OCStructure Структура, содержащая значения ресурсов.
	 * @throws JIException
	 */
	public OCStructure getResourceValues() throws JIException {
		return getResourceValues((OCStructure)null);
	}
	
	/**
	 * Получает значения ресурсов наиболее ранней записи регистра,
	 * соответствующей указанным периоду и значениям измерений регистра. Поиск
	 * по периоду осуществляется "включительно", т.е. если существует запись с
	 * таким же значением одноименного свойства, то она и будет найдена.
	 * Применим только для периодических регистров сведений.
	 * 
	 * @param startDate
	 *            Определяет момент времени, начиная с которого необходимо
	 *            получить значения ресурсов. Может задаваться значениями типа
	 *            Дата, МоментВремени или Граница. Если параметр не указан, то
	 *            будут возвращены значения ресурсов самой первой записи
	 *            регистра.
	 * @param selection
	 *            Структура, содержащая отбор по измерениям регистра. Имя ключа
	 *            структуры должно совпадать с именем измерения регистра,
	 *            заданного в конфигураторе, а значение элемента структуры -
	 *            задает отбираемое по данному измерению значение. Если параметр
	 *            не указан, то отбор не используется.
	 * @return {@link OCStructure} Возвращает структуру, содержащую значения
	 *         ресурсов.
	 * @throws JIException
	 */
	public OCStructure getFirst(Date startDate, OCStructure selection) throws JIException {
		return new OCStructure(callMethodA("GetFirst", new Object[]{
				startDate != null ? new JIVariant(startDate) : null,
				selection != null ? new JIVariant(ocObject2Dispatch(selection)) : null
		})[0]);
	}
	
	/**
	 * Получает значения ресурсов наиболее поздней записи регистра,
	 * соответствующей указанным периоду и значениям измерений регистра.
	 * Применим только для периодических регистров сведений.
	 * 
	 * @param endDate
	 *            Определяет момент времени, по который необходимо получить
	 *            значения ресурсов. Может задаваться значениями типа Дата,
	 *            МоментВремени или Граница. Если параметр не указан, то будут
	 *            возвращены значения ресурсов самой последней записи регистра.
	 * @param selection
	 *            Структура, содержащая отбор по измерениям регистра. Имя ключа
	 *            структуры должно совпадать с именем измерения регистра,
	 *            заданного в конфигураторе, а значение элемента структуры -
	 *            задает отбираемое по данному измерению значение. Если параметр
	 *            не указан, то отбор не используется.
	 * @return {@link OCStructure} Возвращает структуру, содержащую значения
	 *         ресурсов.
	 * @throws JIException
	 */
	public OCStructure getLast(Date endDate, OCStructure selection) throws JIException {
		return new OCStructure(callMethodA("GetLast", new Object[]{
				endDate != null ? new JIVariant(endDate) : null,
				selection != null ? new JIVariant(ocObject2Dispatch(selection)) : null
		})[0]);
	}
	
	/**
	 * Создает ключ записи по переданной структуре.
	 * 
	 * @param structure
	 *            Структура, содержащая значения для заполнения свойств ключа
	 *            записи. Имена элементов структуры должны соответствовать
	 *            именам ключевых полей.
	 * @return OCInformationRegisterRecordKey
	 * @throws JIException
	 */
	public OCInformationRegisterRecordKey createRecordKey(OCStructure structure) throws JIException {
		return new OCInformationRegisterRecordKey(
				callMethodA("CreateRecordKey",  new JIVariant(ocObject2Dispatch(structure)))[0]
				);
	}
	
	/**
	 * Создает объект для управления записью регистра сведений.
	 * 
	 * @return OCInformationRegisterRecordManager
	 * @throws JIException
	 */
	public OCInformationRegisterRecordManager createRecordManager() throws JIException {
		return new OCInformationRegisterRecordManager(callMethodA("CreateRecordManager"));
	}
	
	/**
	 * Создает набор записей регистра сведений. Набор записей создается пустым. 
	 * @return OCInformationRegisterRecordSet
	 * @throws JIException
	 */
	public OCInformationRegisterRecordSet createRecordSet() throws JIException {
		return new OCInformationRegisterRecordSet(callMethodA("CreateRecordSet"));
	}
	
	/**
	 * Получает наиболее ранние записи регистра, соответствующие установленным в
	 * параметрах метода значениям ключевых полей. Записи подбираются для
	 * каждого сочетания из всех имеющихся значений измерений регистра.
	 * Примечание: Применим только для периодических регистров сведений.
	 * 
	 * @param startDate
	 *            Определяет момент времени, начиная с которого необходимо
	 *            выбрать записи. Если параметр не указан, то будут получены
	 *            записи без ограничения по времени.
	 * @param structure
	 *            Структура, содержащая отбор по измерениям и реквизитам
	 *            регистра. Имя ключа структуры должно совпадать с именем
	 *            измерения регистра, заданного в конфигураторе, а значение
	 *            элемента структуры - задает отбираемое по данному измерению
	 *            значение. Если параметр не указан, то отбор не используется.
	 * @return Таблица значений, заполненная данными найденных записей регистра
	 *         сведений.
	 * @throws JIException
	 */
	public OCValueTable getSliceFirst(Date startDate, OCStructure structure) throws JIException {
		JIVariant var = callMethodA("SliceFirst", new Object[]{
				startDate != null ? new JIVariant(startDate) : null,
				structure != null ? new JIVariant(ocObject2Dispatch(structure)) : null
		})[0];
		OCValueTable vt = null;
		
		if (var.getType() != JIVariant.VT_EMPTY) {
			vt = new OCValueTable(var);
		}
		return vt;
	}
	
	/**
	 * Получает наиболее поздние записи регистра, соответствующие установленным
	 * в параметрах метода значениям ключевых полей. Записи подбираются для
	 * каждого сочетания из всех имеющихся значений измерений регистра.
	 * Примечание: Применим только для периодических регистров сведений.
	 * 
	 * @param endDate
	 *            Определяет момент времени, заканчивая которым необходимо
	 *            выбрать записи. Если параметр не указан, то будут возвращены
	 *            значения ресурсов самой последней записи регистра.
	 * @param structure
	 *            Структура, содержащая отбор по измерениям и реквизитам
	 *            регистра. Имя ключа структуры должно совпадать с именем
	 *            измерения регистра, заданного в конфигураторе, а значение
	 *            элемента структуры - задает отбираемое по данному измерению
	 *            значение. Если параметр не указан, то отбор не используется.
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable getSliceLast(Date endDate, OCStructure structure) throws JIException {
		JIVariant var = callMethodA("SliceLast", new Object[]{
				endDate != null ? new JIVariant(endDate) : null,
				structure != null ? new JIVariant(ocObject2Dispatch(structure)) : null
		})[0];
		OCValueTable vt = null;
		
		if (var.getType() != JIVariant.VT_EMPTY) {
			vt = new OCValueTable(var);
		}
		return vt;
	}
}
