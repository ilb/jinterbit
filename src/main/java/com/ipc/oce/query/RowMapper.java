/**
 * 
 */
package com.ipc.oce.query;


/**
 * @author Konovalov
 * 
 */
public abstract class RowMapper<T> {
	public abstract T mapRow(OCQueryResultSelection paramResultSet, int paramInt) throws Throwable;
}
