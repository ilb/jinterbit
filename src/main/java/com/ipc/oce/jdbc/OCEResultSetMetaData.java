/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.metadata.OCType;
import com.ipc.oce.query.OCQueryResultColumn;
import com.ipc.oce.query.OCQueryResultColumnsCollection;

/**
 * @author Konovalov
 *
 */
public class OCEResultSetMetaData implements ResultSetMetaData {

	private OCQueryResultColumnsCollection columnCollection = null;
	
	
	
	protected OCEResultSetMetaData(OCQueryResultColumnsCollection columnCollection) {
		super();
		this.columnCollection = columnCollection;
	}

	@SuppressWarnings("unchecked")
	
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		if (paramClass.equals(OCQueryResultColumnsCollection.class)) {
			return (T) this.columnCollection;
		} else {
			return null;
		}
	}

	
	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		return paramClass.equals(OCQueryResultColumnsCollection.class);
	}

	
	public int getColumnCount() throws SQLException {
		try {
			return columnCollection.size();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public boolean isAutoIncrement(int paramInt) throws SQLException {
		return false;
	}

	
	public boolean isCaseSensitive(int paramInt) throws SQLException {
		return true;
	}

	
	public boolean isSearchable(int paramInt) throws SQLException {
		return true;
	}

	
	public boolean isCurrency(int paramInt) throws SQLException {
		return false;
	}

	
	public int isNullable(int paramInt) throws SQLException {
		return columnNullableUnknown;
	}

	
	public boolean isSigned(int paramInt) throws SQLException {
		return false;
	}

	
	public int getColumnDisplaySize(int paramInt) throws SQLException {
		try {
			OCQueryResultColumn col = columnCollection.getColumn(paramInt - 1);
			return col.getWidth();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public String getColumnLabel(int paramInt) throws SQLException {
		String res = null;
		try {
			OCQueryResultColumn col = columnCollection.getColumn(paramInt - 1);
			res = col.getName();
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return res;
	}


	
	public String getColumnName(int paramInt) throws SQLException {
		return getColumnLabel(paramInt);
	}

	
	public String getSchemaName(int paramInt) throws SQLException {
		return ""; // according jdbc spec
	}

	
	public int getPrecision(int paramInt) throws SQLException {
		int res = 0;
		try {
			OCQueryResultColumn col = columnCollection.getColumn(paramInt - 1);
			if (getScale(paramInt) == 0) {
				res = col.getWidth();
			} else {
				res = col.getValueType().getNumberQualifiers().getDigits();
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return res;
	}

	
	public int getScale(int paramInt) throws SQLException {
		int res = 0;
		try {
			OCQueryResultColumn col = columnCollection.getColumn(paramInt - 1);
			res = col.getValueType().getNumberQualifiers().getFractionDigits();
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return res;
	}

	
	public String getTableName(int paramInt) throws SQLException {
		return ""; // jdbc spec
	}

	
	public String getCatalogName(int paramInt) throws SQLException {
		return null;
	}

	
	public int getColumnType(int paramInt) throws SQLException {
		int res = -100500;
		try {
			OCQueryResultColumn col = columnCollection.getColumn(paramInt - 1);
			OCType[] types = col.getValueType().getNotNullTypes();
			res = OCType.typesToSQLType(types);
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return res;
	}

	
	public String getColumnTypeName(int paramInt) throws SQLException {
		String res = "";
		try {
			OCQueryResultColumn col = columnCollection.getColumn(paramInt - 1);
			OCType[] types = col.getValueType().getNotNullTypes();
			res = OCType.typesToSQLTypeName(types);
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return res;
	}

	
	public boolean isReadOnly(int paramInt) throws SQLException {
		boolean res = false;
		try {
			OCQueryResultColumn col = columnCollection.getColumn(paramInt - 1);
			OCType[] types = col.getValueType().getNotNullTypes();
			if (types.length == 1 
					&& types[0].getTypeCode() < OCType.OCT_REF_CATALOG) {
				res = true;
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		
		
		return res;
	}

	
	public boolean isWritable(int paramInt) throws SQLException {
		return !isReadOnly(paramInt);
	}

	
	public boolean isDefinitelyWritable(int paramInt) throws SQLException {
		return false;
	}

	
	public String getColumnClassName(int paramInt) throws SQLException {
		throw new SQLException("Not supported. Because 1C support multitype columns");
	}
}
