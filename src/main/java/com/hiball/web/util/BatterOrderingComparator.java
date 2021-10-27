package com.hiball.web.util;

import java.math.BigDecimal;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.hiball.api.domain.GameRecord;

public class BatterOrderingComparator implements Comparator<GameRecord> {
	private String criteria;
	
	public BatterOrderingComparator() {
		this.criteria = "";
	}
	
	public BatterOrderingComparator(String criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public int compare(GameRecord preRecord, GameRecord curRecord) {
		int value = 0;
		
		if (StringUtils.isBlank(criteria) || "avg".equals(criteria)){
			value = curRecord.getAvg().compareTo(preRecord.getAvg());
		} else if ("ops".equals(criteria)) {
			value = curRecord.getOps().compareTo(preRecord.getOps());
		} else if ("obp".equals(criteria)) {
			value = curRecord.getObp().compareTo(preRecord.getObp());
		} else if ("slg".equals(criteria)) {
			value = curRecord.getSlg().compareTo(preRecord.getSlg());
		} else if ("isoP".equals(criteria)) {
			value = curRecord.getIsoP().compareTo(preRecord.getIsoP());
		} else if ("goAoRate".equals(criteria)) {
			value = curRecord.getGoAoRate().compareTo(preRecord.getGoAoRate());
		} else if("wpa".equals(criteria)) {
			if(curRecord.getPlayerWpa() == null) curRecord.setPlayerWpa(new BigDecimal(0));
			if(preRecord.getPlayerWpa() == null) preRecord.setPlayerWpa(new BigDecimal(0));
			
			value = curRecord.getPlayerWpa().compareTo(preRecord.getPlayerWpa());
		}
		
		return value;
	}

}
