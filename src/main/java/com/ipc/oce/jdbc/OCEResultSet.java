/**
 * 
 */
package com.ipc.oce.jdbc;

import java.io.ByteArrayInputStream;
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
import java.util.Calendar;
import java.util.Map;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCVariant;
import com.ipc.oce.objects._OCCommonObject;
import com.ipc.oce.objects._OCCommonRef;
import com.ipc.oce.query.OCQueryResult;
import com.ipc.oce.query.OCQueryResultSelection;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.ipc.oce.xml.oc.OCXMLWriter;

/**
 * @author Konovalov
 *
 */
public class OCEResultSet implements ResultSet {

	private static final String METHOD_NOT_APPLICABLE = "Method not applicable";

	private OCQueryResult oceQueryResult = null;
	
	private OCQueryResultSelection selection = null;
	
	private OCEStatement statement = null;
	
	private boolean cursorMoved = false;
	
	private boolean cursorEnded = false;
	
	private int rowCounter = 0;
	
	private int fetchSize = 1;
	
	private Object lastValue = null;
	
	private boolean fClosed = false;
	
	private boolean nested = false;
	
	protected OCEResultSet(OCQueryResult oceQueryResult, OCEStatement statement) throws SQLException {
		this.oceQueryResult = oceQueryResult;
		this.statement = statement;
		try {
			this.selection = this.oceQueryResult.choose();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	public boolean isNested() {
		return nested;
	}

	protected final void setNested(boolean nested) {
		this.nested = nested;
	}

	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		if (paramClass.equals(OCQueryResult.class)) {
			return (T) this.oceQueryResult;
		} else
		if (paramClass.equals(OCQueryResultSelection.class)) {
			return (T) this.selection;
		} else {
			return null;
		}
	}

	
	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		if (paramClass.equals(OCQueryResult.class)) {
			return true;
		} else {
			return (paramClass.equals(OCQueryResultSelection.class));
		}
	}

	
	public boolean next() throws SQLException {
		if (statement.getMaxRows() > 0 
				&& (rowCounter == statement.getMaxRows())) {
			return false;
		}
		try {
			boolean r = selection.next();
			if (!cursorMoved && r) {
				cursorMoved = true;
			}
			if (cursorMoved && !cursorEnded && !r) {
				cursorEnded = true;
			}
			if (r) {
				rowCounter++;
			}
			return r;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}


	
	public void close() throws SQLException {
		fClosed = true;
	}
	
	
	public boolean wasNull() throws SQLException {
		return lastValue == null;
	}

	
	public String getString(int paramInt) throws SQLException {
		paramInt--;
		String res = null;
		try {
			res = selection.getString(paramInt);
		} catch (java.lang.IllegalStateException e) {
			try {
				OCVariant variant = selection.getValue(paramInt);
				Object o = variant.value();
				if (o != null) {
					if (o instanceof _OCCommonRef) {
						Ref ref = new OCERef((_OCCommonRef) o);
						res = ref.toString();
					} else {
						res = o.toString();
					}
				}
			} catch (JIException e1) {
				throw new SQLException(e1);
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		lastValue = res;
		return res;
	}

	
	public boolean getBoolean(int paramInt) throws SQLException {
		paramInt--;
		try {
			boolean b = selection.getBoolean(paramInt);
			lastValue = b;
			return b;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public byte getByte(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public short getShort(int paramInt) throws SQLException {
		paramInt--;
		try {
			short s = 0;
			OCVariant var = selection.getValue(paramInt);
			Object varValue = var.value();
			if (varValue != null) {
				if (varValue instanceof Boolean) {
					Boolean b = (Boolean) varValue;
					if (b) {
						s = 1;
					}
				} else if (varValue instanceof Short) {
					s = (Short) varValue;
				}
			}
			lastValue = s;
			return s;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public int getInt(int paramInt) throws SQLException {
		paramInt--;
		try {
			int i = selection.getInt(paramInt);
			lastValue = i;
			return i;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public long getLong(int paramInt) throws SQLException {
		paramInt--;
		try {
			long l = selection.getLong(paramInt);
			lastValue = l;
			return l;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public float getFloat(int paramInt) throws SQLException {
		paramInt--;
		try {
			float f = selection.getFloat(paramInt);
			lastValue = f;
			return f;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public double getDouble(int paramInt) throws SQLException {
		paramInt--;
		try {
			double d = selection.getDouble(paramInt);
			lastValue = d;
			return d;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public BigDecimal getBigDecimal(int paramInt1, int paramInt2) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public byte[] getBytes(int paramInt) throws SQLException {
		paramInt--;
		String tmp = getString(paramInt);
		lastValue = tmp;
		return (tmp != null ? tmp.getBytes() : null);
	}

	
	public Date getDate(int paramInt) throws SQLException {
		paramInt--;
		Date sqlD = null;
		try {
			java.util.Date d = selection.getDate(paramInt);
			if (d != null) {
				sqlD = new Date(d.getTime());
			}
			lastValue = sqlD;
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return sqlD;
	}

	
	public Time getTime(int paramInt) throws SQLException {
		paramInt--;
		Time sqlT = null;
		try {
			java.util.Date d = selection.getDate(paramInt);
			if (d != null) {
				sqlT = new Time(d.getTime());
			}
			lastValue = sqlT;
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return sqlT;
	}

	
	public Timestamp getTimestamp(int paramInt) throws SQLException {
		paramInt--;
		Timestamp sqlT = null;
		try {
			java.util.Date d = selection.getDate(paramInt);
			if (d != null) {
				sqlT = new Timestamp(d.getTime());
			}
			lastValue = sqlT;
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return sqlT;
	}
	
	
	public InputStream getAsciiStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public InputStream getUnicodeStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}
	
	protected byte[] ref2XDTObytes(Ref ref) throws SQLException {
		
		byte[] bytes = null;
		
		if (ref == null) {
			return bytes;
		}
		
		try {
				OCApp app = ((OCEConnection) getStatement().getConnection()).getApplication();
				
				OCXDTOSerializer serializer = app.getXDTOSerializer();
				
				OCXMLWriter writer = app.newXMLWriter();
				writer.setString("UTF-8");
				
				_OCCommonObject commonObject = (_OCCommonObject) ref.getObject();
				serializer.writeXML(writer, commonObject);
				String r1 = writer.close();
				bytes = r1.getBytes();
		} catch (JIException e) {
			throw new SQLException(e);
		}
		
		return bytes;
	}
	
	public InputStream getBinaryStream(int paramInt) throws SQLException {
		byte[] bytes = null;
		InputStream is = null;
		bytes = ref2XDTObytes(getRef(paramInt));
		if (bytes != null && bytes.length > 0) {
			is = new ByteArrayInputStream(bytes);
		}
		return is;
	}
	
	public String getString(String paramString) throws SQLException {
		String res = null;
		try {
			res = selection.getString(paramString);
		} catch (java.lang.IllegalStateException e) {
			try {
				OCVariant variant = selection.getValue(paramString);
				Object o = variant.value();
				if (o != null) {
					if (o instanceof _OCCommonRef) {
						res = ((_OCCommonRef) o).getUUID().toString();
					} else {
						res = o.toString();
					}
				}
			} catch (JIException e1) {
				throw new SQLException(e1);
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		lastValue = res;
		return res;
	}

	
	public boolean getBoolean(String paramString) throws SQLException {
		try {
			boolean b = selection.getBoolean(paramString);
			lastValue = b;
			return b;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public byte getByte(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public short getShort(String paramString) throws SQLException {
		try {
			short s = 0;
			OCVariant var = selection.getValue(paramString);
			Object varValue = var.value();
			if (varValue != null) {
				if (varValue instanceof Boolean) {
					Boolean b = (Boolean) varValue;
					if (b) {
						s = 1;
					}
				} else 
				if (varValue instanceof Short) {
					s = (Short) varValue;
				}
			}
			lastValue = s;
			return s;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public int getInt(String paramString) throws SQLException {
		try {
			int i = selection.getInt(paramString);
			lastValue = i;
			return i;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public long getLong(String paramString) throws SQLException {
		try {
			long l = selection.getLong(paramString);
			lastValue = l;
			return l;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public float getFloat(String paramString) throws SQLException {
		try {
			float f = selection.getFloat(paramString);
			lastValue = f;
			return f;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public double getDouble(String paramString) throws SQLException {
		try {
			double d = selection.getDouble(paramString);
			lastValue = d;
			return d;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public BigDecimal getBigDecimal(String paramString, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public byte[] getBytes(String paramString) throws SQLException {
		return getString(paramString).getBytes();
	}

	
	public Date getDate(String paramString) throws SQLException {
		Date sqlD = null;
		try {
			java.util.Date d = selection.getDate(paramString);
			if (d != null) {
				sqlD = new Date(d.getTime());
			}
			lastValue = sqlD;
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return sqlD;
	}

	
	public Time getTime(String paramString) throws SQLException {
		Time sqlT = null;
		try {
			java.util.Date d = selection.getDate(paramString);
			if (d != null) {
				sqlT = new Time(d.getTime());
			}
			lastValue = sqlT;
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return sqlT;
	}

	
	public Timestamp getTimestamp(String paramString) throws SQLException {
		Timestamp sqlT = null;
		try {
			java.util.Date d = selection.getDate(paramString);
			if (d != null) {
				sqlT = new Timestamp(d.getTime());
			}
			lastValue = sqlT;
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return sqlT;
	}

	
	public InputStream getAsciiStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}


	
	public InputStream getUnicodeStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public InputStream getBinaryStream(String paramString) throws SQLException {
		byte[] bytes = null;
		InputStream is = null;
		
		bytes = ref2XDTObytes(getRef(paramString));
		
		if (bytes != null && bytes.length > 0) {
			is = new ByteArrayInputStream(bytes);
		}
		return is;
	}

	
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	
	public void clearWarnings() throws SQLException {
		// NOP
	}

	
	public String getCursorName() throws SQLException {
		return statement.getCursorName();
	}

	
	public ResultSetMetaData getMetaData() throws SQLException {
		
		try {
			return new OCEResultSetMetaData(oceQueryResult.getColumns());
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public Object getObject(int paramInt) throws SQLException {
		paramInt--;
		try {
			Object o = selection.getValue(paramInt).value();
			if (o instanceof Boolean) {
				o = ((Boolean) o ? 1 : 0);
			} else 
			if (o instanceof java.util.Date){
				o = new java.sql.Date(((java.util.Date)o).getTime());
			} else
			if (o instanceof OCQueryResult) {
				o = new OCEResultSet((OCQueryResult) o, (OCEStatement) getStatement());
				((OCEResultSet) o).setNested(true);
			}
			lastValue = o;
			return o;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public Object getObject(String paramString) throws SQLException {
		try {
			Object o = selection.getValue(paramString).value();
			if (o instanceof Boolean) {
				o = ((Boolean) o ? 1 : 0);
			} else 
			if (o instanceof java.util.Date){
				o = new java.sql.Date(((java.util.Date)o).getTime());
			} else
			if (o instanceof OCQueryResult) {
				o = new OCEResultSet((OCQueryResult) o, (OCEStatement) getStatement());
				((OCEResultSet) o).setNested(true);
			}
			lastValue = o;
			return o;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public int findColumn(String paramString) throws SQLException {
		int index = 0;
		try {
			index = oceQueryResult.getColumns().getNames().indexOf(paramString);
			if (index == -1) {
				throw new SQLException("Column '" + paramString + "' not found");
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return index + 1;
	}

	
	public Reader getCharacterStream(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public Reader getCharacterStream(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public BigDecimal getBigDecimal(int paramInt) throws SQLException {
		paramInt--;
		BigDecimal bd = null;
		try {
			Object o = selection.getValue(paramInt).value();
			if (o instanceof Long) {
				bd = new BigDecimal((Long) o);
			} else if (o instanceof Double) {
				bd = new BigDecimal((Double) o);
			} else if (o != null) {
				bd = new BigDecimal(o.toString());
			}
			lastValue = bd;
			return bd;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	
	public BigDecimal getBigDecimal(String paramString) throws SQLException {
		BigDecimal bd = null;
		try {
			Object o = selection.getValue(paramString).value();
			if (o instanceof Long) {
				bd = new BigDecimal((Long) o);
			} else if (o instanceof Double) {
				bd = new BigDecimal((Double) o);
			} else if (o != null) {
				bd = new BigDecimal(o.toString());
			}
			lastValue = bd;
			return bd;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	
	public boolean isBeforeFirst() throws SQLException {
		return !cursorMoved;
	}

	
	public boolean isAfterLast() throws SQLException {
		return cursorEnded;
	}

	
	public boolean isFirst() throws SQLException {
		return (rowCounter == 1);
	}

	
	public boolean isLast() throws SQLException {
		try {
			return selection.size() == rowCounter;
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	
	public void beforeFirst() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void afterLast() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public boolean first() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public boolean last() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public int getRow() throws SQLException {
		return rowCounter;
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
		if (paramInt != ResultSet.FETCH_FORWARD) {
			throw new SQLException("Only ResultSet.FETCH_FORWARD supported");
		}

	}

	
	public int getFetchDirection() throws SQLException {
		return ResultSet.FETCH_FORWARD;
	}

	
	public void setFetchSize(int paramInt) throws SQLException {
		fetchSize = paramInt;
	}

	
	public int getFetchSize() throws SQLException {
		return fetchSize;
	}

	
	public int getType() throws SQLException {
		return ResultSet.TYPE_FORWARD_ONLY;
	}

	
	public int getConcurrency() throws SQLException {
		return ResultSet.CONCUR_READ_ONLY;
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
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void updateRow() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void deleteRow() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void refreshRow() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
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
		return statement;
	}

	
	public Object getObject(int paramInt, Map<String, Class<?>> paramMap) throws SQLException {
		return getObject(paramInt);
	}

	
	public Ref getRef(int paramInt) throws SQLException {
		paramInt--;
		Ref ref = null;
		
		try {
			Object o = selection.getValue(paramInt).value();
			if (o instanceof _OCCommonRef) {
				ref = new OCERef((_OCCommonRef) o);
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return ref;
		
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

	
	public Object getObject(String paramString, Map<String, Class<?>> paramMap) throws SQLException {
		return getObject(paramString);
	}

	
	public Ref getRef(String paramString) throws SQLException {
		Ref ref = null;
		
		try {
			Object o = selection.getValue(paramString).value();
			if (o instanceof _OCCommonRef) {
				ref = new OCERef((_OCCommonRef) o);
			}
		} catch (JIException e) {
			throw new SQLException(e);
		}
		return ref;
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
		paramCalendar.setTimeInMillis(getDate(paramInt).getTime());
		return new Date(paramCalendar.getTime().getTime());
	}

	
	public Date getDate(String paramString, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTimeInMillis(getDate(paramString).getTime());
		return new Date(paramCalendar.getTime().getTime());
	}

	
	public Time getTime(int paramInt, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTimeInMillis(getTime(paramInt).getTime());
		return new Time(paramCalendar.getTime().getTime());
	}

	
	public Time getTime(String paramString, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTimeInMillis(getTime(paramString).getTime());
		return new Time(paramCalendar.getTime().getTime());
	}

	
	public Timestamp getTimestamp(int paramInt, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTimeInMillis(getTimestamp(paramInt).getTime());
		return new Timestamp(paramCalendar.getTime().getTime());
	}

	
	public Timestamp getTimestamp(String paramString, Calendar paramCalendar) throws SQLException {
		paramCalendar.setTimeInMillis(getTimestamp(paramString).getTime());
		return new Timestamp(paramCalendar.getTime().getTime());
	}

	
	public URL getURL(int paramInt) throws SQLException {
		String s = getString(paramInt);
		try {
			return new URL(s);
		} catch (MalformedURLException e) {
			throw new SQLException(e);
		}
	}

	
	public URL getURL(String paramString) throws SQLException {
		String s = getString(paramString);
		try {
			return new URL(s);
		} catch (MalformedURLException e) {
			throw new SQLException(e);
		}
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
		return ResultSet.HOLD_CURSORS_OVER_COMMIT;
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
