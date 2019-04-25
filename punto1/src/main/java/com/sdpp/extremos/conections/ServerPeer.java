package com.sdpp.extremos.conections;

import com.sdpp.extremos.conections.hilos.ThreadServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class ServerPeer extends Thread{

    private int port;
    private Map<String,String> fileRutas;

    public ServerPeer(int port, String sharedFolder) {
        this.port = port;
        loadFilesFromPath(sharedFolder);
    }

    private void loadFilesFromPath(String sharedFolder){
        this.fileRutas = new HashMap<>();

        try {
            Files.walk(Paths.get(sharedFolder)).forEach(ruta -> {
                if (Files.isRegularFile(ruta)) {
                    this.fileRutas.put(ruta.getFileName().toString(),ruta.getParent()+"\\"+ruta.getFileName());
                }
            });
        } catch (IOException e) {

        }

    }



    public void run() {

        try (
                ServerSocket ss = new ServerSocket(this.port)
                ){


            int id = 0;
            while (true) { //NOSONAR:

                Socket client = ss.accept();
                System.out.println("Cliente conectado desde: "+client.getInetAddress().getCanonicalHostName()+" : "+client.getPort());

                ThreadServer ts = new ThreadServer(client, id, this.fileRutas);
                Thread tsThread = new Thread (ts);
                tsThread.start();
                id++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
