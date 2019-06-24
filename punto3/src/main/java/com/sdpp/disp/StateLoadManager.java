package com.sdpp.disp;

import com.rabbitmq.client.Channel;
import com.sdpp.nodes.Node;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;

import static com.sdpp.model.Estado.IDLE;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
@Slf4j
public class StateLoadManager extends Thread {

    private List<Node> nodosActivos;
    private String nodeQueueProccesName ;
    private Channel queueChannel;

    public StateLoadManager(List<Node> nodosActivos, String nodeQueueProccesName, Channel queueChannel) {
        this.nodosActivos = nodosActivos;
        this.nodeQueueProccesName = nodeQueueProccesName;
        this.queueChannel = queueChannel;
    }

    @Override
    public void run() {
        log.info("STATE MANAGER STARTING");

        while(true){
            try {
                log.info("Quantity of nodes actives " + nodosActivos.size());

                log.info("Searching for idle node...");
                Iterator iterator = nodosActivos.iterator();
                while(iterator.hasNext()){
                    Node node = (Node) iterator.next();
                    if (node.getEstado().equals(IDLE) && node.getLoad().equals(0L)) {

                        String nodeQueue = nodeQueueProccesName + node.getNodeId();
                        log.info("IDLE node found - node queue: " + nodeQueue);

                        node.setActive(Boolean.FALSE);

                        log.info("Deleting queue and node from nodelist");

                        iterator.remove();
                        node.delete();

                        log.info("Deleting succesfull for node " + node.getNodeId());
                    }
                }
                log.info("Searching in node list done");

                log.info("Sleeping thread for 10000 millis");
                Thread.sleep(10000);

            }catch (Exception e ){
                log.error("Error while deleting queue for IDLE node ", e);
            }

        }

    }
}
