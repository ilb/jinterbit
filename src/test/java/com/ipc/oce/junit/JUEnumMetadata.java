/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.collection.OCMetadataEnumCollection;
import com.ipc.oce.metadata.collection.OCMetadataEnumValueCollection;
import com.ipc.oce.metadata.objects.OCEnumMetadataObject;
import com.ipc.oce.metadata.objects.OCEnumValueMetadataObject;

/**
 * @author Konovalov
 *
 */
public class JUEnumMetadata extends BasicTest {

	@Test
	public void showEnumMetadata() throws JIException {
		OCConfigurationMetadataObject cfg = app.getMetadata();
		OCMetadataEnumCollection enumCollection = cfg.getEnums();
		for (OCEnumMetadataObject metaEnum : enumCollection) {
			String name = metaEnum.getName();
			name = metaEnum.getFullName();
			System.out.println(name);
			OCMetadataEnumValueCollection enumValCollection = metaEnum.getEnumValues();
			for (OCEnumValueMetadataObject valMeta : enumValCollection) {
				System.out.println("\t" + valMeta.getName());
			}
			break; // enough
		}
	}
}
