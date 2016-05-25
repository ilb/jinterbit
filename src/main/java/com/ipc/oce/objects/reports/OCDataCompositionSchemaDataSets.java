/**
 * 
 */
package com.ipc.oce.objects.reports;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Содержит наборы данных. 
 * @author Konovalov
 *
 */
public class OCDataCompositionSchemaDataSets extends OCObject implements Iterable<OCDataCompositionSchemaDataSet>{

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	protected OCDataCompositionSchemaDataSets(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество элементов в коллекции. 
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException {
		return get("Count").getObjectAsInt();
	}
	
	/**
	 *  Расположение элемента в коллекции. 
	 * @param index  Расположение элемента в коллекции.
	 * @return Тип: НаборДанныхЗапросСхемыКомпоновкиДанных, НаборДанныхОбъектСхемыКомпоновкиДанных, НаборДанныхОбъединениеСхемыКомпоновкиДанных. 
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSet get(int index) throws JIException {
		return try2wrapp(callMethodA("Get", new JIVariant(index))[0]);
	}
	
	/**
	 * Осуществляет поиск элемента коллекции по имени. 
	 * @param name  Имя искомого элемента.
	 * @return
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSet find(String name) throws JIException {
		JIVariant var = callMethodA("Find", new JIVariant(name))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return try2wrapp(var);	
		} else {
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDataCompositionSchemaDataSet> iterator() {
		EnumVARIANT<OCDataCompositionSchemaDataSet> enumV = new EnumVARIANT<OCDataCompositionSchemaDataSet>(this) {
			
			@Override
			protected OCDataCompositionSchemaDataSet castToGeneric(JIVariant variant) {
				try {
					return try2wrapp(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	private OCDataCompositionSchemaDataSet try2wrapp(JIVariant variant) throws JIException {
		OCObject basic = new OCObject(variant);

		try{ // try to object ----------
			OCDataCompositionSchemaDataSetObject dsObject = new OCDataCompositionSchemaDataSetObject(basic);
			dsObject.getObjectName();
			return dsObject;
		} catch (Exception e0) {
			try {
				OCDataCompositionSchemaDataSetQuery dsQuery = new OCDataCompositionSchemaDataSetQuery(basic);
				dsQuery.getQuery();
				return dsQuery;
			} catch (Exception e1) {
				OCDataCompositionSchemaDataSetUnion dsUnion = new OCDataCompositionSchemaDataSetUnion(
						basic);
				return dsUnion;
			}
		}
	}
	
	

}
