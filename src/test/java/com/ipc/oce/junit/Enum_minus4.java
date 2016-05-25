/**
 * 
 */
package com.ipc.oce.junit;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import org.jinterop.dcom.common.JIException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.OCKeyAndValue;
import com.ipc.oce.OCStructure;
import com.ipc.oce.PropertiesReader;
import com.ipc.oce.objects.OCCatalogCollection;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCConstantCollection;
import com.ipc.oce.objects.OCConstantManager;
import com.ipc.oce.objects.OCDocumentCollection;
import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCExchangePlanManager;
import com.ipc.oce.objects.OCExchangePlansCollection;
import com.ipc.oce.objects.reports.OCReportCollection;
import com.ipc.oce.objects.reports.OCReportManager;

/**
 * @author Konovalov
 *
 */
public class Enum_minus4 extends BasicTest{
	
	@Test
	public void enumStructure() throws JIException {
		OCStructure structure = app.newStructure();
		structure.insert("Key1", Integer.valueOf(1));
		structure.insert("Key2", String.valueOf(900));
		for (OCKeyAndValue keyAndValue : structure) {
			System.out.println(keyAndValue.getKey() + " = " + keyAndValue.getValue().value());
		}
	}
	
	@Test
	public void enumReportCollectionEnumeration() throws JIException {
		OCReportCollection repCollection = app.getReportCollection();
		
		Enumeration<OCReportManager> enumV = (Enumeration<OCReportManager>) repCollection.iterator();
		while (enumV.hasMoreElements()) {
			System.out.println(enumV.nextElement().toString());
		}
	}
	
	@Test
	public void enumReportCollectionIterator() throws JIException {
		OCReportCollection repCollection = app.getReportCollection();
		System.out.println("\nOCReportCollection ====================");
		Iterator<OCReportManager> enumV = repCollection.iterator();
		while (enumV.hasNext()) {
			System.out.println(enumV.next().toString());
		}
	}
	
	@Test
	public void enumExchangePlans() throws JIException {
		OCExchangePlansCollection collection = app.getExchangePlansCollection();
		System.out.println("\nOCExchangePlansCollection ====================");
		for (OCExchangePlanManager manager : collection) {
			System.out.println(manager.getMetadata().getFullName());
		}
	}
	
	@Test
	public void enumConstants() throws JIException {
		OCConstantCollection collection = app.getConstantsCollection();
		System.out.println("\nOCConstantCollection ====================");
		for (OCConstantManager manager : collection) {
			System.out.println(manager.getMetadata().getFullName());
		}
	}
	
	@Test
	public void enumCatalogs() throws JIException {
		OCCatalogCollection collection = app.getCatalogCollection();
		System.out.println("\nOCCatalogCollection ====================");
		for (OCCatalogManager manager : collection) {
			System.out.println(manager.getMetadata().getFullName());
		}
	}
	
	@Test
	public void enumDocument() throws JIException {
		OCDocumentCollection collection = app.getDocumentCollection();
		System.out.println("\nOCDocumentCollection ====================");
		for (OCDocumentManager manager : collection) {
			System.out.println(manager.getMetadata().getFullName());
		}
	}
	
	
}
