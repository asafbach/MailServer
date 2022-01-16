package com.asaf.model;

public enum EmailPostfix {
    Gmail("gmail.com", "smtp.gmail.com"),
    Yahoo("yahoo.com", "smtp.yahoo.com"),
    Walla("walla.com", "smtp.walla.co.il");

    private final String name;
    public final String host;

    EmailPostfix(String name, String host) {
        this.name = name;
        this.host = host;
    }

    @Override
    public String toString() {
        return name;
    }

}
