package com.hiball.api.domain;

import com.hiball.web.common.domain.CommonDomain;

public class CodeDomain extends CommonDomain{
    private static final long serialVersionUID = 7970220659045959094L;
   
    public int codeId;
    public String name;
    private String shortTeamName;
    private String engTeamName;

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

    public String getShortTeamName() {
        return shortTeamName;
    }

    public void setShortTeamName(String shortTeamName) {
        this.shortTeamName = shortTeamName;
    }

    public String getEngTeamName() {
        return engTeamName;
    }

    public void setEngTeamName(String engTeamName) {
        this.engTeamName = engTeamName;
    }
}
