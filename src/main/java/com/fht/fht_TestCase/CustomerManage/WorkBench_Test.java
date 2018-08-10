package com.fht.fht_TestCase.CustomerManage;

import com.fht.HttpClient.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkBench_Test {

    private static String workBenchUrl = "http://118.31.66.94:8080/api/workbench/statistics";

    private static String workBenchRankUrl = "http://118.31.66.94:8080/api/workbench/rank";

    private static String loginUrl = "http://118.31.66.94:8080/api/user/loginByApp";

    private static JSONObject buildHouseKepperLoginRequest(String mobile, String password){
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

    private static JSONObject buildWorkBenchRequest(String sessionId){
        JSONObject workBenchRequest = new JSONObject();
        workBenchRequest.put("sessionId",sessionId);
        return workBenchRequest;
    }

    @Test
    public void workBench_绩效金额查询(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "18012345678";
        String password = "20eabe5d64b0e216796e834f52d61fd0b70332fc";
        JSONObject param = buildHouseKepperLoginRequest(mobile,password);
        JSONObject loginResult = httpUtil.doPost(loginUrl,param);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(loginResult,true));

        JSONObject response = JSONObject.fromObject(loginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");

        JSONObject WorkBenchRequest = buildWorkBenchRequest(sessionId);
        JSONObject searchWorkBenchResult = httpUtil.doPost(workBenchUrl,WorkBenchRequest);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(searchWorkBenchResult,true));
    }

    @Test
    public void workBench_sessionId为空(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "18012345678";
        String password = "20eabe5d64b0e216796e834f52d61fd0b70332fc";
        JSONObject param = buildHouseKepperLoginRequest(mobile,password);
        JSONObject loginResult = httpUtil.doPost(loginUrl,param);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(loginResult,true));

        JSONObject response = JSONObject.fromObject(loginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");

        JSONObject workBenchRequest = new JSONObject();
        workBenchRequest.put("sessionId",null); //sessionId为空
        JSONObject searchWorkBenchResult = httpUtil.doPost(workBenchUrl,workBenchRequest);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(searchWorkBenchResult,true));
        JSONObject errorMessage = JSONObject.fromObject(searchWorkBenchResult);
        Assert.assertTrue(errorMessage.getString("success").equals("false"));
        Assert.assertTrue(errorMessage.getString("message").equals("sessionId不允许为空"));
    }

    @Test
    public void workBench_绩效排行查询(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "13175112091";
        String password = "942b1603ef74d364171b432619079b2fdd2faac7";
        JSONObject param = buildHouseKepperLoginRequest(mobile,password);
        JSONObject loginResult = httpUtil.doPost(loginUrl,param);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(loginResult,true));

        JSONObject response = JSONObject.fromObject(loginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");

        JSONObject WorkBenchRequest = buildWorkBenchRequest(sessionId);
        JSONObject searchWorkBenchRank = httpUtil.doPost(workBenchRankUrl,WorkBenchRequest);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(searchWorkBenchRank,true));

    }

    @Test
    public void workBench_绩效排行查询sessionId为空(){
        HttpUtil httpUtil = new HttpUtil();
        JSONObject WorkBenchRequest = buildWorkBenchRequest(null);
        JSONObject searchWorkBenchRank = httpUtil.doPost(workBenchRankUrl,WorkBenchRequest);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(searchWorkBenchRank,true));
        JSONObject errorMessage = JSONObject.fromObject(searchWorkBenchRank);
        Assert.assertTrue(errorMessage.getString("success").equals("false"));
        Assert.assertTrue(errorMessage.getString("message").equals("sessionId不允许为空"));

    }
}
