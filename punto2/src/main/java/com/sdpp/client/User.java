package com.sdpp.client;

import com.sdpp.model.Operation;
import com.sdpp.model.OperationResponse;
import com.sdpp.model.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Random;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
@Slf4j
public class User extends Thread {

    Transaction t ;
    BigDecimal monto;
    String ip;
    Long port;

    public Transaction getT() {
        return t;
    }

    public void setT(Transaction t) {
        this.t = t;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int i = 0;
        Random r = new Random();

        while(i < 100) {
            User u = new User();
            u.monto = new BigDecimal("10000");
            u.t = (r.nextBoolean())? Transaction.DEPOSITO: Transaction.EXTRACCION;
            u.ip = "localhost";
            u.port = 8000L;

            u.start();
            i++;
        }

    }

    public void makeTransaction() throws IOException, ClassNotFoundException {
        Operation o = new Operation();

        o.setMonto(monto);
        o.setTransaction(t);

        Socket s = new Socket(ip,port.intValue());

        ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

        log.info("Sending operation: " + o.toString());
        os.writeObject(o);

        ObjectInputStream is = new ObjectInputStream(s.getInputStream());

        OperationResponse or = (OperationResponse) is.readObject();
        log.info("Response for operation"+or.toString());
    }

    @Override
    public void run(){
        try {
            makeTransaction();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
