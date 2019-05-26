package com.sdpp.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
public class Operation implements Serializable {

    private Transaction transaction;

    @Override
    public String toString() {
        return "Operation{" +
                "transaction=" + transaction +
                ", monto=" + monto +
                '}';
    }

    private BigDecimal monto;

    public Operation() {
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
