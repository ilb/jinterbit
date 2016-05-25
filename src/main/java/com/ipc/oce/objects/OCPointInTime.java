package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Предназначен для получения и хранения момента времени для объекта в базе данных. Содержит дату и время, а также ссылку на объект базы данных. Используется в качестве значений свойств и параметров методов других объектов, имеющих тип МоментВремени.
 * Момент времени используется в тех случаях, когда важно различать моменты времени для объектов, имеющих одинаковую дату и время, например для сравнения положений документов на временной оси.
 * @author Konovalov
 *
 */
public class OCPointInTime extends OCObject {

	protected OCPointInTime(OCObject object) {
		super(object);
	}

	protected OCPointInTime(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	protected OCPointInTime(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Содержит дату и время момента времени
	 * @return
	 * @throws JIException
	 */
	public Date getDate() throws JIException{
		return get("Date").getObjectAsDate();
	}
	
	/**
	 * Любая ссылка на объект информационной базы. Содержит ссылку на объект в базе данных. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return OCObject
	 * @throws JIException
	 */
	public OCObject getRef() throws JIException{
		return new OCObject(get("Ref"));
	}
	
	/**
	 * Сравнить(<Момент времени>) 
	 * @param pointInTime Момент времени, с которым производится сравнение. 
	 * @return  -1 - если момент времени меньше другого момента времени, 0 - если момент времени равен другому моменту времени, 1 - если момент времени больше другого момента времени. 
	 * @throws JIException
	 */
	public Integer compare(OCPointInTime pointInTime) throws JIException{
		return callMethodA("Compare", new Object[]{pointInTime.dispatch()})[0].getObjectAsInt();
	}
}
