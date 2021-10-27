package com.hiball.web.util;

import java.math.BigDecimal;
import java.util.Comparator;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.value.SortingType;

public class StrongOrWeakOrderingComprator implements Comparator<GameRecord> {
	String sortValue = "";
	int sortType = 0;
	
	public StrongOrWeakOrderingComprator(String sortValue) {
		this.sortValue = sortValue;
		this.sortType = SortingType.ACENDING;
	}
	
	public StrongOrWeakOrderingComprator(String sortValue, int sortType) {
		this.sortValue = sortValue;
		this.sortType = sortType;
	}
	
	@Override
	public int compare(GameRecord record1, GameRecord record2) {
		BigDecimal record1Value = null;
		BigDecimal record2Value = null;
		
		int sortResultValue = 0;
		
		if(sortType > 0) {
			if("ERA".equalsIgnoreCase(sortValue)) {
				record1Value = record1.getEra();
				record2Value = record2.getEra();
				
				sortResultValue = record1Value.compareTo(record2Value);
			} else if("AVG".equalsIgnoreCase(sortValue)) {
				record1Value = record1.getAvg();
				record2Value = record2.getAvg();
				
				sortResultValue = record1Value.compareTo(record2Value);		
			}
		} else if (sortType < 0) {
			if("ERA".equalsIgnoreCase(sortValue)) {
				record1Value = record1.getEra();
				record2Value = record2.getEra();
				
				sortResultValue = record2Value.compareTo(record1Value);
			} else if("AVG".equalsIgnoreCase(sortValue)) {
				record1Value = record1.getAvg();
				record2Value = record2.getAvg();
				
				sortResultValue = record2Value.compareTo(record1Value);		
			}
		}
		
		return sortResultValue;
	}
	
}
