package com.ipc.oce.v82;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.TypeSynonymParser;
import com.ipc.oce.metadata.OCType;

class TypeSynonymParserV82 extends TypeSynonymParser {
	
	//private static final transient Log LOG = LogFactory.getLog(TypeSynonymParserV82.class);
	
	public TypeSynonymParserV82() {
		super();
		init();
	}
	
	/**
	 * Инициализация парсера.
	 */
	private void init() {
		linkString = "Ref.";
		
	}
	
	@Override
	public boolean isSimple(final String synonym) {
		int code = OCType.nameToCode(synonym);
		return (code != 0 && code < OCType.OCT_REF_CATALOG);
	}
	
	public boolean isLink(final String synonym){
		return !isSimple(synonym);
	}

	@Override
	public String getSynonym(final OCType type) throws JIException {
		return getInstance().string(type);
	}

	@Override
	public String getLinkSynonym(OCType type) throws JIException {
		String synonymXML = getInstance().getXMLType(type).getTypeName();
		String[] split = synonymXML.split(linkString);
		if (split.length == 1) {
			return null;
		} else {
			return split[1];
		}
	}

	@Override
	public String getLinkType(OCType type) throws JIException {
		String synonym = getInstance().getXMLType(type).getTypeName();
		String[] split = synonym.split(linkString);
		if (split.length == 1) {
			return null;
		} else {
			return split[0];
		}
	}

	@Override
	protected void afterAttachInstance() throws JIException {
		/*long s = System.currentTimeMillis();
		
		OCConfigurationMetadataObject metadata = getInstance().getMetadata();
		
		OCMetadataDocumentCollection collectionDoc = metadata.getDocuments();
		OCDocumentMetadataObject dmo = null;
		int size = collectionDoc.size();
		for (int z = 0; z<size; z++) {
			dmo = collectionDoc.get(z); 
		}
		
		OCMetadataCatalogCollection collectionCat = metadata.getCatalogs();
		size = collectionCat.size();
		
		LOG.info("TypeSynonymParserV82 load metadata is "+(System.currentTimeMillis()-s)+"ms");*/
		
	}
	

}
