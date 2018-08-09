package com.fht.fht_TestCase.CustomerManage;

import com.fht.HttpClient.HttpUtil;
import com.fht.Parameter.getName;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateHouse_Test {

    private static String CreateHouseUrl = "http://118.31.66.94:8080";

    private static JSONObject buildCreateHouseRequest(String buildingName,int orgOwnId,List RoomParam,JSONObject subdistrictInfo){
        JSONObject params = new JSONObject();
        params.put("boardCount",1);
        params.put("buildingName",buildingName);
        params.put("chamberCount",3);
        params.put("decorationDegree",3);
        params.put("facilityItems","3,5,4,6");
        params.put("floorAmount",99);
        params.put("floorName","88层");
        params.put("houseArea",101.0);
        params.put("houseDirection",1);
        params.put("orgOwnId",orgOwnId);
        params.put("rooms",RoomParam);
        params.put("subdistrictInfo",subdistrictInfo);
        params.put("toiletCount",1);
        return params;
    }
    private static List buildRoomParam(){
        List roomList = new ArrayList();
        JSONObject room_A = new JSONObject();
        room_A.put("room_attributes","1,2,3,4");
        room_A.put("roomNo","房间A");
        room_A.put("roomArea",66);
        room_A.put("facility_items","1,2,3,4,5");
        room_A.put("houseDirection","1");
        JSONObject room_B = new JSONObject();
        room_B.put("room_attributes","1,2,3,4");
        room_B.put("roomNo","房间B");
        room_B.put("roomArea",55);
        room_B.put("facility_items","1,2,3,4,5");
        room_B.put("houseDirection","1");
        JSONObject room_C = new JSONObject();
        room_C.put("room_attributes","1,2,3,4");
        room_C.put("roomNo","房间C");
        room_C.put("roomArea",44);
        room_C.put("facility_items","1,2,3,4,5");
        room_C.put("houseDirection","1");
        roomList.add(room_A);
        roomList.add(room_B);
        roomList.add(room_C);
        return roomList;
    }
    private static JSONObject buildSubdistrictInfo(String subdistrictName,String subdistrictAddress,int zoneId){
        JSONObject subdistrictInfo = new JSONObject();
        subdistrictInfo.put("provinceId",310000);
        subdistrictInfo.put("cityId",310100);
        subdistrictInfo.put("addrRegionId",310115);
        subdistrictInfo.put("subdistrictName",subdistrictName);
        subdistrictInfo.put("subdistrictAddress",subdistrictAddress);
        subdistrictInfo.put("zoneId",zoneId);
        subdistrictInfo.put("longitude","121.49228986404418");
        subdistrictInfo.put("latitude","31.17173032173108");
        return subdistrictInfo;
    }

    @Test
    public void CreateHouse_冒烟测试(){
        List roomList = buildRoomParam();
        String subdistrictName = getName.getRandomName();
        String subdistrictAddress = "浦东新区测试路1024号";
        int zoneId = 2434;
        JSONObject subdistrictInfo = buildSubdistrictInfo(subdistrictName,subdistrictAddress,zoneId);
        String buildingName = getName.getRandomName();
        int orgOwnId = 1685;
        JSONObject CreateHouseParam = buildCreateHouseRequest(buildingName,orgOwnId,roomList,subdistrictInfo);
        JSONObject CreateHouseResult = HttpUtil.doPost(CreateHouseUrl,CreateHouseParam);
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(CreateHouseResult,true));
    }
}
