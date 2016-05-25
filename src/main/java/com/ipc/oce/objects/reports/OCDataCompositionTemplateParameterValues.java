/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Коллекция значений параметра макета компоновки. 
 * @author Konovalov
 *
 */
public class OCDataCompositionTemplateParameterValues extends AbstractDCSList {

	/**
	 * @param object
	 */
	public OCDataCompositionTemplateParameterValues(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionTemplateParameterValues(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionTemplateParameterValues(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Добавляет элемент в заданную позицию.
	 * @param index - Расположение элемента в коллекции. 
	 * @return OCDataCompositionTemplateParameterValue
	 * @throws JIException
	 */
	@Override
	public OCDataCompositionTemplateParameterValue insert(int index) throws JIException {
		return new OCDataCompositionTemplateParameterValue(super.insert(index));
	}
	
	/**
	 * Добавляет новый элемент коллекции и возвращает его. 
	 * @return
	 * @throws JIException
	 */
	@Override
	public OCDataCompositionTemplateParameterValue add() throws JIException {
		return new OCDataCompositionTemplateParameterValue(super.add());
	}
	
	/**
	 * Получает индекс элемента в коллекции. 
	 * @param value - Элемент, индекс которого определяется
	 * @return int
	 * @throws JIException
	 */
	public int indexOf(OCDataCompositionTemplateParameterValue value) throws JIException {
		return super.indexOf(value);
	}
	
	/**
	 * Получает количество элементов в коллекции.
	 * @return
	 * @throws JIException
	 */
	@Override
	public int size() throws JIException {
		return super.size();
	}
	
	/**
	 * Осуществляет поиск элемента коллекции по имени. 
	 * @param paramName
	 * @return OCDataCompositionTemplateParameterValue. Если элемент не найден, будет возвращено значение Неопределено
	 * @throws JIException
	 */
	@Override
	public OCDataCompositionTemplateParameterValue find(String paramName) throws JIException {
		return new OCDataCompositionTemplateParameterValue(super.find(paramName));
	}
	
	/**
	 * Очищает коллекцию. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		super.clear();
	}
	
	/**
	 * Получает элемент коллекции по индексу.
	 * @param index - Расположение элемента в коллекции. 
	 * @return OCDataCompositionTemplateParameterValue
	 * @throws JIException
	 */
	public OCDataCompositionTemplateParameterValue get(int index) throws JIException {
		return new OCDataCompositionTemplateParameterValue(super.get(index));
	}
	
	/**
	 * Перемещает элемент в коллекции. 
	 * @param param - Перемещаемый элемент. 
	 * @param offset - Смещение элемента в коллекции. 
	 * @throws JIException
	 */
	public void move(OCDataCompositionTemplateParameterValue param, int offset) throws JIException {
		super.move(param, offset);
	}
	
	/**
	 * Удаляет элемент из коллекции
	 * @param param - Удаляемый элемент. 
	 * @throws JIException
	 */
	public void delete(OCDataCompositionTemplateParameterValue param) throws JIException {
		super.delete(param);
	}
}
