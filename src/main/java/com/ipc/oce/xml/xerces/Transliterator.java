/**
 * 
 */
package com.ipc.oce.xml.xerces;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Konovalov
 *
 */
public final class Transliterator {
	
	
	private static Map<Character, String> mapRu2Lat = new HashMap<Character, String>();
	
	static {
		mapRu2Lat.put('А', "A"); 	mapRu2Lat.put('а', "a");
		mapRu2Lat.put('Б', "B"); 	mapRu2Lat.put('б', "b");
		mapRu2Lat.put('В', "V"); 	mapRu2Lat.put('в', "v");
		mapRu2Lat.put('Г', "G"); 	mapRu2Lat.put('г', "g");
		mapRu2Lat.put('Д', "D"); 	mapRu2Lat.put('д', "d");
		mapRu2Lat.put('Е', "E"); 	mapRu2Lat.put('е', "e");
		mapRu2Lat.put('Ё', "YO"); 	mapRu2Lat.put('ё', "yo");
		mapRu2Lat.put('Ж', "Zh"); 	mapRu2Lat.put('ж', "zh");
		mapRu2Lat.put('З', "Z"); 	mapRu2Lat.put('з', "z");
		mapRu2Lat.put('И', "I"); 	mapRu2Lat.put('и', "i");
		mapRu2Lat.put('Й', "Y"); 	mapRu2Lat.put('й', "y");
		mapRu2Lat.put('К', "K"); 	mapRu2Lat.put('к', "k");
		mapRu2Lat.put('Л', "L"); 	mapRu2Lat.put('л', "l");
		mapRu2Lat.put('М', "M"); 	mapRu2Lat.put('м', "m");
		mapRu2Lat.put('Н', "N"); 	mapRu2Lat.put('н', "n");
		mapRu2Lat.put('О', "O"); 	mapRu2Lat.put('о', "o");
		mapRu2Lat.put('П', "P"); 	mapRu2Lat.put('п', "p");
		mapRu2Lat.put('Р', "R"); 	mapRu2Lat.put('р', "r");
		mapRu2Lat.put('С', "S"); 	mapRu2Lat.put('с', "s");
		mapRu2Lat.put('Т', "T"); 	mapRu2Lat.put('т', "t");
		mapRu2Lat.put('У', "U"); 	mapRu2Lat.put('у', "u");
		mapRu2Lat.put('Ф', "F"); 	mapRu2Lat.put('ф', "f");
		mapRu2Lat.put('Х', "Kh");	mapRu2Lat.put('х', "kh");
		mapRu2Lat.put('Ц', "Ts"); 	mapRu2Lat.put('ц', "ts");
		mapRu2Lat.put('Ч', "Ch"); 	mapRu2Lat.put('ч', "ch");
		mapRu2Lat.put('Ш', "Sh"); 	mapRu2Lat.put('ш', "sh");
		mapRu2Lat.put('Щ', "Sh"); 	mapRu2Lat.put('щ', "sh");
		mapRu2Lat.put('Ъ', ""); 	mapRu2Lat.put('ъ', "");
		mapRu2Lat.put('Ы', "Y"); 	mapRu2Lat.put('ы', "");
		mapRu2Lat.put('Ь', ""); 	mapRu2Lat.put('ь', "");
		mapRu2Lat.put('Э', "E"); 	mapRu2Lat.put('э', "e");
		mapRu2Lat.put('Ю', "Yu"); 	mapRu2Lat.put('ю', "yu");
		mapRu2Lat.put('Я', "Ya"); mapRu2Lat.put('я', "ya");
	}
	
	private Transliterator(){
		
	}
	
	public static String ru2lat(String original) {
		char[] chs = original.toCharArray();
		StringBuffer buffer = new StringBuffer();
		for (char c : chs) {
			buffer.append(mapRu2Lat.get(c));
		}
		return buffer.toString();
	}
}
