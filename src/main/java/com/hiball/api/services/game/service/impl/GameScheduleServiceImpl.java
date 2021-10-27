package com.hiball.api.services.game.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiball.api.domain.GameRecord;
import com.hiball.api.domain.GameVideoRecord;
import com.hiball.api.domain.VideoRecord;
import com.hiball.api.param.GameScheduleParam;
import com.hiball.api.services.game.dao.GameScheduleDao;
import com.hiball.api.services.game.dao.impl.ScheduleResultHandler;
import com.hiball.api.services.game.service.GameScheduleService;
import com.hiball.web.common.excpeiton.HiBallException;
import com.hiball.web.common.service.AbstractCommonAPIService;

@Service("gameScheduleService")
public class GameScheduleServiceImpl extends AbstractCommonAPIService<GameScheduleParam> implements GameScheduleService {
    private static Logger logger = LoggerFactory.getLogger(GameScheduleServiceImpl.class);
    @Autowired
    GameScheduleDao gameScheduleDao;
    
    @Override
    public List<GameRecord> retrieveGameSheduleInfo(GameScheduleParam param) {
	List<GameRecord> result = null;
	result = gameScheduleDao.selectGameSheduleInfo(param); 
	return result;
    }
    
    @Override
    public List<GameRecord> retrieveGameScheduleByTeam(GameScheduleParam param) {
	List<GameRecord> result = null;
	long startTime = System.currentTimeMillis();
	result = gameScheduleDao.selectGameSheduleInfo(param); 
	long endTime = System.currentTimeMillis();
	logger.info("##### (get game schedule)Num: {} Elapse Time: {} sec. ######", result.size(), (endTime-startTime)/1000);
	
	return result;
    }
    
    @Override
    public List<GameVideoRecord> retrieveGameVideoRecord(GameScheduleParam param) {
	List<GameVideoRecord> result = null;
	long startTime = System.currentTimeMillis();
	ScheduleResultHandler resultHandler = new ScheduleResultHandler();
	gameScheduleDao.selectGameVideoRecord(param, resultHandler);
	result = resultHandler.getResults();
	//result = gameScheduleDao.selectGameVideoRecord(param);
	long endTime = System.currentTimeMillis();
	
	logger.info("##### (get video record)Num: {} Elapse Time: {} sec. ######", result.size(), (endTime-startTime)/1000);
	return result;
    }
    
    @Override
    public List<VideoRecord> retrieveVideoInfo(GameScheduleParam param) {
	List<VideoRecord> result = null;
	result = gameScheduleDao.selectVideoInfo(param);
	
	return result;
    }

    @Override
    public Map<String, Object> execute(String subType, String sortColumn, Map<String, String[]> paramMap) throws HiBallException {
	Map<String, Object> resultMap = new HashMap<>();
	Object gameInfoRecord = new ArrayList<Object>();
	
	GameScheduleParam param = new GameScheduleParam();
	param = this.autoBindingToDomain(paramMap, param);
	logger.debug("##### Parameter In Service {} ######", param);
	
	if (subType.equals("gameScheduleByTeam")) {
	    gameInfoRecord =  (Object)retrieveGameScheduleByTeam(param);
	} else if (subType.equals("gameVideoRecord")) {
	    gameInfoRecord = (Object)retrieveGameVideoRecord(param);
	} else if (subType.equals("videoInfo")) {
	    gameInfoRecord = (Object)retrieveVideoInfo(param);
	}

	resultMap.put("resultMap", gameInfoRecord);

	return resultMap;
    }

}
