package com.hiball.web.common.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hiball.web.common.dao.AbstractCommonAPIDao;
import com.hiball.web.common.dao.SystemConstantsDao;
import com.hiball.web.common.param.SystemConstantsParam;

@Repository ("systemConstantsDao")
public class SystemConstantsDaoImpl extends AbstractCommonAPIDao implements SystemConstantsDao {
	private String prefix = "com.hiball.web.commoninfo.";

	@Override
	public Map<String, Object> selectSummaryTablePostfix(SystemConstantsParam domain) {
		return sqlSession.selectMap(prefix+"selectSummaryTablePostfix", domain, "year");
	}
}
