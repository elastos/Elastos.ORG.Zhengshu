package org.elastos.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.elastos.conf.DidConfiguration;
import org.elastos.constants.Auth;
import org.elastos.constants.RetCode;
import org.elastos.dao.UserRepository;
import org.elastos.dto.User;
import org.elastos.exception.ElastosServiceException;
import org.elastos.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    DidConfiguration didConfiguration;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    CheckPhoneUtil checkPhoneUtil;

    public String phoneCheck(HttpSession session, String phone) {
        if (StringUtils.isBlank(phone)) {
            logger.error("phoneCheck parameter has null");
            return new ServerResponse().setState(RetCode.ERROR_PARAMETER).setMsg("手机号不能为空").toJsonString();
        }

        User user;
        Optional<User> userOp = userRepository.findByPhone(phone);
        if (!userOp.isPresent()) {
            user = new User();
            user.setPhone(phone);
            userRepository.save(user);
        }

        String code = checkPhoneUtil.createCode(phone);
        if(!AliyunSmsUtil.sendSms(phone,code)){
            return new ServerResponse().setState(RetCode.ERROR_PARAMETER).setMsg("网络异常,请稍候再试").toJsonString();
        }

        Map<String, String> data = new HashMap<>();
        data.put("code", code);
        return new ServerResponse().setState(RetCode.SUCCESS).setData(data).toJsonString();
    }

    public String login(HttpSession session, String phone, String code) {
        if (StringUtils.isAnyBlank(phone, code)) {
            logger.error("login parameter has null");
            return new ServerResponse().setState(RetCode.ERROR_PARAMETER).setMsg("传入参数异常").toJsonString();
        }

        String vCode = checkPhoneUtil.getCode(phone);
        if ((null != vCode)&&(vCode.equals(code))) {
            return new ServerResponse().setState(RetCode.ERROR_DATA_NOT_FOUND).setMsg("校验码错误").toJsonString();
        }

        Optional<User> userOp = userRepository.findByPhone(phone);
        if (!userOp.isPresent()) {
            logger.error("login uid not found. phone:" + phone);
            return new ServerResponse().setState(RetCode.ERROR_DATA_NOT_FOUND).setMsg("手机号异常").toJsonString();
        }
        User user = userOp.get();
        //If there is a new user, we record the did.
        if ( (null == user.getDid()) || user.getDid().isEmpty()) {
            if(user.getId() > Integer.MAX_VALUE){
                throw  new ElastosServiceException("There is not enough did for this memo");
            }

            ElaDidService didService = new ElaDidService();
            String ret = didService.createDid(didConfiguration.getMemo(), user.getId().intValue());
            Map data = JSON.parseObject(ret, Map.class);
            String did = (String) data.get("Did");
            user.setDid(did);
            user = userRepository.save(user);
        }

        session.setAttribute(Auth.USER_ID, user.getId());
        String token = tokenUtil.createToken(user.getId());

        Map<String, String> data = new HashMap<>();
        data.put("did", user.getDid());
        data.put("token", token);

        return new ServerResponse().setState(RetCode.SUCCESS).setData(data).toJsonString();
    }

    public String logout(HttpSession session, Long uid) {
        session.removeAttribute(Auth.USER_ID);
        String token = tokenUtil.userToToken(uid);
        tokenUtil.deleteToken(token, uid);
        return new ServerResponse().setState(RetCode.SUCCESS).toJsonString();
    }
}
