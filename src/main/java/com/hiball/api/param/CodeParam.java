package com.hiball.api.param;

import com.hiball.web.common.param.CommonParam;

public class CodeParam extends CommonParam {
    private int pCodeId;
    private int codeId;
    private int teamId;
    private String name;
    private String codeType;
   

    public int getpCodeId() {
	return pCodeId;
    }

    public void setpCodeId(int pCodeId) {
	this.pCodeId = pCodeId;
    }

    public int getCodeId() {
	return codeId;
    }

    public void setCodeId(int codeId) {
	this.codeId = codeId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getTeamId() {
	return teamId;
    }

    public void setTeamId(int teamId) {
	this.teamId = teamId;
    }

    public String getCodeType() {
	return codeType;
    }

    public void setCodeType(String codeType) {
	this.codeType = codeType;
    }

    @Override
    public String toString() {
	return "CodeParam [pCodeId=" + pCodeId + ", codeId=" + codeId + ", teamId=" + teamId + ", name=" + name
		+ ", codeType=" + codeType + "]";
    }
}
