/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * Предназначен для работы со списком пользователей информационной базы.
 * 
 * @author Konovalov
 * 
 */
public class OCInfoBaseUsersManager extends OCObject {

	/**
	 * @param aDispatch
	 */
	public OCInfoBaseUsersManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCInfoBaseUsersManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * @param object
	 */
	public OCInfoBaseUsersManager(OCObject object) {
		super(object);
	}
	
	/**
	 * Осуществляет поиск информации о пользователе с указанным именем.
	 * Примечание: В случае, если пользователь имеет административные права, то
	 * допускается поиск любого пользователя. Если пользователь не имеет
	 * административных прав, то допускается поиск только того пользователя, под
	 * которым данный пользователь авторизовался. Если указано пустое имя
	 * пользователя, то возвращается объект, который можно использовать в тех
	 * случаях, когда пользователь не задан (например, в фильтре журнала
	 * регистрации)
	 * 
	 * @param userName
	 *            имя пользователя
	 * @return ПользовательИнформационнойБазы; Неопределено. Если значение не
	 *         найдено, то для пользователя с административными правами
	 *         возвращается значение Неопределено, для других пользователей
	 *         вызывается исключение.
	 * @throws JIException
	 */
	public OCInfoBaseUser findByName(String userName) throws JIException {
		return new OCInfoBaseUser(callMethodA("FindByName", new Object[]{new JIVariant(userName)})[0]);
	}
	
	/**
	 * Получает массив, элементами которого являются объекты
	 * ПользовательИнформационнойБазы.
	 * 
	 * @return OCArray
	 * @throws JIException
	 */
	public OCUserArray getUsers() throws JIException {
		return new OCUserArray(callMethodA("GetUsers"));
	}
	
	/**
	 * Получает описание текущего пользователя информационной базы.
	 * 
	 * @return ПользовательИнформационнойБазы. Если текущий пользователь не
	 *         задан, то в качестве текущего пользователя будет возвращен
	 *         пользователь с пустым именем и идентификатором объекта,
	 *         полученного методом НайтиПоИмени, если в параметре указано пустое
	 *         имя. Внимание! Возвращает ту информацию о пользователе, которая
	 *         была на момент начала сеанса.
	 * @throws JIException
	 */
	public OCInfoBaseUser getCurrentUser() throws JIException {
		return new OCInfoBaseUser(callMethodA("CurrentUser"));
	}
	
	/**
	 * Осуществляет поиск информации о пользователе с указанным идентификатором.
	 * В случае, если пользователь имеет административные права, то допускается
	 * поиск любого пользователя. Если пользователь не имеет административных
	 * прав, то по уникальному идентификатору допускается поиск только того
	 * пользователя, под которым данный пользователь авторизовался
	 * 
	 * @param uuid
	 *            Уникальный идентификатор пользователя
	 * @return ПользовательИнформационнойБазы; Неопределено. Если значение не
	 *         найдено, то для пользователя с административными правами
	 *         возвращается значение Неопределено, для других пользователей
	 *         вызывается исключение.
	 * @throws JIException
	 */
	public OCInfoBaseUser findByUUID(OCUUID uuid) throws JIException {
		return new OCInfoBaseUser(callMethodA("FindByUUID", new Object[]{uuid.dispatch()})[0]);
	}
	
	/**
	 * Создает описание нового пользователя информационной базы. Для внесения
	 * пользователя в список следует после заполнения свойств нового
	 * пользователя использовать метод Записать объекта
	 * ПользовательИнформационнойБазы.
	 * 
	 * @return OCInfoBaseUser
	 * @throws JIException
	 */
	public OCInfoBaseUser createUser() throws JIException {
		return new OCInfoBaseUser(callMethodA("CreateUser"));
	}

}
