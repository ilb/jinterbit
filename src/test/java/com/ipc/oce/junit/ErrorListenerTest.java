/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.OCObject;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCDocumentManager;

/**
 * @author Konovalov
 *
 */
public class ErrorListenerTest extends BasicTest {

	@Test
	public void doCatalogError() {
		try {
			app.setErrorListener(new com.ipc.oce.ErrorListener() {

				public void onError(OCObject ocObject, Exception exception) {
					System.out.println("Object " + ocObject.toString() + " has error");
				}
				
			});
			
			OCCatalogManager catManager = app.getCatalogManager("Валюты1");
			catManager.emptyRef();
			
		} catch (JIException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void doDocumentError() {
		try {
			app.setErrorListener(new com.ipc.oce.ErrorListener() {

				public void onError(OCObject ocObject, Exception exception) {
					System.out.println("Object " + ocObject.toString() + " has error");
				}
				
			});
			
			OCDocumentManager catManager = app.getDocumentManager("Валюты1");
			catManager.emptyRef();
			
		} catch (JIException e) {
			
			e.printStackTrace();
		}
	}
}
