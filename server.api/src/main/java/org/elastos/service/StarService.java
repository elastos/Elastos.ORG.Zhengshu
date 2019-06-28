package org.elastos.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.elastos.conf.AccessKeyConfiguration;
import org.elastos.conf.ElaServiceConfiguration;
import org.elastos.conf.DidConfiguration;
import org.elastos.constants.RetCode;
import org.elastos.util.HttpUtil;
import org.elastos.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StarService {

    private static Logger logger = LoggerFactory.getLogger(StarService.class);

    @Autowired
    DidConfiguration didConfiguration;

    @Autowired
    AccessKeyConfiguration accessKeyConfiguration;

    @Autowired
    ElaServiceConfiguration elaServiceConfiguration;

    @Autowired
    ThresholdManager thresholdManager;

    private ElaDidService didService = new ElaDidService();

    public void initService(){
    }

    public String bless(String starName, String userName, String userId, String belssing) {
        if(StringUtils.isAnyBlank(starName, userName, userId, belssing)){
            logger.error("bless parameter has null");
            System.out.println("bless parameter has null");
            return new ServerResponse().setStatus(RetCode.ERROR_PARAMETER).setMsg("传入参数不能为空").toJsonString();
        }

        Integer rest  = thresholdManager.useUserRest(userId);
        if (rest < 0) {
            return new ServerResponse().setStatus(RetCode.ERROR_PARAMETER).setMsg("已达当日最大祝福次数").toJsonString();
        }

        String didPropertyValue = userName + "(" + userId + "), " + belssing;

        String rawData = didService.packDidProperty(didConfiguration.getPrivateKey(), starName, didPropertyValue);
        if (null == rawData) {
            logger.error("Err bless packDidRawData failed.");
            System.out.println("Err bless packDidRawData failed.");
            return new ServerResponse().setStatus(RetCode.ERROR_INTERNAL).setMsg("打包信息错误").toJsonString();
        }

        String txid = didService.upChainByAgent(elaServiceConfiguration.getBlockAgentPrefix(), accessKeyConfiguration.getId(), accessKeyConfiguration.getSecret(), rawData);
        if (null == txid) {
            logger.error("Err bless upChainData failed.");
            System.out.println("Err bless upChainData failed.");
            return new ServerResponse().setStatus(RetCode.ERROR_INTERNAL).setMsg("上链失败").toJsonString();
        }
        Map<String, String> data = new HashMap<>();
        data.put("txid", txid);

        return new ServerResponse().setStatus(RetCode.SUCCESS).setData(data).toJsonString();
    }

    public String blessCountOfStar(String starName) {
        if(StringUtils.isBlank(starName)){
            logger.error("blessCountOfStar parameter has null");
            System.out.println("blessCountOfStar parameter has null");
            return new ServerResponse().setStatus(RetCode.ERROR_PARAMETER).setMsg("传入参数不能为空").toJsonString();
        }

        Integer count = this.getStarBlesses(starName);

        Map<String, Object> data = new HashMap<>();
        data.put("count", count);

        return new ServerResponse().setStatus(RetCode.SUCCESS).setData(data).toJsonString();
    }

    private int getStarBlesses(String starName){
        String response = HttpUtil.get(elaServiceConfiguration.getDidExplorerUrl()+ "/api/1/didexplorer/did/" + didConfiguration.getDid()+ "/property_history?key="+starName, null);
        Map<String, Object> msg = (Map<String, Object>) JSON.parse(response);
        if ((int) msg.get("status") != 200) {
            System.out.println("Err: getStarBlesses failed" + msg.get("result"));
            return 0;
        }

        List<Object>didProperties = JSON.parseArray((String)msg.get("result"));

        int count = 0;
        if (null != didProperties) {
            count = didProperties.size();
        }
        return count;
    }
}
