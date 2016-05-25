/**
 * 
 */
package com.ipc.oce.query;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;

/**
 * Класс инкапсулирует результаны batch-запроса.
 * Является унаследованным от OCArray с дополнительными методами работы именно с OCQueryResult
 * @author Konovalov
 *
 */
public class OCBatchResults extends OCArray {
	
	/**
	 * @param object
	 */
	public OCBatchResults(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCBatchResults(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCBatchResults(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получение результата запроса по индексу.
	 * @param index - индекс результата запроса
	 * @return OCQueryResult или null если значение неопределено.
	 * @throws JIException
	 */
	public OCQueryResult getResult(int index) throws JIException {
		JIVariant var = ocVariant2JI(get(index));
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCQueryResult(get(index).getAsOCObject());
		} else {
			return null;
		}
	}

}
