package com.fht.HttpClient.DataBase;

public class ConnectDataBaseRequest {
    /**
     *  sql语句
     */
    private String sql;
    /**
     *  表唯一主键
     */
    private String column_id;
    /**
     *  第一列
     */
    private String colume_1;
    /**
     *  第二列
     */
    private String colume_2;

    /**
     *
     * @param name
     */
    private String name;

    /**
     *
     * @param mobile 电话号码
     */
    private String mobile;

    /**
     *
     * @param rent_fee 月租金
     */
    private String rent_fee;

    public void setSql(String sql) {
        this.sql = sql;
    }
    public String getSql(){
        return sql;
    }

    public void setColumn_id(String column_id) {
        this.column_id = column_id;
    }

    public String getColumn_id() {
        return column_id;
    }

    public void setColume_1(String colume_int) {
        this.colume_1 = colume_int;
    }

    public String getColume_1() {
        return colume_1;
    }

    public void setColume_2(String colume_String) {
        this.colume_2 = colume_String;
    }

    public String getColume_2() {
        return colume_2;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public void setRent_fee(String rent_fee) {
        this.rent_fee = rent_fee;
    }

    public String getRent_fee() {
        return rent_fee;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }


}
