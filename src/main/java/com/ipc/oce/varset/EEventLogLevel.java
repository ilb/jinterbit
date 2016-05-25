/**
 * 
 */
package com.ipc.oce.varset;


/**
 * Уровни важности событий журнала регистрации.
 * @author Konovalov
 *
 */
public class EEventLogLevel extends EActivator {
	
	/**
	 * @param innerString
	 */
	public EEventLogLevel(String innerString) {
		super(innerString);
	}

	/**
	 * Регистрация информации
	 */
	public static final String INFORMATION = "EEventLogLevel.INFORMATION";
	
	/**
	 * Регистрация ошибок
	 */
	public static final String ERROR = "EEventLogLevel.ERROR";
	
	/**
	 * Регистрация предупреждений
	 */
	public static final String WARNING = "EEventLogLevel.WARNING";
	
	/**
	 * Регистрация примечаний
	 */
	public static final String NOTE = "EEventLogLevel.NOTE";

	

}
