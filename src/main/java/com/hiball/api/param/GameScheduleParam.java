package com.hiball.api.param;

import com.hiball.web.common.param.CommonParam;

public class GameScheduleParam extends CommonParam {
    private int month;
    private int teamId;
    private int oppTeamId;
    private String scheduleType;
    private String fromDate;
    private String toDate;
    private String position;
    private boolean checkbase;
    private String videoType;
    private String[] batterIds;
    private String[] pitcherIds;
    private String[] playerIds;
    private String[] oppPlayerIds;
    private String[] gameInfoIds;
    private String[] innings;
    private String[] ballCodes;
    private String[] runnerStatus;
    private String[] ballCounts;
    private String[] hitTypes;
    private String[] hitResults;
    private String[] checkbases;

    public int getMonth() {
	return month;
    }

    public void setMonth(int month) {
	this.month = month;
    }

    public String getScheduleType() {
	return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
	this.scheduleType = scheduleType;
    }

    public int getTeamId() {
	return teamId;
    }

    public void setTeamId(int teamId) {
	this.teamId = teamId;
    }

    public int getOppTeamId() {
        return oppTeamId;
    }

    public void setOppTeamId(int oppTeamId) {
        this.oppTeamId = oppTeamId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
	return "GameScheduleParam [month=" + month + ", teamId=" + teamId + ", oppTeamId=" + oppTeamId
		+ ", scheduleType=" + scheduleType + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
    }

    public String[] getGameInfoIds() {
        return gameInfoIds;
    }

    public void setGameInfoIds(String[] gameInfoIds) {
        this.gameInfoIds = gameInfoIds;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String[] getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(String[] playerIds) {
        this.playerIds = playerIds;
    }

    public String[] getOppPlayerIds() {
        return oppPlayerIds;
    }

    public void setOppPlayerIds(String[] oppPlayerIds) {
        this.oppPlayerIds = oppPlayerIds;
    }

    public boolean isCheckbase() {
        return checkbase;
    }

    public void setCheckbase(boolean checkbase) {
        this.checkbase = checkbase;
    }

    public String[] getInnings() {
        return innings;
    }

    public void setInnings(String[] innings) {
        this.innings = innings;
    }

    public String[] getBallCodes() {
        return ballCodes;
    }

    public void setBallCodes(String[] ballCodes) {
        this.ballCodes = ballCodes;
    }

    public String[] getRunnerStatus() {
        return runnerStatus;
    }

    public void setRunnerStatus(String[] runnerStatus) {
        this.runnerStatus = runnerStatus;
    }

    public String[] getBallCounts() {
        return ballCounts;
    }

    public void setBallCounts(String[] ballCounts) {
        this.ballCounts = ballCounts;
    }

    public String[] getHitTypes() {
        return hitTypes;
    }

    public void setHitTypes(String[] hitTypes) {
        this.hitTypes = hitTypes;
    }

    public String[] getHitResults() {
        return hitResults;
    }

    public void setHitResults(String[] hitResults) {
        this.hitResults = hitResults;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String[] getBatterIds() {
        return batterIds;
    }

    public void setBatterIds(String[] batterIds) {
        this.batterIds = batterIds;
    }

    public String[] getPitcherIds() {
        return pitcherIds;
    }

    public void setPitcherIds(String[] pitcherids) {
        this.pitcherIds = pitcherids;
    }

    public String[] getCheckbases() {
        return checkbases;
    }

    public void setCheckbases(String[] checkbases) {
        this.checkbases = checkbases;
    }

}
