package org.elastos.util;

import org.elastos.constants.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CheckPhoneUtil {

    private static Logger logger = LoggerFactory.getLogger(CheckPhoneUtil.class);

    @Autowired
    private StringRedisTemplate redis;

    public String createCode(String phone) {
        String code = RandomString.createN(Auth.VERIFICATION_CODE_LENTH);
        redis.boundValueOps(phone).set(code);
        redis.boundValueOps(phone).expire(5, TimeUnit.MINUTES);

        String c = redis.boundValueOps(phone).get();
        if ((null == c)||(!c.equals(code))) {
            throw new ElastosServiceException("Err createCode redis.boundValueOps(phone).get();");
        } else {
            logger.debug("createCode code:"+c);
        }

        return code;
    }

    public void deleteCode(String phone) {
        redis.delete(phone);
    }

    public String getCode(String phone) {
        String code = redis.boundValueOps(phone).get();
        if (null != code) {
            logger.debug("getCode code:"+code);
        }
        return code;
    }
}
