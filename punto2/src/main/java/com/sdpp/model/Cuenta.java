package com.sdpp.model;

import java.math.BigDecimal;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/26/2019
 **/
public class Cuenta {

    private BigDecimal saldo;
    private Long nro;
    private Long suc;
    private Long dver;

    @Override
    public String toString() {
        return "Cuenta{" +
                "saldo=" + saldo +
                ", nro=" + nro +
                ", suc=" + suc +
                ", dver=" + dver +
                '}';
    }

    public Cuenta() {
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Long getNro() {
        return nro;
    }

    public void setNro(Long nro) {
        this.nro = nro;
    }

    public Long getSuc() {
        return suc;
    }

    public void setSuc(Long suc) {
        this.suc = suc;
    }

    public Long getDver() {
        return dver;
    }

    public void setDver(Long dver) {
        this.dver = dver;
    }
}
