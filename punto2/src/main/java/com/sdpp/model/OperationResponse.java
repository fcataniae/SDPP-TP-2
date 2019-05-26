package com.sdpp.model;

import java.io.Serializable;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
public class OperationResponse implements Serializable {

    private String result;

    @Override
    public String toString() {
        return "OperationResponse{" +
                "result='" + result + '\'' +
                '}';
    }

    public OperationResponse() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
