/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.StaticFieldInstance;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.objects.OCDocumentObject;
import com.ipc.oce.xml.oc.OCDOMWriter;
import com.ipc.oce.xml.oc.OCXDTOFactory;
import com.ipc.oce.xml.oc.OCXDTOPackage;
import com.ipc.oce.xml.oc.OCXDTOPackageCollection;
import com.ipc.oce.xml.oc.OCXMLDataType;
import com.ipc.oce.xml.oc.OCXMLSchema;
import com.ipc.oce.xml.oc.OCXMLSchemaSet;
import com.ipc.oce.xml.oc.OCXMLWriter;
import com.ipc.oce.xml.oc.OCXSBasicComponent;
import com.ipc.oce.xml.oc.OCXSComponentFixedList;
import com.ipc.oce.xml.oc.OCXSComponentType;
import com.ipc.oce.xml.oc.OCXSNamedComponentMap;

/**
 * @author Konovalov
 *
 */
public class SchemasDance extends BasicTest {

	@Test
	public void gettingSchema() throws JIException {
		OCDocumentObject documentObject = getRandomDocument("СчетНаОплатуПокупателю");
		OCXMLDataType dt = app.getXMLTypeOf(documentObject);
		System.out.println(dt.getTypeName());
		
		OCXDTOFactory factory = app.getXDTOFactory();
		OCXDTOPackageCollection pacCollection = factory.getPackages();
		
		System.out.println("PACKAGES: --------------------------");
		for (OCXDTOPackage package1 : pacCollection) {
			System.out.println(package1.getNamespaceURI());
		}
		String sSchema = "http://v8.1c.ru/8.1/data/enterprise/current-config";
		System.out.println("SCHEMA ------------------------------");
		OCXMLSchemaSet schemaSet = factory.exportXMLSchema(sSchema);
		System.out.println("Schema set size: " + schemaSet.size());
		OCXMLSchema schema = schemaSet.getSchema(0);
		System.out.println("Schema component type: " + schema.getComponentType());
		
		OCXSComponentFixedList schemaComponents = schema.getComponents();
		int schemaElements = schemaComponents.size();
		System.out.println("Schema {" + sSchema + "} contains " + schemaElements + " elements");
		
		OCXSBasicComponent basicComponent = schemaComponents.get(0);
		System.out.println("isType: " + basicComponent.isTypeOf(OCXSComponentType.IMPORT));
		
		
		StaticFieldInstance sfi = app.getStaticFields("XSComponentType.Annotation");
		System.out.println(sfi);
		System.out.println(basicComponent.getComponentType().equals(sfi));
		System.out.println("FIRST END =-====================-=");
	}
	
	@Test
	public void dance2() throws JIException {
		System.out.println();
		OCDocumentObject document = getRandomDocument("СчетНаОплатуПокупателю");
		OCXMLDataType xmlType = document.getXMLType();
		OCType type = document.getOCType();
		System.out.println(xmlType.getTypeName() + " in {" +xmlType.getNamespaceURI() + "}");
		System.out.println(type);
		
		OCXDTOFactory factory = app.getXDTOFactory();
		String sSchema = "http://v8.1c.ru/8.1/data/enterprise/current-config";
		OCXMLSchemaSet schemaSet = factory.exportXMLSchema(sSchema);
		OCXMLSchema schema = schemaSet.getSchema(0);
		
		OCXSNamedComponentMap typeMap = schema.getTypeDefinitions();
		OCXSBasicComponent bc = typeMap.getByName(xmlType.getTypeName());
		System.out.println("BC toString: " + bc);
		
		System.out.println("DOMElement of BC: " + bc.getDOMElement());
		
		OCXMLWriter xmlWriter = app.newXMLWriter();
		xmlWriter.setString();
		
		OCDOMWriter domWriter = app.newDOMWriter();
		domWriter.write(bc.getDOMElement(), xmlWriter);
		
		String res = xmlWriter.close();
		System.out.println(res);
	}
	
	@Test
	public void dance3() throws JIException {
		OCXDTOFactory factory = app.getXDTOFactory();
		String cfURI = factory.getCurrentConfigURI();
		System.out.println(cfURI);
		OCXMLSchema schema = factory.exportXMLSchema(cfURI).getSchema(cfURI);
		OCXSBasicComponent bc = schema.getTypeDefinitionByName("DocumentTabularSectionRow.СчетНаОплатуПокупателю.ВозвратнаяТара");
		
		OCXMLWriter xmlWriter = app.newXMLWriter();
		xmlWriter.setString();
		
		OCDOMWriter domWriter = app.newDOMWriter();
		domWriter.write(bc.getDOMElement(), xmlWriter);
		
		String res = xmlWriter.close();
		System.out.println(res);
	}
	
	@Test
	public void compare() {
		System.out.println("COMPARE");
		System.out.println(Integer.valueOf(-130) == Integer.valueOf(-130)); // логично. http://govnokod.ru/4862
		System.out.println(Integer.valueOf(-130) == -130);
	}
}
