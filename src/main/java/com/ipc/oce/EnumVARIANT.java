/**
 * 
 */
package com.ipc.oce;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIArray;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;
import org.jinterop.dcom.impls.automation.IJIEnumVariant;

/**
 * Abstract implementarion of IEnumVARIANT COM interface over java's Enumeration and Iterator.
 * @author Konovalov
 * @param <E>
 *
 */
public abstract class EnumVARIANT<E> extends OCObject implements Enumeration<E>, Iterator<E>{
	
	private IJIEnumVariant enumVARIANT = null; // IEnumVARIANT instance it self
	
	private static final int FETCH_LIMIT = 1; // IEnumVARIANT.next celt size
	
	private JIVariant variant = null; // last revised variant. used in next and nextElement
	
	private boolean prevCheck = false;
	
	boolean hasMoreElements = false;
	
	/**
	 * @param object
	 * @throws JIException 
	 */
	public EnumVARIANT(OCObject object) {
		super(object);
		prepareEnum();
	}

	/**
	 * @param aDispatch
	 * @throws JIException 
	 */
	public EnumVARIANT(IJIDispatch aDispatch) throws JIException {
		super(aDispatch);
		prepareEnum();
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public EnumVARIANT(JIVariant aDispatch) throws JIException {
		super(aDispatch);
		prepareEnum();
	}
	
	private void prepareEnum() {
		try {
			JIVariant var = getByID(-4);
			IJIComObject enumObject = var.getObjectAsComObject();
			IJIEnumVariant enumVARIANT = (IJIEnumVariant) JIObjectFactory
					.narrowObject((IJIComObject) enumObject
							.queryInterface(IJIEnumVariant.IID));
			this.enumVARIANT = enumVARIANT;
		} catch (JIException e) {
			throw new RuntimeException(e);
		}
	}
	
	private E checkAndGet() {
		if(!prevCheck) {
			hasMoreElements = hasMoreElements();
		}
		prevCheck = false;
		if (hasMoreElements) {
			E obj = castToGeneric(variant);
			return obj;
		} else {
			throw new NoSuchElementException("No more elements in collection");
		}
	}
	
	/**
	 * Resets the enumeration sequence to the beginning.
	 * @throws JIException
	 */
	public void reset() throws JIException {
		enumVARIANT.reset();
	}
	
	/**
	 * Attempts to skip over the next celt elements in the enumeration sequence.
	 * @param skipElements
	 * @throws JIException
	 */
	public void skip(int skipElements) throws JIException {
		enumVARIANT.skip(skipElements);
	}
	
	/**
	 * Can throw only RuntimeException or, alternatively return null.
	 * @param variant COM-variant object
	 * @return OCTitbit local object
	 */
	protected abstract E castToGeneric(JIVariant variant);
	
	public boolean hasMoreElements() {
		JIVariant tmp = null;
		try { 
			Object[] values = enumVARIANT.next(FETCH_LIMIT);
			JIArray array = (JIArray) values[0];
			/*int itemFetched = (Integer)values[1];
			if (itemFetched < FETCH_LIMIT) {
				System.out.println("End is next");
			}*/
			Object[] arrayObj = (Object[]) array.getArrayInstance(); // size equals FETCH_LIMIT or less
			// TODO Возможно в будущем можно сделать с FETCH_LIMIT > 1. Пока и так неплохо.
			tmp = (JIVariant) arrayObj[0]; // If FETCH_LIMIT==1 only one element. VT_UNKNOWN for COM-objects
		} catch (JIException e) {
			tmp = null;
		}
		
		prevCheck = true;
		variant = tmp;
		hasMoreElements = variant != null;
		
		return hasMoreElements;
	}

	
	public E nextElement() {
		return checkAndGet();
	}
	
	//=========================================================
	// Iterator methods

	public boolean hasNext() {
		return hasMoreElements();
	}


	public void remove() {
		throw new UnsupportedOperationException("EnumVariant iterator don't support remove operation");
		
	}

	public E next() {
		return checkAndGet();
	}

}
