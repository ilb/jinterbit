package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCEnumMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;


/**
 * Предназначен для получения ссылок на значения перечисления (в том числе пустой ссылки).
 * Доступ к объекту осуществляется через свойство объекта ПеречисленияМенеджер. 
 * Полное имя типа объекта определяется с учетом имени конкретного перечисления конфигурации. Например, для перечисления "Виды контрагентов" имя типа будет выглядеть Перечисления.ВидыКонтрагентов.
 * @author Konovalov
 *
 */
public class OCEnumManager extends _OCAbstractManager {

	public OCEnumManager(OCObject object) {
		super(object);
	}

	public OCEnumManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCEnumManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения перечисления. Доступ к значению осуществляется по имени, как они заданы в конфигураторе. 
	 * @param enumValueName
	 * @return
	 * @throws JIException
	 */
	public OCEnumRef getEnumValueRef(String enumValueName) throws JIException { // хрень какая-то
		return new OCEnumRef(get(enumValueName));
	}

	/**
	 * Получает значение по индексу.
	 * @param i Индекс значения перечисления в перечислении. 
	 * @return
	 * @throws JIException
	 */
	public OCEnumRef get(Integer i) throws JIException{
		return new OCEnumRef(callMethodA("Get",
				new Object[] { JIVariant.makeVariant(i) })[0]);
	}
	
	/**
	 * Получает количество перечислений. 
	 * @return
	 * @throws JIException
	 */
	public Integer count() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает пустое значение ссылки на перечисление данного вида. Может использоваться, например, когда нужно передать пустую ссылку в параметр метода.
	 * @return
	 * @throws JIException
	 */
	public OCEnumRef emptyRef() throws JIException{
		return new OCEnumRef(callMethodA("EmptyRef"));
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.objects._OCAbstractManager#loadMetadata()
	 */
	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getEnums().find(managerName);
	}

	@Override
	public OCEnumMetadataObject getMetadata() throws JIException {
		return new OCEnumMetadataObject(super.getMetadata());
	}
	
	

}
