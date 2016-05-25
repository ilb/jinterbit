/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCVariant;
import com.ipc.oce.objects.AttributeBean;
import com.ipc.oce.objects._OCCommonObject;
import com.ipc.oce.objects._OCCommonRef;
import com.ipc.oce.query.OCQuery;
import com.ipc.oce.query.OCQueryResult;

/**
 * @author Konovalov
 *
 */
public class OCEStatement implements Statement {
	private static final String METHOD_NOT_APPLICABLE = "Method not applicable";
	
	private OCEConnection connection = null;
	private OCQuery oceStatement = null;
	
	private int maxFieldSize = 0;
	private int maxRows = 0;
	
	@SuppressWarnings("unused")
	private boolean escapeProcessing = true;
	
	private String cursorName = "";
	
	protected ResultSet lastVisitedResultSet = null;
	
	private boolean fClose = false;

	protected OCEStatement(OCEConnection connection) {
		this.connection = connection;
		OCApp app = connection.getApplication();
		try {
			oceStatement = app.newQuery();
		} catch (JIException e) {
			throw new RuntimeException(e.toString());
		}
	}

	@SuppressWarnings("unchecked")
	
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		if (paramClass.equals(OCQuery.class)) {
			return (T) oceStatement;
		}
		return null;
	}

	
	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		return paramClass.equals(OCQuery.class);
	}

	protected final OCQuery getOceStatement() {
		return oceStatement;
	}

	
	public ResultSet executeQuery(String query) throws SQLException {
		ResultSet rSet = null;
		try {
			oceStatement.setText(query);
			OCQueryResult result = oceStatement.execute();
			rSet = new OCEResultSet(result, this);
			lastVisitedResultSet = rSet;
		} catch (JIException e) {
			throw new SQLException(e.toString());
		}
		return rSet;
	}

	/*
	 * Update Syntax
	 * UPDATE table-name SET column_name=value WHERE column_name=value
	 * UPDATE Document.СчетНаОплатуПокупателю SET АдресДоставки = 'новый' WHERE Number = '19900'
	 */
	
	public int executeUpdate(String paramString) throws SQLException {
		int res = 0;
		Map<String, Object> params = parseSimpleUpdateStatement(paramString);
		String wherePredicates = (String) params.get("WHERE");	
		String tableName = (String) params.get("TABLENAME");
		
		Map<String, Object> mappedSet = (Map<String, Object>) params.get("SETS");
		
		String selectStat = "SELECT Ref FROM " + tableName;
		if (wherePredicates != null) {
			selectStat += " WHERE " + wherePredicates;
		}
		

		ResultSet locRs = executeQuery(selectStat);
		try {
			while (locRs.next()) {
				_OCCommonRef obj = (_OCCommonRef) locRs.getObject(1);
				_OCCommonObject commonObject = obj.getObject();
				boolean objectChanged = false;
				if (commonObject instanceof AttributeBean) {
					AttributeBean aBean = (AttributeBean) commonObject;
					Iterator<String> iter = mappedSet.keySet().iterator();
					while (iter.hasNext()) {
						String key = iter.next();
						Object value = mappedSet.get(key);
						//System.out.println(key + " => " + value);
						aBean.setAttributeValue(key, new OCVariant(value));
						
						if (!objectChanged) {
							objectChanged = true;
						}
					}
					
				}
				if (objectChanged) {
					commonObject.write();
					res++;
				}
			}
		} catch (JIException jie) {
			throw new SQLException(jie);
		}
		
		return res;
	}
	
	protected Map<String, Object> parseSimpleUpdateStatement(String statementString) throws SQLException {
		Map<String, Object> res = new HashMap<String, Object>(4);
		int updateIndex = statementString.substring(0, 10).toUpperCase().indexOf("UPDATE");
		if (updateIndex == -1) {
			throw new SQLException("Statement doesn't start from UPDATE word");
		}
		int setIndex = statementString.indexOf("SET");
		if (setIndex == -1) {
			throw new SQLException("SET section not found");
		}
		
		int whereIndex = statementString.indexOf("WHERE");
		if (whereIndex != -1) {
			res.put("WHERE", statementString.substring(whereIndex + 6));
		}
		res.put("TABLENAME", statementString.substring(7, statementString.indexOf(" ", 7)));
		
		String setsSubstr = statementString.substring(setIndex + 3, (whereIndex != -1 ? whereIndex : statementString.length())).trim();
		
		Map<String, Object> splittedSets = new HashMap<String, Object>();
		String[] ss = setsSubstr.split("AND");
		for (String ss1 : ss) {
			String[] kv = ss1.split("=");
			String key = kv[0].trim();
			String value = kv[1].trim();
			Object objectedValue = null;
			if (value.indexOf("\"") == -1) { // it's digital value
				try {
					objectedValue = Integer.valueOf(value);
				} catch (NumberFormatException nfe) {
					try {
						objectedValue = Long.valueOf(value);
					} catch (NumberFormatException nn1) {
						try {
							objectedValue = Double.valueOf(value);
						} catch (NumberFormatException nn2) {
							try {
								objectedValue = Float.valueOf(value);
							} catch (NumberFormatException nn3) {
								objectedValue = value;
							}
						}
					}
				}
			} else { // it's string
				objectedValue = value.replaceAll("\"", "");
			}
			if (objectedValue == null) {
				throw new SQLException("Can't determinate value for " + key);
			}
			splittedSets.put(key, objectedValue);
			
		}
		// список с элементами вида [АдресДоставки=new_address]
		res.put("SETS", splittedSets); 
		return res;
	}

	
	public void close() throws SQLException {
		fClose = true;
	}

	
	public int getMaxFieldSize() throws SQLException {
		return maxFieldSize;
	}

	
	public void setMaxFieldSize(int paramInt) throws SQLException {
		maxFieldSize = paramInt;
	}

	
	public int getMaxRows() throws SQLException {
		return maxRows;
	}

	
	public void setMaxRows(int paramInt) throws SQLException {
		maxRows = paramInt;
	}

	
	public void setEscapeProcessing(boolean paramBoolean) throws SQLException {
		escapeProcessing = paramBoolean;
	}

	
	public int getQueryTimeout() throws SQLException {
		return 0;
	}

	
	public void setQueryTimeout(int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void cancel() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	
	public void clearWarnings() throws SQLException {
		// NOP
	}

	
	public void setCursorName(String paramString) throws SQLException {
		cursorName = paramString;
	}
	
	protected String getCursorName() {
		return cursorName;
	}

	
	public boolean execute(String paramString) throws SQLException {
		String starting = paramString.substring(0, 10).toUpperCase();
		if (starting.startsWith("SELECT") || starting.startsWith("ВЫБРАТЬ")) {
			ResultSet rs = executeQuery(paramString);
			boolean res = false;
			if (rs != null) {
				lastVisitedResultSet = rs;
				res = true;
			}
			return res;
		} else
		if (starting.startsWith("UPDATE")) {
			throw new SQLException("Unsupported statement " + paramString);
		} else {
			throw new SQLException("Unsupported statement " + paramString);
		}
		
	}

	
	public ResultSet getResultSet() throws SQLException {
		return lastVisitedResultSet;
	}

	
	public int getUpdateCount() throws SQLException {
		return -1;
	}

	
	public boolean getMoreResults() throws SQLException {
		return false;
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
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public int getFetchSize() throws SQLException {
		return 0;
	}

	
	public int getResultSetConcurrency() throws SQLException {
		return  ResultSet.CONCUR_READ_ONLY;
	}

	
	public int getResultSetType() throws SQLException {
		return ResultSet.TYPE_FORWARD_ONLY;
	}

	
	public void addBatch(String paramString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public void clearBatch() throws SQLException {
		// NOP
	}

	
	public int[] executeBatch() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public Connection getConnection() throws SQLException {
		return connection;
	}

	
	public boolean getMoreResults(int paramInt) throws SQLException {
		return false;
	}

	
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public int executeUpdate(String paramString, int paramInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public int executeUpdate(String paramString, int[] paramArrayOfInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public int executeUpdate(String paramString, String[] paramArrayOfString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public boolean execute(String paramString, int paramInt) throws SQLException {
		if (paramInt == Statement.NO_GENERATED_KEYS) {
			return execute(paramString);
		} else {
			throw new SQLException(
					"Only Statement.NO_GENERATED_KEYS supported");
		}
	}

	
	public boolean execute(String paramString, int[] paramArrayOfInt) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public boolean execute(String paramString, String[] paramArrayOfString) throws SQLException {
		throw new SQLException(METHOD_NOT_APPLICABLE);
	}

	
	public int getResultSetHoldability() throws SQLException {
		return ResultSet.HOLD_CURSORS_OVER_COMMIT;
	}

	
	public boolean isClosed() throws SQLException {
		return fClose;
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setPoolable(boolean)
	 */
	
	public void setPoolable(boolean paramBoolean) throws SQLException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#isPoolable()
	 */
	
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

    public void closeOnCompletion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isCloseOnCompletion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
