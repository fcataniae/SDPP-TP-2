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
    private String sharedFolder;

    public ThreadServer (Socket client, int id, String sharedFolder) {
        this.idSession = id;
        this.client = client;
        this.sharedFolder = sharedFolder;
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

        List<String> fileNameList = new ArrayList<>();
        Files.walk(Paths.get(this.sharedFolder)).forEach(ruta-> {
            if (Files.isRegularFile(ruta)) {
                fileNameList.add(ruta.getFileName().toString());
            }
        });
    }

    private void returnDownloadableFile() {



    }


}
