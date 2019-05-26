package com.sdpp.nodes;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import com.sdpp.model.Estado;
import com.sdpp.model.Message;
import com.sdpp.model.RabbitConf;
import com.sdpp.nodes.hilos.NodeThread;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

import static com.sdpp.model.Estado.*;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/

@Slf4j
public class Node extends Thread{

    private Long nodeId;
    private String notificationQueueName;
    private String nodeProccesQueueName;
    private RabbitConf conf;
    private Channel queueChannel;
    private Long load;
    private Boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nodeId, node.nodeId) &&
                Objects.equals(notificationQueueName, node.notificationQueueName) &&
                Objects.equals(nodeProccesQueueName, node.nodeProccesQueueName) &&
                Objects.equals(conf, node.conf) &&
                Objects.equals(queueChannel, node.queueChannel) &&
                Objects.equals(load, node.load) &&
                Objects.equals(active, node.active) &&
                Objects.equals(maxLoad, node.maxLoad) &&
                estado == node.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, notificationQueueName, nodeProccesQueueName, conf, queueChannel, load, active, maxLoad, estado);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    private Long maxLoad;
    private Estado estado;

    public Node(Long nodeId,String notificationQueueName, String nodeProccesQueueName, Long maxLoad) {
        this.nodeId = nodeId;
        this.notificationQueueName = notificationQueueName;
        this.nodeProccesQueueName = nodeProccesQueueName;
        this.conf = RabbitConf.getInstance();
        this.maxLoad = maxLoad;
        this.load = 0L;
        this.estado = IDLE;
        this.active = Boolean.TRUE;
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
            this.queueChannel.queueDeclare(this.notificationQueueName, true, false, false, null);
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
        log.info("Max load : " + maxLoad);
        try {
            Consumer consumer = new DefaultConsumer(this.queueChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, UTF_8);
                    Message m = new Gson().fromJson(message,Message.class);
                    log.info("Message received to process " + message);

                    NodeThread nt = new NodeThread(queueChannel,m,notificationQueueName,nodeId.toString());

                    execute(nt);

                }
            };

            log.info("consuming from queue");
            this.queueChannel.basicConsume(this.nodeProccesQueueName, true, consumer);

        } catch (IOException e) {
            log.warn("Error while consuming queue",e);
        }

    }

    public Long getLoad() {
        return load;
    }

    public void setLoad(Long load) {
        this.load = load;
    }

    public Long getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(Long maxLoad) {
        this.maxLoad = maxLoad;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + nodeId +
                "estate=" + estado.name()+
                '}';
    }

    private void execute(NodeThread nt ){
        try {

            log.debug("current load " + load);

            while(load > maxLoad){
                log.info("waiting for realease");
                Thread.sleep(1000);
            }

            log.debug("Release obtained, starting thread");
            nt.start();


        } catch (InterruptedException e) {
           log.warn("Error while trying to excecute thread node",e);
        }

    }



}
