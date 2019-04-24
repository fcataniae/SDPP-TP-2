package com.sdpp.utils;

import com.sdpp.utils.model.Host;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class WrapperMap implements Serializable {

    Map<String, Host> fileToPeer;

    public WrapperMap() {
        this.fileToPeer = new HashMap<String, Host>();
    }

    public Map<String, Host> getFileToPeer() {
        return fileToPeer;
    }

    public void setFileToPeer(Map<String, Host> fileToPeer) {
        this.fileToPeer = fileToPeer;
    }
}
