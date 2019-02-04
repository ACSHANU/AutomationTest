package com.bjss.utilities;


import com.bjss.restapi.ReqRes;
import com.bjss.stepdefs.ServiceHooks;

public class Common {

    public final static int WAIT_LONG_MS = 30000;
    public final static String MY_ACCOUNT_PAGE_TITLE = "My account - My Stor";

    public static void writeHTML(ReqRes oResEvi) {
        writeHTML(oResEvi.getRequest(), oResEvi.getResponse(), oResEvi.getUrl(), oResEvi.getResHeaders(), oResEvi.getStatusCode(), oResEvi.getReqHeaders());

    }

    public static void writeHTML(String req, String res, String url, String resHeaders, String status, String reqHeaders) {
        StringBuilder str = new StringBuilder();
        str.append("<Table border=1 width=100%>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Status Code");
        str.append("</TD>");
        str.append("<TD>");
        str.append(status);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("URL");
        str.append("</TD>");
        str.append("<TD>");
        str.append(url);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Request");
        str.append("</TD>");
        str.append("<TD>");
        str.append(req);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Response");
        str.append("</TD>");
        str.append("<TD>");
        str.append(res);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Response Headers");
        str.append("</TD>");
        str.append("<TD>");
        str.append(resHeaders);
        str.append("</TD>");
        str.append("</TR>");

        str.append("<TR>");
        str.append("<TD>");
        str.append("Request Headers");
        str.append("</TD>");
        str.append("<TD>");
        str.append(reqHeaders);
        str.append("</TD>");
        str.append("</TR>");
        str.append("</Table>");

        ServiceHooks.scenario.embed(str.toString().getBytes(), "text/html");
    }


}
