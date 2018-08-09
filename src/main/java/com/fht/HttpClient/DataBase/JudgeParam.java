package com.fht.HttpClient.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JudgeParam extends ConnectDataBase{

    public void judgeByParam(int param,ResultSet resultSet,ConnectDataBaseRequest connectDataBaseRequest,QueryDataBaseResult queryDataBaseResult) throws SQLException {
        if (param == 1){
            while(resultSet.next()){
                queryDataBaseResult.setColumn_id(resultSet.getInt(connectDataBaseRequest.getColumn_id()));
                queryDataBaseResult.setColume_int(resultSet.getInt(connectDataBaseRequest.getColume_1()));
                queryDataBaseResult.setColume_String(resultSet.getString(connectDataBaseRequest.getColume_2()));
            }
        }
        else if (param == 2){
            while (resultSet.next()){
                queryDataBaseResult.setName(resultSet.getString(connectDataBaseRequest.getName()));
                queryDataBaseResult.setRent_fee(resultSet.getInt(connectDataBaseRequest.getRent_fee()));
                queryDataBaseResult.setMobile(resultSet.getString(connectDataBaseRequest.getMobile()));

            }
        }
        else if (param == 3){
            while (resultSet.next()){
                queryDataBaseResult.setCustomerId(resultSet.getInt(connectDataBaseRequest.getCustomerId()));
            }
        }
    }
}
