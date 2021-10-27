package com.hiball.web.common.appender;

import ch.qos.logback.classic.db.names.DBNameResolver;

public class HiballSQLBuilder {
    static String buildInsertSQL(DBNameResolver dbNameResolver) {
	StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
	
	sqlBuilder.append(dbNameResolver.getTableName(HiballTableName.HIBALL_LOGGING)).append(" (");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.TIMESTMP)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.FORMATTED_MESSAGE)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.LOGGER_NAME)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.LEVEL_STRING)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.CONNECT_USER_ID)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.CALLER_FILENAME)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.CALLER_CLASS)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.CALLER_METHOD)).append(", ");
	sqlBuilder.append(dbNameResolver.getColumnName(HiballColumnName.CALLER_LINE)).append(") ");
	
	sqlBuilder.append("VALUES (?, ?, ?, ? ,?, ?, ?, ?, ?)");
	
	return sqlBuilder.toString();
    }
}
