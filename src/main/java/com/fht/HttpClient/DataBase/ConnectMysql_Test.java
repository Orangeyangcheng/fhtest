package com.fht.HttpClient.DataBase;

import org.testng.annotations.Test;

public class ConnectMysql_Test {
    @Test
    public void ConnectMysql (){
        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setColumn_id("id");
        connectDataBaseRequest.setColume_1("org_id");
        connectDataBaseRequest.setColume_2("org_name");
        int org_id = 1100;
        connectDataBaseRequest.setSql("SELECT id, org_id,org_name FROM fht_flying_online.temp_org where org_id = "+org_id);
        QueryDataBaseResult Result = ConnectDataBase.connectDB(connectDataBaseRequest,1);
        System.out.println("ID:"+Result.getColumn_id());
        System.out.println("org_id:"+Result.getColume_int());
        System.out.println("org_name："+Result.getColume_String());

    }

    @Test
    public void saveCustomer(){
        ConnectDataBaseRequest connectDataBaseRequest = new ConnectDataBaseRequest();
        connectDataBaseRequest.setName("name");
        connectDataBaseRequest.setRent_fee("rent_fee");
        connectDataBaseRequest.setMobile("mobile");
        connectDataBaseRequest.setSql("select name,rent_fee,mobile from fht_flying_online.ft_customer_source where id = 98 ");
        QueryDataBaseResult Result = ConnectDataBase.connectDB(connectDataBaseRequest,2);
        System.out.println("mobile:"+Result.getMobile());
        System.out.println("rent_fee:"+Result.getRent_fee());
        System.out.println("name："+Result.getName());
    }
}
