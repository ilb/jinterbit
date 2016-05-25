/**
 * 
 */
package com.ipc.oce;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * @author Konovalov
 *
 */
public class DynamicNamespaceContext implements NamespaceContext {
	
	private Map<String, String> namespaces = null;
	
	/**
	 * 
	 */
	public DynamicNamespaceContext() {
		namespaces = new HashMap<String, String>();
		addNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
		addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		addNamespace("xml", XMLConstants.XML_NS_URI);
	}
	
	/**
	 * Добавить новый namespace.
	 * @param prefix - префиксю
	 * @param uri - URI
	 */
	public synchronized void addNamespace(String prefix, String uri) {
		namespaces.put(prefix, uri);
	}
	
	public synchronized void removeNamespacePrefix(String prefix) {
		namespaces.remove(prefix);
	}
	
	public synchronized void clearNamespaces() {
		namespaces.clear();
	}
	
	public String getNamespaceURI(String prefix) {
		String tmpURI = namespaces.get(prefix);
		return tmpURI != null ? tmpURI : XMLConstants.NULL_NS_URI;
	}

	
	public String getPrefix(String arg0) {
		throw new UnsupportedOperationException();
	}

	public Iterator<String> getPrefixes(String arg0) {
		throw new UnsupportedOperationException();
	}
	
	private Map<String, String> getNamespaceMapping() {
		return namespaces;
	}
	
	private synchronized void setNamespaceMapping(Map<String, String> map) {
		this.namespaces = map;
	}

	public synchronized DynamicNamespaceContext copy() {
		DynamicNamespaceContext dnc = new DynamicNamespaceContext();
		Map<String, String> m1 = new HashMap<String, String>();
		Map<String, String> m2 = getNamespaceMapping();
		Set<Entry<String, String>> s = m2.entrySet();
		for (Iterator<Entry<String, String>> iterator = s.iterator(); iterator.hasNext();) {
			Entry<String, String> entry = (Entry<String, String>) iterator.next();
			m1.put(entry.getKey(), entry.getValue());
		}
		dnc.setNamespaceMapping(m1);
		
		return dnc;
	}
	
	

}
