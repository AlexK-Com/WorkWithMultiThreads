package com.mainacad.multiеhreads;

import com.mainacad.ApplicationRunner;
import com.mainacad.model.ConnectionInfo;
import com.mainacad.service.FileService;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;


public class MultitheadWrapper extends Thread  {

    private Semaphore sem;

    private final String threadName;
    private final ConnectionInfo connectionInfo;
    private static final Logger LOG = Logger.getLogger(ApplicationRunner.class.getName());
    private List connectionIpList;

    public MultitheadWrapper(Semaphore sem, String threadName, ConnectionInfo connectionInfo, List connectionIpList) {
        this.sem=sem;
        this.threadName = threadName;
        this.connectionInfo = connectionInfo;
        this.connectionIpList = connectionIpList;
    }

    @Override
    public void run() {  // пишем коннекшенИнфо в файл
        try {
            sem.acquire();
            LOG.info(threadName + " was started!");
            FileService.writeTextToFile(sem.availablePermits() + ". " + connectionInfo.toString(), "multi.txt", true);
            connectionIpList.add(connectionInfo.getIp());
            sem.release();
            LOG.info(threadName + " was STOPPED!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
