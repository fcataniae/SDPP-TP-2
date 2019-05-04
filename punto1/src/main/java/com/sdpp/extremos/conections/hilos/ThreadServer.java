package com.sdpp.extremos.conections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.FileUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

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
       ){

           Consulta c = (Consulta) inputChannel.readObject();

           System.out.println("consulta: " + c.toString());
           returnDownloadableFile(c);


           this.client.close();
       } catch (Exception e){
           e.printStackTrace();
       }
    }


    private void returnDownloadableFile(Consulta c) {

        try(
                ObjectOutputStream outputChannel = new ObjectOutputStream(this.client.getOutputStream());
        ){
            Files.walk(Paths.get(this.sharedFolder)).forEach(ruta -> {
                if (Files.isRegularFile(ruta)) {
                    if(ruta.getFileName().toString().equalsIgnoreCase(c.getFileName())){
                        try {
                            byte[] binary = Files.readAllBytes(ruta);
                            FileUtil respuesta = new FileUtil();


                            respuesta.setBinary(binary);
                            respuesta.setName(ruta.getFileName().toString().split("\\.")[0]);
                            respuesta.setExtension(ruta.getFileName().toString().split("\\.")[1]);

                            outputChannel.writeObject(respuesta);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }


    }


}
