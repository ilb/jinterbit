/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.collection.OCMetadataCatalogCollection;
import com.ipc.oce.metadata.objects.OCCatalogMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * @author Konovalov
 *
 */
public class JUCMO extends BasicTest {

	@Test
	public void findByFullName() throws JIException {
		OCConfigurationMetadataObject cmo = app.getMetadata();
		long s = System.currentTimeMillis();
		OCMetadataCatalogCollection cc = cmo.getCatalogs();
		int counter = 0;
		OCCatalogMetadataObject last = null;
		for (OCCatalogMetadataObject meta : cc) {
			counter++;
			last = meta;
		}
		System.out.println("Catalogs [" + counter + "] enum time: " + (System.currentTimeMillis() - s) + "ms");
		
		s = System.currentTimeMillis();
		_OCCommonMetadataObject oco = cmo.findByFullName(last.getFullName());
		
		System.out.println("Catalog [only one] find time: " + (System.currentTimeMillis() - s) + "ms");
		
		
	}
}
