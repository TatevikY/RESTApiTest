package tests;

import base.TestBase;
import client.RESTClient;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.TestUtil;

import java.io.IOException;
import java.util.HashMap;

public class GetApiTest extends TestBase {
    TestBase testBase;
    String webUrl;
    String apiUrl;
    String url;
    RESTClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException{
        testBase = new TestBase();
        webUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");

        url = webUrl + apiUrl;

    }

    @Test(priority = 1)
    public void getAPITestWithOutHeaders() throws ClientProtocolException, IOException {
        restClient = new RESTClient();
        closeableHttpResponse = restClient.get(url);

        //status codena get anum store anum statusCode variable-um u print a anum
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); //get th status code of a call, 200, 400, 404 etc
//        System.out.println("Status code is ------>" + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "The status code is not equal to 200");

        // get enq anum responce stringy
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        //entity pti uzneq bayc qani vor responceuma grum enq responce.get entity(responce stringey)

        //  responseString convert to json object
        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println("Response JSON is ------>" + responseJSON);

        //_____________________________________________________________________________SINGLE VALUE ASSERTION_____________________________________________________________________________
        //per_page
        String perPageValue = TestUtil.getValueByJPath(responseJSON,"/per_page");
        System.out.println("perPageValue is ------>" + perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 6, "The perPageValue not equal to 6");

        //total
        String totalValue = TestUtil.getValueByJPath(responseJSON,"/total");
        System.out.println("totalValue is ------>" + totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12, "The totalValue not equal to 12");

        //first_name from data array
        String id = TestUtil.getValueByJPath(responseJSON,"/data[1]/id");
        String email = TestUtil.getValueByJPath(responseJSON,"/data[1]/email");
        String first_name = TestUtil.getValueByJPath(responseJSON,"/data[1]/first_name");
        String last_name = TestUtil.getValueByJPath(responseJSON,"/data[1]/last_name");
        String avatar = TestUtil.getValueByJPath(responseJSON,"/data[1]/avatar");

        Assert.assertEquals(Integer.parseInt(id), 2, "The id not equal to 8");
        Assert.assertEquals(email, "janet.weaver@reqres.in", "The email not equal to email");
        Assert.assertEquals(first_name, "Janet", "The first_name not equal to Janet");
        Assert.assertEquals(last_name, "Weaver", "The last_name not equal to Weaver");
        Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg", "The avatar not equal to avatar");

        //headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers are ------>" + allHeaders);

        System.out.println("Header1 is ------>" + headersArray);

    }


    @Test(priority = 2)
    public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
        restClient = new RESTClient();
        //create hashmap in order to pass the headers needed for call
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        //if the call is XML then
        //headerMap.put("Content-Type", "application/xml");
        //if there are several header to pass then create new headerMap for each
        //headerMap.put("username", "sample_username");

        //pass the header map with url
        closeableHttpResponse = restClient.get(url, headerMap);

        //status codena get anum store anum statusCode variable-um u print a anum
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); //get th status code of a call, 200, 400, 404 etc
//        System.out.println("Status code is ------>" + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "The status code is not equal to 200");

        // get enq anum responce stringy
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        //entity pti uzneq bayc qani vor responceuma grum enq responce.get entity(responce stringey)

        //  responseString convert to json object
        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println("Response JSON is ------>" + responseJSON);

        //_____________________________________________________________________________SINGLE VALUE ASSERTION_____________________________________________________________________________
        //per_page
        String perPageValue = TestUtil.getValueByJPath(responseJSON,"/per_page");
        System.out.println("perPageValue is ------>" + perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 6, "The perPageValue not equal to 6");

        //total
        String totalValue = TestUtil.getValueByJPath(responseJSON,"/total");
        System.out.println("totalValue is ------>" + totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12, "The totalValue not equal to 12");

        //first_name from data array
        String id = TestUtil.getValueByJPath(responseJSON,"/data[1]/id");
        String email = TestUtil.getValueByJPath(responseJSON,"/data[1]/email");
        String first_name = TestUtil.getValueByJPath(responseJSON,"/data[1]/first_name");
        String last_name = TestUtil.getValueByJPath(responseJSON,"/data[1]/last_name");
        String avatar = TestUtil.getValueByJPath(responseJSON,"/data[1]/avatar");

        Assert.assertEquals(Integer.parseInt(id), 2, "The id not equal to 8");
        Assert.assertEquals(email, "janet.weaver@reqres.in", "The email not equal to email");
        Assert.assertEquals(first_name, "Janet", "The first_name not equal to Janet");
        Assert.assertEquals(last_name, "Weaver", "The last_name not equal to Weaver");
        Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg", "The avatar not equal to avatar");

        //headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers are ------>" + allHeaders);

        System.out.println("Header1 is ------>" + headersArray);

    }
}
