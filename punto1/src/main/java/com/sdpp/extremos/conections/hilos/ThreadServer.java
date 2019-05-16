package com.sdpp.extremos.conections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class ThreadServer implements Runnable{

    private Socket client;
    private int idSession;
    private String sharedFolder;
    private String logHead;


    public ThreadServer (Socket client, int id, String sharedFolder) {
        this.idSession = id;
        this.client = client;
        this.sharedFolder = sharedFolder;

        this.logHead = "THREAD PEER " + idSession + " - ";
    }

        public void run () {


            try
                    (
                            ObjectInputStream inputChannel = new ObjectInputStream(this.client.getInputStream());
                    ) {

                log.info(logHead + "Receiving request for file");

                Consulta c = (Consulta) inputChannel.readObject();

                log.info(logHead + "File to seek and send " + c.getFileName());

                returnDownloadableFile(c);

                log.info(logHead + "Closing connection from client...");
                this.client.close();
            } catch (Exception e) {
                log.warn("An error happened while receiving a download request",e);
            }
        }


        private void returnDownloadableFile (Consulta c){

            try (
                    ObjectOutputStream outputChannel = new ObjectOutputStream(this.client.getOutputStream());
            ) {
                log.info(logHead + "Preparing response...");

                Files.walk(Paths.get(this.sharedFolder)).forEach(ruta -> {
                    if (Files.isRegularFile(ruta)) {

                        if (ruta.getFileName().toString().equalsIgnoreCase(c.getFileName())) {

                            log.info(logHead + "File found iun shared folder...");

                            try {

                                log.info(logHead + "Generating binary..");

                                byte[] binary = Files.readAllBytes(ruta);
                                FileUtil respuesta = new FileUtil();
                                respuesta.setBinary(binary);
                                respuesta.setName(ruta.getFileName().toString().split("\\.")[0]);
                                respuesta.setExtension(ruta.getFileName().toString().split("\\.")[1]);

                                log.info(logHead + "Sending file to client...");

                                outputChannel.writeObject(respuesta);

                                log.info(logHead + "File transfer complete...");

                            } catch (IOException e) {
                                log.info("Error while transforming and sending binary",e);
                            }
                        }
                    }
                });


            } catch (Exception e) {
                log.warn("Error when seeking for file in shared folder" , e);
            }

        }
    }
