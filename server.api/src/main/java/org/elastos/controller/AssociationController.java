/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elastos.annotation.Auth;
import org.elastos.service.AssociationService;
import org.elastos.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/1/association")
public class AssociationController {
    private static Logger logger = LoggerFactory.getLogger(AssociationController.class);

    @Autowired
    private AssociationService associationService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "phone", method = RequestMethod.POST)
    @ResponseBody
    public String userPhone(HttpServletRequest request, @RequestAttribute String reqBody) {
        JSONObject map = JSON.parseObject(reqBody);
        String phone= map.getString("phone");
        return loginService.phoneCheck(request.getSession(), phone);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String userLogin(HttpServletRequest request, @RequestAttribute String reqBody) {
        JSONObject map = JSON.parseObject(reqBody);
        String phone= map.getString("phone");
        String code= map.getString("code");
        return loginService.login(request.getSession(), phone, code);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Auth
    public String userLogout(HttpServletRequest request, @RequestAttribute Long uid) {
        return loginService.logout(request.getSession(), uid);
    }

    @RequestMapping(value = "certification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @Auth
    public String certificate(@RequestAttribute String reqBody, @RequestAttribute Long uid) {
        logger.info("certification:"+ reqBody);
        JSONObject map = JSON.parseObject(reqBody);
        String starName = map.getString("name");
        String userName = map.getString("content");
        return associationService.certificate(starName, userName, uid);
    }

    @RequestMapping(value = "echo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String echo(@RequestAttribute String reqBody) {
        logger.info("StarController echo data:" + reqBody);
        return reqBody;
    }
}
