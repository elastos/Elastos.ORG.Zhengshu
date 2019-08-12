package org.elastos.util;

import org.elastos.constants.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    @Autowired
    private StringRedisTemplate redis;

    public String createToken(Long uid) {
        String token = UUID.randomUUID().toString();
        redis.boundValueOps(token).set(uid.toString());
        redis.boundValueOps(uid.toString()).set(token);
        redis.boundValueOps(token).expire(Auth.TOKEN_EXPIRES_HOUR, TimeUnit.DAYS);
        return token;
    }

    public void deleteToken(String token, Long uid) {
        redis.delete(token);
        redis.delete(uid.toString());
    }

    public Long tokenToUser(String token) {
        String uid = redis.boundValueOps(token).get();
        if (null == uid) {
            return null;
        } else {
            return Long.valueOf(uid);
        }
    }

    public String userToToken(Long uid) {
        String token = redis.boundValueOps(uid.toString()).get();
        if (null == token) {
            return null;
        } else {
            return token;
        }
    }
}
