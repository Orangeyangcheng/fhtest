package com.fht.fht_TestCase.CustomerManage;

import com.fht.HttpClient.DataBase.ConnectDataBase;
import com.fht.HttpClient.DataBase.ConnectDataBaseRequest;
import com.fht.HttpClient.DataBase.QueryDataBaseResult;
import com.fht.HttpClient.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

public class CustomerFollow {

    private static String followUrl = "http://118.31.66.94:8080/api/customer/follow/save";

    private static String sessionId = "MTgwMTIzNDU2Nzg=";

    private static JSONObject buildCustomerFollowRequest (int customerId,int followType,String sessionId){
        JSONObject param = new JSONObject();
        JSONObject params = new JSONObject();
        param.put("followType",followType);
        param.put("customerId",customerId);
        param.put("remark","脚本测试");
        params.put("params",param);
        params.put("sessionId",sessionId);
        return params;
    }

    @Test
    public void CustomerFollow_冒烟测试(){
        int customerId = 148;
        int followType = 4;
        JSONObject params = buildCustomerFollowRequest(customerId,followType,sessionId);
        JSONObject followResult = HttpUtil.doPost(followUrl,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(followResult,true));
    }

    @Test
    public void CustomerFollow_为客源添加跟进记录(){
        // 查询最新创建的一个客源
        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setCustomerId("id");
        connectDataBaseRequest.setSql(
                "select id from fht_flying_online.ft_customer_source order by gmt_create desc limit 1");
        QueryDataBaseResult queryDataBaseResult = ConnectDataBase.connectDB(connectDataBaseRequest,3);
        System.out.println("id:"+queryDataBaseResult.getCustomerId());

        int followType = 3; // 跟进类型
        JSONObject params = buildCustomerFollowRequest(queryDataBaseResult.getCustomerId(),followType,sessionId);
        JSONObject followResult = HttpUtil.doPost(followUrl,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(followResult,true));

    }

    @Test
    public void CustomerFollow_为已经签约的客源添加跟进记录(){
        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setCustomerId("id");
        // 查询已经是签约状态的客源
        connectDataBaseRequest.setSql(
                "select id from fht_flying_online.ft_customer_source where status = 2 order by gmt_create desc limit 1");
        QueryDataBaseResult queryDataBaseResult = ConnectDataBase.connectDB(connectDataBaseRequest,3);
        System.out.println("id:"+queryDataBaseResult.getCustomerId());

        int followType = 4; // 跟进类型
        JSONObject params = buildCustomerFollowRequest(queryDataBaseResult.getCustomerId(),followType,sessionId);
        JSONObject followResult = HttpUtil.doPost(followUrl,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(followResult,true));
    }



}
