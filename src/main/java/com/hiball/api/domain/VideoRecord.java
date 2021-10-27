package com.hiball.api.domain;

import com.hiball.web.common.domain.CommonDomain;

public class VideoRecord extends CommonDomain {
    private static final long serialVersionUID = -4513579148668753360L;
    
    private String gameInfoId;
    private String season;
    private String gameDay;
    private int homeTeamId;
    private String homeTeamName;
    private int awayTeamId;
    private String awayTeamName;
    private String videoType;
    private int videoSeq;
   
    public String getGameInfoId() {
        return gameInfoId;
    }
    public void setGameInfoId(String gameInfoId) {
        this.gameInfoId = gameInfoId;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String getGameDay() {
        return gameDay;
    }
    public void setGameDay(String gameDay) {
        this.gameDay = gameDay;
    }
    public int getHomeTeamId() {
        return homeTeamId;
    }
    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }
    public String getHomeTeamName() {
        return homeTeamName;
    }
    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }
    public int getAwayTeamId() {
        return awayTeamId;
    }
    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
    public String getAwayTeamName() {
        return awayTeamName;
    }
    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }
    public String getVideoType() {
        return videoType;
    }
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
    public int getVideoSeq() {
        return videoSeq;
    }
    public void setVideoSeq(int videoSeq) {
        this.videoSeq = videoSeq;
    }
    @Override
    public String toString() {
	return "VideoRecord [gameInfoId=" + gameInfoId + ", season=" + season + ", gameDay=" + gameDay + ", homeTeamId="
		+ homeTeamId + ", homeTeamName=" + homeTeamName + ", awayTeamId=" + awayTeamId + ", awayTeamName="
		+ awayTeamName + ", videoType=" + videoType + ", videoSeq=" + videoSeq + "]";
    }
}