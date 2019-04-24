
package com.sdpp.extremos.conections;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.WrapperList;
import com.sdpp.utils.enums.Method;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sdpp.utils.enums.Method.DOWNLOAD;

public class ClientPeer extends Thread {

    private String masterIp;
    private int masterPort;
    private String sharedFolder;
    private int serverPeerIp;

    public ClientPeer(String masterIp, int masterPort, String sharedFolder, int serverPeerIp) {
        this.masterIp = masterIp;
        this.masterPort = masterPort;
        this.sharedFolder = sharedFolder;
        this.serverPeerIp = serverPeerIp;
    }

    public void run(){

        try(
                Socket ss = new Socket(this.masterIp, this.masterPort)
        ){

            ObjectInputStream inputChannel;
            ObjectOutputStream outputChannel = new ObjectOutputStream(ss.getOutputStream());


            WrapperList wpl = getWplObject();


            outputChannel.writeObject(wpl);

            System.out.println("Ingrese el nombre del archivo a buscar entre los peers");

            Scanner sc = new Scanner(System.in);

            String nombre = sc.next();

            Consulta  c = new Consulta();

            c.setFileName(nombre);
            c.setMethod(DOWNLOAD);

            outputChannel.writeObject(c);

            inputChannel = new ObjectInputStream(ss.getInputStream());

            



        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * Devuelve el objeto wpl que es un wrapper para la lista de archivos que comparte el peer
     * y ademas el host (su ip) y el puerto en el que esta escuchando el servidor peer
     * @return
     */
    private WrapperList getWplObject() throws IOException{
        WrapperList wpl = new WrapperList();
        wpl.setFileList(getDownloadableFiles());
        wpl.setServerIp(InetAddress.getLocalHost().getHostAddress());
        wpl.setServerPort(this.serverPeerIp);
        return wpl;

    }


    /**
     * Devuelve la lista de nombres de archivos de la shared folder definida
     * @return
     * @throws IOException
     */
    public List<String> getDownloadableFiles() throws IOException {
        List<String> fileNameList = new ArrayList<>();
        Files.walk(Paths.get(this.sharedFolder)).forEach(ruta -> {
            if (Files.isRegularFile(ruta)) {
                fileNameList.add(ruta.getFileName().toString());
            }
        });
        return fileNameList;
    }


}
