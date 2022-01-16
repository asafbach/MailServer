package com.asaf.model;

import java.util.Properties;

import com.asaf.service.ConfigService;

public class EmailProps {

    private Properties props;
    private String host = "";
    private Email email;
    private Configuration config = ConfigService.getInstance();
    private String password = "";

    public EmailProps(Email email) {
        this.email = email;
        setHostAndPassword();
        initProps();
    }

    private void initProps() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", config.smtpPort);
    }

    private void setHostAndPassword() {
        String suffix = email.getFrom();
        if (suffix.endsWith(EmailPostfix.Gmail.toString())) {
            host = EmailPostfix.Gmail.host;
            password = config.GmailPassword;
        } else if (suffix.endsWith(EmailPostfix.Yahoo.toString())) {
            host = EmailPostfix.Yahoo.host;
            password = config.YahooPassword;
        } else if (suffix.endsWith(EmailPostfix.Walla.toString())) {
            host = EmailPostfix.Walla.host;
            password = config.WallaPassword;
        }
    }

    public String getHost() {
        return host;
    }

    public Properties getProps() {
        return props;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
