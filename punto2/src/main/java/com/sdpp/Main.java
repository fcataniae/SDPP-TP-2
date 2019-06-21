package com.sdpp;

import com.sdpp.client.User;
import com.sdpp.model.Transaction;
import com.sdpp.procces.TransactionManager;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
@Slf4j
public class Main {

    static String ip;
    static Long port = 8000L;
    public static void main(String[] args) {
        if(args.length > 0){
            if(args[0].equals("-S")){


                if (args.length > 2){
                    if(args[1].equals("-p")){
                        port = Long.valueOf(args[2]);
                    }else{
                        throw new IllegalArgumentException("No se reconoce el argumento " + args[1] + ", debe definirse -p para indicar el puerto");
                    }
                }else{
                    log.info("No se indico el parametro -p por lo que se tomara por defecto el puerto " + port) ;
                }
                log.info("Iniciando Server en puerto " + port);

                TransactionManager sc = new TransactionManager(port);

                sc.startTransactionManager();
                log.info("Desconectando servidor...");

            }else if(args[0].equals("-P")){


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

                generateUserInterfaz(ip,port);
                log.info("Desconectando prueba usuario...");
            }else{
                throw new IllegalArgumentException("debe indicar el host y el puerto con -h <host> -p <port>");
            }

        }else{
            throw new IllegalArgumentException("Debe indicarse -S para server o -P para iniciar interfaz");
        }
    }

    private static void generateUserInterfaz(String ip, Long port) {

        log.info("ctrl + c para finalizar");
       while(true) {
           log.info("Operacion a realizar ");
           log.info("1 - N transacciones aleatorias");
           log.info("2 - Transaccion unitaria");

           Scanner sc = new Scanner(System.in);

           String op = sc.nextLine();

           if(op.matches("\\d*")){


                   switch (op) {
                       case "1":
                           cargarPruebaAleatoria(ip, port);
                           break;
                       case "2":
                           cargarPruebaUnitaria(ip, port);
                           break;
                       default:
                           log.info("No se reconece la opcion seleccionada");
                           break;
                   }

           }else{
               log.info("debe ingresar una opcion numerica!");
           }
       }
    }

    private static void cargarPruebaAleatoria(String ip, Long port) {
        Scanner sc = new Scanner(System.in);
        log.info("ingrese el monto de la operacion");
        String monto = sc.nextLine();
        log.info("ingrese la cantidad de transacciones a realizar");
        String trans = sc.nextLine();
        if(monto.matches("\\d*") && trans.matches("\\d*")) {
            int i = 0;
            int t = Integer.valueOf(trans);
            Random r = new Random();
            while(i<t){
               runOperation(r.nextBoolean()? Transaction.DEPOSITO: Transaction.EXTRACCION,new BigDecimal(monto));
               i++;
            }

        }else{
            log.info("el monto debe ser numerico");
        }
    }

    private static boolean isServerOpen(){


        try(Socket s = new Socket(ip,port.intValue()))
        {
            return true;
        } catch (IOException e){
            return false;
        }

    }
    private static void runOperation(Transaction t, BigDecimal monto){

        User u = new User();

        if(isServerOpen()) {

            u.setT(t);
            u.setMonto(monto);
            u.setIp(ip);
            u.setPort(port);
            u.start();
        }else{
            throw new IllegalStateException("Server is not running!");
        }

    }
    private static void cargarPruebaUnitaria(String ip, Long port) {
        Scanner sc = new Scanner(System.in);
        log.info("ingrese el monto de la operacion");
        BigDecimal montoB;
        String monto = sc.nextLine();
        if(monto.matches("\\d*")) {
            log.info("Ingrese el tipo de operacion:");

            montoB = new BigDecimal(monto);

            log.info("1 - deposito");
            log.info("2 - extracion");

            String op = sc.nextLine();

            if (op.matches("\\d*")) {
                Transaction t;
                switch (op) {

                    case "1":
                        t = Transaction.DEPOSITO;
                        runOperation(t, montoB);
                        break;
                    case "2":
                        t = Transaction.EXTRACCION;
                        runOperation(t, montoB);
                        break;
                    default:
                        log.info("No se reconece la opcion seleccionada");
                        break;
                }

            } else {
                log.info("debe ingresar una opcion numerica");
            }
        }else {
            log.info("el monto debe ser numerico");
        }
    }
}
