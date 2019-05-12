package org.elastos.service;

import org.apache.commons.lang3.StringUtils;
import org.elastos.conf.AccessKeyConfiguration;
import org.elastos.conf.DidConfiguration;
import org.elastos.conf.ElaServiceConfiguration;
import org.elastos.constants.RetCode;
import org.elastos.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
    ThresholdManager thresholdManager;

    private ElaDidService didService = new ElaDidServiceImp();

    public void initService() {
        didService.setElaNodeUrl(didConfiguration.getNode());
        didService.setBlockAgentUrl(elaServiceConfiguration.getBlockAgentPrefix());
    }

    public String certificate(String name, String content) {
        if (StringUtils.isAnyBlank(name, content)) {
            logger.error("certificate parameter has null");
            System.out.println("certificate parameter has null");
            return new ServerResponse().setStatus(RetCode.ERROR_PARAMETER).setMsg("传入参数不能为空").toJsonString();
        }


        String rawData = didService.packDidRawData(didConfiguration.getPrivateKey(), name, content);
        if (null == rawData) {
            logger.error("Err certificate packDidRawData failed.");
            System.out.println("Err certificate packDidRawData failed.");
            return new ServerResponse().setStatus(RetCode.ERROR_INTERNAL).setMsg("打包信息错误").toJsonString();
        }

        String txid = didService.upChainByBlockAgent(accessKeyConfiguration.getId(), accessKeyConfiguration.getSecret(), rawData);
        if (null == txid) {
            logger.error("Err certificate upChainData failed.");
            System.out.println("Err certificate upChainData failed.");
            return new ServerResponse().setStatus(RetCode.ERROR_INTERNAL).setMsg("上链失败").toJsonString();
        }
        Map<String, String> data = new HashMap<>();


        //https://idchain.elastos.org/did/igcBAAKG28NDdTfyWDtpH33wevJrKuHay1/property_history/SNH48%E5%86%AF%E8%96%AA%E6%9C%B5
        String didExplorerUrl = elaServiceConfiguration.getDidExplorerUrl() + "/did/" + didConfiguration.getDid() + "/property_history/";
        try {
            didExplorerUrl += java.net.URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //https://idchain.elastos.org/properties_list/igcBAAKG28NDdTfyWDtpH33wevJrKuHay1
            didExplorerUrl = elaServiceConfiguration.getDidExplorerUrl() + "/properties_list/" + didConfiguration.getDid();
        }

        data.put("did_explorer_url", didExplorerUrl);
        data.put("txid", txid);

        return new ServerResponse().setStatus(RetCode.SUCCESS).setData(data).toJsonString();
    }
}
