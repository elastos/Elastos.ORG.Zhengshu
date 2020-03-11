package org.elastos.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.elastos.conf.AccessKeyConfiguration;
import org.elastos.conf.DidConfiguration;
import org.elastos.conf.ElaServiceConfiguration;
import org.elastos.constants.RetCode;
import org.elastos.dao.UserRepository;
import org.elastos.dto.User;
import org.elastos.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AssociationService {

    private static Logger logger = LoggerFactory.getLogger(AssociationService.class);

    @Autowired
    DidConfiguration didConfiguration;

    @Autowired
    AccessKeyConfiguration accessKeyConfiguration;

    @Autowired
    ElaServiceConfiguration elaServiceConfiguration;

    @Autowired
    UserRepository userRepository;

    private ElaDidService didService = new ElaDidService();

    public void initService() {
    }

    public String certificate(String name, String content, Long uid) {
        if (StringUtils.isAnyBlank(name, content)) {
            logger.error("certificate parameter has null");
            return new ServerResponse().setState(RetCode.ERROR_PARAMETER).setMsg("传入参数异常").toJsonString();
        }

        String ret = didService.createDid(didConfiguration.getMemo(), uid.intValue());
        Map obj = JSON.parseObject(ret, Map.class);
        String didPrivateKey = (String) obj.get("DidPrivateKey");
        String did = (String) obj.get("Did");

        String rawData = didService.packDidProperty(didPrivateKey, name, content);
        if (null == rawData) {
            logger.error("Err certificate packDidRawData failed.");
            return new ServerResponse().setState(RetCode.ERROR_INTERNAL).setMsg("打包信息错误").toJsonString();
        }

        String txid = didService.upChainByAgent(elaServiceConfiguration.getBlockAgentPrefix(), accessKeyConfiguration.getId(), accessKeyConfiguration.getSecret(), rawData);
        if (null == txid) {
            logger.error("Err certificate upChainData failed.");
            System.out.println("Err certificate upChainData failed.");
            return new ServerResponse().setState(RetCode.ERROR_INTERNAL).setMsg("上链失败").toJsonString();
        }

        Map<String, String> data = new HashMap<>();
        // https://explorer.elaphant.app/history/igcBAAKG28NDdTfyWDtpH33wevJrKuHay1/SNH48%E5%86%AF%E8%96%AA%E6%9C%B5
        String didExplorerUrl = elaServiceConfiguration.getDidExplorerUrl() + "/history/" + did + "/";
        try {
            didExplorerUrl += java.net.URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //https://idchain.elastos.org/properties_list/igcBAAKG28NDdTfyWDtpH33wevJrKuHay1
            didExplorerUrl = elaServiceConfiguration.getDidExplorerUrl() + "/properties_list/" + did;
        }

        data.put("did_explorer_url", didExplorerUrl);
        data.put("txid", txid);

        return new ServerResponse().setState(RetCode.SUCCESS).setData(data).toJsonString();
    }
}
