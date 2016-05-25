/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.collection.OCMetadataChartOfCharacteristicTypeCollection;
import com.ipc.oce.metadata.objects.OCChartOfCharacteristicTypeMetadataObject;
import com.ipc.oce.objects.OCChartOfCharacteristicTypesManager;
import com.ipc.oce.objects.OCChartOfCharacteristicTypesRef;
import com.ipc.oce.objects.OCChartOfCharacteristicTypesSelection;

/**
 * @author Konovalov
 *
 */
public class TryToGetOwner extends BasicTest {

	@Test
	public void getChartOfCharacteristic() throws JIException {
		OCConfigurationMetadataObject cfgMeta = app.getMetadata();
		OCMetadataChartOfCharacteristicTypeCollection typeCollection = cfgMeta.getChartsOfCharacteristicTypes();
		for (OCChartOfCharacteristicTypeMetadataObject mo : typeCollection) {
			System.out.println(mo.toString());
		}
		
		OCChartOfCharacteristicTypesManager manager = app.getChartOfCharacteristicTypesManager("НастройкиПользователей");
		OCChartOfCharacteristicTypesSelection selection = manager.select();
		selection.next();
		OCChartOfCharacteristicTypesRef ref = selection.getRef();
		System.out.println(ref.getMetadata().getFullName());
	}
}
