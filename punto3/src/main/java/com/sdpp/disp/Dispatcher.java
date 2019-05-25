package com.sdpp.disp;

import com.rabbitmq.client.*;
import com.sdpp.model.RabbitConf;
import com.sdpp.nodes.Node;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
@Slf4j
public class Dispatcher {

    private final RabbitConf conf = RabbitConf.getInstance();

    private Channel queueChannel;

    private static final String nodeQueueProccesName = "Node_proces_";
    private static final String nodeStatusQueueName = "Node_status_";
    private static Long currentNode = 0L;

    private String inputQueueName = "inputQueue";

    private List<String> nodosActivos;


    public Dispatcher(){
        this.nodosActivos = new ArrayList<>();
        configureConnectionWRabbit();
    }

    private void configureConnectionWRabbit(){
        try {
            this.inputQueueName = "inputQueue";
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(this.conf.getIp());
            connectionFactory.setPort(this.conf.getPort());
            connectionFactory.setUsername(this.conf.getUser());
            connectionFactory.setPassword(this.conf.getPass());
            Connection queueConnection = connectionFactory.newConnection();
            this.queueChannel = queueConnection.createChannel();
            this.queueChannel.queueDeclare(this.inputQueueName, true, false, false, null);
        } catch (Exception e) {
            log.warn("Error while conecting to Rabbit", e);
            throw new RuntimeException(e);
        }
    }


    public void startDispatcher(){

        try{
            log.info("Starting Dispatcher");

            Consumer consumer = new DefaultConsumer(this.queueChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, UTF_8);
                    log.info("Message received to process " + message);
                    Long queue = getQueue();
                    log.info("Dispatching to queue " + queue);

                    queueChannel.basicPublish("", nodeQueueProccesName + queue,MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
                }
            };

            log.info("consuming from queue");
            this.queueChannel.basicConsume(this.inputQueueName, true, consumer);


        }
        catch (Exception e){
            log.warn("Error while running dispatcher", e );
        }
    }

    public Long getQueue(){
        Long queue = 0L;

        if(nodosActivos.isEmpty()){

            Node node = new Node(nodeStatusQueueName + currentNode,nodeQueueProccesName+ currentNode, 20L);


            node.start();
            this.nodosActivos.add(currentNode.toString());
            queue = currentNode;
            currentNode++;
        }

        return queue;
    }

    public static void main(String[] args) {
        Dispatcher d = new Dispatcher();
        d.startDispatcher();
    }

}
