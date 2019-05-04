package com.sdpp.master;

import com.sdpp.master.connections.ServerCon;

public class Master {

    public static void main(String[] args) {

        ServerCon sc = new ServerCon(9000);

        sc.start();
    }
}
