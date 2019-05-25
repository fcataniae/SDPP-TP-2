package com.sdpp.user;

import com.sdpp.model.Message;
import com.sdpp.model.Operation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
public class Usuario {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       int i = 0;
        while(i< 100){
            TrheadUser r = new TrheadUser();

            r.start();

            i++;
        }
    }

    private static class TrheadUser extends Thread{

        @Override
        public void run() {
            Socket s = null;
            try {
                s = new Socket("localhost", 8001);

                ObjectOutputStream inputObjectStream = new ObjectOutputStream(s.getOutputStream());

                Message m = new Message();

                m.setOperation(Operation.DELETE);
                m.setBody((Object) "Mensaje con delete");
                inputObjectStream.writeObject(m);

                System.out.println(m.toString());
                ObjectInputStream is = new ObjectInputStream(s.getInputStream());

                Message m2 = (Message) is.readObject();

                System.out.println(m2.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
