/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;

/**
 * @author Konovalov
 *
 */
public class OCEPooledConnection implements PooledConnection {

	/* (non-Javadoc)
	 * @see javax.sql.PooledConnection#getConnection()
	 */
	
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.sql.PooledConnection#close()
	 */
	
	public void close() throws SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.sql.PooledConnection#addConnectionEventListener(javax.sql.ConnectionEventListener)
	 */
	
	public void addConnectionEventListener(
			ConnectionEventListener paramConnectionEventListener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.sql.PooledConnection#removeConnectionEventListener(javax.sql.ConnectionEventListener)
	 */
	
	public void removeConnectionEventListener(
			ConnectionEventListener paramConnectionEventListener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.sql.PooledConnection#addStatementEventListener(javax.sql.StatementEventListener)
	 */
	
	public void addStatementEventListener(
			StatementEventListener paramStatementEventListener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.sql.PooledConnection#removeStatementEventListener(javax.sql.StatementEventListener)
	 */
	
	public void removeStatementEventListener(
			StatementEventListener paramStatementEventListener) {
		// TODO Auto-generated method stub

	}

}
