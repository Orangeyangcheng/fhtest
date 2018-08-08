package com.fht.fht_TestCase;

import com.fht.HttpClient.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

public class houseKeeperLogin_Test {

    private static String url = "http://118.31.66.94:8080/api/user/loginByApp";

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
    public void houseKepperLogin_冒烟测试(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "18012345678";
        String password = "20eabe5d64b0e216796e834f52d61fd0b70332fc";
        JSONObject params = buildHouseKepperLoginRequest(mobile,password);
        JSONObject LoginResult = httpUtil.doPost(url,params);
        System.out.println("==========="+"输入参数"+"===========");
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(params,true));
        System.out.println("==========="+"输出参数"+"===========");
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(LoginResult,true));

        JSONObject response = JSONObject.fromObject(LoginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");
        System.out.println("sessionId:"+sessionId);
    }
}
