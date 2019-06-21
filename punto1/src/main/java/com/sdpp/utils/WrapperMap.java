package com.sdpp.utils;

import com.sdpp.utils.model.Host;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WrapperMap implements Serializable {

    Map<String, List<Host>> fileToPeer;

    public WrapperMap() {
        this.fileToPeer = new HashMap<String, List<Host>>() ;
    }

    public Map<String, List<Host>> getFileToPeer() {
        return fileToPeer;
    }

    public void setFileToPeer(Map<String, List<Host>>  fileToPeer) {
        this.fileToPeer = fileToPeer;
    }
}
