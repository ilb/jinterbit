package com.ipc.oce.v80;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.TypeSynonymParser;
import com.ipc.oce.metadata.OCType;

public class TypeSynonymParserV80 extends TypeSynonymParser {
	public TypeSynonymParserV80() {
		super();
		init();
	}

	private void init() {
		linkString = "ссылка:";
	}

	@Override
	public boolean isSimple(String synonym) {
		return !synonym.contains(linkString);
	}

	@Override
	public boolean isLink(String synonym) {
		return !isSimple(synonym);
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.TypeSynonymParser#getSynonym(com.ipc.oce.metadata.OCType)
	 */
	@Override
	public String getSynonym(OCType type) throws JIException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.TypeSynonymParser#getLinkSynonym(com.ipc.oce.metadata.OCType)
	 */
	@Override
	public String getLinkSynonym(OCType type) throws JIException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.TypeSynonymParser#getLinkType(com.ipc.oce.metadata.OCType)
	 */
	@Override
	public String getLinkType(OCType type) throws JIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void afterAttachInstance() throws JIException {
		// NOT USED
		
	}
	

}
