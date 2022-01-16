package com.asaf.service;

import java.io.IOException;

import com.asaf.model.Configuration;

public class ConfigService {
    private static final Object lock = new Object();
    private static Configuration myConfiguration;

    public static Configuration getInstance() {
        if (myConfiguration == null)
            synchronized (lock) {
                if (myConfiguration == null)
                    try {
                        myConfiguration = new Configuration();
                    } catch (IOException e) {
                        e.printStackTrace();
                        myConfiguration = null;
                    }
            }
        return myConfiguration;
    }

}
