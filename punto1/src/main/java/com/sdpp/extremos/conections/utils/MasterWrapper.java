package com.sdpp.extremos.conections.utils;

/**
 * User: fcatania
 * Date: 16/5/2019
 * Time: 17:02
 */
public class MasterWrapper {

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

    public MasterWrapper() {

    }

    private String ip;

    @Override
    public String toString() {
        return "MasterWrapper{" +
                "port=" + port +
                ", ip='" + ip + '\'' +
                '}';
    }
}