/**
 * 
 */
package com.ipc.oce.events;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jinterop.dcom.common.JIException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ipc.oce.OCApp;

/**
 * @author Konovalov
 *
 */
public class XPathSelector {
	
	private static final XPathFactory FACTORY = XPathFactory.newInstance();
	
	private OCApp instance = null;
	
	public XPathSelector(OCApp instance) {
		super();
		this.instance = instance;
	}


	/**
	 * 
	 * @param document
	 * @param xpathExpression 
	 * @return
	 * @throws XPathExpressionException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws JIException
	 */
	public List<String> selectTextContent(Document document, String xpathExpression) throws XPathExpressionException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, JIException {
		
		XPath xpath = FACTORY.newXPath();
		xpath.setNamespaceContext(instance.getNamespaceContext());
		XPathExpression expr = xpath.compile(xpathExpression);
		
		Object result = expr.evaluate(document, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		List<String> uuidList = new ArrayList<String>(nodes.getLength()); 

		for (int i = 0; i < nodes.getLength(); i++) {
			String elem = nodes.item(i).getNodeValue();
			uuidList.add(elem);
		}
		return uuidList;
	}
	
	public List<String> selectTextContent(Document document, String xpathExpression, boolean distinct) throws XPathExpressionException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, JIException {
		List<String> orig = selectTextContent(document, xpathExpression);
		List<String> res = new ArrayList<String>();
		for (String s : orig) {
			if (distinct && res.contains(s)) {
				continue;
			}
			res.add(s);
		}
		return res;
	}
	
	public int selectForInt(Document document, String expression) throws XPathExpressionException {
		XPath xpath = FACTORY.newXPath();
		xpath.setNamespaceContext(instance.getNamespaceContext());
		
		XPathExpression expr = xpath.compile(expression);
		
		String xpathRes = expr.evaluate(document);
		
		int intRes = Integer.parseInt(xpathRes);
		
		return intRes;
	}
	
}
