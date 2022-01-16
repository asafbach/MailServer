package com.asaf.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.asaf.model.Email;
import com.asaf.model.EmailProps;
import com.sun.mail.smtp.*;

public class EmailService {

    private EmailProps emailProps;
    private String from;
    private String to;
    private String body;
    private String password;

    public EmailService(EmailProps emailProps) {
        this.emailProps = emailProps;
        this.from = emailProps.getEmail().getFrom();
        this.to = emailProps.getEmail().getTo();
        this.body = emailProps.getEmail().getBody();
        this.password = emailProps.getPassword();

    }

    public boolean sendEmail() {
        Session session = Session.getInstance(emailProps.getProps(), null);
        Message msg = new MimeMessage(session);
        SMTPTransport t = null;
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // msg.setSubject("subject");
            msg.setText(body);
            t = (SMTPTransport) session.getTransport("smtp");
            t.connect(null, from, password);
            t.sendMessage(msg, msg.getAllRecipients());
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                t.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    public static Email getEmailData(String payload) {

        String to, from, body, name, value;
        to = from = body = name = value = null;
        try {
            String[] arrPayload = payload.split("&");
            for (String data : arrPayload) {
                name = data.split("=")[0].toString();
                value = data.split("=")[1].toString();
                if (name.equals("to")) {
                    to = value.replace("%40", "@");
                } else if (name.equals("from")) {
                    from = value.replace("%40", "@");
                } else if (name.equals("body")) {
                    body = value;
                }
            }
        } catch (Exception e) {
            return null;
        }
        if (to == null || from == null || body == null) {
            return null;
        }
        return new Email(to, from, body);
    }

    public static OutputStream sendEmailForm(OutputStream outputStream) {
        FileInputStream image = null;
        try {
            image = new FileInputStream("src/resources/index.html");
            outputStream.write((image.readAllBytes()));
            return outputStream;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                image.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
