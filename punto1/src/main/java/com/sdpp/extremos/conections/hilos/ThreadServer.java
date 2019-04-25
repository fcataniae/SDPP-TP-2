package com.sdpp.extremos.conections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Map;

public class ThreadServer implements Runnable{

    private Socket client;
    private int idSession;
    private Map<String,String> fileRutas;

    public ThreadServer (Socket client, int id, Map<String,String> fileRutas) {
        this.idSession = id;
        this.client = client;
        this.fileRutas = fileRutas;
    }

    public void run() {

       try
       (
           ObjectInputStream inputChannel = new ObjectInputStream(this.client.getInputStream());
       ){

           Consulta c = (Consulta) inputChannel.readObject();

           switch(c.getMethod()){
               case DOWNLOAD:

                   returnDownloadableFile(c.getFileName());

                   break;
               default:
                   break;
           }

           this.client.close();
       } catch (Exception e){
           e.printStackTrace();
       }
    }


    /**
     * Metodo que devuelve un objeto fileUtil con el binario del archivo solicitado por el otro peer
     * @param fileName
     */
    private void returnDownloadableFile(String fileName) {

        String ruta = this.fileRutas.get(fileName);

        File f = new File(ruta);

        try (
                ObjectOutputStream outputChannel = new ObjectOutputStream(this.client.getOutputStream());
                ){
            byte[] binary = Files.readAllBytes(f.toPath());

            FileUtil binaryFile = new FileUtil();

            binaryFile.setBinary(binary);
            binaryFile.setName(fileName.split("\\.")[0]);
            binaryFile.setExtension(fileName.split("\\.")[1]);

            outputChannel.writeObject(binaryFile);


        } catch (IOException e) {

        }


    }

}
