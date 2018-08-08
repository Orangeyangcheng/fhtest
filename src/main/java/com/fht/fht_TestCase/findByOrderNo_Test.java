package com.fht.fht_TestCase;

import com.fht.HttpClient.HttpUtil;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

public class findByOrderNo_Test {

    private static String url = "http://118.31.66.94:8080/api/order/findByOrderNo";

    private static JSONObject buildFindOrderRequest(String orderNo){
        JSONObject param = new JSONObject();
        JSONObject params = new JSONObject();
        param.put("orderNo",orderNo);
        params.put("params",param);
        params.put("sessionId","MTMwMDAwMDAwMDA=");
        return params;
    }
    @Test
    public void findByOrderNo_冒烟测试(){
        JSONObject params = buildFindOrderRequest("D20180801112259019827");
        JSONObject result = HttpUtil.doPost(url,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));
    }
}
