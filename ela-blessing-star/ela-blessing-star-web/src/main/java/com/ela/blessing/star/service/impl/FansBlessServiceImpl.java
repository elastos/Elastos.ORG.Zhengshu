package com.ela.blessing.star.service.impl;

import com.alibaba.fastjson.JSON;
import com.ela.blessing.star.dao.BlessingInfoDAO;
import com.ela.blessing.star.dao.FansBlessDAO;
import com.ela.blessing.star.dao.StarInfoDAO;
import com.ela.blessing.star.domain.BlessingInfo;
import com.ela.blessing.star.domain.FansBless;
import com.ela.blessing.star.domain.StarBlenssCount;
import com.ela.blessing.star.domain.StarInfo;
import com.ela.blessing.star.service.FansBlessService;
//import com.ela.blessing.star.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FansBlessServiceImpl implements FansBlessService {

    @Autowired
    private FansBlessDAO fansBlessDAO;
    @Autowired
    private BlessingInfoDAO blessingInfoDAO;

//    @Autowired
//    private RedisService redisService;
    @Autowired
    private StarInfoDAO starInfoDAO;

    @Override
    public StarBlenssCount getStarBlessCountInfo(String star) {

        return fansBlessDAO.getStarBlessCount(star);
    }

    @Override
    public List<StarBlenssCount> getStarBlessRanking() {

        List<StarBlenssCount> list = fansBlessDAO.getStarBlessList();
        return list;

    }

    @Override
    public void saveStarBlessInfo(FansBless fansBless) {

        fansBlessDAO.saveStarBless(fansBless);
    }

    @Override
    public int getBlessingCountToday(String userIdentity){

        return fansBlessDAO.getBlessingCountToday(userIdentity);
    }

    @Override
    public int userExists(String star, String user) {

        return fansBlessDAO.getExists(user, star);
    }

    @Override
    public List<BlessingInfo> getBlessingInfo(){
        return blessingInfoDAO.getBlessingInfo();
    }

    @Override
    public int updateStarInfo(int starId,int blessingCount){
        return starInfoDAO.updateStarInfo(starId,blessingCount);
    }
    @Override
    public List<StarInfo> getStarInfoList(){
        return starInfoDAO.getStarInfoList();

    }
}
