package com.hiball.web.common.domain;

import java.io.Serializable;

public class CommonDomain implements Serializable {
    private static final long serialVersionUID = 3575838505221017243L;
    
    private String gameInfoId;
    private String gameRecordId;
    private int year;
    private String gameDay;
    private int month;
    private int weekDay;
    private int teamId;
    private String teamName;
    private int opponentTeamId;
    private String opponentTeamName;
    private int membersId;
    private int playerId;
    private String membersName;
    private int batterTeamId;
    private int pitcherTeamId;
    private int catcherTeamId;
    private int batterMembersId;
    private int pitcherMembersId;
    private int catcherMembersId;
    private String playerName;
    private String batterName;
    private String pitcherName;
    private String catcherName;
    private int homeTeamId;
    private int awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private String position;
    private int totCnt;
    
    public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public String getGameInfoId() {
	return gameInfoId;
    }

    public void setGameInfoId(String gameInfoId) {
	this.gameInfoId = gameInfoId;
    }

    public String getGameRecordId() {
	return gameRecordId;
    }

    public void setGameRecordId(String gameRecordId) {
	this.gameRecordId = gameRecordId;
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

    public int getMonth() {
	return month;
    }

    public void setMonth(int month) {
	this.month = month;
    }

    public int getWeekDay() {
	return weekDay;
    }

    public void setWeekDay(int weekDay) {
	this.weekDay = weekDay;
    }

    public int getTeamId() {
	return teamId;
    }

    public void setTeamId(int teamId) {
	this.teamId = teamId;
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

    public String getBatterName() {
	return batterName;
    }

    public void setBatterName(String batterName) {
	this.batterName = batterName;
    }

    public String getPitcherName() {
	return pitcherName;
    }

    public void setPitcherName(String pitcherName) {
	this.pitcherName = pitcherName;
    }

    public String getTeamName() {
	return teamName;
    }

    public void setTeamName(String teamName) {
	this.teamName = teamName;
    }

    public int getMembersId() {
	return membersId;
    }

    public void setMembersId(int membersId) {
	this.membersId = membersId;
    }

    public String getMembersName() {
	return membersName;
    }

    public void setMembersName(String membersName) {
	this.membersName = membersName;
    }

    public int getHomeTeamId() {
	return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
	this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
	return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
	this.awayTeamId = awayTeamId;
    }

    public String getHomeTeamName() {
	return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
	this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
	return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
	this.awayTeamName = awayTeamName;
    }

    public int getCatcherMembersId() {
	return catcherMembersId;
    }

    public void setCatcherMembersId(int catcherMembersId) {
	this.catcherMembersId = catcherMembersId;
    }

    public String getCatcherName() {
	return catcherName;
    }

    public void setCatcherName(String catcherName) {
	this.catcherName = catcherName;
    }

    public int getOpponentTeamId() {
	return opponentTeamId;
    }

    public void setOpponentTeamId(int opponentTeamId) {
	this.opponentTeamId = opponentTeamId;
    }

    public String getOpponentTeamName() {
	return opponentTeamName;
    }

    public void setOpponentTeamName(String opponentTeamName) {
	this.opponentTeamName = opponentTeamName;
    }

    public int getCatcherTeamId() {
	return catcherTeamId;
    }

    public void setCatcherTeamId(int catcherTeamId) {
	this.catcherTeamId = catcherTeamId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
