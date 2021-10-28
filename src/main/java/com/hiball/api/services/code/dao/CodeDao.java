package com.hiball.api.services.code.dao;

import java.util.List;

import com.hiball.api.domain.CodeDomain;
import com.hiball.api.param.CodeParam;
import com.hiball.web.common.enums.ColumnsEnum;

public interface CodeDao {
    public List<CodeDomain> selectCodeInfo(CodeParam param);
    public List<CodeDomain> selectPlayerInfo(CodeParam param);
    public List<CodeDomain> selectTeamInfo(CodeParam param);
    public List<CodeDomain> selectMemberInfo(CodeParam param);
    public List<CodeDomain> selectMemberCountInfo(CodeParam param);
    public int insertMemberInfo(CodeParam param);
    public List<CodeDomain> updateMemberInfo(CodeParam param);
    public int updateNewMemberInfo(CodeParam param);
    public int deleteMemberInfo(CodeParam param);
    public List<CodeDomain> selectBoardInfo(CodeParam param);
    public List<CodeDomain> selectBoardCountInfo(CodeParam param);
    public int insertBoardInfo(CodeParam param);
    public List<CodeDomain> updateBoardInfo(CodeParam param);
    public int updateNewBoardInfo(CodeParam param);
    public int deleteBoardInfo(CodeParam param);
    public static ColumnsEnum[] MEMBERS_IN_TEAM = { ColumnsEnum.membersId, ColumnsEnum.name };
	public List<CodeDomain> selectFindIdInfo(CodeParam param);
	public List<CodeDomain> selectFindPwInfo(CodeParam param);
	
}
