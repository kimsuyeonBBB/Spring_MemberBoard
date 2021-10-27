package com.hiball.web.util.ordering;

import java.util.Comparator;

import com.hiball.api.domain.GameRecord;
import com.hiball.web.util.value.SortingValue;

public class TeamOrderingComparator implements Comparator<GameRecord> {
	private String orderingFactor = null;
	
	public TeamOrderingComparator(String orderingFactor) {
		this.orderingFactor = orderingFactor;
	}
	
	public TeamOrderingComparator() {
		
	}
	
	@Override
	public int compare(GameRecord preRecord, GameRecord curRecord) {
		int value = 0;
		
		if(orderingFactor == null) {
			return curRecord.getWinningRate().compareTo(preRecord.getWinningRate());
		} else if(orderingFactor != null) {
			if(orderingFactor.equals(SortingValue.AVG)) {
				if(preRecord.getAvg().compareTo(curRecord.getAvg()) == -1){
					value = 1;
				} else if(preRecord.getAvg().compareTo(curRecord.getAvg()) == 1) {
					value = -1;
				}
			} else if(orderingFactor.equals(SortingValue.SB)) {
				if(preRecord.getSuccessSteal() < curRecord.getSuccessSteal()) {
					value = 1;
				} else if(preRecord.getSuccessSteal() > curRecord.getSuccessSteal()) {
					value = -1;
				}
			}
		}		
		return value;
	}

}
