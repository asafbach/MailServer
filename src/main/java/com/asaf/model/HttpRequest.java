package com.asaf.model;

public class HttpRequest {

    public String method;
    public String payload;

    public HttpRequest(String method, String payload) {
        this.method = method;
        this.payload = payload;
    }

}
