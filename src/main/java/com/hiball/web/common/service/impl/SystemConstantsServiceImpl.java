package com.hiball.web.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiball.web.common.dao.SystemConstantsDao;
import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.param.SystemConstantsParam;
import com.hiball.web.common.service.AbstractCommonAPIService;
import com.hiball.web.common.service.SystemConstantsService;

@Service("systemConstantsService")
public class SystemConstantsServiceImpl extends AbstractCommonAPIService<SystemConstantsParam>
	implements SystemConstantsService {
    @Autowired
    SystemConstantsDao systemConstantsDao;

    @Override
    public Map<String, Object> retrieveSummaryTableInfo(SystemConstantsParam domain) {
	return systemConstantsDao.selectSummaryTablePostfix(domain);
    }

    @Override
    public Map<String, Object> execute(String subType, String sortColumn, Map<String, String[]> paramMap) throws HiBallException {
	Map<String, Object> resultMap = new HashMap<String, Object>();
	SystemConstantsParam param = new SystemConstantsParam();
	
	//param = this.autoBindingToDomain(paramMap, param);
	
	resultMap.putAll(retrieveSummaryTableInfo(param));
	return resultMap;
    }

}
