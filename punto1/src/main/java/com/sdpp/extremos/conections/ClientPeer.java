
package com.sdpp.extremos.conections;

import com.sdpp.extremos.conections.hilos.ClientDownloadThread;
import com.sdpp.utils.Consulta;
import com.sdpp.utils.WrapperList;
import com.sdpp.utils.WrapperMap;
import com.sdpp.utils.model.Host;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.sdpp.utils.enums.Method.ADD_FILE;
import static com.sdpp.utils.enums.Method.DOWNLOAD;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
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

        log.info("Client connecting to server in " + masterIp + ":" + masterPort);
        try(
                Socket ss = new Socket(masterIp, masterPort)
        ){
            log.info("Conection established with MasterWrapper...");

            ObjectInputStream inputChannel;
            ObjectOutputStream outputChannel = new ObjectOutputStream(ss.getOutputStream());


            WrapperList wpl = getWplObject();

            log.info("Sending shared file list to master...");

            outputChannel.writeObject(wpl);


            while(true) {

                log.info("Search file by name in master: ");


                Scanner sc = new Scanner(System.in);
                String nombre = sc.next();
                Consulta c = new Consulta();
                c.setFileName(nombre);
                c.setMethod(DOWNLOAD);

                log.info("Sending request to Master...");

                outputChannel.writeObject(c);
                inputChannel = new ObjectInputStream(ss.getInputStream());
                WrapperMap wpm = (WrapperMap) inputChannel.readObject();

                if(!wpm.getFileToPeer().isEmpty()) {
                    log.info("File list obtained from server: ");

                    wpm.getFileToPeer().forEach((k, v) ->
                            v.forEach( v2 -> log.info(k + " From: " + v2.getIp() + ":" + v2.getPort())));


                    Boolean found = FALSE;
                    Boolean breaked = FALSE;
                    Host h = null;
                    String finalNombre;

                    do{
                        log.info("Insert file name to download");

                        finalNombre = sc.next();
                        List<Host> hostsFound = new ArrayList<>();

                        for (Map.Entry<String, List<Host>> entry : wpm.getFileToPeer().entrySet()) {
                            String k = entry.getKey();
                            List<Host> value = entry.getValue();
                            if (k.equalsIgnoreCase(finalNombre)) {
                                hostsFound.addAll(value);
                            }
                        }

                        if(hostsFound.isEmpty()){
                            log.info("No file matching that name were found!");
                            log.info("Do another search in this list? Y/N");
                            Boolean validOption = FALSE;
                            String opt;
                            do{
                                opt = sc.next();

                                if(opt.equalsIgnoreCase("N") || opt.equalsIgnoreCase("Y")){
                                    validOption = TRUE;
                                }else{
                                    log.info("Not a valid option, must be Y/N");
                                }
                            }while(!validOption);
                            if(opt.equalsIgnoreCase("N"))
                                breaked = TRUE;
                        }else{
                            if(hostsFound.size() > 1){
                                log.info("More than one match where found!");
                                log.info("Select one host for download the file:");

                                for (Host v : hostsFound) {
                                    log.info(hostsFound.indexOf(v) + " - " + v.getIp() + ":" + v.getPort());
                                }


                                Boolean selected = FALSE;

                                do{
                                    log.info("Select an option:");
                                    String hostSelected = sc.next();
                                    if(hostSelected.matches("[0-9]+")){
                                       if(hostsFound.size() > Integer.valueOf(hostSelected)){
                                            h = hostsFound.get(Integer.valueOf(hostSelected));
                                            selected = TRUE;
                                            found = TRUE;
                                       } else {
                                           log.info("Not a valid option!");
                                       }
                                    }else {
                                        log.info("You must enter a valid number!");
                                    }
                                }while(!selected);

                            }else {
                                log.info("Only one match where found");
                                h = hostsFound.get(0);
                                found = TRUE;
                            }
                        }

                    }while (!found && !breaked);

                    if(!breaked)
                        getBinaryFileFromPeer(h, finalNombre, outputChannel);

                }else{
                    log.info("No matching files were returned! do another search");
                }
            }


        }catch (Exception e){
            log.info("An error happened while running the client",e);
        }

    }

    /**
     * Crea la conexion contra el peer servidor para realizar la descarga del archivo
     * @param h
     * @param nombre
     */
    private void getBinaryFileFromPeer(Host h, String nombre, ObjectOutputStream out) {

        log.info("Starting thread to download binary from peer in " + h.getIp() + ":" + h.getPort() );
        ClientDownloadThread c = new ClientDownloadThread(h,nombre,this.sharedFolder);
        c.start();
        try {
            c.join();

            Consulta c2 = new Consulta();
            c2.setFileName(c.getFileName());
            c2.setMethod(ADD_FILE);

            log.info("Updating server w new file downloaded");

            out.writeObject(c2);

            log.info("Update sucessfuly");

        } catch (InterruptedException | IOException e) {
            log.info("Error while joining download thread",e);
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
        wpl.setServerHost(new Host(this.serverPeerIp,InetAddress.getLocalHost().getHostAddress()));
        log.info("Registering server + files " + wpl.toString());
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
