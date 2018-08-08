//package com.fht;
//
//
//
//import com.fht.HttpClient.HttpResult;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Customer_Test {
//    private ConnectType connectType;
//
//    @BeforeTest
//    public void init(){
//        this.connectType = connectType;
//    }
//    @Test
//    public void TestCase()throws Exception {
//        String url = "http://www.baidu.com";
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("title", "测试APIService调用新增接口");
//
//
//        HttpResult httpResult = this.connectType.doGet(url,map);
//
//        System.out.println(httpResult.getCode());
//        System.out.println(httpResult.getBody());
//    }
//}
//
