package com.hiball.web.common.service;

import java.util.Map;

import com.hiball.web.common.excpeiton.HiBallException;

public interface APIIntegrationService {
    public Map<String, Object> integrationService(Object apiParams) throws HiBallException;
    public Map<String, Object> serviceAPICall(Object apiParams) throws HiBallException;
}
