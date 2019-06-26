package com.sdpp;

import com.sdpp.disp.Dispatcher;
import com.sdpp.server.Server;
import com.sdpp.user.Usuario;
import lombok.extern.slf4j.Slf4j;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/

@Slf4j
public class Main {


    public static void main(String[] args) {


        if(args.length > 0){
            if(args[0].equals("-B")){

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
                log.info("Iniciando balancer en puerto " + port);

                Server sc = new Server(port);

                sc.startServer();
                log.info("Desconectando servidor...");

            }else if(args[0].equals("-S")){
                log.info("iniciando Servidor..");

                Dispatcher d = new Dispatcher();

                d.start();
                log.info("Desconectando servidor...");
            }else if(args[0].equals("-U")){

                Long port = 0L;
                String ip;
                if(args.length>2){
                    if(args[1].equals("-h")){
                        ip = args[2];
                    }else{
                        throw new IllegalArgumentException("No se reconoce el argumento " + args[1]);
                    }
                    if(args.length>4){
                        if(args[3].equals("-p")){
                            port = Long.valueOf(args[4]);
                        }else{
                            throw new IllegalArgumentException("No se reconoce el argumento " + args[1]);
                        }
                    }

                }else{
                    throw new IllegalArgumentException("debe indicar el host y el puerto con -h <host> -p <port>");
                }

                log.info("iniciando interaccion de usuario");
                Usuario  u = new Usuario(port,ip);

                u.startUsuario();

                log.info("Desconectando usuario..");

            }else{
                throw new IllegalArgumentException("No se reconoce el comando " + args[0]);
            }
        }else{
            throw new IllegalArgumentException("Debe indicarse -S para server o -D para dispatcher");
        }

    }
}
