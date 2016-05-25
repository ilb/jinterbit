package com.ipc.oce.metadata.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;


public class OCMetadataDocumentCollection extends _OCMetadataObjectCollection<OCDocumentMetadataObject> {
	private static String[] documentsName = null;
	public OCMetadataDocumentCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataDocumentCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCDocumentMetadataObject get(int i) throws JIException {
		return new OCDocumentMetadataObject(super.get(i));
	}
	
	
	
	@Override
	public OCDocumentMetadataObject find(String objectName) throws JIException {
		OCObject object = super.find(objectName);
		if (object != null) {
			return new OCDocumentMetadataObject(object);
		} else {
			return null;
		}
	}

	/**
	 * Получение списка имен (типов) документов.
	 * @return массив имен документов
	 * @throws JIException
	 */
	public synchronized String[] getDocumentNames() throws JIException {
		if (documentsName == null) {
			OCApp app = OCApp.getInstance(getAssociatedSessionID());
			OCConfigurationMetadataObject metadata = app.getMetadata();
			OCMetadataDocumentCollection docCollection = metadata
					.getDocuments();
			int docTypeCount = docCollection.size();
			List<String> dcNames = new ArrayList<String>();
			for (int z = 0; z < docTypeCount; z++) {
				dcNames.add(docCollection.get(z).getName());
			}
			documentsName = dcNames.toArray(new String[docTypeCount]);
		}
		return documentsName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDocumentMetadataObject> iterator() {
		EnumVARIANT<OCDocumentMetadataObject> enumV = new EnumVARIANT<OCDocumentMetadataObject>(this) {
			
			@Override
			protected OCDocumentMetadataObject castToGeneric(JIVariant variant) {
				try {
					OCDocumentMetadataObject meta = new OCDocumentMetadataObject(variant);
					return meta;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
}
