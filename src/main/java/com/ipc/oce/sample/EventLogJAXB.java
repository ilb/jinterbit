/**
 * 
 */
package com.ipc.oce.sample;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.ipc.oce.events.v81.EventLogType;

/**
 * @author Konovalov
 *
 */
public class EventLogJAXB {
	private static JAXBContext jc = null;
	static {
		try {
			jc = JAXBContext.newInstance("com.ipc.oce.events.v81");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static void do1(String content) throws JAXBException{
		long s = System.currentTimeMillis();
		//InputStream is = new ByteArrayInputStream(content.getBytes());
		
		//Unmarshaller unmarshaller = jc.createUnmarshaller();
		
		//JAXBElement<EventLogType> obj = (JAXBElement<EventLogType>)unmarshaller.unmarshal(is);
		
		System.out.println("jaxb time: "+(System.currentTimeMillis()-s)+"ms");
	}
}
