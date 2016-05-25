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
public interface AttributeGetter {
	OCVariant getAttributeValue(String attributeName) throws JIException;
}
