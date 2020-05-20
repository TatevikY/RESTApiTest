package client;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RESTClient {


    //1. GET method without headers
    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
        //3 thing we will get status code, responce and headers
        // this method will create one client connection
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);  // this is my request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // responsen averadarcnum
        return closeableHttpResponse;
    }

    //2. GET method with headers
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
        //3 thing we will get status code, responce and headers
        // this method will create one client connection
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);  // this is my request

        for(Map.Entry<String, String> entry: headerMap.entrySet()){
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // responsen averadarcnum
        return closeableHttpResponse;
    }
}
