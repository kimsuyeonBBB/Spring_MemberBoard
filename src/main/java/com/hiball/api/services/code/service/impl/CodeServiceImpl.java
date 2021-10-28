package com.hiball.api.services.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiball.api.domain.CodeDomain;
import com.hiball.api.param.CodeParam;
import com.hiball.api.services.code.dao.CodeDao;
import com.hiball.api.services.code.service.CodeService;
import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.service.AbstractCommonAPIService;

@Service("codeService")
public class CodeServiceImpl extends AbstractCommonAPIService<CodeParam> implements CodeService {
    private static Logger logger = LoggerFactory.getLogger(CodeServiceImpl.class);
    @Autowired
    CodeDao codeDao;

	@Override
	public List<CodeDomain> retrieveCodeInfo(CodeParam param) {
		return codeDao.selectCodeInfo(param);
	}

	@Override
	public List<CodeDomain> retrievePlayerInfo(CodeParam param) {
		return codeDao.selectPlayerInfo(param);
	}

	@Override
	public List<CodeDomain> retrieveTeamInfo(CodeParam param) {
		List<CodeDomain> result = codeDao.selectTeamInfo(param);
		int gameType = param.getGameType();

		/*  경기형태가 퓨처스(4206)일 경우에만 추가되는 팀 코드 정보 */
		if (gameType == 4206) {
			CodeDomain addCd = new CodeDomain();
			addCd.setTeamId(30037);
			addCd.setTeamName("한화A");
			result.add(addCd);
			CodeDomain addCd1 = new CodeDomain();
			addCd1.setTeamId(30038);
			addCd1.setTeamName("한화B");
			result.add(addCd1);
		}

		return result;
    }

    @Override
    public List<CodeDomain> retrieveMemberInfo(CodeParam param) {
    	//memberInfo totCnt
    	return codeDao.selectMemberInfo(param);
    }
    
    @Override
	public List<CodeDomain> retrieveMemberCountInfo(CodeParam param) {
		return codeDao.selectMemberCountInfo(param);
	}
    
    @Override
	public int retrieveMemberAddInfo(CodeParam param) {
		return codeDao.insertMemberInfo(param);
	}
       
    @Override
	public List<CodeDomain> retrieveMemberUpdateInfo(CodeParam param) {
		return codeDao.updateMemberInfo(param);
	}
    
    @Override
    public int retrieveMemberNewUpdateInfo(CodeParam param) {
    	return codeDao.updateNewMemberInfo(param);
    }
    
    @Override
    public int retrieveMemberDeleteInfo(CodeParam param) {
    	return codeDao.deleteMemberInfo(param);
    }
    
    @Override
    public List<CodeDomain> retrieveBoardListInfo(CodeParam param) {
    	return codeDao.selectBoardInfo(param);
    }
    
    @Override
	public List<CodeDomain> retrieveBoardCountInfo(CodeParam param) {
		return codeDao.selectBoardCountInfo(param);
	}
    
    @Override
    public int retrieveBoardAddInfo(CodeParam param) {
    	return codeDao.insertBoardInfo(param);
    }
    
    @Override
    public List<CodeDomain> retrieveBoardUpdateInfo(CodeParam param) {
    	return codeDao.updateBoardInfo(param);
    }
    
    @Override
    public int retrieveBoardNewUpdateInfo(CodeParam param) {
    	return codeDao.updateNewBoardInfo(param);
    }
    
    @Override
    public int retrieveBoardDeleteInfo(CodeParam param) {
    	return codeDao.deleteBoardInfo(param);
    }
    
    @Override
    public List<CodeDomain> retrieveFindIdInfo(CodeParam param) {
    	return codeDao.selectFindIdInfo(param);
    }
    
    @Override
    public List<CodeDomain> retrieveFindPwInfo(CodeParam param) {
    	return codeDao.selectFindPwInfo(param);
    }

    @Override
	public Map<String, Object> execute(String serType, String sortColumn, Map<String, String[]> paramMap)
			throws HiBallException {
		logger.info("##### Code Service #####");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Object codeDomain = null;
		CodeParam param = new CodeParam();
		param = this.autoBindingToDomain(paramMap, param);
		logger.debug("##### Parameter In Service {} ######", param);
		if ("codeInfo".equals(serType)) {
			codeDomain = retrieveCodeInfo(param);
		} else if ("playerInfo".equals(serType)) {
			codeDomain = retrievePlayerInfo(param);
		} else if ("teamInfo".contentEquals(serType)) {
			codeDomain = retrieveTeamInfo(param);
		} else if ("memberInfo".contentEquals(serType)) {
			codeDomain = retrieveMemberInfo(param);
		} else if ("memberCountInfo".contentEquals(serType)) {
			codeDomain = retrieveMemberCountInfo(param);
		} else if ("memberAddInfo".contentEquals(serType)) {
			codeDomain = retrieveMemberAddInfo(param);
		} else if ("memberUpdateInfo".contentEquals(serType)) {
			codeDomain = retrieveMemberUpdateInfo(param);
		} else if ("memberNewUpdateInfo".contentEquals(serType)) {
			codeDomain = retrieveMemberNewUpdateInfo(param);
		} else if ("memberDeleteInfo".contentEquals(serType)) {
			codeDomain = retrieveMemberDeleteInfo(param);
		} else if ("boardListInfo".contentEquals(serType)) {
			codeDomain = retrieveBoardListInfo(param);
		} else if ("boardCountInfo".contentEquals(serType)) {
			codeDomain = retrieveBoardCountInfo(param);
		} else if ("boardAddInfo".contentEquals(serType)) {
			codeDomain = retrieveBoardAddInfo(param);
		} else if ("boardUpdateInfo".contentEquals(serType)) {
			codeDomain = retrieveBoardUpdateInfo(param);
		} else if ("boardNewUpdateInfo".contentEquals(serType)) {
			codeDomain = retrieveBoardNewUpdateInfo(param);
		} else if ("boardDeleteInfo".contentEquals(serType)) {
			codeDomain = retrieveBoardDeleteInfo(param);
		} else if ("findIdInfo".contentEquals(serType)) {
			codeDomain = retrieveFindIdInfo(param);
		} else if ("findPwInfo".contentEquals(serType)) {
			codeDomain = retrieveFindPwInfo(param);
		} 

		resultMap.put("resultMap", codeDomain);

		return resultMap;
	}

	

	

}