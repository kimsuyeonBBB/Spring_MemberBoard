package com.hiball.api.services.game.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import com.hiball.api.domain.GameVideoRecord;

public class ScheduleResultHandler implements ResultHandler {
    private final List<GameVideoRecord> results = new ArrayList<GameVideoRecord>();
    
    @Override
    public void handleResult(ResultContext context) {
	GameVideoRecord videoRecord = (GameVideoRecord) context.getResultObject();
	results.add(videoRecord);
    }
    
    public List<GameVideoRecord> getResults() {
	return results;
    }
}
