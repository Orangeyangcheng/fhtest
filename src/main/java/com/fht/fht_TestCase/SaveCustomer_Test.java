package com.fht.fht_TestCase;

import com.fht.HttpClient.DataBase.ConnectDataBase;
import com.fht.HttpClient.DataBase.ConnectDataBaseRequest ;
import com.fht.HttpClient.HttpUtil;
import com.fht.HttpClient.DataBase.QueryDataBaseResult;
import com.fht.Parameter.getMobileNo;
import com.fht.Parameter.getName;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SaveCustomer_Test {

    private static String url = "http://118.31.66.94:8080/api/customer/save";

    private static String loginUrl = "http://118.31.66.94:8080/api/user/loginByApp";

    private static String sessionId = " MTMwMDAwMDAwMDA=";

    public static JSONObject buidSaveCustomerRequest(
            String name, String remark, String mobile, int fee,String rentMin ,String rentMax,String sessionId){
        JSONObject params = new JSONObject();
        JSONObject param = new JSONObject();
        JSONObject customerArea1 = new JSONObject();
        JSONObject customerArea2 = new JSONObject();
        List customerArea = new ArrayList();

        param.put("name",name);
        param.put("gender",1);
        param.put("mobile",mobile);
        param.put("source",1);
        param.put("sourceType",1);
        param.put("rentFee",fee);
        param.put("roomType",3);
        param.put("houseType",1);
        param.put("houseDirection",1);
        param.put("houseFeature","1,2");
        param.put("remark",remark);
        param.put("rentMin",rentMin);
        param.put("rentMax",rentMax);
        customerArea1.put("zoneId",2307);
        customerArea2.put("zoneId",2308);
        customerArea.add(customerArea1);
        customerArea.add(customerArea2);
        param.put("customerAreas",customerArea);
        params.put("sessionId",sessionId);
        params.put("params",param);

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
    public void SaveCustomer_冒烟测试() {
        HttpUtil httpUtil = new HttpUtil();
        String name = getName.getRandomName();
        String remark = "脚本测试";
        String mobile = getMobileNo.getTelephone();
        int fee = 1000;
        String rentMin = "1000";
        String rentMax = "1500";
        JSONObject params = buidSaveCustomerRequest(name,remark,mobile,fee,rentMin,rentMax,sessionId);
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println("客源姓名："+name);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));

        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setName("name");
        connectDataBaseRequest.setRent_fee("rent_fee");
        connectDataBaseRequest.setMobile("mobile");
        connectDataBaseRequest.setSql(
                "select name,rent_fee,mobile from fht_flying_online.ft_customer_source order by gmt_create desc limit 1");
        QueryDataBaseResult Result = ConnectDataBase.connectDB(connectDataBaseRequest,2);
        System.out.println("mobile:"+Result.getMobile());
        System.out.println("rent_fee:"+Result.getRent_fee());
        System.out.println("name："+Result.getName());

        Assert.assertTrue(Result.getMobile().equals(mobile),"电话号码不一致");
        Assert.assertTrue(Result.getRent_fee() == fee,"月租金金额不一致");
        Assert.assertTrue(Result.getName().equals(name),"姓名不一致");
    }

    @Test
    public void SaveCustomer_姓名为空(){
        HttpUtil httpUtil = new HttpUtil();
        String name = null;  //姓名为空
        String remark = "脚本测试";
        String mobile = getMobileNo.getTelephone();
        int fee = 1000;
        String rentMin = "1000";
        String rentMax = "1500";
        JSONObject params = buidSaveCustomerRequest(name,remark,mobile,fee,rentMin,rentMax,sessionId);
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));
        JSONObject response = JSONObject.fromObject(result);
        Assert.assertTrue(response.getString("success").equals("false"));
        Assert.assertTrue(response.getString("message").equals("请输入姓名"));

    }

    @Test
    public void SaveCustomer_租金id为负(){
        HttpUtil httpUtil = new HttpUtil();
        String name = getName.getRandomName();
        String remark = "脚本测试";
        String mobile = getMobileNo.getTelephone();
        int fee = -1;
        String rentMin = "1000";
        String rentMax = "1500";
        JSONObject params = buidSaveCustomerRequest(name,remark,mobile,fee,rentMin,rentMax,sessionId);
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
        Assert.assertTrue(Result.getRent_fee()== -1,"月租金金额不一致");
        Assert.assertTrue(Result.getName().equals(name),"姓名不一致");
    }

    @Test
    public void SaveCustomer_最小租金大于最大租金(){
        HttpUtil httpUtil = new HttpUtil();
        String name = getName.getRandomName();
        String remark = "脚本测试";
        String mobile = getMobileNo.getTelephone();
        int fee = 1;
        String rentMin = "1500";
        String rentMax = "1000";
        JSONObject params = buidSaveCustomerRequest(name,remark,mobile,fee,rentMin,rentMax,sessionId);
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println("客源姓名："+name);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));
        JSONObject response = JSONObject.fromObject(result);
        Assert.assertTrue(response.getString("success").equals("false"));
        Assert.assertTrue(response.getString("message").equals("月租金最低价格要低于最高价格!"));
    }

    @Test
    public void SaveCustomer_手机号为空() {
        HttpUtil httpUtil = new HttpUtil();
        String name = getName.getRandomName();
        String remark = "脚本测试";
        String mobile = null;//手机号设置为空
        int fee = 1000;
        String rentMin = "1000";
        String rentMax = "1500";
        JSONObject params = buidSaveCustomerRequest(name,remark,mobile,fee,rentMin,rentMax,sessionId);
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println("客源姓名："+name);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));

        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setName("name");
        connectDataBaseRequest.setRent_fee("rent_fee");
        connectDataBaseRequest.setMobile("mobile");
        connectDataBaseRequest.setSql(
                "select name,rent_fee,mobile from fht_flying_online.ft_customer_source order by gmt_create desc limit 1");
        QueryDataBaseResult Result = ConnectDataBase.connectDB(connectDataBaseRequest,2);
        System.out.println("mobile:"+Result.getMobile());
        System.out.println("rent_fee:"+Result.getRent_fee());
        System.out.println("name："+Result.getName());

        Assert.assertTrue(Result.getRent_fee() == fee,"月租金金额不一致");
        Assert.assertTrue(Result.getName().equals(name),"姓名不一致");
    }

    @Test
    public void SaveCustomer_最小租金为负数() {
        HttpUtil httpUtil = new HttpUtil();
        String name = getName.getRandomName();
        String remark = "脚本测试";
        String mobile = getMobileNo.getTelephone();
        int fee = 1000;
        String rentMin = "-1000"; // 最小租金设为负数
        String rentMax = "1";
        JSONObject params = buidSaveCustomerRequest(name,remark,mobile,fee,rentMin,rentMax,sessionId);
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println("客源姓名："+name);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));

        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setName("name");
        connectDataBaseRequest.setRent_fee("rent_fee");
        connectDataBaseRequest.setMobile("mobile");
        connectDataBaseRequest.setSql(
                "select name,rent_fee,mobile from fht_flying_online.ft_customer_source order by gmt_create desc limit 1");
        QueryDataBaseResult Result = ConnectDataBase.connectDB(connectDataBaseRequest,2);
        System.out.println("mobile:"+Result.getMobile());
        System.out.println("rent_fee:"+Result.getRent_fee());
        System.out.println("name："+Result.getName());

        Assert.assertTrue(Result.getMobile().equals(mobile),"电话号码不一致");
        Assert.assertTrue(Result.getRent_fee() == fee,"月租金金额不一致");
        Assert.assertTrue(Result.getName().equals(name),"姓名不一致");
    }

    @Test
    public void SaveCustomer_在城市管家APP保存客源(){
        HttpUtil httpUtil = new HttpUtil();
        String mobile = "18012345678";
        String password = "20eabe5d64b0e216796e834f52d61fd0b70332fc";
        JSONObject param = buildHouseKepperLoginRequest(mobile,password);
        JSONObject loginResult = HttpUtil.doPost(loginUrl,param);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(loginResult,true));

        JSONObject response = JSONObject.fromObject(loginResult);
        JSONObject data = response.getJSONObject("data");
        String sessionId = data.getString("sessionId");

        String name = getName.getRandomName();
        String remark = "脚本测试";
        String mobileNo = getMobileNo.getTelephone();
        int fee = 1000;
        String rentMin = "1000";
        String rentMax = "1500";
        JSONObject params = buidSaveCustomerRequest(name,remark,mobileNo,fee,rentMin,rentMax,sessionId);
        JSONObject result = httpUtil.doPost(url,params);
        System.out.println("客源姓名："+name);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));

        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setName("name");
        connectDataBaseRequest.setRent_fee("rent_fee");
        connectDataBaseRequest.setMobile("mobile");
        connectDataBaseRequest.setSql(
                "select name,rent_fee,mobile from fht_flying_online.ft_customer_source order by gmt_create desc limit 1");
        QueryDataBaseResult Result = ConnectDataBase.connectDB(connectDataBaseRequest,2);
        System.out.println("mobile:"+Result.getMobile());
        System.out.println("rent_fee:"+Result.getRent_fee());
        System.out.println("name："+Result.getName());

        Assert.assertTrue(Result.getMobile().equals(mobileNo),"电话号码不一致");
        Assert.assertTrue(Result.getRent_fee() == fee,"月租金金额不一致");
        Assert.assertTrue(Result.getName().equals(name),"姓名不一致");


    }


}
