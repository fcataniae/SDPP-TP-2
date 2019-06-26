package com.sdpp.user;

import com.sdpp.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sdpp.model.Operation.*;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/

@Slf4j
public class Usuario {

    Long port;
    String ip;

    public Usuario(Long port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    public static void main(String[] args) throws Exception {
       int i = 0;
        while(i< 50){
            Message m = new Message();

            m.setOperation(DELETE);
            m.setBody("Body");
            TrheadUser r = new TrheadUser(m,8001L,"localhost");

            r.start();

            i++;
        }
    }

    public void startUsuario() {
        log.info("ctrl+c para finalizar.");
        log.info("Ingrese el metodo a utilizar");

        Scanner sc = new Scanner(System.in);
        while(true) {
            log.info("1 - DELETE");
            log.info("2 - POST");
            log.info("3 - GET");

            log.info("seleccione la opcion");
            String op = sc.nextLine();

            if(op.matches("\\d*")){
                Message m = new Message();

                log.info("Ingrese el mensaje a enviar");
                String mess = sc.nextLine();
                m.setBody(mess);
                log.info("Ingrese la cantidad de veces a enviar la operacion");

                String veces = sc.nextLine();

                if(veces.matches("\\d*")) {

                    switch (op) {

                        case "1":
                            m.setOperation(DELETE);
                            sendXTimes(m,Long.valueOf(veces));
                            break;
                        case "2":
                            m.setOperation(POST);
                            sendXTimes(m,Long.valueOf(veces));
                            break;
                        case "3":
                            m.setOperation(GET);
                            sendXTimes(m,Long.valueOf(veces));
                            break;
                        default:
                            log.info("La opcion no es valida..");
                            break;
                    }
                }else{
                    log.info("Debe ingresar una opcion numerica");
                }
            }else{
                log.info("Debe ingresar una opcion numerica");
            }
        }

    }

    private void sendXTimes(Message m, Long veces){

        int i= 0;
        ExecutorService executor = Executors.newFixedThreadPool(50);
        while(i < veces){
            TrheadUser t = new TrheadUser(m,this.port,this.ip);
            executor.execute(t);
            i++;
        }
    }
    private static class TrheadUser extends Thread{

        private Message m ;
        private Long port;
        private String ip;

        private TrheadUser(Message m){
            this.m = m;
        }
        private TrheadUser(Message m, Long port, String ip){
            this.port = port;
            this.ip = ip;
            this.m = m;
        }

        @Override
        public void run() {
            Socket s = null;
            try {
                s = new Socket(ip, port.intValue());

                ObjectOutputStream inputObjectStream = new ObjectOutputStream(s.getOutputStream());


                inputObjectStream.writeObject(m);

                log.info("Mensaje a enviar: " +m.toString());
                ObjectInputStream is = new ObjectInputStream(s.getInputStream());

                Message m2 = (Message) is.readObject();

                log.info("Mensaje recibido: "+m2.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
