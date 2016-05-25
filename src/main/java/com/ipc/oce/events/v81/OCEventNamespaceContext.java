/**
 * 
 */
package com.ipc.oce.events.v81;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * @author Konovalov
 * 
 */
public class OCEventNamespaceContext implements NamespaceContext {


	public String getNamespaceURI(String prefix) {
		if (prefix == null) {
			throw new NullPointerException("Null prefix");
		} else if ("v8e".equals(prefix)) {
			return "http://v8.1c.ru/eventLog";
		} else if ("xsd".equals(prefix)) {
			return "http://www.w3.org/2001/XMLSchema";
		} else if ("xsi".equals(prefix)) {
			return "http://www.w3.org/2001/XMLSchema-instance";
		} else if ("xml".equals(prefix)) {
			return XMLConstants.XML_NS_URI;
		}
		return XMLConstants.NULL_NS_URI;
	}

	public String getPrefix(String paramString) {
		 throw new UnsupportedOperationException();
	}

	public Iterator<String> getPrefixes(String paramString) {
		 throw new UnsupportedOperationException();
	}
}
