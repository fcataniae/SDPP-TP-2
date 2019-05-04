package com.sdpp.master.connections;

import com.sdpp.extremos.conections.hilos.ThreadServer;
import com.sdpp.master.connections.hilos.ThreadMaster;
import com.sdpp.utils.model.Host;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerCon extends Thread  {


    private int port;
    private Map<String, Host> archivoPeer;

    public ServerCon(int port) {
        this.archivoPeer = new HashMap<>();
        this.port = port;
    }

    public void run() {

        try (
                ServerSocket ss = new ServerSocket(this.port)
        ){


            int id = 0;
            while (true) { //NOSONAR:

                Socket client = ss.accept();
                System.out.println("Cliente conectado desde: "+client.getInetAddress().getCanonicalHostName()+" : "+client.getPort());
                ThreadMaster ts = new ThreadMaster(this.archivoPeer,client);
                Thread tsThread = new Thread (ts);
                tsThread.start();
                id++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
