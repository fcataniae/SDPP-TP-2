package com.sdpp.extremos.conections;

import com.sdpp.extremos.conections.hilos.ThreadServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerPeer extends Thread{

    private int port;
    private String sharedFolder;

    public ServerPeer(int port, String sharedFolder) {
        this.sharedFolder = sharedFolder;
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

                ThreadServer ts = new ThreadServer(client, id, this.sharedFolder);
                Thread tsThread = new Thread (ts);
                tsThread.start();
                id++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
