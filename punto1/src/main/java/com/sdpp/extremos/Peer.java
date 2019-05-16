package com.sdpp.extremos;

import com.sdpp.extremos.conections.PeerCon;

public class Peer {

    public static void main(String[] args) throws InterruptedException{

        PeerCon peer = new PeerCon(8000,"C:\\Users\\fcatania\\Desktop\\Aspectos");

        peer.run();

    }

}
