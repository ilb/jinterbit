/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCDataCompositionSettingsComposer extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionSettingsComposer(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSettingsComposer(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSettingsComposer(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Восстанавливает работоспособность настроек. В случае если был изменен
	 * источник доступных настроек, часть полей, использованных в настройках,
	 * может потерять связь с доступными полями. При использовании таких
	 * настроек в компоновке данных произойдет ошибка. Вызов метода: снимает
	 * признак использования у всех элементов настроек, потерявших связь с
	 * доступными полями; в случае если при работе метода у всех полей
	 * оформляемой области снят признак использования, то признак использования
	 * отключается и у самого элемента оформления;
	 * 
	 * в случае если при работе метода у элемента отбора, находящегося в группе,
	 * снимается признак использования, и в группе отсутствуют элементы с
	 * установленным признаком использования, флаг "Использование" снимается у
	 * всей группы.
	 * 
	 * И, таким образом, работоспособность настроек восстанавливается.
	 * 
	 * @throws JIException
	 */
	public void refresh() throws JIException {
		callMethod("Refresh");
	}
	
	/**
	 * Содержит настройки компоновки данных.
	 * @return OCDataCompositionSettings
	 * @throws JIException
	 */
	public OCDataCompositionSettings getSettings() throws JIException {
		return new OCDataCompositionSettings(get("Settings"));
	}
	
	/**
	 * Позволяет получить копию настроек компоновки данных. 
	 * @return OCDataCompositionSettings
	 * @throws JIException
	 */
	public OCDataCompositionSettings getSettingsCopy() throws JIException {
		return new OCDataCompositionSettings(callMethodA("GetSettings"));
	}

}
