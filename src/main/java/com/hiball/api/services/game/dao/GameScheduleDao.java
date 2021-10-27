package com.hiball.api.services.game.dao;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;

import com.hiball.api.domain.GameRecord;
import com.hiball.api.domain.GameVideoRecord;
import com.hiball.api.domain.VideoRecord;
import com.hiball.api.param.GameScheduleParam;

public interface GameScheduleDao {
    public List<GameRecord> selectGameSheduleInfo(GameScheduleParam param);
    public void selectGameVideoRecord(GameScheduleParam param, ResultHandler resultHandler);
    public List<GameVideoRecord> selectGameVideoRecord(GameScheduleParam param);
    public List<VideoRecord> selectVideoInfo(GameScheduleParam param);
}
