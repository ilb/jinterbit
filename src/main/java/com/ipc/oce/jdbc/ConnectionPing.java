/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimerTask;

/**
 * @author Konovalov
 *
 */
public class ConnectionPing extends TimerTask {

	private Connection connection = null;
	
	public ConnectionPing(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void run() {
		if (connection != null) {
			try {
				connection.isClosed();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
