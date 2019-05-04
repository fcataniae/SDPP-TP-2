package com.sdpp.extremos;

import com.sdpp.extremos.conections.ClientPeer;
import com.sdpp.extremos.conections.ServerPeer;

public class Peer {

    public static void main(String[] args) throws InterruptedException{


        ClientPeer client = new ClientPeer("localhost",9000,"C:\\Users\\Franco\\Desktop\\MemoTest", 8002);
        ServerPeer serverPeer = new ServerPeer(8002,"C:\\Users\\Franco\\Desktop\\MemoTest");


        serverPeer.start();
        client.start();


        client.join();

        serverPeer.join();
    }

}
