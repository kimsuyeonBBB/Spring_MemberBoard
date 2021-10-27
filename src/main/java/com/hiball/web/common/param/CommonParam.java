package com.hiball.web.common.param;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hiball.web.common.enums.ColumnsEnum;

public class CommonParam implements Cloneable {
	private String serviceName;
	private String subServiceName;
	private String sortColumn;
	private String serviceType;
	private String gameInfoId;
	private int gameType;
	private int year;
	private int season;
	private int month;
	private String gameDay;
	private int weekDay;
	private int stadiumsId;
	private int refereeMain;
	private String tablePostfix;
	private List<String> tablePostfixList;
	private String homeOrAway;
	private int dayFactor=-10;
	private String startDate;
	private String endDate;
	private int batterTeamId;
	private int pitcherTeamId;
	private int batterMembersId;
	private int pitcherMembersId;
	private int catcherMembersId;
	private Map<String, Object> summaryTablePostfixMap;
	private String distBatOrPitch;
	private int lastedGameCnt;
	private String groupingColumn;
	private int positionTypeCode;
	private String propertyStr = "";
	private String[] propertyArray;
	private ColumnsEnum[] columnsEnumArray;
	private Map<ColumnsEnum, Object> addedValueMap;
	private String keyValue;
	private int fieldingResultPositionCode;
	private int beginIdx;
	private int listSize;

	private String no;
	private String name;
	private String email;
	private String id;
	private String password;
	private String createdDate;
	private Date modifiedDate;
	
	private String title;
	private String story;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getSubServiceName() {
		return subServiceName;
	}
	public void setSubServiceName(String subServiceName) {
		this.subServiceName = subServiceName;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getGameInfoId() {
		return gameInfoId;
	}
	public void setGameInfoId(String gameInfoId) {
		this.gameInfoId = gameInfoId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGameDay() {
		return gameDay;
	}
	public void setGameDay(String gameDay) {
		this.gameDay = gameDay;
	}
	public String getTablePostfix() {
		return tablePostfix;
	}
	public void setTablePostfix(String tablePostfix) {
		this.tablePostfix = tablePostfix;
	}
	public int getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	public int getRefereeMain() {
		return refereeMain;
	}
	public void setRefereeMain(int refereeMain) {
		this.refereeMain = refereeMain;
	}
	public List<String> getTablePostfixList() {
		return tablePostfixList;
	}
	public void setTablePostfixList(List<String> tablePostfixList) {
		this.tablePostfixList = tablePostfixList;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getHomeOrAway() {
		return homeOrAway;
	}
	public void setHomeOrAway(String homeOrAway) {
		this.homeOrAway = homeOrAway;
	}
	public int getDayFactor() {
		return dayFactor;
	}
	public void setDayFactor(int dayFactor) {
		this.dayFactor = dayFactor;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getBatterTeamId() {
		return batterTeamId;
	}
	public void setBatterTeamId(int batterTeamId) {
		this.batterTeamId = batterTeamId;
	}
	public int getPitcherTeamId() {
		return pitcherTeamId;
	}
	public void setPitcherTeamId(int pitcherTeamId) {
		this.pitcherTeamId = pitcherTeamId;
	}
	public Map<String, Object> getSummaryTablePostfixMap() {
		return summaryTablePostfixMap;
	}
	public void setSummaryTablePostfixMap(Map<String, Object> summaryTablePostfixMap) {
		this.summaryTablePostfixMap = summaryTablePostfixMap;
	}
	public int getBatterMembersId() {
		return batterMembersId;
	}
	public void setBatterMembersId(int batterMembersId) {
		this.batterMembersId = batterMembersId;
	}
	public int getPitcherMembersId() {
		return pitcherMembersId;
	}
	public void setPitcherMembersId(int pitcherMembersId) {
		this.pitcherMembersId = pitcherMembersId;
	}
	public int getStadiumsId() {
		return stadiumsId;
	}
	public void setStadiumsId(int stadiumsId) {
		this.stadiumsId = stadiumsId;
	}
	public int getCatcherMembersId() {
		return catcherMembersId;
	}
	public void setCatcherMembersId(int catcherMembersId) {
		this.catcherMembersId = catcherMembersId;
	}
	public String getDistBatOrPitch() {
		return distBatOrPitch;
	}
	public void setDistBatOrPitch(String distBatOrPitch) {
		this.distBatOrPitch = distBatOrPitch;
	}
	public int getLastedGameCnt() {
		return lastedGameCnt;
	}
	public void setLastedGameCnt(int lastedGameCnt) {
		this.lastedGameCnt = lastedGameCnt;
	}
	public String getPropertyStr() {
		return propertyStr;
	}
	public void setPropertyStr(String propertyStr) {
		this.propertyStr = propertyStr;
	}
	public String[] getPropertyArray() {
		return propertyArray;
	}
	public void setPropertyArray(String[] propertyArray) {
		this.propertyArray = propertyArray;
	}
	public ColumnsEnum[] getColumnsEnumArray() {
		return columnsEnumArray;
	}
	public void setColumnsEnumArray(ColumnsEnum[] columnsEnumArray) {
		this.columnsEnumArray = columnsEnumArray;
	}
	public Map<ColumnsEnum, Object> getAddedValueMap() {
		return addedValueMap;
	}
	public void setAddedValueMap(Map<ColumnsEnum, Object> addedValueMap) {
		this.addedValueMap = addedValueMap;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getGroupingColumn() {
		return groupingColumn;
	}
	public void setGroupingColumn(String groupingColumn) {
		this.groupingColumn = groupingColumn;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public int getFieldingResultPositionCode() {
		return fieldingResultPositionCode;
	}
	public void setFieldingResultPositionCode(int fieldingResultPositionCode) {
		this.fieldingResultPositionCode = fieldingResultPositionCode;
	}
	public int getPositionTypeCode() {
		return positionTypeCode;
	}
	public void setPositionTypeCode(int positionTypeCode) {
		this.positionTypeCode = positionTypeCode;
	}
	public int getGameType() {
	    return gameType;
	}
	public void setGameType(int gameType) {
	    this.gameType = gameType;
	}
	public int getSeason() {
	    return season;
	}
	public void setSeason(int season) {
	    this.season = season;
	}
	public int getBeginIdx() {
	    return beginIdx;
	}
	public void setBeginIdx(int beginNum) {
	    this.beginIdx = beginNum;
	}
	public int getListSize() {
	    return listSize;
	}
	public void setListSize(int listSize) {
	    this.listSize = listSize;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
}	
