package com.ipc.oce;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.metadata.OCType;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;
import com.ipc.oce.objects.OCCatalogRef;
import com.ipc.oce.objects.OCChartOfAccountsRef;
import com.ipc.oce.objects.OCChartOfCharacteristicTypesRef;
import com.ipc.oce.objects.OCDocumentRef;
import com.ipc.oce.objects.OCEnumRef;
import com.ipc.oce.objects.OCExchangePlanRef;
import com.ipc.oce.objects._OCCommonObject;
import com.ipc.oce.objects._OCCommonRef;
import com.ipc.oce.query.OCQueryResult;

/**
 * Класс-обертка для значений статических и динамических типов 1С (и DCOM вообще). Надстройка над JIVariant
 * @author Konovalov
 *
 */
public class OCVariant {
	
	private JIVariant variant = null;
	
	private OCTypeDescription typeDescription = null;
	
	private Integer innerTypeCode = OCType.OCT_UNKNOWN;
	
	private Object cachedValueResult = null; // кэш объекта возвращенного методом value
	
	/**
	 * Явное задание объекта JIVariant.
	 * @param var
	 */
	public OCVariant(final JIVariant var) {
		super();
		variant = var;
	}
	
	/**
	 * Конструктор от OCVariant
	 * Внимание, это баг-фикс: tips: тут нужно использовать конструктор OCVariant, т.к. динамически объекты требуют внутри не JIVariant, IJIDispatch.
	 * Идея такая: получить OCObject и на его основе сделать OCVariant. Ну или простой тип.
	 * @param var
	 * @throws JIException
	 */
	public OCVariant(final OCVariant var) throws JIException {
		Object obj = var.value();
		if (obj instanceof OCObject) {
			variant = makeVariant((OCObject) obj);
		} else {
			variant = JIVariant.makeVariant(obj);
		}
	}
	
	/**
	 * Создает JIVariant с помощью JIVariant.makeVariant().
	 * Применяется для создание объекта из примитивного типа и объектов OCObject
	 * @param obj
	 */
	public OCVariant(Object obj){
		super();
		if (obj instanceof OCObject) {
			variant = makeVariant((OCObject) obj);
		} else {
			variant = JIVariant.makeVariant(obj);
		}
	}
	
	/**
	 * Конструктор от динамических типов (OCObject)
	 * @param obj
	 */
	public OCVariant(OCObject obj) {
		super();
		variant = makeVariant(obj);
	}
	
	/**
	 * Конструктор от динамических типов (_OCCommonObject)
	 * @param obj
	 */
	public OCVariant(_OCCommonObject commonObject) {
		super();
		variant = makeVariant(commonObject);
		if (commonObject.getClass().getSuperclass().equals(_OCCommonObject.class)) {
			cachedValueResult = commonObject;
		}
	}
	
	/**
	 * Конструктор от динамических типов (_OCCommonRef)
	 * @param obj
	 */
	public OCVariant(_OCCommonRef commonRef) {
		super();
		variant = makeVariant(commonRef);
		if (commonRef.getClass().getSuperclass().equals(_OCCommonRef.class)) {
			cachedValueResult = commonRef;
		}
	}
	
	/**
	 * Конструктор от динамических типов (OCEnumRef)
	 * @param obj
	 */
	public OCVariant(OCEnumRef enumRef) {
		super();
		variant = makeVariant(enumRef);
		cachedValueResult = enumRef;
	}
	
	/**
	 * Сервисный метод по извлечению из OCObject-ов JIVariant
	 * @param object
	 * @return
	 */
	private static JIVariant makeVariant(final OCObject object) {
		return new JIVariant(object.dispatch());
	}
	/**
	 * Используется для последующего получения с приведением типа.
	 * Обычно используется при переборе выборки с использованием метаданных.
	 * @param var
	 * @param type
	 */
	public OCVariant(JIVariant var, OCTypeDescription type) {
		variant = var;
		this.typeDescription = type;
	}

	public Integer getInt() throws JIException {
		return variant.getObjectAsInt();
	}

	public String getString() throws JIException {
		return variant.getObjectAsString2();
	}

	public Boolean getBoolean() throws JIException {
		return variant.getObjectAsBoolean();
	}

	public Long getLong() throws JIException {
		return variant.getObjectAsLong();
	}

	public Date getDate() throws JIException {
		return variant.getObjectAsDate();
	}

	public Double getDouble() throws JIException {
		return variant.getObjectAsDouble();
	}

	public Character getChar() throws JIException {
		return variant.getObjectAsChar();
	}

	public Short getShort() throws JIException {
		return variant.getObjectAsShort();
	}

	public Float getFloat() throws JIException {
		return variant.getObjectAsFloat();
	}

	public OCObject getAsOCObject() throws JIException {
		return new OCObject(variant);
	}
	
	/**
	 * Получение внутреннего типа объекта.
	 * @return Integer
	 * @throws JIException 
	 */
	public Integer getTypeCode() throws JIException{
		if (innerTypeCode == OCType.OCT_UNKNOWN) {
			value();
		}
		return innerTypeCode;
	}
	
	/**
	 * Рекурсивная проверка нахождения суперкласса.
	 * @param cls - проверяемый класс
	 * @param checkForSuper - суперкласс который должен содержать <cls>
	 * @return true - если cls содержит checkForSuper класс
	 */
	private boolean hasSuperClass(Class cls, Class checkForSuper) {
		Class superCls = cls.getSuperclass();
		if (superCls == null) {
			return false;
		} 
		if (superCls.equals(checkForSuper)) {
			return true;
		} else {
			return hasSuperClass(superCls, checkForSuper);
		}
	}
	
	/**
	 * Проверка на принадлежность к классам порожденным от OCObject
	 * @param cls проверяемый класс
	 * @return true - если класс принадлежит к OCObject-овым
	 */
	private boolean isOCObjectClass(Class cls) {
		return hasSuperClass(cls, OCObject.class);
	}
	
	/**
	 * Получение значения с приведением к определенному типу. 
	 * Полезно при работе с double колонками, т.к. при нулевой дробной части DCOM возвращает int\long (в некоторых случаях). 
	 * <br><i>Пример: variant.value(Double.class)</i>.
	 * <br>
	 * Также может использоваться для автоматического приведения _OCCommonRef в _OCCommonObject и наоборот.
	 * Пример: <br><i>
	 * OCCatalogRef ref = ...; <br>
	 * OCVaraint var = new OCVariant(ref); <br>
	 * OCCatalogObject catObject = var.value(OCCatalogObject.class);</i>
	 * @param <T> приводимый тип
	 * @param type приводимый тип
	 * @return приведенное значение
	 * @throws JIException
	 */
	public <T> T value(Class<T> type) throws JIException {
		Object obj = value();
		
		if (type.isInstance(obj)) {
			return type.cast(obj);
			
		} else if (hasSuperClass(type, _OCCommonRef.class) && (obj instanceof _OCCommonObject)) {
			return (T) ((_OCCommonObject)obj).getRef();
			
		} else if (hasSuperClass(type, _OCCommonObject.class) && (obj instanceof _OCCommonRef)) {
			return (T) ((_OCCommonRef)obj).getObject();
			
		} else {
			boolean cantCastToOCObject =  isOCObjectClass(type) || (obj instanceof OCObject);
			// no one OCObject ahead!
			if (cantCastToOCObject) {
				throw new IllegalStateException("Can't cast from '" + obj.getClass().getName() + "' to '" + type.getName()+"'");
			} else {
				// casting primitives type
				String objAsString = obj.toString();
				int precIndex = objAsString.indexOf('.');
				if (type.getSuperclass().equals(Number.class) 
						&& precIndex != -1
						&& objAsString.substring(precIndex + 1).startsWith("0")) {
					objAsString = objAsString.substring(0,precIndex);
				}
				try {
					Constructor<T> constr = type.getConstructor(String.class);
					Object res = constr.newInstance(objAsString);
					return (T) res;
				} catch (NumberFormatException e) {
					throw e;
				}catch (SecurityException e) {
					throw new IllegalStateException(e);
				} catch (NoSuchMethodException e) {
					throw new IllegalStateException(e);
				} catch (IllegalArgumentException e) {
					throw new IllegalStateException(e);
				} catch (InstantiationException e) {
					throw new IllegalStateException(e);
				} catch (IllegalAccessException e) {
					throw new IllegalStateException(e);
				} catch (InvocationTargetException e) {
					throw new IllegalStateException(e.getCause());
				}
			}
		}
	}

	/**
	 * Возвращает приведенные значения типов данных к внутренней модели. Кроме
	 * простых типов могут возвращаться типы вида OC***Ref и прочие OCObject
	 * 
	 * @return Если тип простой, то приводится к простому java-типу. Если
	 *         VT_UNKNOWN, то приводим к Ref и через метаданные рефа определяем
	 *         тип "общего" рефа и приводим к конкретному. Это позволяет
	 *         получать значение в любом режиме, в т.ч. когда OCTypeDescription
	 *         незадан и даже при multiType-е
	 * @throws JIException
	 */
	// пожалуй это шедевр!
	public <T> T value() throws JIException{
		if (cachedValueResult != null) {
			return (T) cachedValueResult;
		}
		
		int varType = variant.getType();
		Object res = null;
		switch (varType) {
			case JIVariant.VT_BOOL:{
				res = getBoolean();
				innerTypeCode = OCType.OCT_BOOLEAN;
				break;
			}
			case JIVariant.VT_BSTR:{
				res = getString().trim();
				innerTypeCode = OCType.OCT_STRING;
				break;
			}
			case JIVariant.VT_DATE:{
				res = getDate();
				innerTypeCode = OCType.OCT_DATE;
				break;
			}
			case JIVariant.VT_DECIMAL:{
				res = getLong();
				innerTypeCode = OCType.OCT_NUMBER;
				break;
			}
			case JIVariant.VT_INT:{
				res = getInt();
				innerTypeCode = OCType.OCT_NUMBER;
				break;
			}
			case JIVariant.VT_I2:{
				res = getShort();
				innerTypeCode = OCType.OCT_NUMBER;
				break;
			}
			case JIVariant.VT_I4:{
				res = getInt();
				innerTypeCode = OCType.OCT_NUMBER;
				break;
			}
			case JIVariant.VT_I8:{
				res = getLong();
				innerTypeCode = OCType.OCT_NUMBER;
				break;
			}
			case JIVariant.VT_R4:{
				res = getFloat();
				innerTypeCode = OCType.OCT_NUMBER;
				break;
			}
			case JIVariant.VT_R8:{
				res = getDouble();
				innerTypeCode = OCType.OCT_NUMBER;
				break;
			}
			case JIVariant.VT_NULL:{
				res = null;
				innerTypeCode = OCType.OCT_NULL;
				break;
			}
			/* определение VT_UNKNOWN объекта производится:
			 * 1) или по установленному извне OCTypeDescription (например и при параллельном проходе по метаданным)
			 * 2) приведением к OCCommonRef и получению метаданных реф-а
			 */
			case JIVariant.VT_DISPATCH:
			case JIVariant.VT_UNKNOWN:{
				if (typeDescription != null && !(typeDescription.isMultiType())) {
					
					int tCode = typeDescription.getType().getTypeCode();
					
					res = castToRef(getAsOCObject(), tCode);
					
				} else {
					
					OCObject unknownObj = getAsOCObject();
					
					//== detect ref type ===========================
					// предпологаем что у всех рефов есть метод Metadata, если нет - встроить обработчик и думать дальше
					try {
						// хорошо живется тем у кого есть метаданные...
						_OCCommonMetadataObject metadata = new _OCCommonMetadataObject(unknownObj.callMethodA("Metadata"));
						String refTypeName = metadata.getFullName(); // определяем что это за объект (какого типа? но это точно Ref, поэтому кастим к _OCCommonRef)
						refTypeName = refTypeName.substring(0, refTypeName.indexOf(".") ); // отрезаем конкретный тип
						res = castToRef(unknownObj, OCType.nameToCode(refTypeName));
						
					} catch (Exception e) {
						// ... а тут поселились те кого опознать по "паспорту" неудалось
						String ocName = toString1C(unknownObj);
						res = castToRef(unknownObj, OCType.nameToCode(ocName));
					}
				}
				break;
			}
			case JIVariant.VT_EMPTY:{
				res = null;
				innerTypeCode = OCType.OCT_NULL;
				break;
			}
			default:{
				res = getAsOCObject();
				innerTypeCode = OCType.OCT_REF_UNKNOWN;
			}
		}
		
		cachedValueResult = res;
		
		return (T) res;
	}
	
	private String toString1C(OCObject object) throws JIException {
		OCApp app = OCApp.getInstance(object.getAssociatedSessionID());
		return app.string(object);
	}
	
	/**
	 * Приведение общей ссылки или объекта к конкретному ref-у.
	 * @param object объект-ссылка на реф
	 * @param tCode внутренний тип рефа
	 * @return конкретный тип рефа
	 * @throws JIException
	 */
	private Object castToRef(OCObject object, int tCode) throws JIException{
		Object res = null;
		innerTypeCode = tCode;
		switch(tCode) {
			case OCType.OCT_REF_DOCUMENT: {
				res = new OCDocumentRef(object);
				break;
			}
			case OCType.OCT_REF_CATALOG: {
				res = new OCCatalogRef(object);
				break;
			}
			case OCType.OCT_REF_CHART_OF_ACCOUNT: {
				res = new OCChartOfAccountsRef(object);
				break;
			}
			case OCType.OCT_REF_ENUM: {
				res = new OCEnumRef(object);
				break;
			}
			case OCType.OCT_QUERY_RESULT: {
				res = new OCQueryResult(object);
				break;
			}
			case OCType.OCT_ARRAY: {
				res = new OCArray(object);
				break;
			}
			case OCType.OCT_STRUCTURE: {
				res = new OCStructure(object);
				break;
			}
			case OCType.OCT_VALUETABLE: {
				res = new OCValueTable(object);
				break;
			}
			case OCType.OCT_VALUESTORAGE: {
				res = new OCValueStorage(object);
				break;
			}
			case OCType.OCT_REF_CHART_OF_CHARACTERISTIC: {
				res = new OCChartOfCharacteristicTypesRef(object);
				break;
			}
			case OCType.OCT_REF_EXCHANGE_PLAN : {
				res = new OCExchangePlanRef(object);
				break;
			}
			default: {
				res = getAsOCObject();
				innerTypeCode = OCType.OCT_REF_UNKNOWN;
			}
		}
		return res;
	}
	/**
	 * Получение значения и приведение типа.
	 * @param description
	 * @return
	 * @throws JIException
	 */
	public Object valueAs(final OCTypeDescription description) throws JIException{
		this.typeDescription = description;
		return value();
	}
	
	/**
	 * Сброс кэшированного результата метода value.
	 */
	protected void resetValueCache(){
		cachedValueResult = null;
	}
	//=============================================
	protected JIVariant getJIVariant(){
		return variant;
	}
	@Override
	public String toString() {
		try {
			Object v = value();
			return v != null ? v.toString() : "";
		} catch (JIException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
