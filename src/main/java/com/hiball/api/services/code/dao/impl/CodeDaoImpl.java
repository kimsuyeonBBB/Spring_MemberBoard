package com.hiball.api.services.code.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hiball.api.domain.CodeDomain;
import com.hiball.api.param.CodeParam;
import com.hiball.api.services.code.dao.CodeDao;
import com.hiball.web.common.dao.AbstractCommonAPIDao;
import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.handler.ColumnResultHandler;

@Repository("codeDao")
public class CodeDaoImpl extends AbstractCommonAPIDao implements CodeDao {
    private final String prefix = "com.hiball.sql.code.";

	@Override
	public List<CodeDomain> selectCodeInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "selectCodeInfo", param);
	}

	@Override
	public List<CodeDomain> selectPlayerInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "selectPlayerInfo", param);
	}

	@Override
	public List<CodeDomain> selectTeamInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "selectTeamInfo", param);
	}

	@Override
	public List<CodeDomain> selectMemberInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "selectMemberInfo", param);
	}
	
	@Override
	public List<CodeDomain> selectMemberCountInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "selectMemberCountInfo",param);
	}

	@Override
	public int insertMemberInfo(CodeParam param) {
		return sqlSession.insert(prefix + "addMemberInfo", param);
	}

	@Override
	public List<CodeDomain> updateMemberInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "updateMemberInfo", param);
	}

	@Override
	public int updateNewMemberInfo(CodeParam param) {
		return sqlSession.update(prefix + "updateNewMemberInfo",param);
	}

	@Override
	public int deleteMemberInfo(CodeParam param) {
		return sqlSession.delete(prefix + "deleteMemberInfo",param);
	}

	@Override
	public List<CodeDomain> selectBoardInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "selectBoardInfo",param);
	}

	@Override
	public int insertBoardInfo(CodeParam param) {
		return sqlSession.insert(prefix + "addBoardInfo", param);
	}

	@Override
	public List<CodeDomain> updateBoardInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "updateBoardInfo",param);
	}

	@Override
	public int updateNewBoardInfo(CodeParam param) {
		return sqlSession.update(prefix + "updateNewBoardInfo",param);
	}

	@Override
	public int deleteBoardInfo(CodeParam param) {
		return sqlSession.delete(prefix + "deleteBoardInfo",param);
	}

	@Override
	public List<CodeDomain> selectFindIdInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "findIdInfo",param);
	}

	@Override
	public List<CodeDomain> selectFindPwInfo(CodeParam param) {
		return sqlSession.selectList(prefix + "findPwInfo",param);
	}


}
