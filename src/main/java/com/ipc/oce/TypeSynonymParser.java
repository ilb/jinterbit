package com.ipc.oce;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.metadata.OCType;

public abstract class TypeSynonymParser {
	
	protected String linkString;
	
	private OCApp instance = null;
	
	public TypeSynonymParser() {
		super();
	}
	
	public abstract boolean isSimple(String synomym);
	
	public abstract boolean isLink(String synonym);
	
	protected final void attachAppInstance(OCApp app) throws JIException {
		instance = app;
		afterAttachInstance();
	}
	
	protected abstract void afterAttachInstance() throws JIException;
	
	protected OCApp getInstance() {
		return instance;
	}
	
	public String getLinkString() {
		return linkString;
	}
	
	public abstract String getSynonym(OCType type) throws JIException;
	
	public abstract String getLinkSynonym(OCType type) throws JIException;
	
	public abstract String getLinkType(OCType type) throws JIException;
}
