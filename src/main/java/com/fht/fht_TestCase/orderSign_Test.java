package com.fht.fht_TestCase;

import net.sf.json.JSONObject;
import com.fht.HttpClient.HttpUtil;
import org.testng.annotations.Test;
import com.fht.Parameter.getMobileNo;

public class orderSign_Test {
    private static String url = "http://118.31.66.94:8080/api/order/sign";
    private static JSONObject buildOrderSignRequest(String realName,String cardNo,String mobile){
        long date = System.currentTimeMillis();
        JSONObject params = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("roomId",77151);
        param.put("housingType",2);
        param.put("monthNum",12);
        param.put("rentPrice",1000.00);
        param.put("depositPrice",1000.00);
        param.put("serviceChargePrice",100.00);
        param.put("serviceFeeType",2);
        param.put("rentQty",3);
        param.put("roomCode",200077151);
        param.put("roomName","房间A");
        param.put("depositQty",1);
        param.put("startDate",date);
        param.put("serviceFeeDetail","其中服务费包含了 网费0.01元/月，水费0.01元/月，停车费0.01元/月，保洁费0.01元/月，随租期付，每期为 1个月");
        param.put("cardNo",cardNo);
        param.put("cardType",1);
        param.put("gender",1);
        param.put("mobile",mobile);
        param.put("realName",realName);
        param.put("splitFee",35);
        param.put("splitMoney",350.00);
        param.put("customerId",108);
        param.put("orgId",183);
        params.put("params",param);
        params.put("sessionId","MTMwMDAwMDAwMDA=");
        return params;
    }

    @Test
    public void 下单测试(){
        String mobileNo = getMobileNo.getTelephone();
        JSONObject params = buildOrderSignRequest("杨橙","41128119940809001X",mobileNo);
        JSONObject result = HttpUtil.doPost(url,params);
        System.out.println("mobileNo:"+mobileNo);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));
    }
}
