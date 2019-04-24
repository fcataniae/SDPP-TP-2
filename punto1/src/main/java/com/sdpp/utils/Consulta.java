package com.sdpp.utils;

import com.sdpp.utils.enums.Method;

import java.io.Serializable;

public class Consulta implements Serializable {

    private Method method;
    private String fileName;



    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
