/**
 * 
 */
package com.ipc.oce.xml.oc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ipc.oce.DynamicNamespaceContext;
import com.ipc.oce.OCApp;
import com.ipc.oce.xml.xerces.Utils;

/**
 * @author Konovalov
 *
 */
public class XPathDataMapper {
	
	/**
	 * Фабрика XPath.
	 */
	private static final transient XPathFactory FACTORY = XPathFactory.newInstance();
	
	private OCApp application = null;
	
	private DynamicNamespaceContext namespaceContext = null;
	
	private Map<String, String> xPathMap = null; // 
	
	private Map<String, Class> classCastMap = null;
	
	private Map<String, Object> lastResMap = null;
	
	//private static final transient Log LOG = LogFactory.getLog(XPathDataMapper.class);
	

	/**
	 * Конструктор mappings.
	 * xpathMap - это наборы XPath и наименований
	 */
	public XPathDataMapper(OCApp application, Map<String, String> xpathMap) {
		if (xpathMap == null) {
			throw new NullPointerException("XPath map is null!");
		}
		this.xPathMap = xpathMap;
		this.application = application;
		namespaceContext = ((DynamicNamespaceContext) application.getNamespaceContext()).copy();
	}
	
	private Object castFromString(String refName, String value) {
		if (classCastMap != null) {
			Object res = value;
			// TODO кастинг типов надо доделать, пока возвращается как есть - String
			return res;
		} else {
			return value;
		}
	}
	
	public XPathDataMapper(OCApp application, Map<String, String> xpathMap, Map<String, Class> classCasting) {
		this(application,xpathMap);
		this.classCastMap = classCasting;
	}

	public void addNamespace(String prefix, String uri) {
		namespaceContext.addNamespace(prefix, uri);
	}

	public Map<String, Object> process(String xml) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		return process(Utils.xml2Document(xml));
	}
	
	public Map<String, Object> process(Document xml) throws XPathExpressionException {
		Map<String, Object> res = new LinkedHashMap<String, Object>();
		Iterator<String> iter = xPathMap.keySet().iterator();
		while (iter.hasNext()) {
			String xpathS = iter.next();
			
			XPath xpath = FACTORY.newXPath();
			xpath.setNamespaceContext(namespaceContext);
			XPathExpression expr = xpath.compile(xpathS);
			String xpathRes = expr.evaluate(xml);
			
			String mappedKey = xPathMap.get(xpathS);
			res.put(mappedKey, castFromString(mappedKey, xpathRes));
		}
		lastResMap = res;
		return res;
	}
	
	public void applyToBean(Object bean, String xml) throws XPathExpressionException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException{
		applyToBean(bean, Utils.xml2Document(xml));
	}
	
	@SuppressWarnings("rawtypes")
	public void applyToBean(Object bean, Document xml) throws XPathExpressionException, IllegalAccessException, InvocationTargetException{
		Class beanClas = bean.getClass();
		Map<String, Object> mapped = process(xml);
		Method[] methods = beanClas.getMethods();
		
		Iterator<String> mappedIteration = mapped.keySet().iterator();
		while (mappedIteration.hasNext()) {
			String fieldName = mappedIteration.next();
			String fieldName1 = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			String setter = "set" + fieldName1;
			for (Method method : methods) {
				if (method.getName().equals(setter)) {
					try {
						method.setAccessible(true);
					} catch (SecurityException se) {
						se.printStackTrace();
					}
					method.invoke(bean, mapped.get(fieldName));
					break;
				}
			}
		}
	}
	
	public boolean hasLastMap() {
		return lastResMap != null;
	}
	
	public Map<String, Object> getLastMap() {
		return lastResMap;
	}
}
