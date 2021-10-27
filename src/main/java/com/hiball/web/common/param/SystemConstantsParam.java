package com.hiball.web.common.param;


public class SystemConstantsParam extends CommonParam {
	private String type;
	private String summaryTableName;
	private String postfix;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSummaryTableName() {
		return summaryTableName;
	}
	public void setSummaryTableName(String summaryTableName) {
		this.summaryTableName = summaryTableName;
	}
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
}
