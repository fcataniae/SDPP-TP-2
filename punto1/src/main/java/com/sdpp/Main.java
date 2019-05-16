package com.sdpp;

import com.sdpp.extremos.conections.PeerCon;
import com.sdpp.master.connections.ServerCon;
import lombok.extern.slf4j.Slf4j;

/**
 * User: fcatania
 * Date: 16/5/2019
 * Time: 17:35
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {

        if(args.length > 0){
            if(args[0].equals("-M")){
                int port = 8000; //default port

                if (args.length > 2){
                    if(args[1].equals("-p")){
                        port = Integer.valueOf(args[2]);
                    }else{
                        throw new IllegalArgumentException("No se reconoce el argumento " + args[1] + ", debe definirse -p para indicar el puerto");
                    }
                }else{
                    log.info("No se indico el parametro -p por lo que se tomara por defecto el puerto " + port) ;
                }
                log.info("Iniciando servidor MASTER en puerto " + port);

                ServerCon sc = new ServerCon(port);

                sc.start();
                sc.join();
                log.info("Desconectando el servidor MASTER");

            }else if(args[0].equals("-P")){

                String sharedF;
                int port = 8001;

                if(args.length>2){
                    if(args[1].equals("-sf")){
                        sharedF = args[2];
                    }else{
                        throw new IllegalArgumentException("No se reconoce el argumento " + args[1]);
                    }
                    if(args.length>4){
                        if(args[3].equals("-p")){
                            port = Integer.valueOf(args[4]);
                        }else{
                            throw new IllegalArgumentException("No se reconoce el argumento " + args[1]);
                        }
                    }

                }else{
                    throw new IllegalArgumentException("debe indicar la carpeta compartida con el comando -sf");
                }

                log.info("Se inicia el Peer en el puerto " + port + " con la carpeta compartida en la ruta " + sharedF);
                PeerCon peer = new PeerCon(port,sharedF);

                peer.run();
            }else{
                throw new IllegalArgumentException("No se reconoce el comando " + args[0]);
            }
        }else{
            throw new IllegalArgumentException("Debe indicarse -M para master o -P para peer");
        }

    }
}