package com.hiball.web.common.service;


import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.param.CommonParam;

public abstract class AbstractServiceLogic<P extends CommonParam> {
	public abstract Object execute(P param) throws HiBallException;
}
