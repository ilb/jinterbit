/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCDocumentRef;
import com.ipc.oce.objects.OCDocumentSelection;

/**
 * @author Konovalov
 *
 */
public class GetSomeUUID extends BasicTest {

	@Test
	public void getDocumentUUID() throws JIException {
		OCDocumentManager manager = app.getDocumentManager("СчетНаОплатуПокупателю");
		OCDocumentSelection selection = manager.select();
		selection.next();
		OCDocumentRef ref = selection.getRef();
		System.out.println(ref.getRefFullName() + " " + ref.getUUID().toString());
	}
	
	public void getCatalogUUID() {
		
	}
}
