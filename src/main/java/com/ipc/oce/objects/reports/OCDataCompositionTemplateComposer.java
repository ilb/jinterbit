/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Объект для создания макета компоновки данных. 
 * @author Konovalov
 *
 */
public class OCDataCompositionTemplateComposer extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionTemplateComposer(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionTemplateComposer(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionTemplateComposer(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Созданный макет компоновки.
	 * @param schema - Схема, для которой требуется построить макет. 
	 * @param settings - Настройки, для которых необходимо создать макет. 
	 * @param details - Содержит переменную, в которую будут помещены данные расшифровки. Если параметр не указан, расшифровка заполняться не будет. 
	 * @return
	 * @throws JIException
	 */
	public OCDataCompositionTemplate execute(OCDataCompositionSchema schema, OCDataCompositionSettings settings, OCDataCompositionDetailsData details) throws JIException {
		return new OCDataCompositionTemplate(callMethodA("Execute", new Object[]{
				new JIVariant(ocObject2Dispatch(schema)),
				new JIVariant(ocObject2Dispatch(settings)),
				(details != null ? new JIVariant(ocObject2Dispatch(details)) : null)
		})[0]);
	}
	
	/*public enum GeneratorType {
		
	}*/

}
