package com.sdpp.extremos;

import com.sdpp.extremos.conections.ClientPeer;
import com.sdpp.extremos.conections.ServerPeer;

public class Peer {

    public static void main(String[] args) throws InterruptedException{


        ClientPeer client = new ClientPeer("localhost",8000,"C:\\Users\\Franco\\Desktop\\MemoTest", 8000);
        ServerPeer serverPeer = new ServerPeer(8000,"C:\\Users\\Franco\\Desktop\\MemoTest");

        serverPeer.start();
        client.start();


        client.join();

        serverPeer.join();
    }

}
