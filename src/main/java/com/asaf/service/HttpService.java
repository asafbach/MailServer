package com.asaf.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.asaf.model.HttpRequest;
import com.asaf.model.HttpStatusCode;

public class HttpService {

    public static HttpRequest getHttpRequest(InputStream inputStream) throws IOException {
        String method = null;
        String payload = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String request = bufferedReader.readLine();
        method = request.toString().split(" ")[0];

        while ((bufferedReader.readLine()).length() != 0) {
        }

        StringBuilder sb = new StringBuilder();
        while (bufferedReader.ready()) {
            sb.append((char) bufferedReader.read());
        }
        payload = sb.toString();
        return new HttpRequest(method, payload);
    }

    public static OutputStream makeHttpResponse(OutputStream outputStream, HttpStatusCode statusCode)
            throws IOException {
        String CRLF = "\r\n";
        outputStream.write(("HTTP1.1/ " + statusCode.toString() + CRLF).getBytes());
        outputStream.write((CRLF).getBytes());
        return outputStream;
    }

}
