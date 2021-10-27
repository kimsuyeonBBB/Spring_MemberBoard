package com.hiball.web.common.excpeiton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hiball.web.common.enums.ColumnsEnum;

public class HiBallException extends Exception {
	private static final Logger logger = LoggerFactory.getLogger("EXCEPTION");
	
	private static final long serialVersionUID = -8367677865132559898L;
	private String errCode;
	private String errCause;
	private String sysMsg;
	
	public HiBallException(){}
	
	public HiBallException(String message) {
		super(message);
	}
	
	public HiBallException(String errCode, String sysMsg, String errCause) {
		super(sysMsg);
		this.errCode = errCode;
		this.errCause = errCause;
		this.sysMsg = sysMsg;
	}
	
	public HiBallException(Exception e) {
		super(e);
	}
	
	public String getSysMsg() {
		logger.error(sysMsg);
		return sysMsg;
	}
	
	public String getErrCause() {
		logger.error(errCause);
		return errCause;
	}

	public String getErrCode() {
		logger.error(errCode);
		return errCode;
	}
	
	public StackTraceElement[] getStackTrace() {
        return null;
    }
}
