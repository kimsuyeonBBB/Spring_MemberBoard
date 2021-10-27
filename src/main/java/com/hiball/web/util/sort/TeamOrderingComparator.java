package com.hiball.web.util.sort;

import java.util.Comparator;
import java.util.Map;

import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.enums.SortingType;

public class TeamOrderingComparator extends AbstractOrderingComparator implements Comparator<Map<ColumnsEnum, Object>> {

	@Override
	public int compare(Map<ColumnsEnum, Object> preRecord, Map<ColumnsEnum, Object> afterRecord) {
		int comparedResult = compared(preRecord, afterRecord, sortingType);
		return comparedResult;
	}

	public TeamOrderingComparator() {
		super();
	}

	public TeamOrderingComparator(ColumnsEnum sortColumn, SortingType sortingType) {
		super(sortColumn, sortingType);
	}

	public TeamOrderingComparator(ColumnsEnum sortColumn) {
		super(sortColumn);
	}

	public TeamOrderingComparator(SortingType sortingType) {
		super(sortingType);
	}
	
}
