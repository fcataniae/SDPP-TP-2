package com.sdpp.extremos.conections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.FileUtil;
import com.sdpp.utils.model.Host;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;

import static com.sdpp.utils.enums.Method.DOWNLOAD;

public class ClientDownloadThread implements Runnable {

    private Host host;
    private String fileName;
    private String sharedFolder;

    public ClientDownloadThread(Host host, String fileName, String sharedFolder) {
        this.host = host;
        this.fileName = fileName;
        this.sharedFolder = sharedFolder;
    }

    public void run() {

        System.out.println(host.getIp()+":"+host.getPort());
        try (Socket ss = new Socket(this.host.getIp(), this.host.getPort())) {

            ObjectOutputStream os = new ObjectOutputStream(ss.getOutputStream());

            Consulta c = new Consulta();
            c.setMethod(DOWNLOAD);
            c.setFileName(fileName);
            System.out.println("enviando consulta a peer " + c.toString());

            os.writeObject(c);

            ObjectInputStream is = new ObjectInputStream(ss.getInputStream());
            System.out.println("Esperando respuesta del peer");
            FileUtil fu = (FileUtil) is.readObject();
            System.out.println("Respuesta obtenida");

            System.out.println("Guardando archivo");

            Files.write(new File(this.sharedFolder + "\\" + fu.getName() + "-download"+"."+fu.getExtension()).toPath(), fu.getBinary());

            System.out.println("Se creo el archivo descargado en disco correctamente");

        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }
}
