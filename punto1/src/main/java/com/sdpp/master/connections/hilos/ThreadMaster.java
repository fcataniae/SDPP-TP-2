package com.sdpp.master.connections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.WrapperList;
import com.sdpp.utils.WrapperMap;
import com.sdpp.utils.model.Host;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class ThreadMaster implements Runnable {

    private final Map<String, List<Host>> archivos;
    private Socket cliente;
    private int tid;
    private String logHead;
    private Host clientHost;


    public ThreadMaster(Map<String, List<Host>> archivos, Socket cliente, int tid) {
        this.archivos = archivos;
        this.cliente = cliente;
        this.tid = tid;
        this.clientHost = new Host();

        this.logHead = "THREAD " + this.tid+" - ";
    }

    public void run() {

        try{
            log.info(logHead + "Receiving data from client ... ");

            ObjectInputStream is = new ObjectInputStream(this.cliente.getInputStream());
            WrapperMap wpm = new WrapperMap();
            WrapperList wpl = (WrapperList) is.readObject();
            this.clientHost = wpl.getServerHost();

            log.info(logHead + "Processing files ...");
            log.info("DEBUG before" + this.archivos.size());
            synchronized (this.archivos) {
                for (String arch : wpl.getFileList()) {
                    this.archivos.computeIfAbsent(arch, k -> new ArrayList<>()).add(wpl.getServerHost());
                }
            }

            log.info("DEBUG after" + this.archivos.size());
            log.info(logHead + "Amount of files: " + this.archivos.size());

            while(true) {
                log.info(logHead + "Waiting for client request ...");

                Consulta c = (Consulta) (is.readObject());
                ObjectOutputStream os = new ObjectOutputStream(this.cliente.getOutputStream());
                switch (c.getMethod()) {
                    case DOWNLOAD:
                        log.info(logHead + "Requested file: " + c.getFileName());
                        log.info("filtering files from diferents hosts");

                        for (Map.Entry<String, List<Host>> entry : this.archivos.entrySet()) {
                            String k = entry.getKey();
                            List<Host> v = entry.getValue();
                            if (k.contains(c.getFileName())) {
                                v.forEach(v2 -> {
                                    if (!v2.equals(this.clientHost))
                                        wpm.getFileToPeer().computeIfAbsent(k, k2 -> new ArrayList<>()).add(v2);
                                });
                            }
                        }


                        log.info(logHead + "Amount of files founds " + wpm.getFileToPeer().size());
                        os.writeObject(wpm);
                        break;
                    case ADD_FILE:
                        log.info(logHead + " updating file list w " + c.getFileName());
                        this.archivos.computeIfAbsent(c.getFileName(), k-> new ArrayList<>()).add(this.clientHost);
                        break;
                    default:
                        os.writeObject(new String("Not Implemented Method!"));
                        break;
                }

            }
        }catch (Exception e ){
            log.warn(logHead + "An exception happend while the client was being attended ", e);
        }


    }
}
