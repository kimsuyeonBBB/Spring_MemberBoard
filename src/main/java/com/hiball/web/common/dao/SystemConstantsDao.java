package com.hiball.web.common.dao;

import java.util.Map;

import com.hiball.web.common.param.SystemConstantsParam;

public interface SystemConstantsDao {
	public Map<String, Object> selectSummaryTablePostfix(SystemConstantsParam domain);
}
