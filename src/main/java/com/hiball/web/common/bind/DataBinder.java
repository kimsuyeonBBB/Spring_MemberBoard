package com.hiball.web.common.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class DataBinder {
    public static Object bind(Class<?> dataType, Map<String, String[]> paramMap) throws IllegalAccessException,
	    IllegalArgumentException, InvocationTargetException, InstantiationException, ParseException {
	Set<String> paramNames = paramMap.keySet();
	String propertyName = "";
	Object dataObject = dataType.newInstance();
	Method m = null;

	for (String key : paramNames) {
	    if (key.contains("[")) {
		propertyName = key.substring(0, key.indexOf("["));
	    } else {
		propertyName = key;
	    }

	    m = findSetter(dataType, propertyName);
	    Object obj = null;
	    if (m != null) {
		obj = paramMap.get(key);
		if (obj != null) {
		    m.invoke(dataObject, createValueObject(m.getParameterTypes()[0], paramMap.get(key)));
		}
	    }
	}
	return dataObject;

    }

    private static Object createValueObject(Class<?> type, String[] valueArr) throws ParseException {
	Object value = null;
	if (type.getName().equals("int") || type == Integer.class) {
	    if (valueArr[0].isEmpty()) {
		value = new Integer("0");
	    } else {
		value = new Integer(valueArr[0]);
	    }
	} else if (type.getName().equals("float") || type == Float.class) {
	    if (valueArr[0].isEmpty()) {
		value = new Float("0");
	    } else {
		value = new Float(valueArr[0]);
	    }
	} else if (type.getName().equals("double") || type == Double.class) {
	    if (valueArr[0].isEmpty()) {
		value = new Double("0");
	    } else {
		value = new Double(valueArr[0]);
	    }
	} else if (type.getName().equals("long") || type == Long.class) {
	    if (valueArr[0].isEmpty()) {
		value = new Long("0");
	    } else {
		value = new Long(valueArr[0]);
	    }
	} else if (type.getName().equals("boolean") || type == Boolean.class) {
	    value = new Boolean(valueArr[0]);
	} else if (type == Date.class) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    value = sdf.parse(valueArr[0]);
	} else if (type == String.class) {
	    value = String.valueOf(valueArr[0]);
	} else if (type == String[].class) {
	    value = valueArr;
	} else if (type == int[].class) {
	    int[] intArr = new int[valueArr.length];
	    int i = 0;
	    for (String tmp : valueArr) {
		intArr[i] = new Integer(tmp);
		i++;
	    }
	    value = intArr;
	} else {
	    value = valueArr[0];
	}

	return value;
    }

    private static Method findSetter(Class<?> type, String name) {
	Method[] methods = type.getMethods();

	String propName = null;
	for (Method m : methods) {
	    if (!m.getName().startsWith("set"))
		continue;
	    propName = m.getName().substring(3);

	    if (propName.toLowerCase().equals(name.toLowerCase())) {
		return m;
	    }
	}

	return null;
    }
}
