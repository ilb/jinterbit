/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.OCKeyAndValue;
import com.ipc.oce.OCStructure;
import com.ipc.oce.metadata.collection.OCMetadataResourceCollection;
import com.ipc.oce.metadata.objects.OCInformationRegisterMetadataObject;
import com.ipc.oce.metadata.objects.OCResourceMetadataObject;
import com.ipc.oce.objects.OCInformationRegisterCollection;
import com.ipc.oce.objects.OCInformationRegisterManager;
import com.ipc.oce.objects.OCInformationRegisterSelection;

/**
 * @author Konovalov
 *
 */
public class JUInfoReg extends BasicTest {

	@Test
	public void iterateRegister() throws JIException {
		
		OCInformationRegisterCollection collection = app.getInformationRegisterCollection();
		for (OCInformationRegisterManager manager : collection) {
			System.out.println(manager.getManagerName());
		}
		System.out.println("===============================================\n");
	}
	
	@Test
	public void showMetadataViaManager() throws JIException {
		OCInformationRegisterCollection collection = app.getInformationRegisterCollection();
		OCInformationRegisterManager manager = collection.getInformationRegister("АдресныйКлассификатор");
		
		OCInformationRegisterMetadataObject metadata = manager.getMetadata();
		System.out.println("getFullName: " + metadata.getFullName());
		
		OCMetadataResourceCollection resourceCollection = metadata.getResources();
		System.out.println("==== RESOURCES ====");
		for (OCResourceMetadataObject resourceMeta : resourceCollection) {
			System.out.println("\t" + resourceMeta.getName());
		}
		System.out.println("===============================================\n");
	}
	
	@Test
	public void getSelectionWithNullParams() throws JIException {
		OCInformationRegisterCollection collection = app.getInformationRegisterCollection();
		OCInformationRegisterManager manager = collection.getInformationRegister("АдресныйКлассификатор");
		OCInformationRegisterSelection selection = manager.select(null, null, null, null);
		int stopFactor = 2;
		while ((stopFactor--) != 0 && selection.next()) {
			System.out.println("stop factor: " + stopFactor);
			System.out.println("\tLineNumber: " + selection.getLineNumber());
			System.out.println("\tТипАдресногоЭлемента: " + selection.getDimension("ТипАдресногоЭлемента"));
			System.out.println("\tКодРегионаВКоде: " + selection.getDimension("КодРегионаВКоде"));
			System.out.println("\tКодУлицыВКоде: " + selection.getDimension("КодУлицыВКоде"));
			System.out.println("\tКод: " + selection.getDimension("Код") + " [" + selection.getDimension("Код").value().getClass().getName()+"]");
			
			System.out.println("\tНаименование: " + selection.getResource("Наименование"));
			System.out.println("\tИндекс: " + selection.getResource("Индекс"));
			
			System.out.println("\tRecorder: " + selection.getRecorder());
		}
		//System.out.println("Selection size: " + selection.size()); // never call it
		System.out.println("===============================================\n");
		
	}
	
	@Test
	public void getLast() throws JIException {
		System.out.println("==== GET LAST ====");
		OCInformationRegisterCollection collection = app.getInformationRegisterCollection();
		OCInformationRegisterManager manager = collection.getInformationRegister("ВстречныйВыпускПродукцииУслуг");
		OCStructure struct = manager.getLast(null, null);
		for (OCKeyAndValue kav : struct) {
			System.out.println(kav.getKey() + " = " + kav.getValue());
		}
		
		struct = manager.getFirst(null, null);
		for (OCKeyAndValue kav : struct) {
			System.out.println(kav.getKey() + " = " + kav.getValue());
		}
	}
}
