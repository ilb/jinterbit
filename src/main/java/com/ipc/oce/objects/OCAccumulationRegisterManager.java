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
import com.ipc.oce.metadata.objects.OCAccumulationRegisterMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Предназначен для управления данными конкретного регистра накопления. Позволяет получать остатки и обороты по данным регистра накопления, формировать выборки, создавать формы и макеты.
 * @author Konovalov
 *
 */
public class OCAccumulationRegisterManager extends _OCAbstractManager {

	/**
	 * 
	 * @param aDispatch
	 */
	public OCAccumulationRegisterManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccumulationRegisterManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * @param object
	 */
	public OCAccumulationRegisterManager(OCObject object) {
		super(object);
	}

	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getAccumulationRegisters().find(managerName);
	}

	@Override
	public OCAccumulationRegisterMetadataObject getMetadata() throws JIException {
		return new OCAccumulationRegisterMetadataObject(super.getMetadata());
	}
	
	/**
	 * Формирует выборку записей регистра накопления. 
	 * @return
	 * @throws JIException
	 */
	public OCAccumulationRegisterSelection select() throws JIException{
		return new OCAccumulationRegisterSelection(callMethodA("Select"));
	}
	
	/**
	 * Формирует выборку записей регистра накопления. 
	 * @param startDate Начало интервала, за который будут выбираться записи регистра накопления. Если не указан, то будут выбираться записи с самого ранней включительно. 
	 * @param endDate . Конец интервала, за который будут выбираться записи регистра накопления. Если не указан, то будут выбираться записи до самой поздней включительно
	 * @param structure  Задает поле и значение отбора открываемой выборки. Ключ структуры описывает имя поля, а значение структуры - значение отбора по этому полю. В качестве полей для отбора могут задаваться измерения или реквизиты, для которых в конфигураторе признак индексирования установлен в значение "Индексировать".  Важно! Структура может содержать только один элемент. Если параметр не указан, то отбор не используется. 
	 * @param order Может содержать слово "Возр" или "Убыв", тогда стандартный порядок будет использоваться с указанным направлением. По умолчанию "Возр". Может содержать имя поля (реквизита или измерения для которых указано индексирование) и слово "Возр" или "Убыв", тогда упорядочивание будет производится по указанному полю в указанном порядке. Значение по умолчанию: Пустая строка 
	 * @return Формирует выборку записей регистра накопления. 
	 * @throws JIException
	 */
	public OCAccumulationRegisterSelection select(Date startDate, Date endDate, OCStructure structure, String order) throws JIException{
		JIVariant jStartDate = new JIVariant(startDate);
		JIVariant jEndDate = new JIVariant(endDate);
		
		PreparedPredicateStruct wrapper = null;
		if (structure != null) {
			wrapper = new PreparedPredicateStruct(structure);
		}
		JIVariant jStruct = new JIVariant((wrapper != null ? wrapper.dispatch() : null));
		
		JIVariant jOrder = new JIVariant(order);
		return new OCAccumulationRegisterSelection(callMethodA("Select", new Object[]{jStartDate, jEndDate, jStruct, jOrder})[0]);
	}
	
	/**
	 * Формирует выборку по регистратору записей регистра накопления. 
	 * @param recorderRef Ссылка на документ, являющийся регистратором для записей регистра
	 * @return
	 * @throws JIException
	 */
	public OCAccumulationRegisterSelection selectByRecorder(OCDocumentRef recorderRef) throws JIException{
		return new OCAccumulationRegisterSelection(callMethodA("SelectByRecorder", new Object[]{recorderRef.dispatch()})[0]);
	}
	
	/**
	 * Получает обороты регистра накопления
	 * @return
	 * @throws JIException
	 */
	public OCValueTable getTurnovers() throws JIException{
		return new OCValueTable(callMethodA("Turnovers"));
	}
	
	/**
	 * Получает обороты регистра накопления за заданный период времени. Есть возможность фильтрации по значениям измерений. Возвращает таблицу значений, содержащую колонки с измерениями, указанными в параметре Измерения, и по две колонки на каждый ресурс, указанный в параметре <Ресурсы>. 
	 * Названия колонок для ресурсов формируются следующим образом: для регистров остатков: "<Имя ресурса>Приход" и "<Имя ресурса>Расход" или "<ИдентификаторРесурса>Receipt" и "<ИдентификаторРесурса>Expense"; для регистров оборотов: "<Имя ресурса>". 
	 * @param startDate  Момент времени, начиная с которого необходимо получить обороты. 
	 * @param endDate Момент времени, определяющий конец периода за который необходимо получить обороты.
	 * @param structure Структура, содержащая набор значений измерений регистра, по которым надо отбирать обороты. Имя ключа структуры должно совпадать с именем измерения регистра, заданного в конфигураторе, а значение элемента структуры - задает отбираемое по данному измерению значение. Если параметр не указан, то отбор не используется. 
	 * @param dimensions Список измерений, для которых надо разворачивать обороты. Строка, содержащая имена измерений, разделенные запятыми. Если параметр не указан или указана пустая строка, то обороты будут сформированы по всем измерениям.
	 * @param resources Список ресурсов, для которых надо получить обороты. Строка, содержащая имена ресурсов, разделенные запятыми. Если параметр не указан или указана пустая строка, то обороты будут сформированы по всем ресурсам
	 * @return
	 * @throws JIException 
	 */
	public OCValueTable getTurnovers(Date startDate, Date endDate, OCStructure structure, String dimensions, String resources) throws JIException{
		JIVariant jStartDate = new JIVariant(startDate);
		JIVariant jEndDate = new JIVariant(endDate);

		PreparedPredicateStruct wrapper = null;
		if (structure != null) {
			wrapper = new PreparedPredicateStruct(structure);
		}
		JIVariant jStruct = new JIVariant((wrapper != null ? wrapper.dispatch() : null));
		
		JIVariant jDimensions = new JIVariant(dimensions);
		JIVariant jResources = new JIVariant(resources);
		
		return new OCValueTable(callMethodA("Turnovers", new Object[]{jStartDate, jEndDate, jStruct, jDimensions, jResources})[0]);
	}
	
	/**
	 * Получает остатки регистра накопления на заданный момент времени. Есть возможность фильтрации по значениям измерений, а также получения остатков в разрезе других измерений. Возвращает таблицу значений, содержащую колонки с измерениями, указанными в параметре Измерения, и колонки с ресурсами, указанными в параметре <Ресурсы>. 
	 * @param date  Момент времени, на который необходимо получить остатки. Если параметр не указан, то будут получены текущие остатки (на максимальную дату движений регистра). Если в качестве параметра передана Дата, то остатки будут получены на начало дня
	 * @param structure Структура, содержащая набор значений для измерений регистра. Имя ключа структуры должно совпадать с именем измерения регистра, заданного в конфигураторе, а значение элемента структуры - задает отбираемое по данному измерению значение.
	 * @param dimensions Список измерений, для которых надо получить остатки. Строка, содержащая имена измерений, разделенные запятыми. Если параметр не указан или указана пустая строка, то остатки будут сформированы по всем измерениям
	 * @param resources Список ресурсов, для которых надо получить остатки. Строка, содержащая имена ресурсов, разделенные запятыми. Если параметр не указан или указана пустая строка, то остатки будут сформированы по всем ресурсам
	 * @return
	 * @throws JIException
	 */
	public OCValueTable getBalance(Date date, OCStructure structure, String dimensions, String resources) throws JIException {
		JIVariant jDate = new JIVariant(date);
		PreparedPredicateStruct wrapper = null;
		if (structure != null) {
			wrapper = new PreparedPredicateStruct(structure);
		}
		JIVariant jStruct = new JIVariant((wrapper != null ? wrapper.dispatch() : null));
		JIVariant jDimensions = new JIVariant(dimensions);
		JIVariant jResources = new JIVariant(resources);
		
		return new OCValueTable(callMethodA("Balance", new Object[]{jDate, jStruct, jDimensions, jResources})[0]);
	}
	
	/**
	 * Выполняет полный пересчет итогов регистра накопления
	 * @throws JIException
	 */
	public void recalcTotals() throws JIException{
		callMethod("RecalcTotals");
	}
	
	/**
	 * Выполняет пересчет итогов регистра накопления за период
	 * @param startDate Начало периода пересчета. Если параметр не задан, то пересчет выполняется с самого начала
	 * @param endDate Конец периода пересчета. Если параметр не задан, то пересчет выполняется до самого конца
	 * @throws JIException
	 */
	public void recalcTotalsForPeriod(Date startDate, Date endDate) throws JIException {
		JIVariant jStartDate = new JIVariant(startDate);
		JIVariant jEndDate = new JIVariant(endDate);
		callMethod("RecalcTotalsForPeriod", new Object[]{jStartDate, jEndDate});
	}
	
	/**
	 * Выполняет пересчет текущих итогов регистра накопления. 
	 * Примечание: Используется только для регистра остатков. 
	 * @throws JIException
	 */
	public void recalcPresentTotals() throws JIException {
		callMethod("RecalcPresentTotals");
	}
	
	/**
	 * Получает признак использования итогов. Если использование итогов отключено, то при записи набора записей регистра не будет производиться пересчет итогов, но при этом будут не доступны виртуальные таблицы расчета остатков и оборотов.
	 * Данный режим работы регистра позволяет повысить скорость записи набора записей регистра. Он может быть полезен при массовых загрузках данных. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isTotalsUsing() throws JIException{
		return callMethodA("GetTotalsUsing").getObjectAsBoolean();
	}
	
	/**
	 * Получает флаг использования текущих итогов. Примечание: Используется только для регистра остатков
	 * @return
	 * @throws JIException
	 */
	public Boolean isPresentTotalsUsing() throws JIException{
		return callMethodA("GetPresentTotalsUsing").getObjectAsBoolean();
	}
	
	/**
	 * Получает период рассчитанных итогов. Примечание: Имеет смысл только для регистров остатков.
	 * @return
	 * @throws JIException
	 */
	public Date getTotalsPeriod() throws JIException{
		return callMethodA("GetTotalsPeriod").getObjectAsDate();
	}
	
	/**
	 * Получает флаг включенности механизма разделителя итогов
	 * @return
	 * @throws JIException
	 */
	public Boolean isTotalsSplittingMode() throws JIException{
		return callMethodA("GetTotalsSplittingMode").getObjectAsBoolean();
	}
}
