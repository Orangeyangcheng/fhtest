package com.fht.HttpClient.DataBase;

public class QueryDataBaseResult {
    /**
     *  表唯一主键
     */
    private int column_id;
    /**
     *  int类型字段
     */
    private int colume_int;
    /**
     * String类型字段
     */
    private String colume_String;

    /**
     *
     * @param mobile 电话号码
     */
    private String mobile;

    /**
     *
     * @param name 姓名
     */
    private String name;

    /**
     *
     * @param rent_fee 月租金
     */
    private int rent_fee;


    public void setColumn_id(int column_id) {
        this.column_id = column_id;
    }

    public int getColumn_id() {
        return column_id;
    }

    public void setColume_int(int colume_int) {
        this.colume_int = colume_int;
    }

    public int getColume_int() {
        return colume_int;
    }

    public void setColume_String(String colume_String) {
        this.colume_String = colume_String;
    }

    public String getColume_String() {
        return colume_String;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRent_fee(int rent_fee) {
        this.rent_fee = rent_fee;
    }

    public int getRent_fee() {
        return rent_fee;
    }

}
