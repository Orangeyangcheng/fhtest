package com.fht.fht_TestCase;

import com.fht.HttpClient.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class houseKeeper_OCR {

    private static String url = "http://118.31.66.94:8080/api/upload//OCR";

    private static JSONObject bulidRequest(String url){
        JSONObject params = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("url",url);
        params.put("appVersionNum","1.9.0");
        params.put("baseStation","0");
        params.put("equType","Redmi Note 5A");
        params.put("geographicPosition","120.11752,30.2751");
        params.put("imei","865723034456260");
        params.put("lac","0");
        params.put("manufacturerBrand","xiaomi");
        params.put("method","ocrIdentify");
        params.put("params",param);
        params.put("reqId","ocrIdentify");
        params.put("sessionId","MTMwMDAwMDAwMDA=");
        params.put("sign","8F4C4A8E9D850EDD9692DE38723D0543");
        params.put("sysVersionNum","Android");
        params.put("timestamp","1532602647292");
        params.put("v","1.9.0");
        return params;
    }

    @Test
    public void houseKeeperOCR_test(){
        String imageUrl = "https://fh-mjgy-test.oss-cn-hangzhou.aliyuncs.com/20180802133934735610";
        JSONObject params = bulidRequest(imageUrl);
        JSONObject result = HttpUtil.doPost(url,params);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(result,true));
        Assert.assertTrue(!result.isNullObject());
        JSONObject response = JSONObject.fromObject(result);
        Assert.assertTrue(response.getString("success").equals("true"));

        JSONObject data = response.getJSONObject("data");
        Assert.assertTrue(data.getString("name").equals("季甜甜"),"姓名不一致");
        Assert.assertTrue(data.getString("id_card_number").equals("320803199210123665"),"身份证号不一致");
        Assert.assertTrue(data.getString("address").equals("江苏省淮安市淮安区复兴镇南季村东季二组39号"));
        Assert.assertTrue(data.getString("race").equals("汉"));
        Assert.assertTrue(data.getString("gender").equals("女"));


    }
}
