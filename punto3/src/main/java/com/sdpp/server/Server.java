package com.sdpp.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sdpp.model.RabbitConf;
import com.sdpp.server.hilo.ThreadServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
@Slf4j
public class Server {

    private final RabbitConf conf = RabbitConf.getInstance();
    private Channel queueChannel;
    private String inputQueueName;


    private Integer port;


    public Server(Integer port) {

        this.port = port;
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
    public void startServer(){
        log.info("Configuring connection w RabbitMQ");
        configureConnectionWRabbit();
        log.info("Conecction established. Starting server...");

        try {
            ServerSocket ss = new ServerSocket(this.port);
            log.info("Server listening on port " + this.port);
            Long id = 0L;
            while (true) {
                Socket client = ss.accept();
                log.info("Client connected from " + client.getInetAddress().getCanonicalHostName()+":"+client.getPort());
                ThreadServer ts = new ThreadServer(client, id, this.queueChannel, this.inputQueueName);
                log.info("Nuevo TrheadServer: "+ id);
                Thread tsThread = new Thread(ts);
                tsThread.start();
                id++;
            }

        } catch (IOException e) {
            log.warn("Error while creating server." , e);
        }

    }

    public static void main(String[] args) {
        Server s = new Server(8001);
        s.startServer();
    }
}
