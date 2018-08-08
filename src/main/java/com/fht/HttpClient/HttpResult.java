package com.fht.HttpClient;

public class HttpResult {
    /**
     *  响应状态码
     */
    private int code;
    /**
     * 响应体
     */
    private String body;




    public int getCode(){return code; }

    public void setCode(int statusCode){
        this.code = code;
    }

    public String getBody(){return body;}

    public void setBody(String result){
        this.body = body;
    }
}

