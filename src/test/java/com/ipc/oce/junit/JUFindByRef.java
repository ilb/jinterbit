/**
 * 
 */
package com.ipc.oce.junit;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCValueTable;
import com.ipc.oce.OCValueTableColumn;
import com.ipc.oce.OCValueTableColumnCollection;
import com.ipc.oce.OCValueTableRow;
import com.ipc.oce.OCVariant;
import com.ipc.oce.objects._OCCommonRef;

/**
 * @author Konovalov
 *
 */
public class JUFindByRef extends BasicTest {

	@Test
	public void findByRef1() throws JIException {
		OCArray removedRefs = app.findMarkedForDeletion();
		System.out.println("Removes size: " + removedRefs.size());
		int index = 1;
		for (OCVariant remRef : removedRefs) {
			_OCCommonRef ref = ((_OCCommonRef)remRef.value());
			System.out.println("\t" + index++ + " " + ref.toString() + " [" +ref.getUUID().toString() + "]");
		}
		OCValueTable table = app.findByRef(removedRefs);
		OCValueTableColumnCollection columns = table.getColumns();
		int colSZ = columns.size();
		System.out.println();
		for (int z =0; z < colSZ; z++) {
			OCValueTableColumn column = columns.getTableColumn(z);
			System.out.print(column.getName()+"\t|\t");
		}
		System.out.println();
		System.out.println();
		for (OCValueTableRow row : table) {
			for (int z = 0; z < colSZ; z++) {
				System.out.println(z+" "+ row.getValue(z));
			}
			System.out.println("-------------------------------------------------------");
		}
	}
}
