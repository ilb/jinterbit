/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCReportMetadataObject;
import com.ipc.oce.objects._OCAbstractManager;

/**
 * Предназначен для управления отчетом, как объектом конфигурации. С помощью
 * этого объекта можно получить отчет, работать с формами и макетами отчета.
 * Доступ к объекту осуществляется через свойство объекта ОтчетыМенеджер. Полное
 * имя типа объекта определяется с учетом имени отчета в конфигурации. Например,
 * для отчета "Продажи" имя типа будет выглядеть ОтчетыМенеджер.Продажи.
 * 
 * @author Konovalov
 * 
 */
public class OCReportManager extends _OCAbstractManager {

	/**
	 * @param aDispatch
	 */
	public OCReportManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCReportManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * @param object
	 */
	public OCReportManager(OCObject object) {
		super(object);
	}

	@Override
	protected OCReportMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getReports().find(managerName);
	}

	@Override
	public OCReportMetadataObject getMetadata() throws JIException {
		return (OCReportMetadataObject) super.getMetadata();
	}
	
	public void setManagerName(String name) {
		super.setManagerName(name);
	}

	/**
	 * Создает новый экземпляр отчета.
	 * @return
	 * @throws JIException
	 */
	public OCReportObject create() throws JIException {
		return new OCReportObject(callMethodA("Create"));
	}

}
