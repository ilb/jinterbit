/**
 * 
 */
package com.ipc.oce.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.query.OCQueryResult;

/**
 * @author Konovalov
 *
 */
public class OCEPreparedStatement extends OCEStatement implements PreparedStatement {
	
	private static final transient Log LOG = LogFactory.getLog(OCEPreparedStatement.class);
	
	private static final String METHOD_NOT_APPLICABLE = "Method not applicable";
	
	private String preparedQuery = null;
	
	private Map<Integer, String> parametersMap = null;
	private int paramCounter = 0;
	
	public OCEPreparedStatement(OCEConnection connection, String query) throws SQLException {
		super(connection);
		// prepare for 1C-SQL
		parametersMap = new HashMap<Integer, String>();
		while (query.indexOf("?") != -1) {
			String pName = "par" + paramCounter;
			query = query.replaceFirst("\\?", "&" + pName);
			parametersMap.put(paramCounter, pName);
			paramCounter++;
		}
		preparedQuery = query;
		try {
			getOceStatement().setText(preparedQuery);
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}
	
	private String getPName(int index) {
		return parametersMap.get(index - 1);
	}

	
	public ResultSet executeQuery() throws SQLException {
		if (execute()) {
			return lastVisitedResultSet;
		} else {
			return null;
		}
	}

	
	public int executeUpdate() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setNull(int paramInt1, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);

	}

	
	public void setBoolean(int paramInt, boolean paramBoolean) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramBoolean));
		} catch (JIException e) {
			throw new SQLException(e);
		}

	}

	
	public void setByte(int paramInt, byte paramByte) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramByte));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setShort(int paramInt, short paramShort) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramShort));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setInt(int paramInt1, int paramInt2) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt1), new OCVariant(paramInt2));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setLong(int paramInt, long paramLong) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramLong));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setFloat(int paramInt, float paramFloat) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramFloat));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setDouble(int paramInt, double paramDouble) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramDouble));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setBigDecimal(int paramInt, BigDecimal paramBigDecimal) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramBigDecimal));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setString(int paramInt, String paramString) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramString));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setBytes(int paramInt, byte[] paramArrayOfByte) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(new String(paramArrayOfByte)));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setDate(int paramInt, Date paramDate) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramDate));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setTime(int paramInt, Time paramTime) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramTime));
		} catch (JIException e) {
			throw new SQLException(e);
		}

	}

	
	public void setTimestamp(int paramInt, Timestamp paramTimestamp) throws SQLException {
		try {
			getOceStatement().setParameter(getPName(paramInt), new OCVariant(paramTimestamp));
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void clearParameters() throws SQLException {
		try {
			getOceStatement().getParameters().clear();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void setObject(int paramInt1, Object paramObject, int paramInt2) throws SQLException {
		
		// TODO Auto-generated method stub
	}

	
	public void setObject(int paramInt, Object paramObject) throws SQLException {
		if (paramObject instanceof OCObject) {
			try {
				getOceStatement().setParameter(getPName(paramInt),
						new OCVariant(paramObject));
			} catch (JIException e) {
				throw new SQLException(e);
			}
		}
	}

	
	public boolean execute() throws SQLException {
		ResultSet rSet = null;
		boolean res = false;
		try {
			OCQueryResult result = getOceStatement().execute();
			rSet = new OCEResultSet(result, this);
			if (rSet != null) {
				res = true;
				lastVisitedResultSet = rSet;
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return res;
	}

	
	public void addBatch() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setRef(int paramInt, Ref paramRef) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setBlob(int paramInt, Blob paramBlob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setClob(int paramInt, Clob paramClob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setArray(int paramInt, Array paramArray) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public ResultSetMetaData getMetaData() throws SQLException {
		if (lastVisitedResultSet == null) {
			throw new SQLException("Execute statement first");
		}
		return lastVisitedResultSet.getMetaData();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date, java.util.Calendar)
	 */
	
	public void setDate(int paramInt, Date paramDate, Calendar paramCalendar) throws SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTime(int, java.sql.Time, java.util.Calendar)
	 */
	
	public void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp, java.util.Calendar)
	 */
	
	public void setTimestamp(int paramInt, Timestamp paramTimestamp,
			Calendar paramCalendar) throws SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNull(int, int, java.lang.String)
	 */
	
	public void setNull(int paramInt1, int paramInt2, String paramString)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	
	public void setURL(int paramInt, URL paramURL) throws SQLException {
		setString(paramInt, paramURL.toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#getParameterMetaData()
	 */
	
	public ParameterMetaData getParameterMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setRowId(int paramInt, RowId paramRowId) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setNString(int paramInt, String paramString) throws SQLException {
		setString(paramInt, paramString);
	}

	
	public void setNCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setNClob(int paramInt, NClob paramNClob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setBlob(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setNClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setSQLXML(int paramInt, SQLXML paramSQLXML) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int, int)
	 */
	
	public void setObject(int paramInt1, Object paramObject, int paramInt2,
			int paramInt3) throws SQLException {
		// TODO Auto-generated method stub

	}

	
	public void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setAsciiStream(int paramInt, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setBinaryStream(int paramInt, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setCharacterStream(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setNCharacterStream(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setClob(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setBlob(int paramInt, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void setNClob(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

}
