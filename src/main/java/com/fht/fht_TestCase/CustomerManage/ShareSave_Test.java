package com.fht.fht_TestCase.CustomerManage;

import com.fht.HttpClient.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;
import com.fht.Parameter.getMobileNo;

public class ShareSave_Test {

    private static String shareSaveUrl = "http://118.31.66.94:8080/api/share/save";

    private static String shareOpenSave = "http://118.31.66.94:8080/api/share/open";

    private static String loginUrl = "http://118.31.66.94:8080/api/user/loginByApp";

    private static JSONObject buildShareSaveRequest (int relateId,int shareType,String key,String mobile,String sessionId){
        JSONObject shareSaveParam = new JSONObject();
        JSONObject params = new JSONObject();
        shareSaveParam.put("relateId",relateId);
        shareSaveParam.put("shareType",shareType);
        shareSaveParam.put("key",key);
        shareSaveParam.put("mobile",mobile);
        params.put("params",shareSaveParam);
        params.put("sessionId",sessionId);
        return params;
    }

    private static JSONObject buildHouseKepperLoginRequest(String mobile,String password){
        JSONObject params = new JSONObject();
        JSONObject param = new JSONObject();
        Long timeStamp = System.currentTimeMillis();
        param.put("mobile",mobile);
        param.put("password",password);
        params.put("method","method");
        params.put("params",param);
        params.put("v","1.0");
        params.put("timestamp",timeStamp);
        params.put("sign","8F4C4A8E9D850EDD9692DE38723D0543");
        return params;
    }

    @Test
    public void ShareSave_保存分享人信息(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "18012345678";
        String password = "20eabe5d64b0e216796e834f52d61fd0b70332fc";
        JSONObject param = buildHouseKepperLoginRequest(mobile,password);
        JSONObject loginResult = HttpUtil.doPost(loginUrl,param);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(loginResult,true));

        JSONObject response = JSONObject.fromObject(loginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");

        int relateId = 708169;
        int shareType = 1;
        String key =System.currentTimeMillis()+"脚本测试";
        String SaveMobile = getMobileNo.getTelephone();
        System.out.println("mobile:"+mobile);
        JSONObject shareSaveParam = buildShareSaveRequest(relateId,shareType,key,SaveMobile,sessionId);
        JSONObject shareSaveResult = httpUtil.doPost(shareSaveUrl,shareSaveParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(shareSaveResult,true));


    }

    @Test
    public void ShareSave_分享后打开(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "18012345678";
        String password = "20eabe5d64b0e216796e834f52d61fd0b70332fc";
        JSONObject param = buildHouseKepperLoginRequest(mobile,password);
        JSONObject loginResult = HttpUtil.doPost(loginUrl,param);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(loginResult,true));

        JSONObject response = JSONObject.fromObject(loginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");

        int relateId = 708169;
        int shareType = 5;
        String key =System.currentTimeMillis()+"脚本测试";
        String SaveMobile = getMobileNo.getTelephone();
        System.out.println("mobile:"+mobile);
        JSONObject shareSaveParam = buildShareSaveRequest(relateId,shareType,key,SaveMobile,sessionId);
        JSONObject shareSaveResult = httpUtil.doPost(shareSaveUrl,shareSaveParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(shareSaveResult,true));

        JSONObject shareOpenParam = new JSONObject();
        JSONObject shareKey = new JSONObject();
        shareKey.put("key",key);
        shareOpenParam.put("params",shareKey);
        JSONObject shareOpenSaveResult = httpUtil.doPost(shareOpenSave,shareOpenParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(shareOpenSaveResult,true));

    }

    @Test
    public void ShareSave_多次分享后打开(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "18012345678";
        String password = "20eabe5d64b0e216796e834f52d61fd0b70332fc";
        JSONObject param = buildHouseKepperLoginRequest(mobile,password);
        JSONObject loginResult = HttpUtil.doPost(loginUrl,param);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(loginResult,true));

        JSONObject response = JSONObject.fromObject(loginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");

        int relateId = 708169;
        int shareType = 1;
        String key =System.currentTimeMillis()+"脚本测试";
        String SaveMobile = getMobileNo.getTelephone();
        System.out.println("mobile:"+mobile);
        JSONObject shareSaveParam = buildShareSaveRequest(relateId,shareType,key,SaveMobile,sessionId);
        JSONObject shareSaveResult = httpUtil.doPost(shareSaveUrl,shareSaveParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(shareSaveResult,true));//第一次分享

        JSONObject shareOpenParam = new JSONObject();
        JSONObject shareKey = new JSONObject();
        shareKey.put("key",key);
        shareOpenParam.put("params",shareKey);
        JSONObject shareOpenSaveResult1 = httpUtil.doPost(shareOpenSave,shareOpenParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(shareOpenSaveResult1,true));

        JSONObject shareOpenSaveResult2 = httpUtil.doPost(shareOpenSave,shareOpenParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(shareOpenSaveResult2,true));

        JSONObject shareOpenSaveResult3 = httpUtil.doPost(shareOpenSave,shareOpenParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(shareOpenSaveResult3,true));

    }

}
