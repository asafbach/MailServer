package com.asaf.model;

public enum HttpStatusCode {
    STATUS_CODE_400(400, "Bad Request"),
    STATUS_CODE_500(500, "Server Error"),
    STATUS_CODE_404(404, "Not Found"),
    STATUS_CODE_200(200, "OK");

    public final int statusCode;
    public final String message;

    HttpStatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return " " + Integer.toString(statusCode) + " " + message;
    }

}
