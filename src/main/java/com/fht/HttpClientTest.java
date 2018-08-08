package com.fht;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientTest {

    public static void main(String[] args) throws IOException {

            //http请求
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://www.163.com");
            httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
            httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpGet.setHeader("Connection", "keep-alive");
            //httpGet.setHeader("Cookie", "__utma=226521935.73826752.1323672782.1325068020.1328770420.6;");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            //HTTP响应头部显示
            int StatusCode=httpResponse.getStatusLine().getStatusCode();
            String StatusDescription=httpResponse.getStatusLine().getReasonPhrase();
            System.out.println("StatusCode: "+StatusCode);
            System.out.println("StatusDescription: "+StatusDescription);
            Header headers[] = httpResponse.getAllHeaders();
            for(Header h:headers)
            {
                System.out.println(h.getName()+":"+h.getValue());
            }
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }
        reader.close();
        // print result
        System.out.println(response.toString());
        */
            httpClient.close();
        }

    }

