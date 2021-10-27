package com.hiball.api.domain;

import com.hiball.web.common.domain.CommonDomain;

public class GameVideoRecord extends CommonDomain{
    private static final long serialVersionUID = -1598674453985887629L;

    private String videoPath; 	
    private String videoPath2;	
    private String googleVideoPath;
    private String localPath;	
    private String downloadFilename; 
    private String gameInfoId;	
    private String gameRecordId;	
    private String initialPitchTime; 
    private String nvrInitialPitchTime;
    private String taggingGapTime;	
    private String pitchTime;	
    private String videoType;
    private int videoSeq;
    private String season;		
    private String gameDay;		
    private String inning;		
    private String inningTb;		
    private String batInningTurn;
    private String inningPitchSeq;
    private String homeShortName;
    private String homeEngName;
    private String awayShortName;
    private String awayEngName;
    private String batterId;		
    private String batterName;	
    private String batterFirstName;
    private String batterLastName;
    private String batterShortName;
    private String batterBacknum;
    private String batterForeignerYn;
    private String pitcherId;	
    private String pitcherName;
    private String pitcherFirstName;
    private String pitcherLastName;
    private String pitcherShortName;
    private String pitcherBacknum;
    private String pitcherForeignerYn;
    private String catcherId;	
    private String catcherName;	
    private String catcherFirstName;
    private String catcherLastName;
    private String catcherShortName;
    private String catcherBacknum;
    private String ballSpeed;	
    private String ballCode;		
    private String ballCodeName;
    private String ballTypeShortName;
    private String ballTypeFullName;
    private String batterTeamName;
    private String batterTeamShortName;
    private String batterTeamEngName;
    private String pitcherTeamName;
    private String pitcherTeamShortName;
    private String pitcherTeamEngName;
    private String beforeRunnerState;
    private String beforeOutCount;	
    private String beforeStrikeCount;
    private String beforeBallCount;	
    private String beforeScoreGap;
    private String outCount;	
    private String strikeCount;
    private String ballCount;	
    private String scoreGap;
    private String hitResultCode;	
    private String hitResultName;	
    private String hitResultFullName;
    private String hitResultShortName;
    private String checkBase;	
    private String pitchingForm;	
    private String b1b;		
    private String b2b;		
    private String b3b;		
    private String hr;		
    private String ghr;		
    private String hit;		
    private String bb;		
    private String ibb;		
    private String hbp;		
    private String go;		
    private String fo;		
    private String gdp;		
    private String so;		
    private String taggingYn;	
    private String battingHand;	
    private String pitchingHand;	
    private String hitTypeCode;	
    private String hitTypeName;
    private String battedBallRank;
    private int batPitchSeq;
    private int pureBatPitchSeq;
    private String lastPitchYn;
    
    public String getVideoPath() {
        return videoPath;
    }
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
    public String getVideoPath2() {
        return videoPath2;
    }
    public void setVideoPath2(String videoPath2) {
        this.videoPath2 = videoPath2;
    }
    public String getLocalPath() {
        return localPath;
    }
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
    public String getDownloadFilename() {
        return downloadFilename;
    }
    public void setDownloadFilename(String downloadFilename) {
        this.downloadFilename = downloadFilename;
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
    public String getInitialPitchTime() {
        return initialPitchTime;
    }
    public void setInitialPitchTime(String initialPitchTime) {
        this.initialPitchTime = initialPitchTime;
    }
    public String getNvrInitialPitchTime() {
        return nvrInitialPitchTime;
    }
    public void setNvrInitialPitchTime(String nvrInitialPitchTime) {
        this.nvrInitialPitchTime = nvrInitialPitchTime;
    }
    public String getTaggingGapTime() {
        return taggingGapTime;
    }
    public void setTaggingGapTime(String taggingGapTime) {
        this.taggingGapTime = taggingGapTime;
    }
    public String getPitchTime() {
        return pitchTime;
    }
    public void setPitchTime(String pitchTime) {
        this.pitchTime = pitchTime;
    }
    public String getVideoType() {
        return videoType;
    }
    public void setVideoType(String videoType) {
        this.videoType = videoType;
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
    public String getInning() {
        return inning;
    }
    public void setInning(String inning) {
        this.inning = inning;
    }
    public String getInningTb() {
        return inningTb;
    }
    public void setInningTb(String inningTb) {
        this.inningTb = inningTb;
    }
    public String getBatInningTurn() {
        return batInningTurn;
    }
    public void setBatInningTurn(String batInningTurn) {
        this.batInningTurn = batInningTurn;
    }
    public String getBatterId() {
        return batterId;
    }
    public void setBatterId(String batterId) {
        this.batterId = batterId;
    }
    public String getBatterName() {
        return batterName;
    }
    public void setBatterName(String batterName) {
        this.batterName = batterName;
    }
    public String getPitcherId() {
        return pitcherId;
    }
    public void setPitcherId(String pitcherId) {
        this.pitcherId = pitcherId;
    }
    public String getPitcherName() {
        return pitcherName;
    }
    public void setPitcherName(String pitcherName) {
        this.pitcherName = pitcherName;
    }
    public String getCatcherName() {
        return catcherName;
    }
    public void setCatcherName(String catcherName) {
        this.catcherName = catcherName;
    }
    public String getCatcherId() {
        return catcherId;
    }
    public void setCatcherId(String catcherId) {
        this.catcherId = catcherId;
    }
    public String getBallSpeed() {
        return ballSpeed;
    }
    public void setBallSpeed(String ballSpeed) {
        this.ballSpeed = ballSpeed;
    }
    public String getBallCode() {
        return ballCode;
    }
    public void setBallCode(String ballCode) {
        this.ballCode = ballCode;
    }
    public String getBallCodeName() {
        return ballCodeName;
    }
    public void setBallCodeName(String ballCodeName) {
        this.ballCodeName = ballCodeName;
    }
    public String getBatterTeamName() {
        return batterTeamName;
    }
    public void setBatterTeamName(String batterTeamName) {
        this.batterTeamName = batterTeamName;
    }
    public String getPitcherTeamName() {
        return pitcherTeamName;
    }
    public void setPitcherTeamName(String pitcherTeamName) {
        this.pitcherTeamName = pitcherTeamName;
    }
    public String getBeforeRunnerState() {
        return beforeRunnerState;
    }
    public void setBeforeRunnerState(String beforeRunnerState) {
        this.beforeRunnerState = beforeRunnerState;
    }
    public String getBeforeOutCount() {
        return beforeOutCount;
    }
    public void setBeforeOutCount(String beforeOutCount) {
        this.beforeOutCount = beforeOutCount;
    }
    public String getBeforeStrikeCount() {
        return beforeStrikeCount;
    }
    public void setBeforeStrikeCount(String beforeStrikeCount) {
        this.beforeStrikeCount = beforeStrikeCount;
    }
    public String getBeforeBallCount() {
        return beforeBallCount;
    }
    public void setBeforeBallCount(String beforeBallCount) {
        this.beforeBallCount = beforeBallCount;
    }
    public String getBeforeScoreGap() {
        return beforeScoreGap;
    }
    public void setBeforeScoreGap(String beforeScoreGap) {
        this.beforeScoreGap = beforeScoreGap;
    }
    public String getHitResultCode() {
        return hitResultCode;
    }
    public void setHitResultCode(String hitResultCode) {
        this.hitResultCode = hitResultCode;
    }
    public String getHitResultName() {
        return hitResultName;
    }
    public void setHitResultName(String hitResultName) {
        this.hitResultName = hitResultName;
    }
    public String getCheckBase() {
        return checkBase;
    }
    public void setCheckBase(String checkBase) {
        this.checkBase = checkBase;
    }
    public String getPitchingForm() {
        return pitchingForm;
    }
    public void setPitchingForm(String pitchingForm) {
        this.pitchingForm = pitchingForm;
    }
    public String getB1b() {
        return b1b;
    }
    public void setB1b(String b1b) {
        this.b1b = b1b;
    }
    public String getB2b() {
        return b2b;
    }
    public void setB2b(String b2b) {
        this.b2b = b2b;
    }
    public String getB3b() {
        return b3b;
    }
    public void setB3b(String b3b) {
        this.b3b = b3b;
    }
    public String getHr() {
        return hr;
    }
    public void setHr(String hr) {
        this.hr = hr;
    }
    public String getGhr() {
        return ghr;
    }
    public void setGhr(String ghr) {
        this.ghr = ghr;
    }
    public String getHit() {
        return hit;
    }
    public void setHit(String hit) {
        this.hit = hit;
    }
    public String getBb() {
        return bb;
    }
    public void setBb(String bb) {
        this.bb = bb;
    }
    public String getIbb() {
        return ibb;
    }
    public void setIbb(String ibb) {
        this.ibb = ibb;
    }
    public String getHbp() {
        return hbp;
    }
    public void setHbp(String hbp) {
        this.hbp = hbp;
    }
    public String getGo() {
        return go;
    }
    public void setGo(String go) {
        this.go = go;
    }
    public String getFo() {
        return fo;
    }
    public void setFo(String fo) {
        this.fo = fo;
    }
    public String getGdp() {
        return gdp;
    }
    public void setGdp(String gdp) {
        this.gdp = gdp;
    }
    public String getSo() {
        return so;
    }
    public void setSo(String so) {
        this.so = so;
    }
    public String getTaggingYn() {
        return taggingYn;
    }
    public void setTaggingYn(String taggingYn) {
        this.taggingYn = taggingYn;
    }
    public String getBattingHand() {
        return battingHand;
    }
    public void setBattingHand(String battingHand) {
        this.battingHand = battingHand;
    }
    public String getPitchingHand() {
        return pitchingHand;
    }
    public void setPitchingHand(String pitchingHand) {
        this.pitchingHand = pitchingHand;
    }
    public String getHitTypeCode() {
        return hitTypeCode;
    }
    public void setHitTypeCode(String hitTypeCode) {
        this.hitTypeCode = hitTypeCode;
    }
    public String getBattedBallRank() {
        return battedBallRank;
    }
    public void setBattedBallRank(String battedBallRank) {
        this.battedBallRank = battedBallRank;
    }
    public int getBatPitchSeq() {
        return batPitchSeq;
    }
    public void setBatPitchSeq(int batPitchSeq) {
        this.batPitchSeq = batPitchSeq;
    }
    public int getPureBatPitchSeq() {
        return pureBatPitchSeq;
    }
    public void setPureBatPitchSeq(int pureBatPitchSeq) {
        this.pureBatPitchSeq = pureBatPitchSeq;
    }	
    public int getVideoSeq() {
        return videoSeq;
    }
    public void setVideoSeq(int videoSeq) {
        this.videoSeq = videoSeq;
    }
    public String getBatterBacknum() {
        return batterBacknum;
    }
    public void setBatterBacknum(String batterBacknum) {
        this.batterBacknum = batterBacknum;
    }
    public String getPitcherBacknum() {
        return pitcherBacknum;
    }
    public void setPitcherBacknum(String pitcherBacknum) {
        this.pitcherBacknum = pitcherBacknum;
    }
    public String getCatcherBacknum() {
        return catcherBacknum;
    }
    public void setCatcherBacknum(String catcherBacknum) {
        this.catcherBacknum = catcherBacknum;
    }
    public String getHitTypeName() {
        return hitTypeName;
    }
    public void setHitTypeName(String hitTypeName) {
        this.hitTypeName = hitTypeName;
    }
    @Override
    public String toString() {
	return "GameVideoRecord [videoPath=" + videoPath + ", videoPath2=" + videoPath2 + ", localPath=" + localPath
		+ ", downloadFilename=" + downloadFilename + ", gameInfoId=" + gameInfoId + ", gameRecordId="
		+ gameRecordId + ", initialPitchTime=" + initialPitchTime + ", nvrInitialPitchTime="
		+ nvrInitialPitchTime + ", taggingGapTime=" + taggingGapTime + ", pitchTime=" + pitchTime
		+ ", videoType=" + videoType + ", videoSeq=" + videoSeq + ", season=" + season + ", gameDay=" + gameDay
		+ ", inning=" + inning + ", inningTb=" + inningTb + ", batInningTurn=" + batInningTurn + ", batterId="
		+ batterId + ", batterName=" + batterName + ", batterBacknum=" + batterBacknum + ", pitcherId="
		+ pitcherId + ", pitcherName=" + pitcherName + ", pitcherBacknum=" + pitcherBacknum + ", catcherId="
		+ catcherId + ", catcherName=" + catcherName + ", catcherBacknum=" + catcherBacknum + ", ballSpeed="
		+ ballSpeed + ", ballCode=" + ballCode + ", ballCodeName=" + ballCodeName + ", batterTeamId="
		+ super.getBatterTeamId() + ", batterTeamName=" + batterTeamName + ", pitcherTeamId=" + super.getPitcherTeamId()
		+ ", pitcherTeamName=" + pitcherTeamName + ", beforeRunnerState=" + beforeRunnerState
		+ ", beforeOutCount=" + beforeOutCount + ", beforeStrikeCount=" + beforeStrikeCount
		+ ", beforeBallCount=" + beforeBallCount + ", beforeScoreGap=" + beforeScoreGap + ", hitResultCode="
		+ hitResultCode + ", hitResultName=" + hitResultName + ", checkBase=" + checkBase + ", pitchingForm="
		+ pitchingForm + ", b1b=" + b1b + ", b2b=" + b2b + ", b3b=" + b3b + ", hr=" + hr + ", ghr=" + ghr
		+ ", hit=" + hit + ", bb=" + bb + ", ibb=" + ibb + ", hbp=" + hbp + ", go=" + go + ", fo=" + fo
		+ ", gdp=" + gdp + ", so=" + so + ", taggingYn=" + taggingYn + ", battingHand=" + battingHand
		+ ", pitchingHand=" + pitchingHand + ", hitTypeCode=" + hitTypeCode + ", battedBallRank="
		+ battedBallRank + ", batPitchSeq=" + batPitchSeq + ", pureBatPitchSeq=" + pureBatPitchSeq + "]";
    }
    public String getLastPitchYn() {
        return lastPitchYn;
    }
    public void setLastPitchYn(String lastPitchYn) {
        this.lastPitchYn = lastPitchYn;
    }
    public String getInningPitchSeq() {
        return inningPitchSeq;
    }
    public void setInningPitchSeq(String inningPitchSeq) {
        this.inningPitchSeq = inningPitchSeq;
    }
    public String getGoogleVideoPath() {
        return googleVideoPath;
    }
    public void setGoogleVideoPath(String googleVideoPath) {
        this.googleVideoPath = googleVideoPath;
    }
    public String getBatterFirstName() {
        return batterFirstName;
    }
    public void setBatterFirstName(String batterFirstName) {
        this.batterFirstName = batterFirstName;
    }
    public String getBatterLastName() {
        return batterLastName;
    }
    public void setBatterLastName(String batterLastName) {
        this.batterLastName = batterLastName;
    }
    public String getBatterShortName() {
        return batterShortName;
    }
    public void setBatterShortName(String batterShortName) {
        this.batterShortName = batterShortName;
    }
    public String getPitcherFirstName() {
        return pitcherFirstName;
    }
    public void setPitcherFirstName(String pitcherFirstName) {
        this.pitcherFirstName = pitcherFirstName;
    }
    public String getPitcherLastName() {
        return pitcherLastName;
    }
    public void setPitcherLastName(String pitcherLastName) {
        this.pitcherLastName = pitcherLastName;
    }
    public String getPitcherShortName() {
        return pitcherShortName;
    }
    public void setPitcherShortName(String pitcherShortName) {
        this.pitcherShortName = pitcherShortName;
    }
    public String getBallTypeShortName() {
        return ballTypeShortName;
    }
    public void setBallTypeShortName(String ballTypeShortName) {
        this.ballTypeShortName = ballTypeShortName;
    }
    public String getBallTypeFullName() {
        return ballTypeFullName;
    }
    public void setBallTypeFullName(String ballTypeFullName) {
        this.ballTypeFullName = ballTypeFullName;
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
    public String getCatcherFirstName() {
        return catcherFirstName;
    }
    public void setCatcherFirstName(String catcherFirstName) {
        this.catcherFirstName = catcherFirstName;
    }
    public String getCatcherLastName() {
        return catcherLastName;
    }
    public void setCatcherLastName(String catcherLastName) {
        this.catcherLastName = catcherLastName;
    }
    public String getCatcherShortName() {
        return catcherShortName;
    }
    public void setCatcherShortName(String catcherShortName) {
        this.catcherShortName = catcherShortName;
    }
    public String getHitResultFullName() {
        return hitResultFullName;
    }
    public void setHitResultFullName(String hitResultFullName) {
        this.hitResultFullName = hitResultFullName;
    }
    public String getHitResultShortName() {
        return hitResultShortName;
    }
    public void setHitResultShortName(String hitResultShortName) {
        this.hitResultShortName = hitResultShortName;
    }
    public String getBatterTeamShortName() {
        return batterTeamShortName;
    }
    public void setBatterTeamShortName(String batterTeamShortName) {
        this.batterTeamShortName = batterTeamShortName;
    }
    public String getBatterTeamEngName() {
        return batterTeamEngName;
    }
    public void setBatterTeamEngName(String batterTeamEngName) {
        this.batterTeamEngName = batterTeamEngName;
    }
    public String getPitcherTeamShortName() {
        return pitcherTeamShortName;
    }
    public void setPitcherTeamShortName(String pitcherTeamShortName) {
        this.pitcherTeamShortName = pitcherTeamShortName;
    }
    public String getPitcherTeamEngName() {
        return pitcherTeamEngName;
    }
    public void setPitcherTeamEngName(String pitcherTeamEngName) {
        this.pitcherTeamEngName = pitcherTeamEngName;
    }
    public String getBatterForeignerYn() {
        return batterForeignerYn;
    }
    public void setBatterForeignerYn(String batterForeignerYn) {
        this.batterForeignerYn = batterForeignerYn;
    }
    public String getPitcherForeignerYn() {
        return pitcherForeignerYn;
    }
    public void setPitcherForeignerYn(String pitcherForeignerYn) {
        this.pitcherForeignerYn = pitcherForeignerYn;
    }
    public String getOutCount() {
        return outCount;
    }
    public void setOutCount(String outCount) {
        this.outCount = outCount;
    }
    public String getStrikeCount() {
        return strikeCount;
    }
    public void setStrikeCount(String strikeCount) {
        this.strikeCount = strikeCount;
    }
    public String getBallCount() {
        return ballCount;
    }
    public void setBallCount(String ballCount) {
        this.ballCount = ballCount;
    }
    public String getScoreGap() {
        return scoreGap;
    }
    public void setScoreGap(String scoreGap) {
        this.scoreGap = scoreGap;
    }
   
}
