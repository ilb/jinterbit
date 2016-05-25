/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCDocumentObject;
import com.ipc.oce.objects.OCDocumentSelection;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.ipc.oce.xml.oc.OCXMLReader;
import com.ipc.oce.xml.oc.OCXMLWriter;

import static org.junit.Assert.*;

/**
 * @author Konovalov
 *
 */
public class JUXDTO extends BasicTest {

	@Test
	public void object2xmlAndViceversa() throws JIException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		OCDocumentSelection sel = manager.select();
		sel.next();
		OCDocumentObject documentObject = sel.getRef().getObject();
		String originalDocNum = documentObject.getNumberAsString();
		boolean originalIsNew = documentObject.isNew();
		
		OCXDTOSerializer serializer = app.getXDTOSerializer();
		OCXMLWriter writer = app.newXMLWriter();
		writer.setString("UTF-8");
		
		serializer.writeXML(writer, documentObject);
		String xml = writer.close();
		System.out.println("XML size: " + xml.length());
		
		OCXMLReader reader = app.newXMLReader();
		reader.setString(xml);
		
		OCDocumentObject reverseObject = new OCDocumentObject(serializer.readXML(reader));
		
		assertTrue(originalIsNew == reverseObject.isNew());
		assertTrue(originalDocNum.equals(reverseObject.getNumberAsString()));
	}
}
