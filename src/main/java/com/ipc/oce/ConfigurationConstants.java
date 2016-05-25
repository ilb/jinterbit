/**
 * 
 */
package com.ipc.oce;

/**
 * @author Konovalov
 *
 */
public interface ConfigurationConstants {

	// for public use constants
	/**
	 * Наименование конфигурации по умолчанию.
	 */
	String OCE_DEFAULT_INSTANCE = "default";
	/**
	 * Имя драйвера приложения. oce.driver
	 */
	String OCE_CFG_DRIVER = "oce.driver";
	/**
	 * Адрес COM-сервера. oce.host 
	 * J-Interop remark: This should be in the IEEE IP format (e.g. 192.168.170.6) or a resolvable HostName.
	 */
	String OCE_CFG_HOST = "oce.host";
	
	/**
	 * Наименование домена пользователя.
	 */
	String OCE_CFG_DOMAIN = "oce.domain";
	
	/**
	 * Пользователь COM-сервера. oce.host.user
	 */
	String OCE_CFG_HOST_USER = "oce.host.user";
	/**
	 * Пароль пользователя COM-сервера. oce.host.password
	 */
	String OCE_CFG_HOST_PASSWORD = "oce.host.password";
	/**
	 * Путь в файловой системе до базы данных 1С. oce.1c.dbpath
	 */
	String OCE_CFG_1CDB_PATH = "oce.1c.dbpath";
	/**
	 * имя сервера 1С:Предприятия.
	 */
	String OCE_CFG_1CSRVR = "oce.1c.srvr";
	/**
	 * имя информационной базы на сервере.
	 */
	String OCE_CFG_1CREF = "oce.1c.ref";
	/**
	 * Имя пользователя 1С. oce.1c.user
	 */
	String OCE_CFG_1CDB_USER = "oce.1c.user";
	/**
	 * Пароль пользователя 1С. oce.1c.password .
	 */
	String OCE_CFG_1CDB_PASSWORD = "oce.1c.password";
	
	

}