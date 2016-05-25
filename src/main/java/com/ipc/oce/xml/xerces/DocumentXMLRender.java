/**
 * 
 */
package com.ipc.oce.xml.xerces;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jinterop.dcom.common.JIException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataTabularSectionCollection;
import com.ipc.oce.metadata.objects.OCAttributeMetadataObject;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;
import com.ipc.oce.metadata.objects.OCTabularSectionMetadataObject;
import com.ipc.oce.objects.OCDocumentObject;
import com.ipc.oce.objects.OCEnumRef;
import com.ipc.oce.objects.OCTabularSectionManager;
import com.ipc.oce.objects.OCTabularSectionRow;
import com.ipc.oce.objects._OCCommonRef;


/**
 * @author Konovalov
 * 
 */
public class DocumentXMLRender {
	private boolean showRefGUID = false;
	private boolean showRefType = true;
	private boolean showIsRefAttribute = true;
	private boolean showRootGUID = true;
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	//===================================================
	private OCDocumentObject documentObject = null;
	private SimpleDateFormat sdFormat = null;

	/**
	 * 
	 */
	public DocumentXMLRender(OCDocumentObject documentObject) {
		this.documentObject = documentObject;
		sdFormat = new SimpleDateFormat(dateFormat);
	}

	public Document applyRender() throws ParserConfigurationException, JIException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String ns = XMLConstants.IPC_NS;
		OCDocumentMetadataObject metadata = documentObject.getMetadata();

		DocumentBuilder db = dbf.newDocumentBuilder();

		// create an instance of DOM
		Document doc = db.newDocument();
		
		// create root element
		String docTypeName = metadata.getName();
		Element root = doc.createElementNS(ns, XMLConstants.DOC_OBJ_PREF); 
		root.setAttribute("name", docTypeName);
		
		if (showRootGUID) {
			root.setAttribute("guid", documentObject.getRef()
					.getUUID().toString());
		}
	
		
		// create number element 
		Element number = doc.createElementNS(ns, "Number");
		number.setTextContent(documentObject.getNumberAsString());
		root.appendChild(number);
		
		// create date element
		Element date = doc.createElementNS(ns, "Date");
		date.setTextContent(sdFormat.format(documentObject.getDate()));
		root.appendChild(date);
		
		// create isPosted
		Element isPosted = doc.createElementNS(ns, "Posted");
		isPosted.setTextContent(String.valueOf(documentObject.isPosted()));
		root.appendChild(isPosted);
		
		// create isDeleted
		Element isDeleted = doc.createElementNS(ns, "DeletionMark");
		isDeleted.setTextContent(String.valueOf(documentObject.isDeletionMark()));
		root.appendChild(isDeleted);
		
		// create attribute elements
		Element attrElement = doc.createElementNS(ns, "Attributes");
		root.appendChild(attrElement);
		OCMetadataAttributeCollection attributeMetaCollection = metadata.getAttributes();
		OCAttributeMetadataObject amoTmp = null;
		int amcSZ = attributeMetaCollection.size();
		for (int z = 0; z < amcSZ; z++) {
			amoTmp = attributeMetaCollection.get(z);
			String oName = amoTmp.getName();
			//String tName = transliterate(oName);
			Element elem = doc.createElementNS(ns, "Attribute");
			elem.setAttribute("name", oName);
			
			OCVariant var = documentObject.getAttributeValue(oName);
			Object varValue = var.value();
			int typeCode = var.getTypeCode();
			boolean isRef = (typeCode > 99);
			
			Object attrVal = documentObject.getAttributeValue(oName).value();
			
			//handle different type of types
			if (attrVal instanceof Date) {
				attrVal = sdFormat.format(attrVal);
			}
			
			elem.setTextContent(attrVal.toString());
			
			if (showIsRefAttribute) {
				elem.setAttribute("isRef", String.valueOf(isRef));
			}
			
			if (isRef) {
				if (showRefType && varValue instanceof _OCCommonRef) {
					elem.setAttribute("refType", ((_OCCommonRef) varValue).getMetadata().getFullName());
				} else
				if (showRefType && varValue instanceof OCEnumRef ) {
					elem.setAttribute("refType", ((OCEnumRef) varValue).getMetadata().getFullName());
				}
				if (showRefGUID && varValue instanceof _OCCommonRef) {
					elem.setAttribute("guid", ((_OCCommonRef) varValue).getUUID().toString());
				}
			}
			attrElement.appendChild(elem);
		}
		
		// create table section 
		Element tabSections = doc.createElementNS(ns, "TabularSections");
		root.appendChild(tabSections);
		OCMetadataTabularSectionCollection tabSecCollection = metadata.getTabularSections();
		int tabSecColSZ = tabSecCollection.size();
		for (int z = 0; z < tabSecColSZ; z++) {
			OCTabularSectionMetadataObject tsmO = tabSecCollection.get(z);
			OCMetadataAttributeCollection tsAttrMetadata = tsmO.getAttributes();
			String tabSecName = tsmO.getName();
			
			Element tabSection = doc.createElementNS(ns, "TabularSection");
			tabSection.setAttribute("name", tabSecName);
			tabSections.appendChild(tabSection);
			
			OCTabularSectionManager tsm = documentObject.getTabularSection(tabSecName);
			int tsRowCnt = tsm.size();
			tabSection.setAttribute("rows", String.valueOf(tsRowCnt));
			for (int g = 0; g < tsRowCnt; g++) {
				OCTabularSectionRow row = tsm.get(g);
				Element rowElement = doc.createElementNS(ns, "TabularRow");
				tabSection.appendChild(rowElement);
			}
			
		}
		
		doc.appendChild(root);
		
		return doc;
	}
	
	@SuppressWarnings("unused")
	private static String transliterate(String str){
		return Transliterator.ru2lat(str);
	}
}
