package com.hiball.web.util.sort;

import java.util.Comparator;
import java.util.Map;

import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.enums.SortingType;

public class BattingOrderingComparator extends AbstractOrderingComparator implements Comparator<Map<ColumnsEnum, Object>>{
	
	@Override
	public int compare(Map<ColumnsEnum, Object> preRecord, Map<ColumnsEnum, Object> afterRecord) {
		int comparedResult = 0;

		comparedResult = compared(preRecord, afterRecord, sortingType);
		
		return comparedResult;
	}
	
	public BattingOrderingComparator() {}

	public BattingOrderingComparator(ColumnsEnum sortColumn) {
		super(sortColumn);
	}
	
	public BattingOrderingComparator(SortingType sortingType) {
		super(sortingType);
	}
	
	public BattingOrderingComparator(ColumnsEnum sortColumn, SortingType sortingType) {
		super(sortColumn, sortingType);
	}
}
