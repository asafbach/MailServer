package com.asaf.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.asaf.controllers.EmailController;
import com.asaf.model.HttpMethod;
import com.asaf.model.HttpRequest;
import com.asaf.service.HttpService;

public class HttpWorkerThread extends Thread {
    private final Socket context;

    public HttpWorkerThread(Socket context) {
        this.context = context;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = context.getInputStream();
            outputStream = context.getOutputStream();
            EmailController emailController = new EmailController(outputStream);
            HttpRequest req = HttpService.getHttpRequest(inputStream);
            if (req != null) {
                if (req.method.equals(HttpMethod.POST.toString())) {
                    emailController.postRequest(req);
                } else if (req.method.equals(HttpMethod.GET.toString())) {
                    emailController.getRequest();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                context.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
