package org.elastos.util;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by wanghan on 2017/7/30.
 */
public class AccessKeyUtil {

    private static Logger logger = LoggerFactory.getLogger(AccessKeyUtil.class);

    public static boolean isAccess(String accessStr, String accId, String accSecret) {

        //accessStr="{id:org.elastos.bless.star;time:1547448012;auth:90c0687ea72f1c2b0d5dfcd4c47a86a7}"
        Map<String, String> map = (Map<String, String>) JSON.parse(accessStr);
        if (!accId.equals(map.get("id"))) {
            logger.info("isAccess id failed input accessStr:" + accessStr);
            System.out.println("isAccess id failed input accessStr:" + accessStr);
            return false;
        }
        String time = String.valueOf(map.get("time"));
        SimpleHash hash = new SimpleHash("md5", accSecret, time);
        String encodedPassword = hash.toHex();
        if (!encodedPassword.equals(map.get("auth"))) {
            logger.error("isAccess accSecret wrong.");
            System.out.println("isAccess accSecret wrong.");
            return false;
        }

        return true;
    }
}
