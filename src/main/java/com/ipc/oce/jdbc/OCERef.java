/**
 * 
 */
package com.ipc.oce.jdbc;

import java.sql.Ref;
import java.sql.SQLException;
import java.util.Map;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.objects.OCUUID;
import com.ipc.oce.objects._OCCommonObject;
import com.ipc.oce.objects._OCCommonRef;

/**
 * @author Konovalov
 *
 */
public class OCERef implements Ref {
	
	private _OCCommonRef commonRef = null;
	
	protected OCERef(_OCCommonRef commonRef) {
		super();
		this.commonRef = commonRef;
	}

	public String getBaseTypeName() throws SQLException {
		try {
			return commonRef.getRefFullName(); // выдает что-то около Документ.СчетНаОплатуПокупателю
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}


	public Object getObject(Map<String, Class<?>> paramMap) throws SQLException {
		return getObject();
	}

	public Object getObject() throws SQLException {
		
		try {
			return commonRef.getObject();
		} catch (JIException e) {
			throw new SQLException(e);
		}
	}

	public void setObject(Object paramObject) throws SQLException {
		if (paramObject == null) {
			throw new SQLException("Object is null");
		}
		if (!(paramObject instanceof _OCCommonObject)) {
			try {
				commonRef = ((_OCCommonObject) paramObject).getRef();
			} catch (JIException e) {
				throw new SQLException(e);
			}
		} else {
			throw new SQLException("Instance of object isn't " 
					+ _OCCommonObject.class.getName());
		}
		
	}
	
	public OCUUID getUUID() throws JIException {
		return commonRef.getUUID();
	}
	
	protected _OCCommonRef getRef() {
		return commonRef;
	}

	@Override
	public String toString() {
		try {
			return commonRef.getUUID().toString();
		} catch (JIException e) {
			return "";
		}
	}
}
