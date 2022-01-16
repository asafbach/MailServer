package com.asaf;

import com.asaf.core.HttpWorkerThread;
import com.asaf.model.Configuration;
import com.asaf.service.ConfigService;

import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Configuration config = ConfigService.getInstance();
        if (config == null) {
            return;
        }
        try (ServerSocket context = new ServerSocket(config.serverPort)) {
            System.out.println("server listening...");
            ExecutorService threadPool = Executors.newFixedThreadPool(20);
            while (true) {
                threadPool.execute(new HttpWorkerThread(context.accept()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
