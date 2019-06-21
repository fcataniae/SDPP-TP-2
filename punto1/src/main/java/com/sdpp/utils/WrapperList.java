package com.sdpp.utils;

import com.sdpp.utils.model.Host;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WrapperList implements Serializable {

    private List<String> fileList;
    private Host serverHost;

    public Host getServerHost() {
        return serverHost;
    }

    public void setServerHost(Host serverHost) {
        this.serverHost = serverHost;
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

    @Override
    public String toString() {
        return "WrapperList{" +
                "fileList=" + fileList +
                ", serverHost=" + serverHost +
                '}';
    }
}
