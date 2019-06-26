package servidorM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import servidor.Server;





public class ServerM {
	
	
	private int port;
	private int cantidadN;
	
	public ServerM(int port) {
		this.port=port;
		this.startServer();
	}
	
	private void startServer() {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket (this.port);
			System.out.println(" Server started on port: "+this.port);
			
		
		
			while (true) {
				
				Socket client = ss.accept();
				System.out.println("Client connected from port: "+client.getInetAddress().getCanonicalHostName()+" : "+client.getPort());
				BufferedReader inputChannel = new BufferedReader (new InputStreamReader (client.getInputStream()));
				String msg = inputChannel.readLine();
				System.out.println("Cantidad de nodos "+msg);
				this.cantidadN = Integer.parseInt(msg);
				try {
					if (this.cantidadN == 4) {
						ServerImplementaM serverI = new ServerImplementaM();
						ServerImplementaM serverI1 = new ServerImplementaM();
						ServerImplementaM serverI2 = new ServerImplementaM();
						ServerImplementaM serverI3 = new ServerImplementaM();
						Registry registro = LocateRegistry.createRegistry(7001);
						Registry registro1 = LocateRegistry.createRegistry(7002);
						Registry registro2 = LocateRegistry.createRegistry(7003);
						Registry registro3 = LocateRegistry.createRegistry(7004);
						IRemoteM remotoS1 = (IRemoteM) UnicastRemoteObject.exportObject(serverI,5003);
						IRemoteM remotoS2 = (IRemoteM) UnicastRemoteObject.exportObject(serverI1,5004);
						IRemoteM remotoS3 = (IRemoteM) UnicastRemoteObject.exportObject(serverI2,5005);
						IRemoteM remotoS4 = (IRemoteM) UnicastRemoteObject.exportObject(serverI3,5006);
						registro.rebind("Sobel", remotoS1);
						registro1.rebind("Sobel", remotoS2);
						registro2.rebind("Sobel", remotoS3);
						registro3.rebind("Sobel", remotoS4);
					}
					if (this.cantidadN == 9) {
						
						ServerImplementaM serverI4 = new ServerImplementaM();
						ServerImplementaM serverI5 = new ServerImplementaM();
						ServerImplementaM serverI6 = new ServerImplementaM();
						ServerImplementaM serverI7 = new ServerImplementaM();
						ServerImplementaM serverI8 = new ServerImplementaM();
						ServerImplementaM serverI9= new ServerImplementaM();
						ServerImplementaM serverI10 = new ServerImplementaM();
						ServerImplementaM serverI11 = new ServerImplementaM();
						ServerImplementaM serverI12 = new ServerImplementaM();
						Registry registro4 = LocateRegistry.createRegistry(7001);
						Registry registro5 = LocateRegistry.createRegistry(7002);
						Registry registro6 = LocateRegistry.createRegistry(7003);
						Registry registro7 = LocateRegistry.createRegistry(7004);
						Registry registro8 = LocateRegistry.createRegistry(7005);
						Registry registro9 = LocateRegistry.createRegistry(7006);
						Registry registro10 = LocateRegistry.createRegistry(7007);
						Registry registro11 = LocateRegistry.createRegistry(7008);
						Registry registro12 = LocateRegistry.createRegistry(7009);
						IRemoteM remotoS5 = (IRemoteM) UnicastRemoteObject.exportObject(serverI4,5003);
						IRemoteM remotoS6 = (IRemoteM) UnicastRemoteObject.exportObject(serverI5,5004);
						IRemoteM remotoS7 = (IRemoteM) UnicastRemoteObject.exportObject(serverI6,5005);
						IRemoteM remotoS8 = (IRemoteM) UnicastRemoteObject.exportObject(serverI7,5006);
						IRemoteM remotoS9 = (IRemoteM) UnicastRemoteObject.exportObject(serverI8,5007);
						IRemoteM remotoS10 = (IRemoteM) UnicastRemoteObject.exportObject(serverI9,5008);
						IRemoteM remotoS11 = (IRemoteM) UnicastRemoteObject.exportObject(serverI10,5009);
						IRemoteM remotoS12 = (IRemoteM) UnicastRemoteObject.exportObject(serverI11,5010);
						IRemoteM remotoS13 = (IRemoteM) UnicastRemoteObject.exportObject(serverI12,5011);
						registro5.rebind("Sobel", remotoS5);
						registro6.rebind("Sobel", remotoS6);
						registro7.rebind("Sobel", remotoS7);
						registro8.rebind("Sobel", remotoS8);
						registro9.rebind("Sobel", remotoS9);
						registro10.rebind("Sobel", remotoS10);
						registro11.rebind("Sobel", remotoS11);
						registro12.rebind("Sobel", remotoS12);
						registro4.rebind("Sobel", remotoS13);
						
						
					}
					
					if (this.cantidadN == 16) {
						ServerImplementaM serverI = new ServerImplementaM();
						ServerImplementaM serverI1 = new ServerImplementaM();
						ServerImplementaM serverI2 = new ServerImplementaM();
						ServerImplementaM serverI3 = new ServerImplementaM();
						ServerImplementaM serverI4 = new ServerImplementaM();
						ServerImplementaM serverI5 = new ServerImplementaM();
						ServerImplementaM serverI6 = new ServerImplementaM();
						ServerImplementaM serverI7 = new ServerImplementaM();
						ServerImplementaM serverI8 = new ServerImplementaM();
						ServerImplementaM serverI9= new ServerImplementaM();
						ServerImplementaM serverI10 = new ServerImplementaM();
						ServerImplementaM serverI11 = new ServerImplementaM();
						ServerImplementaM serverI12 = new ServerImplementaM();
						ServerImplementaM serverI13 = new ServerImplementaM();
						ServerImplementaM serverI14 = new ServerImplementaM();
						ServerImplementaM serverI15 = new ServerImplementaM();
						Registry registro = LocateRegistry.createRegistry(7001);
						Registry registro1 = LocateRegistry.createRegistry(7002);
						Registry registro2 = LocateRegistry.createRegistry(7003);
						Registry registro3 = LocateRegistry.createRegistry(7004);
						Registry registro4 = LocateRegistry.createRegistry(7005);
						Registry registro5 = LocateRegistry.createRegistry(7006);
						Registry registro6 = LocateRegistry.createRegistry(7007);
						Registry registro7 = LocateRegistry.createRegistry(7008);
						Registry registro8 = LocateRegistry.createRegistry(7009);
						Registry registro9 = LocateRegistry.createRegistry(7010);
						Registry registro10 = LocateRegistry.createRegistry(7011);
						Registry registro11 = LocateRegistry.createRegistry(7012);
						Registry registro12 = LocateRegistry.createRegistry(7013);
						Registry registro13 = LocateRegistry.createRegistry(7014);
						Registry registro14 = LocateRegistry.createRegistry(7015);
						Registry registro15 = LocateRegistry.createRegistry(7016);
						IRemoteM remotoS1 = (IRemoteM) UnicastRemoteObject.exportObject(serverI,5003);
						IRemoteM remotoS2 = (IRemoteM) UnicastRemoteObject.exportObject(serverI1,5004);
						IRemoteM remotoS3 = (IRemoteM) UnicastRemoteObject.exportObject(serverI2,5005);
						IRemoteM remotoS4 = (IRemoteM) UnicastRemoteObject.exportObject(serverI3,5006);
						IRemoteM remotoS5 = (IRemoteM) UnicastRemoteObject.exportObject(serverI4,5003);
						IRemoteM remotoS6 = (IRemoteM) UnicastRemoteObject.exportObject(serverI5,5004);
						IRemoteM remotoS7 = (IRemoteM) UnicastRemoteObject.exportObject(serverI6,5005);
						IRemoteM remotoS8 = (IRemoteM) UnicastRemoteObject.exportObject(serverI7,5006);
						IRemoteM remotoS9 = (IRemoteM) UnicastRemoteObject.exportObject(serverI8,5007);
						IRemoteM remotoS10 = (IRemoteM) UnicastRemoteObject.exportObject(serverI9,5008);
						IRemoteM remotoS11 = (IRemoteM) UnicastRemoteObject.exportObject(serverI10,5009);
						IRemoteM remotoS12 = (IRemoteM) UnicastRemoteObject.exportObject(serverI11,5010);
						IRemoteM remotoS13 = (IRemoteM) UnicastRemoteObject.exportObject(serverI12,5011);
						IRemoteM remotoS14 = (IRemoteM) UnicastRemoteObject.exportObject(serverI13,5009);
						IRemoteM remotoS15 = (IRemoteM) UnicastRemoteObject.exportObject(serverI14,5010);
						IRemoteM remotoS16 = (IRemoteM) UnicastRemoteObject.exportObject(serverI15,5011);
						registro.rebind("Sobel", remotoS1);
						registro1.rebind("Sobel", remotoS2);
						registro2.rebind("Sobel", remotoS3);
						registro3.rebind("Sobel", remotoS4);
						registro5.rebind("Sobel", remotoS5);
						registro6.rebind("Sobel", remotoS6);
						registro7.rebind("Sobel", remotoS7);
						registro8.rebind("Sobel", remotoS8);
						registro9.rebind("Sobel", remotoS9);
						registro10.rebind("Sobel", remotoS10);
						registro11.rebind("Sobel", remotoS11);
						registro12.rebind("Sobel", remotoS12);
						registro4.rebind("Sobel", remotoS13);
						registro13.rebind("Sobel", remotoS14);
						registro14.rebind("Sobel", remotoS15);
						registro15.rebind("Sobel", remotoS16);
					}				
					}catch(Exception e) {
						e.printStackTrace();
					}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(" port in use");
		} 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerM servidor = new ServerM(5000);
		Server servidoraux = new Server(5000);
		
			
		
	}

}
