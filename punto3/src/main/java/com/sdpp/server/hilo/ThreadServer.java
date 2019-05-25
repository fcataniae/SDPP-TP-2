package com.sdpp.server.hilo;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.MessageProperties;
import com.sdpp.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            ObjectOutputStream outputChannel = new ObjectOutputStream (this.client.getOutputStream());
            ObjectInputStream inputChannel = new ObjectInputStream (this.client.getInputStream());
            Gson gson = new Gson();


            this.queueChannel.queueDeclare(id.toString(), false, false, true, null);

            DeliverCallback consumer = (consumerTag, delivery) -> {

                String message = new String(delivery.getBody(), UTF_8);
                Message m = new Gson().fromJson(message, Message.class);
                log.info(TAG + "sending message " + message);
                outputChannel.writeObject(m);
                log.info(TAG + "Message sent.");
                this.queueChannel.queueDelete(id.toString());

            };

            Message decodedMsg = (Message) inputChannel.readObject();
            decodedMsg.setId(id);
            String mString = gson.toJson(decodedMsg);
            log.info(TAG + "[Client has sent a msg --> \n" + mString);

            this.queueChannel.basicPublish("", this.inputQueueName, MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
            log.info(TAG + "Message published");

            this.queueChannel.basicConsume(id.toString(),true,consumer,  consumerTag -> { });

            //borro la cola que cree momentaneamente
            log.info(TAG + "Stopping thread.");
        }catch (Exception e){
            log.warn("Error while serving client",e);
        }

    }
}
