package com.sdpp.nodes;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import com.sdpp.model.Message;
import com.sdpp.model.RabbitConf;
import com.sdpp.nodes.hilos.NodeThread;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/

@Slf4j
public class Node extends Thread{

    private String nodeStateQueueName;
    private String nodeProccesQueueName;
    private RabbitConf conf;
    private Channel queueChannel;
    private ExecutorService tpool;
    private Long load;
    private Long maxLoad;

    public Node(String nodeStateQueueName, String nodeProccesQueueName, Long maxLoad) {
        this.nodeStateQueueName = nodeStateQueueName;
        this.nodeProccesQueueName = nodeProccesQueueName;
        this.conf = RabbitConf.getInstance();
        this.maxLoad = maxLoad;

        this.tpool = Executors.newFixedThreadPool(this.maxLoad.intValue());

    }


    private void configureConnectionWRabbit(){
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(this.conf.getIp());
            connectionFactory.setPort(this.conf.getPort());
            connectionFactory.setUsername(this.conf.getUser());
            connectionFactory.setPassword(this.conf.getPass());
            Connection queueConnection = connectionFactory.newConnection();
            this.queueChannel = queueConnection.createChannel();
            this.queueChannel.queueDeclare(this.nodeProccesQueueName, true, false, false, null);
            this.queueChannel.queueDeclare(this.nodeStateQueueName, true, false, false, null);
        } catch (Exception e) {
            log.warn("Error while conecting to Rabbit", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(){
        log.info("Configuring connection w RabbitMQ");
        configureConnectionWRabbit();
        log.info("Conecction established. Starting server...");
        try {
            Consumer consumer = new DefaultConsumer(this.queueChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, UTF_8);
                    Message m = new Gson().fromJson(message,Message.class);
                    log.info("Message received to process " + message);

                    NodeThread nt = new NodeThread(queueChannel,m);
                    tpool.execute(nt);
                }
            };

            log.info("consuming from queue");
            this.queueChannel.basicConsume(this.nodeProccesQueueName, true, consumer);

        } catch (IOException e) {
            log.warn("Error while consuming queue",e);
        }

    }

}
