package com.hiball.web.common.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.hiball.web.common.bind.DataBinder;
import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.param.CommonParam;

public abstract class AbstractCommonAPIService<D extends CommonParam> {
    private Map<String, Object> summaryTablePostfixMap;

    @Autowired
    protected MultiRetrieveService mrs;

    public abstract Map<String, Object> execute(String subService, String sortColumn, Map<String, String[]> paramMap) throws HiBallException;
    
    @SuppressWarnings("unchecked")
    public void configRetrievedSeason(D domain) {
	String year = "";
	String date = "";
	
	if (domain.getYear() != 0) {
	    year = Integer.toString(domain.getYear());
	} else if (domain.getGameDay() != null && !StringUtils.isEmpty(domain.getGameDay())) {
	    date = domain.getGameDay();
	    year = date.substring(0, date.indexOf("-"));
	} else {
	    year = "2013";
	}

	if (summaryTablePostfixMap != null) {
	    Map<String, Object> sumTableInfoMap = (Map<String, Object>) summaryTablePostfixMap.get(year);
	    if (sumTableInfoMap != null) {
		domain.setTablePostfix((String) sumTableInfoMap.get("postfix"));
	    }
	}
    }

    public void setSummaryTablePostfixMap(Map<String, Object> summaryTablePostfixMap) {
	this.summaryTablePostfixMap = summaryTablePostfixMap;
    }

    protected Map<String, Object> getSummaryTablePostfixMap() {
	return summaryTablePostfixMap;
    }

    @SuppressWarnings("unchecked")
    public D autoBindingToDomain(Map<String, String[]> paramMap, D domain) throws HiBallException {
	D mappedDomain = null;
	try {
	    mappedDomain = (D) DataBinder.bind(domain.getClass(), paramMap);
	} catch (Exception e) {
	    throw new HiBallException(e);
	}
	
	return mappedDomain;
    }

}
