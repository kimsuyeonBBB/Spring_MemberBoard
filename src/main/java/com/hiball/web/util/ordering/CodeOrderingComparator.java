package com.hiball.web.util.ordering;

import java.util.Comparator;

import com.hiball.api.domain.CodeDomain;

public class CodeOrderingComparator implements Comparator<CodeDomain>{
	
	@Override
	public int compare(CodeDomain preDomain, CodeDomain afterDomain) {
		int value = preDomain.getName().compareTo(afterDomain.getName());
		return value;
	}
	
}
