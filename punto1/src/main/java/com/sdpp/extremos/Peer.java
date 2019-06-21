package com.sdpp.extremos;

import com.sdpp.extremos.conections.PeerCon;

/**
 * CLASE SOLO UTILIZADA CORRERLA EN ENTORNO DE DESARROLLO LOCAL
 * POR ESO ESTAN HARDCODEADOS LOS PARAMETROS
 * CLASE MAIN DE EXPORTADO .JAR VER com.sdpp.Main
 */
public class Peer {

    public static void main(String[] args) throws InterruptedException{

        PeerCon peer = new PeerCon(8001,"C:\\Users\\franco\\Desktop\\MemoTest\\");

        peer.run();

    }

}
