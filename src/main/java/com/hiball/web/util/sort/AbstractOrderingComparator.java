package com.hiball.web.util.sort;

import java.util.Map;

import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.enums.SortingType;
import com.hiball.web.util.convertor.ValueConvertor;

public abstract class AbstractOrderingComparator {
	protected ColumnsEnum sortColumn = ColumnsEnum.avg;
	protected SortingType sortingType = SortingType.DECENDING;
	
	public AbstractOrderingComparator() {}
	public AbstractOrderingComparator(ColumnsEnum sortColumn) { 
		this.sortColumn = sortColumn; 
	}
	public AbstractOrderingComparator(SortingType sortingType) { 
		this.sortingType = sortingType; 
	}
	public AbstractOrderingComparator(ColumnsEnum sortColumn, SortingType sortingType) {
		this.sortColumn = sortColumn;
		this.sortingType = sortingType;
	}
	
	protected int compared(Map<ColumnsEnum, Object> preRecord,
			Map<ColumnsEnum, Object> afterRecord, SortingType sortingType) {
		if(preRecord == null || afterRecord == null) return 0;
		
		Double preValue = ValueConvertor.settingValueAsDouble(preRecord.get(sortColumn));
		Double afterValue = ValueConvertor.settingValueAsDouble(afterRecord.get(sortColumn));
		
		switch(sortingType) {
		case ACENDING:
			return preValue.compareTo(afterValue);
		case DECENDING: 
			return afterValue.compareTo(preValue);
		}
	
		return 0;
	}
}
