/**
 * 
 */
package com.ipc.oce.xml.oc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.objects._OCCommonObject;
import com.ipc.oce.objects._OCCommonRef;
import com.ipc.oce.query.OCQueryResultColumnsCollection;
import com.ipc.oce.query.OCQueryResultSelection;
import com.ipc.oce.xml.xerces.XMLConstants;

/**
 * Helper-класс для маршализации оъектов 1С
 * @author Konovalov
 *
 */
public class MarshalHelper {
	public static final int SELECTION_SIMPLE = 10;
	
	public static final int SELECTION_REF2UUID = 20;
	
	public static final int SELECTION_REF2OBJECT = 30;
	
	private OCApp appInstance = null;
	
	private static final transient Log LOG = LogFactory.getLog(MarshalHelper.class);
	
	/**
	 * Конструктор
	 * @param appInstance активный экземпляр OCApp
	 */
	public MarshalHelper(OCApp appInstance) {
		super();
		this.appInstance = appInstance;
	}
	
	/**
	 * Преобразование объекта 1С в XML строку
	 * @param object объект 1С
	 * @param encodind кодировка для декларации xml (если null то декларация не устанавливается)
	 * @return
	 * @throws JIException
	 */
	public String object2xml(OCObject object, String encodind) throws JIException{
		String res = null;
		OCXMLWriter writer = appInstance.newXMLWriter();

		if (encodind != null) {
			writer.setString(encodind);
			writer.writeXMLDeclaration();
		} else {
			writer.setString();
		}
		
		appInstance.writeXML(writer, object);
		
		res = writer.close();
		return res;
	}
	
	/**
	 * Сериализация OCQueryResultSelection в XML формат в режиме SELECTION_SIMPLE
	 * @param selection OCQueryResultSelection
	 * @return xml представление выборки
	 * @throws JIException
	 */
	public String selection2xml(OCQueryResultSelection selection) throws JIException{
		return selection2xml(selection, SELECTION_SIMPLE);
	}
	
	/**
	 * Сериализация OCQueryResultSelection в XML формат
	 * @param selection OCQueryResultSelection
	 * @param mode режим развертывания ссылочных данных (SELECTION_SIMPLE, SELECTION_REF2UUID, ...)
	 * @return xml представление выборки
	 * @throws JIException
	 */
	public String selection2xml(OCQueryResultSelection selection, int mode) throws JIException{
		long s = System.currentTimeMillis();
		StringBuffer rowsBuffer = new StringBuffer(2048);
		StringBuffer mainBuffer = new StringBuffer(2048 + 256);
		OCQueryResultColumnsCollection columns =  selection.getColumns();
		List<String> colNames = columns.getNames();
		
		// add xml declaration
		mainBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		int rowIndex = 0;
		Map<String, String> objectMetadata = new HashMap<String, String>();
		while (selection.next()) {
			rowsBuffer.append("<ResultRow ").append("num=\"").append(rowIndex).append("\">");
			for (String colName : colNames) {
					
					switch(mode){
						case SELECTION_SIMPLE:{
							rowsBuffer.append("<").append(colName).append(">");
							rowsBuffer.append(selection.getValue(colName).value());
							break;
						}
						case SELECTION_REF2UUID:{
							String res = null;
							Object obj = selection.getValue(colName).value();
							if (obj instanceof _OCCommonRef) {
								_OCCommonRef ref = (_OCCommonRef) obj;
								
								// detect ref type -----------
								String refType = null;
								if (objectMetadata.containsKey(colName)) {
									refType = objectMetadata.get(colName);
								} else {
									refType = ref.getMetadata().getFullName();
									objectMetadata.put(colName, refType);
								}
								
								res = ref.getUUID().toString();
								rowsBuffer.append("<").append(colName).append(" type=\"").append(refType).append("\">");
								rowsBuffer.append(res);
							} else {
								rowsBuffer.append("<").append(colName).append(">");
								res = (obj != null ? obj.toString() : "");
								rowsBuffer.append(res);
							}
							break;
						}
						case SELECTION_REF2OBJECT:{
							String res = null;
							Object obj = selection.getValue(colName).value();
					if (obj instanceof _OCCommonRef
							&& !((_OCCommonRef) obj).isEmpty()) {
								_OCCommonObject doObj = ((_OCCommonRef) obj).getObject();
								
								String refType = null;
								if (objectMetadata.containsKey(colName)) {
									refType = objectMetadata.get(colName);
								} else {
									refType = ((_OCCommonRef) obj).getMetadata().getFullName();
									objectMetadata.put(colName, refType);
								}

								rowsBuffer.append("<").append(colName).append(" type=\"").append(refType).append("\">");
								
								res = object2xml(doObj, null);
								rowsBuffer.append(res);
							} else {
								res = (obj != null ? obj.toString() : "");
								rowsBuffer.append("<").append(colName).append(">");
								rowsBuffer.append(res);
							}
							break;
						}
						default:{
							rowsBuffer.append("<").append(colName).append(">");
							rowsBuffer.append(selection.getValue(colName).value());
						}
					}
					
					rowsBuffer.append("</").append(colName).append(">");
				}
			rowsBuffer.append("</ResultRow>");
			rowIndex++;
		}
		
		mainBuffer.append("<QueryResultSelection rows=\"").append(rowIndex).append("\"").append(" xmlns=\"").append(XMLConstants.IPC_NS).append("\">"); // root
		mainBuffer.append(rowsBuffer);
		mainBuffer.append("</QueryResultSelection>");
		LOG.info("ResultSet2XML timing: " + (System.currentTimeMillis() - s) + "ms");
		return mainBuffer.toString();
	}
	
}
