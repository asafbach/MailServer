package com.asaf.model;

public class Email {
    private String to;
    private String from;
    private String body;

    public Email(String to, String from, String body) {
        this.to = to;
        this.from = from;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getBody() {
        return body;
    }

}
