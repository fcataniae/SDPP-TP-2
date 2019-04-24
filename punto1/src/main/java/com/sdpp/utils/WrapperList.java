package com.sdpp.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WrapperList implements Serializable {

    private List<String> fileList;
    private int serverPort;
    private String serverIp;

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public WrapperList() {
        this.fileList = new ArrayList<>();
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
