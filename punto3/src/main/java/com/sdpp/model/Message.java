package com.sdpp.model;

import java.io.Serializable;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
public class Message implements Serializable {

    private Long id;
    private Operation operation;
    private Object body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", operation=" + operation +
                ", body=" + body +
                '}';
    }
}
