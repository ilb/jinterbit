package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class _OCCommonMetadataObject extends OCObject {
	private String name = null;
	private String comment = null;
	private String synonym = null;
	private String fullName = null; 
	private String presentation = null; 
	//========================================================
	public _OCCommonMetadataObject(OCObject object) {
		super(object);
	}

	public _OCCommonMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public _OCCommonMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Имя объекта метаданных. Имя должно состоять из одного слова, начинаться с
	 * буквы и не содержать специальных символов, кроме «_».
	 * 
	 * @return String
	 * @throws JIException 
	 */
	public String getName() throws JIException {
		if (name == null) {
			name = get("Name").getObjectAsString2();
		}
		return name;
	}
	
	/**
	 * Произвольная строка символов. Как правило, расшифровывает и поясняет имя объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public String getComment() throws JIException {
		if (comment == null) {
			comment = get("Comment").getObjectAsString2();
		}
		return comment;
	}
	
	/**
	 * Строка произвольных символов - синоним имени объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public String getSynonym() throws JIException {
		if (synonym == null) {
			synonym = get("Synonym").getObjectAsString2();
		} 
		return synonym;
	}
	
	/**
	 * Получает полное имя объекта метаданного в виде терма. 
	 * @return
	 * @throws JIException
	 */
	public String getFullName() throws JIException {
		if (fullName == null) {
			fullName = callMethodA("FullName").getObjectAsString2();
		}
		return fullName;
	}
	
	/**
	 * Получает строковое представление объекта метаданных. Как правило, в качестве строкового представления выступает синоним, а при его отсутствии имя объекта метаданных, как оно задано в конфигураторе. 
	 * @return
	 * @throws JIException
	 */
	public String getPresentation() throws JIException {
		if (presentation == null) {
			presentation = callMethodA("Presentation").getObjectAsString2();
		}
		return presentation;
	}
	
	/**
	 * Определяет объект описания метаданного, которому подчинен данный объект.
	 * 
	 * @return
	 * @throws JIException
	 */
	public _OCCommonMetadataObject getParent() throws JIException {
		return new _OCCommonMetadataObject(callMethodA("Parent"));
	}

	@Override
	public String toString() {
		try {
			return getFullName();
		} catch (JIException e) {
			return super.toString();
		}
	}
	
	

}
