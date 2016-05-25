/**
 * 
 */
package com.ipc.oce;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.ipc.oce.exceptions.ConfigurationException;

/**
 * Класс для считывания стандартного формата конфигурации.
 * 
 * @author Konovalov Пример конфигурации<br>
 *         oce.inst01.driver = V81Driver<br>
 *         oce.inst01.host = localhost<br>
 *         oce.inst01.host.user = user<br>
 *         oce.inst01.host.password = password<br>
 *         oce.inst01.1c.dbpath = some_db_path<br>
 *         oce.inst01.1c.user = 1cUser<br>
 *         oce.inst01.1c.password = 1cPassword<br>
 */
public class PropertiesReader implements ConfigurationConstants {
	
	/**
	 * Константа "точка".
	 */
	private static final char DOT = '.';

	// different parts of cfg's names
	private static final String _OCE_CFG_PREF = "oce"; // преффикс имен конфигурации
	private static final String _OCE_CFG_DRIVER = "driver";
	private static final String _OCE_CFG_HOST = "host";
	private static final String _OCE_CFG_DOMAIN = "domain";
	private static final String _OCE_CFG_HOST_USER = "host.user";
	private static final String _OCE_CFG_HOST_PASSWORD = "host.password";
	private static final String _OCE_CFG_1CDB_PATH = "1c.dbpath";
	private static final String _OCE_CFG_1CDB_USER = "1c.user";
	private static final String _OCE_CFG_1CDB_PASSWORD = "1c.password";
	private static final String _OCE_CFG_1CDB_SRVR = "1c.srvr";
	private static final String _OCE_CFG_1CDB_REF = "1c.ref";
	
	/**
	 * Оригинальные Properties.
	 */
	private Properties allProperties = null;
	
	/**
	 * Массив имен конфигурация зарегистрированных в файле.
	 */
	private String[] instNames = null;

	/**
	 * @param file
	 *            - путь до файла с настройками стандартного формата
	 * @throws IOException
	 *             - ошибка чтения файла
	 * 
	 */
	public PropertiesReader(final File file) throws IOException {
		allProperties = readProperties(new FileReader(file));
	}

	public PropertiesReader(final Reader reader) throws IOException {
		allProperties = readProperties(reader);
	}
	
	public PropertiesReader(final InputStream stream) throws IOException {
		allProperties = readProperties(stream);
	}

	protected Properties readProperties(Reader reader) throws IOException {
		Properties prp = new Properties();
		prp.load(reader);
		return prp;
	}
	
	protected Properties readProperties(InputStream stream) throws IOException {
		Properties prp = new Properties();
		prp.load(stream);
		return prp;
	}

	protected static String fullKeyBySuff(String suff) {
		StringBuffer sb = new StringBuffer();
		sb.append(_OCE_CFG_PREF).append(DOT).append(suff);
		return sb.toString();
	}

	protected static String fullKeyBySuff(String suff, String instanceName) {
		StringBuffer sb = new StringBuffer();
		sb.append(_OCE_CFG_PREF).append(DOT).append(instanceName).append(DOT)
				.append(suff);
		return sb.toString();
	}

	/**
	 * Получение настроек для конкретного имени конфигурации.
	 * 
	 * @param instanceName
	 *            имя конфигурации (в файле настроек это второй уровень имени -
	 *            oce.<cfg-name>.что-то)
	 * @return Properties
	 * @throws ConfigurationException - ошибка в параметрах конфигурации
	 */
	public final Properties getPropertiesForInstance(final String instanceName)
			throws ConfigurationException {
		// check conf name ------------
		List<String> allCfgs = Arrays.asList(getConfigurationNames());
		if (!allCfgs.contains(instanceName)) {
			throw new ConfigurationException("Configuration '" + instanceName + "' not found. Available cfgs: " + allCfgs.toString());
		}
		//-----------------------------
		Properties tmp = new Properties();
		String[] prefs = new String[] { _OCE_CFG_DRIVER, _OCE_CFG_HOST,
				_OCE_CFG_HOST_USER, _OCE_CFG_HOST_PASSWORD, _OCE_CFG_1CDB_PATH,
				_OCE_CFG_1CDB_USER, _OCE_CFG_1CDB_PASSWORD, _OCE_CFG_1CDB_SRVR,
				_OCE_CFG_1CDB_REF, _OCE_CFG_DOMAIN };
		for (String string : prefs) {
			String aPrpValue = (String) allProperties.get(fullKeyBySuff(string,
					instanceName));
			if (aPrpValue == null) {
				continue;
			}
			// throw new
			// ConfigurationException("The properties '"+fullKeyBySuff(string,
			// instanceName)+"' not found");
			tmp.put(fullKeyBySuff(string), aPrpValue.trim());
		}

		return tmp;
	}

	/**
	 * Получение имен конфигураций. (Второй параметр - oce.<cfg-name>. - в файле
	 * настроек)
	 * 
	 * @return массив имен конфигураций
	 */
	public final String[] getConfigurationNames() {
		if (instNames != null) {
			return instNames;
		}
		List<String> list = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> keys = (Enumeration<String>) allProperties
				.propertyNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String[] split = key.split("\\.");

			if (split == null || split.length < 2) {
				continue;
			}
			if (split[0].equals(_OCE_CFG_PREF) && !(list.contains(split[1]))) {
				list.add(split[1]);
			}
		}
		instNames = list.toArray(new String[list.size()]);
		return instNames;
	}
}
