/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Представляет собой значение реквизита НаборУзлов объекта типа ПараметрыОбменаДанными, который, в свою очередь является значением свойства ОбменДанными, объектов, представляющих данные, по которым ведется учет изменений. 
 * @author Konovalov
 *
 */
public class OCNodeSet extends OCObject {

	/**
	 * @param object
	 */
	public OCNodeSet(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCNodeSet(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCNodeSet(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Если значение свойства Истина, то метод Заполнить будет вызван автоматически перед обращением к обработчику ПередЗаписью() объекта данных, которому принадлежит объект ПараметрыОбменаДанными, которому, в свою очередь, принадлежит данный объект. Если значение свойства АвтоЗаполнение Ложь, то никаких действий предприниматься не будет. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isAutoFill() throws JIException{
		return get("AutoFill").getObjectAsBoolean();
	}
	
	/**
	 * Если значение свойства Истина, то метод Заполнить будет вызван автоматически перед обращением к обработчику ПередЗаписью() объекта данных, которому принадлежит объект ПараметрыОбменаДанными, которому, в свою очередь, принадлежит данный объект. Если значение свойства АвтоЗаполнение Ложь, то никаких действий предприниматься не будет. 
	 * @param autoFillFactor
	 * @throws JIException
	 */
	public void setAutoFill(Boolean autoFillFactor) throws JIException{
		put("AutoFill", new JIVariant(autoFillFactor));
	}
	
	/**
	 * Добавляет узел к набору узлов. Если узел уже входит в набор, то он не будет добавлен. 
	 * @param planRef
	 * @throws JIException
	 */
	public void add(OCExchangePlanRef planRef) throws JIException{
		callMethod("Add", new Object[]{new JIVariant(planRef.dispatch())});
	}
	
	/**
	 * Предназначен для заполнения набора узлов. Объект НаборУзлов принадлежит определенному объекту ПараметрыОбменаДанными, принадлежащий определенному объекту данных, который, в свою очередь, относится к объекту метаданных, входящему в состав хотя бы одного плана обмена. Обращение к данному методу вызывает заполнение набора узлов ссылками на все узлы всех планов обмена, в состав которых входит объект метаданных, для которых указан признак АвтоРегистрация. 
	 * @throws JIException
	 */
	public void fill() throws JIException{
		callMethod("Fill");
	}
	
	/**
	 * Получает количество узлов в наборе узлов. 
	 * @return размер набора.
	 * @throws JIException
	 */
	public int size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Очищает набор узлов. 
	 * @throws JIException
	 */
	public void clear() throws JIException{
		callMethod("Clear");
	}
	
	/**
	 * Определяет, входит ли указанный узел плана обмена в набор узлов. 
	 * @param planRef Ссылка на узел плана обмена, который надо проверить на вхождение в набор узлов. 
	 * @return  Истина - узел входит в набор узлов; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean contains(OCExchangePlanRef planRef) throws JIException{
		return callMethodA("Contains", new Object[]{new JIVariant(planRef.dispatch())})[0].getObjectAsBoolean();
	}
	
	/**
	 * Удаляет узел из набора узлов. 
	 * @param planRef Ссылка на узел плана обмена, который надо удалить из набора узлов. 
	 * @throws JIException
	 */
	public void delete(OCExchangePlanRef planRef) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(planRef.dispatch())});
	}

}
