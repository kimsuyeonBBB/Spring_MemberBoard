package com.hiball.api.services.game.service;

import java.util.List;

import com.hiball.api.domain.GameRecord;
import com.hiball.api.domain.GameVideoRecord;
import com.hiball.api.domain.VideoRecord;
import com.hiball.api.param.GameScheduleParam;

public interface GameScheduleService {
    public List<GameRecord> retrieveGameSheduleInfo(GameScheduleParam param);
    public List<GameRecord> retrieveGameScheduleByTeam(GameScheduleParam param);
    public List<GameVideoRecord> retrieveGameVideoRecord(GameScheduleParam param);
    public List<VideoRecord> retrieveVideoInfo(GameScheduleParam param);
}
