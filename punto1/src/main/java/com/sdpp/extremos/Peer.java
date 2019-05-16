package com.sdpp.extremos;

import com.sdpp.extremos.conections.ClientPeer;
import com.sdpp.extremos.conections.ServerPeer;

public class Peer {

    public static void main(String[] args) throws InterruptedException{


        ClientPeer client = new ClientPeer("localhost",9000,"C:\\Users\\fcatania\\Desktop\\demo-jwt-master", 8002);
        ServerPeer serverPeer = new ServerPeer(8002,"C:\\Users\\fcatania\\Desktop\\demo-jwt-master");


        serverPeer.start();
        client.start();


        client.join();

        serverPeer.join();
    }

}
