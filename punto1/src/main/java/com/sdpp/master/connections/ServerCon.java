package com.sdpp.master.connections;

import com.sdpp.extremos.conections.hilos.ThreadServer;
import com.sdpp.master.connections.hilos.ThreadMaster;
import com.sdpp.utils.model.Host;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class ServerCon extends Thread  {


    private int port;
    private final Map<String, List<Host>> archivoPeer = new ConcurrentHashMap<>();

    public ServerCon(int port) {
        this.port = port;
    }

    @Override
    public void run() {


        log.info("Server starting at port " + this.port);
        try (
                ServerSocket ss = new ServerSocket(this.port)
        ){

            log.info("Server listening for connections...");
            int id = 1;
            while (true) { //NOSONAR:

                Socket client = ss.accept();
                log.info("Client connected from:  "+client.getInetAddress().getCanonicalHostName()+":"+client.getPort());
                ThreadMaster ts = new ThreadMaster(this.archivoPeer,client, id);
                Thread tsThread = new Thread (ts);
                tsThread.start();
                id++;
            }


        } catch (IOException e) {
            log.warn("Error durante la ejecucion del servidor!!!", e);
        }


    }

}
