package com.sdpp.extremos.conections.hilos;

import com.sdpp.utils.Consulta;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ThreadServer implements Runnable{

    private Socket client;
    private int idSession;

    public ThreadServer (Socket client, int id) {
        this.idSession = id;
        this.client = client;
    }

    public void run() {

       try
       (
           ObjectInputStream inputChannel = new ObjectInputStream(this.client.getInputStream());
           ObjectOutputStream outputChannel = new ObjectOutputStream(this.client.getOutputStream());
       ){

           Consulta c = (Consulta) inputChannel.readObject();

           switch(c.getMethod()){
               case DOWNLOAD:

                   returnDownloadableFile();

                   break;
               case GET_FULL_FILES:

                   returnFilesSharedList();

                   break;
               default:
                   break;
           }

           this.client.close();
       } catch (Exception e){
           e.printStackTrace();
       }
    }

    private void returnFilesSharedList() throws IOException {


    }

    private void returnDownloadableFile() {



    }


}
