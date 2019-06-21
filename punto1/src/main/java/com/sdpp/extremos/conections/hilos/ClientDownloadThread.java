package com.sdpp.extremos.conections.hilos;

import com.sdpp.utils.Consulta;
import com.sdpp.utils.FileUtil;
import com.sdpp.utils.model.Host;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;

import static com.sdpp.utils.enums.Method.DOWNLOAD;


@Slf4j
public class ClientDownloadThread extends Thread {

    private Host host;
    private String fileName;
    private String sharedFolder;

    public ClientDownloadThread(Host host, String fileName, String sharedFolder) {
        this.host = host;
        this.fileName = fileName;
        this.sharedFolder = sharedFolder;
    }

    public String getFileName() {
        return fileName;
    }
    public void run() {

        try (Socket ss = new Socket(host.getIp(), host.getPort())) {

            log.info("Conected to peer...");
            ObjectOutputStream os = new ObjectOutputStream(ss.getOutputStream());

            Consulta c = new Consulta();
            c.setMethod(DOWNLOAD);
            c.setFileName(fileName);

            log.info("Sending download request for file " + fileName.toUpperCase());

            os.writeObject(c);
            ObjectInputStream is = new ObjectInputStream(ss.getInputStream());


            log.info("Waiting for peer response ...");

            FileUtil fu = (FileUtil) is.readObject();

            log.info("Response obtained, saving file in disk");

            fileName = fu.getName() + "-download"+"."+fu.getExtension();
            Files.write(new File(this.sharedFolder + "\\" +fileName).toPath(), fu.getBinary());

            log.info("File succesfuly created ...");

        }
        catch (Exception e ){
            log.warn("An error happened while downloading file " + fileName  + " from peer ", e);
        }
    }


}
