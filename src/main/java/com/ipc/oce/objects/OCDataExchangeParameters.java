/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Объект не может быть создан самостоятельно. Объект данного типа представляет собой значение свойства ОбменДанными объектов, представляющих данные, по которым ведется учет изменений. 
 * @author Konovalov
 *
 */
public class OCDataExchangeParameters extends OCObject {
	
	
	/**
	 * @param object
	 */
	public OCDataExchangeParameters(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataExchangeParameters(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataExchangeParameters(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Если значение данного свойства Истина, то при выполнении записи или удаления данных будет производиться минимум проверок, так как при этом делается предположение, что производится запись данных, полученных через механизмы обмена данными, и эти данные корректны. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isLoad() throws JIException{
		return get("Load").getObjectAsBoolean();
	}
	
	/**
	 * Если значение данного свойства Истина, то при выполнении записи или удаления данных будет производиться минимум проверок, так как при этом делается предположение, что производится запись данных, полученных через механизмы обмена данными, и эти данные корректны.
	 * @param loadFactor
	 * @throws JIException
	 */
	public void setLoad(Boolean loadFactor) throws JIException{
		put("Load", new JIVariant(loadFactor));
	}
	
	/**
	 * Набор узлов, для которых будут регистрироваться изменения при записи или удаления объекта данных, к которому относится объект 
	 * @return
	 * @throws JIException
	 */
	public OCNodeSet getRecipients() throws JIException{
		return new OCNodeSet(get("Recipients"));
	}
	
	/**
	 * При записи элемента данных после получения элемента данных из сообщения регистрация изменений должна быть выполнена для всех узлов планов обмена, в состав которых входит объект данных, за исключением того узла, из которого получено изменение. Для того, чтобы регистрация изменений для узла-отправителя не выполнялась, необходимо перед записью объекта данных присвоить свойству Отправитель значение ссылки на узел, из которого получено изменение. 
	 * @return
	 * @throws JIException
	 */
	public OCExchangePlanRef getSender() throws JIException{
		return new OCExchangePlanRef(get("Sender"));
	}
	
	/**
	 * При записи элемента данных после получения элемента данных из сообщения регистрация изменений должна быть выполнена для всех узлов планов обмена, в состав которых входит объект данных, за исключением того узла, из которого получено изменение. Для того, чтобы регистрация изменений для узла-отправителя не выполнялась, необходимо перед записью объекта данных присвоить свойству Отправитель значение ссылки на узел, из которого получено изменение. 
	 * @param ref
	 * @throws JIException
	 */
	public void setSender(OCExchangePlanRef ref) throws JIException{
		put("Sender", new JIVariant(ref.dispatch()) );
	}

}
