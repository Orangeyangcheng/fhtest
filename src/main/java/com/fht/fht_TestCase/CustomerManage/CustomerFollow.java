package com.fht.fht_TestCase.CustomerManage;

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
    public void CustomerFollow_客源跟进冒烟测试(){
        int customerId = 148;
        int fowllowType = 4;
        JSONObject params = buildCustomerFollowRequest(customerId,fowllowType,sessionId);
        JSONObject followResult = HttpUtil.doPost(followUrl,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(followResult,true));
    }
}
