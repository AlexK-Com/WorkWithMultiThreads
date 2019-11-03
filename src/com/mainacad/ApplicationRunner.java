package com.mainacad;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import com.mainacad.multiеhreads.*;

import java.util.*;
import java.util.logging.Logger;

public class ApplicationRunner {
    private static final Logger LOG = Logger.getLogger(ApplicationRunner.class.getName());

    public static void main(String[] args) {

        LOG.info("Result is " );

/*
// рассказ о МАР, потом закомментитиь ОТСЮДА---
        Map<String, String> users = new HashMap<>();
        users.put("111", "Alex1");
        users.put("112", "Alex2");
        users.put("113", "Alex3");
        users.put("114", "Alex4");

        users.put("111", "Nikita");

        users.put(null, "Gleb");

        for (String key : users.keySet() ) {
            String value = users.get(key);
            LOG.info("Key with " + key + " has value \""+ value + "\"" + "\n");
        }

        users.keySet().forEach(key -> // key можно опустить, но тогда нужно поставить - "()"
        LOG.info("Key \"" + key + " has value \"" + users.get(key) + "\"" + "\n"));
        // лямбда выражение это - "->" используется для вызова процедуры одной строкой!!!

// рассказ о МАР, потом закомментитиь СЮДА---!
*/

        List<String> connectionIPList = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
            MultitheadWrapper multitheadWrapper =
                    new MultitheadWrapper("thread" + i, connectionInfo, connectionIPList);

            threads.add(multitheadWrapper);
            multitheadWrapper.start();
        }

        while (threadsAlive(threads)){
            try {
                LOG.info("\n\n---------------\nThreads still alive!\n---------------\n");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info( connectionIPList.size() + " connection were writed to the file.");
    }

    private static boolean threadsAlive(List<Thread> threads) {
        // boolean isAlive = false;
        for (Thread thread : threads) {
            if (thread.isAlive() || thread.getState().equals(Thread.State.NEW)){
                return true;
            }
        }
        return false;
    }


}
