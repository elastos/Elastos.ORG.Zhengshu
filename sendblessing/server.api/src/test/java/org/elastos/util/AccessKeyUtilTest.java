package org.elastos.util;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.assertj.core.util.DateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AccessKeyUtilTest {

    //accessStr="{id:org.elastos.bless.star;time:1547448012;auth:90c0687ea72f1c2b0d5dfcd4c47a86a7}"
    private String accessStr;
    private String id = "org.elastos.bless.star";
    private String secret = "SmATCfWN1LqHH8b5bbRDbaz0IMhA5u";

    @Before
    public void setUp() throws Exception {
        long time = new Date().getTime();
        String strTime = String.valueOf(time);
        SimpleHash hash = new SimpleHash("md5", secret, strTime);
        String auth = hash.toHex();
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("time", String.valueOf(time));
        map.put("auth", auth);
        accessStr = JSON.toJSONString(map);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isAccess() throws Exception {
        Boolean ret = AccessKeyUtil.isAccess(accessStr, id, secret);
        Assert.assertTrue(ret);
    }

}