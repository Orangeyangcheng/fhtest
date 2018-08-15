package com.fht.fht_TestCase.CustomerManage;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class customerLookSave {

    private static String lookSaveUrl = "http://118.31.66.94:8080/api/customer/look/save";

    private static JSONObject buildLookSaveRequest(int customerId,int lookDate,String sessionId){
        JSONObject params  = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("customerId",customerId);
        param.put("lookDate",lookDate);
        List customerLookInfos = new ArrayList();
        JSONObject roomParam_A = new JSONObject();
        roomParam_A.put("roomCode","123");
        roomParam_A.put("roomName","天天小区-3幢-1单元-2楼-203号房间A");
        JSONObject roomParam_B = new JSONObject();
        roomParam_B.put("roomCode","125");
        roomParam_B.put("roomName","天天小区-3幢-1单元-2楼-203号房间B");
        return null;
    }
}
