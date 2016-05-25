/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.Transaction;
import java.util.concurrent.Executor;

/**
 * @author Konovalov
 *
 */
public class OCEConnection implements Connection {
	
	//private static final transient Log LOG = LogFactory.getLog(OCEConnection.class);
	
	private static final String METHOD_NOT_APPLICABLE = "Method not applicable";
	
	private OCApp application = null;
	private boolean closed = false;
	private boolean autocommit = true;
	private Transaction transaction = null;
	private Properties clientInfo = new Properties();
	
	private String connectionURL = null;
	private Properties connectionProperties = null;
	private Driver driver = null;
	
	private Timer connectionTimer = null;
	private TimerTask pingTask = null;
	
	protected OCEConnection(OCApp application, String url, Properties conProps) throws SQLException {
		super();
		connectionURL = url;
		connectionProperties = conProps;
		this.application = application;
		connectionTimer = new Timer(true);
		pingTask = new ConnectionPing(this);
		connectionTimer.schedule(pingTask, 5000, 5000);

		try {
			setTransaction(application.createTransaction());
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}
	
	

	protected final void setDriver(Driver driver) {
		this.driver = driver;
	}

	protected Transaction getTransaction() {
		return transaction;
	}

	protected void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	protected final OCApp getApplication() {
		return application;
	}

	protected final String getConnectionURL() {
		return connectionURL;
	}

	protected final Properties getConnectionProperties() {
		return connectionProperties;
	}

	protected final Driver getDriver() {
		return driver;
	}

	@SuppressWarnings("unchecked")
	
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		if (paramClass.equals(OCApp.class)) {
			return (T) application;
		} else {
			return null;
		}
	}

	
	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		return (paramClass.equals(OCApp.class));
	}

	
	public Statement createStatement() throws SQLException {
		return new OCEStatement(this);
	}

	
	public PreparedStatement prepareStatement(String paramString) throws SQLException {
		return new OCEPreparedStatement(this, paramString);
	}

	
	public CallableStatement prepareCall(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public String nativeSQL(String paramString) throws SQLException {
		return paramString;
	}
	
	public void setAutoCommit(boolean paramBoolean) throws SQLException {
		if (!paramBoolean) {
			try {
				getTransaction().begin();
			} catch (JIException e) {
				throw new SQLException(e);
			}
		} else {
			try {
				getTransaction().commit();
			} catch (JIException e) {
				throw new SQLException(e);
			}
		}
		autocommit = paramBoolean;
	}

	
	public boolean getAutoCommit() throws SQLException {
		return autocommit;
	}

	
	public void commit() throws SQLException {
		try {
			getTransaction().commit();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void rollback() throws SQLException {
		try {
			getTransaction().rollback();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void close() throws SQLException {
		try {
			if (!closed) {
				application.exit();
			}
			closed = true;
		} catch (JIException e) {
			closed = isClosed();
			throw new SQLException(e);
		}
	}

	
	public boolean isClosed() throws SQLException {
		boolean ping = application.ping();
		if (!closed && !ping) { // не закрыто, но пинга нет
			
			// пинша нет -> закрывает + флаги 
			try {
				application.exit();
			} catch (JIException e) {
				e.printStackTrace();
			}
			closed = true;
			return closed;
		}
		return closed;
	}

	
	public DatabaseMetaData getMetaData() throws SQLException {
		return new OCEDatabaseMetaData(this);
	}

	public void setReadOnly(boolean paramBoolean) throws SQLException {
		// NOP
	}

	
	public boolean isReadOnly() throws SQLException {
		
		return false;
	}

	
	public void setCatalog(String paramString) throws SQLException {
		//throw new SQLException(METHOD_NOT_APPLICABLE);
		// NOP
	}

	
	public String getCatalog() throws SQLException {
		return null; // fix by jdbc spec 2011.01.17
	}


	
	public void setTransactionIsolation(int paramInt) throws SQLException {
		if (paramInt != TRANSACTION_READ_COMMITTED) {
			throw new SQLException(METHOD_NOT_APPLICABLE 
					+ ". Always Connection.TRANSACTION_READ_COMMITTED");
		}
		// FIX for Cognos wizard - don't worry
		//LOG.warn(METHOD_NOT_APPLICABLE+". Always Connection.TRANSACTION_READ_COMMITTED");
		
	}

	
	public int getTransactionIsolation() throws SQLException {
		return Connection.TRANSACTION_READ_COMMITTED;
	}

	
	public SQLWarning getWarnings() throws SQLException {
		
		return null;
	}

	
	public void clearWarnings() throws SQLException {
		//NOP
	}

	
	public Statement createStatement(int paramInt1, int paramInt2) throws SQLException {
		if (paramInt1 == ResultSet.TYPE_FORWARD_ONLY && paramInt2 == ResultSet.CONCUR_READ_ONLY) {
			return createStatement();
		}
		throw new SQLException("Only ResultSet.TYPE_FORWARD_ONLY and ResultSet.CONCUR_READ_ONLY supported");
	}

	public PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2) throws SQLException {
		if (paramInt1 == ResultSet.TYPE_FORWARD_ONLY && paramInt2 == ResultSet.CONCUR_READ_ONLY) {
			return prepareStatement(paramString);
		}
		throw new SQLException("PreparedStatement failed. Only ResultSet.TYPE_FORWARD_ONLY and ResultSet.CONCUR_READ_ONLY supported");
	}

	
	public CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return new HashMap<String, Class<?>>(); // like in db2 UWConnection ((
	}
	
	public void setTypeMap(Map<String, Class<?>> paramMap) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setHoldability(int paramInt) throws SQLException {
		if (paramInt != ResultSet.HOLD_CURSORS_OVER_COMMIT) {
			throw new SQLException("Only ResultSet.HOLD_CURSORS_OVER_COMMIT supported");
		}

	}

	
	public int getHoldability() throws SQLException {
		
		return ResultSet.HOLD_CURSORS_OVER_COMMIT;
	}

	
	public Savepoint setSavepoint() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE
				+ ". Savepoints not supported");
	}

	
	public Savepoint setSavepoint(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE
				+ ". Savepoints not supported");
	}

	
	public void rollback(Savepoint paramSavepoint) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE
				+ ". Savepoints not supported");
	}

	
	public void releaseSavepoint(Savepoint paramSavepoint) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE + ". Savepoints not supported");
	}

	
	public Statement createStatement(int paramInt1, int paramInt2, int paramInt3) throws SQLException {
		if (paramInt1 == ResultSet.TYPE_FORWARD_ONLY 
				&& paramInt2 == ResultSet.CONCUR_READ_ONLY 
				&& paramInt3 == ResultSet.HOLD_CURSORS_OVER_COMMIT) {
			return createStatement();
		}
		throw new SQLException("ResultSet.TYPE_FORWARD_ONLY + ResultSet.CONCUR_READ_ONLY + ResultSet.HOLD_CURSORS_OVER_COMMIT supported only");
	}

	public PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2, int paramInt3) throws SQLException {
		return prepareStatement(paramString, paramInt1, paramInt2);
	}

	
	public CallableStatement prepareCall(String paramString, int paramInt1,	int paramInt2, int paramInt3) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}
	
	public PreparedStatement prepareStatement(String paramString, int paramInt) throws SQLException {
		if (paramInt != Statement.NO_GENERATED_KEYS) {
			throw new SQLException(
					"Can't created prepared statement with RETURN_GENERATED_KEYS flag");
		}
		return prepareStatement(paramString);
	}

	public PreparedStatement prepareStatement(String paramString, int[] paramArrayOfInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public PreparedStatement prepareStatement(String paramString, String[] paramArrayOfString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}
	
	public Clob createClob() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public Blob createBlob() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public NClob createNClob() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public SQLXML createSQLXML() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public boolean isValid(int paramInt) throws SQLException {
		if (!application.ping()) {
			try {
				application.reconnect();
			} catch (Exception e) {
				throw new SQLException("Reconnect failed: " + e.getMessage());
			}
		}
		return application.ping();
	}

	
	
	public void setClientInfo(String paramString1, String paramString2) throws SQLClientInfoException {
		clientInfo.put(paramString1, paramString2);
	}

	
	public void setClientInfo(Properties paramProperties) throws SQLClientInfoException {
		clientInfo.clear();
		clientInfo.putAll(paramProperties);
	}

	
	public String getClientInfo(String paramString) throws SQLException {
		return clientInfo.getProperty(paramString);
	}

	
	public Properties getClientInfo() throws SQLException {
		return (Properties) clientInfo.clone();
	}

	
	public Array createArrayOf(String paramString, Object[] paramArrayOfObject) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public Struct createStruct(String paramString, Object[] paramArrayOfObject) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

    public void setSchema(String schema) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSchema() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void abort(Executor executor) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNetworkTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
