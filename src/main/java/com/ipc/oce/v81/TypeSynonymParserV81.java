package com.ipc.oce.v81;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.TypeSynonymParser;
import com.ipc.oce.metadata.OCType;

class TypeSynonymParserV81 extends TypeSynonymParser {
	
	public TypeSynonymParserV81() {
		super();
		init();
	}
	
	private void init(){
		linkString = "ссылка:";
	}
	@Override
	public boolean isSimple(String synonym) {
		return !synonym.contains(linkString);
	}
	public boolean isLink(String synonym){
		return !isSimple(synonym);
	}
	
	@Override
	public String getSynonym(OCType type) throws JIException {
		return getInstance().string(type);
	}
	
	@Override
	public String getLinkSynonym(OCType type) throws JIException {
		String synonymXML = getInstance().getXMLType(type).getTypeName();
		String[] split = synonymXML.split("Ref.");
		if (split.length == 1) {
			return null;
		} else {
			return split[1];
		}
	}

	@Override
	public String getLinkType(OCType type) throws JIException {
		String synonym = getInstance().getXMLType(type).getTypeName();
		String[] split = synonym.split("Ref.");
		if (split.length == 1) {
			return null;
		} else {
			return split[0];
		}
	}

	@Override
	protected void afterAttachInstance() throws JIException {
		// No dynamic initialization required
		
	}
	

}
