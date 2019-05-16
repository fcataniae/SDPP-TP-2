package com.sdpp.extremos.conections.utils;

/**
 * User: fcatania
 * Date: 16/5/2019
 * Time: 17:02
 */
public class Master {

    private int port;

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

    public Master() {

    }

    private String ip;

    @Override
    public String toString() {
        return "Master{" +
                "port=" + port +
                ", ip='" + ip + '\'' +
                '}';
    }
}