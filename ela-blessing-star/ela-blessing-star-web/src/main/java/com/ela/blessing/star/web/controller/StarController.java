package com.ela.blessing.star.web.controller;


//import com.bitgame.bmp.common.util.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ela.blessing.star.common.enums.Star;
import com.ela.blessing.star.common.result.Result;
import com.ela.blessing.star.common.util.StringUtil;
import com.ela.blessing.star.domain.BlessingInfo;
import com.ela.blessing.star.domain.FansBless;
import com.ela.blessing.star.domain.StarBlenssCount;
import com.ela.blessing.star.domain.StarInfo;
import com.ela.blessing.star.service.FansBlessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.apache.shiro.crypto.hash.SimpleHash;


import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * controller class for ela
 *
 * @author wangguoqing
 * @date 2019-1-27
 */
@RestController
@RequestMapping(value = "/api")
public class StarController {
    private static final Logger logger = LoggerFactory.getLogger(StarController.class);

    @Value("${url.chain-api}")
    private String chainApi;
    @Value("${acc_id}")
    private String acc_id;
    @Value("${acc_secret}")
    private String acc_secret;
    @Autowired
    FansBlessService fansBlessService;


    /**
     * 测试
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @CrossOrigin
    public Result test(HttpServletRequest request, HttpServletResponse response){

        return Result.success(1,"success");
    }


    /**
     * 明星祝福信息
     * @param requestMap
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @RequestMapping(value = "/star_bless_info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result StarBless(@RequestBody Map<String,String > requestMap, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String star_id = requestMap.get("starId");
        StarBlenssCount bless_count = fansBlessService.getStarBlessCountInfo(star_id);
        int blessing_count=0;
        String star_name="";
        Star starInfo = Star.fromId(star_id);
        if (starInfo != null) {
            star_name = starInfo.getValue();
        } else {
            //未找到名字
            return Result.fail(1001, "未找到该明星");
        }

        if(bless_count!=null){
            blessing_count=bless_count.getBlessingCount();
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("blessing_count",blessing_count);
        resMap.put("star_id",star_id);
        resMap.put("star_name",star_name);
        return Result.success(resMap,"success");
    }

    /**
     * 祝福排行
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @RequestMapping(value = "/blessing_rank", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result StarBlessRanking(HttpServletRequest request, HttpServletResponse response) throws Exception{


        List<StarInfo> listStarInfo=fansBlessService.getStarInfoList();
        long utime= listStarInfo.get(0).getUtime().getTime()/1000;
        long timeNow= new Date().getTime()/1000;
        long interval=timeNow-utime;
        if(interval>60){
            List<StarBlenssCount> list = list = fansBlessService.getStarBlessRanking();
            for(StarBlenssCount starBlenssCount : list){
                String starId=String.valueOf(starBlenssCount.getStarId());
                //String starName=starBlenssCount.getStarName();
                int blessingCount=starBlenssCount.getBlessingCount();
                fansBlessService.updateStarInfo(Integer.valueOf(starId),blessingCount);

            }
        }
        listStarInfo=fansBlessService.getStarInfoList();
        //List<StarInfo> listStarInfo=fansBlessService.getStarInfoList();
        Map<String,Object> map=new HashMap<>();
        map.put("StarRanking",listStarInfo);
        return Result.success(map,"success");


//        String rankingJson=redisService.getValueByKey("ela_star_ranking_json");
//        Map<String,Object> map=new HashMap<>();
//        if(StringUtil.isNullOrEmpty(rankingJson)){
//            list = fansBlessService.getStarBlessRanking();
//            long timeNow= new Date().getTime()/1000;
//            map.put("StarRanking",list);
//            map.put("updateTime",timeNow);
//            rankingJson=JSONObject.toJSON(map).toString();
//            redisService.set("ela_star_ranking_json",rankingJson);
//        }else{
//            Long rankUpdateTime=Long.valueOf(JSONObject.parseObject(rankingJson).get("updateTime").toString());
//            long timeNow= new Date().getTime()/1000;
//            long interval=timeNow-rankUpdateTime;
//            if(interval>20){
//                list = fansBlessService.getStarBlessRanking();
//                map.put("StarRanking",list);
//                map.put("updateTime",timeNow);
//                rankingJson=JSONObject.toJSON(map).toString();
//                redisService.set("ela_star_ranking_json",rankingJson);
//            }
//        }
//        JSONObject jobj=JSON.parseObject(rankingJson);
//
//        return Result.success(jobj,"success");
    }

    /**
     * 获取祝福语内容 接口
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @RequestMapping(value = "/blessing_content_info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result getBlessingInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{

        List<BlessingInfo> list = fansBlessService.getBlessingInfo();
        return Result.success(list,"success");
    }

    /**
     * 提交祝福 接口
     * @param requestMap
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @RequestMapping(value = "/blessing_save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result SaveStarBless(@RequestBody Map<String,String > requestMap, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String userIdentity = requestMap.get("openId");
        String userNick = requestMap.get("nickName");
        String starId = requestMap.get("starId");
        String starName = requestMap.get("starName");
        if(StringUtil.isNullOrEmpty(userIdentity)||StringUtil.isNullOrEmpty(userNick)||StringUtil.isNullOrEmpty(starId)){
            return Result.fail(1003, "参数错误!");
        }

        Star starInfo = Star.fromId(starId);
        if (starInfo != null) {
            starName = starInfo.getValue();
        } else {
            //未找到名字
            return Result.fail(1001, "提交失败!");
        }
        int emoticonId =Integer.valueOf(requestMap.get("emoticonId"));
        String blessingContent = requestMap.get("blessingContent");
        int count = fansBlessService.getBlessingCountToday(userIdentity);
        if(count>10){
            return Result.fail(1002,"每天最多只能送10次祝福！");
        }

        FansBless fansBless = new FansBless();
        fansBless.setUserIdentity(userIdentity);
        fansBless.setUserNick(userNick);
        fansBless.setStarId(Integer.valueOf(starId));
        fansBless.setStarName(starName);
        fansBless.setBlessingContent(blessingContent);
        fansBless.setEmoticonId(emoticonId);

        String hash="0";
        //发送到链上
//        Map<String,String> postMap=new HashMap<>();
//        postMap.put("star_name",starName);
//        postMap.put("user_name",userNick);
//        postMap.put("user_id",userIdentity);
//        postMap.put("blessing",blessingContent);
//        String postJson=JSONObject.toJSON(postMap).toString();
//        String result = post(chainApi,postJson);
//        if(StringUtil.isNullOrEmpty(result)){
//            return Result.fail(1003,"提交失败");
//        }
//        JSONObject jsonObj = JSONObject.parseObject(result);
//        String status=jsonObj.get("status").toString();
//        if(!"200".equals(status)){
//            return Result.fail(1003,"提交失败");
//        }
//        String data = jsonObj.get("data").toString();
//        hash=JSONObject.parseObject(data).get("txid").toString();

        fansBless.setTxid(hash);
        fansBlessService.saveStarBlessInfo(fansBless);

        Map<String,Object> resMap=new HashMap<>();
        resMap.put("userNick",userNick);
        resMap.put("blessingCount",0);
        resMap.put("hash",hash);

        return Result.success(resMap,"success");
    }

    String createAuthHeaderValue(){
        long time = new Date().getTime();
        String strTime = String.valueOf(time);
        SimpleHash hash = new SimpleHash("md5", acc_secret, strTime);
        String auth = hash.toHex();
        Map<String, String> map = new HashMap<>();
        map.put("id", acc_id);
        map.put("time", String.valueOf(time));
        map.put("auth", auth);
        String X_Elastos_Agent_Auth_value = JSON.toJSONString(map);
        return X_Elastos_Agent_Auth_value;
    }
    private String post(String url, String data) throws Exception {
        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");

        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-Elastos-Agent-Auth",createAuthHeaderValue());
        httpPost.setEntity(postEntity);

        org.apache.http.HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");

    }


}