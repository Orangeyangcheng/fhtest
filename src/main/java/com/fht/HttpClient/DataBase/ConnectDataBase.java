package com.fht.HttpClient.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDataBase {
    // TODO Auto-generated method stub
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.96.87.235:3306/";//连接数据库

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "myhome";
    static final String PASS = "myhome";

    public static QueryDataBaseResult connectDB(ConnectDataBaseRequest connectDataBaseRequest, int param) {
        Connection conn = null;
        Statement stmt = null;
        QueryDataBaseResult queryDataBaseResult = new QueryDataBaseResult();
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("=============连接数据库...=============");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println("=============实例化Statement对象...=============");
            stmt = conn.createStatement();//实例化Statement对象，用stmt接收

            ResultSet resultSet = stmt.executeQuery(connectDataBaseRequest.getSql());	//执行sql

            // 展开结果集数据库
            if (param == 1){
                while(resultSet.next()){
                    // 通过字段检索
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

            
            // 完成后关闭
            resultSet.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return queryDataBaseResult;
    }

    }

