package com.sdpp.utils.model;

import java.io.Serializable;

public class Host implements Serializable {

    private int port;
    private String ip;

    public Host(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
