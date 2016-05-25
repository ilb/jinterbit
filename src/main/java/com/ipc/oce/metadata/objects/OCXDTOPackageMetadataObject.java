/**
 * 
 */
package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.xml.oc.OCXDTOPackage;

/**
 * Используется для обращения к метаданным объекта конфигурации - пакет XDTO
 * 
 * @author Konovalov
 * 
 */
public class OCXDTOPackageMetadataObject extends _OCCommonMetadataObject {

	/**
	 * @param object
	 */
	public OCXDTOPackageMetadataObject(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTOPackageMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTOPackageMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Имя объекта метаданных. Имя должно состоять из одного слова, начинаться с
	 * буквы и не содержать специальных символов, кроме «_».
	 */
	public String getName() throws JIException {
		return super.getName();
	}

	/**
	 * Данное свойство всегда имеет значение Неопределено, т.к. во встроенном
	 * языке не предусмотрена работа со значением данного свойства. Для
	 * получения пакета можно использовать свойство глобального контекста
	 * ФабрикаXDTO и URI пространства имен пакета: Пакет =
	 * ФабрикаXDTO.Пакеты.Получить("http://www.1c.ru/demos/products");
	 * 
	 * @return OCXDTOPackage
	 * @throws JIException
	 */
	public OCXDTOPackage getPackage() throws JIException {
		return new OCXDTOPackage(get("Package"));
	}

	/**
	 * Для Web-сервиса содержит унифицированный глобальный идентификатор (URI)
	 * пространства имен веб-сервиса. Каждый Web-сервис может быть однозначно
	 * идентифицирован по своему имени и URI пространству имен, которому он
	 * принадлежит. Для пакета XDTO содержит унифицированный глобальный
	 * идентификатор (URI) пространства имен, которое определяет данный пакет
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getNamespace() throws JIException {
		return get("Namespace").getObjectAsString2();
	}

}
