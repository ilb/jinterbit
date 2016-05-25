package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCChartsOfAccountCollection extends OCObject {

	public OCChartsOfAccountCollection(OCObject object) {
		super(object);
	}

	public OCChartsOfAccountCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartsOfAccountCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Предназначен для управления планами счетов и предоставляет доступ к значениям типа ПланСчетовМенеджер.<Имя плана счетов>. Доступ к объекту осуществляется через свойство глобального контекста ПланыСчетов.
	 * @param chartsOfAccountName
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsManager getChartsOfAccount(String chartsOfAccountName) throws JIException{
		OCChartOfAccountsManager manager = new OCChartOfAccountsManager(get(chartsOfAccountName));
		manager.setManagerName(chartsOfAccountName);
		return manager;
	}
}
