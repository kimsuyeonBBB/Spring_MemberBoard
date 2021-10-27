package com.hiball.web.common.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;

import com.hiball.web.common.enums.ColumnsEnum;
import com.hiball.web.common.param.CommonParam;
import com.hiball.web.util.value.GroupingColumn;

public abstract class AbstractResultHandler {
	protected <D extends CommonParam> void propertySetter(D domain, ColumnsEnum[] defaultEnumsArray) throws IllegalArgumentException{
		coverLogic(domain, defaultEnumsArray);
		if(domain.getGroupingColumn() != null) addPropertyInPropertyArray(domain);
	}
	
	protected <D extends CommonParam> void coverPropertySetter(D domain, ColumnsEnum[] defaultEnumsArray) throws IllegalArgumentException {
		coverLogic(domain, defaultEnumsArray);
	}
	
	private <D extends CommonParam> void coverLogic(D domain, ColumnsEnum[] defaultEnumsArray) {
		if(domain.getPropertyStr().isEmpty() || domain.getPropertyStr().equals("")
				|| domain.getPropertyStr() == null) {
			domain.setColumnsEnumArray(defaultEnumsArray);
		} else {
			setColumnsEnumSet(domain, defaultEnumsArray);
		}
	}
	
	private <D extends CommonParam> void setColumnsEnumSet(D domain, ColumnsEnum[] defaultEnumsArray) throws IllegalArgumentException{
		String[] splitedArray = domain.getPropertyStr().split("/");
		int arrayLength = splitedArray.length;
		
		List<ColumnsEnum> enumsList = new ArrayList<>(Arrays.asList(defaultEnumsArray));
		
		for(int i=0; i<arrayLength; i++) {
			try {
				ColumnsEnum newEnum = ColumnsEnum.valueOf(splitedArray[i]);
				enumsList.add(newEnum);
			}catch(IllegalArgumentException e) {
				throw new IllegalArgumentException("Would you check about you sned right " + splitedArray[i] + " property??");
			}
			
		}
		
		ColumnsEnum[] newEnumsArray = new ColumnsEnum[enumsList.size()];
		
		newEnumsArray = enumsList.toArray(newEnumsArray);
		
		domain.setColumnsEnumArray(newEnumsArray);
	}
	
	private <D extends CommonParam> void addPropertyInPropertyArray(D domain) {
		String groupingColumnName = GroupingColumn.groupingColumnMatchingMap.get(domain.getGroupingColumn());
		
		if(groupingColumnName.contains("/")) {
			String[] groupingColumnsNames = groupingColumnName.split("/");
			propertiesAdded(groupingColumnsNames, domain);
		} else {
			propertyAdded(groupingColumnName, domain);
		}
	}
	
	private <D extends CommonParam> void propertiesAdded(String[] newProperties, D domain) {
		ColumnsEnum[] basedCEnumArray = domain.getColumnsEnumArray();
		List<ColumnsEnum> propertyList = new ArrayList<>(Arrays.asList(basedCEnumArray));
		
		for(String property : newProperties) {
			propertyList.add(ColumnsEnum.valueOf(WordUtils.uncapitalize(property)));
		}
		
		domain.setColumnsEnumArray(
				propertyList.toArray(
						new ColumnsEnum[propertyList.size()]));
	}
	
	private <D extends CommonParam> void propertyAdded(String newProperty, D param) {
		ColumnsEnum[] basedCEnumArray = param.getColumnsEnumArray();
		
		List<ColumnsEnum> propertyList = new ArrayList<>(Arrays.asList(basedCEnumArray));

		propertyList.add(ColumnsEnum.valueOf(WordUtils.uncapitalize(newProperty)));

		param.setColumnsEnumArray(
				propertyList.toArray(
						new ColumnsEnum[propertyList.size()]));
	}
	
	public abstract List<Map<ColumnsEnum, Object>> getResult();
}
