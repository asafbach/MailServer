package com.asaf.controllers;

import java.io.IOException;
import java.io.OutputStream;

import com.asaf.model.Email;
import com.asaf.model.EmailProps;
import com.asaf.model.HttpRequest;
import com.asaf.model.HttpStatusCode;
import com.asaf.service.EmailService;
import com.asaf.service.HttpService;

public class EmailController {

    private OutputStream outputStream = null;

    public EmailController(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void getRequest() throws IOException {
        OutputStream res = null;
        res = HttpService.makeHttpResponse(outputStream, HttpStatusCode.STATUS_CODE_200);
        res = EmailService.sendEmailForm(outputStream);
        if (res == null) {
            res = HttpService.makeHttpResponse(outputStream, HttpStatusCode.STATUS_CODE_500);
        }
        res.flush();
    }

    public void postRequest(HttpRequest req) throws IOException {
        OutputStream res = null;
        Email email = null;
        email = EmailService.getEmailData(req.payload);
        if (email == null) {
            res = HttpService.makeHttpResponse(outputStream, HttpStatusCode.STATUS_CODE_400);
        } else {
            EmailProps emailProps = new EmailProps(email);
            if (emailProps.getHost().equals("") || emailProps.getPassword().equals("")) {
                res = HttpService.makeHttpResponse(outputStream, HttpStatusCode.STATUS_CODE_400);
            } else {
                EmailService emailService = new EmailService(emailProps);
                boolean emailSent = emailService.sendEmail();
                if (emailSent == false)
                    res = HttpService.makeHttpResponse(outputStream, HttpStatusCode.STATUS_CODE_500);
                else
                    res = HttpService.makeHttpResponse(outputStream, HttpStatusCode.STATUS_CODE_200);
            }
        }
        res.flush();
    }

}
