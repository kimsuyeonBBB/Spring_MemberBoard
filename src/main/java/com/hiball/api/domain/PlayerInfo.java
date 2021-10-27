package com.hiball.api.domain;

import java.util.List;

import com.hiball.web.common.domain.CommonDomain;

public class PlayerInfo extends CommonDomain {
    private static final long serialVersionUID = 7059447314119377827L;

    private String firstJoinTeam;
    private String firstJoinYear;
    private String hometown;
    private String height;
    private String weight;
    private String birthDate;
    private String pitchingHand;
    private String battingHand;
    private String pitchingForm;
    private int positionTypeCode;
    private String positionTypeName;
    private String position;
    private String rosterTypeName;
    private String backnumber;
    private List<PlayerInfo> playerInfos;
    private String firstName;
    private String lastName;
    private String shortName;

    public String getFirstJoinTeam() {
	return firstJoinTeam;
    }

    public void setFirstJoinTeam(String firstJoinTeam) {
	this.firstJoinTeam = firstJoinTeam;
    }

    public String getFirstJoinYear() {
	return firstJoinYear;
    }

    public void setFirstJoinYear(String firstJoinYear) {
	this.firstJoinYear = firstJoinYear;
    }

    public String getHometown() {
	return hometown;
    }

    public void setHometown(String hometown) {
	this.hometown = hometown;
    }

    public String getHeight() {
	return height;
    }

    public void setHeight(String height) {
	this.height = height;
    }

    public String getWeight() {
	return weight;
    }

    public void setWeight(String weight) {
	this.weight = weight;
    }

    public String getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
    }

    public String getPitchingHand() {
	return pitchingHand;
    }

    public void setPitchingHand(String pitchingHand) {
	this.pitchingHand = pitchingHand;
    }

    public String getBattingHand() {
	return battingHand;
    }

    public void setBattingHand(String battingHand) {
	this.battingHand = battingHand;
    }

    public String getPitchingForm() {
	return pitchingForm;
    }

    public void setPitchingForm(String pitchingForm) {
	this.pitchingForm = pitchingForm;
    }

    public List<PlayerInfo> getPlayerInfos() {
	return playerInfos;
    }

    public void setPlayerInfos(List<PlayerInfo> playerInfos) {
	this.playerInfos = playerInfos;
    }

    public int getPositionTypeCode() {
	return positionTypeCode;
    }

    public void setPositionTypeCode(int positionTypeCode) {
	this.positionTypeCode = positionTypeCode;
    }

    public String getPosition() {
	return position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public String getBacknumber() {
	return backnumber;
    }

    public void setBacknumber(String backnumber) {
	this.backnumber = backnumber;
    }

    public String getPositionTypeName() {
	return positionTypeName;
    }

    public void setPositionTypeName(String positionTypeName) {
	this.positionTypeName = positionTypeName;
    }

    public String getRosterTypeName() {
	return rosterTypeName;
    }

    public void setRosterTypeName(String rosterTypeName) {
	this.rosterTypeName = rosterTypeName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
