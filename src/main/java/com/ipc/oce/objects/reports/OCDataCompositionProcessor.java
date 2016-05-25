/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;

/**
 * Объект, выполняющий компоновку данных.
 * @author Konovalov
 *
 */
public class OCDataCompositionProcessor extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionProcessor(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionProcessor(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionProcessor(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Встать в начало компоновки. 
	 * @throws JIException
	 */
	public void reset() throws JIException {
		callMethod("Reset");
	}
	
	/**
	 * Получает следующий элемент результата компоновки.
	 * 
	 * @return OCDataCompositionResultItem. ЭлементРезультатаКомпоновкиДанных,
	 *         Неопределено. Если все элементы результата получены, возвращает
	 *         Неопределено.
	 * @throws JIException
	 */
	public OCDataCompositionResultItem next() throws JIException {
		JIVariant var = callMethodA("Next");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCDataCompositionResultItem(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Инициализировать объект. 
	 * @param template -  Макет, для которого будет выполняться компоновка. 
	 * @param structure - Ключ структуры соответствует имени внешнего набора данных. Значение структуры - внешнему набору данных. 
	 * @param details - Объект, в котором нужно заполнить данные расшифровки. Если не указан, то расшифровка заполняться не будет.
	 * @param extFunctionAllowed - Указывает возможность использования функции общих модулей конфигурации в выражениях компоновки данных.
	 * @throws JIException
	 */
	public void initialize(OCDataCompositionTemplate template, OCStructure structure, OCDataCompositionDetailsData details, Boolean extFunctionAllowed) throws JIException {
		callMethod("Initialize", new Object[]{
				new JIVariant(ocObject2Dispatch(template)),
				(structure != null ? new JIVariant(ocObject2Dispatch(structure)) : null),
				(details != null ? new JIVariant(ocObject2Dispatch(details)) : null),
				(extFunctionAllowed != null ? new JIVariant(extFunctionAllowed) : null)
		});
	}
	
	public void initialize(OCDataCompositionTemplate template, OCStructure structure, OCDataCompositionDetailsData details) throws JIException {
		initialize(template, structure, details, null);
	}

}
