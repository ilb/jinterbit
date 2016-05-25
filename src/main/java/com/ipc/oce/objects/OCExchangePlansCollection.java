/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Предназначен для управления планами обмена и предоставляет доступ к значениям типа ПланОбменаМенеджер.<Имя плана обмена>. Доступ к объекту осуществляется через свойство глобального контекста ПланыОбмена.
 * @author Konovalov
 *
 */
public class OCExchangePlansCollection extends OCObject implements Iterable<OCExchangePlanManager>{

	public OCExchangePlansCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCExchangePlansCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCExchangePlansCollection(OCObject object) {
		super(object);
	}
	
	/**
	 * Набор свойств содержит менеджеры отдельных планов обмена. Доступ к менеджеру осуществляется по имени, как они заданы в конфигураторе. 
	 * @param exchangePlan
	 * @return
	 * @throws JIException
	 */
	public OCExchangePlanManager getExchangePlan(String exchangePlan) throws JIException{
		OCExchangePlanManager manager = new OCExchangePlanManager(get(exchangePlan));
		manager.setManagerName(exchangePlan);
		return manager;
	}
	
	/**
	 * Формирует выборку измененные данные для передачи их в тот или иной узел плана обмена. При этом в процессе выборки изменений в записи регистрации изменений проставляется номер сообщения обмена данными, в котором должны передаваться изменения. Номер сообщения в записи регистрации проставляется для того, чтобы при подтверждении приема сообщения, в котором передавались изменения соответствующие записи регистрации изменений были удалены и в дальнейшем изменения больше не передавались. 
	 * @param planRef Узел, для передачи в который отбираются изменения
	 * @param messageNumber Номер сообщения обмена данными, в который будут помещены выбранные изменения
	 * @return DataSelection
	 * @throws JIException
	 */
	public OCDataSelection selectChanges(OCExchangePlanRef planRef, Integer messageNumber) throws JIException{
		return new OCDataSelection(callMethodA("SelectChanges", new Object[]{new JIVariant(planRef.dispatch())})[0]);
	}
	
	/**
	 * Получает узел распределенной информационной базы, являющийся главным для текущей информационной базы
	 * @return ПланОбменаСсылка.<Имя плана обмена>, Неопределено. Если текущая информационная база не является узлом распределенной информационной базы или главный узел для нее не определен (она сама является корневым узлом) - метод возвращает Неопределено. 
	 * @throws JIException
	 */
	public OCExchangePlanRef getMasterNode() throws JIException{
		JIVariant var = callMethodA("MasterNode");
		if(var.getType() != JIVariant.VT_EMPTY) {
			return new OCExchangePlanRef(callMethodA("MasterNode"));
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCExchangePlanManager> iterator() {
		EnumVARIANT<OCExchangePlanManager> enumV = new EnumVARIANT<OCExchangePlanManager>(this) {
			
			@Override
			protected OCExchangePlanManager castToGeneric(JIVariant variant) {
				try {
					OCExchangePlanManager manager = new OCExchangePlanManager(variant);
					manager.setManagerName(manager.toString().split("\\.")[1]);
					return manager;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}

}
