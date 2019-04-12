package org.elastos.service;

import jnr.ffi.annotations.Synchronized;
import org.elastos.conf.ThresholdConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ThresholdManager {
    Map<String, Integer> userThresholds = new HashMap();


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ThresholdConfiguration thresholdConfiguration;

    @Synchronized
    Integer useUserRest(String userId) {
        Integer rest = userThresholds.get(userId);
        if (null == rest) {
            rest = thresholdConfiguration.getUserThreshold();
        }
        rest--;
        if (rest >= 0) {
            userThresholds.put(userId, rest);
        }
        return rest;
    }

    @Synchronized
    void resetAllUserRest() {
        for (Map.Entry<String, Integer> it : userThresholds.entrySet()) {
            it.setValue(thresholdConfiguration.getUserThreshold());
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetAllUserRestTask() {
        System.out.println("重置开始时间：" + dateFormat.format(new Date()));
        this.resetAllUserRest();
        System.out.println("重置结束时间：" + dateFormat.format(new Date()));
    }

}
