/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCLanguage;

/**
 * Предназначен для получения и записи сведений о пользователе в информационную
 * базу. Если текущий пользователь не обладает административными правами, он
 * может изменить только ограниченный набор сведений о себе.
 * 
 * @author Konovalov
 * 
 */
public class OCInfoBaseUser extends OCObject {

	/**
	 * @param object
	 */
	public OCInfoBaseUser(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCInfoBaseUser(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCInfoBaseUser(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Определяет, использовать ли аутентификацию Windows для данного пользователя. 
	 * @return true - использовать аутентификацию Windows
	 * @throws JIException
	 */
	public boolean isOSAuthentication() throws JIException {
		return get("OSAuthentication").getObjectAsBoolean();
	}
	
	/**
	 * Определяет, использовать ли аутентификацию Windows для данного
	 * пользователя.
	 * 
	 * @param authMarker
	 *            - Истина - аутентификация Windows разрешена; Ложь - запрещена.
	 *            Доступно только пользователю с административными правами.
	 * @throws JIException
	 */
	public void setOSAuthentication(boolean authMarker) throws JIException {
		put("OSAuthentication", new JIVariant(authMarker));
	}
	
	/**
	 * Определяет использование стандартной аутентификации
	 * (пользователь-пароль). Примечание: Доступно только пользователю с
	 * административными правами.
	 * 
	 * @return boolean
	 * @throws JIException
	 */
	public boolean isStandardAuthentication() throws JIException {
		return get("StandardAuthentication").getObjectAsBoolean();
	}
	
	/**
	 * Определяет использование стандартной аутентификации
	 * (пользователь-пароль). Примечание: Доступно только пользователю с
	 * административными правами.
	 * 
	 * @param standartAuthMarker - true\false
	 * @throws JIException
	 */
	public void setStandardAuthentication(boolean standartAuthMarker) throws JIException {
		put("StandardAuthentication", new JIVariant(standartAuthMarker));
	}

	/**
	 * Данному пользователю запрещено изменять свой пароль. Примечание: Имеет
	 * смысл только если установлено свойство АутентификацияСтандартная.
	 * Доступно только пользователю с административными правами.
	 * 
	 * @return
	 * @throws JIException
	 */
	public boolean isCannotChangePassword() throws JIException {
		return get("CannotChangePassword").getObjectAsBoolean();
	}
	
	/**
	 * Данному пользователю запрещено изменять свой пароль. Примечание: Имеет
	 * смысл только если установлено свойство АутентификацияСтандартная.
	 * Доступно только пользователю с административными правами.
	 * 
	 * @param isCannot - true\false
	 * @throws JIException
	 */
	public void setCannotChangePassword(boolean isCannot) throws JIException {
		put("CannotChangePassword", new JIVariant(isCannot));
	}
	
	/**
	 * Определяет, показывать ли данного пользователя в списке для выбора при
	 * старте системы. Примечание: Имеет смысл только если установлено свойство
	 * АутентификацияСтандартная.  Доступно только пользователю с административными
	 * правами.
	 * 
	 * @return Истина - показ данного пользователя разрешен;
	 * Ложь - запрещен.
	 * @throws JIException
	 */
	public boolean isShowInList() throws JIException {
		return get("ShowInList").getObjectAsBoolean();
	}
	
	/**
	 * Определяет, показывать ли данного пользователя в списке для выбора при
	 * старте системы. Примечание: Имеет смысл только если установлено свойство
	 * АутентификацияСтандартная. Истина - показ данного пользователя разрешен;
	 * Ложь - запрещен. Доступно только пользователю с административными
	 * правами.
	 * 
	 * @param showInList
	 *            - Истина - показ данного пользователя разрешен; Ложь -
	 *            запрещен.
	 * @throws JIException
	 */
	public void setShowInList(boolean showInList) throws JIException {
		put("ShowInList", new JIVariant(showInList));
	}
	
	/**
	 * Содержит строку, идентифицирующую пользователя Windows при установленном
	 * свойстве АутентификацияОС. Примечание: Доступно только пользователю с
	 * административными правами.
	 * 
	 * @return имя пользователя Windows
	 * @throws JIException
	 */
	public String getOSUser() throws JIException {
		return get("OSUser").getObjectAsString2();
	}

	/**
	 * Содержит строку, идентифицирующую пользователя Windows при установленном
	 * свойстве АутентификацияОС. Примечание: Доступно только пользователю с
	 * административными правами.
	 * 
	 * @param userName - имя пользователя Windows
	 * @throws JIException
	 */
	public void setOSUser(String userName) throws JIException {
		put("OSUser", new JIVariant(userName));
	}
	
	/**
	 * Уникальный идентификатор пользователя информационной базы
	 * @return OCUUID
	 * @throws JIException
	 */
	public OCUUID getUUID() throws JIException{
		return new OCUUID(get("UUID"));
	}
	
	/**
	 * Содержит имя пользователя информационной базы
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException{
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Запись доступна только пользователю с административными правами
	 * @param name
	 * @throws JIException
	 */
	public void setName(String name) throws JIException{
		put("Name", new JIVariant(name));
	}
	
	/**
	 * Пароль, используемый при стандартной аутентификации. 
	 * @param password
	 * @throws JIException
	 */
	public void setPassword(String password) throws JIException{
		put("Password", new JIVariant(password));
	}
	
	/**
	 * Показывает, установлен ли пароль у пользователя.
	 * @return Истина - пароль установлен; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isPasswordSet() throws JIException{
		return get("PasswordIsSet").getObjectAsBoolean();
	}
	
	/**
	 * Содержит полное имя пользователя информационной базы
	 * @return
	 * @throws JIException
	 */
	public String getFullName() throws JIException{
		return get("FullName").getObjectAsString2();
	}
	
	/**
	 * Установка полного имеми пользователя информационной базы
	 * @param fullName
	 * @throws JIException
	 */
	public void setFullName(String fullName) throws JIException{
		put("FullName", new JIVariant(fullName));
	}
	
	/**
	 * Содержит язык пользователя информационной базы
	 * @return OCLanguage
	 * @throws JIException
	 */
	public OCLanguage getLanguage() throws JIException {
		JIVariant var = get("Language");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCLanguage(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Содержит язык пользователя информационной базы
	 * @param lang
	 * @throws JIException
	 */
	public void setLanguage(OCLanguage lang) throws JIException {
		putRef("Language", lang);
	}
	
	/**
	 * Содержит коллекцию ролей пользователя информационной базы.
	 * @return OCUserRoles
	 * @throws JIException
	 */
	public OCUserRoles getRoles() throws JIException {
		return new OCUserRoles(get("Roles"));
	}
	
	/**
	 * Записывает сведения о пользователе.
	 * 
	 * @throws JIException
	 */
	public void write() throws JIException {
		callMethod("Write");
	}
	
	/**
	 * Удаляет сведения о пользователе. Доступен только пользователю с
	 * административными правами
	 * 
	 * @throws JIException
	 */
	public void delete() throws JIException {
		callMethod("Delete");
	}

}
