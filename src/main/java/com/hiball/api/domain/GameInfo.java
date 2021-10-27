package com.hiball.api.domain;

import com.hiball.web.common.domain.CommonDomain;

public class GameInfo extends CommonDomain {
    private static final long serialVersionUID = -4443749096346712161L;

    private String stadiumName;
    private String weatherCode;
    private String weather;
    private String homeBeginPitcherId;
    private String homeBeginPitcher;
    private String homePitcherFirstName;
    private String homePitcherLastName;
    private String homePitcherShortName;
    private String homePitcherBacknum;
    private String homePitcherForeignerYn;
    private String homeShortName;
    private String homeEngName;
    private String awayBeginPitcherId;
    private String awayBeginPitcher;
    private String awayPitcherFirstName;
    private String awayPitcherLastName;
    private String awayPitcherShortName;
    private String awayPitcherBacknum;
    private String awayPitcherForeignerYn;
    private String awayShortName;
    private String awayEngName;
    private String homeTeamScore;
    private String awayTeamScore;
    private String homePitcherResult;
    private String awayPitcherResult;
    private String videoType;

    public String getStadiumName() {
	return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
	this.stadiumName = stadiumName;
    }

    public String getWeatherCode() {
	return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
	this.weatherCode = weatherCode;
    }

    public String getWeather() {
	return weather;
    }

    public void setWeather(String weather) {
	this.weather = weather;
    }

    public String getHomeBeginPitcherId() {
	return homeBeginPitcherId;
    }

    public void setHomeBeginPitcherId(String homeBeginPitcherId) {
	this.homeBeginPitcherId = homeBeginPitcherId;
    }

    public String getHomeBeginPitcher() {
	return homeBeginPitcher;
    }

    public void setHomeBeginPitcher(String homeBeginPitcher) {
	this.homeBeginPitcher = homeBeginPitcher;
    }

    public String getAwayBeginPitcherId() {
	return awayBeginPitcherId;
    }

    public void setAwayBeginPitcherId(String awayBeginPitcherId) {
	this.awayBeginPitcherId = awayBeginPitcherId;
    }

    public String getAwayBeginPitcher() {
	return awayBeginPitcher;
    }

    public void setAwayBeginPitcher(String awayBeginPitcher) {
	this.awayBeginPitcher = awayBeginPitcher;
    }

    public String getHomeTeamScore() {
	return homeTeamScore;
    }

    public void setHomeTeamScore(String homeTeamScore) {
	this.homeTeamScore = homeTeamScore;
    }

    public String getAwayTeamScore() {
	return awayTeamScore;
    }

    public void setAwayTeamScore(String awayTeamScore) {
	this.awayTeamScore = awayTeamScore;
    }

    public String getHomePitcherResult() {
	return homePitcherResult;
    }

    public void setHomePitcherResult(String homePitcherResult) {
	this.homePitcherResult = homePitcherResult;
    }

    public String getAwayPitcherResult() {
	return awayPitcherResult;
    }

    public void setAwayPitcherResult(String awayPitcherResult) {
	this.awayPitcherResult = awayPitcherResult;
    }

    @Override
    public String toString() {
	return "GameInfo [gameInfoId=" + super.getGameInfoId() + ", gameDay=" + super.getGameDay() + ", weekDay=" + super.getWeekDay() + ", stadiumName="
		+ stadiumName + ", weatherCode=" + weatherCode + ", weather=" + weather + ", homeTeamId=" + super.getHomeTeamId()
		+ ", homeTeamName=" + super.getHomeTeamName() + ", homeBeginPitcherId=" + homeBeginPitcherId
		+ ", homeBeginPitcher=" + homeBeginPitcher + ", awayTeamId=" + super.getAwayTeamId() + ", awayTeamName="
		+ super.getAwayTeamName() + ", awayBeginPitcherId=" + awayBeginPitcherId + ", awayBeginPitcher=" + awayBeginPitcher
		+ ", homeTeamScore=" + homeTeamScore + ", awayTeamScore=" + awayTeamScore + ", homePitcherResult="
		+ homePitcherResult + ", awayPitcherResult=" + awayPitcherResult + "]";
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getHomePitcherFirstName() {
        return homePitcherFirstName;
    }

    public void setHomePitcherFirstName(String homePitcherFirstName) {
        this.homePitcherFirstName = homePitcherFirstName;
    }

    public String getHomePitcherLastName() {
        return homePitcherLastName;
    }

    public void setHomePitcherLastName(String homePitcherLastName) {
        this.homePitcherLastName = homePitcherLastName;
    }

    public String getHomePitcherShortName() {
        return homePitcherShortName;
    }

    public void setHomePitcherShortName(String homePitcherShortName) {
        this.homePitcherShortName = homePitcherShortName;
    }

    public String getAwayPitcherFirstName() {
        return awayPitcherFirstName;
    }

    public void setAwayPitcherFirstName(String awayPitcherFirstName) {
        this.awayPitcherFirstName = awayPitcherFirstName;
    }

    public String getAwayPitcherLastName() {
        return awayPitcherLastName;
    }

    public void setAwayPitcherLastName(String awayPitcherLastName) {
        this.awayPitcherLastName = awayPitcherLastName;
    }

    public String getAwayPitcherShortName() {
        return awayPitcherShortName;
    }

    public void setAwayPitcherShortName(String awayPitcherShortName) {
        this.awayPitcherShortName = awayPitcherShortName;
    }

    public String getHomeShortName() {
        return homeShortName;
    }

    public void setHomeShortName(String homeShortName) {
        this.homeShortName = homeShortName;
    }

    public String getHomeEngName() {
        return homeEngName;
    }

    public void setHomeEngName(String homeEngName) {
        this.homeEngName = homeEngName;
    }

    public String getAwayShortName() {
        return awayShortName;
    }

    public void setAwayShortName(String awayShortName) {
        this.awayShortName = awayShortName;
    }

    public String getAwayEngName() {
        return awayEngName;
    }

    public void setAwayEngName(String awayEngName) {
        this.awayEngName = awayEngName;
    }

    public String getHomePitcherForeignerYn() {
        return homePitcherForeignerYn;
    }

    public void setHomePitcherForeignerYn(String homePitcherForeignerYn) {
        this.homePitcherForeignerYn = homePitcherForeignerYn;
    }

    public String getAwayPitcherForeignerYn() {
        return awayPitcherForeignerYn;
    }

    public void setAwayPitcherForeignerYn(String awayPitcherForeigenerYn) {
        this.awayPitcherForeignerYn = awayPitcherForeigenerYn;
    }

    public String getHomePitcherBacknum() {
        return homePitcherBacknum;
    }

    public void setHomePitcherBacknum(String homePitcherBacknum) {
        this.homePitcherBacknum = homePitcherBacknum;
    }

    public String getAwayPitcherBacknum() {
        return awayPitcherBacknum;
    }

    public void setAwayPitcherBacknum(String awayPitcherBacknum) {
        this.awayPitcherBacknum = awayPitcherBacknum;
    }

}