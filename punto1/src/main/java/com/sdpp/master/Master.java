package com.sdpp.master;

import com.sdpp.master.connections.ServerCon;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Master {


    public static void main(String[] args) throws InterruptedException{
        log.info("Iniciando servidor MASTER");
        ServerCon sc = new ServerCon(9000);
        sc.start();
        sc.join();
        log.info("Desconectando el servidor MASTER");
    }
}
