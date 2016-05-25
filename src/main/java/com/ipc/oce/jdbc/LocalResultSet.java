/**
 * 
 */
package com.ipc.oce.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Konovalov
 *
 */
public class LocalResultSet implements ResultSet {
	
	private static final String METHOD_NOT_APPLICABLE = "Method not applicable";
	
	private Map<String, Integer> header = null;
	
	private int currentRowNumber = -1;
	
	private List<List<Object>> dataBoard = null;
	
	private List<Object> currentRow = null;
	
	private OCEConnection connection = null;
	
	//private Object lastValue = null;
	
	private boolean fClosed = false;
	
	protected static LocalResultSet createEmptyResultSet(String[] head, OCEConnection connection) {
		LocalResultSet lrs = new LocalResultSet(connection);
		int index = 1;
		for (String columnName : head) {
			lrs.setHeader(index++, columnName);
		}
		
		return lrs;
	}
	
	public LocalResultSet(OCEConnection connection) {
		header = new LinkedHashMap<String, Integer>();
		dataBoard = new ArrayList<List<Object>>();
		this.connection = connection;
	}
	
	/**
	 * Получение количества сторок ResultSet.
	 * @return количества сторок ResultSet
	 */
	public int getRowsCount() {
		return dataBoard.size();
	}
	
	protected void addRow() throws SQLException {
		if (header.size() == 0) {
			throw new SQLException("Setup header (column name metadata) first");
		}
		currentRow = new ArrayList<Object>(header.size());
		for (int z = 0; z < header.size(); z++) {
			currentRow.add(null);
		}
		dataBoard.add(currentRow);
	}
	
	protected List<Object> createAndGetNextRow() throws SQLException {
		addRow();		
		return currentRow;
	}
	
	protected void createRowAndSetValues(Object... params) throws SQLException {
		List<Object> row = createAndGetNextRow();
		int index = 0;
		for (Object o : params) {
			row.set(index++, o);
		}
	}
	
	protected void setHeader(Integer position, String columnName) {
		header.put(columnName, position);
	}
	
	protected Map<String, Integer> getHeader() {
		return header;
	}
	
	protected List<Object> getCurrentRow() {
		return currentRow;
	}
	
	//===============================================================
	// ResultSet implementation next...
	
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		return false;
	}

	public boolean next() throws SQLException {
		currentRowNumber++; // 0 at first iteration
		if (currentRowNumber >= dataBoard.size()) {
			return false;
		} else {
			currentRow = dataBoard.get(currentRowNumber);
			return true;
		}
	}

	public void close() throws SQLException {
		fClosed = true;
	}

	public boolean wasNull() throws SQLException {
		return false;
	}

	public String getString(int paramInt) throws SQLException {
		return (currentRow.get(paramInt - 1) != null ? currentRow.get(
				paramInt - 1).toString() : null);
	}

	public boolean getBoolean(int paramInt) throws SQLException {
		return (Boolean) currentRow.get(paramInt - 1);
	}

	public byte getByte(int paramInt) throws SQLException {
		return (Byte) currentRow.get(paramInt - 1);
	}

	public short getShort(int paramInt) throws SQLException {
		return (Short) currentRow.get(paramInt - 1);
	}

	public int getInt(int paramInt) throws SQLException {
		try {
			return (Integer) currentRow.get(paramInt - 1);
		} catch (ClassCastException cce) {
			String val = getString(paramInt);
			if (val != null) {
				return Integer.parseInt(val);
			} else {
				return 0;
			}
		}
	}

	public long getLong(int paramInt) throws SQLException {
		return (Long) currentRow.get(paramInt - 1);
	}

	public float getFloat(int paramInt) throws SQLException {
		return (Float) currentRow.get(paramInt - 1);
	}

	public double getDouble(int paramInt) throws SQLException {
		return (Double) currentRow.get(paramInt - 1);
	}

	public BigDecimal getBigDecimal(int paramInt1, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public byte[] getBytes(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Date getDate(int paramInt) throws SQLException {
		return (Date) currentRow.get(paramInt - 1);
	}

	public Time getTime(int paramInt) throws SQLException {
		return (Time) currentRow.get(paramInt - 1);
	}

	public Timestamp getTimestamp(int paramInt) throws SQLException {
		return (Timestamp) currentRow.get(paramInt - 1);
	}

	public InputStream getAsciiStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public InputStream getUnicodeStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public InputStream getBinaryStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}
	
	public String getString(String paramString) throws SQLException {
		int index = header.get(paramString) - 1;
		return (currentRow.get(index) != null ? currentRow.get(index).toString() : null);
	}

	public boolean getBoolean(String paramString) throws SQLException {
		return (Boolean) currentRow.get(header.get(paramString) - 1);
	}

	public byte getByte(String paramString) throws SQLException {
		return (Byte) currentRow.get(header.get(paramString) - 1);
	}

	public short getShort(String paramString) throws SQLException {
		return (Short) currentRow.get(header.get(paramString) - 1);
	}

	public int getInt(String paramString) throws SQLException {
		return (Integer) currentRow.get(header.get(paramString) - 1);
	}

	public long getLong(String paramString) throws SQLException {

		return (Long) currentRow.get(header.get(paramString) - 1);
	}

	public float getFloat(String paramString) throws SQLException {
		return (Float) currentRow.get(header.get(paramString) - 1);
	}

	public double getDouble(String paramString) throws SQLException {
		return (Double) currentRow.get(header.get(paramString) - 1);
	}

	public BigDecimal getBigDecimal(String paramString, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public byte[] getBytes(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Date getDate(String paramString) throws SQLException {

		return (Date) currentRow.get(header.get(paramString) - 1);
	}

	public Time getTime(String paramString) throws SQLException {
		return (Time) currentRow.get(header.get(paramString) - 1);
	}

	public Timestamp getTimestamp(String paramString) throws SQLException {
		return (Timestamp) currentRow.get(header.get(paramString) - 1);
	}

	public InputStream getAsciiStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public InputStream getUnicodeStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public InputStream getBinaryStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	public void clearWarnings() throws SQLException {
		// OK
	}

	public String getCursorName() throws SQLException {
		return "";
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return new LocalResultSetMetadata(this);
	}

	public Object getObject(int paramInt) throws SQLException {
		return currentRow.get(paramInt - 1);
	}

	public Object getObject(String paramString) throws SQLException {
		return currentRow.get(header.get(paramString));
	}

	public int findColumn(String paramString) throws SQLException {
		return header.get(paramString) + 1;
	}

	public Reader getCharacterStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Reader getCharacterStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public BigDecimal getBigDecimal(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public BigDecimal getBigDecimal(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public boolean isBeforeFirst() throws SQLException {
		return currentRowNumber == -1;
	}

	public boolean isAfterLast() throws SQLException {
		return currentRowNumber >= dataBoard.size();
	}

	public boolean isFirst() throws SQLException {
		return currentRowNumber == 1;
	}

	public boolean isLast() throws SQLException {
		return currentRowNumber == (dataBoard.size() - 1);
	}

	public void beforeFirst() throws SQLException {
		currentRowNumber = -2;
		next();
	}

	public void afterLast() throws SQLException {
		currentRowNumber = dataBoard.size();
	}

	public boolean first() throws SQLException {
		currentRowNumber = -1;
		return next();
	}

	public boolean last() throws SQLException {
		currentRowNumber = dataBoard.size() - 2;
		return next();
	}

	public int getRow() throws SQLException {
		return currentRowNumber;
	}

	public boolean absolute(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public boolean relative(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public boolean previous() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void setFetchDirection(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public int getFetchDirection() throws SQLException {
		return FETCH_FORWARD;
	}

	public void setFetchSize(int paramInt) throws SQLException {
		// NOP
	}

	public int getFetchSize() throws SQLException {
		return 0;
	}

	public int getType() throws SQLException {
		return TYPE_FORWARD_ONLY;
	}

	public int getConcurrency() throws SQLException {
		return CONCUR_READ_ONLY;
	}

	public boolean rowUpdated() throws SQLException {
		return false;
	}

	public boolean rowInserted() throws SQLException {
		return false;
	}

	public boolean rowDeleted() throws SQLException {
		return false;
	}

	public void updateNull(int paramInt) throws SQLException {
		// NOP
		throw new SQLException(METHOD_NOT_APPLICABLE);

	}

	public void updateBoolean(int paramInt, boolean paramBoolean) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);

	}

	public void updateByte(int paramInt, byte paramByte) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateShort(int paramInt, short paramShort) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateInt(int paramInt1, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);

	}

	public void updateLong(int paramInt, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);

	}

	public void updateFloat(int paramInt, float paramFloat) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateDouble(int paramInt, double paramDouble) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBigDecimal(int paramInt, BigDecimal paramBigDecimal) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateString(int paramInt, String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBytes(int paramInt, byte[] paramArrayOfByte) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateDate(int paramInt, Date paramDate) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateTime(int paramInt, Time paramTime) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateTimestamp(int paramInt, Timestamp paramTimestamp) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateObject(int paramInt1, Object paramObject, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateObject(int paramInt, Object paramObject) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNull(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBoolean(String paramString, boolean paramBoolean) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateByte(String paramString, byte paramByte) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateShort(String paramString, short paramShort) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);

	}

	public void updateInt(String paramString, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateLong(String paramString, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateFloat(String paramString, float paramFloat) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateDouble(String paramString, double paramDouble) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBigDecimal(String paramString, BigDecimal paramBigDecimal) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateString(String paramString1, String paramString2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBytes(String paramString, byte[] paramArrayOfByte) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateDate(String paramString, Date paramDate) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateTime(String paramString, Time paramTime) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateTimestamp(String paramString, Timestamp paramTimestamp) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateAsciiStream(String paramString, InputStream paramInputStream, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBinaryStream(String paramString, InputStream paramInputStream, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateCharacterStream(String paramString, Reader paramReader, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateObject(String paramString, Object paramObject, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateObject(String paramString, Object paramObject) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void insertRow() throws SQLException {
		createAndGetNextRow();
	}

	public void updateRow() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void deleteRow() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void refreshRow() throws SQLException {
		// NOP

	}

	public void cancelRowUpdates() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void moveToInsertRow() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void moveToCurrentRow() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Statement getStatement() throws SQLException {
		return null;
	}

	public Object getObject(int paramInt, Map<String, Class<?>> paramMap) throws SQLException {
		return null;
	}

	public Ref getRef(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Blob getBlob(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Clob getClob(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Array getArray(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Object getObject(String paramString, Map<String, Class<?>> paramMap)
			throws SQLException {
		return null;
	}


	public Ref getRef(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Blob getBlob(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Clob getClob(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Array getArray(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Date getDate(int paramInt, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTime(getDate(paramInt));
		return new java.sql.Date(paramCalendar.getTime().getTime());
	}

	public Date getDate(String paramString, Calendar paramCalendar) throws SQLException {
		return getDate(header.get(paramString), paramCalendar);
	}

	public Time getTime(int paramInt, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTime(getDate(paramInt));
		return new java.sql.Time(paramCalendar.getTime().getTime());
	}

	public Time getTime(String paramString, Calendar paramCalendar) throws SQLException {
		return getTime(header.get(paramString), paramCalendar);
	}

	public Timestamp getTimestamp(int paramInt, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTime(getDate(paramInt));
		return new java.sql.Timestamp(paramCalendar.getTime().getTime());
	}

	public Timestamp getTimestamp(String paramString, Calendar paramCalendar) throws SQLException {
		return getTimestamp(header.get(paramString), paramCalendar);
	}

	public URL getURL(int paramInt) throws SQLException {
		try {
			return new URL(getString(paramInt));
		} catch (MalformedURLException e) {
			throw new SQLException(e);
		}
	}

	public URL getURL(String paramString) throws SQLException {
		return getURL(header.get(paramString));
	}

	public void updateRef(int paramInt, Ref paramRef) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateRef(String paramString, Ref paramRef) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBlob(int paramInt, Blob paramBlob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBlob(String paramString, Blob paramBlob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateClob(int paramInt, Clob paramClob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateClob(String paramString, Clob paramClob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateArray(int paramInt, Array paramArray) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateArray(String paramString, Array paramArray) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public RowId getRowId(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public RowId getRowId(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateRowId(int paramInt, RowId paramRowId) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateRowId(String paramString, RowId paramRowId) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public int getHoldability() throws SQLException {
		return HOLD_CURSORS_OVER_COMMIT;
	}

	public boolean isClosed() throws SQLException {
		return fClosed;
	}

	public void updateNString(int paramInt, String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNString(String paramString1, String paramString2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNClob(int paramInt, NClob paramNClob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNClob(String paramString, NClob paramNClob) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public NClob getNClob(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public NClob getNClob(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public SQLXML getSQLXML(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public SQLXML getSQLXML(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateSQLXML(int paramInt, SQLXML paramSQLXML) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateSQLXML(String paramString, SQLXML paramSQLXML) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public String getNString(int paramInt) throws SQLException {
		return getString(paramInt);
	}

	public String getNString(String paramString) throws SQLException {
		return getString(paramString);
	}

	public Reader getNCharacterStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public Reader getNCharacterStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNCharacterStream(String paramString, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateAsciiStream(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBinaryStream(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateCharacterStream(String paramString, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBlob(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBlob(String paramString, InputStream paramInputStream, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateClob(String paramString, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNClob(int paramInt, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNClob(String paramString, Reader paramReader, long paramLong) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNCharacterStream(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNCharacterStream(String paramString, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateAsciiStream(int paramInt, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBinaryStream(int paramInt, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateCharacterStream(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}
 
	public void updateAsciiStream(String paramString, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBinaryStream(String paramString, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateCharacterStream(String paramString, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBlob(int paramInt, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateBlob(String paramString, InputStream paramInputStream) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateClob(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateClob(String paramString, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNClob(int paramInt, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	public void updateNClob(String paramString, Reader paramReader) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

}
