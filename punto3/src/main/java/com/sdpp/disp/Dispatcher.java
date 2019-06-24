package com.sdpp.disp;

import com.rabbitmq.client.*;
import com.sdpp.model.Estado;
import com.sdpp.model.RabbitConf;
import com.sdpp.nodes.Node;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.sdpp.model.Estado.*;
import static com.sdpp.model.Estado.CRITICAL;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
@Slf4j
public class Dispatcher extends Thread{

    private final RabbitConf conf = RabbitConf.getInstance();

    private Channel queueChannel;

    private static final String nodeQueueProccesName = "Node_proces_";
    private static Long currentNode = 1L;
    private String notificationQueueName = "notification";
    private String inputQueueName = "inputQueue";
    private List<Node> nodosActivos;

    private StateLoadManager manager;

    public Dispatcher(){
        this.nodosActivos = new ArrayList<>();
        configureConnectionWRabbit();
        this.manager = new StateLoadManager(this.nodosActivos,nodeQueueProccesName,this.queueChannel);
        this.manager.start();
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
            this.queueChannel.queueDeclare(this.inputQueueName, true, false, false, null);
            this.queueChannel.queueDeclare(this.notificationQueueName, true, false, false, null);
        } catch (Exception e) {
            log.warn("Error while conecting to Rabbit", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        startDispatcher();
    }
    public void startDispatcher(){

        try{
            log.info("Starting Dispatcher");

            Consumer consumer = new DefaultConsumer(this.queueChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, UTF_8);
                    log.info("Message received to process " + message);
                    Long node = getQueue();
                    log.info("Dispatching to queue " + node);
                    incrementLoad(node);
                    queueChannel.basicPublish("", nodeQueueProccesName + node,MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
                }
            };
            Consumer notificationConsumer = new DefaultConsumer(this.queueChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String node = new String(body, UTF_8);
                    log.info("Notification from node " + node);

                    decrementLoad(Long.valueOf(node));
                }
            };

            log.info("consuming from queue");
            this.queueChannel.basicConsume(this.inputQueueName, true, consumer);
            this.queueChannel.basicConsume(this.notificationQueueName, true, notificationConsumer);

            while(true){}
        }
        catch (Exception e){
            log.warn("Error while running dispatcher", e );
        }
    }

    public Long getQueue(){

        AtomicReference<Long> queue = new AtomicReference<>(0L);

        if(nodosActivos.isEmpty()){
            queue.set(createNode().getNodeId());
        }else{
            this.nodosActivos.forEach( n -> {
                if ((n.getEstado().equals(IDLE) || n.getEstado().equals(NORMAL)) && n.getActive()){
                    queue.set(n.getNodeId());
                }
            });
            if(queue.get().equals(0L)){
                queue.set(createNode().getNodeId());
            }
        }
        log.warn(nodosActivos.toString());
        return queue.get();
    }

    public static void main(String[] args) {
        Dispatcher d = new Dispatcher();
        d.start();
    }

    private void incrementLoad(Long id){

        nodosActivos.forEach( n -> {
            if(id.equals(n.getNodeId()) && n.getActive()){
                n.setLoad( n.getLoad() + 1);
                n.setEstado(updateStateQueue(n));
            }
        });

    }


    private void decrementLoad(Long id){

        nodosActivos.forEach( n -> {
            if( id.equals(n.getNodeId()) && n.getActive()){
                n.setLoad( n.getLoad() - 1);
                n.setEstado(updateStateQueue(n));
            }
        });


    }

    private Node createNode(){

        Node node = new Node(currentNode , notificationQueueName,nodeQueueProccesName+ currentNode, 8L);
        node.start();
        this.nodosActivos.add(node);
        currentNode++;

        return node;

    }

    private Estado updateStateQueue(Node node){

        Estado state;
        log.info("LOAD " +node.getLoad());
        if(node.getLoad() <= node.getMaxLoad() * 0.2){
            state = IDLE;
        }else if(node.getLoad() <= node.getMaxLoad() * 0.4){
            state = NORMAL;
        }else if(node.getLoad() <= node.getMaxLoad() * 0.7){
            state = ALERT;
        }else {
            state = CRITICAL;
        }

        return state;

    }
}
