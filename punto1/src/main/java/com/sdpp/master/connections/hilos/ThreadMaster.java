package com.sdpp.master.connections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.WrapperList;
import com.sdpp.utils.WrapperMap;
import com.sdpp.utils.model.Host;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ThreadMaster implements Runnable {

    private Map<String, Host> archivos;
    private Socket cliente;

    public ThreadMaster(Map<String, Host> archivos, Socket cliente) {
        this.archivos = archivos;
        this.cliente = cliente;
    }

    public void run() {

        try{
            ObjectInputStream is = new ObjectInputStream(this.cliente.getInputStream());
            WrapperMap wpm = new WrapperMap();
            WrapperList wpl = (WrapperList) is.readObject();

            wpl.getFileList().forEach( arch -> this.archivos.put(arch, new Host(wpl.getServerPort(),wpl.getServerIp())));

            System.out.println("Cantidad de arhivos: " + this.archivos.size());
            Consulta c = (Consulta)(is.readObject());

            this.archivos.forEach( (k,v) -> {
                if(k.contains(c.getFileName())){
                    wpm.getFileToPeer().put(k,v);
                }
            });
            ObjectOutputStream os = new ObjectOutputStream(this.cliente.getOutputStream());

            os.writeObject(wpm);

        }catch (Exception e ){
            e.printStackTrace();
        }


    }
}
