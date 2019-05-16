package com.sdpp.extremos.conections;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sdpp.extremos.conections.utils.Master;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * User: fcatania
 * Date: 16/5/2019
 * Time: 14:58
 */

@Slf4j
public class PeerCon implements Runnable{


    private int masterPort;
    private String masterIp;
    private ClientPeer client;
    private ServerPeer server;

    public PeerCon(int serverPort, String sharedFolder) {

        String filename = getClass().getClassLoader().getResource("masters.json").getFile();
        log.info(filename);
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            log.warn("Cannot get configuration file for masters",e);
        }
        Master[] masters = new Gson().fromJson(reader, Master[].class);

        log.info("Account of servers obtained from configuration file: " + masters.length);

        Master master = masters[(int)Math.random() * masters.length ];

        log.info("Master selected from masters " + master);

        this.masterIp = master.getIp();
        this.masterPort = master.getPort();
        this.server = new ServerPeer(serverPort,sharedFolder);
        this.client = new ClientPeer(masterIp,masterPort,sharedFolder,serverPort);

    }


    @Override
    public void run() {
        log.info("Initializing server and client peer...");
        Thread s =  server;
        Thread c =  client;

        s.start();
        c.start();
        log.info("Peer sucesfully running...");

        try {
            s.join();
            c.join();
        } catch (InterruptedException e) {
            log.warn("Error while joining server and client peer threads",e);
        }

    }
}