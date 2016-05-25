/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCVariant;

/**
 * @author Konovalov
 *
 */
public interface AttributeSetter {
	void setAttributeValue(String attributeName, OCVariant variant) throws JIException;
}
