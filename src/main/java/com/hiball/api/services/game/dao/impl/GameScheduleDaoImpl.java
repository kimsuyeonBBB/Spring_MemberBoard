package com.hiball.api.services.game.dao.impl;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hiball.api.domain.GameRecord;
import com.hiball.api.domain.GameVideoRecord;
import com.hiball.api.domain.VideoRecord;
import com.hiball.api.param.GameScheduleParam;
import com.hiball.api.services.game.dao.GameScheduleDao;
import com.hiball.web.common.dao.AbstractCommonAPIDao;

@Repository("gameScheduleDao")
public class GameScheduleDaoImpl extends AbstractCommonAPIDao implements GameScheduleDao {
    private static Logger logger = LoggerFactory.getLogger(GameScheduleDaoImpl.class);
    private static final String namespace = "com.hiball.sql.gameinfo.record.";

    @Override
    public List<GameRecord> selectGameSheduleInfo(GameScheduleParam param) {
	return sqlSession(param).selectList(namespace + "selectGameSheduleInfo", param);
    }

    @Override
    public void selectGameVideoRecord(GameScheduleParam param, ResultHandler resultHandler) {
	logger.debug("##### GameScheduleParam on selectGameVideoRecord with resultHandler {} #####", param);
	long startTime = System.currentTimeMillis();
	sqlSession(param).select(namespace + "selectGameVideoRecord", param, resultHandler);
	long endTime = System.currentTimeMillis();
	logger.info("##### DAO get game video record with resultHandler >> Elapse Time: {} sec. ######", (endTime-startTime)/1000);
    }
    @Override
    public List<GameVideoRecord> selectGameVideoRecord(GameScheduleParam param) {
	logger.debug("##### GameScheduleParam on selectGameVideoRecord {} #####", param);
	long startTime = System.currentTimeMillis();
	List<GameVideoRecord> result = sqlSession(param).selectList(namespace + "selectGameVideoRecord", param);
	long endTime = System.currentTimeMillis();
	logger.info("##### DAO get game video record >> Elapse Time: {} sec. ######", (endTime-startTime)/1000);
	
	return result;
    }
    
    @Override
    public List<VideoRecord> selectVideoInfo(GameScheduleParam param) {
	return sqlSession(param).selectList(namespace+"selectVideoInfo", param);
    }
}
