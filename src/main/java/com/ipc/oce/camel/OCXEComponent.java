/**
 * 
 */
package com.ipc.oce.camel;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import com.ipc.oce.ApplicationDriver;

/**
 * OC - 1C, X - xml, E - exchange.
 * Фабрика для OCXEEndpoint. 
 * 
 * @author Konovalov
 * 
 */
public class OCXEComponent extends DefaultComponent {

	public static final String PARAM_DBPASSWORD = "dbpassword";
	public static final String PARAM_DBUSER = "dbuser";
	public static final String PARAM_DBPATH = "dbpath";
	public static final String PARAM_DBREF = "dbref";
	public static final String PARAM_DBSRVR = "dbsrvr";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_HOST = "host";

	private String host = null;
	private String login = null;
	private String password = null;

	private String dbpath = null;
	private String dbsrvr = null;
	private String dbref = null;
	private String dbuser = null;
	private String dbpassword = null;

	private ApplicationDriver driver = null;

	public OCXEComponent() {
		super();
	}

	public String getDbsrvr() {
		return dbsrvr;
	}



	public void setDbsrvr(String dbsrvr) {
		this.dbsrvr = dbsrvr;
	}



	public String getDbref() {
		return dbref;
	}



	public void setDbref(String dbref) {
		this.dbref = dbref;
	}



	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbpath() {
		return dbpath;
	}

	public void setDbpath(String dbpath) {
		this.dbpath = dbpath;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	protected String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	public void setDriver(ApplicationDriver driver) {
		this.driver = driver;
	}

	protected ApplicationDriver getDriver() {
		return driver;
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining,
			Map<String, Object> uriParameters) throws Exception {

		// propagate bean-injected parameters

		if (!(uriParameters.containsKey(PARAM_HOST)) && getHost() != null) {
			uriParameters.put(PARAM_HOST, getHost());
		}

		if (!(uriParameters.containsKey(PARAM_LOGIN)) && getLogin() != null) {
			uriParameters.put(PARAM_LOGIN, getLogin());
		}

		if (!(uriParameters.containsKey(PARAM_PASSWORD))
				&& getPassword() != null) {
			uriParameters.put(PARAM_PASSWORD, getPassword());
		}

		if (!(uriParameters.containsKey(PARAM_DBPATH)) && getDbpath() != null) {
			uriParameters.put(PARAM_DBPATH, getDbpath());
		}

		if (!(uriParameters.containsKey(PARAM_DBUSER)) && getDbuser() != null) {
			uriParameters.put(PARAM_DBUSER, getDbuser());
		}

		if (!(uriParameters.containsKey(PARAM_DBPASSWORD)) && getDbpassword() != null) {
			uriParameters.put(PARAM_DBPASSWORD, getDbpassword());
		}
		
		if (!(uriParameters.containsKey(PARAM_DBSRVR)) && getDbsrvr() != null) {
			uriParameters.put(PARAM_DBSRVR, getDbsrvr());
		}
		
		if (!(uriParameters.containsKey(PARAM_DBREF)) && getDbref() != null) {
			uriParameters.put(PARAM_DBREF, getDbref());
		}

		OCXEEndpoint endpoint = new OCXEEndpoint(uri, this);
		setProperties(endpoint, uriParameters);
		return endpoint;
	}

}
