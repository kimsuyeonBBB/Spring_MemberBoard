package com.hiball.api.domain;

import java.io.Serializable;

public class GamePatternRecord implements Serializable{
	
	/**
	 * Mybatis 캐싱을 하기 위해 직렬화
	 */
	private static final long serialVersionUID = 1L;
	private String gameRecordId;
	private String gameInfoId;
	private String gameStime;
	private int month;
	private int weekDay;
	private int stadiumsId;
	private String stadiumName;
	private String spectatorCnt;
	private int batterTeamId;
	private String batterTeamName;
	private int batterMembersId;
	private String batterName;
	private String battingHand;
	private int batterRosterType;
	private String batterRosterTypeName;
	private int pitcherTeamId;
	private String pitcherTeamName;
	private int pitcherMembersId;
	private String pitcherName;
	private String pitchingHand;
	private int pitchingFormCode;
	private String pitchingFormName;
	private int pitcherRosterType;
	private String pitcherRosterTypeName;
	private int catcherMembersId;
	private String catcherName;
	private int refereeMain;
	private String refereeMainName;
	private int inning;
	private String sbCnt;
	private int beforeOutCount;
	private int outCount;
	private int beforeRunnerState;
	private int runnerState;
	private int ballSpeed;
	private int ballCode;
	private String strikeYn;
	private String strikeZoneMatchYn;
	private String exPitchZoneCode;
	private int pitchZoneX;
	private int pitchZoneY;
	private String pinZoneYn;
	private int hitTypeCode;
	private String hitTypeName;
	private int hitResultCode;
	private String hitResultName;
	private int hitCourseX;
	private int hitCourseY;
	private String homeOrAway;
	private int tpa;
	private int ab;
	private int b1b;
	private int b2b;
	private int b3b;
	private int hr;
	private int bb;
	private int ibb;
	private int so;
	private int k;
	private int go;
	private int fo;
	private int hbp;
	private int sf;
	private int sh;
	private int accmPitchingCnt;
	private String distAccmPitchingCnt;
	private int beforeBatterScoreGap;
	private String distBatterScoreGap;
	private String distPitcherScoreGap;
	private int distBallCode;
	private int beforeStrikeCount;
	private int beforeBallCount;
	
	public String getGameRecordId() {
		return gameRecordId;
	}
	public void setGameRecordId(String gameRecordId) {
		this.gameRecordId = gameRecordId;
	}
	public String getGameInfoId() {
		return gameInfoId;
	}
	public void setGameInfoId(String gameInfoId) {
		this.gameInfoId = gameInfoId;
	}
	public String getGameStime() {
		return gameStime;
	}
	public void setGameStime(String gameStime) {
		this.gameStime = gameStime;
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
	public int getStadiumsId() {
		return stadiumsId;
	}
	public void setStadiumsId(int stadiumsId) {
		this.stadiumsId = stadiumsId;
	}
	public String getStadiumName() {
		return stadiumName;
	}
	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}
	public int getBatterTeamId() {
		return batterTeamId;
	}
	public void setBatterTeamId(int batterTeamId) {
		this.batterTeamId = batterTeamId;
	}
	public String getBatterTeamName() {
		return batterTeamName;
	}
	public void setBatterTeamName(String batterTeamName) {
		this.batterTeamName = batterTeamName;
	}
	public int getBatterMembersId() {
		return batterMembersId;
	}
	public void setBatterMembersId(int batterMembersId) {
		this.batterMembersId = batterMembersId;
	}
	public String getBatterName() {
		return batterName;
	}
	public void setBatterName(String batterName) {
		this.batterName = batterName;
	}
	public String getBattingHand() {
		return battingHand;
	}
	public void setBattingHand(String battingHand) {
		this.battingHand = battingHand;
	}
	public int getPitcherMembersId() {
		return pitcherMembersId;
	}
	public void setPitcherMembersId(int pitcherMembersId) {
		this.pitcherMembersId = pitcherMembersId;
	}
	public String getPitcherName() {
		return pitcherName;
	}
	public void setPitcherName(String pitcherName) {
		this.pitcherName = pitcherName;
	}
	public String getPitchingHand() {
		return pitchingHand;
	}
	public void setPitchingHand(String pitchingHand) {
		this.pitchingHand = pitchingHand;
	}
	public int getPitchingFormCode() {
		return pitchingFormCode;
	}
	public void setPitchingFormCode(int pitchingFormCode) {
		this.pitchingFormCode = pitchingFormCode;
	}
	public String getPitchingFormName() {
		return pitchingFormName;
	}
	public void setPitchingFormName(String pitchingFormName) {
		this.pitchingFormName = pitchingFormName;
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
	public int getRefereeMain() {
		return refereeMain;
	}
	public void setRefereeMain(int refereeMain) {
		this.refereeMain = refereeMain;
	}
	public String getRefereeMainName() {
		return refereeMainName;
	}
	public void setRefereeMainName(String refereeMainName) {
		this.refereeMainName = refereeMainName;
	}
	public int getBeforeRunnerState() {
		return beforeRunnerState;
	}
	public void setBeforeRunnerState(int beforeRunnerState) {
		this.beforeRunnerState = beforeRunnerState;
	}
	public int getInning() {
		return inning;
	}
	public void setInning(int inning) {
		this.inning = inning;
	}
	public String getSbCnt() {
		return sbCnt;
	}
	public void setSbCnt(String sbCnt) {
		this.sbCnt = sbCnt;
	}
	public int getOutCount() {
		return outCount;
	}
	public void setOutCount(int outCount) {
		this.outCount = outCount;
	}
	public int getRunnerState() {
		return runnerState;
	}
	public void setRunnerState(int runnerState) {
		this.runnerState = runnerState;
	}
	public int getBallSpeed() {
		return ballSpeed;
	}
	public void setBallSpeed(int ballSpeed) {
		this.ballSpeed = ballSpeed;
	}
	public int getBallCode() {
		return ballCode;
	}
	public void setBallCode(int ballCode) {
		this.ballCode = ballCode;
	}
	public String getStrikeYn() {
		return strikeYn;
	}
	public void setStrikeYn(String strikeYn) {
		this.strikeYn = strikeYn;
	}
	public String getStrikeZoneMatchYn() {
		return strikeZoneMatchYn;
	}
	public void setStrikeZoneMatchYn(String strikeZoneMatchYn) {
		this.strikeZoneMatchYn = strikeZoneMatchYn;
	}
	public String getExPitchZoneCode() {
		return exPitchZoneCode;
	}
	public void setExPitchZoneCode(String exPitchZoneCode) {
		this.exPitchZoneCode = exPitchZoneCode;
	}
	public String getPinZoneYn() {
		return pinZoneYn;
	}
	public void setPinZoneYn(String pinZoneYn) {
		this.pinZoneYn = pinZoneYn;
	}
	public int getHitTypeCode() {
		return hitTypeCode;
	}
	public void setHitTypeCode(int hitTypeCode) {
		this.hitTypeCode = hitTypeCode;
	}
	public String getHitTypeName() {
		return hitTypeName;
	}
	public void setHitTypeName(String hitTypeName) {
		this.hitTypeName = hitTypeName;
	}
	public String getHomeOrAway() {
		return homeOrAway;
	}
	public void setHomeOrAway(String homeOrAway) {
		this.homeOrAway = homeOrAway;
	}
	public int getB1b() {
		return b1b;
	}
	public void setB1b(int b1b) {
		this.b1b = b1b;
	}
	public int getB2b() {
		return b2b;
	}
	public void setB2b(int b2b) {
		this.b2b = b2b;
	}
	public int getB3b() {
		return b3b;
	}
	public void setB3b(int b3b) {
		this.b3b = b3b;
	}
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	public int getBb() {
		return bb;
	}
	public void setBb(int bb) {
		this.bb = bb;
	}
	public int getIbb() {
		return ibb;
	}
	public void setIbb(int ibb) {
		this.ibb = ibb;
	}
	public int getPitcherTeamId() {
		return pitcherTeamId;
	}
	public void setPitcherTeamId(int pitcherTeamId) {
		this.pitcherTeamId = pitcherTeamId;
	}
	public String getPitcherTeamName() {
		return pitcherTeamName;
	}
	public void setPitcherTeamName(String pitcherTeamName) {
		this.pitcherTeamName = pitcherTeamName;
	}
	public int getHitResultCode() {
		return hitResultCode;
	}
	public void setHitResultCode(int hitResultCode) {
		this.hitResultCode = hitResultCode;
	}
	public String getHitResultName() {
		return hitResultName;
	}
	public void setHitResultName(String hitResultName) {
		this.hitResultName = hitResultName;
	}
	public int getPitchZoneX() {
		return pitchZoneX;
	}
	public void setPitchZoneX(int pitchZoneX) {
		this.pitchZoneX = pitchZoneX;
	}
	public int getPitchZoneY() {
		return pitchZoneY;
	}
	public void setPitchZoneY(int pitchZoneY) {
		this.pitchZoneY = pitchZoneY;
	}
	public int getHitCourseX() {
		return hitCourseX;
	}
	public void setHitCourseX(int hitCourseX) {
		this.hitCourseX = hitCourseX;
	}
	public int getHitCourseY() {
		return hitCourseY;
	}
	public void setHitCourseY(int hitCourseY) {
		this.hitCourseY = hitCourseY;
	}
	public int getBeforeOutCount() {
		return beforeOutCount;
	}
	public void setBeforeOutCount(int beforeOutCount) {
		this.beforeOutCount = beforeOutCount;
	}
	public int getBatterRosterType() {
		return batterRosterType;
	}
	public void setBatterRosterType(int batterRosterType) {
		this.batterRosterType = batterRosterType;
	}
	public String getBatterRosterTypeName() {
		return batterRosterTypeName;
	}
	public void setBatterRosterTypeName(String batterRosterTypeName) {
		this.batterRosterTypeName = batterRosterTypeName;
	}
	public int getPitcherRosterType() {
		return pitcherRosterType;
	}
	public void setPitcherRosterType(int pitcherRosterType) {
		this.pitcherRosterType = pitcherRosterType;
	}
	public String getPitcherRosterTypeName() {
		return pitcherRosterTypeName;
	}
	public void setPitcherRosterTypeName(String pitcherRosterTypeName) {
		this.pitcherRosterTypeName = pitcherRosterTypeName;
	}
	public int getAccmPitchingCnt() {
		return accmPitchingCnt;
	}
	public void setAccmPitchingCnt(int accmPitchingCnt) {
		this.accmPitchingCnt = accmPitchingCnt;
	}
	public String getDistAccmPitchingCnt() {
		return distAccmPitchingCnt;
	}
	public void setDistAccmPitchingCnt(String distAccmPitchingCnt) {
		this.distAccmPitchingCnt = distAccmPitchingCnt;
	}
	public int getBeforeBatterScoreGap() {
		return beforeBatterScoreGap;
	}
	public void setBeforeBatterScoreGap(int beforeBatterScoreGap) {
		this.beforeBatterScoreGap = beforeBatterScoreGap;
	}
	public String getDistBatterScoreGap() {
		return distBatterScoreGap;
	}
	public void setDistBatterScoreGap(String distBatterScoreGap) {
		this.distBatterScoreGap = distBatterScoreGap;
	}
	public String getSpectatorCnt() {
		return spectatorCnt;
	}
	public void setSpectatorCnt(String spectatorCnt) {
		this.spectatorCnt = spectatorCnt;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public int getGo() {
		return go;
	}
	public void setGo(int go) {
		this.go = go;
	}
	public int getFo() {
		return fo;
	}
	public void setFo(int fo) {
		this.fo = fo;
	}
	public int getTpa() {
		return tpa;
	}
	public void setTpa(int tpa) {
		this.tpa = tpa;
	}
	public int getAb() {
		return ab;
	}
	public void setAb(int ab) {
		this.ab = ab;
	}
	public int getHbp() {
		return hbp;
	}
	public void setHbp(int hbp) {
		this.hbp = hbp;
	}
	public int getSf() {
		return sf;
	}
	public void setSf(int sf) {
		this.sf = sf;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public int getDistBallCode() {
		return distBallCode;
	}
	public void setDistBallCode(int distBallCode) {
		this.distBallCode = distBallCode;
	}
	public int getBeforeStrikeCount() {
		return beforeStrikeCount;
	}
	public void setBeforeStrikeCount(int beforeStrikeCount) {
		this.beforeStrikeCount = beforeStrikeCount;
	}
	public int getBeforeBallCount() {
		return beforeBallCount;
	}
	public void setBeforeBallCount(int beforeBallCount) {
		this.beforeBallCount = beforeBallCount;
	}
	public String getDistPitcherScoreGap() {
		return distPitcherScoreGap;
	}
	public void setDistPitcherScoreGap(String distPitcherScoreGap) {
		this.distPitcherScoreGap = distPitcherScoreGap;
	}
	
}
