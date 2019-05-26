package com.sdpp.procces;

import com.sdpp.procces.hilos.TransactionThread;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
@Slf4j
public class TransactionManager {

    private Object cuenta;
    private Long port;

    public TransactionManager(Long port) {
        this.port = port;
        this.cuenta = new Object();
    }

    public void startTransactionManager(){

        try {

            ServerSocket ss = new ServerSocket(this.port.intValue());
            log.info("Server listening on port " + this.port);
            Long id = 0L;
            while (true) {
                Socket client = ss.accept();
                log.info("Client connected from " + client.getInetAddress().getCanonicalHostName()+":"+client.getPort());
                TransactionThread ts = new TransactionThread(client, id, cuenta);
                log.info("Nuevo Thread: "+ id);
                Thread tsThread = new Thread(ts);
                tsThread.start();
                id++;
            }

        } catch (IOException e) {
            log.warn("Error while creating server." , e);
        }


    }

    public static void main(String[] args) {
        TransactionManager t = new TransactionManager(8000L);

        t.startTransactionManager();
    }

}
