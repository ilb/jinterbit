/**
 * 
 */
package com.ipc.oce.junit;

import static org.junit.Assert.*;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCCatalogRef;
import com.ipc.oce.objects.OCCatalogSelection;

/**
 * @author Konovalov
 *
 */
public class CatalogParent extends BasicTest {

	@Test
	public void getCatalogSuperParent() throws JIException {
		OCCatalogManager manager = app.getCatalogManager("Валюты");
		OCCatalogSelection selection = manager.select();
		selection.next();
		OCCatalogRef ref = selection.getRef();
		
		assertTrue(!ref.isEmpty());
		
		OCCatalogRef parRef = ref.getParent();
		assertTrue(parRef.isEmpty());
	}
}
