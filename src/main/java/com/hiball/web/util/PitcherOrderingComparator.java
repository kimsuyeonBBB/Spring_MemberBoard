package com.hiball.web.util;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.hiball.api.domain.GameRecord;

public class PitcherOrderingComparator implements Comparator<GameRecord> {
	private String criteria;
	
	public PitcherOrderingComparator() {
		this.criteria = ""; 
	}
	
	public PitcherOrderingComparator(String criteria) {
		this.criteria = criteria; 
	}
	@Override
	public int compare(GameRecord preRecord, GameRecord curRecord) {
		int value = 0;
		if (StringUtils.isBlank(criteria)|| "era".equals (criteria)) {
			value = preRecord.getEra().compareTo(curRecord.getEra());
		} else if ("whip".equals(criteria)) {
			value = preRecord.getWhip().compareTo(curRecord.getWhip());
		} else if ("babip".equals(criteria)) {
			value = preRecord.getBabip().compareTo(curRecord.getBabip());
		} else if ("dips".equals(criteria)) {
			value = preRecord.getDips().compareTo(curRecord.getDips());
		} else if ("fip".equals(criteria)) {
			value = preRecord.getFip().compareTo(curRecord.getFip());
		} else if ("rc".equals(criteria)) {
			value = preRecord.getRc().compareTo(curRecord.getRc());
		} else if ("rc27".equals(criteria)) {
			value = preRecord.getRc27().compareTo(curRecord.getRc27());
		} else if ("k9".equals(criteria)) {
			value = preRecord.getK9().compareTo(curRecord.getK9());
		} else if ("kBbRate".equals(criteria)) {
			value = preRecord.getKBbRate().compareTo(curRecord.getKBbRate());
		} else if("wpa".equals(criteria)) {
			value = curRecord.getPlayerWpa().compareTo(preRecord.getPlayerWpa());
		}
		
		return value;
	}

}
