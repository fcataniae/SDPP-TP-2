package com.sdpp.model;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Usuario: Franco
 * Project: SDPP-TP-2
 * Fecha: 5/25/2019
 **/
@Slf4j
public class RabbitConf {

    private String user;
    private String pass;
    private Integer port;
    private String ip;
    private static RabbitConf INSTANCE;

    private RabbitConf() {


    }

    public static RabbitConf getInstance() {

        if(INSTANCE != null)
            return INSTANCE;

        String filename = "rabbitmq-properties.json";//RabbitConf.class.getClassLoader().getResource("rabbitmq-properties.json").getFile();// "rabbitmq-properties.json";//
        Gson gson = new Gson();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            log.warn("Cannot get Rabbit MQ connection configuration",e);
        }

        INSTANCE = gson.fromJson(reader,RabbitConf.class);

        return INSTANCE;

    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "RabbitConf{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", port=" + port +
                ", ip='" + ip + '\'' +
                '}';
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
