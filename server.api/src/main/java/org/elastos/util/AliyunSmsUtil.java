package org.elastos.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;


public class AliyunSmsUtil {

    // 此处需要替换成开发者自己的信息(在阿里云访问控制台寻找)
    static private final String accessKeyId = "LTAIx2t2btvErTDy";
    static private final String accessKeySecret = "3q2JqvAECUxXbWEGKDliEul5XezZqm";
    static private final String signName = "亦来云";
    static private final String templateCode = "SMS_172013573";

    public static boolean sendSms(String phone, String code) {
        if (null == phone) {
            System.out.println("Err AliyunSmsUtil sendSms phone number is null.");
            return false;
        }
        boolean ret = true;

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam","{\"code\":\""+ code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);

            System.out.println(response.getData());
            JSONObject obj = JSON.parseObject(response.getData());
            String rCode = obj.getString("Code");
            if(!rCode.equals("OK")){
                ret = false;
            }
        } catch (ServerException e) {
            e.printStackTrace();
            ret = false;
        } catch (ClientException e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }
}
