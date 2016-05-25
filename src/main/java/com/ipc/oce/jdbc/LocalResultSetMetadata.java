/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Konovalov
 *
 */
public class LocalResultSetMetadata implements ResultSetMetaData {

	private LocalResultSet lrSet = null;
	private Map<String, Integer> header = null;
	
	public LocalResultSetMetadata(LocalResultSet lrSet) {
		this.header = lrSet.getHeader();
		this.lrSet = lrSet;
	}
	
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		return false;
	}

	public int getColumnCount() throws SQLException {
		return header.size();
	}

	public boolean isAutoIncrement(int paramInt) throws SQLException {
		return false;
	}

	public boolean isCaseSensitive(int paramInt) throws SQLException {
		return true;
	}

	public boolean isSearchable(int paramInt) throws SQLException {
		return false;
	}

	public boolean isCurrency(int paramInt) throws SQLException {
		return false;
	}

	public int isNullable(int paramInt) throws SQLException {
		return 0;
	}

	public boolean isSigned(int paramInt) throws SQLException {
		return false;
	}
	
	private String searchNameByIndex(int index) {
		Set<Map.Entry<String, Integer>> set = header.entrySet();
		Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> me = iterator.next();
			if (me.getValue() == index) {
				return me.getKey();
			}
		}
		return null;
	}
	
	public int getColumnDisplaySize(int paramInt) throws SQLException {
		return searchNameByIndex(paramInt).length();
	}

	public String getColumnLabel(int paramInt) throws SQLException {
		return searchNameByIndex(paramInt);
	}

	public String getColumnName(int paramInt) throws SQLException {
		return searchNameByIndex(paramInt);
	}

	public String getSchemaName(int paramInt) throws SQLException {
		return "";
	}

	public int getPrecision(int paramInt) throws SQLException {
		return 0;
	}

	public int getScale(int paramInt) throws SQLException {
		return 0;
	}

	public String getTableName(int paramInt) throws SQLException {
		return "";
	}

	public String getCatalogName(int paramInt) throws SQLException {
		return "";
	}

	/* (non-Javadoc)
	 * @see java.sql.ResultSetMetaData#getColumnType(int)
	 */
	public int getColumnType(int paramInt) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.sql.ResultSetMetaData#getColumnTypeName(int)
	 */
	public String getColumnTypeName(int paramInt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isReadOnly(int paramInt) throws SQLException {
		return true;
	}

	public boolean isWritable(int paramInt) throws SQLException {
		return false;
	}

	public boolean isDefinitelyWritable(int paramInt) throws SQLException {
		return false;
	}

	public String getColumnClassName(int paramInt) throws SQLException {
		return null;
	}

}
