package com.ipc.oce.query;

import java.util.Iterator;
import java.util.Map;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;


/**
 * Предназначен для выполнения запросов к базе данных
 * @author Konovalov
 *
 */
public class OCQuery extends OCObject {
	public OCQuery(IJIDispatch aDispatch) {
		super(aDispatch);
	}
	
	
	/*public void setTextFromFile(String aFilePath) throws IOException, JIException {
		BufferedReader in = new BufferedReader(new FileReader(aFilePath));
		
		String text = "";
		String str;
		while ((str = in.readLine()) != null) {
			text += " " + str;
		}
		
		setText(text);
	}*/
	
	/**
	 * Установка исходного текста выполняемого запроса
	 */
	public void setText(String aQueryText) throws JIException {
		put("Text", new Object[]{aQueryText});
	}

	/**
	 * Содержит исходный текст выполняемого запроса
	 * @return
	 * @throws JIException
	 */
	public String getText() throws JIException {
		return get("Text").getObjectAsString2();
	}
	
	/**
	 * Содержит значения параметров, установленных запросу
	 * @return
	 * @throws JIException
	 */
	public OCStructure getParameters() throws JIException{
		return new OCStructure(get("Parameters"));
	}
	
	/**
	 * Устанавливает параметр запроса. Параметры доступны для обращения в тексте запроса. С помощью этого метода можно передавать переменные в запрос, например, для использования в условиях запроса
	 * @param paramName Имя устанавливаемого параметра. Оно должно соответствовать требованиям, предъявляемым к именованию переменных встроенного языка. 
	 * @param variant Значение устанавливаемого параметра
	 * @throws JIException
	 */
	public void setParameter(String paramName, OCVariant variant) throws JIException{
		callMethodA("SetParameter", new Object[]{new JIVariant(paramName), ocVariant2JI(variant)});
	}
	
	/**
	 * Установка множественных параметров. Используется вызов параметров setParameter
	 * @param parameters Map<String, OCVariant>
	 * @throws JIException
	 */
	public void setParameters(Map<String, OCVariant> parameters) throws JIException{
		for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			setParameter(key, parameters.get(key));
		}
	}
	
	/**
	 * Выполняет запрос к базе данных. В случае, если запросу установлен пакетный запрос, метод последовательно выполнит все запросы из пакета и вернет результат последнего запроса пакета, который не создает и не уничтожает временную таблицу. Если такого запроса нет, то будет возвращен результат исполнения последнего запроса
	 * @return
	 * @throws JIException
	 */
	public OCQueryResult execute() throws JIException {
		return new OCQueryResult(callMethodA("Execute"));
	}
	
	/**
	 * Последовательно выполняет все запросы и возвращает массив результатов для каждого запроса из пакета. Результаты помещаются в массив в последовательности расположения запросов в тексте пакета. Результатом выполнения запроса на уничтожение временной таблицы является значение Неопределено, которое также помещается в массив результатов. 
	 * @return OCArray
	 * @throws JIException
	 */
	public OCBatchResults executeBatch() throws JIException{
		return new OCBatchResults(callMethodA("ExecuteBatch"));
	}
	
	/**
	 * Осуществляет поиск параметров, которые присутствуют в тексте запроса
	 * @return OCQueryParametersDescription
	 * @throws JIException
	 */
	public OCQueryParametersDescription findParameters() throws JIException{
		return new OCQueryParametersDescription(callMethodA("FindParameters"));
	}
}
