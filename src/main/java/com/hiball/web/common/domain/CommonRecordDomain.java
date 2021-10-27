package com.hiball.web.common.domain;

import java.math.BigDecimal;

public class CommonRecordDomain extends CommonDomain {
	private int b1b; 			
	private int b2b; 			
	private int b3b; 			
	private int hr;
	private int oB1b;
	private int oB2b;
	private int oB3b;
	private int oHr;
	private int k; 			
	private int so;
	private int bb; 			
	private int ibb; 			
	private int hbp;
	private int fo;
	private int go;
	private int strikeCnt; 	
	private int beforeStrikeCount;
	private int ballCnt; 	
	private int beforeBallCount;
	private int pitchingCount; 
	private int hittingCount;
	private int outCount;
	private int beforeOutCount;
	private int sf;
	private int sh;
	//희생번트
	private int sb;
	private int ab;
	private int oab;
	private int tpa;
	private int gdp;
	private int etcResultCnt;
	private int winningStreak;
	private int losingStreak;
	private int hit;			
	private int err; 			
	private int score; 		
	private int rbi;
	private int r;
	private int er;
	private int win;
	private int lose;
	private int draw;
	private int qs;
	private int qsPlus;
	private int shutOut;				//완봉
	private int completedGame;			//완투
	private int gameCnt;
	private int save;
	private int hold;
	private int total;
	private int totalHitting;
	private int chanceSteal;
	private int successSteal;
	private int failureSteal;
	private int decisionStrike;
	private int decisionBall;
	private int on1bCnt;
	private int on2bCnt;
	private int on3bCnt;
	private int from1bTo2b;
	private int from1bTo3b;
	private int from1bTohm;
	private int from1bToOut;
	private int from2bTo3b;
	private int from2bTohm;
	private int from2bToOut;
	private int from3bTohm;
	private int from3bToOut;
	private int stadiumPlayCnt;
	private String homeOrAway;
	private int gameCount;
	private int sSwingCnt;
	private int sContactCnt;
	private int sSituationCnt;
	private int bSwingCnt;
	private int bContactCnt;
	private int bSituationCnt;
	private int firstBallCnt;
	private int firstBallContactCnt;
	private int firstBallSCnt;
	private int firstBallBCnt;
	private int ballTypePitchingCnt;
	private int strikePitchingCnt;
	private int ballPitchingCnt;
	private int totalSbPitchingCnt;
	private int totalBallTypePitchingCnt;
	private int sJudgeCnt;
	private int bJudgeCnt;
	private int sbTotalCnt;
	private int pinPointStrikeCnt;
	private int pinPointBallCnt;
	private int coursePCnt;
	private int courseCcnt;
	private int course1BCnt;
	private int course2BCnt;
	private int course3BCnt;
	private int courseSSCnt;
	private int courseLFCnt;
	private int courseCFCnt;
	private int courseRFCnt;
	private int totalCourseCnt;
	private int successedStolen2Base;
	private int failedStolen2Base;
	private int successedStolen3Base;
	private int failedStolen3Base;
	private int scoringPositionB1b;
	private int scoringPositionB2b;
	private int scoringPositionB3b;
	private int scoringPositionHr;
	private int scoringPositionK;
	private int scoringPositionFo;
	private int scoringPositionGo;
	private int scoringPositionGdp;
	private int scoringPositionEtc;
	private int scoringPositionAb;
	private int po;
	private int ao;
	private int noBaseSJudgeCnt;
	private int noBaseBJudgeCnt;
	private int on1baseSJudgeCnt;
	private int on1baseBJudgeCnt;
	private int on2baseSJudgeCnt;
	private int on2baseBJudgeCnt;
	private int on3baseSJudgeCnt;
	private int on3baseBJudgeCnt;
	private int on12baseSJudgeCnt;
	private int on12baseBJudgeCnt;
	private int on13baseSJudgeCnt;
	private int on13baseBJudgeCnt;
	private int on23baseSJudgeCnt;
	private int on23baseBJudgeCnt;
	private int onFullbaseSJudgeCnt;
	private int onFullbaseBJudgeCnt;
	private int under5scoreSJudgeCnt;
	private int under5scoreBJudgeCnt;
	private int under3between4scoreSJudgeCnt;
	private int under3between4scoreBJudgeCnt;
	private int under1between2scoreSJudgeCnt;
	private int under1between2scoreBJudgeCnt;
	private int tieSJudgeCnt;
	private int tieBJudgeCnt;
	private int upper1between2scoreSJudgeCnt;
	private int upper1between2scoreBJudgeCnt;
	private int upper3between4scoreSJudgeCnt;
	private int upper3between4scoreBJudgeCnt;
	private int upper5scoreSJudgeCnt;
	private int upper5scoreBJudgeCnt;
	private int fastballSJudgeCnt;
	private int fastballBJudgeCnt;
	private int sliderSJudgeCnt;
	private int sliderBJudgeCnt;
	private int curveSJudgeCnt;
	private int curveBJudgeCnt;
	private int changeupSJudgeCnt;
	private int changeupBJudgeCnt;
	private int etcballSJudgeCnt;
	private int etcballBJudgeCnt;
	private int addStealBase;
	private int addStealBaseChance;
	private int playerChangeCnt;
	private int playerChangeTotalCnt;
	private int playerChangeSuccessCnt;
	private int playerChangeFailCnt;
	private int totalSumCnt;
	private int bunt;
	private int buntSuccessCnt;
	private double pitchingInning;
	private int accumBallCnt;
	private int standingCnt;
	private int contactCnt;
	private int swingCnt;
	private int fastBallCnt;
	private int slideCnt;
	private int curveCnt;
	private int changeUpCnt;
	private int etcBallCnt;
	private int zoneBallNum;
	private int totalZoneBallNum;
	private int wildPitchCnt;
	private String defenseInning;
	private int totalCatchingCnt;
	private int selectionPitcherAccumPitchingBallCnt;
	private int rescuePitcherAccumPitchingBallCnt;
	private int selectionPitcherCnt;
	private int rescuePitcherCnt;
	private BigDecimal beforeWe = new BigDecimal(0);
	private BigDecimal afterWe = new BigDecimal(0);
	private int pinchHitSucc;
	private int pinchHitFail;
	private int reliefFail;
	private int reliefSucc;
	private int buntsCnt;
	private int totalBuntsCnt;
	private int strongAttackCnt;
	private int totalStrongAttackCnt;
	private BigDecimal leagueAvgWoba = new BigDecimal(0);
	private BigDecimal leagueAvgObp = new BigDecimal(0);
	private Integer leagueTotalR = 0;
	private Integer leagueTotalTpa = 0;
	private BigDecimal leagueWsb = new BigDecimal(0);
	private String keyValue;
	private String dataRetrieveYn;
	private int weekUnit;
	private int startWeekUnit;
	private int endWeekUnit;
	private int zPitchingCnt;
	private int oPitchingCnt;
	private int zSwingCnt;
	private int oSwingCnt;
	private int zContactCnt;
	private int oContactCnt;	
	private int pitchingCnt;
	private int aoCnt;
	private int poCnt;
	private int gdpChanceCnt;
	private int addedRunChanceCnt;
	private int addedRunCnt;
	private int startPitcherCnt;
	
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
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
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
	public int getHbp() {
		return hbp;
	}
	public void setHbp(int hbp) {
		this.hbp = hbp;
	}
	public int getFo() {
		return fo;
	}
	public void setFo(int fo) {
		this.fo = fo;
	}
	public int getGo() {
		return go;
	}
	public void setGo(int go) {
		this.go = go;
	}
	public int getStrikeCnt() {
		return strikeCnt;
	}
	public void setStrikeCnt(int strikeCnt) {
		this.strikeCnt = strikeCnt;
	}
	public int getBallCnt() {
		return ballCnt;
	}
	public void setBallCnt(int ballCnt) {
		this.ballCnt = ballCnt;
	}
	public int getPitchingCount() {
		return pitchingCount;
	}
	public void setPitchingCount(int pitchingCnt) {
		this.pitchingCount = pitchingCnt;
	}
	public int getOutCount() {
		return outCount;
	}
	public void setOutCount(int outCount) {
		this.outCount = outCount;
	}
	public int getRbi() {
		return rbi;
	}
	public void setRbi(int rbi) {
		this.rbi = rbi;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getEr() {
		return er;
	}
	public void setEr(int er) {
		this.er = er;
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
	public int getAb() {
		return ab;
	}
	public void setAb(int ab) {
		this.ab = ab;
	}
	public int getTpa() {
		return tpa;
	}
	public void setTpa(int tpa) {
		this.tpa = tpa;
	}
	public int getGdp() {
		return gdp;
	}
	public void setGdp(int gdp) {
		this.gdp = gdp;
	}
	public int getEtcResultCnt() {
		return etcResultCnt;
	}
	public void setEtcResultCnt(int etcResultCnt) {
		this.etcResultCnt = etcResultCnt;
	}
	public int getWinningStreak() {
		return winningStreak;
	}
	public void setWinningStreak(int winningStreak) {
		this.winningStreak = winningStreak;
	}
	public int getLosingStreak() {
		return losingStreak;
	}
	public void setLosingStreak(int losingStreak) {
		this.losingStreak = losingStreak;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getErr() {
		return err;
	}
	public void setErr(int err) {
		this.err = err;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getGameCnt() {
		return gameCnt;
	}
	public void setGameCnt(int gameCnt) {
		this.gameCnt = gameCnt;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSave() {
		return save;
	}
	public void setSave(int save) {
		this.save = save;
	}
	public int getHold() {
		return hold;
	}
	public void setHold(int hold) {
		this.hold = hold;
	}
	public int getChanceSteal() {
		return chanceSteal;
	}
	public void setChanceSteal(int chanceSteal) {
		this.chanceSteal = chanceSteal;
	}
	public int getSuccessSteal() {
		return successSteal;
	}
	public void setSuccessSteal(int successSteal) {
		this.successSteal = successSteal;
	}
	public int getFailureSteal() {
		return failureSteal;
	}
	public void setFailureSteal(int failureSteal) {
		this.failureSteal = failureSteal;
	}
	public int getDecisionBall() {
		return decisionBall;
	}
	public void setDecisionBall(int decisionBall) {
		this.decisionBall = decisionBall;
	}
	public int getDecisionStrike() {
		return decisionStrike;
	}
	public void setDecisionStrike(int decisionStrike) {
		this.decisionStrike = decisionStrike;
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
	public int getBeforeOutCount() {
		return beforeOutCount;
	}
	public void setBeforeOutCount(int beforeOutCount) {
		this.beforeOutCount = beforeOutCount;
	}
	public int getTotalHitting() {
		return totalHitting;
	}
	public void setTotalHitting(int totalHitting) {
		this.totalHitting = totalHitting;
	}
	public int getHittingCount() {
		return hittingCount;
	}
	public void setHittingCount(int hittingCount) {
		this.hittingCount = hittingCount;
	}
	public int getOn1bCnt() {
		return on1bCnt;
	}
	public void setOn1bCnt(int on1bCnt) {
		this.on1bCnt = on1bCnt;
	}
	public int getOn2bCnt() {
		return on2bCnt;
	}
	public void setOn2bCnt(int on2bCnt) {
		this.on2bCnt = on2bCnt;
	}
	public int getOn3bCnt() {
		return on3bCnt;
	}
	public void setOn3bCnt(int on3bCnt) {
		this.on3bCnt = on3bCnt;
	}
	public int getFrom1bTo2b() {
		return from1bTo2b;
	}
	public void setFrom1bTo2b(int from1bTo2b) {
		this.from1bTo2b = from1bTo2b;
	}
	public int getFrom1bTo3b() {
		return from1bTo3b;
	}
	public void setFrom1bTo3b(int from1bTo3b) {
		this.from1bTo3b = from1bTo3b;
	}
	public int getFrom1bTohm() {
		return from1bTohm;
	}
	public void setFrom1bTohm(int from1bTohm) {
		this.from1bTohm = from1bTohm;
	}
	public int getFrom2bTo3b() {
		return from2bTo3b;
	}
	public void setFrom2bTo3b(int from2bTo3b) {
		this.from2bTo3b = from2bTo3b;
	}
	public int getFrom2bTohm() {
		return from2bTohm;
	}
	public void setFrom2bTohm(int from2bTohm) {
		this.from2bTohm = from2bTohm;
	}
	public int getFrom3bTohm() {
		return from3bTohm;
	}
	public void setFrom3bTohm(int from3bTohm) {
		this.from3bTohm = from3bTohm;
	}
	public String getHomeOrAway() {
		return homeOrAway;
	}
	public void setHomeOrAway(String homeOrAway) {
		this.homeOrAway = homeOrAway;
	}
	public int getStadiumPlayCnt() {
		return stadiumPlayCnt;
	}
	public void setStadiumPlayCnt(int stadiumPlayCnt) {
		this.stadiumPlayCnt = stadiumPlayCnt;
	}
	public int getGameCount() {
		return gameCount;
	}
	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}
	public int getsSwingCnt() {
		return sSwingCnt;
	}
	public void setsSwingCnt(int sSwingCnt) {
		this.sSwingCnt = sSwingCnt;
	}
	public int getsContactCnt() {
		return sContactCnt;
	}
	public void setsContactCnt(int sContactCnt) {
		this.sContactCnt = sContactCnt;
	}
	public int getsSituationCnt() {
		return sSituationCnt;
	}
	public void setsSituationCnt(int sSituationCnt) {
		this.sSituationCnt = sSituationCnt;
	}
	public int getbSwingCnt() {
		return bSwingCnt;
	}
	public void setbSwingCnt(int bSwingCnt) {
		this.bSwingCnt = bSwingCnt;
	}
	public int getbContactCnt() {
		return bContactCnt;
	}
	public void setbContactCnt(int bContactCnt) {
		this.bContactCnt = bContactCnt;
	}
	public int getbSituationCnt() {
		return bSituationCnt;
	}
	public void setbSituationCnt(int bSituationCnt) {
		this.bSituationCnt = bSituationCnt;
	}
	public int getFirstBallCnt() {
		return firstBallCnt;
	}
	public void setFirstBallCnt(int firstBallCnt) {
		this.firstBallCnt = firstBallCnt;
	}
	public int getFirstBallContactCnt() {
		return firstBallContactCnt;
	}
	public void setFirstBallContactCnt(int firstBallContactCnt) {
		this.firstBallContactCnt = firstBallContactCnt;
	}
	public int getFirstBallSCnt() {
		return firstBallSCnt;
	}
	public void setFirstBallSCnt(int firstBallSCnt) {
		this.firstBallSCnt = firstBallSCnt;
	}
	public int getFirstBallBCnt() {
		return firstBallBCnt;
	}
	public void setFirstBallBCnt(int firstBallBCnt) {
		this.firstBallBCnt = firstBallBCnt;
	}
	public int getBallTypePitchingCnt() {
		return ballTypePitchingCnt;
	}
	public void setBallTypePitchingCnt(int ballTypePitchingCnt) {
		this.ballTypePitchingCnt = ballTypePitchingCnt;
	}
	public int getStrikePitchingCnt() {
		return strikePitchingCnt;
	}
	public void setStrikePitchingCnt(int strikePitchingCnt) {
		this.strikePitchingCnt = strikePitchingCnt;
	}
	public int getBallPitchingCnt() {
		return ballPitchingCnt;
	}
	public void setBallPitchingCnt(int ballPitchingCnt) {
		this.ballPitchingCnt = ballPitchingCnt;
	}
	public int getTotalSbPitchingCnt() {
		return totalSbPitchingCnt;
	}
	public void setTotalSbPitchingCnt(int totalSbPitchingCnt) {
		this.totalSbPitchingCnt = totalSbPitchingCnt;
	}
	public int getTotalBallTypePitchingCnt() {
		return totalBallTypePitchingCnt;
	}
	public void setTotalBallTypePitchingCnt(int totalBallTypePitchingCnt) {
		this.totalBallTypePitchingCnt = totalBallTypePitchingCnt;
	}
	public int getsJudgeCnt() {
		return sJudgeCnt;
	}
	public void setsJudgeCnt(int sJudgeCnt) {
		this.sJudgeCnt = sJudgeCnt;
	}
	public int getbJudgeCnt() {
		return bJudgeCnt;
	}
	public void setbJudgeCnt(int bJudgeCnt) {
		this.bJudgeCnt = bJudgeCnt;
	}
	public int getSbTotalCnt() {
		return sbTotalCnt;
	}
	public void setSbTotalCnt(int sbTotalCnt) {
		this.sbTotalCnt = sbTotalCnt;
	}
	public int getCoursePCnt() {
		return coursePCnt;
	}
	public void setCoursePCnt(int coursePCnt) {
		this.coursePCnt = coursePCnt;
	}
	public int getCourseCcnt() {
		return courseCcnt;
	}
	public void setCourseCcnt(int courseCcnt) {
		this.courseCcnt = courseCcnt;
	}
	public int getCourse1BCnt() {
		return course1BCnt;
	}
	public void setCourse1BCnt(int course1bCnt) {
		course1BCnt = course1bCnt;
	}
	public int getCourse2BCnt() {
		return course2BCnt;
	}
	public void setCourse2BCnt(int course2bCnt) {
		course2BCnt = course2bCnt;
	}
	public int getCourse3BCnt() {
		return course3BCnt;
	}
	public void setCourse3BCnt(int course3bCnt) {
		course3BCnt = course3bCnt;
	}
	public int getCourseSSCnt() {
		return courseSSCnt;
	}
	public void setCourseSSCnt(int courseSSCnt) {
		this.courseSSCnt = courseSSCnt;
	}
	public int getCourseLFCnt() {
		return courseLFCnt;
	}
	public void setCourseLFCnt(int courseLFCnt) {
		this.courseLFCnt = courseLFCnt;
	}
	public int getCourseCFCnt() {
		return courseCFCnt;
	}
	public void setCourseCFCnt(int courseCFCnt) {
		this.courseCFCnt = courseCFCnt;
	}
	public int getCourseRFCnt() {
		return courseRFCnt;
	}
	public void setCourseRFCnt(int courseRFCnt) {
		this.courseRFCnt = courseRFCnt;
	}
	public int getTotalCourseCnt() {
		return totalCourseCnt;
	}
	public void setTotalCourseCnt(int totalCourseCnt) {
		this.totalCourseCnt = totalCourseCnt;
	}
	public int getSuccessedStolen2Base() {
		return successedStolen2Base;
	}
	public void setSuccessedStolen2Base(int successedStolen2Base) {
		this.successedStolen2Base = successedStolen2Base;
	}
	public int getFailedStolen2Base() {
		return failedStolen2Base;
	}
	public void setFailedStolen2Base(int failedStolen2Base) {
		this.failedStolen2Base = failedStolen2Base;
	}
	public int getSuccessedStolen3Base() {
		return successedStolen3Base;
	}
	public void setSuccessedStolen3Base(int successedStolen3Base) {
		this.successedStolen3Base = successedStolen3Base;
	}
	public int getFailedStolen3Base() {
		return failedStolen3Base;
	}
	public void setFailedStolen3Base(int failedStolen3Base) {
		this.failedStolen3Base = failedStolen3Base;
	}
	public int getScoringPositionB1b() {
		return scoringPositionB1b;
	}
	public void setScoringPositionB1b(int scoringPositionB1b) {
		this.scoringPositionB1b = scoringPositionB1b;
	}
	public int getScoringPositionB2b() {
		return scoringPositionB2b;
	}
	public void setScoringPositionB2b(int scoringPositionB2b) {
		this.scoringPositionB2b = scoringPositionB2b;
	}
	public int getScoringPositionB3b() {
		return scoringPositionB3b;
	}
	public void setScoringPositionB3b(int scoringPositionB3b) {
		this.scoringPositionB3b = scoringPositionB3b;
	}
	public int getScoringPositionHr() {
		return scoringPositionHr;
	}
	public void setScoringPositionHr(int scoringPositionHr) {
		this.scoringPositionHr = scoringPositionHr;
	}
	public int getScoringPositionK() {
		return scoringPositionK;
	}
	public void setScoringPositionK(int scoringPositionK) {
		this.scoringPositionK = scoringPositionK;
	}
	public int getScoringPositionFo() {
		return scoringPositionFo;
	}
	public void setScoringPositionFo(int scoringPositionFo) {
		this.scoringPositionFo = scoringPositionFo;
	}
	public int getScoringPositionGo() {
		return scoringPositionGo;
	}
	public void setScoringPositionGo(int scoringPositionGo) {
		this.scoringPositionGo = scoringPositionGo;
	}
	public int getScoringPositionGdp() {
		return scoringPositionGdp;
	}
	public void setScoringPositionGdp(int scoringPositionGdp) {
		this.scoringPositionGdp = scoringPositionGdp;
	}
	public int getScoringPositionEtc() {
		return scoringPositionEtc;
	}
	public void setScoringPositionEtc(int scoringPositionEtc) {
		this.scoringPositionEtc = scoringPositionEtc;
	}
	public int getScoringPositionAb() {
		return scoringPositionAb;
	}
	public void setScoringPositionAb(int scoringPositionAb) {
		this.scoringPositionAb = scoringPositionAb;
	}
	public int getFrom1bToOut() {
		return from1bToOut;
	}
	public void setFrom1bToOut(int from1bToOut) {
		this.from1bToOut = from1bToOut;
	}
	public int getFrom2bToOut() {
		return from2bToOut;
	}
	public void setFrom2bToOut(int from2bToOut) {
		this.from2bToOut = from2bToOut;
	}
	public int getFrom3bToOut() {
		return from3bToOut;
	}
	public void setFrom3bToOut(int from3bToOut) {
		this.from3bToOut = from3bToOut;
	}
	public int getPo() {
		return po;
	}
	public void setPo(int po) {
		this.po = po;
	}
	public int getAo() {
		return ao;
	}
	public void setAo(int ao) {
		this.ao = ao;
	}
	public int getoB1b() {
		return oB1b;
	}
	public void setoB1b(int oB1b) {
		this.oB1b = oB1b;
	}
	public int getoB2b() {
		return oB2b;
	}
	public void setoB2b(int oB2b) {
		this.oB2b = oB2b;
	}
	public int getoB3b() {
		return oB3b;
	}
	public void setoB3b(int oB3b) {
		this.oB3b = oB3b;
	}
	public int getoHr() {
		return oHr;
	}
	public void setoHr(int oHr) {
		this.oHr = oHr;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public int getOab() {
		return oab;
	}
	public void setOab(int oab) {
		this.oab = oab;
	}
	public int getNoBaseSJudgeCnt() {
		return noBaseSJudgeCnt;
	}
	public void setNoBaseSJudgeCnt(int noBaseSJudgeCnt) {
		this.noBaseSJudgeCnt = noBaseSJudgeCnt;
	}
	public int getNoBaseBJudgeCnt() {
		return noBaseBJudgeCnt;
	}
	public void setNoBaseBJudgeCnt(int noBaseBJudgeCnt) {
		this.noBaseBJudgeCnt = noBaseBJudgeCnt;
	}
	public int getOn1baseSJudgeCnt() {
		return on1baseSJudgeCnt;
	}
	public void setOn1baseSJudgeCnt(int on1baseSJudgeCnt) {
		this.on1baseSJudgeCnt = on1baseSJudgeCnt;
	}
	public int getOn1baseBJudgeCnt() {
		return on1baseBJudgeCnt;
	}
	public void setOn1baseBJudgeCnt(int on1baseBJudgeCnt) {
		this.on1baseBJudgeCnt = on1baseBJudgeCnt;
	}
	public int getOn2baseSJudgeCnt() {
		return on2baseSJudgeCnt;
	}
	public void setOn2baseSJudgeCnt(int on2baseSJudgeCnt) {
		this.on2baseSJudgeCnt = on2baseSJudgeCnt;
	}
	public int getOn2baseBJudgeCnt() {
		return on2baseBJudgeCnt;
	}
	public void setOn2baseBJudgeCnt(int on2baseBJudgeCnt) {
		this.on2baseBJudgeCnt = on2baseBJudgeCnt;
	}
	public int getOn3baseSJudgeCnt() {
		return on3baseSJudgeCnt;
	}
	public void setOn3baseSJudgeCnt(int on3baseSJudgeCnt) {
		this.on3baseSJudgeCnt = on3baseSJudgeCnt;
	}
	public int getOn3baseBJudgeCnt() {
		return on3baseBJudgeCnt;
	}
	public void setOn3baseBJudgeCnt(int on3baseBJudgeCnt) {
		this.on3baseBJudgeCnt = on3baseBJudgeCnt;
	}
	public int getOn12baseSJudgeCnt() {
		return on12baseSJudgeCnt;
	}
	public void setOn12baseSJudgeCnt(int on12baseSJudgeCnt) {
		this.on12baseSJudgeCnt = on12baseSJudgeCnt;
	}
	public int getOn12baseBJudgeCnt() {
		return on12baseBJudgeCnt;
	}
	public void setOn12baseBJudgeCnt(int on12baseBJudgeCnt) {
		this.on12baseBJudgeCnt = on12baseBJudgeCnt;
	}
	public int getOn13baseSJudgeCnt() {
		return on13baseSJudgeCnt;
	}
	public void setOn13baseSJudgeCnt(int on13baseSJudgeCnt) {
		this.on13baseSJudgeCnt = on13baseSJudgeCnt;
	}
	public int getOn13baseBJudgeCnt() {
		return on13baseBJudgeCnt;
	}
	public void setOn13baseBJudgeCnt(int on13baseBJudgeCnt) {
		this.on13baseBJudgeCnt = on13baseBJudgeCnt;
	}
	public int getOn23baseSJudgeCnt() {
		return on23baseSJudgeCnt;
	}
	public void setOn23baseSJudgeCnt(int on23baseSJudgeCnt) {
		this.on23baseSJudgeCnt = on23baseSJudgeCnt;
	}
	public int getOn23baseBJudgeCnt() {
		return on23baseBJudgeCnt;
	}
	public void setOn23baseBJudgeCnt(int on23baseBJudgeCnt) {
		this.on23baseBJudgeCnt = on23baseBJudgeCnt;
	}
	public int getOnFullbaseSJudgeCnt() {
		return onFullbaseSJudgeCnt;
	}
	public void setOnFullbaseSJudgeCnt(int onFullbaseSJudgeCnt) {
		this.onFullbaseSJudgeCnt = onFullbaseSJudgeCnt;
	}
	public int getOnFullbaseBJudgeCnt() {
		return onFullbaseBJudgeCnt;
	}
	public void setOnFullbaseBJudgeCnt(int onFullbaseBJudgeCnt) {
		this.onFullbaseBJudgeCnt = onFullbaseBJudgeCnt;
	}
	public int getUnder5scoreSJudgeCnt() {
		return under5scoreSJudgeCnt;
	}
	public void setUnder5scoreSJudgeCnt(int under5scoreSJudgeCnt) {
		this.under5scoreSJudgeCnt = under5scoreSJudgeCnt;
	}
	public int getUnder5scoreBJudgeCnt() {
		return under5scoreBJudgeCnt;
	}
	public void setUnder5scoreBJudgeCnt(int under5scoreBJudgeCnt) {
		this.under5scoreBJudgeCnt = under5scoreBJudgeCnt;
	}
	public int getUnder3between4scoreSJudgeCnt() {
		return under3between4scoreSJudgeCnt;
	}
	public void setUnder3between4scoreSJudgeCnt(int under3between4scoreSJudgeCnt) {
		this.under3between4scoreSJudgeCnt = under3between4scoreSJudgeCnt;
	}
	public int getUnder3between4scoreBJudgeCnt() {
		return under3between4scoreBJudgeCnt;
	}
	public void setUnder3between4scoreBJudgeCnt(int under3between4scoreBJudgeCnt) {
		this.under3between4scoreBJudgeCnt = under3between4scoreBJudgeCnt;
	}
	public int getUnder1between2scoreSJudgeCnt() {
		return under1between2scoreSJudgeCnt;
	}
	public void setUnder1between2scoreSJudgeCnt(int under1between2scoreSJudgeCnt) {
		this.under1between2scoreSJudgeCnt = under1between2scoreSJudgeCnt;
	}
	public int getUnder1between2scoreBJudgeCnt() {
		return under1between2scoreBJudgeCnt;
	}
	public void setUnder1between2scoreBJudgeCnt(int under1between2scoreBJudgeCnt) {
		this.under1between2scoreBJudgeCnt = under1between2scoreBJudgeCnt;
	}
	public int getTieSJudgeCnt() {
		return tieSJudgeCnt;
	}
	public void setTieSJudgeCnt(int tieSJudgeCnt) {
		this.tieSJudgeCnt = tieSJudgeCnt;
	}
	public int getTieBJudgeCnt() {
		return tieBJudgeCnt;
	}
	public void setTieBJudgeCnt(int tieBJudgeCnt) {
		this.tieBJudgeCnt = tieBJudgeCnt;
	}
	public int getUpper1between2scoreSJudgeCnt() {
		return upper1between2scoreSJudgeCnt;
	}
	public void setUpper1between2scoreSJudgeCnt(int upper1between2scoreSJudgeCnt) {
		this.upper1between2scoreSJudgeCnt = upper1between2scoreSJudgeCnt;
	}
	public int getUpper1between2scoreBJudgeCnt() {
		return upper1between2scoreBJudgeCnt;
	}
	public void setUpper1between2scoreBJudgeCnt(int upper1between2scoreBJudgeCnt) {
		this.upper1between2scoreBJudgeCnt = upper1between2scoreBJudgeCnt;
	}
	public int getUpper3between4scoreSJudgeCnt() {
		return upper3between4scoreSJudgeCnt;
	}
	public void setUpper3between4scoreSJudgeCnt(int upper3between4scoreSJudgeCnt) {
		this.upper3between4scoreSJudgeCnt = upper3between4scoreSJudgeCnt;
	}
	public int getUpper3between4scoreBJudgeCnt() {
		return upper3between4scoreBJudgeCnt;
	}
	public void setUpper3between4scoreBJudgeCnt(int upper3between4scoreBJudgeCnt) {
		this.upper3between4scoreBJudgeCnt = upper3between4scoreBJudgeCnt;
	}
	public int getUpper5scoreSJudgeCnt() {
		return upper5scoreSJudgeCnt;
	}
	public void setUpper5scoreSJudgeCnt(int upper5scoreSJudgeCnt) {
		this.upper5scoreSJudgeCnt = upper5scoreSJudgeCnt;
	}
	public int getUpper5scoreBJudgeCnt() {
		return upper5scoreBJudgeCnt;
	}
	public void setUpper5scoreBJudgeCnt(int upper5scoreBJudgeCnt) {
		this.upper5scoreBJudgeCnt = upper5scoreBJudgeCnt;
	}
	public int getPinPointStrikeCnt() {
		return pinPointStrikeCnt;
	}
	public void setPinPointStrikeCnt(int pinPointStrikeCnt) {
		this.pinPointStrikeCnt = pinPointStrikeCnt;
	}
	public int getPinPointBallCnt() {
		return pinPointBallCnt;
	}
	public void setPinPointBallCnt(int pinPointBallCnt) {
		this.pinPointBallCnt = pinPointBallCnt;
	}
	public int getFastballSJudgeCnt() {
		return fastballSJudgeCnt;
	}
	public void setFastballSJudgeCnt(int fastballSJudgeCnt) {
		this.fastballSJudgeCnt = fastballSJudgeCnt;
	}
	public int getFastballBJudgeCnt() {
		return fastballBJudgeCnt;
	}
	public void setFastballBJudgeCnt(int fastballBJudgeCnt) {
		this.fastballBJudgeCnt = fastballBJudgeCnt;
	}
	public int getSliderSJudgeCnt() {
		return sliderSJudgeCnt;
	}
	public void setSliderSJudgeCnt(int sliderSJudgeCnt) {
		this.sliderSJudgeCnt = sliderSJudgeCnt;
	}
	public int getSliderBJudgeCnt() {
		return sliderBJudgeCnt;
	}
	public void setSliderBJudgeCnt(int sliderBJudgeCnt) {
		this.sliderBJudgeCnt = sliderBJudgeCnt;
	}
	public int getCurveSJudgeCnt() {
		return curveSJudgeCnt;
	}
	public void setCurveSJudgeCnt(int curveSJudgeCnt) {
		this.curveSJudgeCnt = curveSJudgeCnt;
	}
	public int getCurveBJudgeCnt() {
		return curveBJudgeCnt;
	}
	public void setCurveBJudgeCnt(int curveBJudgeCnt) {
		this.curveBJudgeCnt = curveBJudgeCnt;
	}
	public int getChangeupSJudgeCnt() {
		return changeupSJudgeCnt;
	}
	public void setChangeupSJudgeCnt(int changeupSJudgeCnt) {
		this.changeupSJudgeCnt = changeupSJudgeCnt;
	}
	public int getChangeupBJudgeCnt() {
		return changeupBJudgeCnt;
	}
	public void setChangeupBJudgeCnt(int changeupBJudgeCnt) {
		this.changeupBJudgeCnt = changeupBJudgeCnt;
	}
	public int getEtcballSJudgeCnt() {
		return etcballSJudgeCnt;
	}
	public void setEtcballSJudgeCnt(int etcballSJudgeCnt) {
		this.etcballSJudgeCnt = etcballSJudgeCnt;
	}
	public int getEtcballBJudgeCnt() {
		return etcballBJudgeCnt;
	}
	public void setEtcballBJudgeCnt(int etcballBJudgeCnt) {
		this.etcballBJudgeCnt = etcballBJudgeCnt;
	}
	public int getAddStealBase() {
		return addStealBase;
	}
	public void setAddStealBase(int addStealBase) {
		this.addStealBase = addStealBase;
	}
	public int getAddStealBaseChance() {
		return addStealBaseChance;
	}
	public void setAddStealBaseChance(int addStealBaseChance) {
		this.addStealBaseChance = addStealBaseChance;
	}
	public int getPlayerChangeCnt() {
		return playerChangeCnt;
	}
	public void setPlayerChangeCnt(int playerChangeCnt) {
		this.playerChangeCnt = playerChangeCnt;
	}
	public int getPlayerChangeSuccessCnt() {
		return playerChangeSuccessCnt;
	}
	public void setPlayerChangeSuccessCnt(int playerChangeSuccessCnt) {
		this.playerChangeSuccessCnt = playerChangeSuccessCnt;
	}
	public int getPlayerChangeFailCnt() {
		return playerChangeFailCnt;
	}
	public void setPlayerChangeFailCnt(int playerChangeFailCnt) {
		this.playerChangeFailCnt = playerChangeFailCnt;
	}
	public int getBunt() {
		return bunt;
	}
	public void setBunt(int bunt) {
		this.bunt = bunt;
	}
	public int getBuntSuccessCnt() {
		return buntSuccessCnt;
	}
	public void setBuntSuccessCnt(int buntSuccessCnt) {
		this.buntSuccessCnt = buntSuccessCnt;
	}
	public int getTotalSumCnt() {
		return totalSumCnt;
	}
	public void setTotalSumCnt(int totalSumCnt) {
		this.totalSumCnt = totalSumCnt;
	}
	public int getPlayerChangeTotalCnt() {
		return playerChangeTotalCnt;
	}
	public void setPlayerChangeTotalCnt(int playerChangeTotalCnt) {
		this.playerChangeTotalCnt = playerChangeTotalCnt;
	}
	public double getPitchingInning() {
		return pitchingInning;
	}
	public void setPitchingInning(double pitchingInning) {
		this.pitchingInning = pitchingInning;
	}
	public int getAccumBallCnt() {
		return accumBallCnt;
	}
	public void setAccumBallCnt(int accumBallCnt) {
		this.accumBallCnt = accumBallCnt;
	}
	public int getStandingCnt() {
		return standingCnt;
	}
	public void setStandingCnt(int standingCnt) {
		this.standingCnt = standingCnt;
	}
	public int getContactCnt() {
		return contactCnt;
	}
	public void setContactCnt(int contactCnt) {
		this.contactCnt = contactCnt;
	}
	public int getSwingCnt() {
		return swingCnt;
	}
	public void setSwingCnt(int swingCnt) {
		this.swingCnt = swingCnt;
	}
	public int getFastBallCnt() {
		return fastBallCnt;
	}
	public void setFastBallCnt(int fastBallCnt) {
		this.fastBallCnt = fastBallCnt;
	}
	public int getWildPitchCnt() {
		return wildPitchCnt;
	}
	public void setWildPitchCnt(int wildPitchCnt) {
		this.wildPitchCnt = wildPitchCnt;
	}
	public String getDefenseInning() {
		return defenseInning;
	}
	public void setDefenseInning(String defenseInning) {
		this.defenseInning = defenseInning;
	}
	public int getTotalCatchingCnt() {
		return totalCatchingCnt;
	}
	public void setTotalCatchingCnt(int totalCatchingCnt) {
		this.totalCatchingCnt = totalCatchingCnt;
	}
	public int getSelectionPitcherAccumPitchingBallCnt() {
		return selectionPitcherAccumPitchingBallCnt;
	}
	public void setSelectionPitcherAccumPitchingBallCnt(int selectionPitcherAccumPitchingBallCnt) {
		this.selectionPitcherAccumPitchingBallCnt = selectionPitcherAccumPitchingBallCnt;
	}
	public int getRescuePitcherAccumPitchingBallCnt() {
		return rescuePitcherAccumPitchingBallCnt;
	}
	public void setRescuePitcherAccumPitchingBallCnt(int rescuePitcherAccumPitchingBallCnt) {
		this.rescuePitcherAccumPitchingBallCnt = rescuePitcherAccumPitchingBallCnt;
	}
	public int getSelectionPitcherCnt() {
		return selectionPitcherCnt;
	}
	public void setSelectionPitcherCnt(int selectionPitcherCnt) {
		this.selectionPitcherCnt = selectionPitcherCnt;
	}
	public int getRescuePitcherCnt() {
		return rescuePitcherCnt;
	}
	public void setRescuePitcherCnt(int rescuePitcherCnt) {
		this.rescuePitcherCnt = rescuePitcherCnt;
	}
	public int getSlideCnt() {
		return slideCnt;
	}
	public void setSlideCnt(int slideCnt) {
		this.slideCnt = slideCnt;
	}
	public int getCurveCnt() {
		return curveCnt;
	}
	public void setCurveCnt(int curveCnt) {
		this.curveCnt = curveCnt;
	}
	public int getChangeUpCnt() {
		return changeUpCnt;
	}
	public void setChangeUpCnt(int changeUpCnt) {
		this.changeUpCnt = changeUpCnt;
	}
	public int getEtcBallCnt() {
		return etcBallCnt;
	}
	public void setEtcBallCnt(int etcBallCnt) {
		this.etcBallCnt = etcBallCnt;
	}
	public int getZoneBallNum() {
		return zoneBallNum;
	}
	public void setZoneBallNum(int zoneBallNum) {
		this.zoneBallNum = zoneBallNum;
	}
	public int getTotalZoneBallNum() {
		return totalZoneBallNum;
	}
	public void setTotalZoneBallNum(int totalZoneBallNum) {
		this.totalZoneBallNum = totalZoneBallNum;
	}
	public BigDecimal getBeforeWe() {
		return beforeWe;
	}
	public void setBeforeWe(BigDecimal beforeWe) {
		this.beforeWe = beforeWe;
	}
	public BigDecimal getAfterWe() {
		return afterWe;
	}
	public void setAfterWe(BigDecimal afterWe) {
		this.afterWe = afterWe;
	}
	public int getPinchHitSucc() {
		return pinchHitSucc;
	}
	public void setPinchHitSucc(int pinchHitSucc) {
		this.pinchHitSucc = pinchHitSucc;
	}
	public int getPinchHitFail() {
		return pinchHitFail;
	}
	public void setPinchHitFail(int pinchHitFail) {
		this.pinchHitFail = pinchHitFail;
	}
	public int getReliefFail() {
		return reliefFail;
	}
	public void setReliefFail(int reliefFail) {
		this.reliefFail = reliefFail;
	}
	public int getReliefSucc() {
		return reliefSucc;
	}
	public void setReliefSucc(int reliefSucc) {
		this.reliefSucc = reliefSucc;
	}
	public int getBuntsCnt() {
		return buntsCnt;
	}
	public void setBuntsCnt(int buntsCnt) {
		this.buntsCnt = buntsCnt;
	}
	public int getTotalBuntsCnt() {
		return totalBuntsCnt;
	}
	public void setTotalBuntsCnt(int totalBuntsCnt) {
		this.totalBuntsCnt = totalBuntsCnt;
	}
	public int getStrongAttackCnt() {
		return strongAttackCnt;
	}
	public void setStrongAttackCnt(int strongAttackCnt) {
		this.strongAttackCnt = strongAttackCnt;
	}
	public int getTotalStrongAttackCnt() {
		return totalStrongAttackCnt;
	}
	public void setTotalStrongAttackCnt(int totalStrongAttackCnt) {
		this.totalStrongAttackCnt = totalStrongAttackCnt;
	}
	public int getQs() {
		return qs;
	}
	public void setQs(int qs) {
		this.qs = qs;
	}
	public int getQsPlus() {
		return qsPlus;
	}
	public void setQsPlus(int qsPlus) {
		this.qsPlus = qsPlus;
	}
	public int getShutOut() {
		return shutOut;
	}
	public void setShutOut(int shutOut) {
		this.shutOut = shutOut;
	}
	public int getCompletedGame() {
		return completedGame;
	}
	public void setCompletedGame(int completedGame) {
		this.completedGame = completedGame;
	}
	public BigDecimal getLeagueAvgWoba() {
		return leagueAvgWoba;
	}
	public void setLeagueAvgWoba(BigDecimal leagueAvgWoba) {
		this.leagueAvgWoba = leagueAvgWoba;
	}
	public int getLeagueTotalR() {
		return leagueTotalR;
	}
	public void setLeagueTotalR(Integer leagueTotalR) {
		this.leagueTotalR = leagueTotalR;
	}
	public int getLeagueTotalTpa() {
		return leagueTotalTpa;
	}
	public void setLeagueTotalTpa(Integer leagueTotalTpa) {
		this.leagueTotalTpa = leagueTotalTpa;
	}
	public BigDecimal getLeagueAvgObp() {
		return leagueAvgObp;
	}
	public void setLeagueAvgObp(BigDecimal leagueAvgObp) {
		this.leagueAvgObp = leagueAvgObp;
	}
	public BigDecimal getLeagueWsb() {
		return leagueWsb;
	}
	public void setLeagueWsb(BigDecimal leagueWsb) {
		this.leagueWsb = leagueWsb;
	}
	public String getKeyValue() {		
		if(keyValue != null) {
			return keyValue.replaceAll("\t", "").replaceAll("\n", "");
		}
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getDataRetrieveYn() {
		return dataRetrieveYn;
	}
	public void setDataRetrieveYn(String dataRetrieveYn) {
		this.dataRetrieveYn = dataRetrieveYn;
	}
	public int getSb() {
		return sb;
	}
	public void setSb(int sb) {
		this.sb = sb;
	}
	public int getStartWeekUnit() {
		return startWeekUnit;
	}
	public void setStartWeekUnit(int startWeekUnit) {
		this.startWeekUnit = startWeekUnit;
	}
	public int getEndWeekUnit() {
		return endWeekUnit;
	}
	public void setEndWeekUnit(int endWeekUnit) {
		this.endWeekUnit = endWeekUnit;
	}
	public int getWeekUnit() {
		return weekUnit;
	}
	public void setWeekUnit(int weekUnit) {
		this.weekUnit = weekUnit;
	}
	public int getzPitchingCnt() {
		return zPitchingCnt;
	}
	public void setzPitchingCnt(int zPitchingCnt) {
		this.zPitchingCnt = zPitchingCnt;
	}
	public int getoPitchingCnt() {
		return oPitchingCnt;
	}
	public void setoPitchingCnt(int oPitchingCnt) {
		this.oPitchingCnt = oPitchingCnt;
	}
	public int getzSwingCnt() {
		return zSwingCnt;
	}
	public void setzSwingCnt(int zSwingCnt) {
		this.zSwingCnt = zSwingCnt;
	}
	public int getoSwingCnt() {
		return oSwingCnt;
	}
	public void setoSwingCnt(int oSwingCnt) {
		this.oSwingCnt = oSwingCnt;
	}
	public int getzContactCnt() {
		return zContactCnt;
	}
	public void setzContactCnt(int zContactCnt) {
		this.zContactCnt = zContactCnt;
	}
	public int getoContactCnt() {
		return oContactCnt;
	}
	public void setoContactCnt(int oContactCnt) {
		this.oContactCnt = oContactCnt;
	}
	public int getPitchingCnt() {
		return pitchingCnt;
	}
	public void setPitchingCnt(int pitchingCnt) {
		this.pitchingCnt = pitchingCnt;
	}
	public int getAoCnt() {
		return aoCnt;
	}
	public void setAoCnt(int aoCnt) {
		this.aoCnt = aoCnt;
	}
	public int getPoCnt() {
		return poCnt;
	}
	public void setPoCnt(int poCnt) {
		this.poCnt = poCnt;
	}
	public int getGdpChanceCnt() {
		return gdpChanceCnt;
	}
	public void setGdpChanceCnt(int gdpChanceCnt) {
		this.gdpChanceCnt = gdpChanceCnt;
	}
	public int getAddedRunChanceCnt() {
		return addedRunChanceCnt;
	}
	public void setAddedRunChanceCnt(int addedRunChanceCnt) {
		this.addedRunChanceCnt = addedRunChanceCnt;
	}
	public int getAddedRunCnt() {
		return addedRunCnt;
	}
	public void setAddedRunCnt(int addedRunCnt) {
		this.addedRunCnt = addedRunCnt;
	}
	public int getStartPitcherCnt() {
		return startPitcherCnt;
	}
	public void setStartPitcherCnt(int startPitcherCnt) {
		this.startPitcherCnt = startPitcherCnt;
	}
}
