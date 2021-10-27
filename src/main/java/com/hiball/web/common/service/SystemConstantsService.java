package com.hiball.web.common.service;

import java.util.Map;

import com.hiball.web.common.param.SystemConstantsParam;

public interface SystemConstantsService {
	public Map<String, Object> retrieveSummaryTableInfo(SystemConstantsParam domain);
}
