/**
 * 
 */
package com.ipc.oce;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Konovalov
 *
 */
public final class ObjectNameDecoder {
	
	/**
	 * "Словарь".
	 */
	private static Map<String, String> ruEn = null;
	
	static {
		ruEn = new HashMap<String, String>();
		ruEn.put("Справочник", "Catalog");
		ruEn.put("Документ", "Document");
	}
	
	/**
	 * Closed constructor.
	 */
	private ObjectNameDecoder() {
		
	}
	
	/**
	 * Перевод русских названий в английские.
	 * @param ru - английское написание
	 * @return ангийский перевод параметра ru
	 */
	public static String ru2En(final String ru) {
		String res = null;
		res = ruEn.get(ru);
		if (res == null) {
			res = ru;
		}
		return res;
	}
}
