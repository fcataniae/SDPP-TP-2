package com.sdpp.master.connections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.WrapperList;
import com.sdpp.utils.WrapperMap;
import com.sdpp.utils.model.Host;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

@Slf4j(topic = "logger")
public class ThreadMaster implements Runnable {

    private Map<String, Host> archivos;
    private Socket cliente;
    private int tid;
    private String logHead;


    public ThreadMaster(Map<String, Host> archivos, Socket cliente, int tid) {
        this.archivos = archivos;
        this.cliente = cliente;
        this.tid = tid;

        this.logHead = "THREAD " + this.tid+" - ";
    }

    public void run() {

        try{
            log.info(logHead + "Receiving data from client ... ");

            ObjectInputStream is = new ObjectInputStream(this.cliente.getInputStream());
            WrapperMap wpm = new WrapperMap();
            WrapperList wpl = (WrapperList) is.readObject();

            log.info(logHead + "Processing files ...");

            wpl.getFileList().forEach( arch -> this.archivos.put(arch, new Host(wpl.getServerPort(),wpl.getServerIp())));

            log.info(logHead + "Amount of files: " + this.archivos.size());

            while(true) {
                log.info(logHead + "Waiting for client request ...");

                Consulta c = (Consulta) (is.readObject());

                log.info(logHead + "Requested file: " + c.getFileName());

                this.archivos.forEach((k, v) -> {
                    if (k.contains(c.getFileName())) {
                        wpm.getFileToPeer().put(k, v);
                    }
                });

                ObjectOutputStream os = new ObjectOutputStream(this.cliente.getOutputStream());

                log.info(logHead + "Amount of files founds " + wpm.getFileToPeer().size());
                os.writeObject(wpm);
            }
        }catch (Exception e ){
            log.warn(logHead + "An exception happend while the client was being attended ", e);
        }


    }
}
