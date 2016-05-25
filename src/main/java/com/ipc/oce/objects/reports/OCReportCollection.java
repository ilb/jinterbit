/**
 * 
 */
package com.ipc.oce.objects.reports;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Предназначен для управления отчетами и предоставляет доступ к значениям типа
 * ОтчетМенеджер.<Имя отчета>. Доступ к объекту осуществляется через свойство
 * глобального контекста Отчеты.
 * 
 * @author Konovalov
 * 
 */
public class OCReportCollection extends OCObject implements Iterable<OCReportManager>{

	/**
	 * @param object
	 */
	public OCReportCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCReportCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCReportCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Используется для доступа к определенным в конфигурации отчетам. 
	 * @param reportName
	 * @return
	 * @throws JIException
	 */
	public OCReportManager getReport(String reportName) throws JIException{
		OCReportManager manager = new OCReportManager(get(reportName));
		manager.setManagerName(reportName);
		return manager;
	}

	/**
	 * Iterator supports
	 */
	public Iterator<OCReportManager> iterator() {
		EnumVARIANT<OCReportManager> enumVARIANT = new EnumVARIANT<OCReportManager>(this) {

			@Override
			protected OCReportManager castToGeneric(JIVariant variant) {
				try {
					return new OCReportManager(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
			
		};
		return enumVARIANT;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
