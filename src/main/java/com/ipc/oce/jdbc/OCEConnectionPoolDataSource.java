/**
 * 
 */
package com.ipc.oce.jdbc;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

/**
 * @author Konovalov
 *
 */
public class OCEConnectionPoolDataSource implements ConnectionPoolDataSource {

	private String description = null;
	
	private String dataSourceName = null;
	
	private PrintWriter writer = null;
	
	
	public PrintWriter getLogWriter() throws SQLException {
		return writer;
	}

	
	public void setLogWriter(PrintWriter paramPrintWriter) throws SQLException {
		writer = paramPrintWriter;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	
	public void setLoginTimeout(int paramInt) throws SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.sql.ConnectionPoolDataSource#getPooledConnection()
	 */
	
	public PooledConnection getPooledConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.sql.ConnectionPoolDataSource#getPooledConnection(java.lang.String, java.lang.String)
	 */
	
	public PooledConnection getPooledConnection(String paramString1, String paramString2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
