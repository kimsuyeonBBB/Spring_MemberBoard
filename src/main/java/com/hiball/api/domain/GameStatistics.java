package com.hiball.api.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.hiball.web.common.domain.CommonRecordDomain;

public class GameStatistics extends CommonRecordDomain {
	private static final double wbb = 0.690;
	private static final double whbp = 0.722;
	private static final double wibb = 0.1788;
	private static final double wb1b = 0.888;
	private static final double wb2b = 1.271;
	private static final double wb3b = 1.616;
	private static final double whr = 2.101;
	private static final double RUN_SB = 0.200;
	private static final double RUN_CS = -0.410;
	
	private DecimalFormat underOneFillDf = new DecimalFormat("#########.0");
	private DecimalFormat underThreeFillDf = new DecimalFormat("#########.000");
	private BigDecimal oneHundred = new BigDecimal(100);
	private String playInning = null;
	private int tb;
	private BigDecimal ip = new BigDecimal(0);
	private BigDecimal avg = new BigDecimal(0);
	private BigDecimal oAvg = new BigDecimal(0);
	private BigDecimal scoringPositionAvg = new BigDecimal(0);
	private BigDecimal ops = new BigDecimal(0);
	private BigDecimal era = null;
	private BigDecimal obp = new BigDecimal(0);
	private BigDecimal slg = new BigDecimal(0);
	private BigDecimal whip = null;
	private BigDecimal babip = new BigDecimal(0);
	private BigDecimal dips = new BigDecimal(0);
	private BigDecimal fip = new BigDecimal(0);
	private BigDecimal winningRate = new BigDecimal(0);
	private BigDecimal totInning = new BigDecimal(9);
	private BigDecimal constHr = new BigDecimal(13);
	private BigDecimal constBb = new BigDecimal(3);
	private BigDecimal constK = new BigDecimal(2);
	private BigDecimal stolenBaseSucRate = new BigDecimal(0);
	private BigDecimal gpa = new BigDecimal(0);
	private BigDecimal rc = new BigDecimal(0);
	private BigDecimal rc27 = new BigDecimal(0);
	private BigDecimal k9 = new BigDecimal(0);
	private BigDecimal kBbRate = new BigDecimal(0);
	private BigDecimal goAoRate = new BigDecimal(0);
	private BigDecimal isoP = new BigDecimal(0);
	private BigDecimal decisionStrikeRate = new BigDecimal(0);
	private BigDecimal decisionBallRate = new BigDecimal(0);
	private BigDecimal pitchingRate = new BigDecimal(0);
	private BigDecimal hittingRate = new BigDecimal(0);
	private BigDecimal b1bRate = new BigDecimal(0);
	private BigDecimal b2bRate = new BigDecimal(0);
	private BigDecimal b3bRate = new BigDecimal(0);
	private BigDecimal hrRate = new BigDecimal(0);
	private BigDecimal bbRate = new BigDecimal(0);
	private BigDecimal soRate = new BigDecimal(0);
	private BigDecimal hbpRate = new BigDecimal(0);
	private BigDecimal shRate = new BigDecimal(0);
	private BigDecimal sfRate = new BigDecimal(0);
	private BigDecimal kRate = new BigDecimal(0);
	private BigDecimal foRate = new BigDecimal(0);
	private BigDecimal goRate = new BigDecimal(0);
	private BigDecimal gdpRate = new BigDecimal(0);
	private BigDecimal etcResultCntRate = new BigDecimal(0);
	private BigDecimal sbTryRate = new BigDecimal(0);
	private BigDecimal from1bTo2bRate = new BigDecimal(0);
	private BigDecimal from1bTo3bRate = new BigDecimal(0);
	private BigDecimal from1bToHrRate = new BigDecimal(0);
	private BigDecimal from1bToOutRate = new BigDecimal(0);
	private BigDecimal from2bTo3bRate = new BigDecimal(0);
	private BigDecimal from2bToHrRate = new BigDecimal(0);
	private BigDecimal from2bToOutRate = new BigDecimal(0);
	private BigDecimal from3bToHrRate = new BigDecimal(0);
	private BigDecimal from3bToOutRate = new BigDecimal(0);
	private BigDecimal homeWpa = new BigDecimal(52.2);
	private BigDecimal awayWpa = new BigDecimal(47.8);
	private BigDecimal chanceScoreRate = new BigDecimal(0);
	private BigDecimal scoreByWinRate = new BigDecimal(0);
	private BigDecimal sSwingRate = new BigDecimal(0);
	private BigDecimal sContactRate = new BigDecimal(0);
	private BigDecimal bSwingRate = new BigDecimal(0);
	private BigDecimal bContactRate = new BigDecimal(0);
	private BigDecimal firstBallSRate = new BigDecimal(0);
	private BigDecimal firstBallBRate = new BigDecimal(0);
	private BigDecimal bbSoRate = null;
	private BigDecimal foGoRate = new BigDecimal(0);
	private BigDecimal ballTypePitchingRate = new BigDecimal(0);
	private BigDecimal ballZonePitchingRate = new BigDecimal(0);
	private BigDecimal strikeZonePitchingRate = new BigDecimal(0);
	private BigDecimal sJudgeRate = new BigDecimal(0);
	private BigDecimal bJudgeRate = new BigDecimal(0);
	private BigDecimal coursePRate = new BigDecimal(0);
	private BigDecimal courseCRate = new BigDecimal(0);
	private BigDecimal course1BRate = new BigDecimal(0);
	private BigDecimal course2BRate = new BigDecimal(0);
	private BigDecimal course3BRate = new BigDecimal(0);
	private BigDecimal courseSSRate = new BigDecimal(0);
	private BigDecimal courseLFRate = new BigDecimal(0);
	private BigDecimal courseCFRate = new BigDecimal(0);
	private BigDecimal courseRFRate = new BigDecimal(0);
	private BigDecimal stolenBaseSucRateByFactor = new BigDecimal(0);
	private BigDecimal stolen2BaseSucRate = new BigDecimal(0);
	private BigDecimal stolen3BaseSucRate = new BigDecimal(0);
	private BigDecimal noBaseSJudgeRate = new BigDecimal(0);
	private BigDecimal on1baseSJudgeRate = new BigDecimal(0);
	private BigDecimal on2baseSJudgeRate = new BigDecimal(0);
	private BigDecimal on3baseSJudgeRate = new BigDecimal(0);
	private BigDecimal on12baseSJudgeRate = new BigDecimal(0);
	private BigDecimal on13baseSJudgeRate = new BigDecimal(0);
	private BigDecimal on23baseSJudgeRate = new BigDecimal(0);
	private BigDecimal onFullbaseSJudgeRate = new BigDecimal(0);
	private BigDecimal under5scoreSJudgeRate = new BigDecimal(0);
	private BigDecimal under3between4scoreSJudgeRate = new BigDecimal(0);
	private BigDecimal under1between2scoreSJudgeRate = new BigDecimal(0);
	private BigDecimal tieSJudgeRate = new BigDecimal(0);
	private BigDecimal upper1between2scoreSJudgeRate = new BigDecimal(0);
	private BigDecimal upper3between4scoreSJudgeRate = new BigDecimal(0);
	private BigDecimal upper5scoreSJudgeRate = new BigDecimal(0);
	private String batterStateOfPlayerVsPlayer = "";
	private String pitcherStateOfPlayerVsPlayer = "";
	private BigDecimal pinPointStrikeRate = new BigDecimal(0);
	private BigDecimal pinPointBallRate = new BigDecimal(0);
	private BigDecimal fastballSJudgeRate = new BigDecimal(0);
	private BigDecimal sliderSJudgeRate = new BigDecimal(0);
	private BigDecimal curveSJudgeRate = new BigDecimal(0);
	private BigDecimal changeupSJudgeRate = new BigDecimal(0);
	private BigDecimal etcballSJudgeRate = new BigDecimal(0);
	private BigDecimal addStealBaseRate = new BigDecimal(0);
	private BigDecimal b1bDividedGameCnt = new BigDecimal(0);
	private BigDecimal b2bDividedGameCnt = new BigDecimal(0);
	private BigDecimal b3bDividedGameCnt = new BigDecimal(0);
	private BigDecimal hrDividedGameCnt = new BigDecimal(0);
	private BigDecimal hitDividedGameCnt = new BigDecimal(0);
	private BigDecimal erDividedGameCnt = new BigDecimal(0);
	private BigDecimal rDividedGameCnt = new BigDecimal(0);
	private BigDecimal accumPitchingBallDividedGameCnt = new BigDecimal(0);
	private BigDecimal accumPitchingBallDividedBatterCnt = new BigDecimal(0);
	private BigDecimal pitchingInningDividedGameCnt = new BigDecimal(0);
	private BigDecimal successStealDividedGameCnt = new BigDecimal(0);
	private BigDecimal bbDividedGameCnt = new BigDecimal(0);
	private BigDecimal kDividedGameCnt = new BigDecimal(0);
	private BigDecimal playerChangeSuccessRate = new BigDecimal(0);
	private BigDecimal playerChangeFailRate = new BigDecimal(0);
	private BigDecimal buntAttempRate = new BigDecimal(0);
	private BigDecimal buntSuccessRate = new BigDecimal(0);
	private BigDecimal playerChangeRate = new BigDecimal(0);
	private BigDecimal stealAttemptCnt = new BigDecimal(0);
	private BigDecimal stealAttemptRate = new BigDecimal(0); //도루시도율
	private BigDecimal stealFailureRate = new BigDecimal(0); //도루실패율 or 도루저지율
	private BigDecimal stealSuccessRate = new BigDecimal(0); //도루성공률 or 도루허용율
	private BigDecimal stealAttemptDivided9 = new BigDecimal(0); // 도루시도 / 9
	private BigDecimal stealSuccessDivided9 = new BigDecimal(0); // 도루허용 / 9
	private BigDecimal standingRate = new BigDecimal(0);
	private BigDecimal contactRate = new BigDecimal(0);
	private BigDecimal swingRate = new BigDecimal(0);
	private BigDecimal fastBallDividedGameCnt = new BigDecimal(0);
	private BigDecimal wildPitchDividedGameCnt = new BigDecimal(0);
	private BigDecimal fastBallDivided9 = new BigDecimal(0);
	private BigDecimal wildPitchDivided9 = new BigDecimal(0);
	private BigDecimal blockingDivided9 = new BigDecimal(0);
	private BigDecimal catcherAvgEr = new BigDecimal(0);
	private BigDecimal catcherAvgPitchingBallCnt = new BigDecimal(0);
	private BigDecimal catcherKRate = new BigDecimal(0);
	private BigDecimal catcherAvgSelectionPitcherBallCnt = new BigDecimal(0);
	private BigDecimal catcherAvgRescuePitcherBallCnt = new BigDecimal(0);
	private BigDecimal zoneFastBallRate = new BigDecimal(0);
	private BigDecimal zoneSlideRate = new BigDecimal(0);
	private BigDecimal zoneCurveRate = new BigDecimal(0);
	private BigDecimal zoneChangeUpRate = new BigDecimal(0);
	private BigDecimal zoneEtcBallRate = new BigDecimal(0);
	private BigDecimal zonePitchingBallRate = new BigDecimal(0);
	private BigDecimal pinchHitSuccRate = new BigDecimal(0);								// 대타성공비율
	private BigDecimal reliefSuccRate = new BigDecimal(0);									// 구원투수성공비율	
	private BigDecimal playerWpa = null;													// 선수WPA
	private BigDecimal buntsRate = new BigDecimal(0);										// 번트비율
	private BigDecimal strongAttackRate = new BigDecimal(0);								// 강공비율
	private BigDecimal oSwingRate = new BigDecimal(0);
	private BigDecimal zSwingRate = new BigDecimal(0);
	private BigDecimal swingRate2 = new BigDecimal(0);
	private BigDecimal oContactRate = new BigDecimal(0);
	private BigDecimal zContactRate = new BigDecimal(0);
	private BigDecimal zoneRate = new BigDecimal(0);
	private BigDecimal contactRate2 = new BigDecimal(0);
	private BigDecimal bb9 = new BigDecimal(0);
	private BigDecimal hr9 = new BigDecimal(0);
	//Sabermetrics Stats
	private BigDecimal woba = new BigDecimal(0);
	private BigDecimal wrc = new BigDecimal(0);
	private BigDecimal wrcPlus = new BigDecimal(0);
	private BigDecimal wraa = new BigDecimal(0);
	private BigDecimal rplus = new BigDecimal(0);
	private BigDecimal rminus = new BigDecimal(0);
	private BigDecimal wobaScale = new BigDecimal(0);
	private BigDecimal wsb = new BigDecimal(0);
	private BigDecimal lgwSb = new BigDecimal(0);
	private BigDecimal batter_war = new BigDecimal(0);
	private BigDecimal spd = new BigDecimal(0);
	private BigDecimal lob = new BigDecimal(0);
	private BigDecimal gdpSuccRate = new BigDecimal(0);
	private BigDecimal addedRunAllowRate = new BigDecimal(0);
	private BigDecimal errRate = new BigDecimal(0);
	
	public BigDecimal getIp() {
		BigDecimal outNum = new BigDecimal(3);
		BigDecimal outCount = new BigDecimal(super.getOutCount());
		this.ip = outCount.divide(outNum,3,BigDecimal.ROUND_HALF_UP);
		
		return ip;
	}
	
	public int getTb() {
		tb = super.getB1b()+(2*super.getB2b())+(3*super.getB3b())+(4*super.getHr());
		return tb;
	}
	
	public BigDecimal getStolenBaseSucRate() {
		BigDecimal sb = new BigDecimal(super.getSuccessSteal());
		BigDecimal cs = new BigDecimal(super.getFailureSteal());
		
		if(sb.add(cs).intValue() != 0) {
			this.stolenBaseSucRate = sb.divide(sb.add(cs),3,BigDecimal.ROUND_HALF_UP);
			
			this.stolenBaseSucRate = new BigDecimal(underOneFillDf.format(stolenBaseSucRate.doubleValue()));
		}
		
		return stolenBaseSucRate;
	}
	
	public BigDecimal getAvg() {
		BigDecimal h = new BigDecimal(super.getB1b()+super.getB2b()+super.getB3b()+super.getHr());
		BigDecimal ab = new BigDecimal(super.getAb());
		
		if (ab.intValue() != 0) {
			this.avg  = h.divide(ab, 3, BigDecimal.ROUND_HALF_UP); 
			this.avg = new BigDecimal(underThreeFillDf.format(avg.doubleValue()));
		} 
		
		return avg;
	}
	
	public BigDecimal getoAvg() {
		BigDecimal h = new BigDecimal(super.getoB1b() + super.getoB2b() + super.getoB3b() + super.getoHr());
		BigDecimal ab = new BigDecimal(super.getOab());
		
		if(ab.intValue() != 0) {
			this.oAvg = h.divide(ab, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return oAvg;
	}

	public BigDecimal getScoringPositionAvg() {
		BigDecimal scoringPositionAb = new BigDecimal(super.getScoringPositionAb());
		BigDecimal scoringPositionHit = new BigDecimal(super.getScoringPositionB1b() + super.getScoringPositionB2b() + super.getScoringPositionB3b() 
															+ super.getScoringPositionHr());
		
		if(scoringPositionAb.intValue() != 0) {
			this.scoringPositionAvg = scoringPositionHit.divide(scoringPositionAb, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return scoringPositionAvg;
	}

	public BigDecimal getOps() {
		this.ops = getSlg().add(getObp());
		return ops;
	}
	
	public void setEra(BigDecimal era) {
		this.era = era;
	}
	
	public BigDecimal getEra() {
		if(this.era != null) return era;
		
		BigDecimal er = new BigDecimal(super.getEr());
		BigDecimal ip = getIp();
		if (ip.intValue() != 0) {
			this.era = (er.multiply(totInning)).divide(ip, 2, BigDecimal.ROUND_HALF_UP);
		}
		
		if(this.era == null) this.era = new BigDecimal(0);
		return era;
	}
	
	public BigDecimal getObp() {
		BigDecimal totalOnBase = new BigDecimal(super.getB1b()+super.getB2b()+super.getB3b()+super.getHr()+super.getBb()+super.getHbp());
		BigDecimal ab = new BigDecimal(super.getAb()+super.getBb()+super.getHbp()+super.getSf());
		
		if (ab.intValue() != 0) {
			this.obp = totalOnBase.divide(ab, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return obp;
	}
	
	public BigDecimal getSlg() {
		BigDecimal tb = new BigDecimal(getTb());
		BigDecimal ab = new BigDecimal(super.getAb());
		
		if (ab.intValue() != 0) {
			this.slg  = tb.divide(ab, 3, BigDecimal.ROUND_HALF_UP); 
		}
		
		return slg;
	}
	
	public BigDecimal getIsoP() {
		BigDecimal avg = this.getAvg();
		BigDecimal slg = this.getSlg();
		
		this.isoP = slg.subtract(avg);
		
		return isoP;
	}
	
	public void setWhip(BigDecimal whip) {
		this.whip = whip;
	}
	
	public BigDecimal getWhip() {
		if(this.whip != null) return whip;
		BigDecimal h = new BigDecimal(super.getB1b()+super.getB2b()+super.getB3b()+super.getHr());
		BigDecimal bb = new BigDecimal (super.getBb()+super.getIbb()+super.getHbp());
		
		if (ip.intValue() != 0) {
			this.whip = (h.add(bb)).divide(getIp(), 3, BigDecimal.ROUND_HALF_UP);
		}
		
		if(this.whip == null) this.whip = new BigDecimal(0);
		
		return whip;
	}
	
	public BigDecimal getBabip() {
		BigDecimal sunNum = new BigDecimal(super.getB1b()+super.getB2b()+super.getB3b());
		BigDecimal momNum = new BigDecimal (super.getAb()-super.getK()-super.getHr()+super.getSf()+super.getSh());
		
		if (momNum.intValue() != 0) {
			this.babip = sunNum.divide(momNum, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return babip;
	}
	public BigDecimal getDips() {
		BigDecimal constants = new BigDecimal(3);
		BigDecimal hr = new BigDecimal(super.getHr());
		BigDecimal bb = new BigDecimal(super.getBb() + super.getHbp());
		BigDecimal k = new BigDecimal(super.getK());
		
		if (ip.intValue() != 0) {
			this.dips = 
				constants.add(
					(constHr.multiply(hr).
						add(
							constBb.multiply(bb)
						).subtract(
							constK.multiply(k)
						)
					).divide(getIp(), 3, BigDecimal.ROUND_HALF_UP)
				);
		}
		
		return dips;
	}
	public BigDecimal getFip() {
		BigDecimal constants = new BigDecimal("3.2000");
		BigDecimal hr = new BigDecimal(super.getHr());
		BigDecimal bb = new BigDecimal(super.getBb());
		BigDecimal k = new BigDecimal(super.getK());
		
		if (ip.intValue() != 0) {
			this.fip =
				constants.add(
						(constHr.multiply(hr).
							add(
								constBb.multiply(bb)
							).subtract(
								constK.multiply(k)
							)
						).divide(getIp(),3, BigDecimal.ROUND_HALF_UP)
					);
		}
		
		return fip;
	}
	
	public BigDecimal getWinningRate() {
		BigDecimal sunNum = new BigDecimal(super.getWin());
		BigDecimal motherNum = new BigDecimal(super.getWin()+super.getLose());
		
		if (motherNum.intValue() != 0) {
			winningRate = sunNum.divide(motherNum, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return winningRate;
	}

	public String getPlayInning() {
		if(playInning != null) return playInning;
		
		BigDecimal outCount = new BigDecimal(super.getOutCount());
		BigDecimal outNum = new BigDecimal (3);
		String inn = outCount.divideToIntegralValue(outNum).toString();
		String remainNum = outCount.remainder(outNum).toString();
		
		this.playInning = inn.concat(".").concat(remainNum); 
		
		if(playInning == null) return "";
		
		return playInning;
	}
	
	public void setPlayInning(String playInning) {
		this.playInning  = playInning;
	}
	
	public BigDecimal getGpa() {
		BigDecimal obp = getObp();
		BigDecimal slg = getSlg();
		BigDecimal cons = new BigDecimal("1.8000");
		BigDecimal divisor = new BigDecimal(4);
		
		this.gpa = ((cons.multiply(obp)).add(slg)).divide(divisor);
		
		return gpa;
	}
	
	public BigDecimal getRc() {
		BigDecimal fCons = new BigDecimal("0.2600");
		BigDecimal sCons = new BigDecimal("0.5200");
		BigDecimal hitRes = new BigDecimal(super.getHit()+super.getBb()+super.getHbp()-super.getFailureSteal()-super.getGdp());
		BigDecimal tb = new BigDecimal(getTb());
		BigDecimal bb = new BigDecimal (super.getBb()-super.getIbb()+super.getHbp());
		BigDecimal sacrHit = new BigDecimal(super.getSh()+super.getSf()+super.getSuccessSteal());
		BigDecimal divisor = new BigDecimal(super.getAb()+super.getBb()+super.getHbp()+super.getSh()+super.getSf());
		
		if (divisor.intValue()!=0) {
			this.rc = hitRes.multiply(
				tb.add(fCons).multiply(bb)).add(
						sCons.multiply(sacrHit)).divide(divisor,3, BigDecimal.ROUND_HALF_UP);
		}
		
		
		return rc;
	}
	
	public BigDecimal getRc27() {
		BigDecimal cons = new BigDecimal(27);
		BigDecimal divisor = new BigDecimal(super.getAb()-super.getHit()+super.getFailureSteal()+super.getGdp()+super.getSf());
		
		if(divisor.intValue()!=0) {
			rc27 = (cons.multiply(getRc())).divide(divisor, 3, BigDecimal.ROUND_HALF_UP);
		}
		return rc27;
	}
	
	public BigDecimal getK9 () {
		BigDecimal k = new BigDecimal(super.getSo());
		BigDecimal divisor = getIp();
		
		if (divisor.intValue()!=0) {
			k9 = (k.multiply(totInning)).divide(divisor, 3, BigDecimal.ROUND_HALF_UP);
		}
		return k9;
	}
	
	public BigDecimal getKBbRate() {
		BigDecimal k = new BigDecimal(super.getK());
		BigDecimal bb = new BigDecimal(super.getBb());
		
		if (bb.intValue()!=0) {
			kBbRate = k.divide(bb, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return kBbRate;
	}
	
	public BigDecimal getGoAoRate() {
		BigDecimal go = new BigDecimal(super.getGo());
		BigDecimal ao = new BigDecimal(super.getFo());
		
		if (ao.intValue()!=0) {
			goAoRate = go.divide(ao, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return goAoRate;
	}
	
	public BigDecimal getDecisionStrikeRate() {
		BigDecimal strike = new BigDecimal (super.getDecisionStrike());
		BigDecimal ball = new BigDecimal (super.getDecisionBall());
		
		if (strike.add(ball).intValue() !=0) {
			decisionStrikeRate = strike.divide(strike.add(ball), 3, BigDecimal.ROUND_HALF_UP);
		}
		return decisionStrikeRate ;
	}
	
	public BigDecimal getDecisionBallRate() {
		BigDecimal strike = new BigDecimal (super.getDecisionStrike());
		BigDecimal ball = new BigDecimal (super.getDecisionBall());
		
		if (strike.add(ball).intValue() !=0) {
			decisionBallRate = ball.divide(strike.add(ball),3, BigDecimal.ROUND_HALF_UP);
		}
		return decisionBallRate ;
	}

	public BigDecimal getPitchingRate() {
		BigDecimal pitchingCount = new BigDecimal(super.getPitchingCount());
		BigDecimal total = new BigDecimal(super.getTotal());
		
		if (total.intValue()!=0) {
			pitchingRate = pitchingCount.divide(total, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return pitchingRate;
	}
	
	public BigDecimal getHittingRate() {
		BigDecimal hittingCount = new BigDecimal(super.getHittingCount());
		BigDecimal pitchingCount = new BigDecimal(super.getPitchingCount());
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		
		if (pitchingCount.intValue()!=0) {
			hittingRate = hittingCount.divide(pitchingCount, 3, BigDecimal.ROUND_HALF_UP);
		} else if (totalHitting.intValue()!=0) {
			hittingRate = hittingCount.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return hittingRate;
	}
	
	public BigDecimal getB1bRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal b1b = new BigDecimal(super.getB1b());
		
		if (totalHitting.intValue()!=0) {
			b1bRate = b1b.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return b1bRate;
	}
	
	public BigDecimal getB2bRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal b2b = new BigDecimal(super.getB2b());
		
		if (totalHitting.intValue()!=0) {
			b2bRate = b2b.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return b2bRate;
	}
	
	public BigDecimal getB3bRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal b3b = new BigDecimal(super.getB3b());
		
		if (totalHitting.intValue()!=0) {
			b3bRate = b3b.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return b3bRate;
	}
	
	public BigDecimal getHrRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal hr = new BigDecimal(super.getHr());
		
		if (totalHitting.intValue()!=0) {
			hrRate = hr.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return hrRate;
	}

//	public BigDecimal getBbRate() {
//		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
//		BigDecimal bb = new BigDecimal(super.getBb());
//		
//		if (totalHitting.intValue()!=0) {
//			bbRate = bb.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
//		}
//		return bbRate;
//	}
	
	public BigDecimal getBbRate() {
		BigDecimal tpa = new BigDecimal(super.getTpa());
		BigDecimal bb = new BigDecimal(super.getBb());
		
		if(tpa.intValue() != 0) {
			this.bbRate = bb.divide(tpa, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return bbRate;
	}
	
	public BigDecimal getSoRate() {
		BigDecimal tpa = new BigDecimal(super.getTpa());
		BigDecimal so = new BigDecimal(super.getSo());
		
		if(so.intValue() == 0) {
			so = new BigDecimal(super.getK());
		}
		
		if(tpa.intValue() != 0) {
			this.soRate = so.divide(tpa, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return soRate;
	}

	public BigDecimal getHbpRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal hbp = new BigDecimal(super.getHbp());
		
		if (totalHitting.intValue()!=0) {
			hbpRate = hbp.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		return hbpRate;
	}

	public BigDecimal getShRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal sh = new BigDecimal(super.getSh());
		
		if (totalHitting.intValue()!=0) {
			shRate = sh.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		return shRate;
	}

	public BigDecimal getSfRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal sf = new BigDecimal(super.getSf());
		
		if (totalHitting.intValue()!=0) {
			sfRate = sf.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		return sfRate;
	}

	public BigDecimal getkRate() {
//		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal tpa = new BigDecimal(super.getTpa());
		BigDecimal k = new BigDecimal(super.getK());
		
		if (tpa.intValue()!=0) {
			kRate = k.divide(tpa, 3, BigDecimal.ROUND_HALF_UP);
		}
		return kRate;
	}

	public BigDecimal getFoRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal fo = new BigDecimal(super.getFo());
		
		if (totalHitting.intValue()!=0) {
			foRate = fo.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		return foRate;
	}

	public BigDecimal getGoRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal go = new BigDecimal(super.getGo());
		
		if (totalHitting.intValue()!=0) {
			goRate = go.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		return goRate;
	}

	public BigDecimal getGdpRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal gdp = new BigDecimal(super.getGdp());
		
		if (totalHitting.intValue()!=0) {
			gdpRate = gdp.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		return gdpRate;
	}

	public BigDecimal getEtcResultCntRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal etcResultCnt = new BigDecimal(super.getEtcResultCnt());
		
		if (totalHitting.intValue()!=0) {
			etcResultCntRate = etcResultCnt.divide(totalHitting, 3, BigDecimal.ROUND_HALF_UP);
		}
		return etcResultCntRate;
	}
	
	public BigDecimal getSbTryRate(){
		BigDecimal changeSteal = new BigDecimal(super.getChanceSteal());
		BigDecimal successSteal = new BigDecimal(super.getSuccessSteal());
		BigDecimal failureSteal = new BigDecimal(super.getFailureSteal());
		
		if(changeSteal.intValue() != 0){
			BigDecimal sfStealCnt = successSteal.add(failureSteal);
			sbTryRate = sfStealCnt.divide(changeSteal, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return sbTryRate;
	}

	public BigDecimal getFrom1bTo2bRate(){
		BigDecimal on1bCnt = new BigDecimal(super.getOn1bCnt());
		BigDecimal from1bTo2bCnt = new BigDecimal(super.getFrom1bTo2b());
		
		if(on1bCnt.intValue() != 0){
			from1bTo2bRate = from1bTo2bCnt.divide(on1bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from1bTo2bRate;
	}
	
	public BigDecimal getFrom1bTo3bRate(){
		BigDecimal on1bCnt = new BigDecimal(super.getOn1bCnt());
		BigDecimal from1bTo3bCnt = new BigDecimal(super.getFrom1bTo3b());
		
		if(on1bCnt.intValue() != 0){
			from1bTo3bRate = from1bTo3bCnt.divide(on1bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from1bTo3bRate;
	}
	
	public BigDecimal getFrom1bToHrRate(){
		BigDecimal on1bCnt = new BigDecimal(super.getOn1bCnt());
		BigDecimal from1bToHrCnt = new BigDecimal(super.getFrom1bTohm());
		
		if(on1bCnt.intValue() != 0){
			from1bToHrRate = from1bToHrCnt.divide(on1bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from1bToHrRate;
	}
	
	public BigDecimal getFrom1bToOutRate() {
		BigDecimal on1bCnt = new BigDecimal(super.getOn1bCnt());
		BigDecimal from1bToOutCnt = new BigDecimal(super.getFrom1bToOut());
		
		if(on1bCnt.intValue() != 0) {
			this.from1bToOutRate = from1bToOutCnt.divide(on1bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from1bToOutRate;
	}
	
	public BigDecimal getFrom2bTo3bRate(){
		BigDecimal on2bCnt = new BigDecimal(super.getOn2bCnt());
		BigDecimal from2bTo3bCnt = new BigDecimal(super.getFrom2bTo3b());
		
		if(on2bCnt.intValue() != 0){
			this.from2bTo3bRate = from2bTo3bCnt.divide(on2bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from2bTo3bRate;
	}
	
	public BigDecimal getFrom2bToHrRate(){
		BigDecimal on2bCnt = new BigDecimal(super.getOn2bCnt());
		BigDecimal from2bToHrCnt = new BigDecimal(super.getFrom2bTohm());
		
		if(on2bCnt.intValue() != 0){
			this.from2bToHrRate = from2bToHrCnt.divide(on2bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from2bToHrRate;
	}
	
	public BigDecimal getFrom2bToOutRate() {
		BigDecimal on2bCnt = new BigDecimal(super.getOn2bCnt());
		BigDecimal from2bToOutCnt = new BigDecimal(super.getFrom2bToOut());
		
		if(on2bCnt.intValue() != 0) {
			this.from2bToOutRate = from2bToOutCnt.divide(on2bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from2bToOutRate;
	}
	
	public BigDecimal getFrom3bToHrRate(){
		BigDecimal on3bCnt = new BigDecimal(super.getOn3bCnt());
		BigDecimal from3bToHrCnt = new BigDecimal(super.getFrom3bTohm());
		
		if(on3bCnt.intValue() != 0){
			from3bToHrRate = from3bToHrCnt.divide(on3bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from3bToHrRate;
	}
	
	public BigDecimal getFrom3bToOutRate() {
		BigDecimal on3bCnt = new BigDecimal(super.getOn3bCnt());
		BigDecimal from3bToOutCnt = new BigDecimal(super.getFrom3bToOut());
		
		if(on3bCnt.intValue() != 0) {
			this.from3bToOutRate = from3bToOutCnt.divide(on3bCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return from3bToOutRate;
	}
	
	public BigDecimal getHomeWpa(){
		String homeOrAway = super.getHomeOrAway();
		
		if(homeOrAway == null) {
			return homeWpa;
		} else {
			//Data 부정확해서 나오는 데이터 문제...를 해결하기 위한 임시 방편 소스
			if(super.getAfterWe() == null) return homeWpa;
			
			if(homeOrAway.equals("HOME")) {
				
				this.homeWpa = super.getAfterWe().multiply(oneHundred);
			} else {
				this.homeWpa = new BigDecimal(1).subtract(super.getAfterWe()).multiply(oneHundred);
			}
		}
		
		return homeWpa;
		
	}

	public BigDecimal getAwayWpa(){
		String homeOrAway = super.getHomeOrAway();
		
		if(homeOrAway == null) {
			return awayWpa;
		}else {
			//Data 부정확해서 나오는 데이터 문제...를 해결하기 위한 임시 방편 소스
			if(super.getAfterWe() == null) return awayWpa;
			
			if(homeOrAway.equals("AWAY")) {
				awayWpa = super.getAfterWe().multiply(oneHundred);
			} else {
				awayWpa = new BigDecimal(1).subtract(super.getAfterWe()).multiply(oneHundred);
			}
		}
		
		return awayWpa;
	}
	
	public BigDecimal getChanceScoreRate() {
		BigDecimal stadiumPlayCnt = new BigDecimal(super.getStadiumPlayCnt());
		BigDecimal gameCnt = new BigDecimal(super.getGameCount());
		
		if(stadiumPlayCnt.intValue() != 0){
			chanceScoreRate = gameCnt.divide(stadiumPlayCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return chanceScoreRate;
	}
	
	
	public BigDecimal getScoreByWinRate() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCount());
		BigDecimal win = new BigDecimal(super.getWin());

		if(gameCnt.intValue() != 0){
			scoreByWinRate = win.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}

		return scoreByWinRate;
	}

	public BigDecimal getsSwingRate() {
		BigDecimal sSituationCnt = new BigDecimal(super.getsSituationCnt());
		BigDecimal sSwingCnt = new BigDecimal(super.getsSwingCnt());
		
		if(sSituationCnt.intValue() != 0){
			sSwingRate = sSwingCnt.divide(sSituationCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return sSwingRate;
	}

	public BigDecimal getsContactRate() {
		BigDecimal sSituationCnt = new BigDecimal(super.getsSituationCnt());
		BigDecimal sContactCnt = new BigDecimal(super.getsContactCnt());
		
		if(sSituationCnt.intValue() != 0){
			sContactRate = sContactCnt.divide(sSituationCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return sContactRate;
	}

	public BigDecimal getbSwingRate() {
		BigDecimal bSituationCnt = new BigDecimal(super.getbSituationCnt());
		BigDecimal bSwingCnt = new BigDecimal(super.getbSwingCnt());
		
		if(bSituationCnt.intValue() != 0) {
			bSwingRate = bSwingCnt.divide(bSituationCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return bSwingRate;
	}

	public BigDecimal getbContactRate() {
		BigDecimal bSituationCnt = new BigDecimal(super.getbSituationCnt());
		BigDecimal bContactCnt = new BigDecimal(super.getbContactCnt());
		
		if(bSituationCnt.intValue() != 0){
			bContactRate = bContactCnt.divide(bSituationCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return bContactRate;
	}

	public BigDecimal getFirstBallSRate() {
		BigDecimal firstBallCnt = new BigDecimal(super.getFirstBallSCnt() + super.getFirstBallBCnt());
		BigDecimal firstBallSCnt = new BigDecimal(super.getFirstBallSCnt());
		
		if(firstBallCnt.intValue() != 0) {
			firstBallSRate = firstBallSCnt.divide(firstBallCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return firstBallSRate;
	}

	public BigDecimal getFirstBallBRate() {
		BigDecimal firstBallCnt = new BigDecimal(super.getFirstBallSCnt() + super.getFirstBallBCnt());
		BigDecimal firstBallBCnt = new BigDecimal(super.getFirstBallBCnt());
		
		if(firstBallCnt.intValue() != 0) {
			firstBallBRate = firstBallBCnt.divide(firstBallCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return firstBallBRate;
	}

	public void setBbSoRate(BigDecimal bbSoRate) {
		this.bbSoRate = bbSoRate;
	}
	
	public BigDecimal getBbSoRate() {
		if(this.bbSoRate != null) return bbSoRate;
		
		BigDecimal bb = new BigDecimal(super.getBb());
		BigDecimal so = new BigDecimal(super.getK());
		
		if(so.intValue() == 0) {
			so = new BigDecimal(super.getSo());
		}
		
		if(so.intValue() != 0) {
			bbSoRate = bb.divide(so, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		if(this.bbSoRate == null) this.bbSoRate = new BigDecimal(0);
		
		return bbSoRate;
	}
	
	public BigDecimal getFoGoRate() {
		BigDecimal fo = new BigDecimal(super.getFo());
		BigDecimal go = new BigDecimal(super.getGo());
		
		if(go.intValue() != 0) {
			foGoRate = fo.divide(go, 3, BigDecimal.ROUND_HALF_UP);
		}
		return foGoRate;
	}

	public BigDecimal getBallTypePitchingRate() {
		BigDecimal totalCnt = new BigDecimal(super.getTotalBallTypePitchingCnt());
		BigDecimal cnt = new BigDecimal(super.getBallTypePitchingCnt());
		
		if(totalCnt.intValue() != 0) {
			ballTypePitchingRate = cnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP);
			ballTypePitchingRate = ballTypePitchingRate.multiply(oneHundred);
		}
		return ballTypePitchingRate;
	}

	public BigDecimal getBallZonePitchingRate() {
		BigDecimal totalSbPitchingCnt = new BigDecimal(super.getTotalSbPitchingCnt());
		BigDecimal ballPitchingCnt = new BigDecimal(super.getBallPitchingCnt());
	
		if(totalSbPitchingCnt.intValue() != 0) {
			ballZonePitchingRate = ballPitchingCnt.divide(totalSbPitchingCnt, 3, BigDecimal.ROUND_HALF_UP);
			ballZonePitchingRate = ballZonePitchingRate.multiply(oneHundred);
		}
		
		return ballZonePitchingRate;
	}

	public BigDecimal getStrikeZonePitchingRate() {
		BigDecimal totalSbPitchingCnt = new BigDecimal(super.getTotalSbPitchingCnt());
		BigDecimal strikePitchingCnt = new BigDecimal(super.getStrikePitchingCnt());
		
		if(totalSbPitchingCnt.intValue() != 0) {
			strikeZonePitchingRate = strikePitchingCnt.divide(totalSbPitchingCnt, 3, BigDecimal.ROUND_HALF_UP);
			strikeZonePitchingRate = strikeZonePitchingRate.multiply(oneHundred);
		}
		
		return strikeZonePitchingRate;
	}
	public BigDecimal getsJudgeRate() {
		BigDecimal totalCnt = new BigDecimal(super.getSbTotalCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getsJudgeCnt());
		
		if(totalCnt.intValue() != 0){
			sJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP);
			sJudgeRate = sJudgeRate.multiply(oneHundred);
		}

		return sJudgeRate;
		
	}

	public BigDecimal getbJudgeRate() {
		BigDecimal totalCnt = new BigDecimal(super.getSbTotalCnt());
		BigDecimal bJudgeCnt = new BigDecimal(super.getbJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			bJudgeRate = bJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP);
			bJudgeRate = bJudgeRate.multiply(oneHundred);
		}
		
		return bJudgeRate;
	}

	public BigDecimal getCoursePRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal coursePCnt = new BigDecimal(super.getCoursePCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			coursePRate = coursePCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			coursePRate = coursePRate.multiply(oneHundred);
		}
		
		return coursePRate;
	}

	public BigDecimal getCourseCRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal courseCCnt = new BigDecimal(super.getCourseCcnt());
		
		if(totalCourseCnt.intValue() != 0) {
			courseCRate = courseCCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			courseCRate = courseCRate.multiply(oneHundred);
		}
		
		return courseCRate;
	}

	public BigDecimal getCourse1BRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal course1BCnt = new BigDecimal(super.getCourse1BCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			course1BRate = course1BCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			course1BRate = course1BRate.multiply(oneHundred);
		}
		return course1BRate;
	}

	public BigDecimal getCourse2BRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal course2BCnt = new BigDecimal(super.getCourse2BCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			course2BRate = course2BCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			course2BRate = course2BRate.multiply(oneHundred);
		}
		return course2BRate;
	}

	public BigDecimal getCourse3BRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal course3BCnt = new BigDecimal(super.getCourse3BCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			course3BRate = course3BCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			course3BRate = course3BRate.multiply(oneHundred);
		}
		return course3BRate;
	}

	public BigDecimal getCourseSSRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal courseSSCnt = new BigDecimal(super.getCourseSSCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			courseSSRate = courseSSCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			courseSSRate = courseSSRate.multiply(oneHundred);
		}
		return courseSSRate;
	}

	public BigDecimal getCourseLFRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal courseLFCnt = new BigDecimal(super.getCourseLFCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			courseLFRate = courseLFCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			courseLFRate = courseLFRate.multiply(oneHundred);
		}
		
		return courseLFRate;
	}

	public BigDecimal getCourseCFRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal courseCFCnt = new BigDecimal(super.getCourseCFCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			courseCFRate = courseCFCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			courseCFRate = courseCFRate.multiply(oneHundred);
		}
		return courseCFRate;
	}

	public BigDecimal getCourseRFRate() {
		BigDecimal totalCourseCnt = new BigDecimal(super.getTotalCourseCnt());
		BigDecimal courseRFCnt = new BigDecimal(super.getCourseRFCnt());
		
		if(totalCourseCnt.intValue() != 0) {
			courseRFRate = courseRFCnt.divide(totalCourseCnt, 3, BigDecimal.ROUND_HALF_UP);
			courseRFRate = courseRFRate.multiply(oneHundred);
		}
		
		return courseRFRate;
	}

	public BigDecimal getStolenBaseSucRateByFactor() {
		
		return stolenBaseSucRateByFactor;
	}

	public BigDecimal getStolen2BaseSucRate() {
		BigDecimal totalCnt = new BigDecimal(super.getSuccessedStolen2Base() + super.getFailedStolen2Base());
		BigDecimal sucCnt = new BigDecimal(super.getSuccessedStolen2Base());
		
		if(totalCnt.intValue() != 0) {
			stolen2BaseSucRate = sucCnt.divide(totalCnt,3,BigDecimal.ROUND_HALF_UP);
		}
		
		return stolen2BaseSucRate;
	}

	public BigDecimal getStolen3BaseSucRate() {
		BigDecimal totalCnt = new BigDecimal(super.getSuccessedStolen3Base() + super.getFailedStolen3Base());
		BigDecimal sucCnt = new BigDecimal(super.getSuccessedStolen3Base());
		
		if(totalCnt.intValue() != 0) {
			stolen3BaseSucRate = sucCnt.divide(totalCnt,3,BigDecimal.ROUND_HALF_UP);
		}
		
		return stolen3BaseSucRate;
	}

	public BigDecimal getNoBaseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getNoBaseBJudgeCnt() + super.getNoBaseSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getNoBaseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.noBaseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return noBaseSJudgeRate;
	}

	public BigDecimal getOn1baseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getOn1baseSJudgeCnt() + super.getOn1baseBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getOn1baseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.on1baseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return on1baseSJudgeRate;
	}

	public BigDecimal getOn2baseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getOn2baseBJudgeCnt() + super.getOn2baseSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getOn2baseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.on2baseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return on2baseSJudgeRate;
	}

	public BigDecimal getOn3baseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getOn3baseBJudgeCnt() + super.getOn3baseSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getOn3baseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.on3baseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return on3baseSJudgeRate;
	}

	public BigDecimal getOn12baseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getOn12baseBJudgeCnt() + super.getOn12baseSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getOn12baseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.on12baseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return on12baseSJudgeRate;
	}

	public BigDecimal getOn13baseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getOn13baseBJudgeCnt() + super.getOn13baseSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getOn13baseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.on13baseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return on13baseSJudgeRate;
	}

	public BigDecimal getOn23baseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getOn23baseBJudgeCnt() + super.getOn23baseSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getOn23baseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.on23baseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return on23baseSJudgeRate;
	}

	public BigDecimal getOnFullbaseSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getOnFullbaseBJudgeCnt() + super.getOnFullbaseSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getOnFullbaseSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.onFullbaseSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return onFullbaseSJudgeRate;
	}

	public BigDecimal getUnder5scoreSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getUnder5scoreSJudgeCnt() + super.getUnder5scoreBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getUnder5scoreSJudgeCnt());
	
		if(totalCnt.intValue() != 0) {
			this.under5scoreSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return under5scoreSJudgeRate;
	}

	public BigDecimal getUnder3between4scoreSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getUnder3between4scoreSJudgeCnt() + super.getUnder3between4scoreBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getUnder3between4scoreSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.under3between4scoreSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return under3between4scoreSJudgeRate;
	}

	public BigDecimal getUnder1between2scoreSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getUnder1between2scoreSJudgeCnt() + super.getUnder1between2scoreBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getUnder1between2scoreSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.under1between2scoreSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return under1between2scoreSJudgeRate;
	}

	public BigDecimal getTieSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getTieSJudgeCnt() + super.getTieBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getTieSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.tieSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return tieSJudgeRate;
	}

	public BigDecimal getUpper1between2scoreSJudgeRate() {
		
		BigDecimal totalCnt = new BigDecimal(super.getUpper1between2scoreSJudgeCnt() + super.getUpper1between2scoreBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getUpper1between2scoreSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.upper1between2scoreSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return upper1between2scoreSJudgeRate;
	}

	public BigDecimal getUpper3between4scoreSJudgeRate() {
		BigDecimal totalCnt = new BigDecimal(super.getUpper3between4scoreSJudgeCnt() + super.getUpper3between4scoreBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getUpper3between4scoreSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.upper3between4scoreSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return upper3between4scoreSJudgeRate;
	}

	public BigDecimal getUpper5scoreSJudgeRate() {
		BigDecimal totalCnt = new BigDecimal(super.getUpper5scoreSJudgeCnt() + super.getUpper5scoreBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getUpper5scoreSJudgeCnt());
		
		if(totalCnt.intValue() != 0) {
			this.upper5scoreSJudgeRate = sJudgeCnt.divide(totalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return upper5scoreSJudgeRate;
	}

	public String getBatterStateOfPlayerVsPlayer() {
		double ab = (double)super.getAb();
		double h = super.getB1b() + super.getB2b() + super.getB3b() + super.getHr();
		double avg = 0;
		if(ab != 0) {
			avg = h/ab;
		}
		
		if(avg >= 0.350) {
			this.batterStateOfPlayerVsPlayer = "Very Strong";
		} else if (avg >= 0.300 && avg < 0.350) {
			this.batterStateOfPlayerVsPlayer = "Strong";
		} else if (avg >= 0.250 && avg < 0.300) {
			this.batterStateOfPlayerVsPlayer = "Normal";
		} else if (avg >= 0.200 && avg < 0.250) {
			this.batterStateOfPlayerVsPlayer = "Weak";
		} else {
			this.batterStateOfPlayerVsPlayer = "Very Weak";
		}
		
		return batterStateOfPlayerVsPlayer;
	}
	
	public String getPitcherStateOfPlayerVsPlayer() {
		double ab = (double)super.getAb();
		double h = super.getB1b() + super.getB2b() + super.getB3b() + super.getHr();
		double avg = 0;
		if(ab != 0) {
			avg = h/ab;
		}
		
		if(avg >= 0.350) {
			this.pitcherStateOfPlayerVsPlayer = "Very Weak";
		} else if (avg >= 0.300 && avg < 0.350) {
			this.pitcherStateOfPlayerVsPlayer = "Weak";
		} else if (avg >= 0.250 && avg < 0.300) {
			this.pitcherStateOfPlayerVsPlayer = "Normal";
		} else if (avg >= 0.200 && avg < 0.250) {
			this.pitcherStateOfPlayerVsPlayer = "Strong";
		} else {
			this.pitcherStateOfPlayerVsPlayer = "Very Strong";
		}
		
		return pitcherStateOfPlayerVsPlayer;
	}

	public BigDecimal getPinPointStrikeRate() {
		BigDecimal pinPointTotalCnt = new BigDecimal(super.getPinPointBallCnt() + super.getPinPointStrikeCnt());
		BigDecimal pinPointStrikeCnt = new BigDecimal(super.getPinPointStrikeCnt());
		
		if(pinPointTotalCnt.intValue() != 0) {
			this.pinPointStrikeRate = pinPointStrikeCnt.divide(pinPointTotalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return pinPointStrikeRate;
	}

	public BigDecimal getPinPointBallRate() {
		BigDecimal pinPointTotalCnt = new BigDecimal(super.getPinPointBallCnt() + super.getPinPointStrikeCnt());
		BigDecimal pinPointBallCnt = new BigDecimal(super.getPinPointBallCnt());
		
		if(pinPointTotalCnt.intValue() != 0) {
			this.pinPointBallRate = pinPointBallCnt.divide(pinPointTotalCnt,3,BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return pinPointBallRate;
	}

	public BigDecimal getChangeupSJudgeRate() {
		BigDecimal totalJudgeCnt = new BigDecimal(super.getChangeupBJudgeCnt() + super.getChangeupSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getChangeupSJudgeCnt());
		
		if(totalJudgeCnt.intValue() != 0) {
			this.changeupSJudgeRate = sJudgeCnt.divide(totalJudgeCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return changeupSJudgeRate;
	}
	
	public BigDecimal getFastballSJudgeRate() {
		BigDecimal totalJudgeCnt = new BigDecimal(super.getFastballSJudgeCnt() + super.getFastballBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getFastballSJudgeCnt());
		
		if(totalJudgeCnt.intValue() != 0) {
			this.fastballSJudgeRate = sJudgeCnt.divide(totalJudgeCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return fastballSJudgeRate;
	}

	public BigDecimal getSliderSJudgeRate() {
		BigDecimal totalJudgeCnt = new BigDecimal(super.getSliderSJudgeCnt() + super.getSliderBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getSliderSJudgeCnt());
		
		if(totalJudgeCnt.intValue() != 0) {
			this.sliderSJudgeRate = sJudgeCnt.divide(totalJudgeCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return sliderSJudgeRate;
	}

	public BigDecimal getCurveSJudgeRate() {
		BigDecimal totalJudgeCnt = new BigDecimal(super.getCurveSJudgeCnt() + super.getCurveBJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getCurveSJudgeCnt());
		
		if(totalJudgeCnt.intValue() != 0) {
			this.curveSJudgeRate = sJudgeCnt.divide(totalJudgeCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		return curveSJudgeRate;
	}

	public BigDecimal getEtcballSJudgeRate() {
		BigDecimal totalJudgeCnt = new BigDecimal(super.getEtcballBJudgeCnt() + super.getEtcballSJudgeCnt());
		BigDecimal sJudgeCnt = new BigDecimal(super.getEtcballSJudgeCnt());
		
		if(totalJudgeCnt.intValue() != 0) {
			this.etcballSJudgeRate = sJudgeCnt.divide(totalJudgeCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return etcballSJudgeRate;
	}

	public BigDecimal getAddStealBaseRate() {
		BigDecimal addStealBaseChance = new BigDecimal(super.getAddStealBaseChance());
		BigDecimal addStealBase = new BigDecimal(super.getAddStealBase());
		
		if(addStealBaseChance.intValue() != 0) {
			this.addStealBaseRate = addStealBase.divide(addStealBaseChance, 1, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return addStealBaseRate;
	}

	public BigDecimal getB1bDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal b1b = new BigDecimal(super.getB1b());
		
		if(gameCnt.intValue() != 0) {
			this.b1bDividedGameCnt = b1b.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return b1bDividedGameCnt;
	}

	public BigDecimal getB2bDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal b2b = new BigDecimal(super.getB2b());
		
		if(gameCnt.intValue() != 0) {
			this.b2bDividedGameCnt = b2b.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return b2bDividedGameCnt;
	}

	public BigDecimal getB3bDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal b3b = new BigDecimal(super.getB3b());
		
		if(gameCnt.intValue() != 0) {
			this.b3bDividedGameCnt = b3b.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return b3bDividedGameCnt;
	}

	public BigDecimal getHrDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal hr = new BigDecimal(super.getHr());
		
		if(gameCnt.intValue() != 0) {
			this.hrDividedGameCnt = hr.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return hrDividedGameCnt;
	}

	public BigDecimal getHitDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal hit = new BigDecimal(super.getB1b() + super.getB2b() + super.getB3b() + super.getHr());
		
		if(gameCnt.intValue() != 0) {
			this.hitDividedGameCnt = hit.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return hitDividedGameCnt;
	}

	public BigDecimal getSuccessStealDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal successSteal = new BigDecimal(super.getSuccessSteal());
		
		if(gameCnt.intValue() != 0) {
			this.successStealDividedGameCnt = successSteal.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return successStealDividedGameCnt;
	}

	public BigDecimal getBbDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal bb = new BigDecimal(super.getBb());
		
		if(gameCnt.intValue() != 0) {
			this.bbDividedGameCnt = bb.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return bbDividedGameCnt;
	}

	public BigDecimal getkDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal k = new BigDecimal(super.getK());

		if(gameCnt.intValue() != 0) {
			this.kDividedGameCnt = k.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return kDividedGameCnt;
	}

	public BigDecimal getPlayerChangeSuccessRate() {
		BigDecimal playerChangeCnt = new BigDecimal(super.getPlayerChangeCnt());
		BigDecimal playerChangeSuccess = new BigDecimal(super.getPlayerChangeSuccessCnt());
		
		if(playerChangeCnt.intValue() != 0) {
			this.playerChangeSuccessRate = playerChangeSuccess.divide(playerChangeCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return playerChangeSuccessRate;
	}

	public BigDecimal getPlayerChangeFailRate() {
		BigDecimal playerChangeCnt = new BigDecimal(super.getPlayerChangeCnt());
		BigDecimal playerChangeFail = new BigDecimal(super.getPlayerChangeFailCnt());
		
		if(playerChangeCnt.intValue() != 0) {
			this.playerChangeFailRate = playerChangeFail.divide(playerChangeCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		return playerChangeFailRate;
	}

	public BigDecimal getBuntAttempRate() {
		BigDecimal tpa = new BigDecimal(super.getTpa());
		BigDecimal bunt = new BigDecimal(super.getBunt());
		
		if(bunt.intValue() != 0) {
			this.buntAttempRate = bunt.divide(tpa, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return buntAttempRate;
	}

	public BigDecimal getBuntSuccessRate() {
		BigDecimal bunt = new BigDecimal(super.getBunt());
		BigDecimal buntSuccessCnt = new BigDecimal(super.getBuntSuccessCnt());
		
		if(buntSuccessCnt.intValue() != 0) {
			this.buntSuccessRate = buntSuccessCnt.divide(bunt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return buntSuccessRate;
	}

	public BigDecimal getPlayerChangeRate() {
		BigDecimal playerChangeTotalCnt = new BigDecimal(super.getPlayerChangeTotalCnt());
		BigDecimal playerChangeCnt = new BigDecimal(super.getPlayerChangeCnt());
		
		if(playerChangeTotalCnt.intValue() != 0) {
			this.playerChangeRate = playerChangeCnt.divide(playerChangeTotalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return playerChangeRate;
	}

	public BigDecimal getErDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal er = new BigDecimal(super.getEr());
		
		if(gameCnt.intValue() != 0) {
			this.erDividedGameCnt = er.divide(gameCnt, 1, BigDecimal.ROUND_HALF_UP);
		}
		return erDividedGameCnt;
	}

	public BigDecimal getAccumPitchingBallDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal accumBallCnt = new BigDecimal(super.getAccumBallCnt());
		
		if(gameCnt.intValue() != 0) {
			this.accumPitchingBallDividedGameCnt = accumBallCnt.divide(gameCnt, 1, BigDecimal.ROUND_HALF_UP);
		}
		return accumPitchingBallDividedGameCnt;
	}

	public BigDecimal getAccumPitchingBallDividedBatterCnt() {
		BigDecimal batterCnt = new BigDecimal(super.getTpa());
		BigDecimal accumBallCnt = new BigDecimal(super.getAccumBallCnt());
		
		if(batterCnt.intValue() != 0) {
			this.accumPitchingBallDividedBatterCnt = accumBallCnt.divide(batterCnt, 1, BigDecimal.ROUND_HALF_UP);
		}
		
		return accumPitchingBallDividedBatterCnt;
	}

	public BigDecimal getPitchingInningDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal pitchingInning = new BigDecimal(super.getPitchingInning());
		
		if(gameCnt.intValue() !=  0) {
			this.pitchingInningDividedGameCnt = pitchingInning.divide(gameCnt, 1, BigDecimal.ROUND_HALF_UP);
		}
		return pitchingInningDividedGameCnt;
	}

	public BigDecimal getStealAttemptRate() {
		BigDecimal changeSteal = new BigDecimal(super.getChanceSteal());
		BigDecimal stealAttempt = new BigDecimal(super.getSuccessSteal() + super.getFailureSteal());
		
		if(changeSteal.intValue() != 0) {
			this.stealAttemptRate = stealAttempt.divide(changeSteal, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		return stealAttemptRate;
	}

	public BigDecimal getStealFailureRate() {
		BigDecimal stealAttempt = new BigDecimal(super.getSuccessSteal() + super.getFailureSteal());
		BigDecimal failure = new BigDecimal(super.getFailureSteal());
		
		if(stealAttempt.intValue() != 0) {
			this.stealFailureRate = failure.divide(stealAttempt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		return stealFailureRate;
	}

	public BigDecimal getStealSuccessRate() {
		BigDecimal stealAttempt = new BigDecimal(super.getSuccessSteal() + super.getFailureSteal());
		BigDecimal success = new BigDecimal(super.getSuccessSteal());
		
		if(stealAttempt.intValue() != 0) {
			this.stealSuccessRate = success.divide(stealAttempt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		return stealSuccessRate;
	}

	public BigDecimal getStealAttemptDivided9() {
		BigDecimal stealAttempt = new BigDecimal(super.getSuccessSteal() + super.getFailureSteal()).multiply(new BigDecimal(9));
		
		if(super.getDefenseInning() != null) {
			BigDecimal inning = new BigDecimal(super.getDefenseInning());
			if(inning.intValue() != 0) {
				this.stealAttemptDivided9 = stealAttempt.divide(inning, 1, BigDecimal.ROUND_HALF_UP);
			}
		}
		
		return stealAttemptDivided9;
	}

	public BigDecimal getStealSuccessDivided9() {
		BigDecimal success = new BigDecimal(super.getSuccessSteal()).multiply(new BigDecimal(9));
		
		if(super.getDefenseInning() != null) {
			BigDecimal inning = new BigDecimal(super.getDefenseInning());
			if(inning.intValue() != 0) {
				this.stealSuccessDivided9 = success.divide(inning, 1, BigDecimal.ROUND_HALF_UP);
			}
		}
		
		return stealSuccessDivided9;
	}

	public BigDecimal getStandingRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal standingCnt = new BigDecimal(super.getStandingCnt());
		
		if(totalHitting.intValue() != 0) {
			this.standingRate = standingCnt.divide(totalHitting, 1, BigDecimal.ROUND_HALF_UP);
		}
		return standingRate;
	}

	public BigDecimal getContactRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal contactCnt = new BigDecimal(super.getContactCnt());
		
		if(totalHitting.intValue() != 0) {
			this.contactRate = contactCnt.divide(totalHitting, 1, BigDecimal.ROUND_HALF_UP);
		}
		
		return contactRate;
	}

	public BigDecimal getSwingRate() {
		BigDecimal totalHitting = new BigDecimal(super.getTotalHitting());
		BigDecimal swingCnt = new BigDecimal(super.getSwingCnt());
		
		if(totalHitting.intValue() != 0) {
			this.swingRate = swingCnt.divide(totalHitting, 1, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return swingRate;
	}

	public BigDecimal getFastBallDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal fastBallCnt = new BigDecimal(super.getFastBallCnt());
		
		if(gameCnt.intValue() != 0) {
			this.fastBallDividedGameCnt = fastBallCnt.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		return fastBallDividedGameCnt;
	}

	public BigDecimal getWildPitchDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal wildPitchCnt = new BigDecimal(super.getWildPitchCnt());
		
		if(gameCnt.intValue() != 0) {
			this.wildPitchDividedGameCnt = wildPitchCnt.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return wildPitchDividedGameCnt;
	}

	public BigDecimal getFastBallDivided9() {
		BigDecimal fastBallCnt = new BigDecimal(super.getFastBallCnt()).multiply(new BigDecimal(9));
		 
		if(super.getDefenseInning() != null) {
			BigDecimal defeneseInning = new BigDecimal(super.getDefenseInning());
			if(defeneseInning.intValue() != 0) {
				this.fastBallDivided9 = fastBallCnt.divide(defeneseInning, 1, BigDecimal.ROUND_HALF_UP);
			}
		}
		
		return fastBallDivided9;
	}

	public BigDecimal getWildPitchDivided9() {
		BigDecimal wildPitch = new BigDecimal(super.getWildPitchCnt()).multiply(new BigDecimal(9));
		 
		if(super.getDefenseInning() != null) {
			BigDecimal defeneseInning = new BigDecimal(super.getDefenseInning());
			if(defeneseInning.intValue() != 0) {
				this.wildPitchDivided9 = wildPitch.divide(defeneseInning, 1, BigDecimal.ROUND_HALF_UP);
			}
		}
		
		return wildPitchDivided9;
	}

	public BigDecimal getBlockingDivided9() {		
		this.blockingDivided9 = this.getFastBallDivided9().add(this.getWildPitchDivided9());
		return blockingDivided9;
	}

	public BigDecimal getCatcherAvgEr() {
		BigDecimal defenseInning = new BigDecimal(this.getPlayInning());
		BigDecimal er = new BigDecimal(super.getEr()).multiply(new BigDecimal(9));
		
		if(defenseInning.intValue() != 0) {
			this.catcherAvgEr = er.divide(defenseInning, 1, BigDecimal.ROUND_HALF_UP);
		}
		
		return catcherAvgEr;
	}

	public BigDecimal getStealAttemptCnt() {
		this.stealAttemptCnt = new BigDecimal(super.getSuccessSteal()).add(new BigDecimal(super.getFailureSteal()));
		
		return stealAttemptCnt;
	}

	public BigDecimal getCatcherAvgPitchingBallCnt() {
		BigDecimal totalCatchingCnt = new BigDecimal(super.getTotalCatchingCnt());
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		
		if(gameCnt.intValue() != 0) {
			this.catcherAvgPitchingBallCnt = totalCatchingCnt.divide(gameCnt, 1, BigDecimal.ROUND_HALF_UP);
		}
		return catcherAvgPitchingBallCnt;
	}

	public BigDecimal getCatcherKRate() {
		return catcherKRate;
	}

	public BigDecimal getCatcherAvgSelectionPitcherBallCnt() {
		BigDecimal selectionPitcherAccumPitchingBallCnt = new BigDecimal(super.getSelectionPitcherAccumPitchingBallCnt());
		BigDecimal selectionPitcherCnt = new BigDecimal(super.getSelectionPitcherCnt());
		
		if(selectionPitcherCnt.intValue() != 0) {
			this.catcherAvgSelectionPitcherBallCnt = selectionPitcherAccumPitchingBallCnt.divide(selectionPitcherCnt, 1, BigDecimal.ROUND_HALF_UP);
		}
		
		return catcherAvgSelectionPitcherBallCnt;
	}

	public BigDecimal getCatcherAvgRescuePitcherBallCnt() {
		BigDecimal rescuePitcherAccumPitchingBallCnt = new BigDecimal(super.getRescuePitcherAccumPitchingBallCnt());
		BigDecimal rescuePitcherCnt = new BigDecimal(super.getRescuePitcherCnt());
		
		if(rescuePitcherCnt.intValue() != 0) {
			this.catcherAvgRescuePitcherBallCnt = rescuePitcherAccumPitchingBallCnt.divide(rescuePitcherCnt, 1, BigDecimal.ROUND_HALF_UP);
		}
		
		return catcherAvgRescuePitcherBallCnt;
	}

	public BigDecimal getZoneFastBallRate() {
		BigDecimal zoneTotalBallCnt = new BigDecimal(super.getFastBallCnt() 
														+ super.getSlideCnt()
														+ super.getCurveCnt()
														+ super.getChangeUpCnt()
														+ super.getEtcBallCnt());
		BigDecimal zoneFastBallCnt = new BigDecimal(super.getFastBallCnt());
		
		if(zoneTotalBallCnt.intValue() != 0) {
			this.zoneFastBallRate = zoneFastBallCnt.divide(zoneTotalBallCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zoneFastBallRate;
	}

	public BigDecimal getZoneSlideRate() {
		BigDecimal zoneTotalBallCnt = new BigDecimal(super.getFastBallCnt() 
														+ super.getSlideCnt()
														+ super.getCurveCnt()
														+ super.getChangeUpCnt()
														+ super.getEtcBallCnt());
		BigDecimal zoneSlideCnt = new BigDecimal(super.getSlideCnt());
		
		if(zoneTotalBallCnt.intValue() != 0) {
			this.zoneSlideRate = zoneSlideCnt.divide(zoneTotalBallCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zoneSlideRate;
	}

	public BigDecimal getZoneCurveRate() {
		BigDecimal zoneTotalBallCnt = new BigDecimal(super.getFastBallCnt() 
														+ super.getSlideCnt()
														+ super.getCurveCnt()
														+ super.getChangeUpCnt()
														+ super.getEtcBallCnt());
		BigDecimal zoneCurveCnt = new BigDecimal(super.getCurveCnt());
		
		if(zoneTotalBallCnt.intValue() != 0) {
			this.zoneCurveRate = zoneCurveCnt.divide(zoneTotalBallCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zoneCurveRate;
	}

	public BigDecimal getZoneChangeUpRate() {
		BigDecimal zoneTotalBallCnt = new BigDecimal(super.getFastBallCnt() 
														+ super.getSlideCnt()
														+ super.getCurveCnt()
														+ super.getChangeUpCnt()
														+ super.getEtcBallCnt());
		BigDecimal zoneChangeUpCnt = new BigDecimal(super.getChangeUpCnt());
		
		if(zoneTotalBallCnt.intValue() != 0) {
			this.zoneChangeUpRate = zoneChangeUpCnt.divide(zoneTotalBallCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zoneChangeUpRate;
	}

	public BigDecimal getZoneEtcBallRate() {
		BigDecimal zoneTotalBallCnt = new BigDecimal(super.getFastBallCnt() 
														+ super.getSlideCnt()
														+ super.getCurveCnt()
														+ super.getChangeUpCnt()
														+ super.getEtcBallCnt());
		BigDecimal zoneEtcBallCnt = new BigDecimal(super.getEtcBallCnt());
		
		if(zoneTotalBallCnt.intValue() != 0) {
			this.zoneEtcBallRate = zoneEtcBallCnt.divide(zoneTotalBallCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zoneEtcBallRate;
	}

	public BigDecimal getZonePitchingBallRate() {
		BigDecimal totalZoneBallNum = new BigDecimal(super.getTotalZoneBallNum());
		BigDecimal zoneBallNum = new BigDecimal(super.getZoneBallNum());
		
		if(totalZoneBallNum.intValue() != 0) {
			this.zonePitchingBallRate = zoneBallNum.divide(totalZoneBallNum, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zonePitchingBallRate;
	}

	public BigDecimal getPinchHitSuccRate() {
		BigDecimal pinchHitSuccCnt = new BigDecimal(super.getPinchHitSucc());
		BigDecimal pinchHitTotCnt = new BigDecimal(super.getPinchHitFail() + super.getPinchHitSucc());
		
		if(pinchHitTotCnt.intValue() != 0) {
			this.pinchHitSuccRate = pinchHitSuccCnt.divide(pinchHitTotCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return pinchHitSuccRate;
	}

	public BigDecimal getrDividedGameCnt() {
		BigDecimal gameCnt = new BigDecimal(super.getGameCnt());
		BigDecimal r = new BigDecimal(super.getR());
		
		if(gameCnt.intValue() != 0) {
			this.rDividedGameCnt = r.divide(gameCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return rDividedGameCnt;
	}

	public BigDecimal getPlayerWpa() {
		if(this.playerWpa != null) return playerWpa;
		
		this.playerWpa = super.getAfterWe().subtract(super.getBeforeWe());
		
		if(this.playerWpa == null) this.playerWpa = new BigDecimal(0);
		
		return playerWpa;
	}
	
	public void setPlayerWpa(BigDecimal playerWpa) {
		this.playerWpa = playerWpa;
	}

	public BigDecimal getReliefSuccRate() {
		BigDecimal reliefSuccCnt = new BigDecimal(super.getReliefSucc());
		BigDecimal reliefTotalCnt = new BigDecimal(super.getReliefFail() + super.getReliefSucc());
		
		if(reliefTotalCnt.intValue() != 0) {
			this.reliefSuccRate = reliefSuccCnt.divide(reliefTotalCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return reliefSuccRate;
	}

	public BigDecimal getBuntsRate() {
		BigDecimal buntsCnt = new BigDecimal(super.getBuntsCnt());
		BigDecimal totalBuntsCnt = new BigDecimal(super.getTotalBuntsCnt());
		
		if(totalBuntsCnt.intValue() != 0) {
			this.buntsRate = buntsCnt.divide(totalBuntsCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return buntsRate;
	}

	public BigDecimal getStrongAttackRate() {
		BigDecimal strongAttackCnt = new BigDecimal(super.getStrongAttackCnt());
		BigDecimal totalStrongAttackCnt = new BigDecimal(super.getTotalStrongAttackCnt());
		
		if(totalStrongAttackCnt.intValue() != 0) {
			this.strongAttackRate = strongAttackCnt.divide(totalStrongAttackCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		return strongAttackRate;
	}



	public BigDecimal getWoba() {
		BigDecimal calcedChild = new BigDecimal(super.getBb() * wbb + super.getHbp() * whbp
				+ super.getB1b() * wb1b + super.getB2b() * wb2b + super.getB3b() * wb3b + super.getHr() * whr);
		BigDecimal calcedMother = new BigDecimal(getAb() + getBb() - getIbb() + getSf() + getHbp());
		
		if(calcedMother.intValue() != 0) {
			this.woba = calcedChild.divide(calcedMother, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return woba;
	}
	
	public BigDecimal getWrc() {
		BigDecimal leagueTotalTpa = new BigDecimal(super.getLeagueTotalTpa());
		BigDecimal firstCalced = getWoba().subtract(super.getLeagueAvgWoba());
		
		if(leagueTotalTpa.intValue() != 0) {
			firstCalced = firstCalced.divide(new BigDecimal(1.15), 3, BigDecimal.ROUND_HALF_UP);
			
			BigDecimal secondCalced = new BigDecimal(super.getLeagueTotalR());
			secondCalced = secondCalced.divide(leagueTotalTpa, 3, BigDecimal.ROUND_HALF_UP);
			
			this.wrc = firstCalced.add(secondCalced);
			this.wrc = wrc.multiply(new BigDecimal(super.getTpa()));
		}
		
		return wrc;
	}

	public BigDecimal getWraa() {
		if(getWobaScale().doubleValue() != 0) {
			this.wraa = getWoba().subtract(super.getLeagueAvgWoba());
			this.wraa = this.wraa.divide(new BigDecimal(1.15), 3, BigDecimal.ROUND_HALF_UP);
			this.wraa = this.wraa.multiply(new BigDecimal(super.getTpa()));
		}
		
		return wraa;
	}

	public BigDecimal getRplus() {
		BigDecimal calcedChild = new BigDecimal(wbb * (super.getBb() - super.getIbb()) + whbp * super.getHbp() + wb1b * super.getB1b()
													+ wb2b * super.getB2b() + wb3b * super.getB3b() + whr * super.getHr());
		BigDecimal cacledMother = new BigDecimal(super.getB1b() + super.getB2b() + super.getB3b() + super.getHr() 
													+ super.getBb() - super.getIbb() + super.getHbp());
		
		if(cacledMother.intValue() != 0) {
			this.rplus = calcedChild.divide(cacledMother, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return rplus;
	}

	public BigDecimal getRminus() {
		BigDecimal calcedChild = new BigDecimal(wbb * (super.getBb() - super.getIbb()) + whbp * super.getHbp() + wb1b * super.getB1b()
													+ wb2b * super.getB2b() + wb3b * super.getB3b() + whr * super.getHr());
		BigDecimal calcedMother = new BigDecimal(super.getAb() - (super.getB1b() + super.getB2b() + super.getB3b() + super.getHr()) + super.getSf());
		
		if(calcedMother.intValue() != 0) {
			this.rminus = calcedChild.divide(calcedMother, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return rminus;
	}

	public BigDecimal getWobaScale() {
		BigDecimal sumAbBbSfHbp = new BigDecimal(getAb() + getBb() + getSf() + getHbp());
		BigDecimal calcedChild = super.getLeagueAvgObp();
		
		if(sumAbBbSfHbp.intValue() != 0) {
			BigDecimal calcedMother = new BigDecimal(super.getBb() * wbb + super.getHbp() * whbp
					+ super.getB1b() * wb1b + super.getB2b() * wb2b + super.getB3b() * wb3b + super.getHr() * whr)
			.divide(new BigDecimal(getAb() + getBb() + getSf() + getHbp()), 3, BigDecimal.ROUND_HALF_UP);
			
			if(calcedMother.doubleValue() != 0) {
				this.wobaScale = calcedChild.divide(calcedMother, 3, BigDecimal.ROUND_HALF_UP);
			}
		}

		return wobaScale;
	}

	public BigDecimal getWrcPlus() {
		BigDecimal leagueTotalTpa = new BigDecimal(super.getLeagueTotalTpa());
		BigDecimal tpa = new BigDecimal(super.getTpa());
		BigDecimal wraaDividedPa;
		BigDecimal leagueTotRDividedLeagueTotTpa;
		
		if(tpa.intValue() != 0) {
			wraaDividedPa = getWraa().divide(new BigDecimal(super.getTpa()), 3, BigDecimal.ROUND_HALF_UP);
			if(leagueTotalTpa.intValue() != 0) {
				leagueTotRDividedLeagueTotTpa = new BigDecimal(super.getLeagueTotalR()).divide(leagueTotalTpa, 3, BigDecimal.ROUND_HALF_UP);
				
				if(leagueTotRDividedLeagueTotTpa.doubleValue() != 0) {
					BigDecimal result = wraaDividedPa.divide(leagueTotRDividedLeagueTotTpa, 3, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1));
					this.wrcPlus = result.multiply(oneHundred);
				}
			}
		}

		return wrcPlus;
	}

	public BigDecimal getWsb() {
		BigDecimal calcedSb = new BigDecimal(super.getSuccessSteal() * RUN_SB);
		BigDecimal calcedCs = new BigDecimal(super.getFailureSteal() * RUN_CS);
		
		BigDecimal calcedChild = calcedSb.add(calcedCs);
		
		BigDecimal outBase = new BigDecimal(super.getB1b() + super.getBb() + super.getHbp() + super.getIbb());
		
		BigDecimal calcedMother = outBase.multiply(super.getLeagueWsb());

		this.wsb = calcedChild.subtract(calcedMother).setScale(3, BigDecimal.ROUND_HALF_UP);
		
		return wsb;
	}
	
	public BigDecimal getLgwSb() {
		BigDecimal calcedSb = new BigDecimal(super.getSuccessSteal() * RUN_SB);
		BigDecimal calcedCs = new BigDecimal(super.getFailureSteal() * RUN_CS);
		
		BigDecimal calcedMother = new BigDecimal(super.getB1b() + super.getBb() + super.getHbp() + super.getIbb());
		
		BigDecimal SbSumCs = calcedSb.add(calcedCs);
		
		if(calcedMother.intValue() != 0) {
			this.lgwSb = SbSumCs.divide(calcedMother, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return lgwSb;
	}

	public BigDecimal getBatter_war() {
		BigDecimal wraa = this.getWraa();
		
		return batter_war;
	}

	public BigDecimal getoSwingRate() {
		BigDecimal oPitchingCnt = new BigDecimal(super.getoPitchingCnt());
		BigDecimal oSwingCnt = new BigDecimal(super.getoSwingCnt());
		
		if(oPitchingCnt.intValue() != 0) {
			this.oSwingRate = 
					oSwingCnt.divide(oPitchingCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return oSwingRate;
	}


	public BigDecimal getzSwingRate() {
		BigDecimal zPitchingCnt = new BigDecimal(super.getzPitchingCnt());
		BigDecimal zSwingCnt = new BigDecimal(super.getzSwingCnt());
		
		if(zPitchingCnt.intValue() != 0) {
			this.zSwingRate = 
					zSwingCnt.divide(zPitchingCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zSwingRate;
	}


	public BigDecimal getSwingRate2() {
		BigDecimal pitchingCnt = new BigDecimal(super.getPitchingCnt());
		BigDecimal swingCnt = new BigDecimal(super.getSwingCnt());
		
		if(pitchingCnt.intValue() != 0) {
			this.swingRate2 = 
					swingCnt.divide(pitchingCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return swingRate2;
	}


	public BigDecimal getoContactRate() {
		BigDecimal oContactCnt = new BigDecimal(super.getoContactCnt());
		BigDecimal oSwingCnt = new BigDecimal(super.getoSwingCnt());
		
		if(oSwingCnt.intValue() != 0) {
			this.oContactRate = 
					oContactCnt.divide(oSwingCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return oContactRate;
	}


	public BigDecimal getzContactRate() {
		BigDecimal zContactCnt = new BigDecimal(super.getzContactCnt());
		BigDecimal zSwingCnt = new BigDecimal(super.getzSwingCnt());
		
		if(zSwingCnt.intValue() != 0) {
			this.zContactRate = 
					zContactCnt.divide(zSwingCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zContactRate;
	}


	public BigDecimal getZoneRate() {
		BigDecimal strikePitchingCnt = new BigDecimal(super.getStrikePitchingCnt());
		BigDecimal pitchingCnt = new BigDecimal(super.getPitchingCnt());
		
		if(pitchingCnt.intValue() != 0) {
			this.zoneRate = 
					strikePitchingCnt.divide(pitchingCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return zoneRate;
	}

	public BigDecimal getContactRate2() {
		BigDecimal contactCnt = new BigDecimal(super.getContactCnt());
		BigDecimal swingCnt = new BigDecimal(super.getSwingCnt());
		
		if(contactCnt.intValue() != 0) {
			this.contactRate2 = 
					contactCnt.divide(swingCnt, 3, BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
		}
		
		return contactRate2;
	}

	private double spdElem1() {
		double number = 
				((double) (super.getSuccessSteal() + 3) / 
				 (double) (super.getSuccessSteal() + super.getFailureSteal() + 7) - 0.4) * 20;
		
		return number;
	}
	
	private double spdElem2() {
		double number = 0;
		if(super.getB1b() + super.getBb() + super.getHbp() != 0) 
			 number = Math.sqrt((double) (super.getSuccessSteal() + super.getFailureSteal()) /
						        (double) (super.getB1b() + super.getBb() + super.getHbp())) / 0.07;
		
		
		return number;
	}
	
	private double spdElem3() {
		double number = 0;
		if(super.getAb() - super.getHr() - super.getSo() != 0) 
			number = 
					((double) super.getB3b() / (double) (super.getAb() - super.getHr() - super.getSo())) / 0.07;

		return number;
	}
	
	private double spdElem4() {
		double number = 0;
		if(super.getB1b() + super.getB2b() + super.getB3b() + super.getHr()
								+ super.getBb() - super.getHr() - super.getHbp() != 0) 
			number = 
					((double) (super.getR() - super.getHr()) / 
					 (double) (super.getB1b() + super.getB2b() + super.getB3b() + super.getHr()
									+ super.getBb() - super.getHr() - super.getHbp()) - 0.1) / 0.04;
		
		return number;
	}
	
	public BigDecimal getSpd() {
		BigDecimal spdElementSum = 
					new BigDecimal(spdElem1() + spdElem2() + spdElem3() + spdElem4());
		
		this.spd = spdElementSum.divide(new BigDecimal(4), 1, BigDecimal.ROUND_HALF_UP);
		
		return spd;
	}

	public BigDecimal getLob() {
		int hit = super.getB1b() + super.getB2b() + super.getB3b() + super.getHr();
		
		BigDecimal sonValue = new BigDecimal((hit + super.getBb() + super.getHbp() - super.getR()));
		BigDecimal motherValue = new BigDecimal((double) (hit + super.getBb() + super.getHbp() - (1.4 * super.getHr()))); 
		
		if(motherValue.doubleValue() != 0.0) {
			this.lob = sonValue.divide(motherValue, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return lob;
	}

	public BigDecimal getBb9() {
		BigDecimal bb = new BigDecimal(super.getBb());
		BigDecimal ip = this.getIp();
		
		if(ip.doubleValue() != 0.0) {
			this.bb9 = 
					bb.multiply(new BigDecimal(9)).divide(ip, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return bb9;
	}

	public BigDecimal getHr9() {
		BigDecimal hr = new BigDecimal(super.getHr());
		BigDecimal ip = this.getIp();
		
		if(ip.doubleValue() != 0.0) {
			this.hr9 = 
					hr.multiply(totInning).divide(ip, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return hr9;
	}

	public BigDecimal getGdpSuccRate() {
		BigDecimal gdpChanceCnt = new BigDecimal(super.getGdpChanceCnt());
		BigDecimal gdp = new BigDecimal(super.getGdp());
		
		if(gdpChanceCnt.intValue() != 0) {
			this.gdpSuccRate = 
					gdp.divide(gdpChanceCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return gdpSuccRate;
	}

	public BigDecimal getAddedRunAllowRate() {
		BigDecimal addedRunChanceCnt = new BigDecimal(super.getAddedRunChanceCnt());
		BigDecimal addedRunCnt = new BigDecimal(super.getAddedRunCnt());
		
		if(addedRunChanceCnt.intValue() != 0) {
			this.addedRunAllowRate = addedRunCnt.divide(addedRunChanceCnt, 3, BigDecimal.ROUND_HALF_UP);
		}
		
		return addedRunAllowRate;
	}

	public BigDecimal getErrRate() {
		BigDecimal mother = new BigDecimal(super.getAoCnt() + super.getPoCnt() + super.getErr());
		BigDecimal son = new BigDecimal(super.getErr());
		
		if(mother.intValue() != 0) 
			this.errRate = son.divide(mother, 3, BigDecimal.ROUND_HALF_UP);
		
		return errRate;
	}
	
}
