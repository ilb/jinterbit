/**
 * 
 */
package com.ipc.oce.junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jinterop.dcom.common.JIException;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.ipc.oce.OCStructure;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;
import com.ipc.oce.objects.OCDocumentCollection;
import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCDocumentObject;
import com.ipc.oce.objects.OCDocumentRef;
import com.ipc.oce.objects.OCDocumentSelection;
import com.ipc.oce.objects.OCSequenceRecord;
import com.ipc.oce.objects.OCSequenceRecordSet;
import com.ipc.oce.objects.OCSequenceRecordSetCollection;
import com.ipc.oce.objects.OCUUID;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.ipc.oce.xml.oc.OCXMLWriter;

/**
 * @author Konovalov
 *
 */
public class JUDocuments extends BasicTest {

	@Test
	public void getCollectionAndIterate() throws JIException {
		OCDocumentCollection docCollection = app.getDocumentCollection();
		int docCount = 0;
		for (OCDocumentManager manager : docCollection) {
			docCount++;
		}
		System.out.println("Total documents: " + docCount);
	}
	
	@Test
	public void getMetadataViaManager() throws JIException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		OCDocumentMetadataObject meta = manager.getMetadata();
		System.out.println("getFullName: " + meta.getFullName());
		System.out.println("getComment: " + meta.getComment());
		System.out.println("getNumberLength: " + meta.getNumberLength());
		System.out.println("getPresentation: " + meta.getPresentation());
		System.out.println("checkUnique: " + meta.checkUnique());
		System.out.println("getSynonym : " + meta.getSynonym());
		System.out.println("getAttributes: " + meta.getAttributes());
		System.out.println("getBasedOn: " + meta.getBasedOn());
		System.out.println("getDataLockControlMode: " + meta.getDataLockControlMode());
		System.out.println("getNumberType: " + meta.getNumberType());
		System.out.println("getNumberPeriodicity: " + meta.getNumberPeriodicity());
		System.out.println("getNumerator: " + meta.getNumerator());
		System.out.println("getParent: " + meta.getParent());
		System.out.println("getPosting: " + meta.getPosting());
		System.out.println("getRealTimePosting: " + meta.getRealTimePosting());
		System.out.println("getTabularSections: " + meta.getTabularSections());
		System.out.println("isAutoNumbering: " + meta.isAutoNumbering());
		System.out.println(meta.toString());
	}
	
	@Test
	public void findByNumber() throws JIException, ParseException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		System.out.println(manager.getManagerName());
		System.out.println(manager.getMetadata().getFullName());
		// ------------------------
		OCStructure struct = manager.createEmptyStruct();
		
		//-------------------------
		String number = "102-И09-8x";
		Date date = (new SimpleDateFormat("yyyy")).parse("2009");
		OCDocumentRef ref0 = manager.findByNumber(number);
		System.out.println(ref0.isEmpty() + " " + ref0.getUUID());
		assertTrue(ref0.isEmpty());
		
		OCDocumentRef ref1 = manager.findByNumber(number, date);
		System.out.println(ref1.getNumberAsString() + " " + ref1.isEmpty() + " " + ref1.getUUID());
		assertFalse(ref1.isEmpty());
		assertTrue(ref1.getNumberAsString().trim().equals(number));
	}
	
	@Test
	public void findByNumber2() throws JIException, ParseException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		String number = "102-И09-8x";
		Date date = (new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")).parse("14.12.2009 12:12:10");
		
		// without date
		OCDocumentRef[] refs = manager.findByNumber2(number);
		assertTrue(refs != null);
		assertTrue(refs.length == 1);
		assertTrue(refs[0].getNumberAsString().trim().equals(number));
		
		// with date
		OCDocumentRef ref = manager.findByNumber2(number, date);
		System.out.println(ref);
		assertFalse(ref.isEmpty());
		assertTrue(refs[0].getNumberAsString().trim().equals(number));
	}
	
	@Test
	public void emptyRef() throws JIException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		String fullName = manager.getMetadata().getFullName();
		OCDocumentRef ref = manager.emptyRef();
		assertTrue(ref.isEmpty());
		assertTrue(ref.getUUID().toString().equals("00000000-0000-0000-0000-000000000000"));
		assertTrue(ref.getMetadata().getFullName().equals(fullName));
	}
	
	@Test
	public void select() throws JIException {
		
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		OCDocumentSelection selection = manager.select();
		int stopFactor = 5000;
		while (selection.next() && (stopFactor--) != 0) {
			selection.isDeletionMark();
			selection.isPosted();
			assertTrue(selection.getNumberAsString().equals(selection.getRef().getNumberAsString()));
		}
		
	}
	
	@Test
	public void selectWithDate() throws JIException, ParseException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		Date date = (new SimpleDateFormat("yyyy")).parse("2009");
		Date date2 = (new SimpleDateFormat("yyyy")).parse("2010");
		OCDocumentSelection selection = manager.select(date, date2); // select all docs in 2009
		Calendar cal = Calendar.getInstance();
		while (selection.next()) {
			Date selDate = selection.getDate();
			//System.out.println(selDate);
			cal.setTime(selDate);
			int year = cal.get(Calendar.YEAR);
			assertTrue(year == 2009);
		}
		
		selection = manager.select(date);
		while (selection.next()) {
			Date selDate = selection.getDate();
			//System.out.println(selDate);
			cal.setTime(selDate);
			int year = cal.get(Calendar.YEAR);
			assertTrue(year >= 2009);
		}
	}
	
	@Test
	public void createAndDelete() throws JIException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		OCDocumentObject doc = manager.createDocument();
		System.out.println("isNew1: " + doc.isNew() + " [before write]");
		doc.setNumber("XXX-000-002");
		doc.setDate(new Date());
		doc.write();
		System.out.println("isNew2: " + doc.isNew() + " [after write]");
		OCUUID uuid = doc.getRef().getUUID();
		System.out.println(doc.getNumberAsString() + " created with " + uuid);
		
		OCDocumentRef ref = manager.getRef(uuid);
		
		String u = ref.getUUID().toString();
		ref.getObject().delete();
		System.out.println("...and deleted [" + u + "]");
	}
	
	@Test
	public void belongToSequences() throws JIException, ParseException {
		System.out.println(" === belong ===");
		OCDocumentManager manager = app.getDocumentManager("ПриемНаРаботуВОрганизацию");
		OCDocumentSelection selection = manager.select();
		selection.next();
		
		OCSequenceRecordSetCollection collection = selection.getObject().getBelongingToSequences();
		System.out.println(collection + " size " + collection.size());
		for (OCSequenceRecordSet set : collection) {
			System.out.println("\t" + set + " size " + set.size());
			for (OCSequenceRecord record : set) {
				System.out.println("\t\t" + record);
			}
		}
	}
	
	@Test
	public void listAttrNames() throws JIException {
		OCDocumentManager manager = app.getDocumentManager("ПриемНаРаботуВОрганизацию");
		OCDocumentSelection selection = manager.select();
		selection.next();
		OCDocumentObject object = selection.getObject();
		System.out.println(object.getMetadata().getAttributesName());
		System.out.println(object.getMetadata().getTabularSectionsName());
	}
	
	@Test
	public void showNewObjectsXML() throws JIException {
		OCDocumentManager manager = app.getDocumentManager("ПриемНаРаботуВОрганизацию");
		OCDocumentObject doc = manager.createDocument();
		OCXDTOSerializer serializer = app.getXDTOSerializer();
		OCXMLWriter writer = app.newXMLWriter();
		writer.setString();
		serializer.writeXML(writer, doc);
		System.out.println(writer.close());
	}
	
	@Ignore
	@Test
	public void deleteDoc() throws JIException, ParseException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		OCDocumentRef ref = manager.findByNumber("XXX-000-001", (new SimpleDateFormat("yyyy")).parse("2011"));
		System.out.println("FOR DELTE " + ref.getUUID());
		OCDocumentObject docObj = ref.getObject();
		docObj.delete();
		System.out.println("DELETED");
	}
}
