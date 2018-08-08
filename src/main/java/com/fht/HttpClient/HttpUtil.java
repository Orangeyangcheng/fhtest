package com.fht.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import net.sf.json.JSONArray;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;


public class HttpUtil {

    //连接池
    private static PoolingHttpClientConnectionManager connectionMgr;
    //超时时间
    private static final int MAX_TIMEOUT = 7000;

    private static RequestConfig requestConfig;
    static{
        //设置连接池
        connectionMgr = new PoolingHttpClientConnectionManager();
        //设置连接池大小
        connectionMgr.setMaxTotal(100);
        connectionMgr.setDefaultMaxPerRoute(connectionMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        //设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        //设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        //设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();

    }

    /**
     *   GET 请求       带输入参数
     * @param Url      请求host地址
     * @param params   参数
     * @return
     */
    public static String httpGet(String Url,Map<String, Object>params)
    {
        //返回结果
        String result = null;
//        HttpResult httpResult = new HttpResult();
        //拼接url
        StringBuilder builder = new StringBuilder(Url);
        if (Url.contains("?")) {
            builder.append("&");
        }else{
            builder.append("?");
        }
        int i=0;
        for (String key : params.keySet()) {
            if (i != 0 ) {
                builder.append("&");
            }
            builder.append(key);
            builder.append("=");
            builder.append(params.get(key));

            i++;
        }

        String apiUrl = builder.toString();
        apiUrl = Url.replaceAll(" ","%20");
        apiUrl = Url.replaceAll("&","%26");
        //创建client
        HttpClient client = HttpClients.createDefault();

        try {
            HttpGet get = new HttpGet(apiUrl);
            HttpResponse response = client.execute(get);
            //获取请求状态码
            int statusCode = response.getStatusLine().getStatusCode();
//            httpResult.setCode(statusCode);
            System.out.println(statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity,"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        httpResult.setBody(result);
        return result;
    }


    /**
     *   POST 请求
     * @param url             请求url
     * @param params          post提交参数
     * @return
     */
    public static String httpPost(String url,Map<String, Object>params)
    {
        HttpClient client = HttpClients.createDefault();
        String result = null;

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        }else{
            builder.append("?");
        }
        int i=0;
        for (String key : params.keySet()) {
            if (i != 0 ) {
                builder.append("&");
            }
            builder.append(key);
            builder.append("=");
            builder.append(params.get(key));

            i++;
        }

        String apiUrl = builder.toString();
        apiUrl = url.replaceAll(" ","%20");
        apiUrl = url.replaceAll("&","%26");
        try {
            HttpPost post = new HttpPost(apiUrl);
            //添加post提交参数
            ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
            for(Map.Entry<String, Object> entry:params.entrySet())
            {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }

            post.setEntity(new UrlEncodedFormEntity(pairList,"UTF-8"));

            HttpResponse response = client.execute(post);
            //获取状态码
            int statueCode = response.getStatusLine().getStatusCode();
            System.out.println(statueCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * post请求
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPost(String url, JSONObject json){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.addHeader(HTTP.CONTENT_TYPE,"application/json");
        JSONObject response = null;
        try {
            StringEntity reqEntity = new StringEntity(json.toString(),HTTP.UTF_8);
            reqEntity.setContentEncoding("UTF-8");
            reqEntity.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(reqEntity);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
