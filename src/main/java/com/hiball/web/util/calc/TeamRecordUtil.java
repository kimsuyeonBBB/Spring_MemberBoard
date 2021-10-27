package com.hiball.web.util.calc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.excpeiton.ReceivePropertyException;
import com.hiball.web.util.columns.Columns;
import com.hiball.web.util.convertor.ValueConvertor;

public class TeamRecordUtil {
    private static final String superiority = "superiority";
    private static final String tie = "tie";
    private static final String inferiority = "inferiority";
    private static final int homeTeamWin = 1;
    private static final int homeTeamDraw = 0;
    private static final int homeTeamLose = -1;
    private static final int awayTeamWin = -1;
    private static final int awayTeamDraw = 0;
    private static final int awayTeamLose = 1;

    public static GameRecord calcTeamGameRecords(GameRecord motherRecord) {
	List<GameRecord> sunRecords = motherRecord.getRecords();
	motherRecord.setGameCnt(sunRecords.size());

	for (GameRecord record : sunRecords) {
	    motherRecord.setWin(record.getWin() + motherRecord.getWin());
	    motherRecord.setLose(record.getLose() + motherRecord.getLose());
	    motherRecord.setDraw(record.getDraw() + motherRecord.getDraw());

	    motherRecord = calcWinningOrLosingStreak(motherRecord, record);
	}

	motherRecord.setRecords(null);

	return motherRecord;
    }

    private static GameRecord calcWinningOrLosingStreak(GameRecord motherRecord, GameRecord sunRecord) {
	String flag = "";
	int wStreak = 0;
	int lStreak = 0;

	if (sunRecord != null && sunRecord.getWin() != 0) {
	    wStreak = motherRecord.getWinningStreak();
	    motherRecord.setWinningStreak(++wStreak);
	    if (flag.isEmpty() || "lose".equals(flag)) {
		flag = "win";
		motherRecord.setLosingStreak(0);
	    }
	} else if (sunRecord != null && sunRecord.getLose() != 0) {
	    lStreak = motherRecord.getLosingStreak();
	    motherRecord.setLosingStreak(++lStreak);

	    if (flag.isEmpty() || "win".equals(flag)) {
		flag = "lose";
		motherRecord.setWinningStreak(0);
	    }
	}

	return motherRecord;
    }

    /**
     * getStreakCheck()은 1 / 0 / -1을 리턴하며 1의 경우 승리, 0의 경우 무, -1의 경우 패이다.
     * 
     * @author CWH
     */
    public static String calcWinningOrLosingStreak(List<GameRecord> recordList) {
	String returnStr;
	int flagValue = -100;
	int countValue = 0;

	for (GameRecord record : recordList) {
	    if (record.getStreakCheck() == 0)
		continue;

	    if (flagValue == -100) {
		flagValue = record.getStreakCheck();
		countValue += 1;
		continue;
	    } else if (flagValue != record.getStreakCheck()) {
		break;
	    } else {
		countValue += 1;
	    }
	}

	if (flagValue > 0) {
	    returnStr = countValue + "승";
	} else if (flagValue == 0) {
	    returnStr = countValue + "무";
	} else {
	    returnStr = countValue + "패";
	}

	return returnStr;
    }

    /**
     * getStreakCheck()은 1 / 0 / -1을 리턴하며 1의 경우 승리, 0의 경우 무, -1의 경우 패이다.
     * 
     * @author CWH
     */
    public static String calcLasted10GamesWinOrLoseCnt(List<GameRecord> recordList) {
	int loopSize = 0;

	int winValue = 0;
	int drawValue = 0;
	int loseValue = 0;

	if (recordList.size() < 10) {
	    loopSize = recordList.size();
	} else {
	    loopSize = 10;
	}

	for (int i = 0; i < loopSize; i++) {
	    GameRecord record = recordList.get(i);

	    int streakCheckValue = record.getStreakCheck();

	    if (streakCheckValue == 1) {
		winValue++;
	    } else if (streakCheckValue == 0) {
		drawValue++;
	    } else {
		loseValue++;
	    }
	}

	return winValue + "승 " + drawValue + "무 " + loseValue + "패";
    }

    public static double calcGameBehind(GameRecord fstTeamRecord, GameRecord otherTeamRecord) {
	int fstTeamWinCnt = fstTeamRecord.getWin();
	int fstTeamLoseCnt = fstTeamRecord.getLose();

	int otherTeamWinCnt = otherTeamRecord.getWin();
	int otherTeamLostCnt = otherTeamRecord.getLose();

	return ((fstTeamWinCnt - otherTeamWinCnt) * 0.5) + ((fstTeamLoseCnt - otherTeamLostCnt) * -0.5);
    }

    public static Map<String, Map<String, GameRecord>> calcInningWinRateByGettedScore(List<GameRecord> recordList) {
	Map<String, Map<String, GameRecord>> resultMap = new HashMap<>();

	int accumAwayR = 0;
	int accumHomeR = 0;
	String gameInfoId = "";
	int index = 0;
	int winDrawLose = 0;

	for (GameRecord record : recordList) {
	    if (index == 0) {
		index++;
		gameInfoId = record.getGameInfoId();
	    }

	    String homeOrAway = record.getHomeOrAway();

	    int awayR = record.getAwayR();
	    int homeR = record.getHomeR();

	    if (record.getWinDrawLose() != -100) {
		winDrawLose = record.getWinDrawLose();
	    }

	    String inning = record.getInning();

	    if (gameInfoId != record.getGameInfoId()) {
		gameInfoId = record.getGameInfoId();
		accumAwayR = 0;
		accumHomeR = 0;
		accumAwayR += awayR;
		accumHomeR += homeR;
	    } else {
		accumAwayR += awayR;
		accumHomeR += homeR;
	    }

	    // choice team이 Away일때
	    if (homeOrAway.equals("AWAY")) {
		if (accumAwayR > accumHomeR) {
		    setInningMap(inning, winDrawLose, homeOrAway, superiority, resultMap);
		} else if (accumAwayR == accumHomeR) {
		    setInningMap(inning, winDrawLose, homeOrAway, tie, resultMap);
		} else {
		    setInningMap(inning, winDrawLose, homeOrAway, inferiority, resultMap);
		}
	    } else {
		if (accumAwayR < accumHomeR) {
		    setInningMap(inning, winDrawLose, homeOrAway, superiority, resultMap);
		} else if (accumAwayR == accumHomeR) {
		    setInningMap(inning, winDrawLose, homeOrAway, tie, resultMap);
		} else {
		    setInningMap(inning, winDrawLose, homeOrAway, inferiority, resultMap);
		}
	    }
	}

	return resultMap;
    }

    /**
     * 
     * @param inning
     * @param winDrawLose -> {1, 0 , -1} = 1이면 홈팀 승리 , 0이면 동점, -1 이면 원정팀 승
     * @param homeOrAway  -> {HOME ,AWAY}
     * @param key         -> {superiority, tie, inferiority}
     * @param resultMap
     * 
     */
    private static void setInningMap(String inning, int winDrawLose, String homeOrAway, String key,
	    Map<String, Map<String, GameRecord>> resultMap) {
	// key값이 resultMap에 없다면 value를 생성하고 값을 set한 후 put
	if (!resultMap.containsKey(key)) {
	    Map<String, GameRecord> inningMap = new HashMap<>();

	    GameRecord iRecord = new GameRecord();

	    if (homeOrAway.equals("AWAY")) {
		if (winDrawLose == awayTeamWin) {
		    iRecord.setWin(1);
		} else if (winDrawLose == awayTeamDraw) {
		    iRecord.setDraw(1);
		} else if (winDrawLose == awayTeamLose) {
		    iRecord.setLose(1);
		}
	    } else if (homeOrAway.equals("HOME")) {
		if (winDrawLose == homeTeamWin) {
		    iRecord.setWin(1);
		} else if (winDrawLose == homeTeamDraw) {
		    iRecord.setDraw(1);
		} else if (winDrawLose == homeTeamLose) {
		    iRecord.setLose(1);
		}
	    }

	    inningMap.put(inning, iRecord);

	    resultMap.put(key, inningMap);
	} else {
	    Map<String, GameRecord> inningMap = resultMap.get(key);

	    if (!inningMap.containsKey(inning)) {
		GameRecord iRecord = new GameRecord();

		if (homeOrAway.equals("AWAY")) {
		    if (winDrawLose == awayTeamWin) {
			iRecord.setWin(1);
		    } else if (winDrawLose == awayTeamDraw) {
			iRecord.setDraw(1);
		    } else if (winDrawLose == awayTeamLose) {
			iRecord.setLose(1);
		    }
		} else if (homeOrAway.equals("HOME")) {
		    if (winDrawLose == homeTeamWin) {
			iRecord.setWin(1);
		    } else if (winDrawLose == homeTeamDraw) {
			iRecord.setDraw(1);
		    } else if (winDrawLose == homeTeamLose) {
			iRecord.setLose(1);
		    }
		}

		inningMap.put(inning, iRecord);
	    } else {
		GameRecord record = inningMap.get(inning);

		if (homeOrAway.equals("AWAY")) {
		    if (winDrawLose == awayTeamWin) {
			record.setWin(record.getWin() + 1);
		    } else if (winDrawLose == awayTeamDraw) {
			record.setDraw(record.getDraw() + 1);
		    } else if (winDrawLose == awayTeamLose) {
			record.setLose(record.getLose() + 1);
		    }
		} else if (homeOrAway.equals("HOME")) {
		    if (winDrawLose == homeTeamWin) {
			record.setWin(record.getWin() + 1);
		    } else if (winDrawLose == homeTeamDraw) {
			record.setDraw(record.getDraw() + 1);
		    } else if (winDrawLose == homeTeamLose) {
			record.setLose(record.getLose() + 1);
		    }
		}

		inningMap.put(inning, record);

	    }

	}
    }

    public static void calcPitchingRegularInningFilter(int teamGameCnt, List<GameRecord> teamPlayerList) {
	int basedTeamGameCnt = teamGameCnt / 2;

	for (Iterator<GameRecord> iterator = teamPlayerList.iterator(); iterator.hasNext();) {
	    GameRecord record = iterator.next();
	    Double playInning = Double.valueOf(record.getPlayInning());

	    if (playInning < basedTeamGameCnt) {
		iterator.remove();
	    }
	}
    }

    @SuppressWarnings("all")
    public static void calcBattingRegularTpaFilter(int teamGameCnt, List<GameRecord> teamPlayerList) {
	BigDecimal teamGameCntBd = new BigDecimal(teamGameCnt);
	int regularTap = teamGameCntBd.multiply(new BigDecimal(3.1)).ROUND_HALF_UP;

	for (Iterator<GameRecord> iterator = teamPlayerList.iterator(); iterator.hasNext();) {
	    GameRecord record = iterator.next();

	    if (regularTap > record.getTpa()) {
		iterator.remove();
	    }
	}
    }

    @SuppressWarnings("all")
    public static void calcBattingRegularTpaFilter2(int teamGameCnt, List<Map<String, Object>> teamPlayerList) {
	BigDecimal teamGameCntBd = new BigDecimal(teamGameCnt);
	int regularTap = teamGameCntBd.multiply(new BigDecimal(3.1)).ROUND_HALF_UP;

	for (Iterator<Map<String, Object>> iterator = teamPlayerList.iterator(); iterator.hasNext();) {
	    Map<String, Object> record = iterator.next();

	    Integer playerTpa = Integer.parseInt(String.valueOf(record.get(Columns.TPA)));

	    if (regularTap > playerTpa) {
		iterator.remove();
	    }
	}
    }

    @SuppressWarnings("all")
    public static void calcBattingRegularTpaFilter3(int teamGameCnt, List<Map<ColumnsEnum, Object>> teamPlayerList) {
	BigDecimal teamGameCntBd = new BigDecimal(teamGameCnt);
	BigDecimal regularTapBd = teamGameCntBd.multiply(new BigDecimal(3.1));
	double regularTap = regularTapBd.doubleValue();

	for (Iterator<Map<ColumnsEnum, Object>> iterator = teamPlayerList.iterator(); iterator.hasNext();) {
	    Map<ColumnsEnum, Object> record = iterator.next();

	    double playerTpa = Double.parseDouble(String.valueOf(record.get(ColumnsEnum.tpa)));

	    if (regularTap > playerTpa) {
		iterator.remove();
	    }
	}
    }

    public static void calcPitchingRegularInningFilter2(int teamGameCnt, List<Map<String, Object>> teamPlayerList) {
	int basedTeamGameCnt = teamGameCnt / 2;

	for (Iterator<Map<String, Object>> iterator = teamPlayerList.iterator(); iterator.hasNext();) {
	    Map<String, Object> record = iterator.next();
	    Double playInning = ValueConvertor.settingValueAsDouble(record.get(Columns.PLAY_INNING));

	    if (playInning < basedTeamGameCnt) {
		iterator.remove();
	    }
	}
    }

    public static void calcPitchingRegularInningFilter3(int teamGameCnt, List<Map<ColumnsEnum, Object>> teamPlayerList)
	    throws ReceivePropertyException {
	if (!teamPlayerList.get(0).containsKey(ColumnsEnum.playInning)) {
	    throw new ReceivePropertyException(ColumnsEnum.playInning);
	}

	int basedTeamGameCnt = teamGameCnt / 2;

	for (Iterator<Map<ColumnsEnum, Object>> iterator = teamPlayerList.iterator(); iterator.hasNext();) {
	    Map<ColumnsEnum, Object> record = iterator.next();
	    Double playInning = ValueConvertor.settingValueAsDouble(record.get(ColumnsEnum.playInning));

	    if (playInning < basedTeamGameCnt) {
		iterator.remove();
	    }
	}
    }

    /**
     * playInning을 이닝, 아웃으로 분리한다음 이닝을 teamGameCnt로 나누면서 생기는 나머지가 0~0.3333 사이의 값이면
     * out_count 0 0.3333~0.6666사이의 값이면 out_count 1 0.6666~0.9999사이의 값이면 2
     * 
     * @see com.hiball.web.services2.test.TeamRecordUtilTest
     * @author CWH
     */
    public static String calcInningByGameCnt(int teamGameCnt, String playInning) {
	double beforeCalcInning = Double.valueOf(playInning.substring(0, playInning.lastIndexOf('.')));
	int outCount = Integer.valueOf(playInning.substring(playInning.lastIndexOf('.') + 1, playInning.length()));
	double calcInning = beforeCalcInning / teamGameCnt;

	String calcInningStr = String.valueOf(calcInning);

	int resultInning = Integer.valueOf(calcInningStr.substring(0, calcInningStr.lastIndexOf('.')));
	double remainderValue = Double
		.valueOf("0." + calcInningStr.substring(calcInningStr.lastIndexOf('.') + 1, calcInningStr.length()));

	if (0 < remainderValue && remainderValue <= 0.3333) {
	    outCount += 0;
	} else if (0.3333 < remainderValue && remainderValue <= 0.6666) {
	    outCount += 1;
	} else if (0.6666 < remainderValue && remainderValue < 1) {
	    outCount += 2;
	}

	if (outCount >= 3) {
	    resultInning += 1;
	    outCount -= 3;
	}

	String resultPlayInning = resultInning + "." + outCount;

	return resultPlayInning;
    }
}
