package com.hiball.web.util.sort;

import java.util.Comparator;
import java.util.Map;

import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.enums.SortingType;

public class PitchingOrderingComparator extends AbstractOrderingComparator implements Comparator<Map<ColumnsEnum, Object>> {

	@Override
	public int compare(Map<ColumnsEnum, Object> preRecord, Map<ColumnsEnum, Object> afterRecord) {
		int comparedResult = 0;
		
		comparedResult = compared(preRecord, afterRecord, sortingType);
		
		return comparedResult;
	}
	
	public PitchingOrderingComparator() {}

	public PitchingOrderingComparator(ColumnsEnum sortColumn) {
		super(sortColumn);
	}
	
	public PitchingOrderingComparator(SortingType sortingType) {
		super(sortingType);
	}
	
	public PitchingOrderingComparator(ColumnsEnum sortColumn, SortingType sortingType) {
		super(sortColumn, sortingType);
	}
}
