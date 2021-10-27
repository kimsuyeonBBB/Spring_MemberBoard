package com.hiball.web.common.dao;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.handler.ColumnResultHandler;
import com.hiball.web.common.param.CommonParam;

public abstract class AbstractCommonAPIDao {
    protected Logger logger = LoggerFactory.getLogger("SQL_SESSION");

//    @Autowired
//    @Qualifier("sqlSession_2014")
//    protected SqlSession sqlSession_2014;
//    @Autowired
//    @Qualifier("sqlSession_2015")
//    private SqlSession sqlSession_2015;
//    @Autowired
//    @Qualifier("sqlSession_2016")
//    private SqlSession sqlSession_2016;

    @Autowired
    @Qualifier("sqlSession")
    protected SqlSession sqlSession;

    protected <D extends CommonParam> SqlSession sqlSession(D domain) {
	switch (domain.getYear()) {
//	case 2014:
//	    return sqlSession_2014;
//	case 2015:
//	    return sqlSession_2015;
//	case 2016:
//	    return sqlSession_2016;
	default:
	    return sqlSession;
	}
    }

    @SuppressWarnings("unchecked")
    protected final <D extends CommonParam> D cloneParamInstance(D domain) throws CloneNotSupportedException {
	return (D) domain.clone();
    }

    protected final <D extends CommonParam> ColumnResultHandler cloneParamSelect(String sqlMapperName, ColumnsEnum[] enumsArray, D domain) {
	ColumnResultHandler handler = null;
	D newParam = null;

	try {
	    newParam = cloneParamInstance(domain);
	    handler = new ColumnResultHandler(newParam, enumsArray);
	    this.sqlSession(domain).select(sqlMapperName, newParam, handler);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return handler;
    }
}