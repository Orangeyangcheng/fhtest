package com.fht.HttpClient;
import com.fht.HttpClient.DataBase.ConnectDataBase;
import com.fht.HttpClient.DataBase.ConnectDataBaseRequest;
import com.fht.HttpClient.DataBase.QueryDataBaseResult;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fht.Parameter.getName;
import com.fht.Parameter.getMobileNo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient_Test {
    @Test
    public  void baiduTest(){
        HttpUtil httpUtil = new HttpUtil();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nihao","nihaoma");
        String httpResult = httpUtil.httpGet("http://www.baidu.com",params);
        System.out.print(httpResult);

    }

    @Test
    public void applogin(){
        HttpUtil httpUtil = new HttpUtil();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sign","8F4C4A8E9D850EDD9692DE38723D0543");
        params.put("appVersionNum","1.8.0");
        params.put("equType","Redmi Note 5A");
        params.put("sysVersionNum","Android");
        params.put("manufacturerBrand","xiaomi");
        params.put("timestamp","1532402395224");
        params.put("imei","865723034456260");
        params.put("geographicPosition","120.11754,30.275175");
        params.put("baseStation","nihaoma");
        params.put("baseStation",0);
        params.put("lac",0);
        params.put("v","1.3");
        params.put("reqId","queryByClassify");
        Map<String, Object> params1 = new HashMap<String, Object>();
        params1.put("mobile","18012345678");
        params1.put("password","20eabe5d64b0e216796e834f52d61fd0b70332fc");
        params.put("params",params1);
        String url = "http://test-flying-api.mdguanjia.com/api/user/loginByApp";
        String httpResult = httpUtil.httpPost(url,params);
        System.out.print(httpResult);

    }

    @Test
    public void SearchCustomer(){
        HttpUtil httpUtil = new HttpUtil();
        Map<String, Object> params1 = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customerId","1");
        params1.put("params",params);
        params1.put("sessionId","MTMwMDAwMDAwMDA=");
        String url = "https://test-flying-api.mdguanjia.com/api/customer/findById";
        String httpResult = httpUtil.httpPost(url,params1);
        System.out.println(httpResult);
    }

    @Test
    public void SearchCustomer_Test(){
        HttpUtil httpUtil = new HttpUtil();
        JSONObject params = new JSONObject();
        JSONObject param = new JSONObject();

        param.put("customerId",1);
        params.put("sessionId","MTMwMDAwMDAwMDA=");
        params.put("params",param);
        String url = "https://test-flying-api.mdguanjia.com/api/customer/findById";
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));


    }
    public static JSONObject buidSaveCustomerRequest(String name,String remark,String mobile,int fee){
        JSONObject params = new JSONObject();
        JSONObject param = new JSONObject();
        JSONObject customerArea1 = new JSONObject();
        JSONObject customerArea2 = new JSONObject();
        List customerArea = new ArrayList();

        param.put("name",name);
        param.put("gender",1);
        param.put("mobile",mobile);
        param.put("source",1);
        param.put("sourceType",2);
        param.put("rentFee",fee);
        param.put("roomType",2);
        param.put("houseType",1);
        param.put("houseDirection",1);
        param.put("houseFeature","1,2");
        param.put("remark",remark);
        customerArea1.put("zoneId",2307);
        customerArea2.put("zoneId",2308);
        customerArea.add(customerArea1);
        customerArea.add(customerArea2);
        param.put("customerAreas",customerArea);
        params.put("sessionId","MTMwMDAwMDAwMDA=");
        params.put("params",param);

        return params;
    }

    @Test
    public void SaveCustomer_Test(){
        String url = "http://118.31.66.94:8080/api/customer/save";
        HttpUtil httpUtil = new HttpUtil();
        String name = getName.getRandomName();
        String remark = "脚本测试";
        String mobile = getMobileNo.getTelephone();
        int fee = 1000;
        JSONObject params = buidSaveCustomerRequest(name,remark,mobile,fee);
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println("客源姓名："+name);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));

        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setName("name");
        connectDataBaseRequest.setRent_fee("rent_fee");
        connectDataBaseRequest.setMobile("mobile");
        connectDataBaseRequest.setSql("select name,rent_fee,mobile from fht_flying_online.ft_customer_source order by gmt_create desc limit 1");
        QueryDataBaseResult Result = ConnectDataBase.connectDB(connectDataBaseRequest,2);
        System.out.println("mobile:"+Result.getMobile());
        System.out.println("rent_fee:"+Result.getRent_fee());
        System.out.println("name："+Result.getName());

        Assert.assertTrue(Result.getMobile().equals(mobile),"电话号码不一致");
        Assert.assertTrue(Result.getRent_fee() == fee,"月租金金额不一致");
        Assert.assertTrue(Result.getName().equals(name),"姓名不一致");


    }
}
