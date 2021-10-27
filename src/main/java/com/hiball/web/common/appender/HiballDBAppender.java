package com.hiball.web.common.appender;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ch.qos.logback.classic.db.names.DBNameResolver;
import ch.qos.logback.classic.db.names.DefaultDBNameResolver;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;

public class HiballDBAppender extends DBAppenderBase<ILoggingEvent> {
    protected static final Method GET_GENERATED_KEYS_METHOD;
    
    private static final int TIMESTAMP_IDX = 1;
    private static final int FORMATTED_MESSAGE_IDX = 2;
    private static final int LOGGER_NAME_IDX = 3;
    private static final int LEVEL_IDX = 4;
    private static final int CONNECT_USER_ID = 5;
    private static final int CALLER_FILENAME_INDEX = 6;
    private static final int CALLER_CLASS_INDEX = 7;
    private static final int CALLER_METHOD_INDEX = 8;
    private static final int CALLER_LINE_INDEX = 9;
    
    private static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();
    
    protected String insertSQL;
    private DBNameResolver dbNameResolver;
    
    static {
	// PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
	Method getGeneratedKeysMethod;
	try {
	    // the
	    getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
	} catch (Exception ex) {
	    getGeneratedKeysMethod = null;
	}
	GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }
    
    @Override
    public void start() {
	if(dbNameResolver == null) {
	    dbNameResolver = new DefaultDBNameResolver();
	}
	
	insertSQL = HiballSQLBuilder.buildInsertSQL(dbNameResolver);
	super.start();
    }
    
    @Override
    protected Method getGeneratedKeysMethod() {
	return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
	return insertSQL;
    }

    @Override
    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {
	bindLoggingWithInsertStatement(insertStatement, event);

	int updateCount = insertStatement.executeUpdate();
	if (updateCount != 1) {
	    addWarn("Failed to insert loggingEvent");
	}
    }
   
    private void bindLoggingWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
	StackTraceElement[] callerDataArray = event.getCallerData();
	Object[] argArr = event.getArgumentArray();
	StackTraceElement caller = extractFirstCaller(callerDataArray);
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	String userId = "";
	String viewId = "";
	int arrLength = argArr != null ? argArr.length : 0;
	
	if (arrLength != 0) {
	    
	    JSONObject jsonObj = parseArg(argArr);
	    userId = (String)jsonObj.get("id");
	    viewId = (String)jsonObj.get("viewId");
	}
	
	stmt.setLong(TIMESTAMP_IDX, event.getTimeStamp());
	stmt.setString(FORMATTED_MESSAGE_IDX, event.getFormattedMessage());
	stmt.setString(LOGGER_NAME_IDX, format.format(event.getTimeStamp()));
	stmt.setString(LEVEL_IDX, event.getLevel().toString());
	stmt.setString(CONNECT_USER_ID, userId);
	stmt.setString(CALLER_FILENAME_INDEX, viewId);
	stmt.setString(CALLER_CLASS_INDEX, caller.getClassName());
	stmt.setString(CALLER_METHOD_INDEX, caller.getMethodName());
	stmt.setString(CALLER_LINE_INDEX, Integer.toString(caller.getLineNumber()));
    }
    
    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
	StackTraceElement caller = EMPTY_CALLER_DATA;
	if (hasAtLeastOneNonNullElement(callerDataArray))
	    caller = callerDataArray[0];
	return caller;
    }

    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
	return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }
    
    private JSONObject parseArg(Object[] argArr) {
	String result = (String)argArr[0];
	
	JSONParser parser = new JSONParser();
	JSONObject jsonObj = null;
	
	try {
	    jsonObj = (JSONObject)parser.parse(result);
	} catch (ParseException pe) {
	    
	}
	
	return jsonObj;
    }

    @Override
    protected void secondarySubAppend(ILoggingEvent eventObject, Connection connection, long eventId) throws Throwable {
	
    }

}
