package com.sdpp.extremos.conections;

import com.sdpp.extremos.conections.hilos.ThreadServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j(topic = "logger")
public class ServerPeer extends Thread{

    private int port;
    private String sharedFolder;

    public ServerPeer(int port, String sharedFolder) {
        this.port = port;
        this.sharedFolder = sharedFolder;
    }

        public void run () {

            log.info("Starting peer server in port " + port );
            try (
                    ServerSocket ss = new ServerSocket(port)
            ) {

                log.info("Server succesfuly started");

                int id = 0;
                while (true) { //NOSONAR:

                    Socket client = ss.accept();
                    log.info("Client connected from " + client.getInetAddress().getCanonicalHostName() + ":" + client.getPort());

                    ThreadServer ts = new ThreadServer(client, id, this.sharedFolder);
                    Thread tsThread = new Thread(ts);
                    tsThread.start();
                    id++;

                }


            } catch (IOException e) {
                log.warn("An error happened while running server peer",e);
            }


        }
    }

