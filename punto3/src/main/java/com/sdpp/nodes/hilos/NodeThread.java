package com.sdpp.nodes.hilos;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.sdpp.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
@Slf4j
public class NodeThread extends Thread {


    private Channel channel;
    private Message message;
    private String notificationQueueName;
    private String currentNode;

    public NodeThread(Channel channel, Message message, String notificationQueueName,String currentNode) {
        this.channel = channel;
        this.message = message;
        this.notificationQueueName = notificationQueueName;
        this.currentNode = currentNode;

        this.TAG = "THREADNODE " + Thread.currentThread().getName() + " - ";
    }

    private String TAG;

    @Override
    public void run() {
        try {
            log.info(TAG + "processing request ");
            String mString;
            Gson gson = new Gson();
            switch (message.getOperation()) {

                case DELETE :
                    log.info(TAG + "operation DELETE");

                    message.setBody(String.valueOf(calculateE((String)message.getBody()) + " " + new SimpleDateFormat("yyMMMdd hh:mm:ss").format(new Date())));
                    mString = new Gson().toJson(message);
                    this.channel.basicPublish("", message.getId().toString(), MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
                    break;

                case POST :
                    log.info(TAG + "operation POST");

                    message.setBody(calculateE((String)message.getBody()));
                    mString = new Gson().toJson(message);
                    this.channel.basicPublish("", message.getId().toString(), MessageProperties.PERSISTENT_TEXT_PLAIN, mString.getBytes());
                    break;

                case GET :
                    log.info(TAG + "operation GET");

                    message.setBody(calculateE((String)message.getBody()));
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
            Thread.sleep(6000);
            this.channel.basicPublish("",notificationQueueName,MessageProperties.PERSISTENT_TEXT_PLAIN,currentNode.getBytes());
            log.info(TAG+ "Exiting thread");
        } catch (Exception e) {
            log.warn(TAG + "Error while runing node threar ", e);
        }
    }

   private double calculateE(String toconvert) {
        char[] c2 = toconvert.toCharArray();

        BigDecimal b = new BigDecimal("0.0");
        for(char c3 : c2){
            b = b.add(new BigDecimal((int) c3 * 2.423414 *100000));
        }
        b = b.setScale(0, RoundingMode.CEILING);

        double sum = 1.0D;
        int b2 = b.intValue();
        for (int i = b2 -1 ; i > 0; --i )
            sum =  1 + 1.0 * sum / i;

        return sum;
    }
}
