/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Предназначен для организации последовательной записи документов и фрагментов
 * XML.
 * 
 * @author Konovalov
 * 
 */
public class OCXMLWriter extends OCObject {

	/**
	 * @param aDispatch
	 */
	public OCXMLWriter(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * Текущий контекст пространств имен XML
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCXMLNamespaceContext getNamespaceContext() throws JIException {
		return new OCXMLNamespaceContext(get("NamespaceContext"));
	}

	/**
	 * Инициализирует объект для вывода результирующего XML в строку.
	 * 
	 * @throws JIException
	 */
	public void setString() throws JIException {
		callMethod("SetString");
	}

	/**
	 * Инициализирует объект для вывода результирующего XML в строку.
	 * 
	 * @param encoding
	 *            В качестве типа кодировки может быть указано имя одной из
	 *            возможных кодировок. Это никак не влияет на формируемую
	 *            строку. Но если будет записано объявление XML-документа, то
	 *            объявление будет содержать атрибут 'encoding', имеющий
	 *            соответствующее значение. Если же в качестве параметра указана
	 *            пустая строка или ничего не указано, то атрибут 'encoding' не
	 *            будет присутствовать в объявлении XML-документа.
	 * @throws JIException
	 */
	public void setString(String encoding) throws JIException {
		callMethod("SetString", new Object[] { new JIVariant(encoding) });
	}

	/**
	 * Завершает запись текста XML. Если производилась запись в файл, то файл
	 * закрывается. Если производилась запись в строку, то результирующая строка
	 * будет получена в качестве возвращаемого значения метода. Если
	 * производилась запись в файл, то метод вернет пустую строку.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String close() throws JIException {
		return callMethodA("Close").getObjectAsString2();
	}

	/**
	 * Записывает объявление XML. Если формируется XML-документ, а не фрагмент
	 * XML-документа, то в начало документа надо поместить его объявление. Для
	 * того, чтобы поместить в документ его объявление, необходимо вызвать
	 * данный метод. При этом в начало документа помещается текст следующего
	 * вида: <?xml version="1.0"?>. Если при открытии файла или установки строки
	 * была указана кодировка, то в объявление также помещается и атрибут
	 * 'encoding'. Например: <?xml version="1.0" encoding="UTF-8"?> Запись
	 * объявления XML должна выполняться перед другими операциями записи XML.
	 * 
	 * @throws JIException
	 */
	public void writeXMLDeclaration() throws JIException {
		callMethod("WriteXMLDeclaration");
	}

	/**
	 * Показывает, будут ли в результирующем тексте делаться отступы для показа
	 * структуры вложенности элементов XML. Установка данного свойства не имеет
	 * немедленного эффекта.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Boolean getIndent() throws JIException {
		return get("Indent").getObjectAsBoolean();
	}

	/**
	 * Установленное значение свойства будет использовано только после открытия
	 * файла или установки строки. После создания объекта данное свойство имеет
	 * значение Истина.
	 * 
	 * @param indend
	 * @throws JIException
	 */
	public void setIndend(Boolean indend) throws JIException {
		put("Indent", new JIVariant(indend));
	}

	/**
	 * Открывает файл для записи XML. Позволяет указать тип кодировки, который
	 * будет использован для записи файла XML
	 * 
	 * @param filePath
	 *            - Имя файла, в который будет записываться текст XML
	 * @param encoding
	 *            - В качестве типа кодировки может быть указано одна из
	 *            возможных кодировок текста. В этом случае файл будет записан в
	 *            соответствующей кодировке. При этом, в объявлении
	 *            XML-документа (если таковое будет записано) будет
	 *            присутствовать атрибут 'encoding', имеющий соответствующее
	 *            значение. Если же в качестве параметра указана пустая строка
	 *            или ничего не указано, то для записи файла будет использована
	 *            кодировка UTF-8, а атрибут 'encoding' не будет присутствовать
	 *            в объявлении XML-документа.
	 * @throws JIException
	 */
	public void openFile(String filePath, String encoding) throws JIException {
		callMethod("OpenFile", new Object[] { new JIVariant(filePath),
				new JIVariant(encoding) });
	}
}
