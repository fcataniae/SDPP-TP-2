package com.sdpp.master;

import com.sdpp.master.connections.ServerCon;
import lombok.extern.slf4j.Slf4j;

/**
 * CLASE SOLO UTILIZADA CORRERLA EN ENTORNO DE DESARROLLO LOCAL
 * POR ESO ESTAN HARDCODEADOS LOS PARAMETROS
 * CLASE MAIN DE EXPORTADO .JAR VER com.sdpp.Main
 */
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
