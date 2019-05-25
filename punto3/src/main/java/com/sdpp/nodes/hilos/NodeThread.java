package com.sdpp.nodes.hilos;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.sdpp.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
@Slf4j
public class NodeThread implements Runnable {


    private Channel channel;
    private Message message;

    public NodeThread(Channel channel, Message message) {
        this.channel = channel;
        this.message = message;

        this.TAG = "THREADNODE " + Thread.currentThread().getName() + " - ";
    }

    private String TAG;

    @Override
    public void run() {
        try {
            log.info(TAG + "processing request ");
            String mString;
            switch (message.getOperation()) {

                case DELETE :
                    log.info(TAG + "operation DELETE");

                    message.setBody((String) "DELETEd Exceuted from thread node " );
                    mString = new Gson().toJson(message);
                    this.channel.basicPublish("", message.getId().toString(), MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
                    break;

                case POST :
                    log.info(TAG + "operation POST");

                    message.setBody((String) "POSTED Exceuted from thread node " );
                    mString = new Gson().toJson(message);
                    this.channel.basicPublish("", message.getId().toString(), MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
                    break;

                case GET :
                    log.info(TAG + "operation GET");

                    message.setBody((String) "GETED Exceuted from thread node " );
                    mString = new Gson().toJson(message);
                    this.channel.basicPublish("", message.getId().toString(), MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
                    break;

                default:
                    log.info(TAG + "not suported operation");

                    message.setBody((String) "Not suported" );
                    mString = new Gson().toJson(message);
                    this.channel.basicPublish("", message.getId().toString(), MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
                    break;


            }

            log.info(TAG+ "Exiting thread");
        } catch (IOException e) {
            log.warn(TAG + "Error while runing node threar ", e);
        }
    }
}
