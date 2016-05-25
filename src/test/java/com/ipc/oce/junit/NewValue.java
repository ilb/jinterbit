/**
 * 
 */
package com.ipc.oce.junit;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.OCVariant;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCCatalogObject;
import com.ipc.oce.objects.OCCatalogRef;
import com.ipc.oce.objects.OCCatalogSelection;

/**
 * @author Konovalov
 * 
 */
public class NewValue extends BasicTest {

	@Test(expected = IllegalStateException.class)
	public void double2intWrong() throws JIException {
		OCVariant var = new OCVariant(new Double(10.90));
		Object di = var.value(Integer.class);
		assertTrue(di instanceof Double);
	}

	@Test
	public void double2bigDecimalCorrect() throws JIException {
		OCVariant var = new OCVariant(new Double(10.876));
		Object di = var.value(BigDecimal.class);
		assertTrue(di instanceof BigDecimal);
	}

	@Test(timeout = 5)
	public void double2intCorrect() throws JIException {
		OCVariant var = new OCVariant(new Double(10));

		Object di = var.value(Integer.class);
		assertTrue(di instanceof Integer);
	}

	@Test
	public void double2floatCorrect() throws JIException {
		OCVariant var = new OCVariant(new Double(10.90));
		Object di = var.value(Float.class);
		assertTrue(di instanceof Float);
	}

	@Test
	public void int2doubleCorrect() throws JIException {
		OCVariant var = new OCVariant(new Integer(10));
		Object di = var.value(Double.class);
		assertTrue(di instanceof Double);
	}

	@Test
	public void int2shortCorrect() throws JIException {
		OCVariant var = new OCVariant(new Integer(10));
		Object di = var.value(Short.class);
		assertTrue(di instanceof Short);
	}

	@Test
	public void int2floatCorrect() throws JIException {
		OCVariant var = new OCVariant(new Integer(10));
		Object di = var.value(Float.class);
		assertTrue(di instanceof Float);
	}

	@Test
	public void float2doubleCorrect() throws JIException {
		OCVariant var = new OCVariant(new Float(10.90));
		Object di = var.value(Double.class);
		assertTrue(di instanceof Double);
	}

	@Test
	public void float2bigDecimalCorrect() throws JIException {
		OCVariant var = new OCVariant(new Float(10.90));
		Object di = var.value(BigDecimal.class);
		assertTrue(di instanceof BigDecimal);
	}

	@Test
	public void float2intCorrect() throws JIException {
		OCVariant var = new OCVariant(new Float(10));
		Object di = var.value(Integer.class);
		assertTrue(di instanceof Integer);
	}

	@Test(expected = IllegalStateException.class)
	public void float2intWrong() throws JIException {
		OCVariant var = new OCVariant(new Float(10.2));
		Object di = var.value(Integer.class);
		assertTrue(di instanceof Integer);
	}

	@Test
	public void int2bigDecimalCorrect() throws JIException {
		OCVariant var = new OCVariant(new Integer(10));
		Object di = var.value(BigDecimal.class);
		assertTrue(di instanceof BigDecimal);
	}

	@Test
	public void int2stringCorrect() throws JIException {
		OCVariant var = new OCVariant(new Integer(10));
		Object di = var.value(String.class);
		assertTrue(di instanceof String);
	}

	@Test(expected = IllegalStateException.class)
	public void ocobject2int() throws JIException {
		OCCatalogManager manager = app.getCatalogManager("Валюты");
		OCCatalogSelection selection = manager.select();
		selection.next();
		OCCatalogRef ref = selection.getRef();
		OCVariant var = new OCVariant(ref);

		Integer ocObj = var.value(Integer.class);
	}

	@Test(expected = IllegalStateException.class)
	public void int2ocobject() throws JIException {
		OCVariant var = new OCVariant(new Integer(10));
		OCCatalogRef ref = var.value(OCCatalogRef.class);
	}

	@Test
	public void ocobject2ocrefAndViceversa() throws JIException {
		OCCatalogManager manager = app.getCatalogManager("Валюты");
		OCCatalogSelection selection = manager.select();
		selection.next();
		OCCatalogRef ref = selection.getRef();
		OCVariant var = new OCVariant(ref);
 
		OCCatalogObject ocObj = var.value(OCCatalogObject.class);
		assertTrue(ocObj instanceof OCCatalogObject);

		OCVariant var2 = new OCVariant(ocObj);
		assertTrue(var2.value(OCCatalogRef.class) instanceof OCCatalogRef);
	}

}
