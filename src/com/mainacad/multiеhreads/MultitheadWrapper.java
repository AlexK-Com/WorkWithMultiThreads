package com.mainacad.multiеhreads;

import com.mainacad.ApplicationRunner;
import com.mainacad.model.ConnectionInfo;
import com.mainacad.service.FileService;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;


public class MultitheadWrapper extends Thread  {

    private final String threadName;
    private final ConnectionInfo connectionInfo;
    private static final Logger LOG = Logger.getLogger(ApplicationRunner.class.getName());
    private List connectionIpList;


    public MultitheadWrapper(String threadName, ConnectionInfo connectionInfo, List connectionIpList) {
        this.threadName = threadName;
        this.connectionInfo = connectionInfo;
        this.connectionIpList = connectionIpList;
    }

    @Override
    public void run() {  // пишем коннекшенИнфо в файл
        LOG.info(threadName + " was started!");

        FileService.writeTextToFile(connectionInfo.toString(), "multi.txt", true);
        connectionIpList.add(connectionInfo.getIp());
    }
}
