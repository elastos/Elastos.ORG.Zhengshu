package org.elastos.util;

import org.elastos.constants.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CheckPhoneUtil {

    @Autowired
    private StringRedisTemplate redis;

    public String createCode(String phone) {
        String code = RandomString.createN(Auth.VERIFICATION_CODE_LENTH);
        redis.boundValueOps(phone).set(code);
        redis.boundValueOps(phone).expire(5, TimeUnit.MINUTES);
        return code;
    }

    public void deleteCode(String phone) {
        redis.delete(phone);
    }

    public String getCode(String phone) {
        String code = redis.boundValueOps(phone).get();
        return code;
    }
}
