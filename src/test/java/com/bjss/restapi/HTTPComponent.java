package com.bjss.restapi;

import com.bjss.POJO.User;
import com.bjss.utilities.Common;
import gherkin.deps.com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTTPComponent {

    static final Logger logger = LogManager.getLogger(HTTPComponent.class.getName());
    final static String SERVER_URL = "https://reqres.in/api/users";
    static ReqRes oReqRes;

    public static CloseableHttpClient buildHttpClient() {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultHeaders(setDefaultHeaders())
                .build();
        return httpclient;

    }

    public static List<Header> setDefaultHeaders() {

        ArrayList<Header> al = new ArrayList<Header>();
        al.add(new BasicHeader(
                HttpHeaders.CONTENT_TYPE, "application/json"));

        Header header[] = new Header[al.size()];
        header = al.toArray(header);

        return al;

    }


    public static String getHTTPResponse(final Object obj) throws java.io.IOException {
        String responseBody = "";
        CloseableHttpClient httpclient = buildHttpClient();
        try {
    /*       final HttpGet httpget = new HttpGet(uri);
           System.out.println("Executing request " + httpget.getRequestLine());
    */       // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    return HandleResponse(response, obj);

                }

            };

            if (obj instanceof HttpGet)
                responseBody = httpclient.execute((HttpGet) obj, responseHandler);
            else if (obj instanceof HttpPost)
                responseBody = httpclient.execute((HttpPost) obj, responseHandler);
            else if (obj instanceof HttpDelete)
                responseBody = httpclient.execute((HttpDelete) obj, responseHandler);
            else if (obj instanceof HttpPut)
                responseBody = httpclient.execute((HttpPut) obj, responseHandler);

            // System.out.println("----------------------------------------");
            //   System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
        return responseBody;
    }

    public static String HandleResponse(final HttpResponse response, Object obj) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        StringBuilder str = new StringBuilder();
        StringBuilder strReq = new StringBuilder();
        String reqMethod = "";
        HttpGet httpget = null;
        HttpPost httppost = null;
        HttpDelete httpdelete = null;
        HttpPut httpput = null;
        Header[] headers = null;
        String url = "";
        // System.out.println(status);
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            for (Header header : response.getAllHeaders()) {
                //  logger.info(header.getName() + ":" + header.getValue());
                str.append(header.getName() + ":" + header.getValue());
            }

            if (obj instanceof HttpGet) {
                httpget = (HttpGet) obj;
                headers = httpget.getAllHeaders();
                url = httpget.getURI().toURL().toString();
                reqMethod = httpget.getMethod();
            } else if (obj instanceof HttpPost) {
                httppost = (HttpPost) obj;
                headers = httppost.getAllHeaders();
                url = httppost.getURI().toURL().toString();
                reqMethod = httppost.getMethod();

            } else if (obj instanceof HttpDelete) {
                httpdelete = (HttpDelete) obj;
                headers = httpdelete.getAllHeaders();
                url = httpdelete.getURI().toURL().toString();
                reqMethod = httpdelete.getMethod();
            } else if (obj instanceof HttpPut) {
                httpput = (HttpPut) obj;
                headers = httpput.getAllHeaders();
                url = httpput.getURI().toURL().toString();
                reqMethod = httpput.getMethod();
            }

            for (Header header : headers) {
                //  logger.info(header.getName() + ":" + header.getValue());
                strReq.append(header.getName() + ":" + header.getValue());
            }

            oReqRes = new ReqRes();
            oReqRes.setUrl(url);
            oReqRes.setStatusCode(Integer.toString(status));
            oReqRes.setResHeaders(str.toString());
            oReqRes.setReqHeaders(strReq.toString());

            oReqRes.setRequest(reqMethod);

            if (entity == null) {
                //   System.out.println("Entity is null");
                return null;
            } else {
                String res = EntityUtils.toString(entity);
                oReqRes.setResponse(res);
             /*   RestEvidence oEvi = new RestEvidence();

                oEvi.setUrl(httpget.getURI().toURL().toString());
                oEvi.setStatusCode(Integer.toString(status));
                oEvi.setResHeaders(str.toString());
                oEvi.setReqHeaders(strReq.toString());
                oEvi.setResponse(res);
                oEvi.setRequest("");
                Utilities.writeHTML(oEvi);
*/
                Common.writeHTML(oReqRes);
                return res;
            }
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }

    }

    public static String getData(String ID) throws Exception {
        final HttpGet httpget = new HttpGet(SERVER_URL + "/" + ID);
        return getHTTPResponse(httpget);

    }

    public static String deleteData(String ID) throws Exception {
        final HttpDelete httpdelete = new HttpDelete(SERVER_URL + "/" + ID);
        return getHTTPResponse(httpdelete);

    }

    public static String getHTTPResponseCode() throws Exception {
        logger.info("url : " + oReqRes.getUrl());
        logger.info("Response : " + oReqRes.getResponse());
        logger.info("StatusCode : " + oReqRes.getStatusCode());
        logger.info("Request Headers : " + oReqRes.getReqHeaders());
        logger.info("Response Headers : " + oReqRes.getResHeaders());

        return oReqRes.getStatusCode();
    }

    public static String postData() throws Exception {
        User ouser = new User();
        ouser.setJob("Tech Test");
        ouser.setName("John Smith");
        //  JSONObject jsonObj = new JSONObject( ouser );
        // jsonObj.toString();
        final HttpPost httppost = new HttpPost(SERVER_URL);
        Gson gson = new Gson();
        StringEntity postingString = new StringEntity(gson.toJson(ouser));//gson.tojson() converts your pojo to json

        httppost.setEntity(postingString);
        return getHTTPResponse(httppost);


    }

    public static String putData(String name, String job, String id) throws Exception {
        User ouser = new User();
        ouser.setJob(job);
        ouser.setName(name);
        //  JSONObject jsonObj = new JSONObject( ouser );
        // jsonObj.toString();
        final HttpPut httpput = new HttpPut(SERVER_URL + "/" + id);
        Gson gson = new Gson();
        StringEntity postingString = new StringEntity(gson.toJson(ouser));//gson.tojson() converts your pojo to json

        httpput.setEntity(postingString);
        return getHTTPResponse(httpput);


    }

    public static boolean checkRessponseContainsID(String node) {
        JSONObject jsonObj = new JSONObject(oReqRes.getResponse());
        //   System.out.println(jsonObj.get(node));

        return true;
    }

    HttpGet createHTTPGetConnection(String url) {
        HttpClient client = HttpClients.createDefault();
        HttpGet hget = new HttpGet(url);
        HttpClient httpclient = HttpClients.custom().build();
        return hget;
    }


}
