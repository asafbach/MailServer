package com.asaf.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public String smtpPort;
    public int serverPort;
    public String GmailPassword;
    public String WallaPassword;
    public String YahooPassword;

    public Configuration() throws IOException {
        loadConfigFile();
    }

    private void loadConfigFile() throws IOException {
        try {
            FileInputStream file = new FileInputStream("src/resources/config.properties");
            Properties prop = new Properties();
            prop.load(file);
            serverPort = Integer.parseInt(prop.getProperty("serverPort"));
            smtpPort = prop.getProperty("smtpPort");
            GmailPassword = prop.getProperty("GmailPassword");
            WallaPassword = prop.getProperty("WallaPassword");
            YahooPassword = prop.getProperty("YahooPassword");
        } catch (IOException e) {
            throw new IOException();
        }

    }

}
