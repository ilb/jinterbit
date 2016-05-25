/**
 * 
 */
package com.ipc.oce.objects.reports;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Коллекция источников данных схемы компоновки данных. 
 * @author Konovalov
 *
 */
public class OCDataCompositionSchemaDataSources extends AbstractDCSList implements Iterable<OCDataCompositionSchemaDataSource>{

	/**
	 * @param object
	 */
	public OCDataCompositionSchemaDataSources(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSchemaDataSources(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSources(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	@Override
	public OCDataCompositionSchemaDataSource insert(int index) throws JIException {
		
		return new OCDataCompositionSchemaDataSource(super.insert(index));
	}

	@Override
	public OCDataCompositionSchemaDataSource add() throws JIException {
		
		return new OCDataCompositionSchemaDataSource(super.add());
	}

	public int indexOf(OCDataCompositionSchemaDataSource value) throws JIException {
		
		return super.indexOf(value);
	}

	@Override
	public int size() throws JIException {
		
		return super.size();
	}

	@Override
	public OCDataCompositionSchemaDataSource find(String paramName) throws JIException {
		
		return new OCDataCompositionSchemaDataSource(super.find(paramName));
	}

	@Override
	public void clear() throws JIException {
		
		super.clear();
	}

	@Override
	public OCDataCompositionSchemaDataSource get(int index) throws JIException {
		return new OCDataCompositionSchemaDataSource(super.get(index));
	}

	public void move(OCDataCompositionSchemaDataSource param, int offset) throws JIException {
		
		super.move(param, offset);
	}

	public void delete(OCDataCompositionSchemaDataSource param) throws JIException {
		super.delete(param);
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDataCompositionSchemaDataSource> iterator() {
		EnumVARIANT<OCDataCompositionSchemaDataSource> enumV = new EnumVARIANT<OCDataCompositionSchemaDataSource>(this) {
			
			@Override
			protected OCDataCompositionSchemaDataSource castToGeneric(JIVariant variant) {
				try {
					return new OCDataCompositionSchemaDataSource(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
}
