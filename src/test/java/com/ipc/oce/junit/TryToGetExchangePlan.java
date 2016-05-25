/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.collection.OCMetadataExchangePlanCollection;
import com.ipc.oce.metadata.objects.OCExchangePlanMetadataObject;
import com.ipc.oce.objects.OCExchangePlanManager;
import com.ipc.oce.objects.OCExchangePlanRef;
import com.ipc.oce.objects.OCExchangePlanSelection;
import com.ipc.oce.objects.OCExchangePlansCollection;

/**
 * @author Konovalov
 *
 */
public class TryToGetExchangePlan extends BasicTest {

	@Test
	public void doTest() throws JIException {
		OCConfigurationMetadataObject cfgMeta = app.getMetadata();
		OCMetadataExchangePlanCollection planCollection = cfgMeta.getExchangePlans();
		for (OCExchangePlanMetadataObject mo : planCollection) {
			System.out.println(mo.toString());
		}
		OCExchangePlanManager manager = app.getExchangePlanManager("ОбменРозницаБухгалтерия");
		OCExchangePlanSelection selection = manager.select();
		selection.next();
		OCExchangePlanRef ref = selection.getRef();
		OCExchangePlanMetadataObject refMo = ref.getMetadata();
		System.out.println("* " + refMo.getFullName());
	}
}
