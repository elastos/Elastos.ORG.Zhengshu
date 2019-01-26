package org.elastos.service;

import org.apache.commons.lang3.StringUtils;
import org.elastos.conf.DidConfiguration;
import org.elastos.constants.RetCode;
import org.elastos.util.BlockAgentService;
import org.elastos.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StarService {

    private static Logger logger = LoggerFactory.getLogger(StarService.class);

    @Autowired
    DidConfiguration didConfiguration;

    @Autowired
    BlockAgentService blockAgentService;

    private ElaDidService didService = new ElaDidServiceImp();
    public String bless(String starName, String userName, String userId, String belssing) {
        if(StringUtils.isAnyBlank(starName, userName, userId, belssing)){
            logger.error("bless parameter has null");
            System.out.println("bless parameter has null");
            return new ServerResponse().setStatus(RetCode.ERROR_PARAMETER).setMsg("传入参数不能为空").toJsonString();
        }

        String didPropertyValue = userName + "(" + userId + "), " + belssing;

        String rawData = didService.packDidRawData(didConfiguration.getPrivateKey(), starName, didPropertyValue);
        if (null == rawData) {
            logger.error("Err bless packDidRawData failed.");
            System.out.println("Err bless packDidRawData failed.");
            return new ServerResponse().setStatus(RetCode.ERROR_INTERNAL).setMsg("打包信息错误").toJsonString();
        }

        String txid = blockAgentService.upChainData(rawData);
        if (null == txid) {
            logger.error("Err bless upChainData failed.");
            System.out.println("Err bless upChainData failed.");
            return new ServerResponse().setStatus(RetCode.ERROR_INTERNAL).setMsg("上链失败").toJsonString();
        }
        Map<String, String> data = new HashMap<>();
        data.put("txid", txid);

        return new ServerResponse().setStatus(RetCode.SUCCESS).setData(data).toJsonString();
    }
}
