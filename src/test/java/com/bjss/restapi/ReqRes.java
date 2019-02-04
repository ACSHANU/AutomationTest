package com.bjss.restapi;


public class ReqRes {
    String request;
    String response;
    String statusCode;
    String resHeaders;
    String url;
    String reqHeaders;

    public String getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(String reqHeaders) {
        this.reqHeaders = reqHeaders;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getResHeaders() {
        return resHeaders;
    }

    public void setResHeaders(String resHeaders) {
        this.resHeaders = resHeaders;
    }
}
