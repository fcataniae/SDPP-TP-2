
package com.sdpp.extremos.conections;

public class ClientPeer extends Thread {

    private String masterIp;
    private int masterPort;

    public ClientPeer(String masterIp, int masterPort) {
        this.masterIp = masterIp;
        this.masterPort = masterPort;
    }

    public ClientPeer() {

    }


    public void run(){

    }
}
