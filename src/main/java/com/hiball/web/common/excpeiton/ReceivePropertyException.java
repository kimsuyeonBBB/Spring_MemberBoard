package com.hiball.web.common.excpeiton;

import com.hiball.web.common.enums.ColumnsEnum;

public class ReceivePropertyException extends HiBallException {
	private static final long serialVersionUID = 1L;
	
	public ReceivePropertyException(Exception e) {
		super(e);
	}

	public ReceivePropertyException(String errCode, String sysMsg, String errCause) {
		super(errCode, sysMsg, errCause);
	}

	public ReceivePropertyException(ColumnsEnum receivedProperty) {
		super(receivedProperty + " property is omitted");
	}
	
	
}
