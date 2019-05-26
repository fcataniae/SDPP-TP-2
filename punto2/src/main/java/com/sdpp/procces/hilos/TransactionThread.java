package com.sdpp.procces.hilos;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sdpp.model.Cuenta;
import com.sdpp.model.Operation;
import com.sdpp.model.OperationResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
@Slf4j
public class TransactionThread extends Thread {

    private Socket client;
    private Long id;
    private Operation operation;
    private final Object cuenta;
    private String TAG;
    private static String filename = "cuentas.json";//TransactionThread.class.getClassLoader().getResource("cuentas.json").getFile();// "cuentas.json";//


    public TransactionThread(Socket client, Long id, Object cuenta) {
        this.client = client;
        this.cuenta = cuenta;
        this.id = id;
        this.TAG = "Thread " + this.id + " - ";
    }

    @Override
    public void run(){

        try {
            log.info(TAG + "waiting for client request");
            ObjectInputStream is = new ObjectInputStream(client.getInputStream());

            operation = (Operation) is.readObject();

            log.info(TAG + "transaction received from client " + operation.getTransaction().name());
            synchronized (cuenta) {

                switch (operation.getTransaction()){
                    case DEPOSITO:
                        makeDeposit();
                        break;
                    case EXTRACCION:
                        makeExtraction();
                        break;
                    default:
                        ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
                        OperationResponse resp = new OperationResponse();
                        resp.setResult("Unsuported operation");
                        os.writeObject(resp);
                        log.info("operation unsuported.");
                        os.close();
                        break;
                }
            }

        }catch (Exception e){
            log.warn("Error while making transaction " + operation.getTransaction().name(), e);
        }
    }

    private void makeDeposit() throws IOException {
        Cuenta c = retrieveAccount();
        OperationResponse resp = new OperationResponse();

        log.info(TAG + "current balance " + c.getSaldo());


        if(operation.getMonto().compareTo(new BigDecimal("0"))> 0) {
            c.setSaldo(c.getSaldo().add(operation.getMonto()));

            log.info(TAG + "balance after deposit " + c.getSaldo());

            resp.setResult("Deposit succesfully done, current balance of account $" + c.getSaldo());
            log.info(TAG + "Saving into file");

            saveAccount(c);
        }else {
            log.info(TAG + "cannot deposit a negative amount!");
            resp.setResult("cannot deposit a negative amount!");

        }

        ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

        log.info(TAG + "sending response to client");

        os.writeObject(resp);

    }


    private void makeExtraction() throws IOException {

        Cuenta c = retrieveAccount();
        OperationResponse resp = new OperationResponse();

        log.info(TAG + "current balance " + c.getSaldo());


        if(operation.getMonto().compareTo(new BigDecimal("0"))> 0) {

            if(c.getSaldo().compareTo(operation.getMonto()) >= 0) {
                c.setSaldo(c.getSaldo().subtract(operation.getMonto()));

                log.info(TAG + "balance after extraction " + c.getSaldo());

                resp.setResult("Extraction succesfully done, current balance of account $" + c.getSaldo());
                log.info(TAG + "Saving into file");

                saveAccount(c);
            }else {
                log.info(TAG + " current balance("+c.getSaldo()+") is minor tan amount to wxtract("+operation.getMonto()+")." );
                resp.setResult(" current balance("+c.getSaldo()+") is minor tan amount to wxtract("+operation.getMonto()+")." );
            }
        }else {
            log.info(TAG + "cannot extract a negative amount!");
            resp.setResult("cannot extract a negative amount!");

        }

        ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

        log.info(TAG + "sending response to client");

        os.writeObject(resp);


    }

    private void saveAccount(Cuenta c) throws IOException {

        String gsonAccount = new Gson().toJson(c, Cuenta.class);
        log.info(TAG + "gson account " + gsonAccount);
        File f = new File(filename);

        if(f.exists() && f.isFile()){
            log.info("deleting file");
            f.delete();
            f.createNewFile();
        }
        log.info(TAG + "writing account");
        FileOutputStream outputStream = new FileOutputStream(f);
        byte[] strToBytes = gsonAccount.getBytes();
        outputStream.write(strToBytes);
        log.info("write done");
        outputStream.close();

    }


    private Cuenta retrieveAccount(){
        log.info(TAG + "retrieving account information");
        Gson gson = new Gson();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            log.warn("Cannot retrieve information of account",e);
            throw new RuntimeException(e);
        }
        Cuenta c = gson.fromJson(reader,Cuenta.class);
        log.info("account obtained " + c);
        return c;

    }
}
