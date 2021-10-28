package com.hiball.api.services.code.service;

import java.util.List;

import com.hiball.api.domain.CodeDomain;
import com.hiball.api.param.CodeParam;

public interface CodeService {
    public List<CodeDomain> retrieveCodeInfo(CodeParam param);
    public List<CodeDomain> retrievePlayerInfo(CodeParam param);
    public List<CodeDomain> retrieveTeamInfo(CodeParam param);
    public List<CodeDomain> retrieveMemberInfo(CodeParam param);
    public List<CodeDomain> retrieveMemberCountInfo(CodeParam param);
    public int retrieveMemberAddInfo(CodeParam param);
    public List<CodeDomain> retrieveMemberUpdateInfo(CodeParam param);
    public int retrieveMemberNewUpdateInfo(CodeParam param);
    public int retrieveMemberDeleteInfo(CodeParam param);
    public List<CodeDomain> retrieveBoardListInfo(CodeParam param);
    public List<CodeDomain> retrieveBoardCountInfo(CodeParam param);
    public int retrieveBoardAddInfo(CodeParam param);
    public List<CodeDomain> retrieveBoardUpdateInfo(CodeParam param);
    public int retrieveBoardNewUpdateInfo(CodeParam param);
    public int retrieveBoardDeleteInfo(CodeParam param);
    public List<CodeDomain> retrieveFindIdInfo(CodeParam param);
    public List<CodeDomain> retrieveFindPwInfo(CodeParam param);
}
