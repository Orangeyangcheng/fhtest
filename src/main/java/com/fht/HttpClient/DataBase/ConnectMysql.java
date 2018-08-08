package com.fht.HttpClient.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectMysql {
    // TODO Auto-generated method stub
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.96.87.235:3306/hms_dev";//连接数据库

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "myhome";
    static final String PASS = "myhome";

    public static void main(String[] args) {
        Connection conn = null;//设置conn全局变量
        Statement stmt = null;//设置stmt全局变量
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();//实例化Statement对象，用stmt接收
            String sql;//定义一个string类型的数据变量sql
            sql = "SELECT id, estate_id FROM msg_manage";//用sql接收数据库语句
            ResultSet rs = stmt.executeQuery(sql);	//执行sql

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                int estate_id = rs.getInt("estate_id");
//                String url = rs.getString("url");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + estate_id);
//                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
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
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
