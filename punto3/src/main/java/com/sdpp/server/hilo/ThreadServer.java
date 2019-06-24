package com.sdpp.server.hilo;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import com.sdpp.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
@Slf4j
public class ThreadServer implements Runnable {

    private Socket client;
    private Long id;
    private Channel queueChannel;
    private String inputQueueName;
    private String TAG;


    public ThreadServer(Socket client, Long id, Channel queueChannel, String inputQueueName) {
        this.client = client;
        this.id = id;
        this.queueChannel = queueChannel;
        this.inputQueueName = inputQueueName;
        this.TAG = "Thread " + this.id +" - ";
    }

    @Override
    public void run() {

        try{
            ObjectInputStream inputChannel = new ObjectInputStream (this.client.getInputStream());
            Gson gson = new Gson();


            this.queueChannel.queueDeclare(id.toString(), false, false, true, null);

            Consumer consumer = new DefaultConsumer(this.queueChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    ObjectOutputStream outputChannel = new ObjectOutputStream (client.getOutputStream());
                    String message = new String(body, UTF_8);
                    log.info("String message: " + message );
                    Message m = new Gson().fromJson(message, Message.class);
                    log.info(TAG + "sending message " + message);
                    outputChannel.writeObject(m);
                    log.info(TAG + "Message sent.");
                    log.info("DELETING QUEUE");
                    queueChannel.queueDeclare("MensajeRecibido "+id.toString(), false, false, true, null);
                    queueChannel.basicPublish("", "MensajeRecibido "+id.toString(), MessageProperties.PERSISTENT_TEXT_PLAIN, (m.getBody().toString() + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())).getBytes());
                    queueChannel.queueDelete(id.toString());

                }
            };

            Message decodedMsg = (Message) inputChannel.readObject();
            decodedMsg.setId(id);
            String mString = gson.toJson(decodedMsg);
            log.info(TAG + "[Client has sent a msg --> \n" + mString);

            this.queueChannel.basicPublish("", this.inputQueueName, MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
            log.info(TAG + "Message published");

            this.queueChannel.basicConsume(id.toString(),false,consumer);

            //borro la cola que cree momentaneamente
            log.info(TAG + "Stopping thread.");
        }catch (Exception e){
            log.warn("Error while serving client",e);
        }

    }
}
