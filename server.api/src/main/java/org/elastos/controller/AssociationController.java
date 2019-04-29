/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.controller;

import com.alibaba.fastjson.JSON;
import org.elastos.service.AssociationService;
import org.elastos.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/1/association")
public class AssociationController {
    private static Logger logger = LoggerFactory.getLogger(AssociationController.class);

    @Autowired
    private AssociationService associationService;

    @RequestMapping(value = "certification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String certificate(@RequestAttribute String reqBody) {
        logger.info("certification:"+ reqBody);
        Map<String, String> map = (Map<String, String>) JSON.parse(reqBody);
        String starName = map.get("name");
        String userName = map.get("content");
        return associationService.certificate(starName, userName);
    }

    @RequestMapping(value = "echo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String echo(@RequestAttribute String reqBody) {
        logger.info("StarController echo data:" + reqBody);
        return reqBody;
    }
}
